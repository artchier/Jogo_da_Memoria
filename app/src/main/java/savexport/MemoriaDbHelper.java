package savexport;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class MemoriaDbHelper extends SQLiteOpenHelper {
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + GameEntry.TABLE_NAME + " (" +
                    GameEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    GameEntry.START_TIME + " DATETIME NOT NULL," +
                    GameEntry.GAME_TIME + " TIME NOT NULL," +
                    GameEntry.HITS + " INTEGER NOT NULL," +
                    GameEntry.TIPS + " INTEGER NOT NULL," +
                    GameEntry.MISSES + " INTEGER NOT NULL)";

    public MemoriaDbHelper(Context paramContext) {
        super(paramContext, GameEntry.DATABASE_NAME, null, GameEntry.DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int paramInt1, int paramInt2) {
        db.execSQL("DROP TABLE IF EXISTS " + GameEntry.TABLE_NAME);
        onCreate(db);
    }
}

final class GameEntry implements BaseColumns {
    public static final String TABLE_NAME = "DADOS_PARTIDAS";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Memoria";
    public static final String START_TIME = "Início";
    public static final String GAME_TIME = "[Tempo de jogo]";
    public static final String HITS = "Acertos";
    public static final String TIPS = "Dicas";
    public static final String MISSES = "Erros";
}