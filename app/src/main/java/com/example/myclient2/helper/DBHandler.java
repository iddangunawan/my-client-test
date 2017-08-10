package com.example.myclient2.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myclient2.model.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by iddan on 03/08/17.
 */

public class DBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_client";
    private static final String TABLE_CLIENT = "table_client";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ADDRESS = "address";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Function create database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_CLIENT + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                +COLUMN_ADDRESS + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);
    }

    // Function check database already or not
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENT);
        onCreate(db);
    }

    // Function add data
    public  void addClient(Client client) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, client.getName());
        values.put(COLUMN_ADDRESS, client.getAddress());

        db.insert(TABLE_CLIENT, null, values);
        db.close();
    }

    // Function get one data
    public Client getClient(int id_client) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CLIENT, new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_ADDRESS}, COLUMN_ID + "=?", new String[]{String.valueOf(id_client)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Client client =  new Client(cursor.getString(1), cursor.getString(2));

        return client;
    }

    // Function get all data
    public List<Client> getAllClient() {
        List<Client> clientList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " +TABLE_CLIENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Client client = new Client(cursor.getString(1), cursor.getString(2));
                clientList.add(client);
            } while (cursor.moveToNext());
        }

        return clientList;
    }

    // Function count data
    public int getClientCount() {
        String countQuery = "SELECT *  FROM " + TABLE_CLIENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    // Function update data
    public  int updateDataClient(Client client) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, client.getName());
        values.put(COLUMN_ADDRESS, client.getAddress());
        return db.update(TABLE_CLIENT, values, COLUMN_ID + " = ? ", new String[]{String.valueOf(client.getId())});
    }

    // Function delete data
    public void deleteDataClient(Client client) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CLIENT, COLUMN_ID + " = ? ", new String[]{String.valueOf(client.getId())});
        db.close();
    }

    // Function delete all data
    public void deleteAllDataClient() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_CLIENT);
    }
}
