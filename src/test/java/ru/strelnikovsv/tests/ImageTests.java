package ru.strelnikovsv.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ImageTests extends BaseTest {

    @Test
    @DisplayName("Загрузка картинки 100 Кб ")
    void uploadFileImage100Tests() {
        Response response = given()
                .header("Authorization", token)
                .multiPart("image", new File(PATH_TO_IMAGE))
                .log()
                .method()
                .log()
                .uri()
                .when()
                .post("https://api.imgur.com/3/upload")
                .prettyPeek();
        assertThat(response.jsonPath().get("data.type"), equalTo("image/jpeg"));
        assertNotNull(response.jsonPath().get("data.id"));
        assertThat(response.jsonPath().get("data.size"), equalTo(96590));
        assertThat(response.jsonPath().get("status"), equalTo(200));
    }

    @Test
    @DisplayName("Загрузка картинки 1 Кб и размером 20x14")
    void uploadFileImageMicroTests() {
        String PATH_TO_IMAGE_MICRO = "src/test/resources/micro.jpg";
        Response response = given()
                .header("Authorization", token)
                .multiPart("image", new File(PATH_TO_IMAGE_MICRO))
                .log()
                .method()
                .log()
                .uri()
                .when()
                .post("https://api.imgur.com/3/upload")
                .prettyPeek();
        assertThat(response.jsonPath().get("data.type"), equalTo("image/jpeg"));
        assertNotNull(response.jsonPath().get("data.id"));
        assertThat(response.jsonPath().get("data.width"), equalTo(20));
        assertThat(response.jsonPath().get("data.height"), equalTo(14));
        assertThat(response.jsonPath().get("data.size"), equalTo(413));
        assertThat(response.jsonPath().get("status"), equalTo(200));
    }

    @Test
    @DisplayName("Загрузка картинки GIF")
    void uploadFileGIFTests() {
        Response response = given()
                .header("Authorization", token)
                .multiPart("image", new File(PATH_TO_GIF))
                .log()
                .method()
                .log()
                .uri()
                .when()
                .post("https://api.imgur.com/3/upload")
                .prettyPeek();
        assertThat(response.jsonPath().get("data.type"), equalTo("image/gif"));
        assertNotNull(response.jsonPath().get("data.id"));
        assertThat(response.jsonPath().get("status"), equalTo(200));
    }


    @Test
    @DisplayName("Загрузка видео в поле картинки")
    void uploadFileVideoToImageTests() {
        Response response = given()
                .header("Authorization", token)
                .multiPart("image", new File(PATH_TO_VIDEO))
                .log()
                .method()
                .log()
                .uri()
                .when()
                .post("https://api.imgur.com/3/upload")
                .prettyPeek();
        assertThat(response.jsonPath().get("data.type"), equalTo("video/mp4"));
        assertNotNull(response.jsonPath().get("data.id"));
        assertThat(response.jsonPath().get("status"), equalTo(200));
    }

    @Test
    @DisplayName("Загрузка документа в поле картинки")
    void uploadFileDocToImageTests() {
        Response response = given()
                .header("Authorization", token)
                .multiPart("image", new File(PATH_TO_DOC))
                .log()
                .method()
                .log()
                .uri()
                .when()
                .post("https://api.imgur.com/3/upload")
                .prettyPeek();
        assertThat(response.jsonPath().get("data.error"), equalTo("We don't support that file type!"));
        assertThat(response.jsonPath().get("success"), equalTo(false));
        assertThat(response.jsonPath().get("status"), equalTo(400));
    }

    @Test
    @DisplayName("Загрузка MP3 в поле картинки")
    void uploadFileMP3ToImageTests() {
        Response response = given()
                .header("Authorization", token)
                .multiPart("image", new File(PATH_TO_MP3))
                .log()
                .method()
                .log()
                .uri()
                .when()
                .post("https://api.imgur.com/3/upload")
                .prettyPeek();
        assertThat(response.jsonPath().get("data.error"), equalTo("Internal expectation failed"));
        assertThat(response.jsonPath().get("success"), equalTo(false));
        assertThat(response.jsonPath().get("status"), equalTo(417));
    }

}
