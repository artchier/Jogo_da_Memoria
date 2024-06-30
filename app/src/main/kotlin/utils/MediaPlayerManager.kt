package utils

import android.content.Context
import android.media.MediaPlayer
import com.adaca.memoria.R

class MediaPlayerManager private constructor(context: Context) {
    val endFX: MediaPlayer = MediaPlayer.create(context, R.raw.fim)
    val hitFX: MediaPlayer = MediaPlayer.create(context, R.raw.tumm_c5)
    val music: MediaPlayer = MediaPlayer.create(context, R.raw.plancton).apply {
        seekTo(0)
        isLooping = true
    }
    var fxVolume = 0f
    var musicVolume = 0f

    fun setMediaPlayersVolume(toggleButtonIsChecked: Boolean) {
        if (!toggleButtonIsChecked) {
            endFX.setVolume(fxVolume, fxVolume)
            music.setVolume(musicVolume, musicVolume)
            hitFX.setVolume(fxVolume, fxVolume)
        } else {
            endFX.setVolume(0f, 0f)
            music.setVolume(0f, 0f)
            hitFX.setVolume(0f, 0f)
        }
    }

    companion object {
        @Volatile
        private var instance: MediaPlayerManager? = null

        fun getInstance(context: Context): MediaPlayerManager {
            return instance ?: synchronized(this) {
                instance ?: MediaPlayerManager(context).also { instance = it }
            }
        }

        fun resetInstance() {
            instance = null
        }
    }
}