package utils;

import android.app.Application;
import android.content.Context;
import android.media.MediaPlayer;

import com.adaca.memoria.R;

public class MediaPlayerManager {
    public final MediaPlayer certo, fim, musica;

    private float efeitosVolume, musicaVolume;

    private static MediaPlayerManager instance = null;

    public MediaPlayerManager(Context context) {
        fim = MediaPlayer.create(context, R.raw.fim);
        certo = MediaPlayer.create(context, R.raw.tumm_c5);
        musica = MediaPlayer.create(context, R.raw.plancton);

        musica.seekTo(0);
        musica.setLooping(true);
    }

    public static MediaPlayerManager getInstance(Context context) {
        if (instance == null) {
            instance = new MediaPlayerManager(context);
        }

        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }

    public void setMediaPlayersVolume(boolean toggleButtonIsChecked) {
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

    public float getEfeitosVolume() {
        return this.efeitosVolume;
    }

    public void setEfeitosVolume(float efeitosVolume) {
        this.efeitosVolume = efeitosVolume;
    }

    public float getMusicaVolume() {
        return this.musicaVolume;
    }

    public void setMusicaVolume(float musicaVolume) {
        this.musicaVolume = musicaVolume;
    }
}
