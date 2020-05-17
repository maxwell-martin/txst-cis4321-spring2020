package edu.txstate.mhm85;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {
    List<Attraction> list = new ArrayList<Attraction>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        // Footer for copyright
        TextView tvFooter = new TextView(getApplicationContext());
        tvFooter.setText("Icon made by Pixel perfect from www.flaticon.com");
        listView = getListView();
        listView.addFooterView(tvFooter);

        // Create attractions.
        Attraction a1 = new Attraction();
        a1.setId(100);
        a1.setName("Mayan");
        a1.setImage(R.drawable.mayan);

        Attraction a2 = new Attraction();
        a2.setId(107);
        a2.setName("Forbidden City");
        a2.setImage(R.drawable.forbidden_city);

        Attraction a3 = new Attraction();
        a3.setId(109);
        a3.setName("Pyramid");
        a3.setImage(R.drawable.pyramids);

        // Store attractions inside list.
        list.add(a1);
        list.add(a2);
        list.add(a3);

        // Set list adapter with our custom attraction adapter.
        setListAdapter(new AttractionAdapter(MainActivity.this, R.layout.attraction_row, list));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //super.onListItemClick(l, v, position, id);
        Toast.makeText(MainActivity.this, list.get(position).getName(), Toast.LENGTH_LONG).show();
    }
}
