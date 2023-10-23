package endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import payloads.UserObject;

import static io.restassured.RestAssured.given;

public class UserResponses {
    public static Response createUser(UserObject user){
         Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(user)
                .when()
                .post(URLs.post_url);
        return response;
    }

    public static Response getUserByName(String username){
        return given()
                .pathParam("username",username)
                .when()
                .get(URLs.user_byName_url);
    }

    public static Response updateUserByName(String username, UserObject user){
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",username)
                .body(user)
                .when()
                .put(URLs.user_byName_url);

    }
    public static Response deleteUserByName(String username){
        return given()
                .pathParam("username",username)
                .when()
                .delete(URLs.user_byName_url);
    }
}
