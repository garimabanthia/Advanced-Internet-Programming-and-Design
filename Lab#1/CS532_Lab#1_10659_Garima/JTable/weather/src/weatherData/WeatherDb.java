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

	public void addTemp(CityWeatherData cityData, int newTemp) {
		int temp = 0;
		// int newMaxTemp = cityData.getMaxtemp();
		int maxTemp = 0;
		// int newMinTemp = 0;
		int minTemp = 0;
		String city = null;
		String state = null;
		System.out.println("New Temp is:" + newTemp);

		for (int i = 0; i < cityWeatherSet.size(); i++) {
			city = cityWeatherSet.get(i).getCity();
			state = cityWeatherSet.get(i).getState();

			if ((cityData.getCity().equals(city))
					&& (cityData.getState().equals(state))) {
				maxTemp = cityWeatherSet.get(i).getMaxtemp();
				minTemp = cityWeatherSet.get(i).getMinTemp();

				if (newTemp <= minTemp) {
					cityData.setMinTemp(newTemp);
					cityData.setTemp(newTemp);

				} else if (newTemp >= maxTemp) {
					cityData.setMaxTemp(newTemp);
					cityData.setTemp(newTemp);

				} else {
					cityData.setTemp(newTemp);
				}

			}

		}
	}

	public int size() {
		return cityWeatherSet.size();
	}

	public CityWeatherData getCityWeatherData(int idx) {
		CityWeatherData cityWeatherData;

		cityWeatherData = cityWeatherSet.get(idx);
		return (cityWeatherData);
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

	public void removeRow(int row) {
		cityWeatherSet.remove(row);
	}
}