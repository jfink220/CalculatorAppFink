package com.example.calculatorappfink;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String numberType = "first";
    String firstNumber = "";
    String secondNumber = "";
    String operator = "";
    boolean piGame = false;
    String pi = "3.141592653589793238462643383279502884197169399375105820974944592307816406286208998628034825342117";
    String userInput = "3.";
    int timesCalled = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void addNum(View v){
        TextView answerBox = findViewById(R.id.answerBox);
        if(!piGame) {
            if (numberType.equals("first")) {
                firstNumber += v.getTransitionName();
                answerBox.setText(answerBox.getText() + v.getTransitionName());
            }
            if (numberType.equals("second")) {
                secondNumber += v.getTransitionName();
                answerBox.setText(answerBox.getText() + v.getTransitionName());
            }
        }
        else{
            answerBox.setText(userInput + v.getTransitionName());
            userInput += v.getTransitionName();
        }
    }
    public void addOperator(View v){
        TextView answerBox = findViewById(R.id.answerBox);
        if(operator.equals("") && !piGame){
            operator = v.getTransitionName();
            numberType = "second";
            answerBox.setText(answerBox.getText()+" "+ operator + " ");
        }
    }
    public void displayResult(View v){
        double result = 0;
        TextView answerBox = findViewById(R.id.answerBox);
        if(!piGame) {
            if (!firstNumber.isEmpty() && !secondNumber.isEmpty() && !operator.isEmpty()) {
                if (operator.equals("+")) {
                    result = Double.parseDouble(firstNumber) + Double.parseDouble(secondNumber);
                } else if (operator.equals("-")) {
                    result = Double.parseDouble(firstNumber) - Double.parseDouble(secondNumber);
                } else if (operator.equals("*")) {
                    result = Double.parseDouble(firstNumber) * Double.parseDouble(secondNumber);
                } else if (operator.equals("/")) {
                    result = Double.parseDouble(firstNumber) / Double.parseDouble(secondNumber);
                }
                answerBox.setText("");
                firstNumber = "" + result;
                secondNumber = "";
                operator = "";
                numberType = "first";
                answerBox.setText("" + result);
            } else {
                answerBox.setText("");
                firstNumber = "";
                secondNumber = "";
                operator = "";
                numberType = "first";
            }
        }
        else{
            int numCorrect = 0;
            timesCalled++;
            // builds a new SpannableStringBuilder to change the styling of a string
            // https://chat.openai.com/
                SpannableStringBuilder builder = new SpannableStringBuilder(pi);
                for (int i = 2; i < pi.length(); i++) {
                    if (userInput.length() >= i + 1) {
                        if (userInput.substring(i, i + 1).equals(pi.substring(i, i + 1))) {
                            numCorrect++;
                            if(timesCalled == 2) {
                                // sets the styling of the built String to bold at certain intervals
                                // https://chat.openai.com/
                                builder.setSpan(new StyleSpan(Typeface.BOLD), i, i + 1, 0);
                            }
                        }
                    }
                }
            if(timesCalled == 2) {
                answerBox.setText(builder);
                userInput = "3.";
                piGame = false;
                timesCalled = 0;
            }
            else if(timesCalled == 1){
                answerBox.setText("You got " + numCorrect + " correct out of the "
                        + (pi.length() - 2) + " possible measured characters");
            }
        }
    }

    public void clearAll(View v){
        if(!piGame) {
            TextView answerBox = findViewById(R.id.answerBox);
            firstNumber = "";
            secondNumber = "";
            operator = "";
            numberType = "first";
            answerBox.setText("");
        }
    }

    public void piGame(View v){
        TextView answerBox = findViewById(R.id.answerBox);
        piGame = true;
        answerBox.setText(userInput);
    }
}