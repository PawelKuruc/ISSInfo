package pawelkuruc.issInfo.Controller;

import pawelkuruc.issInfo.Model.JSONParser;
import pawelkuruc.issInfo.Model.ISSData;
import pawelkuruc.issInfo.Model.Properties;

public class ISSVelocityMeasurement extends Thread {

    private String uri;
    private double velocity;

    public ISSVelocityMeasurement(String uri){
        super("VelocityCalculator");
        this.uri = uri;
    }

    public double getVelocity(){
        return velocity;
    }

    @Override
    public void run() {
        System.out.println("Thread: " +
                this.getName()+
                " - velocity measurement has been started");

        ISSData issStatus1;
        ISSData issStatus2;

        do {
            try {

                issStatus1 = JSONParser.getISSData(APIHandler.getJson(uri));
                Thread.sleep(Properties.readingInterval * 1000);
                issStatus2 = JSONParser.getISSData(APIHandler.getJson(uri));

                this.velocity = ISSDataCalculator.calculateVelocity(issStatus1, issStatus2);
                System.out.println("Thread: " +
                        this.getName()+
                        " - new velocity value: "+
                        velocity);

            } catch (InterruptedException i) {
                System.out.println("Thread: " +
                        this.getName() +
                        " - velocity measurement has been interrupted");
                velocity = 0;
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }while(!Thread.interrupted());
    }

}
