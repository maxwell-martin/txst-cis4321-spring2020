package edu.txstate.mhm85;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

public class HotelListActivity extends ListActivity {
    List<Hotel> lstHotels = new ArrayList<>();  // Holds all of the Hotel objects.
    ListView listView;                          // Used to add footer to ListView.
    SharedPreferences sharedPreferences;        // Used to store hotel information about hotel that the user clicked.

    // Used to programmatically get images for hotels.
    int[] hotelImageIds = { R.drawable.best_western, R.drawable.embassy_suites, R.drawable.marriott, R.drawable.holiday_inn, R.drawable.la_quinta };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Headers for the HTTP request.
        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", "application/json"));

        // Make HTTP GET request via RestClient class.
        RestClient.get(HotelListActivity.this, "hotels.json",
                headers.toArray(new Header[headers.size()]), null,
                new JsonHttpResponseHandler() {

                    // This is the callback action for a successful HTTP GET request.
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        super.onSuccess(statusCode, headers, response);

                        // Loop through all JSONObjects in response.
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                // Create Hotel object from JSONObject.
                                Hotel hotel = new Hotel(response.getJSONObject(i));

                                // Add unique image and image description about hotel to Hotel object.
                                hotel.setImageId(hotelImageIds[i]);
                                hotel.setImageContentDescription("An image of a " + hotel.getName() + " hotel.");

                                // Add Hotel object to hotels list.
                                lstHotels.add(hotel);
                            } catch (JSONException ex) {
                                ex.printStackTrace();
                            }
                        }

                        // Set list adapter with our custom hotel adapter.
                        setListAdapter(new HotelAdapter(HotelListActivity.this, R.layout.activity_hotel_list, lstHotels));
                    }
                }
        );

        // Instantiate the SharedPreferences object.
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Footer for copyright
        TextView tvFooter = new TextView(getApplicationContext());
        tvFooter.setText("All images of hotels are from Google Images.");
        tvFooter.setPadding(0, 50, 0, 0);
        tvFooter.setGravity(Gravity.CENTER_HORIZONTAL);
        listView = getListView();
        listView.addFooterView(tvFooter, null, false);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        // Get the hotel object for the hotel that the user clicked.
        Hotel hotel = lstHotels.get(position);

        // Create the SharedPreferences editor object.
        SharedPreferences.Editor editor  = sharedPreferences.edit();

        // Store hotel information in shared preference key-value pairs.
        editor.putInt("id", hotel.getId());
        editor.putString("name", hotel.getName());
        editor.putString("city", hotel.getCity());
        editor.putString("state", hotel.getState());
        editor.putFloat("costPerDay", (float)hotel.getCostPerDay());

        // Store position of hotel in lst in shared preferences so it can be accessed for updating.
        editor.putInt("position", position);

        // Commit the additions to shared preferences.
        editor.commit();

        // Got to the HotelInformationActivity page.
        startActivity(new Intent(HotelListActivity.this, HotelInformationActivity.class));
    }


}
