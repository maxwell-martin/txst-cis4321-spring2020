package edu.txstate.mhm85;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {
    List<Attraction> attractionList = new ArrayList<Attraction>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tv = new TextView(getApplicationContext());
        tv.setText("Icon made by Pixel perfect from www.flaticon.com");
        listView = getListView();
        listView.addFooterView(tv);

        //String[] attractions = { "Art Institute", "Magnificent Mile", "Willis Tower", "Navy Pier", "Water Tower"};

        Attraction a1 = new Attraction();
        a1.setId(101);
        a1.setName("Art Institute");
        a1.setUrl("http://artic.edu");
        attractionList.add(a1);

        Attraction a2 = new Attraction();
        a2.setId(102);
        a2.setName("Magnificent Mile");
        a2.setUrl("http://themagnificentmile.com");
        attractionList.add(a2);

        Attraction a3 = new Attraction();
        a3.setId(103);
        a3.setName("Willis Tower");
        a3.setUrl("http://google.com");
        attractionList.add(a3);

        // VERSION ONE - Default layout and String array
        // 1: Current activity (context) - controller with a default view
        // 2: Display parameters of the layout to be used
        // 3: Data source - the String array of attractions
        //setListAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, attractions));

        // VERSION TWO - Overloaded setListAdapter method - Custom layout and String array
        //setListAdapter(new ArrayAdapter<String>(MainActivity.this, R.layout.row_template, R.id.txtAttraction, attractions));

        // VERSION THREE - Overloaded setListAdapter method - Custom layout and Object array
        setListAdapter(new ArrayAdapter<Attraction>(MainActivity.this, R.layout.row_template, R.id.txtAttraction, attractionList));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_LONG).show();

        // Send user to URL associated with the item clicked.
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(attractionList.get(position).getUrl())));
    }
}
