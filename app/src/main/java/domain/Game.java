package domain;

public class Game {
    String nome, data, hora;
    int pontos, dicas, erros;

    private static Game instance = null;

    public Game() {
    }

    public String getNome() {
        return nome;
    }

    public String getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public int getPontos() {
        return pontos;
    }

    public int getDicas() {
        return dicas;
    }

    public int getErros() {
        return erros;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public void setDicas(int dicas) {
        this.dicas = dicas;
    }

    public void setErros(int erros) {
        this.erros = erros;
    }
//    public Game(String nome, String data, String hora, int pontos, int dicas, int erros) {
//        this.nome = nome;
//        this.data = data;
//        this.hora = hora;
//        this.pontos = pontos;
//        this.dicas = dicas;
//        this.erros = erros;
//    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }

        return instance;
    }
}
