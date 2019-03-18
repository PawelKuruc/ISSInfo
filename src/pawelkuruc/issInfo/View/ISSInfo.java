package pawelkuruc.issInfo.View;

import pawelkuruc.issInfo.Controller.APIHandler;
import pawelkuruc.issInfo.Controller.ISSDataCalculator;
import pawelkuruc.issInfo.Model.ISSData;
import pawelkuruc.issInfo.Model.JSONParser;
import pawelkuruc.issInfo.Model.Properties;
import pawelkuruc.issInfo.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class ISSInfo {
    static String URI = "http://api.open-notify.org/iss-now.json";

    private JTextArea velocityView;
    private JTextArea distanceView;
    public JPanel issInfoView;
    private JButton vRefreshButton;

    public ISSInfo(){
        vRefreshButton.addActionListener(new RefreshButtonClicked());
        issInfoView.addComponentListener(new ComponentAdapter() {
        });
    }

    class RefreshButtonClicked implements ActionListener {
      public void actionPerformed(ActionEvent e){
          try
          {

              velocityView.setText(String.format("%.2f",Main.threadVelocity.getVelocity()*3.6)+" km/h");
              ISSData issCurrentStatus = JSONParser.getISSData(APIHandler.getJson(Properties.URI));
              distanceView.setText(String.format("%.2f",ISSDataCalculator.calculateDistance(Main.issInitialStatus,issCurrentStatus)/1000)+" km");
          }catch(Exception f){
              f.printStackTrace();
          }
       }
    }
}


