package com.example.flashcard_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        ImageView cancelIcon = findViewById(R.id.cancel_button);

        //when the cancel button is clicked, nagivate to the Main Activity screen
        cancelIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dismiss the current activity when I click the button
                finish();
            }
        });

        ImageView saveIcon = findViewById(R.id.save_button);
        EditText question1 = (EditText) findViewById(R.id.editQuestionField);
        EditText answer1 = (EditText) findViewById(R.id.editAnswerField);

        //when the save button is clicked, the input is saved and displayed in main activity
        saveIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create a new Intent to put data
                Intent data = new Intent();
                // put the first question and answer into the Intent, with the key as 'question1' and 'answer'
                data.putExtra("question1", question1.getText().toString());
                data.putExtra("answer1", answer1.getText().toString());
                // set result code and bundle data for response
                setResult(RESULT_OK, data);
                // closes this activity and pass data to the main activity that launched this activity
                finish();
            }
        });

    }
}
