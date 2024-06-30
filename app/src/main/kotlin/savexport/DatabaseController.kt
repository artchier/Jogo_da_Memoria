package savexport

import android.content.ContentValues
import android.content.Context
import model.Game

class DatabaseController(paramContext: Context?) {
    private val memoriaDbHelper: MemoriaDbHelper
    fun insertData(game: Game): String {
        val db = memoriaDbHelper.writableDatabase
        val contentValues = ContentValues().apply {
            put(GameEntry.START_TIME, game.inicio)
            put(GameEntry.GAME_TIME, game.duracao)
            put(GameEntry.HITS, game.acertos)
            put(GameEntry.TIPS, game.dicas)
            put(GameEntry.MISSES, game.erros)
        }
        val result = db.insert(GameEntry.TABLE_NAME, null, contentValues)
        db.close()
        return if (result == -1L) "Erro ao inserir registro" else "Registro inserido com sucesso"
    }

    init {
        memoriaDbHelper = MemoriaDbHelper(paramContext)
    }
}