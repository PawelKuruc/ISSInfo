package pawelkuruc.issInfo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "iss_position",
        "message",
        "timestamp"
})
public class ISSData {

    @JsonProperty("iss_position")
    private ISSPosition issPosition;

    @JsonProperty("message")
    private String message;

    @JsonProperty("timestamp")
    private Integer timestamp;


    @JsonProperty("iss_position")
    public ISSPosition getIssPosition() {
        return issPosition;
    }


    @JsonProperty("message")
    public String getMessage() {
        return message;
    }


    @JsonProperty("timestamp")
    public Integer getTimestamp() {
        return timestamp;
    }

}