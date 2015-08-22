package weatherUI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import weatherData.WeatherDb;

public class WeatherTableFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private WeatherTableUI weatherTblUI;

    public WeatherTableFrame(WeatherDb weatherdb) {
      weatherTblUI = new WeatherTableUI(weatherdb);
      WindowListener exitListener = new FrameTerminator();
      addWindowListener(exitListener);
      setTitle("Weather Table");
      setSize(575,265); 
      add(weatherTblUI);
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

