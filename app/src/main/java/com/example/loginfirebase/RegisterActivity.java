package com.example.loginfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText id_email_register;
    private EditText id_password_register;
    private Button btnRegister,btnLo;
    private TextView txlogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);
        mAuth = FirebaseAuth.getInstance();
        id_email_register = findViewById(R.id.id_mail_register);
        id_password_register = findViewById(R.id.id_password_register);
        btnRegister = findViewById(R.id.btn_register_register);
        txlogin = findViewById(R.id.txLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        txlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }

    private void register() {
        String email, password;
        email = id_email_register.getText().toString();
        password = id_password_register.getText().toString();
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this,"Vui long nhap email hop le!!!",Toast.LENGTH_SHORT).show();
            return;

        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Vui long nhap password!!!",Toast.LENGTH_SHORT).show();
            return;

        }
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Tao tai khoan thanh cong!!!",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(i);
                }else {
                    Toast.makeText(getApplicationContext(),"Tao tai khoan khong thanh cong!!!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
