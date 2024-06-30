package savexport;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import model.Game;

public class DatabaseController {
    private final MemoriaDbHelper memoriaDbHelper;

    public DatabaseController(Context paramContext) {
        this.memoriaDbHelper = new MemoriaDbHelper(paramContext);
    }

    public String insertData(Game game) {
        SQLiteDatabase db = this.memoriaDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GameEntry.START_TIME, game.getInicio());
        contentValues.put(GameEntry.GAME_TIME, game.getDuracao());
        contentValues.put(GameEntry.HITS, game.getAcertos());
        contentValues.put(GameEntry.TIPS, game.getDicas());
        contentValues.put(GameEntry.MISSES, game.getErros());
        long l = db.insert(GameEntry.TABLE_NAME, null, contentValues);
        db.close();
        return (l == -1L) ? "Erro ao inserir registro" : "Registro inserido com sucesso";
    }
}