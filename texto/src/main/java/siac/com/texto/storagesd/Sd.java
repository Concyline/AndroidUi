package siac.com.texto.storagesd;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Sd implements SdInterface {

    private File file;
    private FileReader fileReader;
    private BufferedReader bufferedReader;
    private FileWriter fileWriter;
    private Log log;
    private String traco = "+---------------------------------------------------------------------+\r\n";
    private String linha;

    public Sd(Log log, String folderName, String fileName) {
        try {
            this.log = log;
            String PATH = Environment.getExternalStorageDirectory() + File.separator + folderName + File.separator;
            File folder = new File(PATH);
            if (!folder.exists())
                folder.mkdirs();

            file = new File(Environment.getExternalStorageDirectory() + File.separator + folderName + File.separator + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            instanciarControles(file);
        } catch (Exception e) {
            e.printStackTrace();
            log.onLog("ERRO > " + e.getMessage());
        }
    }

    private void instanciarControles(File file) {
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
        } catch (Exception e) {
            e.printStackTrace();
            log.onLog("ERRO > " + e.getMessage());
        }
    }

    @Override
    public boolean erro(String value) {
        try {
            fileWriter = new FileWriter(file, true);
            fileWriter.write("Erro : " + value + "\r\n");
            fileWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
            log.onLog("ERRO > " + e.getMessage());
        }
        return true;
    }

    @Override
    public boolean linha(String value) {
        try {
            fileWriter = new FileWriter(file, true);
            fileWriter.write("linha : " + value + "\r\n");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.onLog("ERRO > " + e.getMessage());
        }
        return true;
    }

    @Override
    public boolean info(String value) {
        try {
            fileWriter = new FileWriter(file, true);
            fileWriter.write("Info : " + value + "\r\n");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.onLog("ERRO > " + e.getMessage());
        }
        return true;
    }

    @Override
    public boolean cabecalho(String value) {
        try {
            divisao();
            if (file.length() >= 30000) {
                delete();
            }
            fileWriter = new FileWriter(file, true);
            fileWriter.write("EAgro : " + value + "\r\n");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.onLog("ERRO > " + e.getMessage());
        }
        return true;
    }

    @Override
    public boolean divisao() {
        try {
            fileWriter = new FileWriter(file, true);
            fileWriter.write(traco);
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.onLog("ERRO > " + e.getMessage());
        }
        return true;
    }

    @Override
    public boolean delete() {
        try {
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
            log.onLog("ERRO > " + e.getMessage());
        }
        return true;
    }

    @Override
    public String getVersao() {
        try {
            linha = bufferedReader.readLine();
            android.util.Log.e("linha", "linha : " + linha);
        } catch (Exception e) {
            e.printStackTrace();
            log.onLog("ERRO > " + e.getMessage());
        }
        return linha;
    }

    @Override
    public String getAll() {
        try {
            instanciarControles(file);
            StringBuilder builder = new StringBuilder();
            while ((linha = bufferedReader.readLine()) != null) {
                builder.append(linha.concat("\n"));
            }
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            log.onLog("ERRO > " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<String> getAllList() {
        try {
            List<String> lista = new ArrayList<>();
            instanciarControles(file);

            while ((linha = bufferedReader.readLine()) != null) {
                lista.add("" + linha);
            }
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            log.onLog("ERRO > " + e.getMessage());
            return null;
        }
    }

    @Override
    public void insereLog(StackTraceElement[] erro) {
        try {
            for (int i = 0; i < erro.length; i++) {
                erro(erro[i].toString());
            }
            divisao();
        } catch (Exception e) {
            e.printStackTrace();
            log.onLog("ERRO > " + e.getMessage());
        }
    }

    @Override
    public void processaException(String classe, Exception e) {
        try {
            info(classe);
            erro(e.toString());
            insereLog(e.getStackTrace());
        } catch (Exception ee) {
            e.printStackTrace();
            log.onLog("ERRO > " + ee.getMessage());
        }
    }
}
