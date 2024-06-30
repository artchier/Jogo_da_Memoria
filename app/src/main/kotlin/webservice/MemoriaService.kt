package webservice

import model.Game
import model.WriteDataResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface MemoriaService {
    //https://www.adaca.com.br/jogos-svc/jogos/memoria?DeviceUID
    @POST("memoria?")
    fun writeData(@Query("DeviceUID") idDevice: String, @Body data: ArrayList<Game>): Call<WriteDataResponse>
}