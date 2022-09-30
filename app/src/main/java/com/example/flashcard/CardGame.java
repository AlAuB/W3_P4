package com.example.flashcard;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class CardGame extends AppCompatActivity {

    TextView num1, num2, sign;
    EditText input;
    Button generator, submit, restart;
    Random rand;
    int correct, counter;
    boolean isClickable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_game);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        sign = findViewById(R.id.sign);
        input = findViewById(R.id.input);
        generator = findViewById(R.id.generator);
        submit = findViewById(R.id.submit_result);
        restart = findViewById(R.id.restart);
        rand = new Random();
        correct = 0;
        counter = 0;
        isClickable = false;

        generator.setOnClickListener(view -> {
            update();
            generator.setEnabled(false);
            isClickable = false;
        });

        submit.setOnClickListener(view -> {
            try {
                if (num1.getText().toString().length() > 0 &&
                        num2.getText().toString().length() > 0 &&
                        input.getText().toString().length() > 0) {
                    int result = calculate();
                    int ans = Integer.parseInt(input.getText().toString());
                    counter += 1;
                    if (result == ans) correct += 1;
                    if (counter == 10) {
                        Toast.makeText(CardGame.this,
                                correct + " out of 10",
                                Toast.LENGTH_LONG).show();
                        reset();
                    } else {
                        update();
                    }
                    input.setText("");
                }
            } catch (Exception ignored) {
            }
        });

        restart.setOnClickListener(view -> {
            reset();
            Toast.makeText(CardGame.this,
                    "Game Restart",
                    Toast.LENGTH_LONG).show();
        });
    }

    private void reset() {
        counter = 0;
        correct = 0;
        num1.setText("");
        num2.setText("");
        sign.setText("");
        input.setText("");
        generator.setEnabled(true);
        isClickable = true;
    }

    private int randomNum(int max) {
        return (int) Math.floor(Math.random() * (max - 1 + 1) + 1);
    }

    private String randomSign() {
        int temp = rand.nextInt(2);
        if (temp == 0)
            return "-";
        return "+";
    }

    private void update() {
        num1.setText(String.valueOf(randomNum(99)));
        num2.setText(String.valueOf(randomNum(20)));
        sign.setText(randomSign());
    }

    private int calculate() {
        int first = Integer.parseInt(num1.getText().toString());
        int second = Integer.parseInt(num2.getText().toString());
        String op = sign.getText().toString();
        int result = 0;
        switch (op) {
            case "+":
                result = first + second;
                break;
            case "-":
                result = first - second;
                break;
        }
        return result;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        outState.putString("num1", num1.getText().toString());
        outState.putString("num2", num2.getText().toString());
        outState.putString("sign", sign.getText().toString());
        outState.putString("input", input.getText().toString());
        outState.putInt("correct", correct);
        outState.putInt("count", counter);
        outState.putBoolean("clickable", isClickable);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        num1.setText(savedInstanceState.getString("num1"));
        num2.setText(savedInstanceState.getString("num2"));
        sign.setText(savedInstanceState.getString("sign"));
        input.setText(savedInstanceState.getString("input"));
        generator.setEnabled(savedInstanceState.getBoolean("clickable"));
        correct = savedInstanceState.getInt("correct");
        counter = savedInstanceState.getInt("count");
    }
}