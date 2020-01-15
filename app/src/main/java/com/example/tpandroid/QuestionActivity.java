package com.example.tpandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        TextView textView = findViewById(R.id.textViewLocalisation);
        if (getIntent() != null && getIntent().hasExtra(Intent.EXTRA_TEXT)) {
            MyDataBase db = new MyDataBase(this);
            CarteTresor carte = db.getCarte(getIntent().getStringExtra(Intent.EXTRA_TEXT));
            textView.setText(carte.getLocalisation());
        }
    }
}
