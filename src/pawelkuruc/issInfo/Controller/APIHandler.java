package pawelkuruc.issInfo.Controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.jetbrains.annotations.Nullable;
import pawelkuruc.issInfo.Main;
import pawelkuruc.issInfo.Model.Properties;

import javax.swing.*;

public abstract class APIHandler
{
    @Nullable
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
                JOptionPane.showMessageDialog(null, Properties.connectionErrorMessage);
                Main.threadVelocity.interrupt();
            }
            return null;
    }
}
