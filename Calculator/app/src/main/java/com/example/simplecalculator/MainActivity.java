package com.example.simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvInput, tvResult;
    String expressionString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInput = findViewById(R.id.tvInput);
        tvResult = findViewById(R.id.tvResult);

        assignId(R.id.btnC);
        assignId(R.id.btnAC);
        assignId(R.id.btnPercent);
        assignId(R.id.btnDivide);
        assignId(R.id.btnMultiply);
        assignId(R.id.btnSubtract);
        assignId(R.id.btnAdd);
        assignId(R.id.btnEquals);
        assignId(R.id.btnDot);
        assignId(R.id.btn0);
        assignId(R.id.btn00);
        assignId(R.id.btn1);
        assignId(R.id.btn2);
        assignId(R.id.btn3);
        assignId(R.id.btn4);
        assignId(R.id.btn5);
        assignId(R.id.btn6);
        assignId(R.id.btn7);
        assignId(R.id.btn8);
        assignId(R.id.btn9);
    }

    void assignId(int id){
        Button btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        switch(buttonText) {
            case "AC":
                expressionString = "";
                tvResult.setText("0");
                break;
            case "C":
                if (!expressionString.isEmpty()){
                    expressionString = expressionString.substring(0, expressionString.length()-1);
                }
                break;
            case "=":
                Expression expression = new Expression(expressionString);
                String result = String.valueOf(expression.calculate());
                tvResult.setText(result);
                break;
            default:
                expressionString += buttonText;
                break;
        }

        tvInput.setText(expressionString);
    }
}