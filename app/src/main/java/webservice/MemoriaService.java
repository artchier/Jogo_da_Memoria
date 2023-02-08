package webservice;

import java.util.ArrayList;
import java.util.List;

import model.Game;
import model.PegarDadosResposta;
import model.GravarDadosResposta;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MemoriaService {
    //https://www.adaca.com.br/jogos-svc/jogos/memoria?DeviceUID
    //TODO precisa do array list?
    @POST("memoria?")
    Call<GravarDadosResposta> gravarDados(@Query("DeviceUID") String idDispositivo, @Body ArrayList<Game> dados);

    @GET("memoria/existe/{inicio}/{fim}/?DeviceUID={deviceUID}")
    Call<List<PegarDadosResposta>> pegarDados(@Path("inicio") String inicio, @Path("fim") String fim, @Path("deviceUID") String idDispositivo);
//    POST: /jogos/memoria?DeviceUID=AAAAAAAAA
//    Coloca a coleção
//
//    GET: /jogos/memoria/existe/2018-01-01/2022-01-01/?DeviceUID=AAAAAAAAA
//    'POST', '/jogos/{nome}', 'postCollection',
//            'GET',  '/jogos/{nome}/existe/{min:\d+}/{max:\d+}/', 'getChecarRange',
//            'GET',  '/jogos/{nome}/existe/{dataini}/{datafim}/', 'getChecarData',
//            'GET',  '/jogos', 'getJogosDisponiveis',
}
