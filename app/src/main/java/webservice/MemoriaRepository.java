package webservice;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import model.Game;
import model.GravarDadosResposta;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MemoriaRepository {
    private final MemoriaService memoria;
    static MemoriaRepository instance = null;

    public static MemoriaRepository getInstance() {
        if (instance == null) {
            instance = new MemoriaRepository();
        }

        return instance;
    }

    public MemoriaRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.adaca.com.br/jogos-svc/jogos/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        memoria = retrofit.create(MemoriaService.class);
    }

    public void gravarDados(String deviceUID, Game dadosPartida) {
        ArrayList<Game> dados = new ArrayList<>();
        dados.add(new Game(
                dadosPartida.getInicio(),
                dadosPartida.getDuracao(),
                dadosPartida.getAcertos(),
                dadosPartida.getDicas(),
                dadosPartida.getErros()
        ));
        Call<GravarDadosResposta> gravarDados = memoria.gravarDados(deviceUID, dados);

        gravarDados.enqueue(new Callback<GravarDadosResposta>() {
            @Override
            public void onResponse(@NonNull Call<GravarDadosResposta> call, @NonNull Response<GravarDadosResposta> response) {
                if (response.body() != null) {
                    switch (response.code()) {
                        case 200:
                            int gravados = response.body().getGravados();

                            if (gravados == 1) {
                                Log.d("sucesso", "deu bom");
                            } else {
                                Log.d("sucesso", "deu ruim");
                            }
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<GravarDadosResposta> call, Throwable t) {

            }
        });
    }

    public void gravarDados(String deviceUID, ArrayList<Game> dados) {
        Call<GravarDadosResposta> gravarDados = memoria.gravarDados(deviceUID, dados);

        gravarDados.enqueue(new Callback<GravarDadosResposta>() {
            @Override
            public void onResponse(@NonNull Call<GravarDadosResposta> call, @NonNull Response<GravarDadosResposta> response) {
                if (response.body() != null) {
                    switch (response.code()) {
                        case 200:
                            int gravados = response.body().getGravados();

                            if (gravados == 1) {
                                Log.d("sucesso", "deu bom");
                            } else {
                                Log.d("sucesso", "deu ruim");
                            }
                            break;
                    }
                }
            }

            @Override
            public void onFailure(Call<GravarDadosResposta> call, Throwable t) {

            }
        });
    }

//    public List<PegarDadosResposta> pegarDados(String inicio, String fim, String idDispositivo) {
//        final List<PegarDadosResposta> teste;
//        Call<List<PegarDadosResposta>> pegarDados = memoria.pegarDados(inicio, fim, idDispositivo);
//
//        pegarDados.enqueue(new Callback<List<PegarDadosResposta>>() {
//            @Override
//            public void onResponse(@NonNull Call<List<PegarDadosResposta>> call, @NonNull Response<List<PegarDadosResposta>> response) {
//                if (response.body() != null) {
//                    switch (response.code()) {
//                        case 200:
//                            teste = response.body();
//                            Log.d("sucesso", "deu bom no 2");
//                            break;
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<PegarDadosResposta>> call, Throwable t) {
//
//            }
//        });
//        return null;
//    }
}
