package weatherUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import weatherData.CityWeatherData;
import weatherData.Location;
import weatherData.WeatherDb;

class WeatherUpdateUI extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JLabel cityLbl = new JLabel("City");
	private JLabel stateLbl = new JLabel("State");
	private JLabel tempLbl = new JLabel("Temp");
	private JCheckBox severeWeatherCheckBox = new JCheckBox(
			"Severe Weather Warning?");
	private JTextField cityTxtField = new JTextField(20);
	private JTextField stateTxtField = new JTextField(3);
	private JTextField tempTxtField = new JTextField(5);
	private JPanel dataPanel = new JPanel();
	private JPanel locPanel = new JPanel();

	private JButton updateBtn = new JButton("Update");
	private WeatherDb weatherdb;

	public WeatherUpdateUI(WeatherDb weatherdb) {
		setLayout(new BorderLayout());
		this.weatherdb = weatherdb;
		locPanel.add(cityLbl);
		locPanel.add(cityTxtField);
		locPanel.add(stateLbl);
		locPanel.add(stateTxtField);
		dataPanel.add(tempLbl);
		dataPanel.add(tempTxtField);
		dataPanel.add(severeWeatherCheckBox);
		add(locPanel, "North");
		add(dataPanel, "Center");
		add(updateBtn, "South");
		updateBtn.addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt) {
		String city = cityTxtField.getText();
		String state = stateTxtField.getText();
		Location loc;
		CityWeatherData cityData;
		String strData, errMsg;
		int temp;
		boolean isSevereWeather;

		errMsg = Location.validateData(city, state);

		/*  To Do:  Pop up dialog box about error.  Use errMsg above.  */
		if (evt.getSource()==updateBtn){
			if (errMsg!= null) {
				JOptionPane.showMessageDialog(this,errMsg);
				return;
			}
			if (city.equals("")) {
				JOptionPane.showMessageDialog(this,"Please enter the city");
				return;
			}
			
			loc = new Location(city, state);

			strData = tempTxtField.getText();
			if (strData.length() > 3) {
				JOptionPane.showMessageDialog(this,
						"Temperature may have a maximum of three digits");
				return;
			}

			try {
				temp = Integer.parseInt(strData);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Invalid Temperature");
				return;
			}

			isSevereWeather = severeWeatherCheckBox.isSelected();

			cityData = weatherdb.getLocWeather(loc);
			if (cityData == null) {
				cityData = new CityWeatherData(loc, temp);
				cityData.setMinTemp(temp);
				cityData.setMaxTemp(temp);
				cityData.setTemp(temp);
				
				weatherdb.addNewLocation(cityData);
			} else {
				
				weatherdb.addTemp(cityData, temp);
			}

			cityData.setSevereWeather(isSevereWeather);

			cityTxtField.setText("");
			stateTxtField.setText("");
			tempTxtField.setText("");
			severeWeatherCheckBox.setSelected(false);
		}
	}
}
