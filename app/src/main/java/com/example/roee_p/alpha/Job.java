package com.example.roee_p.alpha;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class Job extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView list;
    List<Item> itemList;
    DatabaseReference ref;
    int age1,if1;
    String city1,nam2,phoneNum2;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

        tv=(TextView)findViewById(R.id.tv);
        list = findViewById(R.id.list);
        list.setOnItemClickListener(this);
        itemList = new ArrayList<>();
        ref = FirebaseDatabase.getInstance().getReference("items");

        age1=getIntent().getIntExtra("AGE",0);
        city1=getIntent().getStringExtra("CIT");
        phoneNum2=getIntent().getStringExtra("XX");
        if1=getIntent().getIntExtra("I",0);
        nam2=getIntent().getStringExtra("XXX");

        if (if1==0){
           tv.setText("משרות המתאימות לך:");
        }else {
            tv.setText("משרתך הועלתה בהצלחה");
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Item item = ds.getValue(Item.class);
                    if (if1 == 1)
                        itemList.add(item);
                    else
                        if (age1 >= item.getAge() && city1.equalsIgnoreCase(item.getCity()))
                            itemList.add(item);
                }
                NameList adapter = new NameList(Job.this, itemList);
                list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder adb=new AlertDialog.Builder(this);
        adb.setTitle("פרטי המשרה                    ");
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.list_layout, null);
        adb.setView(v);
        TextView description, city, age, email, conditions;
        description = v.findViewById(R.id.description);
        city = v.findViewById(R.id.city);
        age = v.findViewById(R.id.age);
        email = v.findViewById(R.id.email);
        conditions = v.findViewById(R.id.conditions);

        final Item item = itemList.get(i);
        description.setText(item.getDescription());
        city.setText("עיר:"+item.getCity());
        age.setText("גיל דרוש:"+ item.getAge());
        conditions.setText("שכר לשעה:"+item.getConditions());
        email.setText("אימייל:"+item.getEmail());


        adb.setPositiveButton("סגור", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        adb.setNeutralButton("שלח מייל", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent z = new Intent(Intent.ACTION_SEND);
                z.setType("message/rfc822");
                z.putExtra(Intent.EXTRA_EMAIL  , new String[]{item.getEmail()});
                z.putExtra(Intent.EXTRA_SUBJECT, "ראיון עבודה");
                z.putExtra(Intent.EXTRA_TEXT   ,"שלום, קוראים לי "+nam2+" אני רוצה להגיע לראיון עבודה ב "+item.getDescription()+" מספר הטלפון שלי הוא: " +phoneNum2);
                try {
                    startActivity(Intent.createChooser(z, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Job.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }


            }
        });
        AlertDialog ad=adb.create();
        ad.show();
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
