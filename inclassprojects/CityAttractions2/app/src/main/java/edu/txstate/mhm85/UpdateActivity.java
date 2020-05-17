package edu.txstate.mhm85;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class UpdateActivity extends AppCompatActivity {
    int intPos;
    int intId;
    double dblCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        // Receive data from previous activity
        intPos = this.getIntent().getIntExtra("POS", 0);
        intId = this.getIntent().getIntExtra("ID", 0);
        dblCost = this.getIntent().getDoubleExtra("COST", 0);

        // Display Attraction ID
        TextView id = findViewById(R.id.txtId);
        id.setText(String.valueOf(intId));

        // Display Cost
        DecimalFormat decFormat = new DecimalFormat("###.##");
        final EditText cost = findViewById(R.id.edtCost);
        cost.setText(decFormat.format(dblCost));

        Button update = findViewById(R.id.btnUpdate);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Relative url to update Cost property of specific object in database.
                String url = "attractions/" + intPos + "/Cost.json";

                StringEntity entity = null;

                // Handle invalid input by user.
                try {
                    entity = new StringEntity(cost.getText().toString());
                    entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/text"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                RestClient.put(UpdateActivity.this, url, entity, "application/text", new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Toast.makeText(UpdateActivity.this, "Failure", Toast.LENGTH_LONG).show();
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Toast.makeText(UpdateActivity.this, "Success", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
