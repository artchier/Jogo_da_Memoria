package com.adaca.memoria

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.View.*
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.WorkManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import model.Game
import org.joda.time.DateTime
import savexport.DatabaseController
import utils.MediaPlayerManager
import utils.TimeManager
import worker.WorkerUtils

class GameActivity : AppCompatActivity() {
    private val figures = arrayListOf(
            R.drawable.circle,
            R.drawable.circle,
            R.drawable.hexagon,
            R.drawable.hexagon,
            R.drawable.losango,
            R.drawable.losango,
            R.drawable.pentagon,
            R.drawable.pentagon,
            R.drawable.triangulo,
            R.drawable.triangulo,
            R.drawable.square,
            R.drawable.square
    )

    private val timeManager = TimeManager
    private val mediaPlayerManager = MediaPlayerManager.getInstance(applicationContext)
    private val mainView = window.decorView
    private lateinit var points: TextView
    private var firstCard: ImageButton? = null

    private var hits: Int = 0
    private var tips: Int = 0
    private var misses: Int = 0
    private var missesInARow: Int = 0
    private var hit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        timeManager.startTime = DateTime()

        figures.shuffle()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) window.attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES

        points = findViewById(R.id.tvNumPontos)
        points.text = ""
    }

    fun cardClickListener(imageButton: ImageButton) {
        if (firstCard == null || imageButton.id != firstCard?.id) {
            val label = imageButton.resources.getResourceEntryName(imageButton.id)
            val indexBackground = label.split("d")[1].toInt()
            setButtonsClickable(false)

            val figureBackground = figures[indexBackground]
            imageButton.setBackgroundResource(figureBackground)
            imageButton.tag = figureBackground

            if (firstCard != null) {
                hit = (firstCard?.tag as Int) == figureBackground
            } else {
                firstCard = imageButton
            }

            CoroutineScope(Dispatchers.Main).launch {
                delay(500).run {
                    imageButton.setBackgroundResource(R.drawable.cartas)
                    if (firstCard != imageButton) {
                        if (!hit) {
                            misses++
                            missesInARow++
                        } else {
                            mediaPlayerManager.hitFX.start()
                            points.text = (++hits).toString()
                            missesInARow = 0
                            firstCard?.visibility = View.INVISIBLE
                            imageButton.visibility = View.INVISIBLE
                            hit = false

                            if (hits == 6) {
                                startActivity(Intent(this@GameActivity, CongratulationsActivity::class.java))
                                finish()
                            }

                            if (missesInARow % 3 == 0 && missesInARow > 0) {
                                showTip(firstCard as ImageButton)
                                tips++
                            }
                            firstCard = null
                        }
                        setButtonsClickable(true)
                    }
                }
            }
        }
    }

    override fun onWindowFocusChanged(paramBoolean: Boolean) {
        super.onWindowFocusChanged(paramBoolean)

        if (paramBoolean) mainView.systemUiVisibility = (SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or SYSTEM_UI_FLAG_LAYOUT_STABLE
                or SYSTEM_UI_FLAG_FULLSCREEN
                or SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)
    }

    override fun onStop() {
        super.onStop()
        saveData()
    }

    private fun saveData() {
        timeManager.endTime = DateTime()

        val game = Game().apply {
            deviceUID = Settings.Secure.getString(contentResolver, "bluetooth_name")
            inicio = timeManager.date
            duracao = timeManager.gameTime()
            acertos = hits
            dicas = tips
            erros = misses

        }

        DatabaseController(baseContext).insertData(game).let {
            Toast.makeText(applicationContext, it, Toast.LENGTH_LONG)
        }

        WorkManager.getInstance(this).enqueue(WorkerUtils.addDataToRequest(game.toString()))
    }

    private fun showTip(firstCard: ImageButton) {
        val possibilities = arrayListOf<Int>()

        for (index in 0 until 12) {
            if (firstCard.tag.equals(figures[index])) {
                possibilities.add(index)
            }
            if (possibilities.size == 2) break
        }

        val firstCardLabel = firstCard.resources.getResourceEntryName(firstCard.id)
        val indexBackground = firstCardLabel.split("d")[1].toInt()

        if (possibilities[0] == indexBackground) {
            possibilities.remove(0)
        } else {
            possibilities.remove(1)
        }

        val secondCard: ImageButton = findViewById(resources.getIdentifier(
                "card${possibilities[0]}", "id", packageName
        ))

        firstCard.setBackgroundResource(R.drawable.dica_animation)
        secondCard.setBackgroundResource(R.drawable.dica_animation)
        (firstCard.background as AnimationDrawable).start()
        (secondCard.background as AnimationDrawable).start()
    }

    private fun setButtonsClickable(clickable: Boolean) {
        for (index in 0 until 12) {
            findViewById<ImageButton>(resources.getIdentifier(
                    "card$index",
                    "id",
                    packageName
            )).isClickable = clickable
        }
    }
}