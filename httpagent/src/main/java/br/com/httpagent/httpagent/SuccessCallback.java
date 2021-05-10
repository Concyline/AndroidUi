package br.com.httpagent.httpagent;

public abstract class SuccessCallback extends HttpAgentResults {
    protected abstract void onDone(boolean success);

    @Override
    protected void notify(String results) {
        onDone(!hasError());
    }
}
