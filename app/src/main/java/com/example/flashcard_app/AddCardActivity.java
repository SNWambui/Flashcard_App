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

        //var for the cancel button
        ImageView cancelIcon = findViewById(R.id.cancel_button);

        //when the cancel button is clicked, nagivate to the Main Activity screen
        cancelIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dismiss the current activity when I click the button
                finish();
            }
        });
        //var for save icon, question and answers
        ImageView saveIcon = findViewById(R.id.save_button);
        EditText question1 = (EditText) findViewById(R.id.editQuestionField);
        EditText answer1 = (EditText) findViewById(R.id.editAnswerField);
        EditText wrongAnswer1 = (EditText) findViewById(R.id.editAnswer2Field);
        EditText wrongAnswer2 = (EditText) findViewById(R.id.editAnswer3Field);

        Intent editCard = getIntent();
        //get the data passed from clicking the edit button
        String editQuestion1 = editCard.getStringExtra("question1");
        String editAnswer1 = editCard.getStringExtra("answer1");
        String editWrongAnswer1 = editCard.getStringExtra("answer1b");
        String editWrongAnswer2 = editCard.getStringExtra("answer1c");

        //set the text in the text field to this data
        question1.setText(editQuestion1);
        answer1.setText(editAnswer1);
        wrongAnswer1.setText(editWrongAnswer1);
        wrongAnswer2.setText(editWrongAnswer2);

        //when the save button is clicked, the input is saved and displayed in main activity
        saveIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create a new Intent to put data
                Intent data = new Intent();
                // put the first question and answer into the Intent, with the key as 'question1' and 'answer'
                data.putExtra("question1", question1.getText().toString());
                data.putExtra("answer1", answer1.getText().toString());
                //put the multiple choices into the Intent
                data.putExtra("answer1a", answer1.getText().toString());
                data.putExtra("answer1b", wrongAnswer1.getText().toString());
                data.putExtra("answer1c", wrongAnswer2.getText().toString());
                // set result code and bundle data for response
                setResult(RESULT_OK, data);
                // closes this activity and pass data to the main activity that launched this activity
                finish();
            }
        });



    }
}
