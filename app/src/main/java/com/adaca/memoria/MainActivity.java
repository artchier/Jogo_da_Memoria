package com.adaca.memoria;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Collections;

import static android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
import static android.view.WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;

import constantes.Constantes;

public class MainActivity extends Activity implements Constantes {
    protected MediaPlayer musica, certo, fim;

    private float efeitosVolume, musicaVolume;

    private boolean toggleButtonIsChecked;

    private View mainView;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_main);

        lerPreferencias();

        initMediaPlayers();

        this.mainView = getWindow().getDecorView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
            getWindow().getAttributes().layoutInDisplayCutoutMode = LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;

        View optionsDialog = View.inflate(this, R.layout.options, null);

        ToggleButton muteButton = optionsDialog.findViewById(R.id.toggle_sound);

        checkToggleButton(muteButton, toggleButtonIsChecked);
        SeekBar fx_sB = optionsDialog.findViewById(R.id.fx_seekBar);
        SeekBar music_sB = optionsDialog.findViewById(R.id.music_seekBar);
        SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
            float newVolume;

            public void onProgressChanged(SeekBar seekBar, int volume, boolean fromUser) {
                newVolume = (float) (volume / 10.0);
                seekBar.setProgress(volume);

                if (!toggleButtonIsChecked) {
                    if (seekBar.getId() == R.id.fx_seekBar) {
                        fim.setVolume(newVolume, newVolume);
                    } else {
                        musica.setVolume(newVolume, newVolume);
                    }
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                if (!toggleButtonIsChecked) {
                    if (seekBar.getId() == R.id.fx_seekBar) {
                        musica.pause();
                        fim.seekTo(0);
                        fim.start();
                        fim.setLooping(true);
                    }
                }
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                if (!toggleButtonIsChecked) {
                    if (seekBar.getId() == R.id.fx_seekBar) {
                        efeitosVolume = newVolume;
                        certo.setVolume(efeitosVolume, efeitosVolume);

                        fim.pause();
                        fim.seekTo(0);
                        fim.setLooping(false);
                        musica.start();

                        Toast.makeText(getApplicationContext(), "Efeitos: " + (int) (efeitosVolume * 10), Toast.LENGTH_SHORT).show();
                    } else {
                        musicaVolume = newVolume;
                        Toast.makeText(getApplicationContext(), "Música: " + (int) (musicaVolume * 10), Toast.LENGTH_SHORT).show();
                    }

                    muteButton.setClickable(efeitosVolume != 0 || musicaVolume != 0);
                }
            }
        };
        try {
            fx_sB.setMax(10);
            fx_sB.setKeyProgressIncrement(1);
            fx_sB.setProgress((int) (efeitosVolume * 10));
            fx_sB.setOnSeekBarChangeListener(seekBarListener);

            music_sB.setMax(10);
            music_sB.setProgress((int) (musicaVolume * 10));
            music_sB.setKeyProgressIncrement(1);
            music_sB.setOnSeekBarChangeListener(seekBarListener);
        } catch (Exception ignored) {
        }

        findViewById(R.id.options).setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            if (optionsDialog.getParent() != null) {
                ((ViewGroup) optionsDialog.getParent()).removeView(optionsDialog);
            }
            builder.setView(optionsDialog);
            builder.setCancelable(true);
            muteButton.setOnCheckedChangeListener((button, isMuting) -> {
                if (efeitosVolume != 0 || musicaVolume != 0) {
                    checkToggleButton(button, isMuting);
                    setMediaPlayersVolume();
                } else {
                    button.setClickable(false);
                }
            });
            builder.create();
            builder.show();
        });

        findViewById(R.id.play).setOnClickListener(view -> {
            //MainActivity2.pontos = 0;
            //MainActivity2.cont = 0;
            Collections.shuffle(pos);
            View playDialog = LayoutInflater.from(this).inflate(R.layout.input_dialog, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(playDialog);
            builder.setCancelable(false)
                    .setPositiveButton("OK", (dialog, which) -> {
                        String nome = ((EditText) findViewById(R.id.edt_Name)).getText().toString();
                        if (nome.equals("")) {
                            Toast.makeText(this.getApplicationContext(), "Faltou digitar o nome!!", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(this, MainActivity2.class);
                            this.startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());
            builder.create();
            builder.show();
        });

        findViewById(R.id.quit).setOnClickListener(view -> {
            finish();
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        salvarPreferencias();

        musica.release();
        certo.release();
        fim.release();
    }

    protected void onPause() {
        super.onPause();
        musica.pause();
    }

    protected void onResume() {
        super.onResume();

        setMediaPlayersVolume();

        // agora funciona, mas depois tem que ter um jeito de tocar na tela de parabéns!!!
        musica.start();
//        if (true) {//Parabens.i != 1) {
//            musica.seekTo(0);
//            musica.start();
//        } else {
//            musica.start();
//        }
//        if (tgpref == true) {
//            musica.setVolume(0.0F, 0.0F);
//            return;
//        }
//        if (musicaVolume == 10.0F) {
//            musica.setVolume(1.0F, 1.0F);
//            return;
//        }
//        musica.setVolume(musicaVolume / 10.0F, musicaVolume / 10.0F);
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

    private void initMediaPlayers() {
        fim = MediaPlayer.create(this, R.raw.fim);
        certo = MediaPlayer.create(this, R.raw.tumm_c5);
        musica = MediaPlayer.create(this, R.raw.plancton);

        musica.seekTo(0);
        musica.setLooping(true);
    }

    private void setMediaPlayersVolume() {
        if (!toggleButtonIsChecked) {
            fim.setVolume(efeitosVolume, efeitosVolume);
            musica.setVolume(musicaVolume, musicaVolume);
            certo.setVolume(efeitosVolume, efeitosVolume);
        } else {
            fim.setVolume(0, 0);
            musica.setVolume(0, 0);
            certo.setVolume(0, 0);
        }
    }


    void lerPreferencias() {
        SharedPreferences preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);

        efeitosVolume = preferences.getFloat("efeitos", 1.0f);
        musicaVolume = preferences.getFloat("musica", 1.0f);
        toggleButtonIsChecked = preferences.getBoolean("mudo", false);
    }

    void salvarPreferencias() {
        SharedPreferences preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor savePreferences = preferences.edit();

        savePreferences.putFloat("efeitos", efeitosVolume);
        savePreferences.putFloat("musica", musicaVolume);
        savePreferences.putBoolean("mudo", toggleButtonIsChecked);

        savePreferences.apply();
    }

    void checkToggleButton(CompoundButton button, boolean isMuting) {
        if (!isMuting) {
            toggleButtonIsChecked = false;
            button.setBackgroundResource(R.drawable.sound_off);
        } else {
            toggleButtonIsChecked = true;
            button.setBackgroundResource(R.drawable.sound_in);
        }
    }
}