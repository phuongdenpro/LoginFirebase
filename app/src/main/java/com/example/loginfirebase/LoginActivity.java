package com.example.loginfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText id_email;
    private EditText id_password;
    private Button btnSignin, btnRe;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_userr);
        mAuth = FirebaseAuth.getInstance();
        id_email = findViewById(R.id.id_email_login);
        id_password = findViewById(R.id.id_password_login);
        btnSignin = findViewById(R.id.btn_signin_signin);
        btnRe = findViewById(R.id.btn_re);

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
        btnRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register() {
        Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
    }



    private void Login() {
        String email, pass;
        email = id_email.getText().toString();
        pass = id_password.getText().toString();
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this,"Vui long nhap email hop le!!!",Toast.LENGTH_SHORT).show();
            return;

        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Vui long nhap password!!!",Toast.LENGTH_SHORT).show();
            return;

        }
        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Dang nhap thanh cong!!",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"Dang nhap khong thanh cong!!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
