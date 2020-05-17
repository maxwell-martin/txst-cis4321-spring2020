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

        // Set the view to the activity_main.xml file.
        setContentView(R.layout.activity_main);

        // Button to go to page that displays hotel list.
        Button btnViewHotelList = findViewById(R.id.btnViewHotelList);

        // Event fired when user clicks the view hotel list button.
        btnViewHotelList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to the next screen: the screen that displays a listing of hotels.
                startActivity(new Intent(MainActivity.this, HotelListActivity.class));
            }
        });
    }
}
