package mentimeter.project.utility;

import java.util.List;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {

	private static final int SUCCESS_STATUS_CODE 			= 200;
	private static final String MENTIMETER_BASE_URL 		= "https://api.mentimeter.com/";
	private static final String MENTIMETER_QUESTION_PATH 	= "questions/48d75c359ce4";
	private static final String MENTIMETER_RESULT_PATH 		= "questions/48d75c359ce4/result";
	private static final String QUESTION_ASKED_ON_API 		= "Which is the best interactive presentation platform?";
	
	

	public static Response getHTTPResponse(String path) {

		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = MENTIMETER_BASE_URL;
		RequestSpecification httpRequest = RestAssured.given();

		// Get the response for the API from the server.
		Response response = httpRequest.request(Method.GET, path);

		// Assert that success status code should be returned for API.
		Assert.assertEquals(response.getStatusCode(), SUCCESS_STATUS_CODE, "Status code returned for API is not 200");
		return response;
	}

	public static String getStringJSONData(Response httpResponse, String path) {

		// Get the JsonPath object instance from the Response interface
		JsonPath JSONPathEvaluator = httpResponse.jsonPath();

		// JsonPath object to get a String value of the node
		String JSONPathResponse = JSONPathEvaluator.get(path);
		
		return JSONPathResponse;
	}
	
	public static List<String> getListOfJSONData(Response httpResponse, String path) {

		// Get the JsonPath object instance from the Response interface
		JsonPath JSONPathEvaluator = httpResponse.jsonPath();

		// JsonPath object to get a String value of the node
		List<String> JSONPathResponse = JSONPathEvaluator.get(path);
		
		return JSONPathResponse;
	}

	
	
	
	public static String getQuestionAskedOnAPI() {
		return QUESTION_ASKED_ON_API;
	}

	public static String getMentimeterBaseURL() {
		return MENTIMETER_BASE_URL;
	}

	public static String getMentimeterQuestionPath() {
		return MENTIMETER_QUESTION_PATH;
	}

	public static String getMentimeterResultPath() {
		return MENTIMETER_RESULT_PATH;
	}

}
