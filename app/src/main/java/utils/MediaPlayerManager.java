package utils;

import android.content.Context;
import android.media.MediaPlayer;

import com.adaca.memoria.R;

public class MediaPlayerManager {
    public final MediaPlayer hitFX, endFX, music;

    private float fxVolume, musicVolume;

    private static MediaPlayerManager instance = null;

    public MediaPlayerManager(Context context) {
        endFX = MediaPlayer.create(context, R.raw.fim);
        hitFX = MediaPlayer.create(context, R.raw.tumm_c5);
        music = MediaPlayer.create(context, R.raw.plancton);

        music.seekTo(0);
        music.setLooping(true);
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
            endFX.setVolume(fxVolume, fxVolume);
            music.setVolume(musicVolume, musicVolume);
            hitFX.setVolume(fxVolume, fxVolume);
        } else {
            endFX.setVolume(0, 0);
            music.setVolume(0, 0);
            hitFX.setVolume(0, 0);
        }
    }

    public float getFxVolume() {
        return this.fxVolume;
    }

    public void setFxVolume(float fxVolume) {
        this.fxVolume = fxVolume;
    }

    public float getMusicVolume() {
        return this.musicVolume;
    }

    public void setMusicVolume(float musicVolume) {
        this.musicVolume = musicVolume;
    }
}
