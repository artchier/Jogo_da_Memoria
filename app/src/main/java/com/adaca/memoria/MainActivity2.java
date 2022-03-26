package com.adaca.memoria;

import static android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
import static android.view.WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;

import static com.adaca.memoria.MainActivity.certo;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import figuras.Figuras;
import domain.CardWithImageButton;

public class MainActivity2 extends AppCompatActivity implements Figuras {
    private final List<CardWithImageButton> cardWithImageButtonArrayList = new ArrayList<>();
    private TextView ptos;
    private View mainView;
    private CardWithImageButton primeiraCarta;
    String data, hora;
    protected int pontos, dicas, erros = 0;
    String resultado;
    String time;
    boolean acertou;
    DateTime dt, dt2;
    DateTimeFormatter x, y;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_main2);

        this.mainView = getWindow().getDecorView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
            getWindow().getAttributes().layoutInDisplayCutoutMode = LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;

        View.OnClickListener cardClickListener = v -> {
            if (primeiraCarta == null || v.getId() != primeiraCarta.getImageButton().getId()) {
                String label = v.getResources().getResourceEntryName(v.getId());
                int indexBackground = Integer.parseInt(label.split("d")[1]);
                setButtonsClickable(false, indexBackground);

                CardWithImageButton cardWithImageButton = cardWithImageButtonArrayList.get(indexBackground);
                v.setBackgroundResource(cardWithImageButton.getAlternativeBackground());

                if (primeiraCarta != null) {
                    acertou = cardWithImageButton.compara(primeiraCarta.getAlternativeBackground());
                } else {
                    primeiraCarta = cardWithImageButton;
                }

                new Handler().postDelayed(() -> {
                    cardWithImageButton.getImageButton().setBackgroundResource(R.drawable.cartas);

                    if (primeiraCarta != cardWithImageButton) {
                        if (!acertou) {
                            erros++;
                            dicas++;
                            Log.d("erros", String.valueOf(erros));
                        } else {
                            certo.start();
                            ptos.setText(String.valueOf(++pontos));
                            dicas = 0;
                            primeiraCarta.getImageButton().setVisibility(View.INVISIBLE);
                            cardWithImageButton.getImageButton().setVisibility(View.INVISIBLE);
                            acertou = false;

                            if (pontos == 6) {
                                startActivity(new Intent(this, MainActivity.class));
                                finish();
                            }
                        }

                        if (erros % 3 == 0) {
                            mostraDica(primeiraCarta);
                            dicas++;
                        }
                        primeiraCarta = null;
                    }
                    setButtonsClickable(true, indexBackground);
                }, 500);
            }
        };

        cardWithImageButtonArrayList.add(new CardWithImageButton(figures.get(0), findViewById(R.id.card0)));
        cardWithImageButtonArrayList.add(new CardWithImageButton(figures.get(1), findViewById(R.id.card1)));
        cardWithImageButtonArrayList.add(new CardWithImageButton(figures.get(2), findViewById(R.id.card2)));
        cardWithImageButtonArrayList.add(new CardWithImageButton(figures.get(3), findViewById(R.id.card3)));
        cardWithImageButtonArrayList.add(new CardWithImageButton(figures.get(4), findViewById(R.id.card4)));
        cardWithImageButtonArrayList.add(new CardWithImageButton(figures.get(5), findViewById(R.id.card5)));
        cardWithImageButtonArrayList.add(new CardWithImageButton(figures.get(6), findViewById(R.id.card6)));
        cardWithImageButtonArrayList.add(new CardWithImageButton(figures.get(7), findViewById(R.id.card7)));
        cardWithImageButtonArrayList.add(new CardWithImageButton(figures.get(8), findViewById(R.id.card8)));
        cardWithImageButtonArrayList.add(new CardWithImageButton(figures.get(9), findViewById(R.id.card9)));
        cardWithImageButtonArrayList.add(new CardWithImageButton(figures.get(10), findViewById(R.id.card10)));
        cardWithImageButtonArrayList.add(new CardWithImageButton(figures.get(11), findViewById(R.id.card11)));
        ptos = findViewById(R.id.tvNumPontos);
        ptos.setText(String.valueOf(pontos));

        cardWithImageButtonArrayList.get(0).getImageButton().setOnClickListener(cardClickListener);
        cardWithImageButtonArrayList.get(1).getImageButton().setOnClickListener(cardClickListener);
        cardWithImageButtonArrayList.get(2).getImageButton().setOnClickListener(cardClickListener);
        cardWithImageButtonArrayList.get(3).getImageButton().setOnClickListener(cardClickListener);
        cardWithImageButtonArrayList.get(4).getImageButton().setOnClickListener(cardClickListener);
        cardWithImageButtonArrayList.get(5).getImageButton().setOnClickListener(cardClickListener);
        cardWithImageButtonArrayList.get(6).getImageButton().setOnClickListener(cardClickListener);
        cardWithImageButtonArrayList.get(7).getImageButton().setOnClickListener(cardClickListener);
        cardWithImageButtonArrayList.get(8).getImageButton().setOnClickListener(cardClickListener);
        cardWithImageButtonArrayList.get(9).getImageButton().setOnClickListener(cardClickListener);
        cardWithImageButtonArrayList.get(10).getImageButton().setOnClickListener(cardClickListener);
        cardWithImageButtonArrayList.get(11).getImageButton().setOnClickListener(cardClickListener);

        this.dt = new DateTime();
        this.y = DateTimeFormat.forPattern("dd/MM/yyyy");
        this.x = DateTimeFormat.forPattern("HH:mm:ss");
        data = this.y.print((ReadableInstant) this.dt);
        hora = this.x.print((ReadableInstant) this.dt);
    }


    public void onWindowFocusChanged(boolean paramBoolean) {
        super.onWindowFocusChanged(paramBoolean);
        if (paramBoolean)
            this.mainView.setSystemUiVisibility(SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | SYSTEM_UI_FLAG_FULLSCREEN
                    | SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
    }

        /*protected void onStop () {
            super.onStop();
            if (pontos != 6) {
                this.dt2 = new DateTime();
                hora = this.x.print((ReadableInstant) this.dt2);
                time = Tempo.result(this.dt, this.dt2);
                BancoController bancoController = new BancoController(getBaseContext());
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(MainActivity.name_c);
                stringBuilder.append(" (*)");
                resultado = bancoController.insereDado(stringBuilder.toString(), data, time, this.dicas, this.erros, pontos);
                Exportacao.Salvadados();
                Toast.makeText(getApplicationContext(), resultado, 1).show();
                Toast.makeText(getApplicationContext(), Exportacao.result, 0).show();
            }
        }*/

    private void mostraDica(CardWithImageButton primeiraCarta) {
        CardWithImageButton segundaCarta = null;
        for (int index = 0; index < 12; index++) {
            if (primeiraCarta.getImageButton().getId() == cardWithImageButtonArrayList.get(index).getImageButton().getId()) {
                continue;
            }
            if (primeiraCarta.compara(cardWithImageButtonArrayList.get(index).getAlternativeBackground())) {
                segundaCarta = cardWithImageButtonArrayList.get(index);
                break;
            }
        }
        primeiraCarta.getImageButton().setBackgroundResource(R.drawable.dica_animation);
        segundaCarta.getImageButton().setBackgroundResource(R.drawable.dica_animation);
        ((AnimationDrawable) primeiraCarta.getImageButton().getBackground()).start();
        ((AnimationDrawable) segundaCarta.getImageButton().getBackground()).start();
    }

    private void setButtonsClickable(boolean clickable, int indexBackground) {
        if (indexBackground > 0) {
            for (int index = indexBackground - 1; index >= 0; index--) {
                cardWithImageButtonArrayList.get(index).getImageButton().setClickable(clickable);
            }
        }

        for (int index = indexBackground + 1; index < 12; index++) {
            cardWithImageButtonArrayList.get(index).getImageButton().setClickable(clickable);
        }
    }
}
