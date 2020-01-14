package com.example.ereaapp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ereaapp.Module.Country;

import java.util.ArrayList;

public class CountriesDBHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "countries";
    private static final String NATIVE_NAME = "nativeName";
    private static final String ENG_NAME = "engName";
    private static final String BORDERS_COUNTRIES = "bordersCountries";
    private static final String AREA = "area";
    private static final String FLAG = "flag";

    public CountriesDBHelper(Context context) {
        super(context, "countryDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = String.format("create table %s (%s text, %s text, %s text, %s integer, %s text)",
                TABLE_NAME, NATIVE_NAME, ENG_NAME, BORDERS_COUNTRIES, AREA, FLAG);
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Country> getCountries(int sort) {
        ArrayList<Country> countries = new ArrayList<>( );
        SQLiteDatabase db = getReadableDatabase( );
        Cursor cursor;
        switch (sort){
            case 1:
                cursor = db.query(TABLE_NAME, null, null, null, null, null, NATIVE_NAME);
                break;
            case 2:
                cursor = db.query(TABLE_NAME, null, null, null, null, null, NATIVE_NAME + " DESC");
                break;
            case 3:
                cursor = db.query(TABLE_NAME, null, null, null, null, null, AREA);
                break;
            case 4:
                cursor = db.query(TABLE_NAME, null, null, null, null, null, AREA + " DESC");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + sort);
        }
        while (cursor.moveToNext( )) {
            String nativeName = cursor.getString(cursor.getColumnIndex(NATIVE_NAME));
            String engName = cursor.getString(cursor.getColumnIndex(ENG_NAME));
            String bordersCountries = cursor.getString(cursor.getColumnIndex(BORDERS_COUNTRIES));
            long area = cursor.getLong(cursor.getColumnIndex(AREA));
            String flag = cursor.getString(cursor.getColumnIndex(FLAG));
            countries.add(new Country(nativeName, engName, bordersCountries, area, flag));
        }
        db.close( );
        return countries;
    }

    public void InsertCountry(Country country){
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(NATIVE_NAME, country.getNativeName());
            values.put(ENG_NAME, country.getEngName());
            values.put(BORDERS_COUNTRIES, country.getBorderCountries());
            values.put(FLAG, country.getFlag());
        values.put(AREA, country.getArea());
            db.insert(TABLE_NAME, null, values);
            db.close();
    }

    public Country getCountry(String name) {
        SQLiteDatabase db = getReadableDatabase( );
        return null;
    }
}
