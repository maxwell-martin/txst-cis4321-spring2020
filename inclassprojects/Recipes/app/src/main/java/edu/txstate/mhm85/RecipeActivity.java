package edu.txstate.mhm85;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        // Demo only
        Person me = new Person("Max", "Martin", 22);
        String message = "My name is " + me.getFullName();
        me.increaseAge();
    }
}
