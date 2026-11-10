package ru.cinema.web;

import org.springframework.web.bind.annotation.RestController;
import ru.cinema.api.CatalogApi;
import ru.cinema.api.CatalogApiDelegate;

@RestController
public class CatalogController implements CatalogApi {
    private final CatalogApiDelegate delegate;

    public CatalogController(CatalogApiDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public CatalogApiDelegate getDelegate() {
        return delegate;
    }
}
