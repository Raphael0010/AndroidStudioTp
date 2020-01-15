package com.example.tpandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyDataBase extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "CarteManager";

    // Table name
    private static final String TABLE_NAME = "Carte";

    public MyDataBase(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script.
        String script = "CREATE TABLE " + TABLE_NAME + "("
                + "id" + " INTEGER PRIMARY KEY," + "titre" + " TEXT,"
                + "localisation" + " TEXT,"
                + "difficulte" + " TEXT,"
                + "duree" + " TEXT" + ")";
        // Execute Script.
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public void createDefaultCarteIfNeed()  {
        int count = this.getCarteCount();
        if(count == 0) {
            this.addCarte(new CarteTresor(1,"Map1","Angoulême","Elevé",60));
            this.addCarte(new CarteTresor(2,"Map2","La Rochelle","Moyenne",120));
            this.addCarte(new CarteTresor(3,"Map3","Surgères","Moyenne",180));
            this.addCarte(new CarteTresor(4,"Map4","Niort","Faible",30));
        }
    }

    public void addCarte(CarteTresor carte) {
        Log.i(TAG, "MyDatabaseHelper.addCarte ... " + carte.getNom());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("titre", carte.getNom());
        values.put("localisation", carte.getLocalisation());
        values.put("difficulte", carte.getDifficulte());
        values.put("duree", carte.getDuree());
        // Inserting Row
        db.insert(TABLE_NAME, null, values);

        // Closing database connection
        db.close();
    }

    public CarteTresor getCarte(String name){
        Log.i(TAG, "MyDatabaseHelper.getCarte ... " + name);
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT id,titre,localisation,difficulte,duree FROM " + TABLE_NAME + " WHERE titre = ?",
                new String[]{String.valueOf(name)});

        if (cursor != null){
            cursor.moveToFirst();
        }

        CarteTresor carte = new CarteTresor(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                Integer.parseInt(cursor.getString(4)));

        return carte;
    }


    public int getCarteCount() {
        Log.i(TAG, "MyDatabaseHelper.getCarteCount ... " );
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    public List<CarteTresor> getAllCartes() {
        Log.i(TAG, "MyDatabaseHelper.getAllCartes ... (" + getCarteCount() +")" );

        List<CarteTresor> carteList = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CarteTresor carte = new CarteTresor();
                carte.setId(Integer.parseInt(cursor.getString(0)));
                carte.setNom(cursor.getString(1));
                carte.setLocalisation(cursor.getString(2));
                carte.setDifficulte(cursor.getString(3));
                carte.setDuree(Integer.parseInt(cursor.getString(4)));

                carteList.add(carte);
            } while (cursor.moveToNext());
        }

        // return carte list
        return carteList;
    }


}
