package edu.txstate.mhm85;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

public class MainActivity extends ListActivity {
    List<Attraction> attractionList = new ArrayList<Attraction>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        TextView tv = new TextView(getApplicationContext());
//        tv.setText("Icon made by Pixel perfect from www.flaticon.com");
//        listView = getListView();
//        listView.addFooterView(tv);

        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", "application/json"));

        RestClient.get(MainActivity.this, "attractions.json", headers.toArray(new Header[headers.size()]),
                        null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                //super.onSuccess(statusCode, headers, response);

                // Loop through all JSONObjects in response.
                for (int i = 0; i < response.length(); i++) {
                    try {
                        // Create Note object from JSONObject.
                        Attraction bean = new Attraction(response.getJSONObject(i));

                        // Add Note object to Note ArrayList.
                        attractionList.add(bean);

                        setListAdapter(new ArrayAdapter<Attraction>(MainActivity.this, R.layout.row_template, R.id.txtAttraction, attractionList));
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_LONG).show();

        int attractionId = attractionList.get(position).getId();
        double cost = attractionList.get(position).getCost();
        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
        intent.putExtra("POS", position);
        intent.putExtra("ID", attractionId);
        intent.putExtra("COST", cost);
        startActivity(intent);
    }
}
