package edu.txstate.mhm85;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class HotelInformationActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;    // Used to store hotel information about hotel user clicked.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the view to the activity_hotel_information.xml file.
        setContentView(R.layout.activity_hotel_information);

        // Instantiate the SharedPreferences object.
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Create Hotel object based on values stored in shared preferences.
        final Hotel hotel = new Hotel();
        hotel.setId(sharedPreferences.getInt("id", 0));
        hotel.setName(sharedPreferences.getString("name", ""));
        hotel.setCity(sharedPreferences.getString("city", ""));
        hotel.setState(sharedPreferences.getString("state", ""));
        hotel.setCostPerDay(sharedPreferences.getFloat("costPerDay", 0));

        // Formatter to display currency values in currency format.
        final DecimalFormat currencyFormatter = new DecimalFormat("$###,###,##0.00");

        // Set text for the components based off Hotel object's property values.
        final TextView txtHotelId = findViewById(R.id.txtHotelId);
        txtHotelId.setText(txtHotelId.getText() + " " + hotel.getId());
        final TextView txtHotelName = findViewById(R.id.txtHotelName);
        txtHotelName.setText(txtHotelName.getText() + " " + hotel.getName());
        final TextView txtHotelCity = findViewById(R.id.txtHotelCity);
        txtHotelCity.setText(txtHotelCity.getText() + " " + hotel.getCity());
        final TextView txtHotelState = findViewById(R.id.txtHotelState);
        txtHotelState.setText(txtHotelState.getText() + " " + hotel.getState());
        final TextView txtHotelCostPerDay = findViewById(R.id.txtHotelCostPerDay);
        txtHotelCostPerDay.setText(txtHotelCostPerDay.getText() + " " + currencyFormatter.format(hotel.getCostPerDay()));

        // Get update total button.
        Button btnUpdate = findViewById(R.id.btnUpdate);

        // Set the on click event handler for update button click
        btnUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Go to update hotel screen.
                startActivity(new Intent(HotelInformationActivity.this, HotelUpdateActivity.class));
            }
        });
    }
}
