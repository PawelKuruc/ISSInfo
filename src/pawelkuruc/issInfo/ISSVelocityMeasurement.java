package pawelkuruc.issInfo;

import java.util.concurrent.TimeUnit;

public class ISSVelocityMeasurement extends Thread {

    private String uri;
    private double velocity;
    private int readingInterval = 5;

    public boolean exitMeasurement = false;

    public ISSVelocityMeasurement(String uri){
        super("VelocityCalculator");
        this.uri = uri;
    }

    public double getVelocity(){
        return this.velocity;
    }

    public void run() {
        System.out.println("Thread: " +
                this.getName()+
                " - uruchomiono pomiar prędkości");

        ISSData issStatus1;
        ISSData issStatus2;

        try {
        while(!exitMeasurement) {
            issStatus1 = JSONParser.getISSData(APIHandler.getJson(uri));
            TimeUnit.SECONDS.sleep(readingInterval);
            issStatus2 = JSONParser.getISSData(APIHandler.getJson(uri));

            this.velocity = ISSDataCalculator.calculateVelocity(issStatus1, issStatus2);
        }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            System.out.println("Thread: " +
                    this.getName()+
                    " - zakończono pomiar prędkości");
        }
    }

}
