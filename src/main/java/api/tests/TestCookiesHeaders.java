package api.tests;

import io.restassured.http.Headers;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestCookiesHeaders {
    @Test
    void testCookies (){
       Response response = given()
                .when()
                .get("https://google.com");

        String cookie1 =response.getCookie("AEC");

        Map<String, String> cookies = response.getCookies();
        for (String key:cookies.keySet()){
            String value = response.getCookie(key);
            System.out.println(key + "      " + value);
        }
    }

    @Test
    void testHeaders(){
        Response response = given()
                .when()
                .get("https://google.com");

        String hdValue = response.getHeader("Content-Type");
        System.out.println("Value of header Content-Type: " +hdValue);

        Headers headers = response.getHeaders();
        for (Header hd:headers){
            System.out.println(hd.getName()+":   " + hd.getValue());
        }
    }
}
