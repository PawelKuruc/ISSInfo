package pawelkuruc.issInfo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JSONParser
{
    public static ISSData getISSData(String jsonFile) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(jsonFile, ISSData.class);
    }
}
