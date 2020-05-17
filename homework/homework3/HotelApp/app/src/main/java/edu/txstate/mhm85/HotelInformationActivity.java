package edu.txstate.mhm85;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        hotel.setUrl(sharedPreferences.getString("url", ""));
        hotel.setImageId(sharedPreferences.getInt("imageId", 0));
        hotel.setImageContentDescription(sharedPreferences.getString("imageContentDescription", ""));

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

        // Get calculate total button.
        Button btnCalculateTotalCost = findViewById(R.id.btnCalculateTotalCost);

        // Set the on click event handler for calculate button click
        btnCalculateTotalCost.setOnClickListener(new View.OnClickListener() {
            // The EditText where the user enters number of stay days.
            final EditText txtNumberOfStayDays = findViewById(R.id.txtNumberOfStayDays);

            // The TextView where the total cost is displayed.
            final TextView txtTotalCost = findViewById(R.id.txtTotalCost);

            // Variable to hold number of stay days.
            int numberOfStayDays;

            // Variable to hold total cost.
            double totalCost;

            @Override
            public void onClick(View v) {
                // Clear the total cost text view for better UX.
                txtTotalCost.setText("");

                // Handle invalid number of stay days entered by user.
                try {
                    // Get number of stay days entered by user.
                    numberOfStayDays = Integer.parseInt(txtNumberOfStayDays.getText().toString());

                    // Check if number of stay days is valid.
                    if (numberOfStayDays > 0) {
                        // Calculate total cost.
                        totalCost = numberOfStayDays * hotel.getCostPerDay();

                        // Display total cost to user.
                        txtTotalCost.setText("Total Cost: " + currencyFormatter.format(totalCost));
                    } else {
                        // Throw an exception to display invalid number of stay days message (in catch).
                        throw new Exception();
                    }
                } catch (Exception ex) {
                    // Tell user they entered an invalid number of stay days.
                    Toast.makeText(HotelInformationActivity.this,
                            "Please enter a valid number of stay days.", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Get view hotel information button.
        Button btnViewHotelInfo = findViewById(R.id.btnViewHotelInfo);

        // Set the on click event handler for view hotel information button click.
        btnViewHotelInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open browser and send user to hotel's URL.
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(hotel.getUrl())));
            }
        });
    }
}
