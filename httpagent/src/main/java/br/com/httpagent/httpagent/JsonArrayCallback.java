package br.com.httpagent.httpagent;

import android.util.Log;

import org.json.JSONArray;

public abstract class JsonArrayCallback extends HttpAgentResults {

    protected abstract void onDone(boolean success, JSONArray jsonResults);

    @Override
    protected void notify(String results) {
        if (hasError()) {
            onDone(false, null);
            return;
        }
        //there was no error -> lets parse the json array
        try {
            JSONArray jsonArray = new JSONArray(results);
            onDone(!hasError(), jsonArray);
        } catch (Exception ex) {
            Log.e(HttpAgent.TAG_ERROR, ex.getMessage());
            //update the error message (probably parse error...)
            setErrorMessage(ex.getMessage());
            onDone(false, null);
            return;
        }

    }
}
