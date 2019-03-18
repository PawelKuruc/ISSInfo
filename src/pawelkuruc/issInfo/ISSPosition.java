package pawelkuruc.issInfo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "latitude",
        "longitude"
})
public class ISSPosition {

    @JsonProperty("latitude")
    private String latitude;

    @JsonProperty("longitude")
    private String longitude;

    @JsonProperty("latitude")
    public String getLatitude() {
        return latitude;
    }

    @JsonProperty("longitude")
    public String getLongitude() {
        return longitude;
    }

}