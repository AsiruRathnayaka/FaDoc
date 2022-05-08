package com.example.fadoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_2_Activity extends AppCompatActivity {

    private Button mReg;




    private FirebaseAuth mAuth;
    private EditText mName;
    private EditText mMail;
    private EditText mpw;
    private EditText mStretch;
    private EditText mCity;
    private EditText mConnu;

    private FirebaseAuth auth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login2);

        mAuth = FirebaseAuth.getInstance();



        mName = findViewById(R.id.Name);
        mMail = findViewById(R.id.mail);
        mpw = findViewById(R.id.pw);
        mStretch = findViewById(R.id.stretch);
        mCity = findViewById(R.id.city);
        mConnu = findViewById(R.id.connu);

        mReg = findViewById(R.id.reg);

        auth = FirebaseAuth.getInstance();



        mReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txt_email = mMail.getText().toString();
                String txt_pw = mpw.getText().toString();

                  if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_pw)){
                      Toast.makeText(Login_2_Activity.this, "week", Toast.LENGTH_SHORT).show();
                      
                  }else if (txt_pw.length()<6){
                      Toast.makeText(Login_2_Activity.this, "week", Toast.LENGTH_SHORT).show();
                  }else {
                      registerUser(txt_email ,txt_pw);
                  }

            }
        });




        /*String name =  mName.getText().toString().trim();
        String mail =  mMail.getText().toString().trim();
        String pw =  mpw.getText().toString().trim();
        String stretch =  mStretch.getText().toString().trim();
        String city =  mCity.getText().toString().trim();
        String con =  mConnu.getText().toString().trim();

         if (name.isEmpty()){

             mName.setError("Name is reqired");
             mName.requestFocus();
             return;
         }

        if (mail.isEmpty()){

            mMail.setError("mail");
            mMail.requestFocus();
            return;
        }

        if (pw.isEmpty()){

            mMail.setError("pw");
            mMail.requestFocus();
            return;
        }


        if (stretch.isEmpty()){

            mStretch.setError("Stretch");
            mStretch.requestFocus();
            return;
        }

        if (city.isEmpty()){

            mCity.setError("City");
            mCity.requestFocus();
            return;
        }

        if (con.isEmpty()){

            mConnu.setError("Connu");
            mConnu.requestFocus();
            return;
        }
*/






    }

    private void registerUser(String email, String pw) {

        auth.createUserWithEmailAndPassword(email,pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Login_2_Activity.this, "successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login_2_Activity.this, Vregister.class);
                    startActivity(i);
                }else {
                    Toast.makeText(Login_2_Activity.this, "unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}