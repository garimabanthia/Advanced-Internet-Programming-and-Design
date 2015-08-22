package weatherData;



import java.util.*;

public class WeatherDb {
	private ArrayList<CityWeatherData> cityWeatherSet;

	public WeatherDb() {
		cityWeatherSet = new ArrayList<CityWeatherData>();

	}

	public CityWeatherData getLocWeather(Location city) {
		int i;
		CityWeatherData tstCity;

		for (i = 0; i < cityWeatherSet.size(); i++) {

			tstCity = cityWeatherSet.get(i);

			if (tstCity.hasLocation(city)) {
				return tstCity;

			}
		}

		return null;
	}
	public int size() {
		return cityWeatherSet.size();
	}

	public CityWeatherData getCityWeatherData(int idx) {
		CityWeatherData cityWeatherData;

		cityWeatherData = cityWeatherSet.get(idx);
		return(cityWeatherData);
	}
	public void addNewLocation(CityWeatherData cityData) {
		cityWeatherSet.add(cityData);
	}

	public boolean contains(Location city) {
		CityWeatherData cityWeather;

		cityWeather = getLocWeather(city);
		if (cityWeather != null) {
			return true;
		}

		return false;
	}
	
}