package savexport;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import domain.Game;

public class BancoController {
    private final MemoriaDbHelper memoriaDbHelper;

    public BancoController(Context paramContext) {
        this.memoriaDbHelper = new MemoriaDbHelper(paramContext);
    }

//    public String insereDado(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, int paramInt3) {
//        this.db = this.banco.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("Nome", paramString1);
//        contentValues.put("Data", paramString2);
//        contentValues.put("[Tempo de jogo]", paramString3);
//        contentValues.put("Dicas", Integer.valueOf(paramInt1));
//        contentValues.put("Erros", Integer.valueOf(paramInt2));
//        contentValues.put("Acertos", Integer.valueOf(paramInt3));
//        long l = this.db.insert("DADOS_JOGO", null, contentValues);
//        this.db.close();
//        return (l == -1L) ? "Erro ao inserir registro" : "Registro inserido com sucesso";
//    }

    public String insereDado(Game game) {
        SQLiteDatabase db = this.memoriaDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GameEntry.NOME, game.getNome());
        contentValues.put(GameEntry.DATA, game.getData());
        contentValues.put(GameEntry.TJ, game.getHora());
        contentValues.put(GameEntry.ACERTOS, game.getPontos());
        contentValues.put(GameEntry.DICAS, game.getDicas());
        contentValues.put(GameEntry.ERROS, game.getErros());
        long l = db.insert(GameEntry.TABELA, null, contentValues);
        db.close();
        return (l == -1L) ? "Erro ao inserir registro" : "Registro inserido com sucesso";
    }
}