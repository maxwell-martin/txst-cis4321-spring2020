package edu.txstate.mhm85;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class CabFareActivity extends AppCompatActivity {
    static final double INITIAL_FEE = 2.00;        // Constant for initial fee
    static final double MILEAGE_RATE = 3.25;       // Constant for mileage rate

    double distance;                        // Variable to hold distance entered by user.
    String cabSelection;                    // Variable to hold user's cab preference.
    double cabFare;                         // Variable to hold calculated cab fare.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cab_fare);

        final EditText txtDistance = findViewById(R.id.txtDistance);    // The EditText where the user enters distance in miles.
        final Spinner spnCabTypes = findViewById(R.id.spnCabTypes);     // The Spinner where the user selects his or her cab preference.

        Button btnCalculateCabFare = findViewById(R.id.btnCalculateCabFare);    // The button that the user clicks to calculate cab fare.

        btnCalculateCabFare.setOnClickListener(new View.OnClickListener() {         // Set button's onclick event listener.
            final TextView txtCabSelection = findViewById(R.id.txtCabSelection);    // The TextView to display cab selection.
            final TextView txtCabCost = findViewById(R.id.txtCabCost);              // The TextView to display cab fare.

            @Override
            public void onClick(View v) {       // Method called when user click's button.

                // Clear results TextViews on click for a cleaner user experience.
                txtCabSelection.setText("");
                txtCabCost.setText("");

                // Exception handling for when the user enters nothing or too large of a number.
                try {
                    distance = Double.parseDouble(txtDistance.getText().toString());
                } catch (Exception ex) {
                    // Display error message if parsing double fails to parse a valid number.
                    Toast.makeText(CabFareActivity.this, "Please enter a valid distance.",
                            Toast.LENGTH_LONG).show();

                    // Exit onClick method to avoid calculating anything since an invalid distance was entered.
                    return;
                }

                cabSelection = spnCabTypes.getSelectedItem().toString();        // Get the cab preference that the user selected.
                cabFare = calculateCabFare(distance);                           // Calculate the cab fare.

                DecimalFormat currencyFormatterUsd = new DecimalFormat("$###,##0.00");  // Used to format total cost to USD.

                txtCabSelection.setText("Cab selection: " + cabSelection);                     // Display cab selection
                txtCabCost.setText("Cab fare: " + currencyFormatterUsd.format(cabFare));       // Display cab fare.
            }
        });
    }

    private double calculateCabFare(double distance) {      // Method to calculate cab fare.
        return INITIAL_FEE + (distance * MILEAGE_RATE);
    }
}
