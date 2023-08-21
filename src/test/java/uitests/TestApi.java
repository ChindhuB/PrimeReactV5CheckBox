package uitests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class TestApi {
    RequestSpecification rq;
    Response rs;

    @Test
    public void m() {
        rs = RestAssured.given().baseUri("https://jsonmock.hackerrank.com/api/article_users").get();
        JsonPath j = new JsonPath(rs.asString());
        int size = j.get("data.size()");
        for (int i = 0; i < size; i++) {
            System.out.println(j.getString("data[" + i + "].username"));
            System.out.println(j.getString("data[" + i + "].submission_count"));
        }

    }
}
