package siac.com.texto.storagesd;

import android.content.Context;

import java.util.List;

public class StorageSD {

    private static TextoFacade textoFacade;

    public static StorageSDBuilder init(Context context) {
        return new StorageSDBuilder(context);
    }

    public static void build(StorageSDBuilder storageSDBuilder) {
        textoFacade = new DefaultTextoFacade(storageSDBuilder);
    }

    public static boolean erro(String value) {
        return textoFacade.erro(value);
    }

    public static boolean linha(String value) {
        return textoFacade.linha(value);
    }

    public static boolean info(String value) {
        return textoFacade.info(value);
    }

    public static boolean cabecalho(String value) {
        return textoFacade.cabecalho(value);
    }

    public static boolean divisao() {
        return textoFacade.divisao();
    }

    public static boolean delete() {
        return textoFacade.delete();
    }

    public static String getVersao() {
        return textoFacade.getVersao();
    }

    public static String getAll() {
        return textoFacade.getAll();
    }

    public static List<String> getAllList() {
        return textoFacade.getAllList();
    }

    public static void insereLog(StackTraceElement[] erro) {
        textoFacade.insereLog(erro);
    }

    public static void processaException(String classe, Exception e) {
        textoFacade.processaException(classe, e);
    }

}
