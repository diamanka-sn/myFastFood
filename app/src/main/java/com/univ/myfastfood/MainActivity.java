package com.univ.myfastfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText txtLogin, txtPassword;
    private Button btnConnect, btnSignUp;
    private String login, password;
    private DbRestaurant db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConnect = findViewById(R.id.btnConnect);
        btnSignUp = findViewById(R.id.btnSignUp);
        txtLogin = findViewById(R.id.txtLogin);
        txtPassword = findViewById(R.id.txtPassword);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InscriptionActivity.class);
                startActivity(intent);
            }
        });
        db = new DbRestaurant(this);
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValiderChamp validerChamp = new ValiderChamp();
                login = txtLogin.getText().toString().trim();
                password = txtPassword.getText().toString().trim();
                if (login.isEmpty() || password.isEmpty()) {
                    String message = getString(R.string.error_fields);
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                } else if (!validerChamp.validerMail(login)) {
                    String message = getString(R.string.error_email);
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                } else if (!validerChamp.validerMotPasse(password)) {
                    String message = getString(R.string.error_password_format);
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    if(login.equals("admin@myfastfood.sn") && password.equals("Azerty1")){
                        Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                        startActivity(intent);
                    } else{
                        boolean b = db.loginUser(login, password);
                        if(b){
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Login ou mot de passe incorrecte", Toast.LENGTH_SHORT).show();
                        }
                    }


                }
            }
        });
    }


}