package robindymer.interactivestory.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import robindymer.interactivestory.R;

// This is a subclass of the AppCompatActivity class
public class MainActivity extends AppCompatActivity {

    private EditText nameField;
    private Button startButton;
    private boolean red = true;
    private boolean green = true;
    private boolean blue = true;

    // @Override - In this case it states that we are overriding the onCreate method of the parent class
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = (EditText) findViewById(R.id.nameEditText);
        startButton = (Button) findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Convert Editable string to a regular String
                String name = nameField.getText().toString();
                startStory(name);
            }
        });
    }



    // If the user go back the same instance will be used so we need to set the text to blank
    // The code will always run after onCreate
    @Override
    protected void onResume() {
        super.onResume();
        nameField.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        // Change color of image icon
        Drawable nextImageDrawable = menu.findItem(R.id.nextImage).getIcon();
        nextImageDrawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        // findItem - get an item from an id
        menu.findItem(R.id.red).setChecked(true);
        menu.findItem(R.id.green).setChecked(true);
        menu.findItem(R.id.blue).setChecked(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nextImage:
                // Code for the click on nextImage
                break;
            case R.id.color:
                // Code
                break;
            case R.id.red:
                item.setChecked(red);
                if (red) {
                    red = false;
                } else {
                    red = true;
                }
                break;
            case R.id.green:
                item.setChecked(green);
                if (green) {
                    green = false;
                } else {
                    green = true;
                }
                break;
            case R.id.blue:
                item.setChecked(blue);
                if (blue) {
                    blue = false;
                } else {
                    blue = true;
                }
                break;
            case R.id.reset:
                red = green = blue = true;
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // Initialize our other activity and put in data in the intent so we can use it in our other activity
    private void startStory(String name) {
        // this - Refers to this current class. This is also the context
        Intent intent = new Intent(this, StoryActivity.class);
        // Using the class when you're trying to access things outside of an Activity
        Resources resources = getResources();
        // Get the string id value
        String key = resources.getString(R.string.key_name);
        intent.putExtra(key, name);
        startActivity(intent);
    }
}
