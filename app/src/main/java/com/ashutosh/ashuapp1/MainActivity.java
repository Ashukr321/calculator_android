package com.ashutosh.ashuapp1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvResult;
    private String currentNumber = "";
    private String previousNumber = "";
    private String operation = "";
    private boolean isNewOperation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);

        int[] buttonIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
                R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
                R.id.btn8, R.id.btn9, R.id.btnAdd, R.id.btnSubtract,
                R.id.btnMultiply, R.id.btnDivide, R.id.btnDecimal,
                R.id.btnEqual, R.id.btnClear
        };

        for (int id : buttonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "+":
            case "-":
            case "*":
            case "/":
                handleOperation(buttonText);
                break;
            case "=":
                handleEqual();
                break;
            case ".":
                handleDecimal();
                break;
            case "C":
                handleClear();
                break;
            default:
                handleNumber(buttonText);
                break;
        }
    }

    private void handleNumber(String number) {
        if (isNewOperation) {
            tvResult.setText("");
            isNewOperation = false;
        }
        currentNumber += number;
        tvResult.setText(currentNumber);
    }

    private void handleOperation(String operation) {
        if (!currentNumber.isEmpty()) {
            if (!this.operation.isEmpty()) {
                handleEqual();
            }
            previousNumber = currentNumber;
            currentNumber = "";
            this.operation = operation;
        }
    }

    private void handleEqual() {
        if (!operation.isEmpty() && !currentNumber.isEmpty()) {
            double num1 = Double.parseDouble(previousNumber);
            double num2 = Double.parseDouble(currentNumber);
            double result = 0;

            switch (operation) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        tvResult.setText("Error");
                        return;
                    }
                    break;
            }
            tvResult.setText(String.valueOf(result));
            currentNumber = String.valueOf(result);
            operation = "";
            isNewOperation = true;
        }
    }

    private void handleDecimal() {
        if (!currentNumber.contains(".")) {
            currentNumber += ".";
            tvResult.setText(currentNumber);
        }
    }

    private void handleClear() {
        currentNumber = "";
        previousNumber = "";
        operation = "";
        isNewOperation = true;
        tvResult.setText("0");
    }
}