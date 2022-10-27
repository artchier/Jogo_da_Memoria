package model;

public class Game {
    String deviceUID, inicio, duracao;
    int acertos, dicas, erros;

    public Game() {

    }

    public Game(String inicio, String duracao, int acertos, int dicas, int erros) {
        this.inicio = inicio;
        this.duracao = duracao;
        this.acertos = acertos;
        this.dicas = dicas;
        this.erros = erros;
    }

    public Game(String deviceUID, String inicio, String duracao, int acertos, int dicas, int erros) {
        this.deviceUID = deviceUID;
        this.inicio = inicio;
        this.duracao = duracao;
        this.acertos = acertos;
        this.dicas = dicas;
        this.erros = erros;
    }

    public String getDeviceUID() {
        return deviceUID;
    }

    public String getDuracao() {
        return duracao;
    }

    public String getInicio() {
        return inicio;
    }

    public int getAcertos() {
        return acertos;
    }

    public int getDicas() {
        return dicas;
    }

    public int getErros() {
        return erros;
    }

    public void setDeviceUID(String deviceUID) {
        this.deviceUID = deviceUID;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public void setAcertos(int acertos) {
        this.acertos = acertos;
    }

    public void setDicas(int dicas) {
        this.dicas = dicas;
    }

    public void setErros(int erros) {
        this.erros = erros;
    }

    @Override
    public String toString() {
        return "deviceUID='" + deviceUID + '\'' +
                ", inicio='" + inicio + '\'' +
                ", duracao='" + duracao + '\'' +
                ", acertos=" + acertos +
                ", dicas=" + dicas +
                ", erros=" + erros;
    }

    public static Game parseGame(String data) {
        Game game = new Game();

        String[] gameData = data.split(",");

        game.setDeviceUID(gameData[0].split("=")[1].replace("'", ""));
        game.setInicio(gameData[1].split("=")[1].replace("'", ""));
        game.setDuracao(gameData[2].split("=")[1].replace("'", ""));
        game.setAcertos(Integer.parseInt(gameData[3].split("=")[1]));
        game.setDicas(Integer.parseInt(gameData[4].split("=")[1]));
        game.setErros(Integer.parseInt(gameData[5].split("=")[1]));

        return game;
    }

//    public Game(String nome, String data, String hora, int acertos, int dicas, int erros) {
//        this.nome = nome;
//        this.data = data;
//        this.hora = hora;
//        this.acertos = acertos;
//        this.dicas = dicas;
//        this.erros = erros;
//    }

//    public static Game getInstance() {
//        if (instance == null) {
//            instance = new Game();
//        }
//
//        return instance;
//    }
}
