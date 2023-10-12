package org.example.controller;

import ru.tinkoff.kora.common.Component;
import ru.tinkoff.kora.http.common.HttpHeaders;
import ru.tinkoff.kora.http.common.HttpMethod;
import ru.tinkoff.kora.http.common.annotation.HttpRoute;
import ru.tinkoff.kora.http.server.common.HttpServerResponse;
import ru.tinkoff.kora.http.server.common.SimpleHttpServerResponse;
import ru.tinkoff.kora.http.server.common.annotation.HttpController;

import java.nio.charset.StandardCharsets;

@Component
@HttpController
public final class HelloWorldController {
    @HttpRoute(method = HttpMethod.GET, path = "/hello/world")
    public HttpServerResponse helloWorld() {
        return new SimpleHttpServerResponse(
                200,
                "text/plain",
                HttpHeaders.of(),
                StandardCharsets.UTF_8.encode("Hello world")
        );
    }
}

