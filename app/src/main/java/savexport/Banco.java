package savexport;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {
    public static final String ACERTOS = "Acertos";

    public static final String DATA = "Data";

    public static final String DICAS = "Dicas";

    public static final String ERROS = "Erros";

    public static final String NOME = "Nome";

    public static final String NOME_BANCO = "DADOS.db";

    public static final String TABELA = "DADOS_JOGO";

    public static final String TJ = "[Tempo de jogo]";

    public static final int VERSAO = 1;

    public Banco(Context paramContext) {
        super(paramContext, "DADOS.db", null, 1);
    }

    public void onCreate(SQLiteDatabase paramSQLiteDatabase) {
        paramSQLiteDatabase.execSQL(ScriptSQL.tabela());
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {
        paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTSDADOS_JOGO");
        onCreate(paramSQLiteDatabase);
    }
}