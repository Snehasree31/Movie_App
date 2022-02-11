package com.example.movieapplication.LoginAndRegister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movieapplication.MenupageActivity;
import com.example.movieapplication.MovieDisplayActivity;
import com.example.movieapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private EditText email;
    private EditText Password;
    private Button register;
    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email=findViewById(R.id.email);
        Password=findViewById(R.id.Password);
        register=findViewById(R.id.register);

        Auth=FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_Email =email.getText().toString();
                String txt_password=Password.getText().toString();

                if(TextUtils.isEmpty(txt_Email)||TextUtils.isEmpty(txt_password)){
                    Toast.makeText(Register.this,"Invalid Credential",Toast.LENGTH_SHORT).show();
                }

                else{
                    registerUser(txt_Email,txt_password);
                }
            }
            });
        }

    private void registerUser(String email, String password) {
        Auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(Register.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Register.this,"User Registered Successfully!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this, MenupageActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(Register.this,"Registration Failed, Try Again!!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
   }
