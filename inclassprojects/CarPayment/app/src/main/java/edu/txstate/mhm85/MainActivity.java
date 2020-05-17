package edu.txstate.mhm85;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.prefs.PreferenceChangeEvent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText numberOfYears = findViewById(R.id.txtNumberOfYears);
        final EditText carLoanAmount = findViewById(R.id.txtCarLoanAmount);
        final EditText interestRate = findViewById(R.id.txtInterestRate);

        Button carPayment = findViewById(R.id.btnCarPayment);

        carPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int intNumberOfYear = Integer.parseInt(numberOfYears.getText().toString());
                int intCarLoanAmount = Integer.parseInt(carLoanAmount.getText().toString());
                double dblInterestRate = Double.parseDouble(interestRate.getText().toString());

                // Option 1: Save the numbers to a persistence storage
//                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
//                SharedPreferences.Editor editor = pref.edit();
//                editor.putInt("KeyNumberOfYears", intNumberOfYear);
//                editor.putInt("KeyCarLoanAmount", intCarLoanAmount);
//                editor.putFloat("KeyInterestRate", (float) dblInterestRate);
//                editor.commit();
//
//                startActivity(new Intent(MainActivity.this, PaymentActivity.class));

                // Option 2: Pass data between activities via using putExtra() method of intent
                Intent intent = new Intent(MainActivity.this, PaymentActivity.class);
                intent.putExtra("KeyNumberOfYears", intNumberOfYear);
                intent.putExtra("KeyCarLoanAmount", intCarLoanAmount);
                intent.putExtra("KeyInterestRate", dblInterestRate);

                startActivity(intent);
            }
        });

    }
}
