package com.origin.shorturl.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class UrlControllerTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    public void testShortenUrl_validUrl_shouldReturnShortenedUrl() {
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
    public void testShortenUrl_invalidUrl_shouldReturnBadRequest() {
        String payload = "{ \"url\": \"htp:/bad-url\" }";

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
        .when()
                .post("/shorten-url")
        .then()
                .statusCode(400)
                .body(containsString("Invalid URL"));
    }

    @Test
    public void testRedirect_existingId_shouldReturn302() {
        RestAssured.given()
        .when()
                .get("/abc123")
        .then()
                .statusCode(302)
                .header("Location", startsWith("https://"));
    }

    @Test
    public void testGetDummyPath_shouldReturnMessage() {
        RestAssured.given()
        .when()
                .get("/a/b/c/d")
        .then()
                .statusCode(200)
                .body(containsString("Redirected Successfully to OriginalUrl"));
    }
}
