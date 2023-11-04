package test;

import com.github.javafaker.Faker;
import endpoints.UserEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import payload.User;

public class UserTests {

    Faker faker;
    User userPayload;
@BeforeClass
    public void setupData(){
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
    }

    @Test(priority = 1)

    public void testPostUser(){
        Response response= UserEndpoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority = 2)

    public void testGetUserByName(){
        Response response = UserEndpoints.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 3)

    public void updateUserByName(){
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());

        Response response = UserEndpoints.updateUser(this.userPayload.getUsername(),userPayload);
   response.then().log().all();

        Response responseA = UserEndpoints.readUser(this.userPayload.getUsername());
        responseA.then().log().all();
        Assert.assertEquals(responseA.getStatusCode(),200);
    }
    @Test(priority = 4)
    public void deleteUserByName(){
        Response response = UserEndpoints.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

}
