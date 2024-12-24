package com.qa.steps;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import static org.hamcrest.Matchers.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestStep {
	Response response;
	RequestSpecification request;

	@Given("I send a POST request to {string} and set parameter with payload from file {string}")
	public void i_send_a_post_request_to_and_set_parameter_with_payload_from_file(String baseURI, String payload) {

		RestAssured.baseURI = baseURI;
		response = RestAssured.given().queryParam("author", "beeceptor").contentType(ContentType.JSON)
				.body(new File(payload)).when().post("/sample-request");
		System.out.println("Response body ==>>: " + response.prettyPrint());

	}

	@Then("the response should include customer information, payment details, and product information")
	public void the_response_should_include_customer_information_payment_details_and_product_information() {

		JsonPath js = response.jsonPath();
		assertTrue("Status code is not 200", response.statusCode() == 200);
		assertNotNull("Path field is missing", js.getString("path"));
		assertNotNull("ip field is missing", js.getString("ip"));
		assertNotNull("host field is missing", js.getString("host"));
		response.then().assertThat().header("Content-Type", "application/json");
		response.then().assertThat().header("Vary", "Accept-Encoding");

		// Verification for customer information
		response.then().statusCode(200).body("parsedBody.order_id", equalTo("12345"))
				.body("parsedBody.customer.name", equalTo("Jane Smith"))
				.body("parsedBody.customer.email", equalTo("janesmith@gmail.com"))
				// Verification for payment details
				.body("parsedBody.payment.amount", equalTo(111.97f))
				.body("parsedBody.payment.method", equalTo("credit_card"))
				// Verification for product information.
				.body("parsedBody.items[0].name", equalTo("Wireless Headphones"))
				.body("parsedBody.items[1].name", equalTo("Smartphone Case"))
				.body("parsedBody.items[1].quantity", equalTo(2));

	}

}
