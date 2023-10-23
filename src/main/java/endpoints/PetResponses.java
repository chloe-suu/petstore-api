package endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.PetObject;

import java.io.File;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class PetResponses extends baseResponse {

    //declare url variables ---------------------------------------------------------
    static String base_url = getURL().getString("base_url");
    static String post_pet_url = base_url + getURL().getString("post_pet_url");
    static String pet_byId = base_url +getURL().getString("pet_byId");
    static String pet_upload_image = base_url +getURL().getString("pet_upload_image");

    //responses-----------------------------------------------------
    public static Response createPet(PetObject pet){
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(pet)
                .when()
                .post(post_pet_url);
    }
    public static Response updateImage(int id, File image){
        return given()
                .multiPart("file",image)
                .contentType("multipart/form-data")
                .pathParam("petId",id)
                .when()
                .post(pet_upload_image);
    }
    public static Response getPetById(int petId){
        return given()
                .accept(ContentType.JSON)
                .pathParam("petId", petId)
                .when()
                .get(pet_byId);
    }
}
