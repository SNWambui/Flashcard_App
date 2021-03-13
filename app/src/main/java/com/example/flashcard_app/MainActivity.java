package com.example.flashcard_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView flashcardAnswer = findViewById(R.id.flashcard_answer);
        TextView flashcardQuestion = findViewById(R.id.flashcard_question);
        //when the user clicks on the question, the answer is visible and the question is not
        flashcardQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardAnswer.setVisibility(View.VISIBLE);
                flashcardQuestion.setVisibility(View.INVISIBLE);
            }
        });
        //when the user clicks on the answer, the question is visible and the answer is not
        flashcardAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardAnswer.setVisibility(View.INVISIBLE);
                flashcardQuestion.setVisibility(View.VISIBLE);
            }
        });
        TextView firstAnswer = findViewById(R.id.first_answer);
        TextView secondAnswer = findViewById(R.id.second_answer);
        TextView thirdAnswer = findViewById(R.id.third_answer);

        //when the first answer is clicked, set the color to green
        firstAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstAnswer.setBackgroundColor(getResources().getColor(R.color.my_green, null));

            }
        });
        //when the second answer is clicked, set the correct one as green and this one as red
        secondAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstAnswer.setBackgroundColor(getResources().getColor(R.color.my_green, null));
                secondAnswer.setBackgroundColor(getResources().getColor(R.color.my_red, null));

            }
        });
        //when the third answer is clicked, set the correct one as green and this one as red
        thirdAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstAnswer.setBackgroundColor(getResources().getColor(R.color.my_green, null));
                thirdAnswer.setBackgroundColor(getResources().getColor(R.color.my_red, null));

            }
        });
        ImageView showIcon = findViewById(R.id.toggle_choices_visibility);
        ImageView hideIcon = findViewById(R.id.toggle_choices_visibility_hide);

        //when the show icon is clicked, make the answers visible
        showIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showIcon.setImageResource(R.drawable.eye_show_icon);
                showIcon.setVisibility(View.INVISIBLE);
                hideIcon.setVisibility(View.VISIBLE);
                firstAnswer.setVisibility(View.VISIBLE);
                secondAnswer.setVisibility(View.VISIBLE);
                thirdAnswer.setVisibility(View.VISIBLE);
            }
        });
        //when the hide icon is clicked, make the answers not visible
        hideIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showIcon.setImageResource(R.drawable.eye_show_icon);
                showIcon.setVisibility(View.VISIBLE);
                hideIcon.setVisibility(View.INVISIBLE);
                firstAnswer.setVisibility(View.INVISIBLE);
                secondAnswer.setVisibility(View.INVISIBLE);
                thirdAnswer.setVisibility(View.INVISIBLE);
            }
        });

        ImageView addCardIcon = findViewById(R.id.add_card_btn);

        //when the add card icon is clicked, nagivate to the Add Card screen
        addCardIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addCard = new Intent(MainActivity.this, AddCardActivity.class);
                //indicates that expect a result to be returned
                MainActivity.this.startActivityForResult(addCard, 100);

            }
        });
    }
    //new method for getting the saved results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // this 100 needs to match the 100 used when calling startActivityForResult!
        //also needs to match result_ok initialized in the addcardactivity
        if (requestCode == 100 && data !=null) {
            //get the data that was saved
            String question1 = data.getExtras().getString("question1");
            String answer1 = data.getExtras().getString("answer1");
            ((TextView) findViewById(R.id.flashcard_question)).setText(question1);
            ((TextView) findViewById(R.id.flashcard_answer)).setText(answer1);
            ((TextView) findViewById(R.id.first_answer)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.second_answer)).setVisibility(View.INVISIBLE);
            ((TextView) findViewById(R.id.third_answer)).setVisibility(View.INVISIBLE);
        }
    }
}