package com.josanna.tenta_2_2022_du;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchHorse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_horse);
    }

    /**
     * Hämtar in användarens val och visar hästens information
     *
     * @param view
     */
    public void btnSearchHorse(View view) {

        DatabaseHelper dbHelper = new DatabaseHelper(this);

        // Hämta fält från xml-fil
        EditText txt_horseName = findViewById(R.id.txt_horseName);
        TextView txt_owner = findViewById(R.id.txt_owner);
        TextView txt_shoe = findViewById(R.id.txt_shoe);
        TextView txt_phone = findViewById(R.id.txt_phone);

        // Hämta användarens input
        String horse = txt_horseName.getText().toString();
        // Anropa metod för att hitta häst i databas
        Cursor c = dbHelper.getHorse(horse);

        // Skriv ut info om häst i text-fält
        if (c.moveToFirst()) {
            txt_owner.setText("Ägarens namn: " + c.getString(2));
            txt_shoe.setText("Skostorlek: " + c.getString(3));
            txt_phone.setText("Telefonnummer: " + c.getString(4));
        } else {
            Toast.makeText(this, "Något gick fel", Toast.LENGTH_LONG);
        }
    }
}