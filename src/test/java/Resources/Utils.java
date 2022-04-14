package Resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class Utils {

	static RequestSpecification req;

	public RequestSpecification RequestSpec() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("GlobalParameters.properties");
		prop.load(fis);

		if (req == null) {
			PrintStream ps = new PrintStream(new FileOutputStream("LogFile.txt"));
			req = new RequestSpecBuilder().setBaseUri(prop.getProperty("baseURL")).addQueryParam("key", "qaclick123")
					.setContentType(ContentType.JSON).addFilter(RequestLoggingFilter.logRequestTo(ps))
					.addFilter(ResponseLoggingFilter.logResponseTo(ps)).build();
		}
		return req;

	}

	public String jsonKeyVal(String response, String key) {
		JsonPath jp = new JsonPath(response);
		return jp.get(key).toString();
	}

}
