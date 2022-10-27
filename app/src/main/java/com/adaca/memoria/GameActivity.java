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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.WorkManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import model.CardWithImageButton;
import model.Game;
import savexport.BancoController;
import utils.MediaPlayerManager;
import utils.TimeManager;
import webservice.MemoriaRepository;
import worker.WorkerUtils;

public class GameActivity extends AppCompatActivity {
    private MemoriaRepository memoriaRepository;
    private String deviceUID;
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
    //    private Game game;
    private List<CardWithImageButton> cardWithImageButtonArrayList;
    private TimeManager timeManager;
    private MediaPlayerManager mediaPlayerManager;
    private TextView ptos;
    private View mainView;
    private CardWithImageButton primeiraCarta;
    protected int acertos, dicas, erros;
    protected int erros_seguidos = -1;
    boolean acertou;

    private void init() {
//        game = Game.getInstance();
        timeManager = TimeManager.getInstance();
        cardWithImageButtonArrayList = new ArrayList<>();
        memoriaRepository = MemoriaRepository.getInstance();
        mediaPlayerManager = MediaPlayerManager.getInstance(getApplication());
        mainView = getWindow().getDecorView();
//        deviceUID = Settings.Secure.getString(getContentResolver(), "bluetooth_name");
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_game);

        init();

        timeManager.setInicio();

        Collections.shuffle(figures);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
            getWindow().getAttributes().layoutInDisplayCutoutMode = LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;

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
        ptos.setText(String.valueOf(acertos));
    }

    public void cardClickListener(View v) {
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
                        erros_seguidos++;
                    } else {
                        mediaPlayerManager.certo.start();
                        ptos.setText(String.valueOf(++acertos));
                        erros_seguidos = -1;
                        primeiraCarta.getImageButton().setVisibility(View.INVISIBLE);
                        cardWithImageButton.getImageButton().setVisibility(View.INVISIBLE);
                        acertou = false;

                        if (acertos == 6) {
                            startActivity(new Intent(this, CongratulationsActivity.class));
                            finish();
                        }
                    }

                    if (erros_seguidos == 2) {
                        mostraDica(primeiraCarta);
                        dicas++;
                    }
                    primeiraCarta = null;
                }
                setButtonsClickable(true, indexBackground);
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
        salvaDados();
    }

    private void salvaDados() {
        timeManager.setFim();
        Game game = new Game();
//        if (acertos < 6) {
//            game.setNome(game.getNome() + " (*)");
//        }
//        game.setData(timeManager.getDate());
//        game.setHora(timeManager.duracaoPartida());
        game.setDeviceUID(Settings.Secure.getString(getContentResolver(), "bluetooth_name"));
        game.setInicio(timeManager.getDate());
        game.setDuracao(timeManager.duracaoPartida());
        game.setAcertos(acertos);
        game.setDicas(dicas);
        game.setErros(erros);
//        String deviceUID = Settings.Secure.getString(getContentResolver(), "bluetooth_name");
        String resultadoDB = new BancoController(getBaseContext()).insereDado(
                game
        );
        WorkManager.getInstance(this).enqueue(WorkerUtils.addDataToRequest((game.toString())));
//        memoriaRepository.gravarDados(deviceUID, game);

        Toast.makeText(getApplicationContext(), resultadoDB, Toast.LENGTH_LONG).show();
    }

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
        assert segundaCarta != null;
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
