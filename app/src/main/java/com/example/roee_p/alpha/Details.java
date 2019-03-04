package com.example.roee_p.alpha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static android.view.View.GONE;

public class Details extends AppCompatActivity {
    String Nam1,phoneNum;
    int z;
    EditText Age,City,Desc,Condi,Email;
    String age,city,desc,condi,emai;


    ListView list;
    DatabaseReference ref;
    List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        list=(ListView)findViewById(R.id.list);
        itemList=new ArrayList<>();
        ref = FirebaseDatabase.getInstance().getReference("items");


        z = getIntent().getIntExtra("I", 0);
        Nam1 = getIntent().getStringExtra("XXX");
        phoneNum = getIntent().getStringExtra("XX");

        Age = (EditText) findViewById(R.id.age);
        City = (EditText) findViewById(R.id.city);
        Desc = (EditText) findViewById(R.id.description);
        Condi = (EditText) findViewById(R.id.conditions);
        Email = (EditText) findViewById(R.id.email);

        if(z==0){
            Age.setHint("גיל");
        }else {
            Age.setHint("דרישת גיל");
        }




        if (z==0){                              // employer
            Email.setVisibility(GONE);
            Desc.setVisibility(GONE);
            Condi.setVisibility(GONE);



        }else {if (z==1){
            emai=Email.getText().toString();
            desc=Desc.getText().toString();
            condi=Condi.getText().toString();}





        }


    }

    public void Upload1(View view) {
        if (z==0){
            age=Age.getText().toString();
            city=City.getText().toString();
            condi="";
            emai="";
            desc="";
            if (!age.isEmpty() && !city.isEmpty()){


                Intent e=new Intent(this, Job.class);
                e.putExtra("I", z);
                e.putExtra("XX", phoneNum);
                e.putExtra("XXX", Nam1);
                e.putExtra("AGE", Integer.parseInt(age));
                e.putExtra("CIT",city);


                startActivity(e);


            }else {
                Toast.makeText(this,"תקן פרטים",Toast.LENGTH_LONG).show();
            }

        }
        else {
            age=Age.getText().toString();
            city=City.getText().toString();
            condi=Condi.getText().toString();
            emai=Email.getText().toString();
            desc=Desc.getText().toString();
            if (!age.isEmpty() && !city.isEmpty() && !condi.isEmpty() && !desc.isEmpty() && chek()){
                String id=ref.push().getKey();
                Item item=new Item(id, city, Integer.parseInt(age), condi, emai, desc );
                ref.child(id).setValue(item);

                Intent e=new Intent(this, Job.class);
                e.putExtra("I", z);
                e.putExtra("XX", phoneNum);
                e.putExtra("XXX", Nam1);
                e.putExtra("AGE", Integer.parseInt(age));
                e.putExtra("CIT",city);


                startActivity(e);

            }else {
                Toast.makeText(this,"תקן פרטים",Toast.LENGTH_LONG).show();
            }




        }

    }
    boolean chek(){
        if (emai.isEmpty()){
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emai).matches()){
            return false;
        }

        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){

        Intent in=new Intent(this, Credits.class);
        startActivity(in);
        return true;

    }

}
