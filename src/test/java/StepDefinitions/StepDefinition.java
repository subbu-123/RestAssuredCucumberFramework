package StepDefinitions;

import Resources.ApiResources;
import Resources.Utils;
import TestData.TestDataBuilder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

public class StepDefinition extends Utils {

	TestDataBuilder tdb = new TestDataBuilder();
	RequestSpecification req;
	Response res;
	public static String place_ID;

	@Given("^User has Add place payload with (.+), (.+),(.+)$")
	public void user_has_add_place_payload_with_(String name, String language, String address) throws IOException {

		req = given().spec(RequestSpec()).body(tdb.addPlacePayload(name, language, address));
	}

	@When("^User hits \"([^\"]*)\" with \"([^\"]*)\" HTTP request$")
	public void user_hits_something_with_something_http_request(String resource, String httpMethod) {

		ApiResources apiResource = ApiResources.valueOf(resource);
		if (httpMethod.equalsIgnoreCase("post")) {
			res = req.when().post(apiResource.getResource());
			//res.prettyPrint();
		} else if (httpMethod.equalsIgnoreCase("get")) {
			res = req.when().get(apiResource.getResource());
		}
	}

	@Then("^User verifies the API call was successful with status code \"([^\"]*)\"$")
	public void user_verifies_the_api_call_was_successful_with_status_code_something(String ExpectedStatusCode) {

		int actualStatusCode = res.getStatusCode();
		assertEquals(Integer.toString(actualStatusCode), ExpectedStatusCode);

	}
	
	 @And("^response is validated as per post call json schema$")
	    public void response_is_validated_as_per_post_call_json_schema() {
		 
		 res.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/post_call_schema.json"));
	        
	    }


	@And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void something_in_response_body_is_something(String key, String expectedValue) {

		String statusVal = jsonKeyVal(res.asString(), key);
		assertEquals(statusVal, expectedValue);

	}

	@And("^verify place_Id created maps to (.+) using \"([^\"]*)\"$")
	public void verify_placeid_created_maps_to_using_something(String ExpectedName, String resource) throws IOException {

		place_ID = jsonKeyVal(res.asString(), "place_id");
		req = given().spec(RequestSpec()).queryParam("place_id", place_ID);
		user_hits_something_with_something_http_request("getPlaceAPI","get");
		String ActualName = jsonKeyVal(res.asString(), "name");
		assertEquals(ActualName, ExpectedName);
	}

	@Given("User has Delete place payload")
	public void user_has_delete_place_payload() throws IOException {

		req = given().spec(RequestSpec()).body(tdb.deletPlacePayload(place_ID));
	}

}
