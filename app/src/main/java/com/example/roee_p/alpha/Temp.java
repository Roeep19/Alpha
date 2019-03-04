package com.example.roee_p.alpha;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class Temp extends AppCompatActivity {
    int x;
    String phoneNumber,Nam,cod;
    EditText Phone,Name,Code;
    Button EnterCode;
    int CodeNumber;
    AlertDialog.Builder adb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);


        x=getIntent().getIntExtra("I",0);

        Phone=(EditText)findViewById(R.id.Phone);
        Code=(EditText)findViewById(R.id.Code);
        EnterCode=(Button)findViewById(R.id.EnterCode);
        Name=(EditText)findViewById(R.id.Name);

    }

    boolean chekphone(){
        if(phoneNumber.length()!=10){
            return false;
        }
        if (!phoneNumber.substring(0,2).equals("05")){
            return false;
        }
        if(phoneNumber.substring(2,3).equals("7") || phoneNumber.substring(2,3).equals("9")){
            return false;
        }
        return true;
    }
    boolean chekelet(){
        if (!checkName()) {
            return false;
        }

        if (!chekphone()){
            return false;
        }
        return true;
    }

    boolean checkName()
    {
        if(Nam.isEmpty()){
            return false;
        }
        if (Nam.contains("%") || Nam.contains(".") || Nam.contains("!") || Nam.contains("#") || Nam.contains("/")
                || Nam.contains("\\"))
            return false;
        return true;
    }

    public void chek(View view) {
        phoneNumber=Phone.getText().toString();
        Nam=Name.getText().toString();
        if(!chekelet()){
            adb=new AlertDialog.Builder(this);
            adb.setTitle("הזנת פרטים לא נכונים");
            adb.setMessage("חזור לתקן");
            adb.setPositiveButton("סגור", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            AlertDialog ad=adb.create();
            ad.show();
        }
        else {
            Intent R=new Intent(this, Details.class);
            R.putExtra("I" ,x);
            R.putExtra("XX" ,phoneNumber);
            R.putExtra("XXX" ,Nam);

            startActivity(R);
            //Random rnd = new Random();
           // CodeNumber = rnd.nextInt(9000) + 1000;

           // SmsManager.getDefault().sendTextMessage(phoneNumber, null, "" + CodeNumber, null, null); //
        }
    }

    public void chekCode(View view) {
        cod=Code.getText().toString();
        if(cod.equals(""+CodeNumber)){
            Intent R=new Intent(this, Details.class);
            R.putExtra("I" ,x);
            R.putExtra("XX" ,phoneNumber);
            R.putExtra("XXX" ,Nam);

            startActivity(R);
        }else {
            Toast.makeText(this, "קוד שגוי",Toast.LENGTH_SHORT).show();
        }
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
