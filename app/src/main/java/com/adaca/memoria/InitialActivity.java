package com.adaca.memoria;

import static android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
import static android.view.WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import utils.MediaPlayerManager;

public class InitialActivity extends AppCompatActivity {
    private MediaPlayerManager mediaPlayerManager;
    private boolean toggleButtonIsChecked;
    private View mainView;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_initial);

        mediaPlayerManager = MediaPlayerManager.getInstance(this);
        mainView = getWindow().getDecorView();
        readPreferences();

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
                        mediaPlayerManager.endFX.setVolume(newVolume, newVolume);
                    } else {
                        mediaPlayerManager.music.setVolume(newVolume, newVolume);
                    }
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                if (!toggleButtonIsChecked) {
                    if (seekBar.getId() == R.id.fx_seekBar) {
                        mediaPlayerManager.music.pause();
                        mediaPlayerManager.endFX.seekTo(0);
                        mediaPlayerManager.endFX.start();
                        mediaPlayerManager.endFX.setLooping(true);
                    }
                }
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                if (!toggleButtonIsChecked) {
                    if (seekBar.getId() == R.id.fx_seekBar) {
                        mediaPlayerManager.setFxVolume(newVolume);
                        mediaPlayerManager.hitFX.setVolume(newVolume, newVolume);

                        mediaPlayerManager.endFX.pause();
                        mediaPlayerManager.endFX.seekTo(0);
                        mediaPlayerManager.endFX.setLooping(false);
                        mediaPlayerManager.music.start();

                        Toast.makeText(getApplicationContext(), getString(R.string.fx_toast) + (int) (newVolume * 10), Toast.LENGTH_SHORT).show();
                    } else {
                        mediaPlayerManager.setMusicVolume(newVolume);
                        Toast.makeText(getApplicationContext(), getString(R.string.music_toast) + (int) (newVolume * 10), Toast.LENGTH_SHORT).show();
                    }

                    muteButton.setClickable(mediaPlayerManager.getFxVolume() != 0 || mediaPlayerManager.getMusicVolume() != 0);
                }
            }
        };
        try {
            fx_sB.setMax(10);
            fx_sB.setKeyProgressIncrement(1);
            fx_sB.setProgress((int) (mediaPlayerManager.getFxVolume() * 10));
            fx_sB.setOnSeekBarChangeListener(seekBarListener);

            music_sB.setMax(10);
            music_sB.setProgress((int) (mediaPlayerManager.getMusicVolume() * 10));
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
            muteButton.setOnCheckedChangeListener((button, isMute) -> {
                if (mediaPlayerManager.getFxVolume() != 0 || mediaPlayerManager.getMusicVolume() != 0) {
                    checkToggleButton(button, isMute);
                    mediaPlayerManager.setMediaPlayersVolume(toggleButtonIsChecked);
                } else {
                    button.setClickable(false);
                }
            });
            builder.create();
            builder.show();
        });

        findViewById(R.id.play).setOnClickListener(view -> {
            View playDialog = LayoutInflater.from(this).inflate(R.layout.input_dialog, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(playDialog);
            builder.setCancelable(false)
                    .setPositiveButton("OK", (dialog, which) -> {
                        String nome = ((EditText) playDialog.findViewById(R.id.etNome)).getText().toString();
                        if (nome.equals("")) {
                            Toast.makeText(getApplicationContext(), "Faltou digitar o nome!!", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(this, GameActivity.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());
            builder.create();
            builder.show();
        });

        findViewById(R.id.quit).setOnClickListener(view -> finish());
    }

    protected void onDestroy() {
        super.onDestroy();
        mediaPlayerManager.music.release();
        mediaPlayerManager.hitFX.release();
        mediaPlayerManager.endFX.release();

        MediaPlayerManager.resetInstance();
    }

    protected void onPause() {
        super.onPause();
        savePreferences();

        mediaPlayerManager.music.pause();
    }

    protected void onResume() {
        super.onResume();
        mediaPlayerManager.setMediaPlayersVolume(toggleButtonIsChecked);
        mediaPlayerManager.music.start();
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

    void readPreferences() {
        SharedPreferences preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);

        mediaPlayerManager.setFxVolume(preferences.getFloat("efeitos", 1.0f));
        mediaPlayerManager.setMusicVolume(preferences.getFloat("musica", 1.0f));
        toggleButtonIsChecked = preferences.getBoolean("mudo", false);
    }

    void savePreferences() {
        SharedPreferences preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor savePreferences = preferences.edit();

        savePreferences.putFloat("efeitos", mediaPlayerManager.getFxVolume());
        savePreferences.putFloat("musica", mediaPlayerManager.getMusicVolume());
        savePreferences.putBoolean("mudo", toggleButtonIsChecked);

        savePreferences.apply();
    }

    private void checkToggleButton(CompoundButton button, boolean isMute) {
        if (!isMute) {
            toggleButtonIsChecked = false;
            button.setBackgroundResource(R.drawable.sound_off);
        } else {
            toggleButtonIsChecked = true;
            button.setBackgroundResource(R.drawable.sound_in);
        }
        button.setChecked(toggleButtonIsChecked);
    }
}