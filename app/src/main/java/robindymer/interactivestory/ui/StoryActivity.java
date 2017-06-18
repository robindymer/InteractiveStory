package robindymer.interactivestory.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Stack;

import robindymer.interactivestory.R;
import robindymer.interactivestory.model.Page;
import robindymer.interactivestory.model.Story;

public class StoryActivity extends AppCompatActivity {

    public static final String TAG = StoryActivity.class.getSimpleName();

    private String name;
    private Story story;
    // Set up the data for the UI based on the page we get
    private ImageView storyImageView;
    private TextView storyTextView;
    private Button choice1Button;
    private Button choice2Button;
    // Preparing to add items to a stack
    private Stack<Integer> pageStack = new Stack<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        // Now we have views and data
        storyImageView = (ImageView) findViewById(R.id.storyImageView);
        storyTextView = (TextView) findViewById(R.id.storyTextView);
        choice1Button = (Button) findViewById(R.id.choice1Button);
        choice2Button = (Button) findViewById(R.id.choice2Button);

        // Return the intent that started this activity with getIntent
        Intent intent = getIntent();
        name = intent.getStringExtra(getString(R.string.key_name));
        // Handle if it's the wrong key
        if (name == null || name.isEmpty()) {
            name = "Friend";
        }
        Log.d(TAG, name);

        story = new Story();
        loadPage(0);
    }

    // Method to load different pages so we don't have to write dublicate code
    private void loadPage(int pageNumber) {
        pageStack.push(pageNumber);

        // Will return a page object with different attributes
        final Page page = story.getPage(pageNumber);

        // Use ContextCompat because we are in an activity which is a context
        // page.getImageId() - The getter from the Page class
        Drawable image = ContextCompat.getDrawable(this, page.getImageId());
        storyImageView.setImageDrawable(image);
        // Get the page text and store it as a formatted string
        String pageText = getString(page.getTextId());
        // Add name if placeholder included. Won't add if not
        pageText = String.format(pageText, name);
        storyTextView.setText(pageText); // Takes a string

        // To not get a NullPointerException we want to handle the buttons differently if it's the final page
        if (page.isFinalPage()) {
            // Change the views visibility with setVisibility()
            choice1Button.setVisibility(View.INVISIBLE);
            choice2Button.setText(R.string.play_again_button_text);
            choice2Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Go back to the main activity
                    // finish();
                    loadPage(0);
                }
            });
        }
        else {
            loadButtons(page);
        }
    }

    private void loadButtons(final Page page) {
        choice1Button.setVisibility(View.VISIBLE);
        choice1Button.setText(page.getChoice1().getTextId()); // Takes the id of a string resource
        choice1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextPage = page.getChoice1().getNextPage();
                loadPage(nextPage);
            }
        });

        choice2Button.setVisibility(View.VISIBLE);
        choice2Button.setText(page.getChoice2().getTextId());
        choice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextPage = page.getChoice2().getNextPage();
                loadPage(nextPage);
            }
        });
    }

    @Override
    public void onBackPressed() {
        pageStack.pop();
        // Go back to main activity is stack is empty
        if (pageStack.isEmpty()) {
            super.onBackPressed();
            Toast.makeText(this, "You pressed the back button", Toast.LENGTH_SHORT).show();
        }
        else {
            loadPage(pageStack.pop());
            Toast.makeText(this, "You pressed the back button", Toast.LENGTH_SHORT).show();
        }
        // The stack:
        // page 1
        // page 0
        // remove page 1 from stack, then load the page 0 and pop it off at the same time
        // it then gets pushed in with the loadPage method
    }
}
