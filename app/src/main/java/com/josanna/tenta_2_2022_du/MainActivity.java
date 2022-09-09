package com.josanna.tenta_2_2022_du;

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

    /**
     * Gå till Lägg till häst
     *
     * @param view
     */
    public void goToAddHorse(View view) {
        // Skapa intent för att gå till Lägg till häst
        Intent i = new Intent(MainActivity.this, AddHorse.class);
        startActivity(i);

    }

    /**
     * Gå till Visa alla hästar
     *
     * @param view
     */
    public void goToShowAllHorses(View view) {
        Intent i = new Intent(MainActivity.this, ShowAllHorses.class);
        startActivity(i);
    }

    /**
     * Gå till Ta bort häst
     *
     * @param view
     */
    public void goToDeleteHorse(View view) {
        Intent i = new Intent(MainActivity.this, DeleteHorse.class);
        startActivity(i);
    }

    /**
     * Gå till uppdatera häst
     *
     * @param view
     */
    public void goToUpdateHorse(View view) {
        Intent i = new Intent(MainActivity.this, UpdateHorse.class);
        startActivity(i);
    }

    /**
     * Gå till Sök häst
     *
     * @param view
     */
    public void goToSearchHorse(View view) {
        Intent i = new Intent(MainActivity.this, SearchHorse.class);
        startActivity(i);
    }
}