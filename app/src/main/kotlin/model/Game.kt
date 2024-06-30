package model

data class Game(
        var inicio: String = "",
        var duracao: String = "",
        var acertos: Int = 0,
        var dicas: Int = 0,
        var erros: Int = 0
) {
    var deviceUID: String = ""
    override fun toString(): String {
        return "deviceUID='$deviceUID', " +
                "startTime='$inicio', " +
                "gameTime='$duracao', " +
                "hits=$acertos, " +
                "tips=$dicas, " +
                "misses=$erros"
    }

    companion object {
        fun parseGame(data: String): Game {
            val gameData = data.split(",")

            return Game().apply {
                deviceUID = gameData[0].split("=")[1].replace("'", "")
                inicio = gameData[1].split("=")[1].replace("'", "")
                duracao = gameData[2].split("=")[1].replace("'", "")
                acertos = gameData[0].split("=")[1].replace("'", "").toInt()
                dicas = gameData[0].split("=")[1].replace("'", "").toInt()
                erros = gameData[0].split("=")[1].replace("'", "").toInt()
            }
        }
    }
}
