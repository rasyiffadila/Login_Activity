package com.example.login_activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SigninFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SigninFragment extends Fragment {
    Button btnSignin, btnForgot;
    EditText username_text, password_text;
    DatabaseHelper db;
    SharedPreferences sp;
    MainActivity main;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SigninFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SigninFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SigninFragment newInstance(String param1, String param2) {
        SigninFragment fragment = new SigninFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_signin, container, false);
        db = new DatabaseHelper(getActivity());
        sp = getActivity().getSharedPreferences("user_session", Context.MODE_PRIVATE);
        username_text = (EditText) v.findViewById(R.id.usernameText);
        password_text = (EditText) v.findViewById(R.id.passwordText);
        btnSignin = (Button) v.findViewById(R.id.signInbtn);
        btnForgot = (Button) v.findViewById(R.id.forgot);
        this.btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgotpassIntent = new Intent(getActivity(), ForgotPass.class);
                startActivity(forgotpassIntent);
            }
        });
        this.btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUsername = username_text.getText().toString();
                String strPassword = password_text.getText().toString();
                if (strUsername.equals("") || strPassword.equals("")){
                    Toast.makeText(getActivity().getApplicationContext(), "Data yang diinput harus lengkap!", Toast.LENGTH_SHORT).show();
                }else {
                    if (strUsername.contains(" ")){
                        Toast.makeText(getActivity().getApplicationContext(), "Username tidak boleh ada spasi!", Toast.LENGTH_SHORT).show();
                    }else {
                        Boolean login = db.Login(strUsername, strPassword);
                        if (login == true) {
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("username", strUsername);
                            editor.commit();
                            Toast.makeText(getActivity().getApplicationContext(), "Berhasil login", Toast.LENGTH_SHORT).show();
                            Intent mainIntent = new Intent(getActivity(), DashboardActivity.class);
                            startActivity(mainIntent);
                            getActivity().finish();
                        }else {
                            Toast.makeText(getActivity().getApplicationContext(), "Akun tidak ditemukan!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        return v;
    }

    public Button getBtnSignin() {
        return btnSignin;
    }

    public EditText getUsername_text() {
        return username_text;
    }

    public EditText getPassword_text() {
        return password_text;
    }




//    @Override
//    public void onClick(View view) {
//        String username = username_text.getText().toString();
//        String password = password_text.getText().toString();
//        MainActivity mainActivity = new MainActivity();
//        String username_source = String.valueOf(mainActivity.getUsername());
//        String password_source = String.valueOf(mainActivity.getPassword());
//        Toast.makeText(getActivity(), String.valueOf(mainActivity.getUsername()),Toast.LENGTH_SHORT).show();
////        if (username.equals(username_source) && password.equals(password_source)){
////            Toast.makeText(getActivity(),"Login Berhasil!",Toast.LENGTH_SHORT).show();
////        }
////        else {
////            Toast.makeText(getActivity(), "Akun Tidak Ditemukan!",Toast.LENGTH_SHORT).show();
////        }
//
//    }
}