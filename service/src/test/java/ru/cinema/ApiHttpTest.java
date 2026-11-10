package ru.cinema;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(properties = {"cinema.security.issuer=", "cinema.security.jwk-set-uri=", "cinema.security.audience="})
@AutoConfigureMockMvc
class ApiHttpTest {
    private static final String ID = "550e8400-e29b-41d4-a716-446655440000";
    private static final String RESERVATIONS = "/api/v1/reservations";
    private static final String VALID_CREATE = "{\"sessionId\":\"" + ID + "\",\"seatIds\":[\"" + ID + "\"]}";

    @Autowired
    private MockMvc mvc;

    @Test
    void publicCatalogueReturnsDocumentedSkeletonProblem() throws Exception {
        mvc.perform(get("/api/v1/movies").param("date", "2026-11-15"))
                .andExpect(status().isNotImplemented())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.type").value("about:blank"))
                .andExpect(jsonPath("$.title").value("Not Implemented"))
                .andExpect(jsonPath("$.status").value(501))
                .andExpect(jsonPath("$.code").value("NOT_IMPLEMENTED"))
                .andExpect(jsonPath("$.detail").isString())
                .andExpect(jsonPath("$.instance").value("/api/v1/movies"))
                .andExpect(jsonPath("$.trace").doesNotExist())
                .andExpect(header().string("Cache-Control", containsString("no-store")));
    }

    @ParameterizedTest
    @ValueSource(strings = {"/api/v1/movies", "/api/v1/movies?date=invalid", "/api/v1/movies?date=2026-11-15&page=-1",
            "/api/v1/movies?date=2026-11-15&size=101", "/api/v1/sessions/not-a-uuid"})
    void invalidPublicInputIsAProblem(String uri) throws Exception {
        mvc.perform(get(uri)).andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.code").value("INVALID_REQUEST"));
    }

    @Test
    void missingTokenReturns401WithChallengeBeforeParsingBody() throws Exception {
        mvc.perform(post(RESERVATIONS).contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isUnauthorized())
                .andExpect(header().string("WWW-Authenticate", "Bearer"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.status").value(401))
                .andExpect(jsonPath("$.code").value("UNAUTHORIZED"));
    }

    @Test
    void invalidBearerIsRejectedByRealFilterChain() throws Exception {
        mvc.perform(delete(RESERVATIONS + "/" + ID).header("Authorization", "Bearer invalid-token"))
                .andExpect(status().isUnauthorized()).andExpect(jsonPath("$.status").value(401));
    }

    @Test
    void authenticatedAccessDeniedIs403RatherThan401() throws Exception {
        mvc.perform(get("/internal").with(jwt()))
                .andExpect(status().isForbidden())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.status").value(403)).andExpect(jsonPath("$.code").value("FORBIDDEN"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"{}", "{\"sessionId\":null,\"seatIds\":[]}",
            "{\"sessionId\":\"550e8400-e29b-41d4-a716-446655440000\",\"seatIds\":[null]}",
            "{\"sessionId\":\"550e8400-e29b-41d4-a716-446655440000\",\"seatIds\":[\"550e8400-e29b-41d4-a716-446655440000\",\"550e8400-e29b-41d4-a716-446655440000\"]}"})
    void invalidReservationBodyIsRejected(String body) throws Exception {
        mvc.perform(post(RESERVATIONS).with(jwt()).header("Idempotency-Key", ID)
                        .contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.code").value("INVALID_REQUEST"));
    }

    @Test
    void unknownFieldsCannotSetServerCalculatedPrice() throws Exception {
        String body = VALID_CREATE.substring(0, VALID_CREATE.length() - 1) + ",\"totalKopecks\":1}";
        mvc.perform(post(RESERVATIONS).with(jwt()).header("Idempotency-Key", ID)
                        .contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isBadRequest()).andExpect(jsonPath("$.detail").value("Request parameters or body are invalid."));
    }

    @Test
    void missingIdempotencyKeyIsRejected() throws Exception {
        mvc.perform(post(RESERVATIONS).with(jwt()).contentType(MediaType.APPLICATION_JSON).content(VALID_CREATE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void validCreateReachesDelegateWithoutCsrfOrSession() throws Exception {
        mvc.perform(post(RESERVATIONS).with(jwt()).header("Idempotency-Key", ID)
                        .contentType(MediaType.APPLICATION_JSON).content(VALID_CREATE))
                .andExpect(status().isNotImplemented()).andExpect(jsonPath("$.code").value("NOT_IMPLEMENTED"))
                .andExpect(cookie().doesNotExist("JSESSIONID"));
    }

    @Test
    void patchUpdatesStateWhilePutIsNotMapped() throws Exception {
        mvc.perform(patch(RESERVATIONS + "/" + ID).with(jwt()).contentType(MediaType.APPLICATION_JSON)
                        .content("{\"status\":\"PAID\",\"paymentReference\":\"pay_123\"}"))
                .andExpect(status().isNotImplemented());
        mvc.perform(put(RESERVATIONS + "/" + ID).with(jwt()).contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isMethodNotAllowed()).andExpect(jsonPath("$.status").value(405));
    }

    @ParameterizedTest
    @ValueSource(strings = {"{\"status\":\"CANCELLED\",\"paymentReference\":\"pay_123\"}",
            "{\"status\":\"PAID\",\"paymentReference\":\" \"}", "{\"status\":\"PAID\"}"})
    void patchRequiresSupportedStateAndPayment(String body) throws Exception {
        mvc.perform(patch(RESERVATIONS + "/" + ID).with(jwt()).contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void unsupportedMediaTypeUsesProblemDetails() throws Exception {
        mvc.perform(post(RESERVATIONS).with(jwt()).header("Idempotency-Key", ID)
                        .contentType(MediaType.TEXT_PLAIN).content(VALID_CREATE))
                .andExpect(status().isUnsupportedMediaType()).andExpect(jsonPath("$.status").value(415));
    }

    @Test
    void swaggerDocumentsNewContractAndIsPublic() throws Exception {
        mvc.perform(get("/v3/api-docs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paths.*", hasSize(4)))
                .andExpect(jsonPath("$.paths['/api/v1/reservations'].post.responses['201']").exists())
                .andExpect(jsonPath("$.paths['/api/v1/reservations'].post.responses['200']").exists())
                .andExpect(jsonPath("$.paths['/api/v1/reservations/{reservationId}'].patch").exists())
                .andExpect(jsonPath("$.paths['/api/v1/reservations/{reservationId}'].put").doesNotExist())
                .andExpect(jsonPath("$.components.schemas.ProblemDto").exists());
        mvc.perform(get("/swagger-ui/index.html")).andExpect(status().isOk());
    }
}
