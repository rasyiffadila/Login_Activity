package com.example.login_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPass extends AppCompatActivity {
    Button changePassbtn;
    EditText username_text, email_text, newpassword_text;
    DatabaseHelper db;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        db = new DatabaseHelper(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Forgot Password");
        setSupportActionBar(toolbar);
        username_text = (EditText) findViewById(R.id.usernameText);
        email_text = (EditText) findViewById(R.id.emailText);
        newpassword_text = (EditText) findViewById(R.id.newpasswordText);
        changePassbtn = (Button) findViewById(R.id.forgotPassbtn);
        changePassbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String strUsername = username_text.getText().toString();
                String strEmail = email_text.getText().toString();
                String strNewpassword = newpassword_text.getText().toString();
                if (strUsername.equals("") || strEmail.equals("") || strNewpassword.equals("")){
                    Toast.makeText(getApplicationContext(), "Data yang diinput harus lengkap!", Toast.LENGTH_SHORT).show();
                }else {
                    if (strUsername.contains(" ")){
                        Toast.makeText(getApplicationContext(), "Username tidak boleh ada spasi", Toast.LENGTH_SHORT).show();
                    }
                    else if (!strEmail.contains("@") || !strEmail.contains(".")){
                        Toast.makeText(getApplicationContext(), "Email tidak memenuhi syarat", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Boolean forgotPass = db.forgotChangepass(strUsername, strEmail, strNewpassword);
                        if (forgotPass == true){
                            Toast.makeText(getApplicationContext(), "Berhasil ganti password", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Akun tidak ditemukan!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}