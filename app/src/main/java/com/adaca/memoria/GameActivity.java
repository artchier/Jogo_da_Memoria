package com.adaca.memoria;

import static android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
import static android.view.WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.work.WorkManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Game;
import savexport.DatabaseController;
import utils.MediaPlayerManager;
import utils.TimeManager;
import worker.WorkerUtils;

public class GameActivity extends AppCompatActivity {
    private final List<Integer> figures = new ArrayList<Integer>() {
        {
            add((R.drawable.circle));
            add((R.drawable.circle));
            add((R.drawable.hexagon));
            add((R.drawable.hexagon));
            add((R.drawable.losango));
            add((R.drawable.losango));
            add((R.drawable.pentagon));
            add((R.drawable.pentagon));
            add((R.drawable.triangulo));
            add((R.drawable.triangulo));
            add((R.drawable.square));
            add((R.drawable.square));
        }
    };

    private TimeManager timeManager;
    private MediaPlayerManager mediaPlayerManager;
    private TextView points;
    private View mainView;
    private ImageButton firstCard;
    protected int hits, tips, misses;
    protected int missesInARow = 0;
    boolean hit;

    private void init() {
        timeManager = TimeManager.getInstance();
        mediaPlayerManager = MediaPlayerManager.getInstance(getApplication());
        mainView = getWindow().getDecorView();
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_game);

        init();

        timeManager.setStartTime();

        Collections.shuffle(figures);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
            getWindow().getAttributes().layoutInDisplayCutoutMode = LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;

        points = findViewById(R.id.tvNumPontos);
        points.setText(String.valueOf(hits));
    }

    public void cardClickListener(View imageButton) {
        if (firstCard == null || imageButton.getId() != firstCard.getId()) {
            String label = imageButton.getResources().getResourceEntryName(imageButton.getId());
            int indexBackground = Integer.parseInt(label.split("d")[1]);
            setButtonsClickable(false);

            int figureBackground = figures.get(indexBackground);
            imageButton.setBackgroundResource(figureBackground);
            imageButton.setTag(figureBackground);

            if (firstCard != null) {
                hit = ((Integer) firstCard.getTag() == figureBackground);
            } else {
                firstCard = (ImageButton) imageButton;
            }

            new Handler().postDelayed(() -> {
                imageButton.setBackgroundResource(R.drawable.cartas);
                if (firstCard != imageButton) {
                    if (!hit) {
                        misses++;
                        missesInARow++;
                    } else {
                        mediaPlayerManager.hitFX.start();
                        points.setText(String.valueOf(++hits));
                        missesInARow = 0;
                        firstCard.setVisibility(View.INVISIBLE);
                        imageButton.setVisibility(View.INVISIBLE);
                        hit = false;

                        if (hits == 6) {
                            startActivity(new Intent(this, CongratulationsActivity.class));
                            finish();
                        }
                    }

                    if (missesInARow % 3 == 0 && missesInARow > 0) {
                        showTip(firstCard);
                        tips++;
                    }
                    firstCard = null;
                }
                setButtonsClickable(true);
            }, 500);
        }
    }

    public void onWindowFocusChanged(boolean paramBoolean) {
        super.onWindowFocusChanged(paramBoolean);
        if (paramBoolean)
            mainView.setSystemUiVisibility(SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | SYSTEM_UI_FLAG_FULLSCREEN
                    | SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
    }

    protected void onStop() {
        super.onStop();
        saveData();
    }

    private void saveData() {
        timeManager.setEndTime();
        Game game = new Game();
        game.setDeviceUID(Settings.Secure.getString(getContentResolver(), "bluetooth_name"));
        game.setInicio(timeManager.getDate());
        game.setDuracao(timeManager.gameTime());
        game.setAcertos(hits);
        game.setDicas(tips);
        game.setErros(misses);
        String resultDB = new DatabaseController(getBaseContext()).insertData(
                game
        );
        WorkManager.getInstance(this).enqueue(WorkerUtils.addDataToRequest((game.toString())));

        Toast.makeText(getApplicationContext(), resultDB, Toast.LENGTH_LONG).show();
    }

    private void showTip(View firstCard) {
        ArrayList<Integer> possibilities = new ArrayList<>();

        for (int index = 0; index < 12; index++) {
            if (firstCard.getTag().equals(figures.get(index))) {
                possibilities.add(index);
            }
            if (possibilities.size() == 2) break;
        }

        String firstCardLabel = firstCard.getResources().getResourceEntryName(firstCard.getId());
        int indexBackground = Integer.parseInt(firstCardLabel.split("d")[1]);

        if (possibilities.get(0) == indexBackground) {
            possibilities.remove(0);
        } else {
            possibilities.remove(1);
        }

        View secondCard = findViewById(getResources().getIdentifier(
                "card" + possibilities.get(0),
                "id",
                getPackageName()
        ));

        firstCard.setBackgroundResource(R.drawable.dica_animation);
        secondCard.setBackgroundResource(R.drawable.dica_animation);
        ((AnimationDrawable) firstCard.getBackground()).start();
        ((AnimationDrawable) secondCard.getBackground()).start();

        new Handler().postDelayed(() ->
                secondCard.setBackgroundResource(R.drawable.cartas), (long) (
                (AnimationDrawable) ResourcesCompat.getDrawable(getResources(),
                        R.drawable.dica_animation,
                        null)).getNumberOfFrames() * R.integer.animation_duration);
    }

    private void setButtonsClickable(boolean clickable) {
        for (int index = 0; index < 12; index++) {
            findViewById(getResources().getIdentifier(
                    "card" + index,
                    "id",
                    getPackageName()
            )).setClickable(clickable);
        }
    }
}
