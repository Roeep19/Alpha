package com.example.roee_p.alpha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText phone,email,text;
    String smsNum,smsText, emailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phone=(EditText)findViewById(R.id.phone);
        email=(EditText)findViewById(R.id.email);
        text=(EditText)findViewById(R.id.text);

    }

    public void sendSMS(View view) {
        smsNum=phone.getText().toString();
        smsText=text.getText().toString();

        SmsManager.getDefault().sendTextMessage(smsNum, null, smsText, null,null);
    }

    public void sendEmail(View view) {
        emailAddress=email.getText().toString();
        smsText=text.getText().toString();

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{emailAddress});
        i.putExtra(Intent.EXTRA_SUBJECT, "EMAIL MESSAGE");
        i.putExtra(Intent.EXTRA_TEXT   , smsText);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void ori(View view) {
        Intent t =new Intent(this, FireBase.class);
        startActivity(t);
    }
}
