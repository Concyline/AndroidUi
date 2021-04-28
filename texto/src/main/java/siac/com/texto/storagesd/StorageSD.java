package siac.com.texto;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class StorageSD {

	private FileReader fileReader;
	private BufferedReader bufferedReader;
	private FileWriter fileWriter;
	private String linha;
	private File file;
	private static StorageSD manipulaTexto;
	private String traco = "+---------------------------------------------------------------------+\r\n";

	private static String folderName = "ManipulaTexto";
	private static String fileName = "Log.txt";
	private static Context context;

	public static void init(Context ctx, String folder){
		context = ctx;
		folderName = folder;
		testaPermisao();
	}

	public static void init(Context ctx, String folder, String file){
		context = ctx;
		folderName = folder;
		fileName = file;
		testaPermisao();
	}

	public static StorageSD getInstance() {
		if (manipulaTexto == null)
			manipulaTexto = new StorageSD();

		return manipulaTexto;
	}

	public static void testaPermisao() {

		if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
			Log.e("ERRO", "A aplicação precisa da permissão para manipular o SD Card no AndroidManifest.xml\n\n <uses-permission android:name=\"android.permission.READ_EXTERNAL_STORAGE\" />\n <uses-permission android:name=\"android.permission.WRITE_EXTERNAL_STORAGE\" />");
		}
	}

	public StorageSD() {
		try {

			String PATH = Environment.getExternalStorageDirectory() + File.separator + folderName + File.separator;
			File folder = new File(PATH);
			if (!folder.exists())
				folder.mkdirs();

			file = new File(Environment.getExternalStorageDirectory() + File.separator + folderName + File.separator + fileName);
			if (!file.exists())
				file.createNewFile();
			instanciarControles(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public StorageSD(String path) {
		try {
			file = new File(Environment.getExternalStorageDirectory() + "/OS/" + path);
			instanciarControles(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void instanciarControles(File file) {
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean erro(String value) {
		try {
			fileWriter = new FileWriter(file, true);
			fileWriter.write("Erro : " + value + "\r\n");
			fileWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean linha(String value) {
		try {
			fileWriter = new FileWriter(file, true);
			fileWriter.write("linha : " + value + "\r\n");
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean info(String value) {
		try {
			fileWriter = new FileWriter(file, true);
			fileWriter.write("Info : " + value + "\r\n");
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean cabecalho(String value) {
		try {
			divisao();
			if(file.length() >= 30000){
				delete();
			}
			fileWriter = new FileWriter(file, true);
			fileWriter.write("EAgro : " + value + "\r\n");
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean divisao() {
		try {
			fileWriter = new FileWriter(file, true);
			fileWriter.write(traco);
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean delete() {

		file.delete();
		manipulaTexto = new StorageSD();
		return true;
	}

	public String getVersao() {

		try {
			linha = bufferedReader.readLine();
			Log.e("linha", "linha : " + linha);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return linha;
	}

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
			return null;
		}
	}

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
			return null;
		}
	}
	
	public void insereLog(StackTraceElement[] erro){
		for (int i = 0; i < erro.length; i++) {
			erro(erro[i].toString());
		}
		divisao();
	}

	public void processaException(String classe, Exception e) {
		info(classe);
		erro(e.toString());
		insereLog(e.getStackTrace());
	}

}
