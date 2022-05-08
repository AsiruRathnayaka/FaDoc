package com.example.petex;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddPet extends AppCompatActivity {

    private static final  int Gallery_Code=1;
    Uri imageUrl=null;
    ProgressDialog progressDialog;

    EditText petName, petHeight, petWeight, petGender, petvaccination;
    ImageButton imageButton;
    Button petAdd;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage firebaseStorage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("petData");
        firebaseStorage = FirebaseStorage.getInstance();
        progressDialog = new ProgressDialog(this);

        petName = (EditText) findViewById(R.id.petName);
        petHeight = (EditText) findViewById(R.id.peth);
        petWeight = (EditText) findViewById(R.id.petw);
        petGender = (EditText) findViewById(R.id.petg);
        petvaccination = (EditText) findViewById(R.id.petv);
        petAdd = (Button) findViewById(R.id.et_btnF);
        imageButton = (ImageButton) findViewById(R.id.imageButton);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, Gallery_Code);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Gallery_Code && requestCode == RESULT_OK){
            imageUrl= data.getData();
            imageButton.setImageURI(imageUrl);
        }
        petAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pName = petName.getText().toString().trim();
                String pHeight = petHeight.getText().toString().trim();
                String pWeight = petWeight.getText().toString().trim();
                String pGender = petGender.getText().toString().trim();
                String pVac = petvaccination.getText().toString().trim();

                if (!pName.isEmpty()|| !pHeight.isEmpty() || !pWeight.isEmpty()  || !pGender.isEmpty() || imageUrl!=null){
                    progressDialog.setTitle("Uploading....");
                    progressDialog.show();
                    imageUrl= data.getData();

                    StorageReference filepath= firebaseStorage.getReference().child("images").child(imageUrl.getLastPathSegment());
                    filepath.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> downloadUrl=taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String t=task.getResult().toString();
                                    DatabaseReference newPost= databaseReference.push();

                                    newPost.child("name").setValue(pName);
                                    newPost.child("height").setValue(pHeight);
                                    newPost.child("weight").setValue(pWeight);
                                    newPost.child("gender").setValue(pGender);
                                    newPost.child("vaccination").setValue(pVac);
                                    newPost.child("image").setValue(task.getResult().toString());
                                    progressDialog.dismiss();

                                    Intent intent = new Intent(getApplicationContext(), AddedPets.class);
                                    startActivity(intent);

                                }
                            });
                        }
                    });
                }


            }

        });

    }
}
