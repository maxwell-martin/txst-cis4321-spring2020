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

import java.util.ArrayList;
import java.util.List;

public class HotelListActivity extends ListActivity {
    List<Hotel> lstHotels = new ArrayList<>();  // Holds all of the Hotel objects.
    ListView listView;                          // Used to add footer to ListView.
    SharedPreferences sharedPreferences;        // Used to store hotel information about hotel user clicked.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Instantiate the SharedPreferences object.
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Arrays to provide values for properties of each Hotel object.
        int[] hotelIds = { 20201, 20202, 20203, 20204, 20205 };
        String[] hotelNames = { "Best Western", "Embassy Suites", "Marriott", "Holiday Inn", "La Quinta" };
        String[] hotelCities = { "Austin", "Denver", "Los Angeles", "Miami", "Boston" };
        String[] hotelStates = { "Texas", "Colorado", "California", "Florida", "Massachusetts" };
        double[] hotelCostsPerDay = { 75.00, 100.00, 150.00, 60.00, 40.00 };
        String[] hotelUrls = { "https://www.bestwestern.com/", "https://www.hilton.com/en/embassy/",
                               "https://www.marriott.com/", "https://www.ihg.com/holidayinn/",
                               "https://www.wyndhamhotels.com/laquinta"};
        int[] hotelImageIds = { R.drawable.best_western, R.drawable.embassy_suites, R.drawable.marriott,
                                R.drawable.holiday_inn, R.drawable.la_quinta };

        // A loop that creates Hotel objects with corresponding properties and adds each to list of Hotel objects.
        for (int i = 0; i < 5; i++) {
            Hotel hotel = new Hotel(hotelIds[i], hotelNames[i], hotelCities[i], hotelStates[i],
                                    hotelCostsPerDay[i], hotelUrls[i], hotelImageIds[i],
                                    "An image of " + hotelNames[i]);
            lstHotels.add(hotel);
        }

        // Footer for copyright
        TextView tvFooter = new TextView(getApplicationContext());
        tvFooter.setText("All images of hotels are from Google Images.");
        tvFooter.setPadding(0, 50, 0, 0);
        tvFooter.setGravity(Gravity.CENTER_HORIZONTAL);
        listView = getListView();
        listView.addFooterView(tvFooter, null, false);

        // Set list adapter with our custom hotel adapter.
        setListAdapter(new HotelAdapter(HotelListActivity.this, R.layout.activity_hotel_list, lstHotels));
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
        editor.putString("url", hotel.getUrl());
        editor.putInt("imageId", hotel.getImageId());
        editor.putString("imageContentDescription", hotel.getImageContentDescription());

        // Commit the additions to shared preferences.
        editor.commit();

        // Got to the HotelInformationActivity page.
        startActivity(new Intent(HotelListActivity.this, HotelInformationActivity.class));
    }
}
