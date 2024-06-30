package webservice;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.WorkManager;

import java.util.ArrayList;

import model.Game;
import model.WriteDataResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import worker.WorkerUtils;

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

    public void writeData(String deviceUID, Game gameData, Context context) {
        ArrayList<Game> data = new ArrayList<>();
        data.add(new Game(
                gameData.getInicio(),
                gameData.getDuracao(),
                gameData.getAcertos(),
                gameData.getDicas(),
                gameData.getErros()
        ));
        Call<WriteDataResponse> writeData = memoria.writeData(deviceUID, data);

        writeData.enqueue(new Callback<WriteDataResponse>() {
            @Override
            public void onResponse(@NonNull Call<WriteDataResponse> call, @NonNull Response<WriteDataResponse> response) {
                if (response.isSuccessful() && response.body().getSavedGames() == 1) {
                    Log.d("sucesso", "deu bom");
                } else {
                    Log.d("sucesso", "deu ruim");
                    WorkManager.getInstance(context).enqueue(WorkerUtils.addDataToRequest((gameData.toString())));
                }
            }

            @Override
            public void onFailure(Call<WriteDataResponse> call, Throwable t) {
                Log.d("", "");
            }
        });
    }
}
