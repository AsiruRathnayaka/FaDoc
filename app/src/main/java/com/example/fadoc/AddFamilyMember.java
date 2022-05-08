package com.example.fadoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddFamilyMember extends AppCompatActivity {

    private EditText NameEdt,RelationEdt,DOBEdt,Age, Gender, HANDI;
    private Button AddMemberBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String MemberID;
    private MemberRVModal memberRVModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_family_member);
        NameEdt = findViewById(R.id.idNameEdt);
        RelationEdt = findViewById(R.id.idrelationEdt);
        DOBEdt = findViewById(R.id.idDOBEdt);
        Age = findViewById(R.id.idAgeEdt);
        Gender = findViewById(R.id.idGenderEdt);
        HANDI = findViewById(R.id.idHandiEdt);
        AddMemberBtn = findViewById(R.id.idAddMemberBtn);
        loadingPB = findViewById(R.id.idloadingPB);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Family Members");

        AddMemberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(View.VISIBLE);
                String Name = NameEdt.getText().toString();
                String Relation = RelationEdt.getText().toString();
                String DOB = DOBEdt.getText().toString();
                String age = Age.getText().toString();
                String gender = Gender.getText().toString();
                String Handi = HANDI.getText().toString();
                MemberID = Name;

                MemberRVModal memberRVModal = new MemberRVModal( Name,Relation, DOB,age,gender,Handi,MemberID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    private Object MemberRVModal;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.child(MemberID).setValue(MemberRVModal);
                        Toast.makeText(AddFamilyMember.this, "Member Added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddFamilyMember.this, MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AddFamilyMember.this, "Error is " +error.toString() , Toast.LENGTH_SHORT).show();
                    }
                });




            }
        });
    }
}