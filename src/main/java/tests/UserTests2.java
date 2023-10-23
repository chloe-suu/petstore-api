package tests;

import com.github.javafaker.Faker;
import endpoints.UserResponses2;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import payloads.UserObject;

import static org.hamcrest.Matchers.*;

public class UserTests2 {
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
        Response response = UserResponses2.createUser(userObject);

        //verify
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority = 2)
    public void getUserByName(){
        Response response = UserResponses2.getUserByName(this.userObject.getUsername());
        response.then().log().all()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .body("username", equalTo(this.userObject.getUsername()));
        Assert.assertEquals(response.getStatusCode(),200);

    }

    @Test(priority = 2)
    public void testUpdateUserByName(){
        //update data using payload
        userObject.setFirstName("test");
        userObject.setLastName("test2");

        //send request
        Response response = UserResponses2.updateUserByName(this.userObject.getUsername(),userObject);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        Response responseAfterUpdate = UserResponses2.getUserByName(this.userObject.getUsername());
        //Approach 1
        responseAfterUpdate.then().log().all()
                .body("firstName", equalTo("test"))
                .body("lastName", equalTo("test2"));
        //Approach 2
        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
        //Approach 3
        String fN = responseAfterUpdate.jsonPath().get("firstName").toString();
        Assert.assertEquals(fN, "test");
    }

    @Test(priority = 3)
    public void testDeleteUserByName(){
        Response response = UserResponses2.deleteUserByName(this.userObject.getUsername());
        Assert.assertEquals(response.getStatusCode(),200);
        //response.then().statusCode(200);
    }
    @Test
    public void testLogin(){
        String username = "string";
        String pwd = "string";

        Response rs = UserResponses2.login(username,pwd);
        rs.then().statusCode(200)
                .body("message",containsString("logged in user session"));
    }
}
