package ru.cinema.application;

import java.time.LocalDate;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.cinema.api.CatalogApiDelegate;
import ru.cinema.dto.MoviePageDto;
import ru.cinema.dto.SessionDetailsDto;

/** Replace with a use-case service once persistence and catalogue logic are implemented. */
@Service
public class CatalogStub implements CatalogApiDelegate {
    @Override
    public ResponseEntity<MoviePageDto> listMovies(LocalDate date, Integer page, Integer size) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    public ResponseEntity<SessionDetailsDto> getSession(UUID sessionId) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }
}
