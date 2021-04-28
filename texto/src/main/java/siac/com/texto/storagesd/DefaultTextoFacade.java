package siac.com.texto.storagesd;

import java.util.List;

public class DefaultTextoFacade implements TextoFacade {

    private Log log;
    private SdInterface sd;

    public DefaultTextoFacade(StorageSDBuilder storageSDBuilder) {
        this.log = storageSDBuilder.getLog();
        this.sd = storageSDBuilder.getSdInterface();
    }

    @Override
    public boolean erro(String value) {
        log.onLog("erro > "+value);
        return sd.erro(value);
    }

    @Override
    public boolean linha(String value) {
        log.onLog("linha > "+value);
        return sd.linha(value);
    }

    @Override
    public boolean info(String value) {
        log.onLog("info > "+value);
        return sd.info(value);
    }

    @Override
    public boolean cabecalho(String value) {
        log.onLog("cabecalho > "+value);
        return sd.cabecalho(value);
    }

    @Override
    public boolean divisao() {
        log.onLog("divisao");
        return sd.divisao();
    }

    @Override
    public boolean delete() {
        log.onLog("delete");
        return sd.delete();
    }

    @Override
    public String getVersao() {
        log.onLog("getVersao");
        return sd.getVersao();
    }

    @Override
    public String getAll() {
        log.onLog("getAll");
        return sd.getAll();
    }

    @Override
    public List<String> getAllList() {
        log.onLog("getAllList");
        return sd.getAllList();
    }

    @Override
    public void insereLog(StackTraceElement[] erro) {
        log.onLog("insereLog");
        sd.insereLog(erro);
    }

    @Override
    public void processaException(String classe, Exception e) {
        log.onLog("processaException");
        sd.processaException(classe, e);
    }
}
