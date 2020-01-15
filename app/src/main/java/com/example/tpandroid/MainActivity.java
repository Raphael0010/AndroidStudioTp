package com.example.tpandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goHelp(View v) {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

    public void goOption(View v) {
        Intent intent = new Intent(this, OptionActivity.class);
        startActivity(intent);
    }

    public void goNewParty(View v) {
        Intent intent = new Intent(this, NewPartyActivity.class);
        startActivity(intent);
    }
}
