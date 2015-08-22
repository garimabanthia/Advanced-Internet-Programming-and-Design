package weatherUI;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

import weatherData.CityWeatherData;
import weatherData.WeatherDb;

public class WeatherTableModel extends AbstractTableModel {
	private final static String colNames[] = { "City", "State", "Temp",
		"Min Temp", "Max Temp", "Warning" };
	private WeatherDb weatherDb;

	public WeatherTableModel(WeatherDb weatherDb) {
		this.weatherDb = weatherDb;
	}

	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public int getRowCount() {
		return weatherDb.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int col) {

		CityWeatherData cityData = weatherDb.getCityWeatherData(rowIndex);
		String temp;
		String minTemp;
		String maxTemp;
		Boolean warning;

		if (col == 0) {
			fireTableCellUpdated(rowIndex, col);
			return (cityData.getCity());
			
		} else if (col == 1) {
			fireTableCellUpdated(rowIndex, col);
			return (cityData.getState());
		} else if (col == 2) {
			temp = cityData.getTemp() + "";
			fireTableCellUpdated(rowIndex, col);
			return (temp);
		} else if (col == 3) {
			minTemp = cityData.getMinTemp() + "";
			fireTableCellUpdated(rowIndex, col);
			return (minTemp);
		} else if (col == 4) {
			fireTableCellUpdated(rowIndex, col);
			maxTemp = cityData.getMaxtemp() + "";
			return (maxTemp);
		} else {
			fireTableCellUpdated(rowIndex, col);
			warning = cityData.getSevereWeather();
			return (warning);
		}
	}

	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	public String getColumnName(int col) {
		return colNames[col];
	}
	


}
