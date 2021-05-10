package br.com.httpagent.soapaction;

public abstract class JSoapCallback {
    public abstract void onSuccess(Object result);
    public abstract void onError(int error);
    public void onDebugMessage(String title, String message) {}
}
