package ru.strelnikovsv.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.strelnikovsv.dto.PostImageResponse;

import static io.restassured.RestAssured.given;
import static ru.strelnikovsv.Endpoints.GET_ACCOUNT;

class AccountTests extends BaseTest {

    @Test
    @DisplayName("Проверка аккаунта")
    void getAccountInfoTest() {
        given(requestSpecificationWithAuth)
                .get(GET_ACCOUNT, username).prettyPeek().then()
                .spec(positiveAccountResponseSpecification)
                .extract().body().as(PostImageResponse.class);
    }

    @Test
    @DisplayName("Аккаунт без токена")
    void getAccountInfoWithoutTokenTest() {
        given()
                .get(GET_ACCOUNT, username).prettyPeek().then()
                .spec(negative401ResponseSpecification)
                .extract().body().as(PostImageResponse.class);
    }
}
