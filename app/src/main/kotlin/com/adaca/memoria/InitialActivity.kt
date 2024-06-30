package com.adaca.memoria

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import utils.MediaPlayerManager

class InitialActivity : AppCompatActivity() {
    private val mediaPlayerManager: MediaPlayerManager = MediaPlayerManager.getInstance(this)
    private var toggleButtonIsChecked: Boolean = false
    private val mainView: View = window.decorView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)

        readPreferences()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) window.attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES

        val optionsDialog = View.inflate(this, R.layout.options, null)
        val muteButton: ToggleButton = optionsDialog.findViewById(R.id.toggle_sound)

        checkToggleButton(muteButton, toggleButtonIsChecked)
        val fxSb: SeekBar = optionsDialog.findViewById(R.id.fx_seekBar)
        val musicSb: SeekBar = optionsDialog.findViewById(R.id.music_seekBar)
        val seekBarListener = object : SeekBar.OnSeekBarChangeListener {
            var newVolume: Float = 0f

            override fun onProgressChanged(seekBar: SeekBar, volume: Int, fromUser: Boolean) {
                newVolume = (volume / 10.0).toFloat()
                seekBar.progress = volume

                if (!toggleButtonIsChecked) {
                    if (seekBar.id == R.id.fx_seekBar) mediaPlayerManager.endFX.setVolume(newVolume, newVolume)
                    else mediaPlayerManager.music.setVolume(newVolume, newVolume)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                if (!toggleButtonIsChecked) {
                    if (seekBar.id == R.id.fx_seekBar) {
                        with(mediaPlayerManager) {
                            music.pause()
                            endFX.seekTo(0)
                            endFX.start()
                            endFX.isLooping = true
                        }
                    }
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                if (!toggleButtonIsChecked) {
                    if (seekBar.id == R.id.fx_seekBar) {
                        with(mediaPlayerManager) {
                            fxVolume = newVolume
                            hitFX.setVolume(newVolume, newVolume)

                            endFX.pause()
                            endFX.seekTo(0)
                            endFX.isLooping = false
                            music.start()

                        }

                        Toast.makeText(
                                applicationContext,
                                "${getString(R.string.fx_toast)} ${(newVolume * 10).toInt()}",
                                Toast.LENGTH_SHORT).show()
                    } else {
                        mediaPlayerManager.musicVolume = newVolume

                        Toast.makeText(applicationContext,
                                "${getString(R.string.music_toast)} ${(newVolume * 10).toInt()}",
                                Toast.LENGTH_SHORT).show()
                    }

                    mediaPlayerManager.let {
                        muteButton.isClickable = (it.fxVolume != 0f).or(it.musicVolume != 0f)
                    }
                }
            }
        }

        try {
            fxSb.apply {
                max = 10
                keyProgressIncrement = 1
                progress = (mediaPlayerManager.fxVolume * 10).toInt()
                setOnSeekBarChangeListener(seekBarListener)
            }

            musicSb.apply {
                max = 10
                keyProgressIncrement = 1
                progress = (mediaPlayerManager.musicVolume * 10).toInt()
                setOnSeekBarChangeListener(seekBarListener)
            }
        } catch (_: Exception) {
        }

        findViewById<ImageButton>(R.id.options).setOnClickListener {
            AlertDialog.Builder(this).apply {
                if (optionsDialog.parent != null)
                    (optionsDialog.parent as ViewGroup).removeView(optionsDialog)

                setView(optionsDialog)
                setCancelable(true)
                muteButton.setOnCheckedChangeListener { button, isMute ->
                    if ((mediaPlayerManager.fxVolume != 0f).or(mediaPlayerManager.musicVolume != 0f)) {
                        checkToggleButton(button, isMute)
                        mediaPlayerManager.setMediaPlayersVolume(toggleButtonIsChecked)
                    } else {
                        button.isClickable = false
                    }
                }
                create()
                show()
            }
        }

        findViewById<ImageButton>(R.id.play).setOnClickListener {
            val playDialog = LayoutInflater.from(this).inflate(R.layout.input_dialog, null)

            AlertDialog.Builder(this).apply {
                setView(playDialog)
                setCancelable(false).setPositiveButton("OK") { _, _ ->
                    val nome = findViewById<EditText>(R.id.etNome).text

                    if (nome.equals("")) Toast.makeText(applicationContext, "faltou digitar o nome!!", Toast.LENGTH_SHORT).show()
                    else {
                        startActivity(Intent(this@InitialActivity, GameActivity::class.java))
                    }
                }
                        .setNegativeButton("Cancelar") { dialog, _ ->
                            dialog.cancel()
                        }
                        .create()
                        .show()
            }
        }

        findViewById<ImageButton>(R.id.quit).setOnClickListener { finish() }
    }

    override fun onDestroy() {
        super.onDestroy()

        with(mediaPlayerManager) {
            music.release()
            hitFX.release()
            endFX.release()
        }

        MediaPlayerManager.resetInstance()
    }

    override fun onPause() {
        super.onPause()

        savePreferences()

        mediaPlayerManager.music.pause()
    }

    override fun onResume() {
        super.onResume()

        with(mediaPlayerManager) {
            setMediaPlayersVolume(toggleButtonIsChecked)
            music.start()
        }
    }

    override fun onWindowFocusChanged(paramBoolean: Boolean) {
        super.onWindowFocusChanged(paramBoolean)
        if (paramBoolean) mainView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
    }

    private fun readPreferences() {
        getSharedPreferences("preferences", Context.MODE_PRIVATE).let {
            mediaPlayerManager.fxVolume = it.getFloat("efeitos", 1.0f)
            mediaPlayerManager.musicVolume = it.getFloat("musica", 1.0f)
            toggleButtonIsChecked = it.getBoolean("mudo", false)
        }
    }

    private fun savePreferences() {
        getSharedPreferences("preferences", Context.MODE_PRIVATE).let {
            it.edit().apply {
                putFloat("efeitos", mediaPlayerManager.fxVolume)
                putFloat("musica", mediaPlayerManager.musicVolume)
                putBoolean("mudo", toggleButtonIsChecked)

                apply()
            }
        }
    }

    private fun checkToggleButton(button: CompoundButton, isMute: Boolean) {
        if (!isMute) {
            toggleButtonIsChecked = false
            button.setBackgroundResource(R.drawable.sound_off)
        } else {
            toggleButtonIsChecked = true
            button.setBackgroundResource(R.drawable.sound_in)
        }
        button.isChecked = toggleButtonIsChecked
    }
}