package weatherUI;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import sun.awt.geom.AreaOp.AddOp;
import weatherData.CityWeatherData;
import weatherData.Location;
import weatherData.WeatherDb;

class WeatherTablePanel extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	private WeatherDb weatherDb;
	private JLabel cityNameLbl;
	private JLabel curTempLbl;
	private CityWeatherData curWeather;
	// private TableModel tblModel; // selects the data to display
	private JTable wthrTable;
	private WeatherTableModel tblModel;

	public WeatherTablePanel(WeatherDb weatherDb) {

		this.weatherDb = weatherDb;

		cityNameLbl = new JLabel("Unknown City");
		curTempLbl = new JLabel("Temperature Not Set");

		tblModel = new WeatherTableModel(weatherDb);
		wthrTable = new JTable(tblModel);

		JScrollPane scroll = new JScrollPane(wthrTable);
		add(scroll);

		add(cityNameLbl);
		add(curTempLbl);
	}

	public void setNewLocation(Location newLoc) {
		String cityName;

		if (curWeather == null || !curWeather.hasLocation(newLoc)) {
			curWeather = weatherDb.getLocWeather(newLoc);
			cityName = newLoc.getCity();
			cityNameLbl.setText(cityName);
			curWeather.addObserver(this);
			tblModel.fireTableDataChanged();
		}

		updateWeatherData();
	}

	public void updateWeatherData() {
		int curTemp;

		curTemp = curWeather.getTemp();
		curTempLbl.setText("" + curTemp);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		curTempLbl.setText(arg1.toString());
	}

	public void getSelectedTableRow() {
		int row = wthrTable.getSelectedRow();
		if (row >= 0) {
			weatherDb.removeRow(row);

		}
	}
}
