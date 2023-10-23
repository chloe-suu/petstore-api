package tests;

import com.github.javafaker.Faker;
import endpoints.PetResponses;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import payloads.PetCategoryObject;
import payloads.PetObject;
import payloads.PetTagObject;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class PetTests {
    PetObject pet;
    Faker faker;
    @BeforeClass
    void setUpPet(){
        String image_URL = "testData\\avatar.png";

        faker = new Faker();
        pet = new PetObject();
        pet.setId(faker.idNumber().hashCode());
        pet.setCategory(new PetCategoryObject(1,"dog"));
        pet.setName(faker.name().name());
        pet.setTags(Collections.singletonList(new PetTagObject(1, "golden")));
        pet.setPhotoURLs(Collections.singletonList(image_URL));
        pet.setStatus("available");

    }
    @Test(priority = 1)
    void testCreateUser(){
        Response rs = PetResponses.createPet(pet);
        rs.then().log().all()
                .statusCode(200);

    }
    @Test (priority = 2)
    void testUploadAnImage(){
        //int id = PetResponses.createPet(pet).jsonPath().get("id");
        File myImage = new File("testData\\avatar.png");
        Response rs = PetResponses.updateImage(pet.getId(),myImage);
        rs.then().statusCode(200).log().all();

        File myImage2 = new File("testData\\avatar2.png");
        PetResponses.updateImage(pet.getId(), myImage2);
        Response rs2 = PetResponses.getPetById(pet.getId());
                rs2.then().statusCode(200).log().all();
    }
    @Test (priority = 2)
    void testGetPetById(){
        //int id = PetResponses.createPet(pet).jsonPath().get("id");
        Response rs = PetResponses.getPetById(pet.getId());
        rs.then().statusCode(200).log().all();
    }
}
