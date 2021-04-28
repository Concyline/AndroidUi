package siac.com.texto.storagesd;

import java.util.List;

public interface TextoFacade {

    boolean erro(String value);

    boolean linha(String value);

    boolean info(String value);

    boolean cabecalho(String value);

    boolean divisao();

    boolean delete();

    String getVersao();

    String getAll();

    List<String> getAllList();

    void insereLog(StackTraceElement[] erro);

    void processaException(String classe, Exception e);
}
