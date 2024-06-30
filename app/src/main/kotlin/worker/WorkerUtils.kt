package worker

import androidx.work.*
import java.util.concurrent.TimeUnit

object WorkerUtils {
    fun addDataToRequest(dataToBeAdded: String): OneTimeWorkRequest {
        return OneTimeWorkRequest.Builder(AdacaWorker::class.java)
                .setConstraints(Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .setRequiresBatteryNotLow(true)
                        .build())
                .setInputData(Data.Builder()
                        .putString("game", dataToBeAdded)
                        .build())
                .setBackoffCriteria(
                        BackoffPolicy.LINEAR,
                        OneTimeWorkRequest.MAX_BACKOFF_MILLIS,
                        TimeUnit.MILLISECONDS

                )
                .build()
    }
}