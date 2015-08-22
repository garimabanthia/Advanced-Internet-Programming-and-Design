package weatherUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import weatherData.CityWeatherData;
import weatherData.Location;
import weatherData.WeatherDb;

class AddNewLocPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JLabel cityLbl = new JLabel("City");
	private JLabel stateLbl = new JLabel("State");
	private JTextField cityTxtField = new JTextField(20);
	private JTextField stateTxtField = new JTextField(3);
	private JButton addBtn = new JButton("Add");
	private WeatherDb weatherDb;
	private WeatherTablePanel weatherTblPanel;

	public AddNewLocPanel(WeatherDb weatherDb, WeatherTablePanel weatherTblPanel) {
		this.weatherDb = weatherDb;
		this.weatherTblPanel = weatherTblPanel;
		add(cityLbl);
		add(cityTxtField);
		add(stateLbl);
		add(stateTxtField);
		add(addBtn);
		addBtn.addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt) {
		CityWeatherData cityData;
		Location loc;
		String city = cityTxtField.getText();
		String state = stateTxtField.getText();
		String errMsg;

		errMsg = Location.validateData(city, state);
		/* To Do: Pop up dialog box to give error message. Use errMsg above. */

		loc = new Location(city, state);

		cityData = weatherDb.getLocWeather(loc);
		if (cityData == null) {
			/* To Do: Handle this error (Unknown City) */
		}

		weatherTblPanel.setNewLocation(loc);

		// Reset UI
		cityTxtField.setText("");
		stateTxtField.setText("");
	}
}

