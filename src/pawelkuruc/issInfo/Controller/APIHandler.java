package pawelkuruc.issInfo.Controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import pawelkuruc.issInfo.View.ConnectionError;

import javax.swing.*;

public class APIHandler
{
    public static String getJson(String uri)throws RuntimeException
    {
            try {
                Client apiClient = Client.create();

                WebResource webResource = apiClient.resource(uri);

                ClientResponse apiClientResponse = webResource.accept("application/json").get(ClientResponse.class);

                if (apiClientResponse.getStatus() != 200) {

                    throw new RuntimeException("Błąd HTTP ..." + apiClientResponse.getStatus());
                }

                return apiClientResponse.getEntity(String.class);
            }catch(Exception e) {
                JFrame frame = new JFrame("ConnectionError");
                frame.setContentPane(new ConnectionError().errorView);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
            return null;
    }
}
