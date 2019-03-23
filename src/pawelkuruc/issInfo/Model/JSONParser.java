package pawelkuruc.issInfo.Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.Nullable;

public abstract class JSONParser
{
    @Nullable
    public static ISSData getISSData(String jsonFile)
    {
        try {
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(jsonFile, ISSData.class);
        }catch (Exception e){
            return null;
        }
    }
}
