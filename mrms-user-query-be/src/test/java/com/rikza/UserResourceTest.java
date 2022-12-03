package com.rikza;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import com.rikza.user.UserResource;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(UserResource.class)
public class UserResourceTest {

    @Test
    public void testGetTest() {
        given()
                .when().get()
                .then()
                .statusCode(200);
    }
}