package ru.strelnikovsv.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AccountTests extends BaseTest {

    @Test
    void getAccountInfoTest() {
        Response response = given()
                .header("Authorization", token)
                .log()
                .method()
                .log()
                .uri()
                .when()
                .get("https://api.imgur.com/3/account/{username}", username)
                .prettyPeek();
        assertThat(response.jsonPath().get("data.url"), equalTo(username));
        assertNotNull(response.jsonPath().get("data.url"));
        assertThat(response.jsonPath().get("status"), equalTo(200));
    }


}
