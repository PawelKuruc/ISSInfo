package pawelkuruc.issInfo;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class APIHandler
{
    public static String getJson(String uri)
    {
        try
        {
            Client apiClient = Client.create();

            WebResource webResource = apiClient.resource(uri);

            ClientResponse apiClientResponse = webResource.accept("application/json").get(ClientResponse.class);

            if (apiClientResponse.getStatus() != 200)
            {
                throw new RuntimeException("Błąd HTTP ..."+apiClientResponse.getStatus());
            }

            return apiClientResponse.getEntity(String.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
