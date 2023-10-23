package endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.UserObject;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserResponses2 {
    //method to get URLs from properties file
    static ResourceBundle getURL(){
        //Load properties file
        ResourceBundle routes = ResourceBundle.getBundle("urls");
        return routes;
    }

    //declare url variables ---------------------------------------------------------
    static String base_url = getURL().getString("base_url");
    static String post_url = base_url + getURL().getString("post_user_url");
    static String user_byName_url = base_url +getURL().getString("user_byName_url");
    static String login_url = base_url + getURL().getString("login_url");


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
