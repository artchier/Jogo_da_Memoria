package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WriteDataResponse {
    @SerializedName("gravados")
    @Expose
    int savedGames;

    public int getSavedGames() {
        return savedGames;
    }

    public int getTotal() {
        return total;
    }

    @SerializedName("total")
    @Expose
    int total;

    public WriteDataResponse(int savedGames, int total) {
        this.savedGames = savedGames;
        this.total = total;
    }
}
