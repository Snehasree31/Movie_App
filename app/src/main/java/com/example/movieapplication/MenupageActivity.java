package com.example.movieapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieapplication.LoginAndRegister.Login;
import com.example.movieapplication.LoginAndRegister.Register;
//import com.example.movieapplication.RoomDatabase.FavAdapter;
import com.example.movieapplication.Retrofit.Movie;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MenupageActivity extends AppCompatActivity {
    private Button shomv;
    TextView getusemail1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupage);

        shomv=findViewById(R.id.shomv);
        getusemail1=findViewById(R.id.getusemail);
        SharedPreferences s= getApplicationContext().getSharedPreferences("Login Details", Context.MODE_PRIVATE);
        String semail=s.getString("User","");
        getusemail1.setText(semail);

        shomv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenupageActivity.this, MovieDisplayActivity.class));
                finish();
            }
        });
    }

    public void openLoginPage(){
        Intent intent=new Intent(this, Login.class);
        startActivity(intent);
    }
    public void openRegister(){
        Intent intent=new Intent(this, Register.class);
        startActivity(intent);
    }


}