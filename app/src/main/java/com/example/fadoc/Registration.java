package com.example.fadoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

public class Registration extends AppCompatActivity {

    private TextInputEditText userNameEdt,EdtPassword,EdtCnPassword;
    private Button BtnSignup;
    private ProgressBar loadingPB;
    private TextView loginTV;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        userNameEdt = findViewById(R.id.idEdtUserName);
        EdtPassword = findViewById(R.id.idEdtPassword);
        EdtCnPassword = findViewById(R.id.idEdtCnPassword);
        BtnSignup = findViewById(R.id.idBtnSignup);
        loadingPB = findViewById(R.id.idloadingPB);
        loginTV = findViewById(R.id.idTVLogin);
        mAuth = FirebaseAuth.getInstance();
        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Registration.this, Login.class);
                startActivity(i);

            }
        });

        BtnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);
                String UserName = userNameEdt.getText().toString();
                String pwd = EdtPassword.getText().toString();
                String cnPwd = EdtCnPassword.getText().toString();
                if (!pwd.equals(cnPwd)){
                    Toast.makeText(Registration.this, "Passwords are not matching", Toast.LENGTH_SHORT).show();

                }else if (TextUtils.isEmpty(UserName) && TextUtils.isEmpty(pwd) && TextUtils.isEmpty(cnPwd)){
                    Toast.makeText(Registration.this, "Please Enter Your Credentials...", Toast.LENGTH_SHORT).show();

                }else{
                    mAuth.createUserWithEmailAndPassword(UserName,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(Registration.this, "Successfully Signed in!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Registration.this, Login.class);
                                startActivity(i);
                                finish();
                            }

                                else{
                                    loadingPB.setVisibility(View.GONE);
                                    Toast.makeText(Registration.this, "Failed To Signin", Toast.LENGTH_SHORT).show();


                            }
                        }
                    });
                }
            }
        });

    }
}