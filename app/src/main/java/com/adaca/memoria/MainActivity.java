package com.adaca.memoria;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity {
    static MediaPlayer certo;

    static MediaPlayer fim;

    static float inicial_f = 0.0F;

    static float inicial_m = 10.0F;

    static MediaPlayer mp;

    public static String name_c;

    static float new_prog = 0.0F;

    public static final int pecas = 12;

    public static List<Integer> pos;

    static boolean tgpref;

    private SeekBar fx_sB;

    int lock;

    View mainView;

    private SeekBar music_sB;

    private ToggleButton mute;

    int vezes;

    static {
        inicial_f = 10.0F;
        pos = new ArrayList<Integer>();
    }

    public void jogar(View paramView) {
        pos.clear();
        int j = 0;
        int i = 0;
        while (i < 12) {
            pos.add(Integer.valueOf(j));
            int k = j;
            if (i % 2 != 0)
                k = j + 1;
            i++;
            j = k;
        }
        //MainActivity2.pontos = 0;
        //MainActivity2.cont = 0;
        Collections.shuffle(pos);
        paramView = LayoutInflater.from(this).inflate(R.layout.input_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(paramView);
        builder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface param1DialogInterface, int param1Int) {
                MainActivity.name_c = ((EditText) ((Dialog) param1DialogInterface).findViewById(R.id.edt_Name)).getText().toString();
                if (MainActivity.name_c.equals("")) {
                    Toast.makeText(MainActivity.this.getApplicationContext(), "Faltou digitar o nome!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Intent intent = new Intent((Context) MainActivity.this, MainActivity2.class);
                //MainActivity.this.startActivity(intent);
            }
        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface param1DialogInterface, int param1Int) {
                param1DialogInterface.cancel();
            }
        });
        builder.create();
        builder.show();
    }

    public void lerDados() {
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
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_main);
        fim = MediaPlayer.create(this, R.raw.fim);
        certo = MediaPlayer.create(this, R.raw.tumm_c5);
        mp = MediaPlayer.create(this, R.raw.plancton);
        this.mainView = getWindow().getDecorView();
        lerDados();
        this.vezes++;
    }

    public boolean onCreateOptionsMenu(Menu paramMenu) {
        getMenuInflater().inflate(R.menu.options_menu, paramMenu);
        return true;
    }

    protected void onDestroy() {
        super.onDestroy();
        salvaDados(inicial_m, inicial_f, this.lock, tgpref, this.vezes, name_c);
        mp.release();
        certo.release();
        fim.release();
    }

    public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
        return (paramMenuItem.getItemId() == 2131165199) ? true : super.onOptionsItemSelected(paramMenuItem);
    }

    protected void onPause() {
        super.onPause();
        mp.pause();
    }

    protected void onResume() {
        super.onResume();
        mp.setLooping(true);
        if (true){//Parabens.i != 1) {
            mp.seekTo(0);
            mp.start();
        } else {
            mp.start();
        }
        if (tgpref == true) {
            mp.setVolume(0.0F, 0.0F);
            return;
        }
        if (inicial_m == 10.0F) {
            mp.setVolume(1.0F, 1.0F);
            return;
        }
        mp.setVolume(inicial_m / 10.0F, inicial_m / 10.0F);
    }

    public void onWindowFocusChanged(boolean paramBoolean) {
        super.onWindowFocusChanged(paramBoolean);
        if (paramBoolean)
            this.mainView.setSystemUiVisibility(5894);
    }

    public void options(View paramView) {
        paramView = LayoutInflater.from((Context) this).inflate(R.layout.options, null);
        AlertDialog.Builder builder = new AlertDialog.Builder((Context) this);
        builder.setView(paramView);
        builder.setCancelable(true);
        this.mute = (ToggleButton) paramView.findViewById(R.id.toggle_sound);
        this.mute.setChecked(tgpref);
        if (!tgpref) {
            this.mute.setBackgroundResource(R.drawable.sound_off);
            if (this.lock == 1)
                this.mute.setClickable(false);
        } else {
            this.mute.setBackgroundResource(R.drawable.sound_in);
            if (this.lock == 0)
                this.mute.setClickable(true);
        }
        if (this.lock == 2) {
            this.mute.setBackgroundResource(R.drawable.sound_in);
            this.mute.setClickable(false);
            this.mute.setChecked(false);
        }
        this.mute.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton param1CompoundButton, boolean param1Boolean) {
                if (param1Boolean) {
                    MainActivity.this.mute.setBackgroundResource(R.drawable.sound_in);
                    MainActivity.tgpref = true;
                    MainActivity.mp.setVolume(0.0F, 0.0F);
                    MainActivity.certo.setVolume(0.0F, 0.0F);
                    MainActivity.fim.setVolume(0.0F, 0.0F);
                    return;
                }
                MainActivity.this.mute.setBackgroundResource(R.drawable.sound_off);
                MainActivity.tgpref = false;
                if (MainActivity.inicial_m != 0.0F)
                    if (MainActivity.inicial_m == 10.0F) {
                        MainActivity.mp.setVolume(1.0F, 1.0F);
                    } else {
                        MainActivity.mp.setVolume(MainActivity.inicial_m / 10.0F, MainActivity.inicial_m / 10.0F);
                    }
                if (MainActivity.inicial_f != 0.0F) {
                    if (MainActivity.inicial_f == 10.0F) {
                        MainActivity.certo.setVolume(1.0F, 1.0F);
                        MainActivity.fim.setVolume(1.0F, 1.0F);
                        return;
                    }
                    MainActivity.certo.setVolume(MainActivity.inicial_f / 10.0F, MainActivity.inicial_f / 10.0F);
                    MainActivity.fim.setVolume(MainActivity.inicial_f / 10.0F, MainActivity.inicial_f / 10.0F);
                }
            }
        });
        builder.create();
        builder.show();
        this.music_sB = paramView.findViewById(R.id.music_seekBar);
        this.fx_sB = paramView.findViewById(R.id.fx_seekBar);
        try {
            this.music_sB.setMax(10);
            this.fx_sB.setMax(10);
            this.music_sB.setProgress((int) inicial_m);
            this.fx_sB.setProgress((int) inicial_f);
            this.music_sB.setKeyProgressIncrement(1);
            this.fx_sB.setKeyProgressIncrement(1);
            this.music_sB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onProgressChanged(SeekBar param1SeekBar, int param1Int, boolean param1Boolean) {
                    if (param1Int == 10) {
                        MainActivity.new_prog = 1.0F;
                        MainActivity.inicial_m = 10.0F;
                    } else {
                        MainActivity.new_prog = (float) (param1Int / 10.0D);
                        MainActivity.inicial_m = param1Int;
                    }
                    MainActivity.mp.setVolume(MainActivity.new_prog, MainActivity.new_prog);
                    param1SeekBar.setProgress(param1Int);
                }

                public void onStartTrackingTouch(SeekBar param1SeekBar) {
                }

                public void onStopTrackingTouch(SeekBar param1SeekBar) {
                    if (MainActivity.inicial_m == 0.0F && MainActivity.inicial_f == 0.0F) {
                        MainActivity.tgpref = true;
                        MainActivity.this.mute.setClickable(false);
                        MainActivity.this.mute.setBackgroundResource(R.drawable.sound_in);
                        MainActivity.this.lock = 2;
                    } else {
                        MainActivity.this.mute.setBackgroundResource(R.drawable.sound_off);
                        if (MainActivity.inicial_m == 0.0F || MainActivity.inicial_f == 0.0F) {
                            MainActivity.this.mute.setClickable(false);
                            MainActivity.this.lock = 1;
                        } else {
                            MainActivity.this.mute.setClickable(true);
                            MainActivity.this.lock = 0;
                        }
                        MainActivity.tgpref = false;
                    }
                    Context context = MainActivity.this.getApplicationContext();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Música: ");
                    stringBuilder.append((int) MainActivity.inicial_m);
                    Toast.makeText(context, stringBuilder.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            this.fx_sB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onProgressChanged(SeekBar param1SeekBar, int param1Int, boolean param1Boolean) {
                    if (param1Int == 10) {
                        MainActivity.new_prog = 1.0F;
                        MainActivity.inicial_f = 10.0F;
                    } else {
                        MainActivity.new_prog = (float) (param1Int / 10.0D);
                        MainActivity.inicial_f = param1Int;
                    }
                    MainActivity.certo.setVolume(MainActivity.new_prog, MainActivity.new_prog);
                    MainActivity.fim.setVolume(MainActivity.new_prog, MainActivity.new_prog);
                    param1SeekBar.setProgress(param1Int);
                }

                public void onStartTrackingTouch(SeekBar param1SeekBar) {
                    MainActivity.mp.pause();
                    MainActivity.fim.seekTo(0);
                    MainActivity.fim.setVolume(MainActivity.new_prog, MainActivity.new_prog);
                    MainActivity.fim.start();
                    MainActivity.fim.setLooping(true);
                }

                public void onStopTrackingTouch(SeekBar param1SeekBar) {
                    if (MainActivity.inicial_m == 0.0F && MainActivity.inicial_f == 0.0F) {
                        MainActivity.tgpref = true;
                        MainActivity.this.mute.setClickable(false);
                        MainActivity.this.mute.setBackgroundResource(R.drawable.sound_in);
                        MainActivity.this.lock = 2;
                    } else {
                        MainActivity.this.mute.setBackgroundResource(R.drawable.sound_off);
                        if (MainActivity.inicial_f == 0.0F || MainActivity.inicial_m == 0.0F) {
                            MainActivity.this.mute.setClickable(false);
                            MainActivity.this.lock = 1;
                        } else {
                            MainActivity.this.mute.setClickable(true);
                            MainActivity.this.lock = 0;
                        }
                        MainActivity.tgpref = false;
                    }
                    MainActivity.fim.pause();
                    MainActivity.fim.seekTo(0);
                    MainActivity.fim.setLooping(false);
                    MainActivity.mp.start();
                    Context context = MainActivity.this.getApplicationContext();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Efeitos: ");
                    stringBuilder.append((int) MainActivity.inicial_f);
                    Toast.makeText(context, stringBuilder.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            return;
        } catch (Exception exception) {
            return;
        }
    }

    public void sair(View paramView) {
        finish();
    }

    public void salvaDados(float paramFloat1, float paramFloat2, int paramInt1, boolean paramBoolean, int paramInt2, String paramString) {
        SharedPreferences sharedPreferences6 = getSharedPreferences("musica", 0);
        SharedPreferences sharedPreferences5 = getSharedPreferences("efeitos", 0);
        SharedPreferences sharedPreferences4 = getSharedPreferences("valido", 0);
        SharedPreferences sharedPreferences3 = getSharedPreferences("botao", 0);
        SharedPreferences sharedPreferences2 = getSharedPreferences("ctrl", 0);
        SharedPreferences sharedPreferences1 = getSharedPreferences("nome", 0);
        SharedPreferences.Editor editor6 = sharedPreferences6.edit();
        editor6.putFloat("musica", paramFloat1);
        editor6.commit();
        SharedPreferences.Editor editor5 = sharedPreferences5.edit();
        editor5.putFloat("efeitos", paramFloat2);
        editor5.commit();
        SharedPreferences.Editor editor4 = sharedPreferences4.edit();
        editor4.putInt("valido", paramInt1);
        editor4.commit();
        SharedPreferences.Editor editor3 = sharedPreferences3.edit();
        editor3.putBoolean("botao", paramBoolean);
        editor3.commit();
        SharedPreferences.Editor editor2 = sharedPreferences2.edit();
        editor2.putInt("ctrl", paramInt2);
        editor2.commit();
        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
        editor1.putString("name", paramString);
        editor1.commit();
    }
}