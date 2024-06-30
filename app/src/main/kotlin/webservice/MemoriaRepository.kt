package webservice

import android.content.Context
import android.util.Log
import androidx.work.WorkManager
import model.Game
import model.WriteDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import worker.WorkerUtils

class MemoriaRepository {
    private var memoria: MemoriaService

    init {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://www.adaca.com.br/jogos-svc/jogos/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        memoria = retrofit.create(MemoriaService::class.java)
    }

    fun writeData(deviceUID: String, gameData: Game, context: Context) {
        val data = arrayListOf(
                Game(
                        gameData.inicio,
                        gameData.duracao,
                        gameData.acertos,
                        gameData.dicas,
                        gameData.erros
                ))

        memoria.writeData(deviceUID, data).enqueue(object : Callback<WriteDataResponse> {
            override fun onResponse(call: Call<WriteDataResponse>, response: Response<WriteDataResponse>) {
                if (response.isSuccessful && response.body()?.savedGames == 1) Log.d(" sucesso", "deu bom")
                else {
                    Log.d("sucesso", "deu ruim")
                    WorkManager.getInstance(context).enqueue(WorkerUtils.addDataToRequest(gameData.toString()))
                }
            }

            override fun onFailure(call: Call<WriteDataResponse>, t: Throwable) {
            }
        })
    }

    companion object {
        @Volatile
        private var instance: MemoriaRepository? = null

        fun getInstance(): MemoriaRepository {
            return instance ?: synchronized(this) {
                instance ?: MemoriaRepository().also { instance = it }
            }
        }
    }
}