package com.example.petex;

import android.os.Bundle;
import android.view.View;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class AddedPets extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petsview);
        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerView_Item);


        new ItemHelper().readItems(new ItemHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<PetModel> pets, List<String> keys) {
                findViewById(R.id.Loading).setVisibility(View.GONE);
                new RecyclerViewPets().setConfig(mRecyclerView, AddedPets.this, pets,keys);
            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}
