package ru.strelnikovsv.tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public abstract class BaseTest {
    static Properties properties = new Properties();
    static String token;
    static String token_zero;
    static String username;

    public final String PATH_TO_IMAGE = "src/test/resources/photo1.jpg";
    public final String PATH_TO_GIF = "src/test/resources/gif1.gif";
    public final String PATH_TO_DOC = "src/test/resources/word.docx";
    public final String PATH_TO_MP3 = "src/test/resources/music.mp3";
    public final String PATH_TO_VIDEO = "src/test/resources/video.mp4";

    @BeforeAll
    static void beforeAll() {
        RestAssured.filters(new AllureRestAssured());
        getProperties();
        token = properties.getProperty("token");
        token_zero = properties.getProperty("token_zero");
        username = properties.getProperty("username");
    }

    private static void getProperties() {
        try (InputStream output = new FileInputStream("src/test/resources/application.properties")) {
            properties.load(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
