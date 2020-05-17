package edu.txstate.mhm85;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import java.text.DecimalFormat;

public class PaymentActivity extends AppCompatActivity {
    int intCarLoanAmount;
    int intNumberOfYears;
    double dblInterestRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        TextView carLoanAmount = findViewById(R.id.txtCarLoanAmountDisplay);
        TextView monthlyPayment = findViewById(R.id.txtMonthlyPaymentDisplay);

        // Option 1: Get data from shared preferences.
//        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(PaymentActivity.this);
//        intNumberOfYears = pref.getInt("KeyNumberOfYears", 0);
//        intCarLoanAmount = pref.getInt("KeyCarLoanAmount", 0);
//        dblInterestRate = pref.getFloat("KeyInterestRate", 0);

        // Option 2: Get data from intent.
        intNumberOfYears = getIntent().getIntExtra("KeyNumberOfYears", 0);
        intCarLoanAmount = getIntent().getIntExtra("KeyCarLoanAmount", 0);
        dblInterestRate = getIntent().getDoubleExtra("KeyInterestRate", 0);

        double dblMonthlyPayment = (intCarLoanAmount * (1 + (dblInterestRate * intNumberOfYears))) / (12 * intNumberOfYears);
        carLoanAmount.setText(String.valueOf(intCarLoanAmount));
        DecimalFormat format = new DecimalFormat("$###,###.##");
        monthlyPayment.setText(format.format(dblMonthlyPayment));
    }
}
