package com.example.login_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ChangePass extends AppCompatActivity {
    Button changePassbtn;
    EditText oldPass_text, newPass_text;
    DatabaseHelper db;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Change Password");
        setSupportActionBar(toolbar);
        db = new DatabaseHelper(this);
        SharedPreferences sp = getApplicationContext().getSharedPreferences("user_session", Context.MODE_PRIVATE);
        oldPass_text = (EditText) findViewById(R.id.oldpassText);
        newPass_text = (EditText) findViewById(R.id.newpassText);
        changePassbtn = (Button) findViewById(R.id.changePassbtn);
        changePassbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String strUsername = sp.getString("username","");
                String strOldpass = oldPass_text.getText().toString();
                String strNewpass = newPass_text.getText().toString();
                if (strUsername.equals("") || strOldpass.equals("") || strNewpass.equals("")){
                    Toast.makeText(getApplicationContext(), "Data yang diinput harus lengkap!", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean changePass = db.Changepass(strUsername, strOldpass, strNewpass);
                    if (changePass == true){
                        Toast.makeText(getApplicationContext(), "Berhasil ganti password", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "Password lama salah!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}