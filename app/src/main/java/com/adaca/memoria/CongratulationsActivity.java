package com.adaca.memoria;

import static android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import utils.MediaPlayerManager;

public class CongratulationsActivity extends AppCompatActivity {
    private MediaPlayerManager mediaPlayerManager;
    private View mainView;

    private Animation fadein = (Animation) new AlphaAnimation(0.0F, 1.0F);

    private ImageView fogos, parabens, estrelas;

    private ImageButton novo, voltar;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_congratulations);

        mediaPlayerManager = MediaPlayerManager.getInstance(getApplication());

        parabens = findViewById(R.id.ivParabens);
        estrelas = findViewById(R.id.ivEstrelas);
        fogos = findViewById(R.id.ivFogos);
        novo = findViewById(R.id.ibDeNovo);
        voltar = findViewById(R.id.ibVoltar);
        mainView = getWindow().getDecorView();

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
        fadein.setDuration(3000L);
        fadein.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation param1Animation) {
                fogos.setBackgroundResource(R.drawable.fogos_animation);
                estrelas.setBackgroundResource(R.drawable.estrela_animation);
                AnimationDrawable animationDrawable = (AnimationDrawable) estrelas.getBackground();
                ((AnimationDrawable) fogos.getBackground()).start();
                animationDrawable.start();
                //if (!tgpref)
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