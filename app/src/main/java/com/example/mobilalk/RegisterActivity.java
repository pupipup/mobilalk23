package com.example.mobilalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText userEmail;
    EditText userPassword;
    EditText userPasswordVerify;
    private SharedPreferences preferences;
    private static final String LOG_TAG = RegisterActivity.class.getName();
    private static final String PREF_KEY = MainActivity.class.getPackage().toString();
    private static final int SECRET_KEY = 99;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        int secret_key = getIntent().getIntExtra("SECRET_KEY",0);
        if (secret_key != 99){
            finish();
        }
        userEmail = findViewById(R.id.email);
        userPassword = findViewById(R.id.password);
        userPasswordVerify = findViewById(R.id.passwordverify);
        preferences = getSharedPreferences(PREF_KEY,MODE_PRIVATE);
        String email = preferences.getString("email","");
        String password = preferences.getString("password", "");
        userEmail.setText(email);
        userPassword.setText(password);
        userPasswordVerify.setText(password);
        mAuth = FirebaseAuth.getInstance();

    }

    public void register(View view) {
        String userName = userEmail.getText().toString();
        String password = userPassword.getText().toString();
        String password2 = userPasswordVerify.getText().toString();
        if (!password.equals(password2)){
            Log.e(LOG_TAG, "Nem egyenlő a jelszó és a megerősítése.");
            return;

        }

        mAuth.createUserWithEmailAndPassword(userName,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startCompare();
                }else{
                    Toast.makeText(RegisterActivity.this, "User not created: "+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }


            }
        });



    }
    public void  startCompare(){
        Intent intent = new Intent(this,MainPageActivity.class);
//        intent.putExtra("SECRET_KEY",SECRET_KEY);
        startActivity(intent);
    }

    public void cancel(View view) {
        finish();
    }
}



