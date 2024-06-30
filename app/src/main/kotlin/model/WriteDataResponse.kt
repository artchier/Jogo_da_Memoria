package model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WriteDataResponse(
        @SerializedName("gravados")
        @Expose
        val savedGames: Int,

        @SerializedName("total")
        @Expose
        val total: Int
)
