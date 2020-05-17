package edu.txstate.mhm85;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class CarWashActivity extends AppCompatActivity {
    static final double EXTERIOR = 8.50;                       // Constant for cost of exterior wash only.
    static final double EXTERIOR_INTERIOR_TEN_PLUS = 12.50;    // Constant for cost of exterior and interior vacuum of 10 or more.
    static final double EXTERIOR_INTERIOR = 15.50;             // Constant for cost of exterior and interior vacuum of less than 10.

    int numberOfWashes;                                 // Variable to hold number of washes user enters.
    double totalCost;                                   // Variable to hold car wash total cost.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_wash);

        final EditText txtNumberOfWashes = findViewById(R.id.txtNumberOfWashes);    // The EditText where the user enters number of washes they want.

        // The RadioButton for exterior only wash.
        // Only need one radio button to determine which button is checked.
        // If this button is not checked, then the other button (exterior with interior vacuum) will be checked.
        final RadioButton radExteriorOnly = findViewById(R.id.radExteriorOnly);

        Button btnCalculatePackage = findViewById(R.id.btnCalculatePackage);        // The button that the user clicks to calculate car wash cost.

        btnCalculatePackage.setOnClickListener(new View.OnClickListener() {             // Set button's onclick event listener.
            final TextView txtCarWashResults = findViewById(R.id.txtCarWashResults);    // The TextView to display car wash cost.

            @Override
            public void onClick(View v) {       // Method called when user click's button.

                // Clear results TextView on click for a cleaner user experience.
                txtCarWashResults.setText("");

                // Exception handling for when the user enters nothing or too large of a number.
                try {
                    numberOfWashes = Integer.parseInt(txtNumberOfWashes.getText().toString());
                } catch (Exception ex) {
                    // Display error message if parsing integer fails to parse a valid number.
                    Toast.makeText(CarWashActivity.this, "Please enter a valid number of car washes.",
                            Toast.LENGTH_LONG).show();

                    // Exit onClick method to avoid calculating anything since an invalid number of washes was entered.
                    return;
                }

                if (radExteriorOnly.isChecked()) {                                  // Check if exterior only radio button was selected.
                    totalCost = numberOfWashes * EXTERIOR;                          // Calculate total cost.
                } else {                                                            // Exterior with interior vacuum radio button is checked.
                    if (numberOfWashes >= 10) {                                     // Check if user wants to purchase 10 or more washes.
                        totalCost = numberOfWashes * EXTERIOR_INTERIOR_TEN_PLUS;    // Calculate total cost with discount.

                        // Display message telling user they got a discount for purchasing 10 or more exterior washes with interior vacuums.
                        Toast.makeText(CarWashActivity.this, "You received the 10+ washes discount!",
                                Toast.LENGTH_LONG).show();
                    } else {
                        totalCost = numberOfWashes * EXTERIOR_INTERIOR;             // Calculate total cost without discount.
                    }
                }

                DecimalFormat currencyFormatterUsd = new DecimalFormat("$###,##0.00");  // Used to format total cost to USD.

                // Display total cost of car wash package. Check for 1 wash to determine singular or plural spelling of "washes."
                if (numberOfWashes == 1) {
                    txtCarWashResults.setText("Cost of " + numberOfWashes + " wash: " + currencyFormatterUsd.format(totalCost));
                } else {
                    txtCarWashResults.setText("Cost of " + numberOfWashes + " washes: " + currencyFormatterUsd.format(totalCost));
                }

            }
        });
    }
}
