package api.endpoints;

import api.payloads.PetObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PetResponses extends baseResponse {

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
