package pawelkuruc.issInfo;

import javax.swing.*;

import pawelkuruc.issInfo.Controller.APIHandler;
import pawelkuruc.issInfo.Controller.ISSVelocityMeasurement;
import pawelkuruc.issInfo.Model.ISSData;
import pawelkuruc.issInfo.Model.JSONParser;
import pawelkuruc.issInfo.Model.Properties;
import pawelkuruc.issInfo.View.ISSInfo;

public class Main{
    public static ISSVelocityMeasurement threadVelocity = new ISSVelocityMeasurement(Properties.URI);
    public static ISSData issInitialStatus = new ISSData();

    public static void main(String[] args){

        try {
            issInitialStatus = JSONParser.getISSData(APIHandler.getJson(Properties.URI));
            threadVelocity.start();

            JFrame frame = new JFrame("ISSInfo");
            frame.setContentPane(new ISSInfo().issInfoView);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}