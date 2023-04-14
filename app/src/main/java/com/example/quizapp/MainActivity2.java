package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.QandA;
import com.example.quizapp.R;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{

    TextView mText2;
    ProgressBar bBar;
    TextView rQuestions;
    TextView qQuestions;
    Button choiceA, choiceB, choiceC;
    Button nextQ;
    //Variables

    int score= 0;
    int totalQuestions= QandA.questions.length;
    int qLeft= QandA.questions.length;
    int currentQuestion= 0;
    String answerSelected = "";
    int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mText2= findViewById(R.id.textView3);
        rQuestions= findViewById(R.id.textView4);
        qQuestions= findViewById(R.id.textView5);
        choiceA= findViewById(R.id.button2);
        choiceB= findViewById(R.id.button3);
        choiceC= findViewById(R.id.button4);
        nextQ= findViewById(R.id.button5);
        bBar= findViewById(R.id.progressBar);
        //Getting value from other activity
        String userName = getIntent().getStringExtra("USER_NAME");
        mText2.setText("Welcome " +userName);
        //Listeners
        choiceA.setOnClickListener(this);
        choiceB.setOnClickListener(this);
        choiceC.setOnClickListener(this);
        nextQ.setOnClickListener(this);
        //Questions remaining
        rQuestions.setText("Total questions :" + totalQuestions +"/" + qLeft);

        nextQuestion();





    }

    @Override
    public void onClick(View view) {
        //Button colors
        choiceA.setBackgroundColor(Color.parseColor("#95B898"));
        choiceB.setBackgroundColor(Color.parseColor("#95B898"));
        choiceC.setBackgroundColor(Color.parseColor("#95B898"));
        Button clickedB = (Button) view;
        clickedB.setBackgroundColor(Color.YELLOW);

        // Check answer when Next button is clicked
        if (clickedB.getId() == R.id.button5) {
            i++;
            // Check if answer is correct
            boolean isCorrect = answerSelected.equals(QandA.answers[currentQuestion]);
            boolean isIncorrect = answerSelected!=(QandA.answers[currentQuestion]);
            if (isCorrect ) {

                if (answerSelected.equals(choiceA.getText().toString())&& answerSelected==(QandA.answers[currentQuestion])) {
                    choiceA.setBackgroundColor(Color.GREEN);
                } else if (answerSelected.equals(choiceB.getText().toString())&& answerSelected==(QandA.answers[currentQuestion])) {
                    choiceB.setBackgroundColor(Color.GREEN);
                } else if (answerSelected.equals(choiceC.getText().toString())&& answerSelected==(QandA.answers[currentQuestion])) {
                    choiceC.setBackgroundColor(Color.GREEN);
                }
                //upon double click add score
                if (i == 1){
                    score ++;
                }

            } else {
                // Find the correct answer button and set its background color
                if (choiceA.getText().toString().equals(QandA.answers[currentQuestion])) {
                    choiceA.setBackgroundColor(Color.GREEN);
                } else if (choiceB.getText().toString().equals(QandA.answers[currentQuestion])) {
                    choiceB.setBackgroundColor(Color.GREEN);
                } else if (choiceC.getText().toString().equals(QandA.answers[currentQuestion])) {
                    choiceC.setBackgroundColor(Color.GREEN);
                }
                if (isIncorrect) {
                    clickedB.setBackgroundColor(Color.RED);
                    if (answerSelected.equals(choiceA.getText().toString())&& answerSelected!=(QandA.answers[currentQuestion])) {
                        choiceA.setBackgroundColor(Color.RED);
                    } else if (answerSelected.equals(choiceB.getText().toString())&& answerSelected!=(QandA.answers[currentQuestion])) {
                        choiceB.setBackgroundColor(Color.RED);
                    } else if (answerSelected.equals(choiceC.getText().toString())&& answerSelected!=(QandA.answers[currentQuestion])) {
                        choiceC.setBackgroundColor(Color.RED);
                    }

                }




            }
            //double click function for answers


            if (i==2){
                i=0;


                currentQuestion ++;
                qLeft --;
                bBar.setProgress(currentQuestion);
                //resetting background to blue
                choiceA.setBackgroundColor(Color.parseColor("#95B898"));
                choiceB.setBackgroundColor(Color.parseColor("#95B898"));
                choiceC.setBackgroundColor(Color.parseColor("#95B898"));

                nextQuestion();

            }


            rQuestions.setText("Total questions :" + qLeft +"/" + totalQuestions);




        }else {
            //compare option selected to answer
            answerSelected = clickedB.getText().toString();
            clickedB.setBackgroundColor(Color.YELLOW);
        }

    }
//function for not ending
    void nextQuestion(){

        if (currentQuestion == totalQuestions){

            endQuiz();
            return;
        }
        //Setting question in text view with index
        qQuestions.setText(QandA.questions[currentQuestion]);
        choiceA.setText(QandA.choices[currentQuestion][0]);
        choiceB.setText(QandA.choices[currentQuestion][1]);
        choiceC.setText(QandA.choices[currentQuestion][2]);
    }
//quiz end dialog and score
    void endQuiz(){
        new AlertDialog.Builder(this)
                .setMessage("Score of "+ score +" out of "+ totalQuestions)
                .setPositiveButton("Restart", (dialogInterface, i) -> restartQuiz())
                .setNegativeButton("Finish Quiz", (dialogInterface, i) -> closeQuiz())
                .setCancelable(false)
                .show();

    }

    void restartQuiz(){
        System.exit(0);


    }
    //exit quiz
    void closeQuiz(){
        ((Activity) this).finishAffinity();
        System.exit(0);

    }
}