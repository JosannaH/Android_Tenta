package com.josanna.tenta_2_2022_du;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowAllHorses extends AppCompatActivity {

    DatabaseHelper dbHelper;
    private ListView listViewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_horses);

        listViewAll = findViewById(R.id.listViewAll);
        dbHelper = new DatabaseHelper(this);

        // Lägg till hästar i listviewn när sidan öppnas
        addToListView();
    }

    /**
     * Metod för att lägga till hästar i listviewn
     */
    private void addToListView() {
        // hämta data från databas genom att anropa metoden .getData()
        // från DatabaseHelper
        Cursor data = dbHelper.getData();

        ArrayList<String> arrayData = new ArrayList<>();
        // Ta datan från cursorn och spara i lista
        while (data.moveToNext()) {
            arrayData.add("\nHäst:\t" + data.getString(1) + "\nÄgare:\t" + data.getString(2) + "\nSkostrl:\t" + data.getString(3) + "\nTelefon:\t" + data.getString(4) + "\n");
        }
        // visa datan i listview med hjälp av en adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayData);
        listViewAll.setAdapter(adapter);
    }
}