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

public class MainActivity extends AppCompatActivity {
    double dblCostPerTicket = 79.99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText ticketNumber = findViewById(R.id.txtTicketNumber);
        Button findCost = findViewById(R.id.btnFindCost);
        final TextView totalCost = findViewById(R.id.txtTotalCost);
        final Spinner concerts = findViewById(R.id.spnConcerts);

        findCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strTicketNumber = ticketNumber.getText().toString();
                int intTicketNumber = 0;
                try {
                    intTicketNumber = Integer.parseInt(strTicketNumber);
                } catch (Exception ex) {
                    Toast.makeText(MainActivity.this,
                            "Please input a number.", Toast.LENGTH_SHORT);
                    return; // When there is an exception thrown parsing input, exit method.
                }

                String strConcert = concerts.getSelectedItem().toString();

                if (strConcert.equals("Spatial Sense")) {
                    dblCostPerTicket = 120.60;
                }

                double dblTotalCost = intTicketNumber * dblCostPerTicket;

                DecimalFormat formatter = new DecimalFormat("$###,###.00");
                String strTotalCost = formatter.format(dblTotalCost);

                totalCost.setText("Total Cost: " + strTotalCost + " and your concert is " +
                        concerts.getSelectedItem().toString() + ".");
            }
        });
    }
}
