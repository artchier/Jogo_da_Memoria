package worker;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.Objects;

import model.Game;
import webservice.MemoriaRepository;

public class AdacaWorker extends Worker {
    private final MemoriaRepository repository = MemoriaRepository.getInstance();

    public AdacaWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            Game game = Game.parseGame(Objects.requireNonNull(getInputData().getString("game")));
            repository.gravarDados(game.getDeviceUID(), game, getApplicationContext());

            return Result.success();
        } catch (NullPointerException exception) {
            return Result.failure();
        } catch (Exception exception) {
            return Result.retry();
        }
    }
}
