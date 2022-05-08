package com.example.fadoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private TextInputEditText userNamEdt, pwdEdt;
    private Button LoginBtn;
    private ProgressBar LoadingPB;
    private TextView registerTV;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userNamEdt = findViewById(R.id.idEdtUserName);
        pwdEdt = findViewById(R.id.idEdtPassword);
        LoginBtn = findViewById(R.id.idBtnLogin);
        LoadingPB = findViewById(R.id.idloadingPB);
        registerTV = findViewById(R.id.idTVRegister);
        mAuth = FirebaseAuth.getInstance();

        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Registration.class);
                startActivity(i);

            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = userNamEdt.getText().toString();
                String pwd = pwdEdt.getText().toString();
                if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(pwd)){
                    Toast.makeText(Login.this, "Please Enter Your Credentials", Toast.LENGTH_SHORT).show();
                    return;

                }else {
                    mAuth.signInWithEmailAndPassword(userName,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                LoadingPB.setVisibility(View.GONE);
                                Toast.makeText(Login.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Login.this , MainActivity.class);
                                startActivity(i);
                                finish();
                            }else{
                                LoadingPB.setVisibility(View.GONE);
                                Toast.makeText(Login.this, "Failed To Login", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user!= null){
            Intent i = new Intent(Login.this, MainActivity.class);
            startActivity(i);
            this.finish();
        }
    }
}