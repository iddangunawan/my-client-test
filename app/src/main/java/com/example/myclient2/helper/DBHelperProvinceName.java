package com.example.myclient2.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by iddan on 07/08/17.
 */

public class DBHelperProvinceName extends SQLiteOpenHelper {

    private static final String TAG = DBHelperProvinceName.class.getSimpleName();
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "db_province";
    private static final String TABLE_NAME = "table_province";
    private static final String KEY_PROV_ID = "id";
    private static final String KEY_PROV_NAME = "province_name";

    public DBHelperProvinceName(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            KEY_PROV_ID + " INTEGER PRIMARY KEY," +
            KEY_PROV_NAME + " TEXT" + ");";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    public void loadContent(){
        onUpgrade(this.getReadableDatabase(), DATABASE_VERSION, DATABASE_VERSION);

        addData(1, "JAWA BARAT");
        addData(2, "BANTEN");
        addData(3, "JAWA TIMUR");
        addData(4, "JAWA TENGAH");
        addData(5, "KALIMANTAN");
        addData(6, "SULAWESI");
    }

    void addData(int province_id, String province_name) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PROV_ID, province_id);
        values.put(KEY_PROV_NAME, province_name);

        db.insert(TABLE_NAME, null, values);
        Log.i(TAG, "addData " + "ID : " + values.get(KEY_PROV_ID) + " PROVINCE_NAME : " + values.get(KEY_PROV_NAME));
        db.close();
    }

    public String[] SelectAllDataProvinceName() {
        try {
            String arrData[] = null;
            SQLiteDatabase db;
            db = this.getReadableDatabase();

            String strSQL = "SELECT " + KEY_PROV_NAME + " FROM " + TABLE_NAME;
            Cursor cursor = db.rawQuery(strSQL, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()];
                    int i = 0;
                    do {
                        arrData[i] = cursor.getString(0);
                        i++;
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();

            return arrData;
        } catch (Exception e) {
            return null;
        }
    }
}
