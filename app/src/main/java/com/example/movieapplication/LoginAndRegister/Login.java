package com.example.movieapplication.LoginAndRegister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Login extends AppCompatActivity {

    EditText email;
    EditText password;
    private Button login;

    private FirebaseAuth lAuth;
    SharedPreferences s;
    String txt_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.email);
        password=findViewById(R.id.Password);
        login=findViewById(R.id.login);

        lAuth=FirebaseAuth.getInstance();
        s=getSharedPreferences("Login Details", Context.MODE_PRIVATE);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Extraction of Data
                String txt_email=email.getText().toString();
                String txt_password=password.getText().toString();

                if(TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_password)){
                    Toast.makeText(Login.this,"Please enter Email and Password",Toast.LENGTH_SHORT).show();
                }

                else{
                    SharedPreferences.Editor edit=s.edit();
                    edit.putString("User",txt_email);
                    edit.commit();
                    loginUser(txt_email,txt_password);
                }
            }
        });
    }

    private void loginUser(String email, String password) {


        lAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(Login.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    Toast.makeText(Login.this, "Login Successful!!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, MenupageActivity.class));
                    finish();
                }
                else{

                    Toast.makeText(Login.this,"Invalid Credential!!",Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}

