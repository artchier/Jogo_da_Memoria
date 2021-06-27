package savexport;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class BancoController {
    private Banco banco;

    private SQLiteDatabase db;

    public BancoController(Context paramContext) {
        this.banco = new Banco(paramContext);
    }

    public String insereDado(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, int paramInt3) {
        this.db = this.banco.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Nome", paramString1);
        contentValues.put("Data", paramString2);
        contentValues.put("[Tempo de jogo]", paramString3);
        contentValues.put("Dicas", Integer.valueOf(paramInt1));
        contentValues.put("Erros", Integer.valueOf(paramInt2));
        contentValues.put("Acertos", Integer.valueOf(paramInt3));
        long l = this.db.insert("DADOS_JOGO", null, contentValues);
        this.db.close();
        return (l == -1L) ? "Erro ao inserir registro" : "Registro inserido com sucesso";
    }
}