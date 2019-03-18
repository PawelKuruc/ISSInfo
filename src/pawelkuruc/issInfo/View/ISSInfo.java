package pawelkuruc.issInfo.View;

import pawelkuruc.issInfo.Controller.APIHandler;
import pawelkuruc.issInfo.Controller.ISSDataCalculator;
import pawelkuruc.issInfo.Controller.ISSVelocityMeasurement;
import pawelkuruc.issInfo.Model.ISSData;
import pawelkuruc.issInfo.Model.JSONParser;
import pawelkuruc.issInfo.Model.Properties;
import pawelkuruc.issInfo.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class ISSInfo {
    static String URI = "http://api.open-notify.org/iss-now.json";

    private JTextArea velocityView;
    private JTextArea distanceView;
    public JPanel issInfoView;
    private JButton vRefreshButton;
    private JButton sRefreshButton;

    public ISSInfo(){
        vRefreshButton.addActionListener(new vRefreshButtonClicked());
        sRefreshButton.addActionListener(new sRefreshButtonClicked());
    }

    class vRefreshButtonClicked implements ActionListener {
      public void actionPerformed(ActionEvent e){
          velocityView.setText(String.valueOf(Main.threadVelocity.getVelocity()*3.6)+" km/h");
       }
    }
    class sRefreshButtonClicked implements ActionListener {
        public void actionPerformed(ActionEvent e){
            try
            {
            ISSData issCurrentStatus = JSONParser.getISSData(APIHandler.getJson(Properties.URI));
            distanceView.setText(String.valueOf(ISSDataCalculator.calculateDistance(Main.issInitialStatus,issCurrentStatus)/1000)+" km");
            }catch(Exception f){
                f.printStackTrace();
            }
        }
    }
}


