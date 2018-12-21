package com.bkhust.dungnd.emaildemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;

public class SendActivity extends AppCompatActivity {
    public static final String SUB = " abc";
    public static final String MES = "acd";
    private EditText etMailAddress;
    private Button btnSend;
    private String emailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        initView();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailAddress = etMailAddress.getText().toString();
                sendEmail();
            }
        });
    }

    private void sendEmail() {
        String file = "Download/model.csv";
        File fileLocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), file);
        Uri path = Uri.fromFile(fileLocation);
        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{emailAddress});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, SUB);
        emailIntent.putExtra(Intent.EXTRA_STREAM, path);
        emailIntent.putExtra(Intent.EXTRA_TEXT, MES);
        this.startActivity(Intent.createChooser(emailIntent, "Sending Email..."));
    }

    private void initView() {
        etMailAddress = findViewById(R.id.et_mail_address);
        btnSend = findViewById(R.id.btn_send);
    }

}
