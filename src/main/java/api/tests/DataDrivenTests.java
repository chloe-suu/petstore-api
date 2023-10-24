package api.tests;

import utilities.DataProviders;
import com.github.javafaker.Faker;
import api.endpoints.UserResponses;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import api.payloads.UserObject;

public class DataDrivenTests {
    UserObject user;
    Faker faker;

    @Test(priority = 1, dataProvider = "allData", dataProviderClass = DataProviders.class)
    public void testCreateUser(String userId, String userName, String fname, String lname, String email, String pwd, String phoneNo){
        user = new UserObject();
        faker = new Faker();

        user.setId(Integer.parseInt(userId));
        user.setUsername(userName);
        user.setFirstName(fname);
        user.setLastName(lname);
        user.setEmail(email);
        user.setPassword(pwd);
        user.setPhone(phoneNo);

        Response response = UserResponses.createUser(user);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
