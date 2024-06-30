package savexport

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class MemoriaDBHelper(paramContext: Context) : SQLiteOpenHelper(paramContext, GameEntry.DATABASE_NAME, null, GameEntry.DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${GameEntry.TABLE_NAME}")
        onCreate(db)
    }

    companion object {
        private val SQL_CREATE_ENTRIES =
                "CREATE TABLE ${GameEntry.TABLE_NAME} " +
                        "(${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        "${GameEntry.START_TIME} DATETIME NOT NULL," +
                        "${GameEntry.GAME_TIME} TIME NOT NULL," +
                        "${GameEntry.HITS} INTEGER NOT NULL," +
                        "${GameEntry.TIPS} INTEGER NOT NULL," +
                        "${GameEntry.MISSES} INTEGER NOT NULL)"
    }

}

internal object GameEntry : BaseColumns {
    val TABLE_NAME = "DADOS_PARTIDAS"
    val DATABASE_VERSION = 1
    val DATABASE_NAME = "Memoria"
    val START_TIME = "Início"
    val GAME_TIME = "[Tempo de jogo]"
    val HITS = "Acertos"
    val TIPS = "Dicas"
    val MISSES = "Erros"
}
