package ru.cinema.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.BadJwtException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import ru.cinema.web.ProblemResponses;

@Configuration
public class SecurityConfiguration {
    @Bean
    SecurityFilterChain apiSecurity(HttpSecurity http, ProblemResponses problems) throws Exception {
        AuthenticationEntryPoint unauthorized = (request, response, exception) -> {
            response.setHeader(HttpHeaders.WWW_AUTHENTICATE, "Bearer");
            problems.write(HttpStatus.UNAUTHORIZED, request, response);
        };
        AccessDeniedHandler forbidden = (request, response, exception) ->
                problems.write(HttpStatus.FORBIDDEN, request, response);

        return http
                // Stateless Authorization: Bearer only; no cookie or session authentication.
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .requestCache(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(access -> access
                        .requestMatchers(HttpMethod.GET, "/", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**",
                                "/api/v1/movies", "/api/v1/sessions/*").permitAll()
                        .requestMatchers("/api/v1/**").authenticated()
                        .anyRequest().denyAll())
                .exceptionHandling(errors -> errors.authenticationEntryPoint(unauthorized).accessDeniedHandler(forbidden))
                .oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults())
                        .authenticationEntryPoint(unauthorized).accessDeniedHandler(forbidden))
                .build();
    }

    @Bean
    JwtDecoder jwtDecoder(@Value("${cinema.security.issuer}") String issuer,
            @Value("${cinema.security.jwk-set-uri}") String jwkSetUri,
            @Value("${cinema.security.audience}") String audience) {
        if (issuer.isBlank() && jwkSetUri.isBlank() && audience.isBlank()) {
            return token -> { throw new BadJwtException("JWT provider is not configured"); };
        }
        if (issuer.isBlank() || jwkSetUri.isBlank() || audience.isBlank()) {
            throw new IllegalStateException("Configure JWT_ISSUER, JWT_JWK_SET_URI and JWT_AUDIENCE together");
        }
        NimbusJwtDecoder decoder = NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
        OAuth2TokenValidator<Jwt> audienceValidator = token -> token.getAudience().contains(audience)
                ? OAuth2TokenValidatorResult.success()
                : OAuth2TokenValidatorResult.failure(new OAuth2Error("invalid_token", "Invalid audience", null));
        OAuth2TokenValidator<Jwt> requiredClaims = token -> token.getExpiresAt() != null
                && token.getSubject() != null && !token.getSubject().isBlank()
                ? OAuth2TokenValidatorResult.success()
                : OAuth2TokenValidatorResult.failure(new OAuth2Error("invalid_token", "Missing required claims", null));
        decoder.setJwtValidator(new DelegatingOAuth2TokenValidator<>(
                JwtValidators.createDefaultWithIssuer(issuer), audienceValidator, requiredClaims));
        return decoder;
    }
}
