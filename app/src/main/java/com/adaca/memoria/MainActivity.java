package com.adaca.memoria;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
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
    MediaPlayer certo, fim;

    float efeitosVolume, musicaVolume;

    static MediaPlayer mp;

    public static String name_c;

    static float new_prog = 0.0F;

    static boolean tgpref;

    int lock;

    View mainView;

    SharedPreferences preferences;

    public void jogar() {
        //MainActivity2.pontos = 0;
        //MainActivity2.cont = 0;
        Collections.shuffle(pos);
        View paramView = LayoutInflater.from(this).inflate(R.layout.input_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(paramView);
        builder.setCancelable(false)
                .setPositiveButton("OK", (dialog, which) -> {
                    name_c = ((EditText) findViewById(R.id.edt_Name)).getText().toString();
                    if (name_c.equals("")) {
                        Toast.makeText(this.getApplicationContext(), "Faltou digitar o nome!!", Toast.LENGTH_SHORT).show();
                    } else {
                        //Intent intent = new Intent((Context) this, MainActivity2.class);
                        //this.startActivity(intent);
                    }
                })
                .setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());
        builder.create();
        builder.show();
    }

    /*public void lerDados() {
        SharedPreferences sharedPreferences1 = getSharedPreferences("musica", 0);
        SharedPreferences sharedPreferences2 = getSharedPreferences("efeitos", 0);
        SharedPreferences sharedPreferences3 = getSharedPreferences("ctrl", 0);
        SharedPreferences sharedPreferences4 = getSharedPreferences("valido", 0);
        SharedPreferences sharedPreferences5 = getSharedPreferences("botao", 0);
        SharedPreferences sharedPreferences6 = getSharedPreferences("nome", 0);
        inicial_m = sharedPreferences1.getFloat("musica", 0.0F);
        inicial_f = sharedPreferences2.getFloat("efeitos", 0.0F);
        this.vezes = sharedPreferences3.getInt("ctrl", 0);
        if (this.vezes == 0) {
            inicial_f = 10.0F;
            inicial_m = 10.0F;
        }
        this.lock = sharedPreferences4.getInt("valido", 0);
        tgpref = sharedPreferences5.getBoolean("botao", false);
        name_c = sharedPreferences6.getString("name", null);
    }*/

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        fim = MediaPlayer.create(this, R.raw.fim);
        certo = MediaPlayer.create(this, R.raw.tumm_c5);
        mp = MediaPlayer.create(this, R.raw.plancton);
        efeitosVolume = preferences.getFloat("efeitos", 10.0f);
        musicaVolume = preferences.getFloat("musica", 10.0f);
        this.mainView = getWindow().getDecorView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
            getWindow().getAttributes().layoutInDisplayCutoutMode = LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;

        findViewById(R.id.options).setOnClickListener(view -> {
            View paramView = LayoutInflater.from(this).inflate(R.layout.options, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(paramView);
            builder.setCancelable(true);
            ToggleButton mute = paramView.findViewById(R.id.toggle_sound);
            mute.setChecked(tgpref);
            if (!tgpref) {
                mute.setBackgroundResource(R.drawable.sound_off);
                if (this.lock == 1)
                    mute.setClickable(false);
            } else {
                mute.setBackgroundResource(R.drawable.sound_in);
                if (this.lock == 0)
                    mute.setClickable(true);
            }
            if (this.lock == 2) {
                mute.setBackgroundResource(R.drawable.sound_in);
                mute.setClickable(false);
                mute.setChecked(false);
            }
            mute.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                    if (param1Boolean) {
                        mute.setBackgroundResource(R.drawable.sound_in);
                        tgpref = true;
                        mp.setVolume(0.0F, 0.0F);
                        certo.setVolume(0.0F, 0.0F);
                        fim.setVolume(0.0F, 0.0F);
                        return;
                    }
                    mute.setBackgroundResource(R.drawable.sound_off);
                    tgpref = false;
                    if (musicaVolume != 0.0F)
                        if (musicaVolume == 10.0F) {
                            mp.setVolume(1.0F, 1.0F);
                        } else {
                            mp.setVolume(musicaVolume / 10.0F, musicaVolume / 10.0F);
                        }
                    if (efeitosVolume != 0.0F) {
                        if (efeitosVolume == 10.0F) {
                            certo.setVolume(1.0F, 1.0F);
                            fim.setVolume(1.0F, 1.0F);
                            return;
                        }
                        certo.setVolume(efeitosVolume / 10.0F, efeitosVolume / 10.0F);
                        fim.setVolume(efeitosVolume / 10.0F, efeitosVolume / 10.0F);
                    }
                }
            });
            builder.create();
            builder.show();
            SeekBar music_sB = paramView.findViewById(R.id.music_seekBar);
            SeekBar fx_sB = paramView.findViewById(R.id.fx_seekBar);
            try {
                music_sB.setMax(10);
                fx_sB.setMax(10);
                music_sB.setProgress((int) musicaVolume);
                fx_sB.setProgress((int) efeitosVolume);
                music_sB.setKeyProgressIncrement(1);
                fx_sB.setKeyProgressIncrement(1);
                music_sB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    public void onProgressChanged(SeekBar param1SeekBar, int param1Int, boolean param1Boolean) {
                        if (param1Int == 10) {
                            new_prog = 1.0F;
                            musicaVolume = 10.0F;
                        } else {
                            new_prog = (float) (param1Int / 10.0D);
                            musicaVolume = param1Int;
                        }
                        mp.setVolume(new_prog, new_prog);
                        param1SeekBar.setProgress(param1Int);
                    }

                    public void onStartTrackingTouch(SeekBar param1SeekBar) {
                    }

                    public void onStopTrackingTouch(SeekBar param1SeekBar) {
                        if (musicaVolume == 0.0F && efeitosVolume == 0.0F) {
                            tgpref = true;
                            mute.setClickable(false);
                            mute.setBackgroundResource(R.drawable.sound_in);
                            lock = 2;
                        } else {
                            mute.setBackgroundResource(R.drawable.sound_off);
                            if (musicaVolume == 0.0F || efeitosVolume == 0.0F) {
                                mute.setClickable(false);
                                lock = 1;
                            } else {
                                mute.setClickable(true);
                                lock = 0;
                            }
                            tgpref = false;
                        }
                        Toast.makeText(getApplicationContext(), "Música: "+ (int) musicaVolume, Toast.LENGTH_SHORT).show();
                    }
                });
                fx_sB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    public void onProgressChanged(SeekBar param1SeekBar, int param1Int, boolean param1Boolean) {
                        if (param1Int == 10) {
                            new_prog = 1.0F;
                            efeitosVolume = 10.0F;
                        } else {
                            new_prog = (float) (param1Int / 10.0D);
                            efeitosVolume = param1Int;
                        }
                        certo.setVolume(new_prog, new_prog);
                        fim.setVolume(new_prog, new_prog);
                        param1SeekBar.setProgress(param1Int);
                    }

                    public void onStartTrackingTouch(SeekBar param1SeekBar) {
                        mp.pause();
                        fim.seekTo(0);
                        fim.setVolume(new_prog, new_prog);
                        fim.start();
                        fim.setLooping(true);
                    }

                    public void onStopTrackingTouch(SeekBar param1SeekBar) {
                        if (musicaVolume == 0.0F && efeitosVolume == 0.0F) {
                            tgpref = true;
                            mute.setClickable(false);
                            mute.setBackgroundResource(R.drawable.sound_in);
                            lock = 2;
                        } else {
                            mute.setBackgroundResource(R.drawable.sound_off);
                            if (efeitosVolume == 0.0F || musicaVolume == 0.0F) {
                                mute.setClickable(false);
                                lock = 1;
                            } else {
                                mute.setClickable(true);
                                lock = 0;
                            }
                            tgpref = false;
                        }
                        fim.pause();
                        fim.seekTo(0);
                        fim.setLooping(false);
                        mp.start();

                        Toast.makeText(getApplicationContext(), "Efeitos: "+ (int) efeitosVolume, Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception ignored) {
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu paramMenu) {
        getMenuInflater().inflate(R.menu.options_menu, paramMenu);
        return true;
    }

    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor savePreferences = preferences.edit();

        savePreferences.putFloat("efeitos", efeitosVolume);
        savePreferences.putFloat("musica", musicaVolume);
        savePreferences.putBoolean("botao", tgpref);
        savePreferences.putString("name", name_c);
        savePreferences.putInt("valido", lock);

        savePreferences.apply();

        mp.release();
        certo.release();
        fim.release();
    }

    /*public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
        return paramMenuItem.getItemId() == 2131165199 || super.onOptionsItemSelected(paramMenuItem);
    }*/

    protected void onPause() {
        super.onPause();
        mp.pause();
    }

    protected void onResume() {
        super.onResume();
        mp.setLooping(true);
        if (true) {//Parabens.i != 1) {
            mp.seekTo(0);
            mp.start();
        } else {
            mp.start();
        }
        if (tgpref == true) {
            mp.setVolume(0.0F, 0.0F);
            return;
        }
        if (musicaVolume == 10.0F) {
            mp.setVolume(1.0F, 1.0F);
            return;
        }
        mp.setVolume(musicaVolume / 10.0F, musicaVolume / 10.0F);
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


    public void sair(View paramView) {
        finish();
    }
}