package com.adaca.memoria;

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

import java.util.Collections;

public class MainActivity3 extends AppCompatActivity {
    static int i;

    View endView;

    Animation fadein = (Animation) new AlphaAnimation(0.0F, 1.0F);

    ImageView fogos;

    ImageButton novo, voltar;

    ImageView parabens, estrelas;

    public void denovo(View paramView) {
        Intent intent = new Intent((Context) this, MainActivity2.class);
        MainActivity.musica.pause();
        MainActivity2.pontos = 0;
        Collections.shuffle(MainActivity.pos);
        startActivity(intent);
        finish();
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_main3);
        this.parabens = findViewById(R.id.ivParabens);
        this.estrelas = findViewById(R.id.ivEstrelas);
        this.fogos = findViewById(R.id.ivFogos);
        this.novo = findViewById(R.id.ibDeNovo);
        this.voltar = findViewById(R.id.ibVoltar);
        i = 1;
        this.endView = getWindow().getDecorView();
    }

    protected void onResume() {
        super.onResume();
        this.fadein.setDuration(3000L);
        this.fadein.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation param1Animation) {
                MainActivity3.this.fogos.setBackgroundResource(2131099732);
                MainActivity3.this.estrelas.setBackgroundResource(2131099719);
                AnimationDrawable animationDrawable = (AnimationDrawable) MainActivity3.this.estrelas.getBackground();
                ((AnimationDrawable) MainActivity3.this.fogos.getBackground()).start();
                animationDrawable.start();
                if (!MainActivity.tgpref)
                    MainActivity.fim.start();
            }

            public void onAnimationRepeat(Animation param1Animation) {
            }

            public void onAnimationStart(Animation param1Animation) {
                MainActivity.mp.seekTo(0);
                MainActivity.mp.start();
            }
        });
        this.parabens.startAnimation(this.fadein);
        this.novo.startAnimation(this.fadein);
        this.voltar.startAnimation(this.fadein);
    }

    public void onWindowFocusChanged(boolean paramBoolean) {
        super.onWindowFocusChanged(paramBoolean);
        if (paramBoolean)
            this.endView.setSystemUiVisibility(5894);
    }

    public void voltar(View paramView) {
        finish();
    }
}