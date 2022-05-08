package com.example.petex;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class UpdatePets extends AppCompatActivity {

    EditText pName;
    EditText pHeight;
    EditText pWeight;
    EditText pGen;

    Button Update_btn;

    private String key;
    private String pname;
    private String pheight;
    private String pweight;
    private String pgen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petupdate);
        getSupportActionBar().hide();

        key=getIntent().getStringExtra("key");
        pname =getIntent().getStringExtra("name");
        pheight =getIntent().getStringExtra("height");
        pweight =getIntent().getStringExtra("weight");
        pgen =getIntent().getStringExtra("gender");


        pName =(EditText)findViewById(R.id.name);
        pName.setText(pname);
        pHeight =(EditText)findViewById(R.id.height);
        pHeight.setText(pheight);
        pWeight =(EditText)findViewById(R.id.weight);
        pWeight.setText(pweight);
        pGen =(EditText)findViewById(R.id.gender);
        pGen.setText(pgen);

        Update_btn=(Button)findViewById(R.id.bt_upitme_update);

        Update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PetModel pets=new PetModel();
                pets.setName(pName.getText().toString());
                pets.setWeight(pHeight.getText().toString());
                pets.setGender(pWeight.getText().toString());
                pets.setGender(pGen.getText().toString());

                new com.example.petex.ItemHelper().deleteItems(key, pets, new com.example.petex.ItemHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<PetModel> pets, List<String> stringList) {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(UpdatePets.this,"Pet Deleted Successfully", Toast.LENGTH_LONG).show();
                        finish();return;

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });




    }




    //Ignore this for test purpose
    public void updatePet(View view) {

    }
}
