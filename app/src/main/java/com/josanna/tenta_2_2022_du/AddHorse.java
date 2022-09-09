package com.josanna.tenta_2_2022_du;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddHorse extends AppCompatActivity {

    DatabaseHelper dbHelper;
    private EditText txt_horseName, txt_ownerName, txt_shoeSize, txt_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_horse);

        // hämta edittext-fält från xml-fil
        txt_horseName = findViewById(R.id.txt_horseName);
        txt_ownerName = findViewById(R.id.txt_ownerName);
        txt_shoeSize = findViewById(R.id.txt_shoeSize);
        txt_phone = findViewById(R.id.txt_phone);
        dbHelper = new DatabaseHelper(this);
    }

    /**
     * Metod för spara-knappen
     *
     * @param view
     */
    public void btnClickSaveHorse(View view) {
        // hämta in värden som användaren angett
        String horseName = txt_horseName.getText().toString();
        String ownerName = txt_ownerName.getText().toString();
        String shoeSize = txt_shoeSize.getText().toString();
        String phone = txt_phone.getText().toString();

        // Kolla att alla fält är ifyllda
        if (horseName.equals("") || ownerName.equals("") || shoeSize.equals("") || phone.equals("")) {
            // be användaren fylla i alla fält
            Toast.makeText(this, "Vänligen fyll i alla fält", Toast.LENGTH_LONG).show();
        } else {
            // Lägg till häst i databasen
            addHorseToDB(horseName, ownerName, shoeSize, phone);
        }
    }

    /**
     * Lägg till häst i databas
     *
     * @param horseName
     * @param ownerName
     * @param shoeSize
     * @param phone
     */
    private void addHorseToDB(String horseName, String ownerName, String shoeSize, String phone) {

        // anropa metod för att lägga till häst. Spara resultatet i variabeln result
        boolean result = dbHelper.addHorse(horseName, ownerName, shoeSize, phone);
        // visar resultatet för användaren
        if (result) {
            Toast.makeText(this, horseName + " är tillagd!", Toast.LENGTH_LONG).show();
            // skicka tillbaka användaren till menyn
            Intent i = new Intent(AddHorse.this, MainActivity.class);
            startActivity(i);
        } else {
            // om addHorse-metoden returnerar false
            Toast.makeText(this, "Något gick fel", Toast.LENGTH_LONG).show();
        }
    }
}