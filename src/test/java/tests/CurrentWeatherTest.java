package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import logic.CurrentWeather;
import logic.Responses;
import logic.StatusCode;

public class CurrentWeatherTest {

	private static final String BASE_PATH = "/current";
	StatusCode sc = new StatusCode();

	@AfterClass
	public void clean() {
	}

	@Test
	public void positiveTestValidQuery() {
		Responses rs = new Responses();
		CurrentWeather cw = new CurrentWeather("New York");
		String jsonBody = rs.responseStatusCheck(BASE_PATH, cw.ACCESS_KEY, 200, cw.getQueryParameters(),
				"request.query", "New York, United States of America").getBody().asString();
		System.out.println(jsonBody);
	}

	@Test
	public void positiveTestValidQueryAndPositiveOffset() {
		Responses rs = new Responses();
		CurrentWeather cw = new CurrentWeather("London" + "11");
		String jsonBody = rs.responseStatusCheck(BASE_PATH, cw.ACCESS_KEY, 200, cw.getQueryParameters(),
				"request.query", "London, United Kingdom").getBody().asString();
		System.out.println(jsonBody);
	}

	@Test
	public void positiveTestValidQueryTwoCitiesSpaceDivision() {
		Responses rs = new Responses();
		CurrentWeather cw = new CurrentWeather("Amsterdam Tokyo");
		String jsonBody = rs.responseStatusCheck(BASE_PATH, cw.ACCESS_KEY, 200, cw.getQueryParameters(),
				"request.query", "Tokyo, Japan").getBody().asString();
		System.out.println(jsonBody);
	}

	@Test
	public void negativeTestNonExistantPlace() {
		Responses rs = new Responses();
		CurrentWeather cw = new CurrentWeather("ABCDEFGHIVJKLMNOP");
		String jsonBody = rs.responseStatusCheck(BASE_PATH, cw.ACCESS_KEY, 200, cw.getQueryParameters(), "error.type",
				sc.returnCode(615)).getBody().asString();
		System.out.println(jsonBody);
	}

	@Test
	public void negativeTestCorrectQueryTwoCityCrossSemicolon() {
		Responses rs = new Responses();
		CurrentWeather cw = new CurrentWeather("New York;London");
		String jsonBody = rs.responseStatusCheck(BASE_PATH, cw.ACCESS_KEY, 200, cw.getQueryParameters(), "error.type",
				sc.returnCode(604)).getBody().asString();
		System.out.println(jsonBody);
	}

}
