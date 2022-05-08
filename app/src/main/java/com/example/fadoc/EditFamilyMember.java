package com.example.fadoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditFamilyMember extends AppCompatActivity {

    private EditText NameEdt,RelationEdt,DOBEdt,Age, Gender, HandI;
    private Button UpdateBtn , DeleteBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String MemberID;
    private MemberRVModal memberRVModal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_family_member);
        firebaseDatabase =FirebaseDatabase.getInstance();
        NameEdt = findViewById(R.id.idNameEdt);
        RelationEdt = findViewById(R.id.idrelationEdt);
        DOBEdt = findViewById(R.id.idDOBEdt);
        Age = findViewById(R.id.idAgeEdt);
        Gender = findViewById(R.id.idGenderEdt);
        HandI = findViewById(R.id.idHandiEdt);
        UpdateBtn = findViewById(R.id.idUpdateBtn);
        DeleteBtn = findViewById(R.id.idDeleteBtn);
        loadingPB = findViewById(R.id.idloadingPB);

        memberRVModal = getIntent().getParcelableExtra("Member");
        if(memberRVModal!=null){
            NameEdt.setText(memberRVModal.getName());
            RelationEdt.setText(memberRVModal.getRelation());
            DOBEdt.setText(memberRVModal.getDOB());
            Age.setText(memberRVModal.getAge());
            Gender.setText(memberRVModal.getGender());
            HandI.setText(memberRVModal.getHandI());
            MemberID = memberRVModal.getMemberID();


        }


        databaseReference = firebaseDatabase.getReference("Family Members").child(MemberID);
        UpdateBtn.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view){
                loadingPB.setVisibility(View.VISIBLE);
                String Name = NameEdt.getText().toString();
                String Relation = RelationEdt.getText().toString();
                String DOB = DOBEdt.getText().toString();
                String age = Age.getText().toString();
                String gender = Gender.getText().toString();
                String Handi = HandI.getText().toString();


                Map<String,Object> map = new HashMap<>();
                map.put("Name", Name);
                map.put("Relation", Relation);
                map.put("DOB", DOB);
                map.put("age", age);
                map.put("gender", gender);
                map.put("HandI", Handi);
                map.put("memberID", MemberID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.updateChildren(map);
                        Toast.makeText(EditFamilyMember.this, "Updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditFamilyMember.this,MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(EditFamilyMember.this, "Failed to Update" , Toast.LENGTH_SHORT).show();

                    }


                });

            }

        });

        DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMember();

            }
        });


    }

    private void deleteMember(){

        databaseReference.removeValue();
        Toast.makeText(this, "Deleted Successfully" , Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditFamilyMember.this, MainActivity.class));
    }

}