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

public class MainActivity extends AppCompatActivity {
    double dblConvertedWeight;
    static final double CONVERSION_RATE = 2.2; // Constant

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText weightOfPatient = findViewById(R.id.txtWeightOfPatient);
        final RadioButton poundsToKilograms = findViewById(R.id.radPoundsToKilograms);
        final RadioButton kilogramsToPounds = findViewById(R.id.radKilogramsToPounds);
        Button convertWeight = findViewById(R.id.btnConvertWeight);
        final TextView results = findViewById(R.id.txtResults);

        convertWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                results.setText("");
                if (weightOfPatient.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter a number!",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                double dblWeightOfPatient = Double.parseDouble(weightOfPatient.getText().toString());
                if (poundsToKilograms.isChecked() && dblWeightOfPatient > 500) {
                    Toast.makeText(MainActivity.this, "The entered weight must be less than 500!",
                            Toast.LENGTH_LONG).show();
                    return;
                } else if (poundsToKilograms.isChecked() && dblWeightOfPatient <= 500) {
                    dblConvertedWeight = dblWeightOfPatient / CONVERSION_RATE;
                } else if (kilogramsToPounds.isChecked() && dblWeightOfPatient <= 225) {
                    dblConvertedWeight = dblWeightOfPatient * CONVERSION_RATE;
                } else {
                    Toast.makeText(MainActivity.this, "The entered weight must be less than 225!",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                DecimalFormat formatter = new DecimalFormat("#.#");

                results.setText(formatter.format(dblConvertedWeight));
            }
        });
    }
}
