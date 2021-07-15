package ru.strelnikovsv.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.strelnikovsv.dto.PostImageResponse;
import ru.strelnikovsv.dto.PostImageResponseBase64;

import static io.restassured.RestAssured.given;
import static ru.strelnikovsv.Endpoints.GET_DELETE;
import static ru.strelnikovsv.Endpoints.UPLOAD_BASE64;

class DeleteTests extends BaseTest {
    String uploadedImageId;

    @Test
    @DisplayName("Загрузка картинки c помощью Base64")
    void uploadFileBase64Test() {
        uploadedImageId = given(requestSpecUploadImageBase64)
                .post(UPLOAD_BASE64).prettyPeek().then()
                .spec(uploadFileImageBase64ResponseSpecification)
                .extract().body().as(PostImageResponse.class)
                .getData().getDeletehash();
    }

    @AfterEach
    @DisplayName("Удаление картинки загруженной c помощью Base64")
    void tearDown() {
        given(requestSpecDeleteImageBase64)
                .delete(GET_DELETE, username, uploadedImageId).prettyPeek().then()
                .spec(deleteFileImageBase64ResponseSpecification)
                .extract().body().as(PostImageResponseBase64.class);
    }
}
