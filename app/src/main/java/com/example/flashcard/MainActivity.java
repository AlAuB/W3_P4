package com.example.flashcard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button submit;
    ArrayList<String> users, pass;
    String[] userName = {"Alex", "Lesley", "Louis", "Nick", "Tiffany"};
    String[] passWord = {"12", "34", "56", "78", "90"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.user);
        password = findViewById(R.id.password);
        submit = findViewById(R.id.submit);
        users = new ArrayList<>(Arrays.asList(userName));
        pass = new ArrayList<>(Arrays.asList(passWord));

        submit.setOnClickListener(view -> {
            String tempName = username.getText().toString().trim();
            String tempPass = password.getText().toString().trim();
            int passIndex;
            if (users.contains(tempName) && pass.contains(tempPass)) {
                passIndex = users.indexOf(tempName);
                if (tempPass.equals(pass.get(passIndex))) {
                    Toast.makeText(MainActivity.this,
                            "Welcome " + tempName,
                            Toast.LENGTH_LONG).show();
                    successfulLogIn();
                }
                else {
                    Toast.makeText(MainActivity.this,
                            "Username or Password is incorrect!",
                            Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(MainActivity.this,
                        "Username or Password is incorrect!",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void successfulLogIn() {
        Intent intent = new Intent(this, CardGame.class);
        startActivity(intent);
    }
}