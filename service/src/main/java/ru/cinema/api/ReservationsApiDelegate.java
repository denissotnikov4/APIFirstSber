package ru.cinema.api;

import ru.cinema.dto.CreateReservationDto;
import ru.cinema.dto.ProblemDto;
import ru.cinema.dto.ReservationDto;
import java.util.UUID;
import ru.cinema.dto.UpdateReservationDto;
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
 * A delegate to be called by the {@link ReservationsApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.12.0")
public interface ReservationsApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * DELETE /api/v1/reservations/{reservationId} : Отменить неоплаченную бронь и освободить места
     * Переводит HELD в CANCELLED. Повторная отмена возвращает 200 с тем же результатом. Оплаченные и истёкшие брони возвращают 409; возврат купленных билетов не входит в этот API. Запись сохраняется для повторных запросов. 
     *
     * @param reservationId Публичный идентификатор бронирования. (required)
     * @return Бронь отменена, статус CANCELLED. (status code 200)
     *         or Некорректный UUID, дата, пагинация или тело запроса; нарушены ограничения DTO. (status code 400)
     *         or Не знаю, кто ты — JWT отсутствует, недействителен или истёк. (status code 401)
     *         or Знаю, кто ты, но нельзя — пользователь аутентифицирован, однако бронь принадлежит другому покупателю. (status code 403)
     *         or Бронь с указанным идентификатором не существует. (status code 404)
     *         or Бронь уже оплачена или истекла. (status code 409)
     *         or Непредвиденная ошибка сервера; внутренние подробности не раскрываются. (status code 500)
     *         or Операция описана контрактом, но бизнес-логика каркаса ещё не реализована. (status code 501)
     * @see ReservationsApi#cancelReservation
     */
    default ResponseEntity<ReservationDto> cancelReservation(UUID reservationId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"seatIds\" : [ \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" ], \"tickets\" : [ { \"admissionCode\" : \"admissionCode\", \"seatId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"admissionCode\" : \"admissionCode\", \"seatId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"sessionId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"totalKopecks\" : 0, \"expiresAt\" : \"2000-01-23T04:56:07.000+00:00\", \"status\" : \"HELD\" }";
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
     * POST /api/v1/reservations : Забронировать места на сеанс на 15 минут
     * Все места резервируются атомарно либо возвращается 409. Цена рассчитывается сервером. Первый запрос возвращает 201, повтор с тем же ключом и теми же sessionId и множеством seatIds возвращает 200 и актуальную бронь, не продлевая её срок. Другое содержимое с тем же ключом — 409. Ключ действует 24 часа в рамках пользователя; параллельные повторы не создают дополнительные брони. 
     *
     * @param idempotencyKey Уникальный ключ запроса для безопасного повтора создания брони. (required)
     * @param createReservationDto  (required)
     * @return Бронь создана; Location указывает URI созданного ресурса. (status code 201)
     *         or Повторный запрос; возвращена существующая бронь. (status code 200)
     *         or Некорректный UUID, дата, пагинация или тело запроса; нарушены ограничения DTO. (status code 400)
     *         or Не знаю, кто ты — JWT отсутствует, недействителен или истёк. (status code 401)
     *         or Сеанс или одно из мест в его зале не найдены. (status code 404)
     *         or Места заняты, сеанс уже начался или ключ повторно использован с другим телом. (status code 409)
     *         or Непредвиденная ошибка сервера; внутренние подробности не раскрываются. (status code 500)
     *         or Операция описана контрактом, но бизнес-логика каркаса ещё не реализована. (status code 501)
     * @see ReservationsApi#createReservation
     */
    default ResponseEntity<ReservationDto> createReservation(UUID idempotencyKey,
        CreateReservationDto createReservationDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"seatIds\" : [ \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" ], \"tickets\" : [ { \"admissionCode\" : \"admissionCode\", \"seatId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"admissionCode\" : \"admissionCode\", \"seatId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"sessionId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"totalKopecks\" : 0, \"expiresAt\" : \"2000-01-23T04:56:07.000+00:00\", \"status\" : \"HELD\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"seatIds\" : [ \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" ], \"tickets\" : [ { \"admissionCode\" : \"admissionCode\", \"seatId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"admissionCode\" : \"admissionCode\", \"seatId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"sessionId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"totalKopecks\" : 0, \"expiresAt\" : \"2000-01-23T04:56:07.000+00:00\", \"status\" : \"HELD\" }";
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
     * PATCH /api/v1/reservations/{reservationId} : Подтвердить покупку забронированных билетов
     * Частичное обновление application/json по схеме UpdateReservationDto (не JSON Patch). Единственное разрешённое изменение — status&#x3D;PAID с paymentReference. Переводит HELD в PAID и выдаёт билеты. Сервис проверяет подтверждение оплаты у платёжного провайдера: сумму, валюту и привязку к брони. Карточные данные не передаются. Повтор с тем же paymentReference возвращает те же билеты. Смена подтверждения у оплаченной брони запрещена (409). Проверка срока, переход состояния и выпуск билетов выполняются атомарно; повтор не выпускает новые билеты. Конкурирующие покупка и отмена не могут обе завершиться успешно. 
     *
     * @param reservationId Публичный идентификатор бронирования. (required)
     * @param updateReservationDto  (required)
     * @return Оплаченная бронь с билетами, включая повторное подтверждение. (status code 200)
     *         or Некорректный UUID, дата, пагинация или тело запроса; нарушены ограничения DTO. (status code 400)
     *         or Не знаю, кто ты — JWT отсутствует, недействителен или истёк. (status code 401)
     *         or Знаю, кто ты, но нельзя — пользователь аутентифицирован, однако бронь принадлежит другому покупателю. (status code 403)
     *         or Бронь с указанным идентификатором не существует. (status code 404)
     *         or Бронь истекла, отменена, сеанс начался или подтверждение оплаты не соответствует брони. (status code 409)
     *         or Непредвиденная ошибка сервера; внутренние подробности не раскрываются. (status code 500)
     *         or Операция описана контрактом, но бизнес-логика каркаса ещё не реализована. (status code 501)
     * @see ReservationsApi#updateReservation
     */
    default ResponseEntity<ReservationDto> updateReservation(UUID reservationId,
        UpdateReservationDto updateReservationDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"seatIds\" : [ \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" ], \"tickets\" : [ { \"admissionCode\" : \"admissionCode\", \"seatId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" }, { \"admissionCode\" : \"admissionCode\", \"seatId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\" } ], \"id\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"sessionId\" : \"046b6c7f-0b8a-43b9-b35d-6489e6daee91\", \"totalKopecks\" : 0, \"expiresAt\" : \"2000-01-23T04:56:07.000+00:00\", \"status\" : \"HELD\" }";
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
