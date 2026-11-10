package ru.cinema.api;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import ru.cinema.dto.MoviePageDto;
import ru.cinema.dto.ProblemDto;
import ru.cinema.dto.SessionDetailsDto;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

/**
 * A delegate to be called by the {@link CatalogApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.12.0")
public interface CatalogApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /api/v1/sessions/{sessionId} : Получить один сеанс и схему зала с доступностью мест
     * Доступность является снимком; окончательная проверка мест выполняется атомарно при бронировании.
     *
     * @param sessionId Публичный идентификатор сеанса. (required)
     * @return Сеанс, фильм и схема зала. (status code 200)
     *         or Некорректный UUID, дата, пагинация или тело запроса; нарушены ограничения DTO. (status code 400)
     *         or Сеанс с указанным идентификатором не существует. (status code 404)
     *         or Непредвиденная ошибка сервера; внутренние подробности не раскрываются. (status code 500)
     *         or Операция описана контрактом, но бизнес-логика каркаса ещё не реализована. (status code 501)
     * @see CatalogApi#getSession
     */
    default ResponseEntity<SessionDetailsDto> getSession(UUID sessionId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"movie\" : { \"durationMinutes\" : 169, \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"ageRating\" : \"0+\", \"title\" : \"Интерстеллар\" }, \"session\" : { \"startsAt\" : \"2026-11-15T19:00:00+05:00\", \"priceKopecks\" : 45000, \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"hallName\" : \"Зал 1\" }, \"seats\" : [ { \"number\" : 1, \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"row\" : 1, \"status\" : \"AVAILABLE\" }, { \"number\" : 1, \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"row\" : 1, \"status\" : \"AVAILABLE\" } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/problem+json"))) {
                    String exampleString = "Custom MIME type example not yet supported: application/problem+json";
                    ApiUtil.setExampleResponse(request, "application/problem+json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/problem+json"))) {
                    String exampleString = "Custom MIME type example not yet supported: application/problem+json";
                    ApiUtil.setExampleResponse(request, "application/problem+json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/problem+json"))) {
                    String exampleString = "Custom MIME type example not yet supported: application/problem+json";
                    ApiUtil.setExampleResponse(request, "application/problem+json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/problem+json"))) {
                    String exampleString = "Custom MIME type example not yet supported: application/problem+json";
                    ApiUtil.setExampleResponse(request, "application/problem+json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /api/v1/movies : Получить афишу с расписанием сеансов на дату
     * Возвращает фильмы, имеющие сеансы в выбранную календарную дату по часовому поясу кинотеатра Asia/Yekaterinburg. Фильмы отсортированы по title, затем id; сеансы — по startsAt, затем id. Страница за границей выборки содержит пустой items. 
     *
     * @param date Дата афиши по местному времени кинотеатра. (required)
     * @param page Номер страницы, начиная с нуля. (optional, default to 0)
     * @param size Максимальное количество фильмов на странице. (optional, default to 20)
     * @return Страница афиши; при отсутствии сеансов items пуст. (status code 200)
     *         or Некорректный UUID, дата, пагинация или тело запроса; нарушены ограничения DTO. (status code 400)
     *         or Непредвиденная ошибка сервера; внутренние подробности не раскрываются. (status code 500)
     *         or Операция описана контрактом, но бизнес-логика каркаса ещё не реализована. (status code 501)
     * @see CatalogApi#listMovies
     */
    default ResponseEntity<MoviePageDto> listMovies(LocalDate date,
        Integer page,
        Integer size) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"size\" : 60, \"page\" : 0, \"items\" : [ { \"sessions\" : [ { \"startsAt\" : \"2026-11-15T19:00:00+05:00\", \"priceKopecks\" : 45000, \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"hallName\" : \"Зал 1\" }, { \"startsAt\" : \"2026-11-15T19:00:00+05:00\", \"priceKopecks\" : 45000, \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"hallName\" : \"Зал 1\" } ], \"movie\" : { \"durationMinutes\" : 169, \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"ageRating\" : \"0+\", \"title\" : \"Интерстеллар\" } }, { \"sessions\" : [ { \"startsAt\" : \"2026-11-15T19:00:00+05:00\", \"priceKopecks\" : 45000, \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"hallName\" : \"Зал 1\" }, { \"startsAt\" : \"2026-11-15T19:00:00+05:00\", \"priceKopecks\" : 45000, \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"hallName\" : \"Зал 1\" } ], \"movie\" : { \"durationMinutes\" : 169, \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"ageRating\" : \"0+\", \"title\" : \"Интерстеллар\" } } ], \"totalElements\" : 0 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/problem+json"))) {
                    String exampleString = "Custom MIME type example not yet supported: application/problem+json";
                    ApiUtil.setExampleResponse(request, "application/problem+json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/problem+json"))) {
                    String exampleString = "Custom MIME type example not yet supported: application/problem+json";
                    ApiUtil.setExampleResponse(request, "application/problem+json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/problem+json"))) {
                    String exampleString = "Custom MIME type example not yet supported: application/problem+json";
                    ApiUtil.setExampleResponse(request, "application/problem+json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
