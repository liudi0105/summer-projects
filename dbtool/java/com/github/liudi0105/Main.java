package com.github.liudi0105;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class Main {
    public static void main(String[] args) {
    }

    public void start() {
        HttpServer server = Vertx.vertx().createHttpServer();

        Router router = Router.router(Vertx.vertx());

        server.requestHandler(router).listen();
    }
}