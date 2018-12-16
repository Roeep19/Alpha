package com.example.roee_p.alpha;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FireBase extends AppCompatActivity {
    EditText nameText,valueText;
    ListView list;

    DatabaseReference ref;
    List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base);

        nameText=(EditText)findViewById(R.id.nameText);
        valueText=(EditText)findViewById(R.id.valueText);
        list=(ListView)findViewById(R.id.list);
        itemList=new ArrayList<>();

        ref =FirebaseDatabase.getInstance().getReference("items");


    }

    @Override
    protected void onStart() {
        super.onStart();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                itemList.clear();
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren())
                {
                    Item item = itemSnapshot.getValue(Item.class);
                    itemList.add(item);
                }

                ItemList adapter = new ItemList(FireBase.this, itemList);
                list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void upload(View view) {
        String name= nameText.getText().toString();
        String value= valueText.getText().toString();

        if (!name.isEmpty() && !value.isEmpty()){
            String id = ref.push().getKey();
            Item item = new Item(id, name, Integer.parseInt(value));
            ref.child(id).setValue(item);

        }
    }
}
