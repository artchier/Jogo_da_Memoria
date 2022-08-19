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
    private final Animation fadein = (Animation) new AlphaAnimation(0.0F, 1.0F);
    private ImageView fogos, parabens, estrelas;
    private ImageButton novo, voltar;
    private ConstraintLayout clAnimations;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_congratulations);

        mediaPlayerManager = MediaPlayerManager.getInstance(getApplication());

        parabens = findViewById(R.id.ivParabens);
        estrelas = findViewById(R.id.ivEstrelas);
        fogos = findViewById(R.id.ivFogos);
        novo = findViewById(R.id.ibDeNovo);
        voltar = findViewById(R.id.ibVoltar);
        clAnimations = findViewById(R.id.clAnimations);
        mainView = getWindow().getDecorView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
            getWindow().getAttributes().layoutInDisplayCutoutMode = LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;

        novo.setOnClickListener(view -> {
            Intent intent = new Intent((Context) this, GameActivity.class);
            mediaPlayerManager.musica.pause();
            startActivity(intent);
            finish();
        });

        voltar.setOnClickListener(view -> finish());
    }

    protected void onResume() {
        super.onResume();
        fadein.setDuration(1000L);
        fadein.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation param1Animation) {
                fogos.setBackgroundResource(R.drawable.fogos_animation);
                estrelas.setBackgroundResource(R.drawable.estrela_animation);
                AnimationDrawable animationDrawable = (AnimationDrawable) estrelas.getBackground();
                ((AnimationDrawable) fogos.getBackground()).start();
                animationDrawable.start();
                mediaPlayerManager.fim.start();
            }

            public void onAnimationRepeat(Animation param1Animation) {
            }

            public void onAnimationStart(Animation param1Animation) {
                mediaPlayerManager.musica.seekTo(0);
                mediaPlayerManager.musica.start();
            }
        });
        parabens.startAnimation(fadein);
        clAnimations.startAnimation(fadein);
        novo.startAnimation(fadein);
        voltar.startAnimation(fadein);
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