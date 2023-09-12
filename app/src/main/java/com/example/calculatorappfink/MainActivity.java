package com.example.calculatorappfink;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void addNum(View v){
        TextView answerBox = findViewById(R.id.answerBox);
        if(numberType.equals("first")) {
            firstNumber += v.getTransitionName();
            answerBox.setText(answerBox.getText() + v.getTransitionName());
        }
        if(numberType.equals("second")){
            secondNumber += v.getTransitionName();
            answerBox.setText(answerBox.getText() + v.getTransitionName());
        }
    }
    public void addOperator(View v){
        TextView answerBox = findViewById(R.id.answerBox);
        if(operator.equals("")){
            operator = v.getTransitionName();
            numberType = "second";
            answerBox.setText(answerBox.getText()+" "+ operator + " ");
        }
    }
    public void displayResult(View v){
        double result = 0;
        TextView answerBox = findViewById(R.id.answerBox);
        if(!firstNumber.isEmpty() && !secondNumber.isEmpty() && !operator.isEmpty()) {
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
            firstNumber = ""+result;
            secondNumber = "";
            operator = "";
            numberType = "first";
            answerBox.setText("" + result);
        }
        else{
            answerBox.setText("");
            firstNumber = "";
            secondNumber = "";
            operator = "";
            numberType = "first";
        }
    }

    public void clearAll(View v){
        TextView answerBox = findViewById(R.id.answerBox);
        firstNumber = "";
        secondNumber = "";
        operator = "";
        numberType = "first";
        answerBox.setText("");
    }

    public void piGame(View v){
        String pi = "3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211706798214";
        String userInput = "3.";
        piGame = true;
    }
}