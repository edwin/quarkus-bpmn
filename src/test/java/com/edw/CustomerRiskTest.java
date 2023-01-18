package com.edw;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * <pre>
 *     com.edw.CustomerRiskTest
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 18 Jan 2023 13:23
 */
@QuarkusTest
public class CustomerRiskTest {


    @Test
    public void testLowRiskCustomer() {
        given()
                .body("{ \"age\": 25, \"salary\": 5000 }")
                .contentType(ContentType.JSON)
                .when()
                .post("/customer_risk")
                .then()
                .statusCode(201)
                .body("'status'", equalTo("Loan is Approved"));

    }

    @Test
    public void testMediumRiskCustomer() {
        given()
                .body("{ \"age\": 15, \"salary\": 5000 }")
                .contentType(ContentType.JSON)
                .when()
                .post("/customer_risk")
                .then()
                .statusCode(201)
                .body("'status'", equalTo("Loan is on Review"));
    }

    @Test
    public void testHighRiskCustomer() {
        given()
                .body("{ \"age\": 15, \"salary\": 300 }")
                .contentType(ContentType.JSON)
                .when()
                .post("/customer_risk")
                .then()
                .statusCode(201)
                .body("'status'", equalTo("Loan is Rejected because Customer is High Risk"));
    }

}
