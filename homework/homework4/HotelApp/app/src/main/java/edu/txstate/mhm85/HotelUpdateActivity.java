package edu.txstate.mhm85;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.TextHttpResponseHandler;

import java.text.DecimalFormat;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

public class HotelUpdateActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;    // Used to store hotel information about hotel user clicked.
    int position;                           // Used to hold position of Hotel object for updating purposes.
    double stayCostPerDay;                  // Used to hold new cost entered by user.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_update);

        // Instantiate the SharedPreferences object.
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Create Hotel object based on values stored in shared preferences.
        final Hotel hotel = new Hotel();
        hotel.setId(sharedPreferences.getInt("id", 0));
        hotel.setName(sharedPreferences.getString("name", ""));
        hotel.setCostPerDay(sharedPreferences.getFloat("costPerDay", 0));

        // Get position of Hotel object from shared preferences
        position = sharedPreferences.getInt("position", 0);

        // The EditText where the user enters the new cost.
        final EditText txtStayCostPerDay = findViewById(R.id.txtStayCostPerDay);

        // Set text of EditText to current cost per day.
        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        String currentCostPerDay = formatter.format(hotel.getCostPerDay());
        txtStayCostPerDay.setText(currentCostPerDay);

        // Set text for the components based off Hotel object's property values.
        final TextView txtHotelId = findViewById(R.id.txtHotelId);
        txtHotelId.setText(txtHotelId.getText() + " " + hotel.getId());
        final TextView txtHotelName = findViewById(R.id.txtHotelName);
        txtHotelName.setText(txtHotelName.getText() + " " + hotel.getName());

        // Get button for updating hotel.
        Button btnUpdateCost = findViewById(R.id.btnUpdateCost);

        // Setup on-click listener for updating hotel stay cost per day.
        btnUpdateCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Relative url to update Cost property of specific object in database.
                String url = "hotels/" + position + "/costPerDay.json";

                // Handle exceptions related to user input.
                try {
                    // Get new stay cost per day that the user enters.
                    String newStayCostPerDay = txtStayCostPerDay.getText().toString();

                    // Check to make sure the user entered a value for new stay cost per day.
                    if (!newStayCostPerDay.isEmpty()) {
                        // Create the StringEntity object to be passed to the RestClient put method.
                        StringEntity entity = new StringEntity(newStayCostPerDay);
                        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/text"));

                        // Make a PUT HTTP request to the database to update cost for hotel.
                        RestClient.put(HotelUpdateActivity.this, url, entity, "application/text",
                            new TextHttpResponseHandler() {
                                @Override
                                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                                    // Warn user that PUT HTTP request failed.
                                    Toast.makeText(HotelUpdateActivity.this,
                                            "Failed to update Hotel cost - Status Code: " +
                                                    statusCode, Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                                    // Inform user that PUT HTTP request succeeded.
                                    Toast.makeText(HotelUpdateActivity.this,
                                            "Hotel cost has been updated - Status Code: " +
                                                    statusCode, Toast.LENGTH_LONG).show();
                                }
                            }
                        );
                    } else {
                        // Error message when user doesn't enter a new cost.
                        Toast.makeText(HotelUpdateActivity.this, "Please enter a new cost.", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Get button for returning to opening screen.
        Button btnReturnToOpeningScreen = findViewById(R.id.btnHome);

        // Setup on-click listener for returning to opening screen.
        btnReturnToOpeningScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Return to opening screen.
                startActivity(new Intent(HotelUpdateActivity.this, MainActivity.class));
            }
        });
    }
}
