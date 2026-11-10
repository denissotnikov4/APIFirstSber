package ru.cinema.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;

class JwtValidationTest {
    private static final String ISSUER = "https://issuer.example";
    private static RSAKey key;
    private static HttpServer jwksServer;
    private static JwtDecoder decoder;

    @BeforeAll
    static void setUp() throws Exception {
        key = new RSAKeyGenerator(2048).keyID("test-key").generate();
        byte[] jwks = new JWKSet(key.toPublicJWK()).toString().getBytes(StandardCharsets.UTF_8);
        jwksServer = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        jwksServer.createContext("/jwks", exchange -> {
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, jwks.length);
            try (var output = exchange.getResponseBody()) { output.write(jwks); }
        });
        jwksServer.start();
        decoder = new SecurityConfiguration().jwtDecoder(ISSUER,
                "http://127.0.0.1:" + jwksServer.getAddress().getPort() + "/jwks", "cinema-api");
    }

    @AfterAll
    static void tearDown() {
        if (jwksServer != null) { jwksServer.stop(0); }
    }

    @Test
    void acceptsSignedTokenWithExpectedClaims() throws Exception {
        assertThat(decoder.decode(sign(claims().build(), key)).getSubject()).isEqualTo("customer-123");
    }

    @ParameterizedTest
    @ValueSource(strings = {"issuer", "audience", "expired", "no-expiry", "no-subject", "not-yet-valid"})
    void rejectsInvalidClaims(String invalidClaim) throws Exception {
        JWTClaimsSet.Builder claims = claims();
        switch (invalidClaim) {
            case "issuer" -> claims.issuer("https://another-issuer.example");
            case "audience" -> claims.audience("another-service");
            case "expired" -> claims.expirationTime(Date.from(Instant.now().minusSeconds(300)));
            case "no-expiry" -> claims.expirationTime(null);
            case "no-subject" -> claims.subject(null);
            case "not-yet-valid" -> claims.notBeforeTime(Date.from(Instant.now().plusSeconds(300)));
            default -> throw new IllegalArgumentException(invalidClaim);
        }
        String token = sign(claims.build(), key);
        assertThatThrownBy(() -> decoder.decode(token)).isInstanceOf(JwtException.class);
    }

    @Test
    void rejectsInvalidSignature() throws Exception {
        RSAKey otherKey = new RSAKeyGenerator(2048).keyID("test-key").generate();
        String token = sign(claims().build(), otherKey);
        assertThatThrownBy(() -> decoder.decode(token)).isInstanceOf(JwtException.class);
    }

    @Test
    void unconfiguredProviderIsClosedAndPartialConfigurationFailsFast() {
        SecurityConfiguration configuration = new SecurityConfiguration();
        assertThatThrownBy(() -> configuration.jwtDecoder("", "", "").decode("anything"))
                .isInstanceOf(JwtException.class);
        assertThatThrownBy(() -> configuration.jwtDecoder(ISSUER, "", "cinema-api"))
                .isInstanceOf(IllegalStateException.class);
    }

    private static JWTClaimsSet.Builder claims() {
        return new JWTClaimsSet.Builder().issuer(ISSUER).audience("cinema-api").subject("customer-123")
                .issueTime(Date.from(Instant.now())).expirationTime(Date.from(Instant.now().plusSeconds(300)));
    }

    private static String sign(JWTClaimsSet claims, RSAKey signingKey) throws Exception {
        SignedJWT jwt = new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(signingKey.getKeyID()).build(), claims);
        jwt.sign(new RSASSASigner(signingKey));
        return jwt.serialize();
    }
}
