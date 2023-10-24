package api.tests;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestResponseBodyWithJsonOrXml {
    @Test
    void testJson(){
       Response rs =
                given()
                        .contentType(ContentType.JSON)
                .when()
                        .get("https://reqres.in/api/users?page=2");

       //Approach 1
        rs.then()
                .statusCode(200)
                .body("data.first_name[0]",equalTo("Michael"));

        //Approach 2
        Assert.assertEquals(rs.getStatusCode(),200);
        Assert.assertEquals(rs.getBody().jsonPath().get("data.first_name[0]"), "Michael");

        //Approach 3
        String fN = rs.jsonPath().getString("data.first_name[0]");
        Assert.assertEquals(fN, "Michael");

        //Approach 4
        //Store response body in Json Object
        JSONObject jo = new JSONObject(rs.asString());
        int sum = 0;
        for (int i = 0; i<jo.getJSONArray("data").length();i++){
            String firstName = jo.getJSONArray("data").getJSONObject(i).get("first_name").toString();
            System.out.println(firstName);
            sum = sum+1;
        }
        //validate total users added
        Assert.assertEquals(sum,6);
    }

    @Test
    void testXml(){
        Response rs =
                given()
                        .get("http://restapi.adequateshop.com/api/Traveler?page=2");

        //Approach 1
        rs.then()
                .statusCode(200)
                .header("Content-Type","application/xml; charset=utf-8")
                .body("TravelerinformationResponse.page",equalTo("2"))
                .body("TravelerinformationResponse.travelers.Travelerinformation[0].name",equalTo("ASCAS"));

        //Approach 2
        Assert.assertEquals(rs.getStatusCode(),200);
        Assert.assertEquals(rs.header("Content-Type"),"application/xml; charset=utf-8");

        //Approach 3
        XmlPath xmlPath = rs.xmlPath();
        String pNo = xmlPath.getString("TravelerinformationResponse.page");
        Assert.assertEquals(pNo, "2");
        String name1 = xmlPath.getString("TravelerinformationResponse.travelers.Travelerinformation[0].name");
        Assert.assertEquals(name1,"ASCAS");

        List<String> names = xmlPath.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
        boolean test = false;
        for (String name:names){
            if (name.equals("ASCAS")){
                test = true;
                break;
            }
        }
        Assert.assertTrue(test);

        //Approach 4
        //Store response body in xml Object
        XmlPath xo = new XmlPath(rs.asString());

        List<String> travelers = xo.getList("TravelerinformationResponse.travelers.Travelerinformation");
        Assert.assertEquals(travelers.size(),10);
    }
}
