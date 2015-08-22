package weatherUI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import weatherData.WeatherDb;

public class WeatherUpdateFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel weatherUpdateUI;

    public WeatherUpdateFrame(WeatherDb weatherdb) {
      weatherUpdateUI = new WeatherUpdateUI(weatherdb);
      WindowListener exitListener = new FrameTerminator();
      addWindowListener(exitListener);
      setTitle("Weather Update");
      setSize(400,150); 
      add(weatherUpdateUI);
   }

   public void freeFrame() {
       setVisible(false);
       dispose();
   }

   class FrameTerminator extends WindowAdapter {
      public void windowClosing(WindowEvent event) {
          freeFrame();
      }
   }

}

