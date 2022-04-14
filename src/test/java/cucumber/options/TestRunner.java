package cucumber.options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",glue= "StepDefinitions"
,plugin= {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}, monochrome=true)
public class TestRunner {

	// plugin="json:target/jsonReports/cucumber-jsonReport.json"
}
