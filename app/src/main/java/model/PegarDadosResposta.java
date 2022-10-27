package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PegarDadosResposta {
    @SerializedName("timestamp")
    @Expose
    String timestamp;

    public String getTimestamp() {
        return timestamp;
    }

    public String getTimezone() {
        return timezone;
    }

    @SerializedName("timezone")
    @Expose
    String timezone;

    public PegarDadosResposta(String timestamp, String timezone) {
        this.timestamp = timestamp;
        this.timezone = timezone;
    }
}
