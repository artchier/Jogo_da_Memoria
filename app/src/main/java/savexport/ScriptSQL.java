package savexport;

public class ScriptSQL {
    public static String tabela() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE DADOS_JOGO ( ");
        stringBuilder.append("_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        stringBuilder.append("Nome VARCHAR(100) NOT NULL, ");
        stringBuilder.append("Data DATE NOT NULL, ");
        stringBuilder.append("[Tempo de jogo] TIME NOT NULL, ");
        stringBuilder.append("Dicas INTEGER NOT NULL, ");
        stringBuilder.append("Erros INTEGER NOT NULL, ");
        stringBuilder.append("Acertos INTEGER NOT NULL); ");
        return stringBuilder.toString();
    }
}