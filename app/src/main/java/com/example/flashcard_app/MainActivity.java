package com.example.flashcard_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize the database variable
        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        //read the data from the database
        allFlashcards = flashcardDatabase.getAllCards();

        //var that indicate the answer and question by their id
        TextView flashcardAnswer = findViewById(R.id.flashcard_answer);
        TextView flashcardQuestion = findViewById(R.id.flashcard_question);

        //var that indicate the choices
        TextView firstAnswer = findViewById(R.id.first_answer);
        TextView secondAnswer = findViewById(R.id.second_answer);
        TextView thirdAnswer = findViewById(R.id.third_answer);

        //initially set the answers to be invisible
        firstAnswer.setVisibility(View.INVISIBLE);
        secondAnswer.setVisibility(View.INVISIBLE);
        thirdAnswer.setVisibility(View.INVISIBLE);

        //if the database is not empty and its size is greater than 1, set the question and answers
        //to be the first entry otherwise the default question and answer will be displayed
        if(allFlashcards != null && allFlashcards.size() > 0) {
            flashcardQuestion.setText(allFlashcards.get(0).getQuestion());
            flashcardAnswer.setText(allFlashcards.get(0).getAnswer());
            firstAnswer.setText(allFlashcards.get(0).getAnswer());
            secondAnswer.setText(allFlashcards.get(0).getWrongAnswer1());
            thirdAnswer.setText(allFlashcards.get(0).getWrongAnswer2());
        }

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
        //var for show card icon, hide card
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
        //var for the add  icons
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
         //var for the edit icon
        ImageView editCardIcon = findViewById(R.id.edit_card_btn);

        //when the edit icon is click, navigate to the add card screen and allow user to edit
        editCardIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the question and answer data
                String editQuestion1 = flashcardQuestion.getText().toString();
                String editAnswer1 = flashcardAnswer.getText().toString();
                String answer1b = secondAnswer.getText().toString();
                String answer1c = thirdAnswer.getText().toString();
                Intent editCard = new Intent(MainActivity.this, AddCardActivity.class);
                //pass data to the AddActivity when we launch it
                editCard.putExtra("question1", editQuestion1);
                editCard.putExtra("answer1", editAnswer1);
                editCard.putExtra("answer1b", answer1b);
                editCard.putExtra("answer1c", answer1c);
                MainActivity.this.startActivityForResult(editCard, 100);
            }
        });
        //next icon
        ImageView nextCardIcon = findViewById(R.id.next_btn);

        //when the next icon is clicked, display the next card with questions and answers
        nextCardIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if there are no cards to go to then don't
                if (allFlashcards.size() ==0){
                    return;
                }
                // advance our pointer index so we can show the next card
                currentCardDisplayedIndex ++;

                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if (currentCardDisplayedIndex == allFlashcards.size()){
                    Snackbar.make(findViewById(R.id.flashcard_question),
                            "You have reached the end of the cards, going back to start",
                            Snackbar.LENGTH_SHORT)
                            .show();
                    currentCardDisplayedIndex = 0;

                }
                // get the data from the database and then the current index
                allFlashcards = flashcardDatabase.getAllCards();
                Flashcard flashcard = allFlashcards.get(currentCardDisplayedIndex);

                // set the question and answer TextViews with data from the database
                flashcardQuestion.setText(flashcard.getQuestion());
                flashcardAnswer.setText(flashcard.getAnswer());
                firstAnswer.setText(flashcard.getAnswer());
                secondAnswer.setText(flashcard.getWrongAnswer1());
                thirdAnswer.setText(flashcard.getWrongAnswer2());

            }
        });
        //button to delete a card
        ImageView deleteIcon = findViewById(R.id.delete_btn);

        // when the deleted button is clicked, delete the questions and answers
        // note that we only need to question  and everything is deletes
        // this is enabled in the delete in flashcardSDatabase.java
        deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardDatabase.deleteCard(flashcardQuestion.getText().toString());
                //update the database/list of cards
                allFlashcards = flashcardDatabase.getAllCards();

                //if the current index is greater than one (there's data), reduce the index and
                //set the card to the previous question and answers
                if (currentCardDisplayedIndex >0){
                    currentCardDisplayedIndex --;

                    //get the index of the previous flashcard
                    Flashcard prevCard = allFlashcards.get(currentCardDisplayedIndex);

                    // set the question and answer TextViews with data from the previous flashcard
                    flashcardQuestion.setText(prevCard.getQuestion());
                    flashcardAnswer.setText(prevCard.getAnswer());
                    firstAnswer.setText(prevCard.getAnswer());
                    secondAnswer.setText(prevCard.getWrongAnswer1());
                    thirdAnswer.setText(prevCard.getWrongAnswer2());
                }
                else{
                    Snackbar.make(findViewById(R.id.flashcard_question),
                            "You have deleted all cards, please add a new card",
                            Snackbar.LENGTH_SHORT)
                            .show();
                }
            }
        });

    }
    //new method for getting the saved results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // this 100 needs to match the 100 used when calling startActivityForResult!
        //also needs to match result_ok initialized in the addcardactivity
        if (requestCode == 100 && data !=null) {
            //get the data that was saved
            String question1 = data.getExtras().getString("question1");
            String answer1 = data.getExtras().getString("answer1");
            String wrongAnswer1 = data.getExtras().getString("answer1b");
            String wrongAnswer2 = data.getExtras().getString("answer1c");
            ((TextView) findViewById(R.id.flashcard_question)).setText(question1);
            ((TextView) findViewById(R.id.flashcard_answer)).setText(answer1);
            ((TextView) findViewById(R.id.first_answer)).setText(answer1);
            ((TextView) findViewById(R.id.second_answer)).setText(wrongAnswer1);
            ((TextView) findViewById(R.id.third_answer)).setText(wrongAnswer2);
            //save the added questions
            flashcardDatabase.insertCard(new Flashcard(question1, answer1, wrongAnswer1, wrongAnswer2));
            //update the var holding the flashcards
            allFlashcards = flashcardDatabase.getAllCards();
            //message to user
            Snackbar.make(findViewById(R.id.flashcard_question),
                    "Flashcard saved successfully",
                    Snackbar.LENGTH_SHORT)
                    .show();
        }

    }
    //var to get an instance of the database
    FlashcardDatabase flashcardDatabase;
    //list to hold all flashcard objects
    List<Flashcard> allFlashcards;
    //var to keep track of index of card being displayed
    int currentCardDisplayedIndex = 0;
}