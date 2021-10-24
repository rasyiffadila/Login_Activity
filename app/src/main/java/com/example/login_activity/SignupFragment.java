package com.example.login_activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragment extends Fragment {
    Button btnSignup;
    EditText username_text, password_text, passwordconf_text, email_text;
    DatabaseHelper db;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
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
        View v = inflater.inflate(R.layout.fragment_signup, container, false);
        db = new DatabaseHelper(getActivity());
        username_text = (EditText) v.findViewById(R.id.usernameText);
        password_text = (EditText) v.findViewById(R.id.passwordText);
        email_text = (EditText) v.findViewById(R.id.emailText);
        passwordconf_text = (EditText) v.findViewById(R.id.password_confText);
        btnSignup = (Button) v.findViewById(R.id.signUpbtn);
        btnSignup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String strUsername = username_text.getText().toString();
                String strPassword = password_text.getText().toString();
                String strEmail = email_text.getText().toString();
                String strPasswordconf = passwordconf_text.getText().toString();
                if (strUsername.equals("") || strPassword.equals("") || strPasswordconf.equals("") || strEmail.equals("")){
                    Toast.makeText(getActivity().getApplicationContext(), "Data yang diinput harus lengkap!", Toast.LENGTH_SHORT).show();
                }else {
                    if (strUsername.contains(" ")){
                        Toast.makeText(getActivity().getApplicationContext(), "Username tidak boleh ada spasi!", Toast.LENGTH_SHORT).show();
                    }
                    else if (!strEmail.contains("@") || !strEmail.contains(".")){
                        Toast.makeText(getActivity().getApplicationContext(), "Email tidak memenuhi syarat", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (strPassword.equals(strPasswordconf)){
                            Boolean register = db.Register(strUsername, strPassword, strEmail);
                            if (register == true){
                                Toast.makeText(getActivity().getApplicationContext(), "Berhasil register", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getActivity().getApplicationContext(), "Username sudah terpakai!", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(getActivity().getApplicationContext(), "Konfirmasi password salah!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        return v;
    }
}