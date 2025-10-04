package com.example;

import static spark.Spark.*;

import com.google.gson.Gson;

public class Application {

    public static void main(String[] args) {
        port(getPort());

        // Simple health endpoint
        get("/health", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(new Health("UP"));
        });

        // Root endpoint returns simple JSON
        get("/", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(new Message("Hello from example-service!"));
        });

        // Example /env endpoint that reads env vars or config
        get("/env", (req, res) -> {
            res.type("application/json");
            String profile = System.getenv().getOrDefault("APP_PROFILE", "dev");
            return new Gson().toJson(new Env(profile));
        });
    }

    private static int getPort() {
        String p = System.getenv("PORT");
        if (p != null) {
            try { return Integer.parseInt(p); } catch (NumberFormatException ignored) {}
        }
        return 8080;
    }

    static class Health {
        String status;
        Health(String status) { this.status = status; }
    }

    static class Message {
        String message;
        Message(String message) { this.message = message; }
    }

    static class Env {
        String profile;
        Env(String profile) { this.profile = profile; }
    }
}
