package com.josanna.tenta_2_2022_du;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteHorse extends AppCompatActivity {

    EditText txt_search;
    String horseName;
    DatabaseHelper dbHelper;
    TextView txt_owner;
    TextView txt_shoes;
    TextView txt_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_horse);

        dbHelper = new DatabaseHelper(this);

        // Hämta edittext-fält från xml-fil
        txt_search = findViewById(R.id.txt_searchName);
        txt_owner = findViewById(R.id.txt_ownerName);
        txt_shoes = findViewById(R.id.txt_shoeSize);
        txt_phone = findViewById(R.id.txt_phone);
    }

    /**
     * Ta bort häst från databas
     *
     * @param view
     */
    public void btnDelete(View view) {
        // hämta hästnamn från edittext
        horseName = txt_search.getText().toString();
        // Ta bort häst från databas med hjälp av metod från DatabaseHelper
        boolean result = dbHelper.deleteHorse(horseName);

        if (result) {
            // Om hästen tagits bort, visa meddelande för användaren
            Toast.makeText(view.getContext(), horseName + " har tagits bort", Toast.LENGTH_LONG).show();
            // skicka tillbaka användaren till menyn
            Intent i = new Intent(DeleteHorse.this, MainActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(view.getContext(), "Något gick fel, försök igen!", Toast.LENGTH_LONG).show();
        }
    }
}