package br.com.httpagent.httpagent;

public abstract class HttpAgentResults {

    protected final String TAG = getClass().getName();

    private int responseCode;
    private String errorMessage = "";
    private String stringResults;

    protected String getErrorMessage() {
        return errorMessage;
    }

    protected boolean hasError() {
        return !U.isEmpty(getErrorMessage());
    }

    protected String getStringResults() {
        return stringResults;
    }

    protected int getResponseCode() {
        return responseCode;
    }

    protected abstract void notify(String results);

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setStringResults(String stringResults) {
        this.stringResults = stringResults;
    }
}
