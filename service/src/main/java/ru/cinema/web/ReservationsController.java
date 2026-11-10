package ru.cinema.web;

import org.springframework.web.bind.annotation.RestController;
import ru.cinema.api.ReservationsApi;
import ru.cinema.api.ReservationsApiDelegate;

@RestController
public class ReservationsController implements ReservationsApi {
    private final ReservationsApiDelegate delegate;

    public ReservationsController(ReservationsApiDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public ReservationsApiDelegate getDelegate() {
        return delegate;
    }
}
