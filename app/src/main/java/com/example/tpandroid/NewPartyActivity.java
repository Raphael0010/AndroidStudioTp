package com.example.tpandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.List;

public class NewPartyActivity extends AppCompatActivity {

    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_party);
        tableLayout = findViewById(R.id.tableLayoutCarte);
        loadData();
    }

    private void loadData(){
        List<CarteTresor> cartes;

        MyDataBase db = new MyDataBase(this);
        db.createDefaultCarteIfNeed();
        cartes = db.getAllCartes();

        createColumns();
        fillData(cartes);
    }

    private void goToQuestion(String id){
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, id);
        startActivity(intent);
    }

    private void createColumns(){
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        // Nom Column
        TextView textViewName = new TextView(this);
        textViewName.setText("Nom");
        textViewName.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewName.setPadding(5, 5, 5, 10);
        textViewName.setTextSize(24);
        tableRow.addView(textViewName);

        // Localisation Column
        TextView textViewLocalisation = new TextView(this);
        textViewLocalisation.setText("Localisation");
        textViewLocalisation.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewLocalisation.setPadding(5, 5, 5, 10);
        textViewLocalisation.setTextSize(24);
        tableRow.addView(textViewLocalisation);

        // Difficulté Column
        TextView textViewDiffculte = new TextView(this);
        textViewDiffculte.setText("Difficulté");
        textViewDiffculte.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewDiffculte.setPadding(5, 5, 5, 10);
        textViewDiffculte.setTextSize(24);
        tableRow.addView(textViewDiffculte);

        // Durée Column
        TextView textViewDuree = new TextView(this);
        textViewDuree.setText("Durée");
        textViewDuree.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewDuree.setPadding(5, 5, 5, 10);
        textViewDuree.setTextSize(24);
        tableRow.addView(textViewDuree);

        tableLayout.addView(tableRow, new TableLayout.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
    }

    private void fillData(List<CarteTresor> cartes) {
        for (CarteTresor carte : cartes) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            tableRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TableRow currentRow = (TableRow) view;
                    TextView textViewId = (TextView) currentRow.getChildAt(0);
                    String id = textViewId.getText().toString();
                    // Redirection to question page
                    goToQuestion(id);
                }
            });

            // Nom Column
            TextView textViewNom = new TextView(this);
            textViewNom.setText(carte.getNom());
            textViewNom.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            textViewNom.setPadding(5, 5, 5, 0);
            tableRow.addView(textViewNom);

            // Localisation Column
            TextView textViewLocalisation = new TextView(this);
            textViewLocalisation.setText(carte.getLocalisation());
            textViewLocalisation.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            textViewLocalisation.setPadding(5, 5, 5, 0);
            tableRow.addView(textViewLocalisation);

            // Difficulte Column
            TextView textViewDifficulte = new TextView(this);
            textViewDifficulte.setText(carte.getDifficulte());
            textViewDifficulte.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            textViewDifficulte.setPadding(5, 5, 5, 0);
            tableRow.addView(textViewDifficulte);

            // Duree Column
            TextView textViewDuree = new TextView(this);
            textViewDuree.setText(carte.getDuree()+"min");
            textViewDuree.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            textViewDuree.setPadding(5, 5, 5, 0);
            tableRow.addView(textViewDuree);

            tableLayout.addView(tableRow, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
        }
    }
}
