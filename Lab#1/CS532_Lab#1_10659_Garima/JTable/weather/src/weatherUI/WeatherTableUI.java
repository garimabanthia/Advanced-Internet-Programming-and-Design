package weatherUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import weatherData.WeatherDb;

public class WeatherTableUI extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private WeatherTablePanel weatherTblPanel;
	private AddNewLocPanel addNewLocPanel;
	private JPanel removeBtnPanel = new JPanel();
	private JButton removeLocBtn = new JButton("Remove Location");
	//private WeatherDb weatherDb;


	public WeatherTableUI(WeatherDb weatherDb) {
		weatherTblPanel = new WeatherTablePanel(weatherDb);
		addNewLocPanel = new AddNewLocPanel(weatherDb, weatherTblPanel);
		setLayout(new BorderLayout());
		add(weatherTblPanel, "Center");
		add(addNewLocPanel, "South");
		removeBtnPanel.add(removeLocBtn);
		add(removeBtnPanel, "East");
		removeLocBtn.addActionListener(this);

}

	public void actionPerformed(ActionEvent evt) {
		/* Delete a row from the table */
		if (evt.getSource() == removeLocBtn) {
			weatherTblPanel.getSelectedTableRow();
		}
	}

}
