package com.example.fadoc;

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

import java.util.HashMap;
import java.util.Map;

public class EditHealthActivity extends AppCompatActivity {

    //declare variables
    private EditText weight,height,ongoing,allergics,bmi,membername;
    private Button savehealthbtn,deletehealthbtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String memberID;
    private HealthRVModal healthRVModal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_health);
        firebaseDatabase = FirebaseDatabase.getInstance();

        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        ongoing = findViewById(R.id.ongoing);
        allergics = findViewById(R.id.allergics);
        bmi = findViewById(R.id.bmi);
        membername = findViewById(R.id.membername);
        savehealthbtn= findViewById(R.id.savehealthbtn);
        deletehealthbtn= findViewById(R.id.deletehealthbtn);

        //geting data from previous activity
healthRVModal = getIntent().getParcelableExtra("MemberHealth");
if(healthRVModal != null){

    weight.setText(healthRVModal.getWeight());
    height.setText(healthRVModal.getHeight());
    ongoing.setText(healthRVModal.getOngoing());
    bmi.setText(healthRVModal.getBmi());
    membername.setText(healthRVModal.getMembername());
    allergics.setText(healthRVModal.getAllergics());
    memberID = healthRVModal.getMemberID();
}

        databaseReference = firebaseDatabase.getReference("MemberHealth").child(memberID);

//update details
savehealthbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String weight1 = weight.getText().toString();
                String height1 = height.getText().toString();
                String ongoing1 = ongoing.getText().toString();
                String bmi1 = bmi.getText().toString();
                String membername1 = membername.getText().toString();
                String allergics1 = allergics.getText().toString();

                Map<String,Object> map = new HashMap<>();
                 map.put("weight",weight1);
                 map.put("height",height1);
                 map.put("ongoing",ongoing1);
                 map.put("bmi",bmi1);
                 map.put("allergics",allergics1);
                 map.put("membername",membername1);

          databaseReference.addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot snapshot) {

            databaseReference.updateChildren(map);
                  Toast.makeText(EditHealthActivity.this,"member health details are updated",
                          Toast.LENGTH_SHORT).show();
              startActivity(new Intent(EditHealthActivity.this,Health.class)); //move to health members page
              }

              @Override
              public void onCancelled(@NonNull DatabaseError error) {
Toast.makeText(EditHealthActivity.this,"failed to updated...",Toast.LENGTH_SHORT).show();
              }
          });

            }
        });

//delete part

        deletehealthbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              deleteHealth();
            }
        });
    }
    private void deleteHealth(){

        databaseReference.removeValue();
        Toast.makeText(this,"health details are deleted",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditHealthActivity.this,Health.class));
    }

}