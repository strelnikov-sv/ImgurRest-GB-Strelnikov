package ru.strelnikovsv.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

class VideoTests extends BaseTest {

    @Test
    @DisplayName("Загрузка видео больше 200 МБ")
    void uploadFileVideoTests() {
        String PATH_TO_VIDEO200 = "src/test/resources/video_more_200.mp4";
        Response response = given()
                .header("Authorization", token)
                .multiPart("video", new File(PATH_TO_VIDEO200))
                .log()
                .method()
                .log()
                .uri()
                .when()
                .post("https://api.imgur.com/3/upload")
                .prettyPeek();
        assertThat(response.jsonPath().get("data.error"), equalTo("File is over the size limit"));
        assertThat(response.jsonPath().get("success"), equalTo(false));
        assertThat(response.jsonPath().get("status"), equalTo(413));
    }

    @Test
    @DisplayName("Загрузка картинки в поле видео")
    void uploadFileImageToVideoTests() {
        Response response = given()
                .header("Authorization", token)
                .multiPart("video", new File(PATH_TO_IMAGE))
                .log()
                .method()
                .log()
                .uri()
                .when()
                .post("https://api.imgur.com/3/upload")
                .prettyPeek();
        assertThat(response.jsonPath().get("data.error"), equalTo("Unable to determine file duration"));
        assertThat(response.jsonPath().get("success"), equalTo(false));
        assertThat(response.jsonPath().get("status"), equalTo(400));
    }

    @Test
    @DisplayName("Загрузка MP3 в поле видео")
    void uploadFileMP3ToVideoTests() {
        Response response = given()
                .header("Authorization", token)
                .multiPart("video", new File(PATH_TO_MP3))
                .log()
                .method()
                .log()
                .uri()
                .when()
                .post("https://api.imgur.com/3/upload")
                .prettyPeek();
        assertThat(response.jsonPath().get("data.error"), equalTo("File exceeds max duration"));
        assertThat(response.jsonPath().get("success"), equalTo(false));
        assertThat(response.jsonPath().get("status"), equalTo(400));
    }

    @Test
    @DisplayName("Загрузка документа в поле видео")
    void uploadFileDocToVideoTests() {
        Response response = given()
                .header("Authorization", token)
                .multiPart("video", new File(PATH_TO_DOC))
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
    @DisplayName("Загрузка GIF в поле видео")
    void uploadFileGIFToVideoTests() {
        Response response = given()
                .header("Authorization", token)
                .multiPart("video", new File(PATH_TO_GIF))
                .log()
                .method()
                .log()
                .uri()
                .when()
                .post("https://api.imgur.com/3/upload")
                .prettyPeek();
        assertThat(response.jsonPath().get("data.error"), equalTo("Unable to determine file duration"));
        assertThat(response.jsonPath().get("success"), equalTo(false));
        assertThat(response.jsonPath().get("status"), equalTo(400));
    }
}
