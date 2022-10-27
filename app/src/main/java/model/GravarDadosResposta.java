package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GravarDadosResposta {
    @SerializedName("gravados")
    @Expose
    int gravados;

    public int getGravados() {
        return gravados;
    }

    public int getTotal() {
        return total;
    }

    @SerializedName("total")
    @Expose
    int total;

    public GravarDadosResposta(int gravados, int total) {
        this.gravados = gravados;
        this.total = total;
    }
}
