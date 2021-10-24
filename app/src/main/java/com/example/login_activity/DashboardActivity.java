package com.example.login_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {
    private Toolbar toolbar;
    DatabaseHelper db;
    TextView username_text, email_text;
    Button logout, changePass;
    MainActivity main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Android Login");
        setSupportActionBar(toolbar);
        db = new DatabaseHelper(this);
        logout = (Button) findViewById(R.id.logout);
        changePass = (Button) findViewById(R.id.change_password);
        main = new MainActivity();
        SharedPreferences sp = getApplicationContext().getSharedPreferences("user_session", Context.MODE_PRIVATE);
        if (sp.getString("username", "").equals("")){
            Intent loginIntent = new Intent(DashboardActivity.this, MainActivity.class);
            startActivity(loginIntent);
            finish();
        }
        String s_username = sp.getString("username","");
        Cursor user_data = db.getUserdata(s_username);
        username_text = (TextView) findViewById(R.id.username_text);
        email_text = (TextView) findViewById(R.id.email_text);
        String s_email = user_data.getString(2);
        username_text.setText(s_username);
        email_text.setText(s_email);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(getApplicationContext(), "Berhasil Keluar", Toast.LENGTH_SHORT).show();
                Intent loginIntent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent changepassIntent = new Intent(DashboardActivity.this, ChangePass.class);
                startActivity(changepassIntent);
            }
        });
    }

}