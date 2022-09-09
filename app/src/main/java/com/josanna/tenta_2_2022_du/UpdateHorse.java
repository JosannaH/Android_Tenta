package com.josanna.tenta_2_2022_du;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateHorse extends AppCompatActivity {

    // deklarera variabler
    DatabaseHelper dbHelper;
    private EditText txt_searchName, txt_newHorseName, txt_newOwnerName, txt_newShoeSize, txt_newPhone;
    String searchedHorse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_horse);

        // Hämta alla fält från xml
        txt_newHorseName = findViewById(R.id.txt_newHorseName);
        txt_newOwnerName = findViewById(R.id.txt_newOwnerName);
        txt_newShoeSize = findViewById(R.id.txt_newShoeSize);
        txt_newPhone = findViewById(R.id.txt_newPhone);
        txt_searchName = findViewById(R.id.txt_searchName);
        dbHelper = new DatabaseHelper(this);
    }

    /**
     * Söka fram nuvarande data om häst och skriva ut i edittext-fält
     *
     * @param view
     */
    public void btnSearchHorse(View view) {

        // Hämta användarens input
        searchedHorse = txt_searchName.getText().toString();
        // Anropa metod för att hitta häst i databas
        Cursor c = dbHelper.getHorse(searchedHorse);

        // Skriv ut info om häst i text-fält
        if (c.moveToFirst()) {
            if (c.getString(1).equals("")) {
                Toast.makeText(this, "Hästen finns inte", Toast.LENGTH_LONG);
            } else {
                txt_newHorseName.setText(c.getString(1));
                txt_newOwnerName.setText(c.getString(2));
                txt_newShoeSize.setText(c.getString(3));
                txt_newPhone.setText(c.getString(4));
            }
        } else {
            Toast.makeText(this, "Något gick fel", Toast.LENGTH_LONG);
        }
    }

    /**
     * Spara ny info om häst i databasen
     *
     * @param view
     */
    public void btnSaveUpdate(View view) {

        //hämta ny data frn edittext-fält
        String newHorseName = txt_newHorseName.getText().toString();
        String newOwner = txt_newOwnerName.getText().toString();
        String newShoeSize = txt_newShoeSize.getText().toString();
        String newPhone = txt_newPhone.getText().toString();

        // anropa metod som uppdaterar databasen
        boolean update = dbHelper.updateHorse(searchedHorse, newHorseName, newOwner, newShoeSize, newPhone);
        // Skriv ut meddelande till användare
        if (update) {
            Toast.makeText(this, newHorseName + " är uppdaterad!", Toast.LENGTH_LONG).show();
            // skicka tillbaka användaren till menyn
            Intent i = new Intent(UpdateHorse.this, MainActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(this, "Något gick fel", Toast.LENGTH_LONG).show();
        }
    }
}