package tests;

import com.github.javafaker.Faker;
import endpoints.UserResponses;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import payloads.UserObject;

public class UserTests {
    Faker faker ;
    UserObject userObject ;

    @BeforeClass
    public void setupUser(){
        faker = new Faker();
        userObject = new UserObject();

        userObject.setId(faker.idNumber().hashCode());
        userObject.setUsername(faker.name().username());
        userObject.setFirstName(faker.name().firstName());
        userObject.setLastName(faker.name().lastName());
        userObject.setEmail(faker.internet().safeEmailAddress());
        userObject.setPassword(faker.internet().password(5,10));
        userObject.setPhone(faker.phoneNumber().cellPhone());
    }

    @Test(priority = 1)
    public void testCreateUser(){
        Response response = UserResponses.createUser(userObject);

        //verify
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority = 2)
    public void getUserByName(){
        Response response = UserResponses.getUserByName(this.userObject.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 2)
    public void testUpdateUserByName(){
        //update data using payload
        userObject.setFirstName("test");
        userObject.setLastName("test2");

        //send request
        Response response = UserResponses.updateUserByName(this.userObject.getUsername(),userObject);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        Response responseAfterUpdate = UserResponses.getUserByName(this.userObject.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 3)
    public void testDeleteUserByName(){
        Response response = UserResponses.deleteUserByName(this.userObject.getUsername());
        //Assert.assertEquals(response.getStatusCode(),200);
        response.then().statusCode(200);
    }
}
