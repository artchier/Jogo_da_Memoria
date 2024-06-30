package webservice;

import java.util.ArrayList;

import model.Game;
import model.WriteDataResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MemoriaService {
    //https://www.adaca.com.br/jogos-svc/jogos/memoria?DeviceUID
    @POST("memoria?")
    Call<WriteDataResponse> writeData(@Query("DeviceUID") String idDevice, @Body ArrayList<Game> data);
}
