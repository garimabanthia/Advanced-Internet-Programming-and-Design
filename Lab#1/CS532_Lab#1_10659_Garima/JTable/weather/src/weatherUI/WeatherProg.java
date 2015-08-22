package weatherUI;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import weatherData.WeatherDb;

class WeatherCtrlPanelFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton weatherTblBtn;
	private JButton weatherSetterBtn;
	private WeatherDb weatherdb;
	private JPanel topPanel = new JPanel(new GridLayout(2, 1));

	public WeatherCtrlPanelFrame(WeatherDb weatherdb) {
		this.weatherdb = weatherdb;
		setLayout(new FlowLayout());
		weatherTblBtn = new JButton("New Weather Table");
		weatherSetterBtn = new JButton("New Weather Setter");
		weatherTblBtn.addActionListener(this);
		weatherSetterBtn.addActionListener(this);
		WindowListener exitListener = new FrameTerminator();
		addWindowListener(exitListener);
		setTitle("Weather Watcher Manager");
		setSize(500, 200);
		topPanel.add(weatherTblBtn);
		topPanel.add(weatherSetterBtn);
		add(topPanel);
	}

	public void actionPerformed(ActionEvent evt) {
		JButton srcBtn = (JButton) evt.getSource();
		JFrame newFrame;

		if (srcBtn == weatherTblBtn) {
			newFrame = new WeatherTableFrame(weatherdb);
		} else {
			newFrame = new WeatherUpdateFrame(weatherdb);
		}

		newFrame.setVisible(true);
	}

	class FrameTerminator extends WindowAdapter {
		public void windowClosing(WindowEvent event) {
			System.exit(0);
		}
	}

}

public class WeatherProg {
	public static void main(String[] args) {
		WeatherDb weatherdb = new WeatherDb();
		WeatherCtrlPanelFrame weatherCtrlFrame = new WeatherCtrlPanelFrame(
				weatherdb);

		weatherCtrlFrame.pack();
		weatherCtrlFrame.setVisible(true);
	}
}
