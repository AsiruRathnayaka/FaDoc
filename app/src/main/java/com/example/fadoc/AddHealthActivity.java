package com.example.fadoc;

import static com.example.fadoc.R.id.textinput_error;
import static com.example.fadoc.R.id.weight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddHealthActivity extends AppCompatActivity {
    //variable declare
    private EditText weight,height,ongoing,allergics,bmi,membername;
    private Button addhealthbtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String memberID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_health);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        ongoing = findViewById(R.id.ongoing);
        allergics = findViewById(R.id.allergics);
        bmi = findViewById(R.id.bmi);
        addhealthbtn= findViewById(R.id.addhealthbtn);
        membername = findViewById(R.id.membername);
        //databaseconnection
firebaseDatabase = FirebaseDatabase.getInstance();
databaseReference =firebaseDatabase.getReference("MemberHealth");

addhealthbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String weight1 = weight.getText().toString();
        String height1 = height.getText().toString();
        String ongoing1 = ongoing.getText().toString();
        String bmi1 = bmi.getText().toString();
        String membername1 = membername.getText().toString();
        String allergics1 = allergics.getText().toString();
        memberID = membername1;
        HealthRVModal healthRVModal = new HealthRVModal(memberID,membername1,weight1, height1,ongoing1,bmi1,allergics1);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                databaseReference.child(memberID).setValue(healthRVModal);
                Toast.makeText(AddHealthActivity.this,"details added",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddHealthActivity.this,Health.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
Toast.makeText(AddHealthActivity.this,"error is "+error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
});
    }
}












