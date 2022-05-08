package com.example.petex;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewPets {

    private Context mContext;
    private ItemAdapter petsAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<PetModel> items, List<String> keys){
        mContext=context;
        petsAdapter =new ItemAdapter(items,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(petsAdapter);
    }

    class ItemView extends RecyclerView.ViewHolder{
        FirebaseDatabase firebase;
        DatabaseReference reference;
        private Button delete;
        private TextView petName;
        private TextView petHeight;
        private TextView petWeight;
        private TextView petGender;
        private ImageView imageView;



        private String key;

        public ItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.pets_list,parent,false));


            petName =(TextView)itemView.findViewById(R.id.petId);
            petHeight =(TextView)itemView.findViewById(R.id.petHid);
            petWeight =(TextView)itemView.findViewById(R.id.petWid);
            petGender =(TextView)itemView.findViewById(R.id.petGid);

            imageView=(ImageView)itemView.findViewById(R.id.imageView4);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mContext, UpdatePets.class);
                    intent.putExtra("key",key);
                    intent.putExtra("name", petName.getText().toString());
                    intent.putExtra("height", petHeight.getText().toString());
                   intent.putExtra("weight", petWeight.getText().toString());
                   intent.putExtra("gender", petGender.getText().toString());

                    mContext.startActivity(intent);
                }
            });



            firebase = FirebaseDatabase.getInstance();
            reference = firebase.getReference("petData");


        }
        public void bind(PetModel item, String key){
            petName.setText(item.getName());
            petHeight.setText(item.getWeight());
            petWeight.setText(item.getGender());
            petGender.setText(item.getHeight());
            String imageUri=null;
            imageUri=item.getImage();
            Picasso.get().load(imageUri).into(imageView);
            this.key=key;

        }
    }
    class ItemAdapter extends RecyclerView.Adapter<ItemView>{
        private List<PetModel> petList;
        private List<String> mKeys;

        public ItemAdapter(List<PetModel> petModelList, List<String> mKeys) {
            this.petList = petModelList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public ItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemView holder, int position) {
            holder.bind(petList.get(position),mKeys.get(position));

        }

        @Override
        public int getItemCount() {
            return petList.size();
        }
    }
}

