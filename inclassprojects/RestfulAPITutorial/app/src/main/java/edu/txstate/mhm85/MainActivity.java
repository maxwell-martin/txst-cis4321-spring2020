package edu.txstate.mhm85;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", "application/json"));

        RestClient.get(MainActivity.this, "notes.json",
                headers.toArray(new Header[headers.size()]), null,
                new JsonHttpResponseHandler() {

                    // This is the callback action for a successful HTTP GET request.
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        super.onSuccess(statusCode, headers, response);

                        // An ArrayList to hold note objects retrieved from Firebase.
                        ArrayList<Note> noteArray = new ArrayList<Note>();

                        // Loop through all JSONObjects in response.
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                // Create Note object from JSONObject.
                                Note bean = new Note(response.getJSONObject(i));

                                // Add Note object to Note ArrayList.
                                noteArray.add(bean);
                            } catch (JSONException ex) {
                                ex.printStackTrace();
                            }
                        }

                        // Add Notes to list adapter for display on screen with default simple list layout.
                        setListAdapter(new ArrayAdapter<Note>(MainActivity.this,
                                android.R.layout.simple_list_item_1, noteArray));
                    }
                }
        );
    }
}
