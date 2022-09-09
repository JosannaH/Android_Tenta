package com.josanna.tenta_2_2022_du;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // namnvariabler
    static final String TABLE_NAME = "Horse_Data";
    static final String colHorseName = "Horse_Name";
    static final String colOwnerName = "Owner_Name";
    static final String colShoeSize = "Shoe_Size";
    static final String colPhone = "Phone";


    /**
     * konstruktor
     */
    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // databasen skapas
        db.execSQL("CREATE TABLE " + TABLE_NAME
                + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + colHorseName + " TEXT, "
                + colOwnerName + " TEXT, "
                + colShoeSize + " TEXT, "
                + colPhone + " TEXT)");
    }

    /**
     * databasen uppdateras (dvs raderas och skapas på nytt)
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * Lägg till ny häst i databasen
     *
     * @param horseName
     * @param ownerName
     * @param shoeSize
     * @param phone
     * @return
     */
    public boolean addHorse(String horseName, String ownerName, String shoeSize, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();

        // tala om vilka värden som ska läggas till
        ContentValues cv = new ContentValues();
        cv.put(colHorseName, horseName);
        cv.put(colOwnerName, ownerName);
        cv.put(colShoeSize, shoeSize);
        cv.put(colPhone, phone);

        // Databasfrågan körs och i result sparas info om huruvida db uppdaterades korrekt
        // -1 om det blivit fel
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Ta bort häst från databas
     *
     * @param horseName
     * @return
     */
    public boolean deleteHorse(String horseName) {
        // Skapa länk till databasen
        SQLiteDatabase db = this.getWritableDatabase();

        // Databasfrågan körs och i result sparas info om huruvida db uppdaterades korrekt
        // -1 om det blivit fel
        long result = db.delete(TABLE_NAME, colHorseName + " = ?", new String[]{horseName});

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Uppdatera databas med ny info om hästen
     *
     * @param horseName
     * @param newHorseName
     * @param newOwnerName
     * @param newShoeSize
     * @param newPhone
     * @return
     */
    public boolean updateHorse(String horseName, String newHorseName, String newOwnerName, String newShoeSize, String newPhone) {
        SQLiteDatabase db = this.getWritableDatabase();
        // ange vilka värden som ska ändras
        ContentValues cv = new ContentValues();
        cv.put(colHorseName, newHorseName);
        cv.put(colOwnerName, newOwnerName);
        cv.put(colShoeSize, newShoeSize);
        cv.put(colPhone, newPhone);
        // Uppdatera databasen och spara resultatet i result (-1 om det blivit fel och nåt annat om det gått igenom)
        long result = db.update(TABLE_NAME, cv, colHorseName + " = ?", new String[]{horseName});

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Hämta data om en häst från databasen
     *
     * @param horseName
     * @return
     */
    public Cursor getHorse(String horseName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + colHorseName + " = '" + horseName + "'", null);
        return c;
    }

    /**
     * Hämta all data från databasen
     *
     * @return
     */
    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }
}

