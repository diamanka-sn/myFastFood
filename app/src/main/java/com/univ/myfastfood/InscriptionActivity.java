package com.univ.myfastfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InscriptionActivity extends AppCompatActivity {
    private Button btnHaveAccount, btnSignUp;
    private String telephone, login, password, confirmPassword, prenom, nom;
    private EditText txtFirstName, txtLastName, txtTelephone, txtLogin, txtPassword, txtConfirm;
    private DbRestaurant db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        btnHaveAccount = findViewById(R.id.btnHaveAccount);
        btnSignUp = findViewById(R.id.btnSignUp);

        //Edit Text
        txtFirstName = findViewById(R.id.txtFirstName);
        txtLastName = findViewById(R.id.txtLastName);
        txtTelephone = findViewById(R.id.txtTelephone);
        txtLogin = findViewById(R.id.txtLogin);
        txtPassword = findViewById(R.id.txtPassword);
        txtConfirm = findViewById(R.id.txtConfirm);

        db = new DbRestaurant(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValiderChamp validerChamp = new ValiderChamp();

                login = txtLogin.getText().toString().trim();
                password = txtPassword.getText().toString().trim();
                confirmPassword = txtConfirm.getText().toString().trim();
                prenom = txtFirstName.getText().toString().trim();
                nom = txtLastName.getText().toString().trim();
                telephone = txtTelephone.getText().toString().trim();

                if (login.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || telephone.isEmpty() || prenom.isEmpty() || nom.isEmpty()) {
                    String message = getString(R.string.error_fields);
                    Toast.makeText(InscriptionActivity.this, message, Toast.LENGTH_SHORT).show();
                } else if (!validerChamp.validerMail(login)) {
                    String message = getString(R.string.error_email);
                    Toast.makeText(InscriptionActivity.this, message, Toast.LENGTH_SHORT).show();
                } else if (!validerChamp.validerTelephone(telephone)) {
                    String message = getString(R.string.error_telephone_format);
                    Toast.makeText(InscriptionActivity.this, message, Toast.LENGTH_SHORT).show();
                } else if (!validerChamp.verifNom(prenom)) {
                    String message = getString(R.string.error_firstname_format);
                    Toast.makeText(InscriptionActivity.this, message, Toast.LENGTH_SHORT).show();
                } else if (!validerChamp.verifNom(nom)) {
                    String message = getString(R.string.error_lastname_format);
                    Toast.makeText(InscriptionActivity.this, message, Toast.LENGTH_SHORT).show();
                } else if (!validerChamp.validerMotPasse(password)) {
                    String message = getString(R.string.error_password_format);
                    Toast.makeText(InscriptionActivity.this, message, Toast.LENGTH_SHORT).show();
                } else if (!confirmPassword.equals(password)) {
                    String message = getString(R.string.error_password);
                    Toast.makeText(InscriptionActivity.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    int tel = Integer.parseInt(telephone);
                    boolean inscrire = db.addUser(prenom, nom,tel,login,password,false);
                    if(inscrire){
                        Toast.makeText(InscriptionActivity.this, "reussie", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(InscriptionActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                       // String message = getString(R.string.error_password);
                        Toast.makeText(InscriptionActivity.this, getString(R.string.error_fields), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        btnHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InscriptionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}