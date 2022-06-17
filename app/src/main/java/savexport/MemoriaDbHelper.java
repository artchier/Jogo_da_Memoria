package savexport;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class MemoriaDbHelper extends SQLiteOpenHelper {
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + GameEntry.TABELA + " (" +
                    GameEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    GameEntry.NOME + " VARCHAR(100) NOT NULL," +
                    GameEntry.DATA + " DATE NOT NULL," +
                    GameEntry.TJ + " TIME NOT NULL," +
                    GameEntry.ACERTOS + " INTEGER NOT NULL," +
                    GameEntry.DICAS + " INTEGER NOT NULL," +
                    GameEntry.ERROS + " INTEGER NOT NULL)";

    public MemoriaDbHelper(Context paramContext) {
        super(paramContext, GameEntry.DATABASE_NAME, null, GameEntry.DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int paramInt1, int paramInt2) {
        db.execSQL("DROP TABLE IF EXISTS " + GameEntry.TABELA);
        onCreate(db);
    }
}

final class GameEntry implements BaseColumns {
    public static final String TABELA = "DADOS_PARTIDAS";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Memoria.db";
    public static final String NOME = "Nome";
    public static final String DATA = "Data";
    public static final String TJ = "[Tempo de jogo]";
    public static final String ACERTOS = "Acertos";
    public static final String DICAS = "Dicas";
    public static final String ERROS = "Erros";
}