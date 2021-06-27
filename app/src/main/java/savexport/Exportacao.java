package savexport;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class Exportacao {
    public static String result = "";

    public static void Salvadados() {
        File file1 = new File(Environment.getExternalStorageDirectory().getPath(), "Memória");
        file1.mkdirs();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Environment.getDataDirectory().getPath());
        stringBuilder.append("/data/com.example.arthur.memoria30/databases");
        File file2 = new File(stringBuilder.toString(), "DADOS.db");
        file1 = new File(file1, "DADOS.db");
        try {
            FileChannel fileChannel2 = (new FileInputStream(file2)).getChannel();
            FileChannel fileChannel1 = (new FileOutputStream(file1)).getChannel();
            fileChannel2.transferTo(0L, fileChannel2.size(), fileChannel1);
            fileChannel2.close();
            fileChannel1.close();
            result = "Exportado com sucesso!";
            return;
        } catch (IOException iOException) {
            result = "Erro!";
            return;
        }
    }
}