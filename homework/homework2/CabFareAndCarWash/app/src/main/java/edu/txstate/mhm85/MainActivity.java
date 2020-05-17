package edu.txstate.mhm85;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Cab fare button
        Button btnCabFare = findViewById(R.id.btnCabFare);

        // Cab fare button onclick event listener
        btnCabFare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sends user to Cab Fare screen
                startActivity(new Intent(MainActivity.this, CabFareActivity.class));
            }
        });

        // Car wash button
        Button btnCarWash = findViewById(R.id.btnCarWash);

        // Car wash button onclick event listener
        btnCarWash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sends user to Car Wash screen
                startActivity(new Intent(MainActivity.this, CarWashActivity.class));
            }
        });
    }
}
