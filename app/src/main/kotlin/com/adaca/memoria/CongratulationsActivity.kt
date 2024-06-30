package com.adaca.memoria

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.view.WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import utils.MediaPlayerManager

class CongratulationsActivity : AppCompatActivity() {
    private lateinit var mediaPlayerManager: MediaPlayerManager
    private lateinit var mainView: View
    private val fadeIn: Animation = AlphaAnimation(0.0F, 1.0F)
    private lateinit var fireworks: ImageView
    private lateinit var congratulations: ImageView
    private lateinit var stars: ImageView
    private lateinit var newGame: ImageButton
    private lateinit var quit: ImageButton
    private lateinit var clAnimations: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_congratulations)

        mediaPlayerManager = MediaPlayerManager.getInstance(applicationContext)

        congratulations = findViewById(R.id.ivParabens)
        stars = findViewById(R.id.ivEstrelas)
        fireworks = findViewById(R.id.ivFogos)
        newGame = findViewById(R.id.ibDeNovo)
        quit = findViewById(R.id.ibVoltar)
        clAnimations = findViewById(R.id.clAnimations)
        mainView = window.decorView

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P)
            window.attributes.layoutInDisplayCutoutMode = LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES

        newGame.setOnClickListener {
            mediaPlayerManager.music.pause()
            startActivity(Intent(this, GameActivity::class.java))
            finish()
        }

        quit.setOnClickListener { finish() }
    }

    override fun onResume() {
        super.onResume()

        with(fadeIn) {
            duration = 1000L
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                    mediaPlayerManager.music.seekTo(0)
                    mediaPlayerManager.music.start()
                }

                override fun onAnimationEnd(animation: Animation?) {
                    fireworks.setBackgroundResource(R.drawable.fogos_animation)
                    stars.setBackgroundResource(R.drawable.estrela_animation)

                    (fireworks.background as AnimationDrawable).start()
                    (stars.background as AnimationDrawable).start()
                    mediaPlayerManager.endFX.start()
                }

                override fun onAnimationRepeat(animation: Animation?) {
                }

            })
            congratulations.startAnimation(this)
            clAnimations.startAnimation(this)
            newGame.startAnimation(this)
            quit.startAnimation(this)
        }
    }

    override fun onWindowFocusChanged(paramBoolean: Boolean) {
        super.onWindowFocusChanged(paramBoolean)

        if (paramBoolean)
            mainView.systemUiVisibility =
                    SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    SYSTEM_UI_FLAG_FULLSCREEN or
                    SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    }
}