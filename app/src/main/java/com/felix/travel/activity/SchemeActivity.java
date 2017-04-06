package com.felix.travel.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.felix.travel.R;

public class SchemeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme);
        Intent intent = getIntent();
        String schemeTest = intent.getScheme();
        Uri uri = intent.getData();
        if(uri != null){
           String query = uri.getQueryParameter("sid");
        }
    }
}
