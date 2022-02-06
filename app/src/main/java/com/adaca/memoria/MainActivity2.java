/*
package com.adaca.memoria;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import savexport.BancoController;
import savexport.Exportacao;
import savexport.Tempo;

public class MainActivity2 extends AppCompatActivity {
    static int cont;

    static String data;

    static String hora;

    static int pontos = 0;

    static String resultado;

    static String time;

    List<ImageButton> bt = new ArrayList<ImageButton>();

    int controle = 0;

    int dicas = 0;

    DateTime dt;

    DateTime dt2;

    int er = 0;

    int erros = 0;

    final Handler handler = new Handler();

    int i;

    int j = 0;

    int[] pos = new int[2];

    int primeiro;

    TextView ptos;

    private String r = "";

    DateTimeFormatter x;

    DateTimeFormatter y;

    static {
        cont = 0;
    }

    public void bt1(View paramView) {
        ((ImageButton) this.bt.get(0)).setClickable(false);
        if (((Integer) MainActivity.pos.get(0)).intValue() == 0) {
            ((ImageButton) this.bt.get(0)).setBackgroundResource(2131099715);
            if (cont == 0)
                this.primeiro = 0;
        }
        if (((Integer) MainActivity.pos.get(0)).intValue() == 1) {
            ((ImageButton) this.bt.get(0)).setBackgroundResource(2131099741);
            if (cont == 0)
                this.primeiro = 1;
        }
        if (((Integer) MainActivity.pos.get(0)).intValue() == 2) {
            ((ImageButton) this.bt.get(0)).setBackgroundResource(2131099746);
            if (cont == 0)
                this.primeiro = 2;
        }
        if (((Integer) MainActivity.pos.get(0)).intValue() == 3) {
            ((ImageButton) this.bt.get(0)).setBackgroundResource(2131099749);
            if (cont == 0)
                this.primeiro = 3;
        }
        if (((Integer) MainActivity.pos.get(0)).intValue() == 4) {
            ((ImageButton) this.bt.get(0)).setBackgroundResource(2131099751);
            if (cont == 0)
                this.primeiro = 4;
        }
        if (((Integer) MainActivity.pos.get(0)).intValue() == 5) {
            ((ImageButton) this.bt.get(0)).setBackgroundResource(2131099753);
            if (cont == 0)
                this.primeiro = 5;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.r);
        stringBuilder.append(MainActivity.pos.get(0));
        this.r = stringBuilder.toString();
        cont++;
        if (cont == 2) {
            this.i = 0;
            while (this.i < 12) {
                ((ImageButton) this.bt.get(this.i)).setClickable(false);
                this.i++;
            }
            comp(this.r);
            cont = 0;
            this.r = "";
        }
    }

    public void bt10(View paramView) {
        ((ImageButton) this.bt.get(9)).setClickable(false);
        if (((Integer) MainActivity.pos.get(9)).intValue() == 0) {
            ((ImageButton) this.bt.get(9)).setBackgroundResource(2131099715);
            if (cont == 0)
                this.primeiro = 0;
        }
        if (((Integer) MainActivity.pos.get(9)).intValue() == 1) {
            ((ImageButton) this.bt.get(9)).setBackgroundResource(2131099741);
            if (cont == 0)
                this.primeiro = 1;
        }
        if (((Integer) MainActivity.pos.get(9)).intValue() == 2) {
            ((ImageButton) this.bt.get(9)).setBackgroundResource(2131099746);
            if (cont == 0)
                this.primeiro = 2;
        }
        if (((Integer) MainActivity.pos.get(9)).intValue() == 3) {
            ((ImageButton) this.bt.get(9)).setBackgroundResource(2131099749);
            if (cont == 0)
                this.primeiro = 3;
        }
        if (((Integer) MainActivity.pos.get(9)).intValue() == 4) {
            ((ImageButton) this.bt.get(9)).setBackgroundResource(2131099751);
            if (cont == 0)
                this.primeiro = 4;
        }
        if (((Integer) MainActivity.pos.get(9)).intValue() == 5) {
            ((ImageButton) this.bt.get(9)).setBackgroundResource(2131099753);
            if (cont == 0)
                this.primeiro = 5;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.r);
        stringBuilder.append(MainActivity.pos.get(9));
        this.r = stringBuilder.toString();
        cont++;
        if (cont == 2) {
            this.i = 0;
            while (this.i < 12) {
                ((ImageButton) this.bt.get(this.i)).setClickable(false);
                this.i++;
            }
            comp(this.r);
            cont = 0;
            this.r = "";
        }
    }

    public void bt11(View paramView) {
        ((ImageButton) this.bt.get(10)).setClickable(false);
        if (((Integer) MainActivity.pos.get(10)).intValue() == 0) {
            ((ImageButton) this.bt.get(10)).setBackgroundResource(2131099715);
            if (cont == 0)
                this.primeiro = 0;
        }
        if (((Integer) MainActivity.pos.get(10)).intValue() == 1) {
            ((ImageButton) this.bt.get(10)).setBackgroundResource(2131099741);
            if (cont == 0)
                this.primeiro = 1;
        }
        if (((Integer) MainActivity.pos.get(10)).intValue() == 2) {
            ((ImageButton) this.bt.get(10)).setBackgroundResource(2131099746);
            if (cont == 0)
                this.primeiro = 2;
        }
        if (((Integer) MainActivity.pos.get(10)).intValue() == 3) {
            ((ImageButton) this.bt.get(10)).setBackgroundResource(2131099749);
            if (cont == 0)
                this.primeiro = 3;
        }
        if (((Integer) MainActivity.pos.get(10)).intValue() == 4) {
            ((ImageButton) this.bt.get(10)).setBackgroundResource(2131099751);
            if (cont == 0)
                this.primeiro = 4;
        }
        if (((Integer) MainActivity.pos.get(10)).intValue() == 5) {
            ((ImageButton) this.bt.get(10)).setBackgroundResource(2131099753);
            if (cont == 0)
                this.primeiro = 5;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.r);
        stringBuilder.append(MainActivity.pos.get(10));
        this.r = stringBuilder.toString();
        cont++;
        if (cont == 2) {
            this.i = 0;
            while (this.i < 12) {
                ((ImageButton) this.bt.get(this.i)).setClickable(false);
                this.i++;
            }
            comp(this.r);
            cont = 0;
            this.r = "";
        }
    }

    public void bt12(View paramView) {
        ((ImageButton) this.bt.get(11)).setClickable(false);
        if (((Integer) MainActivity.pos.get(11)).intValue() == 0) {
            ((ImageButton) this.bt.get(11)).setBackgroundResource(2131099715);
            if (cont == 0)
                this.primeiro = 0;
        }
        if (((Integer) MainActivity.pos.get(11)).intValue() == 1) {
            ((ImageButton) this.bt.get(11)).setBackgroundResource(2131099741);
            if (cont == 0)
                this.primeiro = 1;
        }
        if (((Integer) MainActivity.pos.get(11)).intValue() == 2) {
            ((ImageButton) this.bt.get(11)).setBackgroundResource(2131099746);
            if (cont == 0)
                this.primeiro = 2;
        }
        if (((Integer) MainActivity.pos.get(11)).intValue() == 3) {
            ((ImageButton) this.bt.get(11)).setBackgroundResource(2131099749);
            if (cont == 0)
                this.primeiro = 3;
        }
        if (((Integer) MainActivity.pos.get(11)).intValue() == 4) {
            ((ImageButton) this.bt.get(11)).setBackgroundResource(2131099751);
            if (cont == 0)
                this.primeiro = 4;
        }
        if (((Integer) MainActivity.pos.get(11)).intValue() == 5) {
            ((ImageButton) this.bt.get(11)).setBackgroundResource(2131099753);
            if (cont == 0)
                this.primeiro = 5;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.r);
        stringBuilder.append(MainActivity.pos.get(11));
        this.r = stringBuilder.toString();
        cont++;
        if (cont == 2) {
            this.i = 0;
            while (this.i < 12) {
                ((ImageButton) this.bt.get(this.i)).setClickable(false);
                this.i++;
            }
            comp(this.r);
            cont = 0;
            this.r = "";
        }
    }

    public void bt2(View paramView) {
        ((ImageButton) this.bt.get(1)).setClickable(false);
        if (((Integer) MainActivity.pos.get(1)).intValue() == 0) {
            ((ImageButton) this.bt.get(1)).setBackgroundResource(2131099715);
            if (cont == 0)
                this.primeiro = 0;
        }
        if (((Integer) MainActivity.pos.get(1)).intValue() == 1) {
            ((ImageButton) this.bt.get(1)).setBackgroundResource(2131099741);
            if (cont == 0)
                this.primeiro = 1;
        }
        if (((Integer) MainActivity.pos.get(1)).intValue() == 2) {
            ((ImageButton) this.bt.get(1)).setBackgroundResource(2131099746);
            if (cont == 0)
                this.primeiro = 2;
        }
        if (((Integer) MainActivity.pos.get(1)).intValue() == 3) {
            ((ImageButton) this.bt.get(1)).setBackgroundResource(2131099749);
            if (cont == 0)
                this.primeiro = 3;
        }
        if (((Integer) MainActivity.pos.get(1)).intValue() == 4) {
            ((ImageButton) this.bt.get(1)).setBackgroundResource(2131099751);
            if (cont == 0)
                this.primeiro = 4;
        }
        if (((Integer) MainActivity.pos.get(1)).intValue() == 5) {
            ((ImageButton) this.bt.get(1)).setBackgroundResource(2131099753);
            if (cont == 0)
                this.primeiro = 5;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.r);
        stringBuilder.append(MainActivity.pos.get(1));
        this.r = stringBuilder.toString();
        cont++;
        if (cont == 2) {
            this.i = 0;
            while (this.i < 12) {
                ((ImageButton) this.bt.get(this.i)).setClickable(false);
                this.i++;
            }
            comp(this.r);
            cont = 0;
            this.r = "";
        }
    }

    public void bt3(View paramView) {
        ((ImageButton) this.bt.get(2)).setClickable(false);
        if (((Integer) MainActivity.pos.get(2)).intValue() == 0) {
            ((ImageButton) this.bt.get(2)).setBackgroundResource(2131099715);
            if (cont == 0)
                this.primeiro = 0;
        }
        if (((Integer) MainActivity.pos.get(2)).intValue() == 1) {
            ((ImageButton) this.bt.get(2)).setBackgroundResource(2131099741);
            if (cont == 0)
                this.primeiro = 1;
        }
        if (((Integer) MainActivity.pos.get(2)).intValue() == 2) {
            ((ImageButton) this.bt.get(2)).setBackgroundResource(2131099746);
            if (cont == 0)
                this.primeiro = 2;
        }
        if (((Integer) MainActivity.pos.get(2)).intValue() == 3) {
            ((ImageButton) this.bt.get(2)).setBackgroundResource(2131099749);
            if (cont == 0)
                this.primeiro = 3;
        }
        if (((Integer) MainActivity.pos.get(2)).intValue() == 4) {
            ((ImageButton) this.bt.get(2)).setBackgroundResource(2131099751);
            if (cont == 0)
                this.primeiro = 4;
        }
        if (((Integer) MainActivity.pos.get(2)).intValue() == 5) {
            ((ImageButton) this.bt.get(2)).setBackgroundResource(2131099753);
            if (cont == 0)
                this.primeiro = 5;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.r);
        stringBuilder.append(MainActivity.pos.get(2));
        this.r = stringBuilder.toString();
        cont++;
        if (cont == 2) {
            this.i = 0;
            while (this.i < 12) {
                ((ImageButton) this.bt.get(this.i)).setClickable(false);
                this.i++;
            }
            comp(this.r);
            cont = 0;
            this.r = "";
        }
    }

    public void bt4(View paramView) {
        ((ImageButton) this.bt.get(3)).setClickable(false);
        if (((Integer) MainActivity.pos.get(3)).intValue() == 0) {
            ((ImageButton) this.bt.get(3)).setBackgroundResource(2131099715);
            if (cont == 0)
                this.primeiro = 0;
        }
        if (((Integer) MainActivity.pos.get(3)).intValue() == 1) {
            ((ImageButton) this.bt.get(3)).setBackgroundResource(2131099741);
            if (cont == 0)
                this.primeiro = 1;
        }
        if (((Integer) MainActivity.pos.get(3)).intValue() == 2) {
            ((ImageButton) this.bt.get(3)).setBackgroundResource(2131099746);
            if (cont == 0)
                this.primeiro = 2;
        }
        if (((Integer) MainActivity.pos.get(3)).intValue() == 3) {
            ((ImageButton) this.bt.get(3)).setBackgroundResource(2131099749);
            if (cont == 0)
                this.primeiro = 3;
        }
        if (((Integer) MainActivity.pos.get(3)).intValue() == 4) {
            ((ImageButton) this.bt.get(3)).setBackgroundResource(2131099751);
            if (cont == 0)
                this.primeiro = 4;
        }
        if (((Integer) MainActivity.pos.get(3)).intValue() == 5) {
            ((ImageButton) this.bt.get(3)).setBackgroundResource(2131099753);
            if (cont == 0)
                this.primeiro = 5;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.r);
        stringBuilder.append(MainActivity.pos.get(3));
        this.r = stringBuilder.toString();
        cont++;
        if (cont == 2) {
            this.i = 0;
            while (this.i < 12) {
                ((ImageButton) this.bt.get(this.i)).setClickable(false);
                this.i++;
            }
            comp(this.r);
            cont = 0;
            this.r = "";
        }
    }

    public void bt5(View paramView) {
        ((ImageButton) this.bt.get(4)).setClickable(false);
        if (((Integer) MainActivity.pos.get(4)).intValue() == 0) {
            ((ImageButton) this.bt.get(4)).setBackgroundResource(2131099715);
            if (cont == 0)
                this.primeiro = 0;
        }
        if (((Integer) MainActivity.pos.get(4)).intValue() == 1) {
            ((ImageButton) this.bt.get(4)).setBackgroundResource(2131099741);
            if (cont == 0)
                this.primeiro = 1;
        }
        if (((Integer) MainActivity.pos.get(4)).intValue() == 2) {
            ((ImageButton) this.bt.get(4)).setBackgroundResource(2131099746);
            if (cont == 0)
                this.primeiro = 2;
        }
        if (((Integer) MainActivity.pos.get(4)).intValue() == 3) {
            ((ImageButton) this.bt.get(4)).setBackgroundResource(2131099749);
            if (cont == 0)
                this.primeiro = 3;
        }
        if (((Integer) MainActivity.pos.get(4)).intValue() == 4) {
            ((ImageButton) this.bt.get(4)).setBackgroundResource(2131099751);
            if (cont == 0)
                this.primeiro = 4;
        }
        if (((Integer) MainActivity.pos.get(4)).intValue() == 5) {
            ((ImageButton) this.bt.get(4)).setBackgroundResource(2131099753);
            if (cont == 0)
                this.primeiro = 5;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.r);
        stringBuilder.append(MainActivity.pos.get(4));
        this.r = stringBuilder.toString();
        cont++;
        if (cont == 2) {
            this.i = 0;
            while (this.i < 12) {
                ((ImageButton) this.bt.get(this.i)).setClickable(false);
                this.i++;
            }
            comp(this.r);
            cont = 0;
            this.r = "";
        }
    }

    public void bt6(View paramView) {
        ((ImageButton) this.bt.get(5)).setClickable(false);
        if (((Integer) MainActivity.pos.get(5)).intValue() == 0) {
            ((ImageButton) this.bt.get(5)).setBackgroundResource(2131099715);
            if (cont == 0)
                this.primeiro = 0;
        }
        if (((Integer) MainActivity.pos.get(5)).intValue() == 1) {
            ((ImageButton) this.bt.get(5)).setBackgroundResource(2131099741);
            if (cont == 0)
                this.primeiro = 1;
        }
        if (((Integer) MainActivity.pos.get(5)).intValue() == 2) {
            ((ImageButton) this.bt.get(5)).setBackgroundResource(2131099746);
            if (cont == 0)
                this.primeiro = 2;
        }
        if (((Integer) MainActivity.pos.get(5)).intValue() == 3) {
            ((ImageButton) this.bt.get(5)).setBackgroundResource(2131099749);
            if (cont == 0)
                this.primeiro = 3;
        }
        if (((Integer) MainActivity.pos.get(5)).intValue() == 4) {
            ((ImageButton) this.bt.get(5)).setBackgroundResource(2131099751);
            if (cont == 0)
                this.primeiro = 4;
        }
        if (((Integer) MainActivity.pos.get(5)).intValue() == 5) {
            ((ImageButton) this.bt.get(5)).setBackgroundResource(2131099753);
            if (cont == 0)
                this.primeiro = 5;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.r);
        stringBuilder.append(MainActivity.pos.get(5));
        this.r = stringBuilder.toString();
        cont++;
        if (cont == 2) {
            this.i = 0;
            while (this.i < 12) {
                ((ImageButton) this.bt.get(this.i)).setClickable(false);
                this.i++;
            }
            comp(this.r);
            cont = 0;
            this.r = "";
        }
    }

    public void bt7(View paramView) {
        ((ImageButton) this.bt.get(6)).setClickable(false);
        if (((Integer) MainActivity.pos.get(6)).intValue() == 0) {
            ((ImageButton) this.bt.get(6)).setBackgroundResource(2131099715);
            if (cont == 0)
                this.primeiro = 0;
        }
        if (((Integer) MainActivity.pos.get(6)).intValue() == 1) {
            ((ImageButton) this.bt.get(6)).setBackgroundResource(2131099741);
            if (cont == 0)
                this.primeiro = 1;
        }
        if (((Integer) MainActivity.pos.get(6)).intValue() == 2) {
            ((ImageButton) this.bt.get(6)).setBackgroundResource(2131099746);
            if (cont == 0)
                this.primeiro = 2;
        }
        if (((Integer) MainActivity.pos.get(6)).intValue() == 3) {
            ((ImageButton) this.bt.get(6)).setBackgroundResource(2131099749);
            if (cont == 0)
                this.primeiro = 3;
        }
        if (((Integer) MainActivity.pos.get(6)).intValue() == 4) {
            ((ImageButton) this.bt.get(6)).setBackgroundResource(2131099751);
            if (cont == 0)
                this.primeiro = 4;
        }
        if (((Integer) MainActivity.pos.get(6)).intValue() == 5) {
            ((ImageButton) this.bt.get(6)).setBackgroundResource(2131099753);
            if (cont == 0)
                this.primeiro = 5;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.r);
        stringBuilder.append(MainActivity.pos.get(6));
        this.r = stringBuilder.toString();
        cont++;
        if (cont == 2) {
            this.i = 0;
            while (this.i < 12) {
                ((ImageButton) this.bt.get(this.i)).setClickable(false);
                this.i++;
            }
            comp(this.r);
            cont = 0;
            this.r = "";
        }
    }

    public void bt8(View paramView) {
        ((ImageButton) this.bt.get(7)).setClickable(false);
        if (((Integer) MainActivity.pos.get(7)).intValue() == 0) {
            ((ImageButton) this.bt.get(7)).setBackgroundResource(2131099715);
            if (cont == 0)
                this.primeiro = 0;
        }
        if (((Integer) MainActivity.pos.get(7)).intValue() == 1) {
            ((ImageButton) this.bt.get(7)).setBackgroundResource(2131099741);
            if (cont == 0)
                this.primeiro = 1;
        }
        if (((Integer) MainActivity.pos.get(7)).intValue() == 2) {
            ((ImageButton) this.bt.get(7)).setBackgroundResource(2131099746);
            if (cont == 0)
                this.primeiro = 2;
        }
        if (((Integer) MainActivity.pos.get(7)).intValue() == 3) {
            ((ImageButton) this.bt.get(7)).setBackgroundResource(2131099749);
            if (cont == 0)
                this.primeiro = 3;
        }
        if (((Integer) MainActivity.pos.get(7)).intValue() == 4) {
            ((ImageButton) this.bt.get(7)).setBackgroundResource(2131099751);
            if (cont == 0)
                this.primeiro = 4;
        }
        if (((Integer) MainActivity.pos.get(7)).intValue() == 5) {
            ((ImageButton) this.bt.get(7)).setBackgroundResource(2131099753);
            if (cont == 0)
                this.primeiro = 5;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.r);
        stringBuilder.append(MainActivity.pos.get(7));
        this.r = stringBuilder.toString();
        cont++;
        if (cont == 2) {
            this.i = 0;
            while (this.i < 12) {
                ((ImageButton) this.bt.get(this.i)).setClickable(false);
                this.i++;
            }
            comp(this.r);
            cont = 0;
            this.r = "";
        }
    }

    public void bt9(View paramView) {
        ((ImageButton) this.bt.get(8)).setClickable(false);
        if (((Integer) MainActivity.pos.get(8)).intValue() == 0) {
            ((ImageButton) this.bt.get(8)).setBackgroundResource(2131099715);
            if (cont == 0)
                this.primeiro = 0;
        }
        if (((Integer) MainActivity.pos.get(8)).intValue() == 1) {
            ((ImageButton) this.bt.get(8)).setBackgroundResource(2131099741);
            if (cont == 0)
                this.primeiro = 1;
        }
        if (((Integer) MainActivity.pos.get(8)).intValue() == 2) {
            ((ImageButton) this.bt.get(8)).setBackgroundResource(2131099746);
            if (cont == 0)
                this.primeiro = 2;
        }
        if (((Integer) MainActivity.pos.get(8)).intValue() == 3) {
            ((ImageButton) this.bt.get(8)).setBackgroundResource(2131099749);
            if (cont == 0)
                this.primeiro = 3;
        }
        if (((Integer) MainActivity.pos.get(8)).intValue() == 4) {
            ((ImageButton) this.bt.get(8)).setBackgroundResource(2131099751);
            if (cont == 0)
                this.primeiro = 4;
        }
        if (((Integer) MainActivity.pos.get(8)).intValue() == 5) {
            ((ImageButton) this.bt.get(8)).setBackgroundResource(2131099753);
            if (cont == 0)
                this.primeiro = 5;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.r);
        stringBuilder.append(MainActivity.pos.get(8));
        this.r = stringBuilder.toString();
        cont++;
        if (cont == 2) {
            this.i = 0;
            while (this.i < 12) {
                ((ImageButton) this.bt.get(this.i)).setClickable(false);
                this.i++;
            }
            comp(this.r);
            cont = 0;
            this.r = "";
        }
    }

    public void comp(final String r) {
        if (r.substring(0, 1).equals(r.substring(1, 2))) {
            pontos++;
            TextView textView = this.ptos;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Pontos: ");
            stringBuilder.append(pontos);
            textView.setText(stringBuilder.toString());
            this.er = 0;
            this.i = 0;
            while (this.i < 12) {
                ((ImageButton) this.bt.get(this.i)).setClickable(false);
                this.i++;
            }
            if (!MainActivity.tgpref)
                this.handler.postDelayed(new Runnable() {
                    public void run() {
                        MainActivity.certo.start();
                    }
                }, 1000L);
            this.handler.postDelayed(new Runnable() {
                public void run() {
                    boolean bool = r.equals("00");
                    boolean bool1 = false;
                    boolean bool2 = false;
                    boolean bool3 = false;
                    boolean bool4 = false;
                    boolean bool5 = false;
                    int i = 0;
                    if (bool) {
                        MainActivity2 mainActivity2 = MainActivity2.this;
                        while (true) {
                            mainActivity2.i = i;
                            if (MainActivity2.this.i < 12) {
                                if (((Integer) MainActivity.pos.get(MainActivity2.this.i)).intValue() == 0)
                                    ((ImageButton) MainActivity2.this.bt.get(MainActivity2.this.i)).setVisibility(4);
                                mainActivity2 = MainActivity2.this;
                                i = mainActivity2.i + 1;
                                continue;
                            }
                            break;
                        }
                    } else if (r.equals("11")) {
                        MainActivity2 mainActivity2 = MainActivity2.this;
                        i = bool1;
                        while (true) {
                            mainActivity2.i = i;
                            if (MainActivity2.this.i < 12) {
                                if (((Integer) MainActivity.pos.get(MainActivity2.this.i)).intValue() == 1)
                                    ((ImageButton) MainActivity2.this.bt.get(MainActivity2.this.i)).setVisibility(4);
                                mainActivity2 = MainActivity2.this;
                                i = mainActivity2.i + 1;
                                continue;
                            }
                            break;
                        }
                    } else if (r.equals("22")) {
                        MainActivity2 mainActivity2 = MainActivity2.this;
                        i = bool2;
                        while (true) {
                            mainActivity2.i = i;
                            if (MainActivity2.this.i < 12) {
                                if (((Integer) MainActivity.pos.get(MainActivity2.this.i)).intValue() == 2)
                                    ((ImageButton) MainActivity2.this.bt.get(MainActivity2.this.i)).setVisibility(4);
                                mainActivity2 = MainActivity2.this;
                                i = mainActivity2.i + 1;
                                continue;
                            }
                            break;
                        }
                    } else if (r.equals("33")) {
                        MainActivity2 mainActivity2 = MainActivity2.this;
                        i = bool3;
                        while (true) {
                            mainActivity2.i = i;
                            if (MainActivity2.this.i < 12) {
                                if (((Integer) MainActivity.pos.get(MainActivity2.this.i)).intValue() == 3)
                                    ((ImageButton) MainActivity2.this.bt.get(MainActivity2.this.i)).setVisibility(4);
                                mainActivity2 = MainActivity2.this;
                                i = mainActivity2.i + 1;
                                continue;
                            }
                            break;
                        }
                    } else if (r.equals("44")) {
                        MainActivity2 mainActivity2 = MainActivity2.this;
                        i = bool4;
                        while (true) {
                            mainActivity2.i = i;
                            if (MainActivity2.this.i < 12) {
                                if (((Integer) MainActivity.pos.get(MainActivity2.this.i)).intValue() == 4)
                                    ((ImageButton) MainActivity2.this.bt.get(MainActivity2.this.i)).setVisibility(4);
                                mainActivity2 = MainActivity2.this;
                                i = mainActivity2.i + 1;
                                continue;
                            }
                            break;
                        }
                    } else {
                        MainActivity2 mainActivity2 = MainActivity2.this;
                        i = bool5;
                        while (true) {
                            mainActivity2.i = i;
                            if (MainActivity2.this.i < 12) {
                                if (((Integer) MainActivity.pos.get(MainActivity2.this.i)).intValue() == 5)
                                    ((ImageButton) MainActivity2.this.bt.get(MainActivity2.this.i)).setVisibility(4);
                                mainActivity2 = MainActivity2.this;
                                i = mainActivity2.i + 1;
                                continue;
                            }
                            break;
                        }
                    }
                }
            }, 1000L);
            this.handler.postDelayed(new Runnable() {
                public void run() {
                    MainActivity2.this.i = 0;
                    while (MainActivity2.this.i < 12) {
                        ((ImageButton) MainActivity2.this.bt.get(MainActivity2.this.i)).setClickable(true);
                        MainActivity2 mainActivity2 = MainActivity2.this;
                        mainActivity2.i++;
                    }
                }
            }, 800L);
            if (pontos == 6) {
                this.dt2 = new DateTime();
                hora = this.x.print((ReadableInstant) this.dt2);
                time = Tempo.result(this.dt, this.dt2);
                resultado = (new BancoController(getBaseContext())).insereDado(MainActivity.name_c, data, time, this.dicas, this.erros, pontos);
                Exportacao.Salvadados();
                Toast.makeText(getApplicationContext(), resultado, 0).show();
                Toast.makeText(getApplicationContext(), Exportacao.result, 0).show();
                final Intent intent = new Intent((Context) this, Parabens.class);
                this.handler.postDelayed(new Runnable() {
                    public void run() {
                        MainActivity2.this.startActivity(intent);
                        MainActivity2.this.finish();
                    }
                }, 1000L);
                return;
            }
        } else {
            this.erros++;
            this.controle = ++this.er;
            this.handler.postDelayed(new Runnable() {
                public void run() {
                    MainActivity2.this.i = 0;
                    while (MainActivity2.this.i < 12) {
                        ((ImageButton) MainActivity2.this.bt.get(MainActivity2.this.i)).setBackgroundResource(2131099714);
                        if (MainActivity2.this.controle == 3) {
                            ((ImageButton) MainActivity2.this.bt.get(MainActivity2.this.i)).setClickable(false);
                            MainActivity2.this.controle = 0;
                        } else {
                            ((ImageButton) MainActivity2.this.bt.get(MainActivity2.this.i)).setClickable(true);
                        }
                        MainActivity2 mainActivity2 = MainActivity2.this;
                        mainActivity2.i++;
                    }
                }
            }, 1000L);
            if (this.er % 3 == 0 && this.er != 0) {
                this.handler.postDelayed(new Runnable() {
                    public void run() {
                        MainActivity2 mainActivity2 = MainActivity2.this;
                        mainActivity2.dicas++;
                        MainActivity2.this.i = 0;
                        while (MainActivity2.this.i < 12) {
                            if (((Integer) MainActivity.pos.get(MainActivity2.this.i)).intValue() == MainActivity2.this.primeiro) {
                                MainActivity2.this.pos[MainActivity2.this.j] = MainActivity2.this.i;
                                ((ImageButton) MainActivity2.this.bt.get(MainActivity2.this.i)).setClickable(false);
                                ((ImageButton) MainActivity2.this.bt.get(MainActivity2.this.i)).setBackgroundResource(2131099718);
                                mainActivity2 = MainActivity2.this;
                                mainActivity2.j++;
                            }
                            mainActivity2 = MainActivity2.this;
                            mainActivity2.i++;
                        }
                    }
                }, 1000L);
                this.handler.postDelayed(new Runnable() {
                    public void run() {
                        ((ImageButton) MainActivity2.this.bt.get(MainActivity2.this.pos[0])).setBackgroundResource(2131099714);
                        ((ImageButton) MainActivity2.this.bt.get(MainActivity2.this.pos[1])).setBackgroundResource(2131099714);
                        ((ImageButton) MainActivity2.this.bt.get(MainActivity2.this.pos[0])).setClickable(true);
                        ((ImageButton) MainActivity2.this.bt.get(MainActivity2.this.pos[1])).setClickable(true);
                        MainActivity2.this.j = 0;
                    }
                }, 2000L);
                this.er = 0;
            }
        }
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(2131296281);
        this.bt.add((ImageButton) findViewById(2131165205));
        this.bt.add((ImageButton) findViewById(2131165209));
        this.bt.add((ImageButton) findViewById(2131165210));
        this.bt.add((ImageButton) findViewById(2131165211));
        this.bt.add((ImageButton) findViewById(2131165212));
        this.bt.add((ImageButton) findViewById(2131165213));
        this.bt.add((ImageButton) findViewById(2131165214));
        this.bt.add((ImageButton) findViewById(2131165215));
        this.bt.add((ImageButton) findViewById(2131165216));
        this.bt.add((ImageButton) findViewById(2131165206));
        this.bt.add((ImageButton) findViewById(2131165207));
        this.bt.add((ImageButton) findViewById(2131165208));
        this.ptos = (TextView) findViewById(2131165323);
        TextView textView = this.ptos;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Pontos: ");
        stringBuilder.append(pontos);
        textView.setText(stringBuilder.toString());
        this.dt = new DateTime();
        this.y = DateTimeFormat.forPattern("dd/MM/yyyy");
        this.x = DateTimeFormat.forPattern("HH:mm:ss");
        data = this.y.print((ReadableInstant) this.dt);
        hora = this.x.print((ReadableInstant) this.dt);
    }

    public boolean onCreateOptionsMenu(Menu paramMenu) {
        getMenuInflater().inflate(2131361794, paramMenu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
        return (paramMenuItem.getItemId() == 2131165199) ? true : super.onOptionsItemSelected(paramMenuItem);
    }

    protected void onStop() {
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
    }
}
*/
