package ru.cinema.application;

import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.cinema.api.ReservationsApiDelegate;
import ru.cinema.dto.CreateReservationDto;
import ru.cinema.dto.ReservationDto;
import ru.cinema.dto.UpdateReservationDto;

/** No simulated payment or in-memory reservation store in the generated skeleton. */
@Service
public class ReservationsStub implements ReservationsApiDelegate {
    @Override
    public ResponseEntity<ReservationDto> createReservation(UUID idempotencyKey, CreateReservationDto request) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<ReservationDto> updateReservation(UUID reservationId, UpdateReservationDto request) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<ReservationDto> cancelReservation(UUID reservationId) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }
}
