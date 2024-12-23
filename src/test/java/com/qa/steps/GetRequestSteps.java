package com.qa.steps;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequestSteps {

	Response response;
	RequestSpecification request;

	@Given("I perform a GET request to {string} and set parameter")
	public void i_perform_a_get_request_to_and_set_parameter_as(String baseURI) {

		RestAssured.baseURI = baseURI;
		response = RestAssured.given().queryParam("author", "beeceptor").when().get("/sample-request");
		System.out.println("Response body ==>>: " + response.prettyPrint());
	}

	@Then("the response should include fields such as path, ip, headers")
	public void the_response_should_include_fields_such_as_path_ip_headers() {
		JsonPath js = response.jsonPath();
		assertTrue("Status code is not 200", response.statusCode() == 200);
		assertNotNull("Path field is missing", js.getString("path"));
		assertNotNull("ip field is missing", js.getString("ip"));
		assertNotNull("host field is missing", js.getString("host"));
		response.then().assertThat().header("Content-Type", "application/json");
		response.then().assertThat().header("Vary", "Accept-Encoding");

	}

}
