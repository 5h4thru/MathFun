package com.yahoo.palagummi.mathfun;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton = null;
    TextView timerTextView = null;
    TextView sumTextView = null;
    TextView pointsTextView = null;
    TextView resultTextView = null;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;

    // answerButtons
    Button button0;
    Button button1;
    Button button2;
    Button button3;

    // variables to keep track of scores
    int score = 0;
    int numberOfQuestions = 0;

    // startButton onClick
    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
    }


    // answerButtons onClick
    public void chooseAnswer(View view) {
        // get the tag of the button tapped
        if(view.getTag().toString().equalsIgnoreCase(Integer.toString(locationOfCorrectAnswer))) {
            // add to score and generate a new question
            score++;
            resultTextView.setText("Correct");
        } else {
            // do not add to score but generate a new question
            resultTextView.setText("Wrong");
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + " / " + Integer.toString(numberOfQuestions));
        generateQuestion();
    }


    // generateQuestion helper method
    public void generateQuestion() {
        // declare two numbers to represent the sum
        int firstNum;
        int secondNum;
        Random rand = new Random(); // helps to generate the random numbers for the sum 0-20
        firstNum = rand.nextInt(21);
        secondNum = rand.nextInt(21);

        // get the sumTextView
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        sumTextView.setText(Integer.toString(firstNum) + " + " + Integer.toString(secondNum));

        // generate a random number 0-4 to position the correct answer
        locationOfCorrectAnswer = rand.nextInt(4);
        int correctAnswer = firstNum + secondNum;
        int incorrectAnswer;
        // clear the answers ArrayList
        answers.clear();
        for (int i=0; i<4; i++) {
            if(i == locationOfCorrectAnswer) {
                answers.add(correctAnswer);
            } else {
                incorrectAnswer = rand.nextInt(41);
                while(incorrectAnswer == correctAnswer) { // a little quirk to get around getting the random number to be correctAnswer
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        // set the randomly generated values ot each of the answerButtons
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the startButton
        startButton = (Button) findViewById(R.id.startButton);
        // get the answerButtons
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        // get the resultTextView, pointsTextView
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);

        // generate first question
        generateQuestion();

        // countDownTimer for 30 seconds
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        new CountDownTimer(3100, 1000){
            @Override
            public void onTick(long l) {
                long timeLeft = l / 1000;
                String timeLeftString = String.valueOf(timeLeft);
                if(timeLeft < 10) {
                    timerTextView.setText("00:0" + timeLeftString);
                } else {
                    timerTextView.setText("00:" + timeLeftString);
                }
            }
            @Override
            public void onFinish() {
                timerTextView.setText("00:00");
                timerTextView.setBackgroundColor(Color.rgb(255,0,0));
                resultTextView.setText("Done!");
            }
        }.start();
    }
}
