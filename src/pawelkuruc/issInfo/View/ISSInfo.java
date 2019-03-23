package pawelkuruc.issInfo.View;

import pawelkuruc.issInfo.Controller.APIHandler;
import pawelkuruc.issInfo.Controller.ISSDataCalculator;
import pawelkuruc.issInfo.Controller.ISSVelocityMeasurement;
import pawelkuruc.issInfo.Model.ISSData;
import pawelkuruc.issInfo.Model.JSONParser;
import pawelkuruc.issInfo.Model.Properties;
import pawelkuruc.issInfo.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class ISSInfo {
    static String URI = "http://api.open-notify.org/iss-now.json";

    private JTextArea velocityView;
    private JTextArea distanceView;
    public JPanel issInfoView;
    private JButton vRefreshButton;
    private JButton vMeasurementStartStop;

    public ISSInfo(){
        vRefreshButton.addActionListener(new RefreshButtonClicked());
        vMeasurementStartStop.addActionListener(new StartStopButtonClicked());
        issInfoView.addComponentListener(new ComponentAdapter() {
        });
    }

    class RefreshButtonClicked implements ActionListener {
      public void actionPerformed(ActionEvent e){
          try
          {
              velocityView.setText(String.format("%.2f",Main.threadVelocity.getVelocity()*3.6)+" km/h");
              if(Main.issInitialStatus == null){ Main.issInitialStatus = JSONParser.getISSData(APIHandler.getJson(Properties.URI));}
              ISSData issCurrentStatus = JSONParser.getISSData(APIHandler.getJson(Properties.URI));
              distanceView.setText(String.format("%.2f",ISSDataCalculator.calculateDistance(Main.issInitialStatus,issCurrentStatus)/1000)+" km");
          }catch(Exception f){
              f.printStackTrace();
          }
       }
    }

    class StartStopButtonClicked implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try
            {
                if(Main.threadVelocity.isAlive()){
                    Main.threadVelocity.interrupt();
                    vMeasurementStartStop.setBackground(new Color(200,163,165));
                }else{
                    Main.threadVelocity = new ISSVelocityMeasurement(Properties.URI);
                    Main.threadVelocity.start();
                    vMeasurementStartStop.setBackground(new Color(160,200,165));
                }
            }catch(Exception f){
                f.printStackTrace();
            }
        }
    }
}


