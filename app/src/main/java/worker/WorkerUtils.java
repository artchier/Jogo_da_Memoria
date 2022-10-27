package worker;

import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Worker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import model.Game;

public class WorkerUtils {
//    public static <T> Map<String, T> prepareDataToBeAdded(String key, List<T> value) {
//        Map<String, T> dataMapped = new HashMap<>();
//
//        dataMapped.put(key, null);
//        for (int index = 0; index < value.size(); index++) {
//            dataMapped.put("parameter "+ index, value.get(index));
//        }
//
//        return dataMapped;
//    }
//
//    public static <T> Map<String, T> prepareDataToBeAdded(T value) {
//        Map<String, T> dataMapped = new HashMap<>();
//
//        dataMapped.put("0", value);
//
//        return dataMapped;
//    }

    public static OneTimeWorkRequest addDataToRequest(String dataToBeAdded) {

//        try {
////            for (int index = 0; index < 5; index++) {
//            request.add(
//                    new OneTimeWorkRequest.Builder(AdacaWorker.class)
//                            .setConstraints(new Constraints.Builder()
//                                    .setRequiredNetworkType(NetworkType.CONNECTED)
//                                    .setRequiresBatteryNotLow(true)
//                                    .build())
//                            .setInputData(new Data.Builder()
//                                    .putString(String.valueOf(index), dataToBeAdded.get(index))
//                                    .build())
//                            .setBackoffCriteria(
//                                    BackoffPolicy.LINEAR,
//                                    OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
//                                    TimeUnit.MILLISECONDS)
//                            .build()
//            );
//        }
//    } catch(
//    Exception ignored)
//
//    {
//    }

        return new OneTimeWorkRequest.Builder(AdacaWorker.class)
                .setConstraints(new Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .setRequiresBatteryNotLow(true)
                        .build())
                .setInputData(new Data.Builder()
                        .putString("game", dataToBeAdded)
                        .build())
                .setBackoffCriteria(
                        BackoffPolicy.LINEAR,
                        OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                        TimeUnit.MILLISECONDS)
                .build();
    }
}
