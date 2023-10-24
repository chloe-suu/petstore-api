package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import api.payloads.UserObject;

import static io.restassured.RestAssured.given;

public class UserResponses2 extends baseResponse{

    //responses-----------------------------------------------------
    public static Response createUser(UserObject user){
         return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(user)
                .when()
                .post(post_url);
    }

    public static Response getUserByName(String username){
        return given()
                .pathParam("username",username)
                .when()
                .get(user_byName_url);
    }

    public static Response updateUserByName(String username, UserObject user){
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",username)
                .body(user)
                .when()
                .put(user_byName_url);
    }
    public static Response deleteUserByName(String username){
        return given()
                .pathParam("username",username)
                .when()
                .delete(user_byName_url);
    }
    public static Response login(String username, String pwd){
        return given()
                .auth().basic(username,pwd)
                .when()
                .get(login_url);
    }
}
