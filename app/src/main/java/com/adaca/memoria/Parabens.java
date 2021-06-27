/*
package com.adaca.memoria;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;

public class Parabens extends AppCompatActivity {
    static int i;

    View endView;

    Animation fadein = (Animation) new AlphaAnimation(0.0F, 1.0F);

    ImageView fogos1;

    Button novo;

    ImageView p;

    ImageView star;

    Button voltar;

    public void denovo(View paramView) {
        Intent intent = new Intent((Context) this, MainActivity2.class);
        MainActivity.mp.pause();
        MainActivity2.pontos = 0;
        Collections.shuffle(MainActivity.pos);
        startActivity(intent);
        finish();
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(2131296282);
        this.p = (ImageView) findViewById(2131165273);
        this.star = (ImageView) findViewById(2131165307);
        this.fogos1 = (ImageView) findViewById(2131165246);
        this.novo = (Button) findViewById(2131165271);
        this.voltar = (Button) findViewById(2131165327);
        i = 1;
        this.endView = getWindow().getDecorView();
    }

    protected void onResume() {
        super.onResume();
        this.fadein.setDuration(3000L);
        this.fadein.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation param1Animation) {
                Parabens.this.fogos1.setBackgroundResource(2131099732);
                Parabens.this.star.setBackgroundResource(2131099719);
                AnimationDrawable animationDrawable = (AnimationDrawable) Parabens.this.star.getBackground();
                ((AnimationDrawable) Parabens.this.fogos1.getBackground()).start();
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
        this.p.startAnimation(this.fadein);
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
}*/
