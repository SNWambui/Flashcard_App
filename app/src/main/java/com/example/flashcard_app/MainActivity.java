package com.example.flashcard_app;

import androidx.appcompat.app.AppCompatActivity;

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
        }
        );
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
        firstAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstAnswer.setBackgroundColor(getResources().getColor(R.color.my_green, null));

            }
        });
        secondAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstAnswer.setBackgroundColor(getResources().getColor(R.color.my_green, null));
                secondAnswer.setBackgroundColor(getResources().getColor(R.color.my_red, null));

            }
        });
        thirdAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstAnswer.setBackgroundColor(getResources().getColor(R.color.my_green, null));
                thirdAnswer.setBackgroundColor(getResources().getColor(R.color.my_red, null));

            }
        });
        ImageView showIcon = findViewById(R.id.toggle_choices_visibility_hide);
        //ImageView hideIcon = findViewById(R.id.toggle_choices_visibility);

        showIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showIcon.setImageResource(R.drawable.eye_show_icon);
                boolean isShowingAnswers;
            }
        });

    }
}