package worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import model.Game
import webservice.MemoriaRepository

class AdacaWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    private var repository: MemoriaRepository = MemoriaRepository.getInstance()

    override fun doWork(): Result {
        return try {
            val gameInputData = inputData.getString("game")
            if (gameInputData != null) {
                val game = Game.parseGame(gameInputData)
                repository.writeData(game.deviceUID, game, applicationContext)

                Result.success()
            } else throw NullPointerException()
        } catch (exception: NullPointerException) {
            return Result.failure()
        } catch (exception: Exception) {
            Result.retry()
        }
    }
}