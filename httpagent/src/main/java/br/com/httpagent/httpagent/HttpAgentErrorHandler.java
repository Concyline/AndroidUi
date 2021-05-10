package br.com.httpagent.httpagent;

import android.os.Handler;
import android.os.Message;

public abstract class HttpAgentErrorHandler implements Handler.Callback {

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    protected abstract boolean onError(int responseCode, String errorMessage);
}
