package weatherData;

import java.util.Observable;

public class CityWeatherData extends Observable {
	private Location loc;
	private int temp;
	private int humidity;
	private boolean isSevereWeather;
	private int minTemp;
	private int maxTemp;
	WeatherDb weatherDb;

	public CityWeatherData(Location loc, int temp) {
		this.loc = loc;
		this.temp = temp;
	}

	public String getCity() {
		return loc.getCity();
	}

	public String getState() {
		return loc.getState();
	}

	public int getTemp() {
		return temp;
	}

	public Location getLocation() {
		return loc;
	}

	public boolean getSevereWeather() {
		return isSevereWeather;
	}

	public void setLocation(Location loc) {
		this.loc = loc;
	}

	public boolean hasLocation(Location tstLoc) {
		return loc.equals(tstLoc);
	}

	public void setTemp(int temp) {
		this.temp = temp;

		setChanged();
		notifyObservers(temp);
	}

	public int getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(int minTemp) {
		 this.minTemp= minTemp;
		 	
	}

	public int getMaxtemp() {
		return maxTemp;
	}

	public void setMaxTemp(int maxTemp) {
		this.maxTemp= maxTemp;
		
	}

	public void setSevereWeather(boolean isSevereWeather) {
		this.isSevereWeather = isSevereWeather;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public String toString() {
		return loc + " Temperature: " + temp;
	}
}