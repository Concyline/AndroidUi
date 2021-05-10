package br.com.httpagent.httpagent;

import android.util.Log;

import org.json.JSONObject;

public abstract class JsonCallback extends HttpAgentResults {
    protected abstract void onDone(boolean success, JSONObject jsonResults);

    @Override
    protected void notify(String results) {
        if (hasError()) {
            onDone(false, null);
            return;
        }

        //there was no error -> lets parse the json object
        try {
            if (U.isEmpty(results)) results = "{}";
            JSONObject jsonObject = new JSONObject(results);
            onDone(!hasError(), jsonObject);
        } catch (Exception ex) {
            Log.e(HttpAgent.TAG_ERROR, ex.getMessage());
            //update the error message (probably parse error...)
            setErrorMessage(ex.getMessage());
            onDone(false, null);
            return;
        }
    }
}
