package com.qa.steps;

import org.hamcrest.Matchers;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.MatcherAssert.assertThat;

public class GetRequestSteps {
	private Response response;
	RequestSpecification request;

	@Given("I perform a GET request to {string}")
	public void i_perform_a_get_request_to(String baseURI) {
		//response = RestAssured.given().get(baseURI);
		request=RestAssured.given().log().all();
		response = request.get(baseURI);
		System.out.println(response);
	}

	@Then("the status code is {int}")
	public void the_status_code_is(Integer statusCode) {
		System.out.println(response.getStatusCode());
		assertThat(response.getStatusCode(), Matchers.equalTo(statusCode));
		
	}

	@Then("the ip address is not empty")
	public void the_ip_address_is_not_empty() {
		System.out.println(response.body().jsonPath().getString("ip"));
		assertThat(response.body().jsonPath().getString("ip"), Matchers.notNullValue());
	}

	@Then("all headers are present")
	public void all_headers_are_present() {
		Headers headers = response.headers();
		System.out.println(headers.size());
        assertThat(headers.size(), Matchers.greaterThan(0));
	}

}
