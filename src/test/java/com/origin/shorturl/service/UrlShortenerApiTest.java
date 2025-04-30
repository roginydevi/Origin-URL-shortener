package com.origin.shorturl.service;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class UrlShortenerApiTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    public void testShortenUrl_Success() {
        String payload = "{ \"url\": \"https://localhost:8080/origin/longurl/code/sample\" }";

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/shorten-url")
        .then()
                .statusCode(200)
                .body("url", equalTo("https://localhost:8080/origin/longurl/code/sample"))
                .body("shortenUrl", startsWith("http://localhost:8080/"));
    }

    @Test
    public void testShortenUrl_InvalidUrl() {
        String payload = "{ \"url\": \"htp:/not-a-url\" }";

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/shorten-url")
        .then()
                .statusCode(400)
                .body(containsString("Invalid URL format"));
    }
}
