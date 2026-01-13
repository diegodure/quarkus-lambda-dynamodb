package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class LambdaHandlerTest {
    @Test
    public void testSimpleLambdaSuccess() {
        // Esto simula el evento que AWS enviaría a tu Lambda
        String payload = "{\"name\": \"Diego Test\"}";
        
        given()
            .contentType("application/json")
            .accept("application/json")
            .body(payload)
            .when()
            .post("/_lambda_") // Quarkus mapea esto internamente en los tests
            .then()
            .statusCode(200)
            .body(containsString("Diego Test"));
    }
}