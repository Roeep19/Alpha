package com.example.roee_p.alpha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

public class Enter extends AppCompatActivity {
    Switch sw;
    int I=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        sw=(Switch)findViewById(R.id.switch1);


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



    public void Enter(View view) {
        if (sw.isChecked()) {
            I=1;                                          //employer
        }else {
            I=0;                                         //employee

        }
        Intent T=new Intent(this, Temp.class);
        T.putExtra("I",I);
        startActivity(T);
    }
}
