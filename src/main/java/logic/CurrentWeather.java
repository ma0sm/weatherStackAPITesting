package logic;

public class CurrentWeather extends QueryParameters {

	public CurrentWeather(String query) {
		super(query);
	}

	public CurrentWeather(String query, String units, String language, String callback) {
		super(query, units, language, callback);
	}

}
