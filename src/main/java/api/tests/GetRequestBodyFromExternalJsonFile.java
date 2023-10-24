package api.tests;

import api.endpoints.URLs;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.given;

public class GetRequestBodyFromExternalJsonFile {
    @Test
    void createUser() throws FileNotFoundException {
        File file = new File ("testData/users.json");
        FileReader reader = new FileReader(file);
        JSONTokener tokener = new JSONTokener(reader);
        JSONObject data = new JSONObject(tokener);

        given()
                .contentType("application/json")
                .body(data.toString())
        .when()
                .post(URLs.post_url)
        .then()
                .statusCode(200)
                .log().all();
    }
}
