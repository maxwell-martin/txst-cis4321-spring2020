package edu.txstate.mhm85;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;

public class RestClient {
    // Server and database location.
    private static final String BASE_URL = "https://maxwell-martin-cis4321-2020.firebaseio.com/";

    // Class from LoopJ library.
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(Context context, String url, Header[] headers, RequestParams params,
                           AsyncHttpResponseHandler responseHandler) {
        client.get(context, getAbsoluteUrl(url), headers, params, responseHandler);
    }

    public static void put(Context context, String url, HttpEntity entity, String contentType, ResponseHandlerInterface responseHandler) {
        client.put(context, getAbsoluteUrl(url), entity, contentType, responseHandler);
    }

    // Combine base url with collection path
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
