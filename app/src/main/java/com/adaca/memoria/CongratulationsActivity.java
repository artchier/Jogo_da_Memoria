package com.adaca.memoria;

import static android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
import static android.view.WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import utils.MediaPlayerManager;

public class CongratulationsActivity extends AppCompatActivity {
    private MediaPlayerManager mediaPlayerManager;
    private View mainView;
    private final Animation fadein = new AlphaAnimation(0.0F, 1.0F);
    private ImageView fireworks, congratulations, stars;
    private ImageButton newGame, quit;
    private ConstraintLayout clAnimations;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_congratulations);

        mediaPlayerManager = MediaPlayerManager.getInstance(getApplication());

        congratulations = findViewById(R.id.ivParabens);
        stars = findViewById(R.id.ivEstrelas);
        fireworks = findViewById(R.id.ivFogos);
        newGame = findViewById(R.id.ibDeNovo);
        quit = findViewById(R.id.ibVoltar);
        clAnimations = findViewById(R.id.clAnimations);
        mainView = getWindow().getDecorView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
            getWindow().getAttributes().layoutInDisplayCutoutMode = LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;

        newGame.setOnClickListener(view -> {
            Intent intent = new Intent((Context) this, GameActivity.class);
            mediaPlayerManager.music.pause();
            startActivity(intent);
            finish();
        });

        quit.setOnClickListener(view -> finish());
    }

    protected void onResume() {
        super.onResume();
        fadein.setDuration(1000L);
        fadein.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation param1Animation) {
                fireworks.setBackgroundResource(R.drawable.fogos_animation);
                stars.setBackgroundResource(R.drawable.estrela_animation);
                ((AnimationDrawable) fireworks.getBackground()).start();
                ((AnimationDrawable) stars.getBackground()).start();
                mediaPlayerManager.endFX.start();
            }

            public void onAnimationRepeat(Animation param1Animation) {
            }

            public void onAnimationStart(Animation param1Animation) {
                mediaPlayerManager.music.seekTo(0);
                mediaPlayerManager.music.start();
            }
        });
        congratulations.startAnimation(fadein);
        clAnimations.startAnimation(fadein);
        newGame.startAnimation(fadein);
        quit.startAnimation(fadein);
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
}