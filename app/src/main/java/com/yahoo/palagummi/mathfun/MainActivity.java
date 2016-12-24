package com.yahoo.palagummi.mathfun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the startButton
        startButton = (Button) findViewById(R.id.startButton);
        // get the answerButtons
        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);

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
        // set the randomnly generated values ot each of the answerButtons
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
}
