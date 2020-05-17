package edu.txstate.mhm85;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Reference to button on MainActivity
        Button btnViewRecipe = findViewById(R.id.btnViewRecipe);

        // OnClick Listener for button on MainActivity
        btnViewRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display a message
                Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_LONG).show();

                // Intent class used to signal activity we are coming from and which class to go to.
                // packageContext: The current activity.
                Intent intent = new Intent(MainActivity.this, RecipeActivity.class);

                // Start RecipeActivity by specifying our intent.
                startActivity(intent);

                // The above two lines in one line.
                // startActivity(new Intent(MainActivity.this, RecipeActivity.class));
            }
        });

        // Demo
        //showIntroductionMessage(new Lecturer("Harold", "Popcorn", 20));   // Lecture message
        //showIntroductionMessage(new SalesPerson("Joe", "Smith", 20));     // SalesPerson message
        showIntroductionMessage(new Speaker() {     // Anonymous class; use introduce() method without creating whole new class
            @Override
            public String introduce() {
                return "Howdy. It's nice to meet you.";
            }
        });
    }

    // Demo method to show OOP inheritance, polymorphism, and interfaces.
    void showIntroductionMessage(Speaker s) {
        Toast.makeText(MainActivity.this, s.introduce(), Toast.LENGTH_LONG).show();
    }
}
