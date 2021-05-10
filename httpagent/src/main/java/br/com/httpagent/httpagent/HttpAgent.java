package br.com.httpagent.httpagent;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpAgent extends AsyncTask<Void, Void, String> {
    public static final int DEFAULT_TIMEOUT = 1000 * 10;
    private static final int RETRY_TIMES = 2;

    public final String TAG = getClass().getName();
    public static final String TAG_ERROR = "HttpAgent_ERROR";

    private String mUrl;
    private HttpURLConnection mConnection;
    private String mVerb = HTTP.GET;
    private Map<String, String> mQueryParams = new HashMap<>();
    private Map<String, String> mHeaders = new HashMap<>();
    private int mTimeOut = DEFAULT_TIMEOUT;
    private String mBody;
    private boolean mHasBody = false;
    private String mContentType = HTTP.CONTENT_TYPE_APPLICATION_JSON;
    private HttpAgentResults mCallback;
    private String mErrorMessage = "";
    private int mResponseCode;
    private boolean isParallel = true;
    private static HttpAgentErrorHandler mErrorHandler;
    private static Handler mHandler;
    private Activity activity;

    public static void setErrorHandler(HttpAgentErrorHandler errorHandler) {
        if (errorHandler != null) {
            mErrorHandler = errorHandler;
            mHandler = new Handler(mErrorHandler);
        }
    }

    public HttpAgent(Activity activity , String url, String httpVerb) {
        this.activity = activity;
        this.mUrl = url;
        this.mVerb = httpVerb;
    }

    /*private HttpAgent(String url, String httpVerb) {
        this.mUrl = url;
        this.mVerb = httpVerb;
    }*/

    /*public static HttpAgent get(String url) {
        HttpAgent instance = new HttpAgent(url, HTTP.GET);
        return instance;
    }

    public static HttpAgent post(String url) {
        HttpAgent instance = new HttpAgent(url, HTTP.POST);
        return instance;
    }

    public static HttpAgent put(String url) {
        HttpAgent instance = new HttpAgent(url, HTTP.PUT);
        return instance;
    }

    public static HttpAgent delete(String url) {
        HttpAgent instance = new HttpAgent(url, HTTP.DELETE);
        return instance;
    }

    public static HttpAgent patch(String url) {
        HttpAgent instance = new HttpAgent(url, HTTP.PATCH);
        return instance;
    }*/

    public HttpAgent queryParams(String... queryParams) {
        if (queryParams.length % 2 != 0) {
            mErrorMessage += "\nquery params must be even number";
            Log.e(TAG, "query params must be even number");
            return this;
        }
        for (int i = 0; i < queryParams.length; i += 2) {
            mQueryParams.put(queryParams[i], queryParams[i + 1]);
        }
        return this;
    }

    /**
     * sets whether to run http call parallel or not
     *
     * @param enable
     * @return
     */
    public HttpAgent parallel(boolean enable) {
        this.isParallel = enable;
        return this;
    }

    public HttpAgent setTokenBearer(String token){
        headers("Authorization","Bearer " + token);
        return this;
    }

    public HttpAgent headers(String... headers) {
        if (headers.length % 2 != 0) {
            mErrorMessage += "\nheaders must be even number";
            Log.e(TAG, "headers must be even number");
            return this;
        }
        for (int i = 0; i < headers.length; i += 2) {
            mHeaders.put(headers[i], headers[i + 1]);
        }

        return this;
    }

    public HttpAgent setTimeOut(int timeOutInMillis) {
        this.mTimeOut = timeOutInMillis;
        return this;
    }

    public HttpAgent contentType(String contentType) {
        this.mContentType = contentType;
        return this;
    }

    public HttpAgent withBody(String body) {
        this.mBody = body;
        this.mHasBody = !U.isEmpty(mBody);
        return this;
    }

    public HttpAgent withBody(JSONObject body) {
        this.mBody = body.toString();
        this.mHasBody = !U.isEmpty(mBody);
        return this;
    }

    public HttpAgent withBody(String... bodyParams) {
        if (bodyParams.length % 2 != 0) {
            mErrorMessage += "\nquery params must be even number";
            Log.e(TAG, "query params must be even number");
            return this;
        }
        try {
            JSONObject body = new JSONObject();
            for (int i = 0; i < bodyParams.length; i += 2) {
                body.put(bodyParams[i], bodyParams[i + 1]);
            }
            this.mBody = body.toString();
            this.mHasBody = !U.isEmpty(mBody);
        } catch (Exception ex) {
            mErrorMessage = ex.getMessage();
        }
        return this;
    }

    public void go(SuccessCallback callback) {
        this.mCallback = callback;
        executeSelf();
    }

    public void goJson(JsonCallback callback) {
        this.mCallback = callback;
        executeSelf();
    }

    public void goJsonArray(JsonArrayCallback callback) {
        this.mCallback = callback;
        executeSelf();
    }

    public void goString(StringCallback callback) {
        this.mCallback = callback;
        executeSelf();
    }

    private void executeSelf() {
        if (isParallel) {
            // AsyncTaskCompat.executeParallel(this);
            this.execute();
            return;
        }
        this.execute();
    }

    private ProgressDialog progressDialog;

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(activity, "Aguarde", "Fazendo conexão com o servidor...", false, false);
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            //check if there is an error
            if (!U.isEmpty(mErrorMessage))
                return null;

            String queryParams = createQueryStringForParameters(mQueryParams);
            String urlWithParams = mUrl + queryParams;

            for (int i = 0; i < RETRY_TIMES; ++i) {
                mConnection = (HttpURLConnection) (new URL(urlWithParams)).openConnection();
                mConnection.setRequestMethod(mVerb);

                if (mVerb.equalsIgnoreCase(HTTP.PATCH)) {
                    mConnection.setRequestMethod("POST");
                    mConnection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
                }

                mConnection.setReadTimeout(mTimeOut);
                mConnection.setConnectTimeout(mTimeOut);
                mConnection.setDoInput(true);
                mConnection.setDoOutput(mHasBody);
                mConnection.setRequestProperty("Content-Type", mContentType);
                addHeaders();
                //mConnection.connect();
                Log.d(TAG, "Sending " + mVerb + " request to:\n" + urlWithParams + (mHasBody ? ("\nwith body:\n" + mBody) : ""));

                // write body
                if (mHasBody) {
                    Writer writer = new BufferedWriter(new OutputStreamWriter(mConnection.getOutputStream(), "UTF-8"));
                    writer.write(mBody);
                    writer.close();
                }

                //get response code
                mResponseCode = mConnection.getResponseCode();
                if (mResponseCode <= 0) {
                    Log.e(TAG_ERROR, "Response code was: " + mResponseCode + ". retrying...");
                } else {
                    break;
                }
            }
            // get the results
            BufferedReader reader = new BufferedReader(new InputStreamReader(mResponseCode >= 400 ? mConnection.getErrorStream() : mConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            //String line;
            while (true) {
                final String line = reader.readLine();
                if (line == null) break;
                stringBuilder.append(line + "\n");
            }

            //check if the server response is an error response
            if (mResponseCode >= 400) {
                mErrorMessage = stringBuilder.toString();
                return null;
            }

            String result = stringBuilder.toString();
            return result;

        } catch (FileNotFoundException e) {
            mErrorMessage = e.getMessage();

        } catch (Exception e) {
            if (e.getMessage() != null) {
                mErrorMessage = e.getMessage();
            }

        } finally {
            if (mConnection != null)
                mConnection.disconnect();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {

        if (progressDialog != null) {
            progressDialog.dismiss();
        }

        if (!U.isEmpty(mErrorMessage)) {
            Log.e(TAG_ERROR, mErrorMessage);

            if (mErrorHandler != null) {
                boolean shouldContinue = mErrorHandler.onError(mResponseCode, mErrorMessage);
                if (!shouldContinue) return;
            }

            if (mCallback != null) {
                mCallback.setErrorMessage(mErrorMessage);
                mCallback.setResponseCode(mResponseCode);
                mCallback.setStringResults(result);
                mCallback.notify(null);
            }
            return;
        }
        //there is no error message
        if (result == null) result = "";

        Log.d(TAG, "Server Response: " + result);

        if (mCallback != null) {
            mCallback.setStringResults(result);
            mCallback.setErrorMessage(mErrorMessage);
            mCallback.setResponseCode(mResponseCode);
            mCallback.notify(result.trim());
        }
    }

    public static String createQueryStringForParameters(Map<String, String> parameters) {
        if (parameters == null) {
            return "";
        }

        Uri.Builder builder = new Uri.Builder();
        for (String key : parameters.keySet()) {
            builder.appendQueryParameter(key, parameters.get(key));
        }

        String query = builder.build().getEncodedQuery();
        if (query == null) query = "";
        //add question mark ata the beginning
        if (query.trim().length() > 0)
            query = "?" + query;
        return query;
    }

    private void addHeaders() {
        if (mHeaders == null) return;

        for (Map.Entry<String, String> entry : mHeaders.entrySet()) {
            mConnection.setRequestProperty(entry.getKey(), entry.getValue());
        }
    }
}

