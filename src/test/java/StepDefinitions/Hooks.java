package StepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

// Imp thing to note is hooks should be written along with step definitions in stepDefinition class or 
// if we write those in a separate class then both stepDefinition class and hooks class will be under one package

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		StepDefinition sd =new StepDefinition();
		if(StepDefinition.place_ID==null)
		{
			sd.user_has_add_place_payload_with_("subhankar", "Odia", "Cuttack");
			sd.user_hits_something_with_something_http_request("AddPlaceAPI","post");
			sd.verify_placeid_created_maps_to_using_something("subhankar","getPlaceAPI");
		}
	}
	
}
