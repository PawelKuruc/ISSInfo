package pawelkuruc.issInfo;

import java.util.Scanner;

public class Main
{
    static String URI = "http://api.open-notify.org/iss-now.json";

    public static void main(String args[]) {
        ISSVelocityMeasurement threadVelocity = new ISSVelocityMeasurement(URI);
        threadVelocity.start();

        try {
            ISSData issInitialStatus = JSONParser.getISSData(APIHandler.getJson(URI));

            System.out.println("Aktualna pozycja stacji ISS" +
                    "\nszerokość: "+issInitialStatus.getIssPosition().getLatitude() +
                    "\ndługość: "+issInitialStatus.getIssPosition().getLongitude());

            while(true){
                boolean exit = false;
                Scanner scan = new Scanner(System.in);

                String command = scan.nextLine();

                double velocity;

                switch (command){
                    case "distance":                                                      //obliczanie drogi
                        double distance = ISSDataCalculator.calculateDistance(issInitialStatus,JSONParser.getISSData(APIHandler.getJson(URI)));
                        System.out.println("Stacja ISS przebyła "+distance/1000+" km od momentu uruchomienia tego programu.");
                        break;

                    case "velocity":
                        velocity = threadVelocity.getVelocity();
                        System.out.println("Prędkość ISS wynosi "+velocity+" m/s = "+velocity*3.6+" km/h = "+velocity/1000+" km/s.");
                        break;

                    case "help":
                        System.out.println("help - wyświetla listę komend" +                         //wyświetlenie komend
                                "\nexit - kończy program" +
                                "\ndistance - wyświetla dystans przebyty przez stację ISS od startu programu" +
                                "\nvelocity - oblicza aktualną prędkość stacji ISS");
                        break;

                    case "exit":
                        exit = true;
                        break;

                    default:
                        System.out.println("Nieznane polecenie, wpisz \"help\", aby uzyskać informację.");

                }
                if (exit == true) break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            threadVelocity.exitMeasurement = true;
        }
    }
}
