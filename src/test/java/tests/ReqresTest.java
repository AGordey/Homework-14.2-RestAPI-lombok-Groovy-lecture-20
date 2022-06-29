package tests;

import io.restassured.RestAssured;
import lombok.UserData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ReqresTest extends ReqresEndpoints {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://reqres.in/api/";
    }

    @Test
    @DisplayName("Test with Lombok")
    void checkApiEndpoint1() {

        UserData data = given()
                .when()
                .get(getSingleUser)
                .then()
                .log().body()
                .extract().as(UserData.class);

        assertEquals(2, data.getUser().getId());
        assertEquals("janet.weaver@reqres.in", data.getUser().getEmail());
        assertEquals("Janet", data.getUser().getFirstName());
        assertEquals("Weaver", data.getUser().getLastName());
    }

    @Test
    @DisplayName("Test with Groovy")
    void checkApiEndpoint2() {

        given()
                .when()
                .get(getListUser)
                .then()
                .log().all()
                .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()",
                        hasItem("michael.lawson@reqres.in"))
                .body("data.id.flatten()",
                        hasItem(7));
    }
}
