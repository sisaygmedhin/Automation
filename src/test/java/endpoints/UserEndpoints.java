package endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.User;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class UserEndpoints {

    public static Response createUser(User payload)
    {
       Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
               .when()
                .post(Routes.Post_url);
return response;

    }
    public static Response readUser(String userName)
    {
        Response response= given()
                .pathParam("username",userName)
                .when()
                .get(Routes.Get_url);
        return response;
}
    public static Response updateUser(String userName, User payload)
    {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",userName)
                .body(payload)
                .when()
                .put(Routes.Update_url);
        return response;

    }
    public static Response deleteUser(String userName) {
        Response response = given()
                .pathParam("username", userName)

                . when()
                .get(Routes.Delete_url);
        return response;
    }}