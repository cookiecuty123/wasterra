package com.example.wasterra;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String INV_TABLE = "INV_TABLE";
    public static final String COLUMN_ITEM_NAME = "ITEM_NAME";
    public static final String COLUMN_QUANTITY = "QUANTITY";
    public static final String COLUMN_ID = "ID";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "inv.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + INV_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_ITEM_NAME + " TEXT, " + COLUMN_QUANTITY + " INTEGER)";

        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(ItemModel itemModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ITEM_NAME, itemModel.getItem());
        cv.put(COLUMN_QUANTITY, itemModel.getQty());

        long insert = db.insert(INV_TABLE, null, cv);
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }

    }

    public boolean deleteOne(ItemModel itemModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + INV_TABLE + " WHERE " + COLUMN_ID + " = " + itemModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        }
        else {
            return false;
        }


    }

    public List<ItemModel> getEverything(){
        List<ItemModel> returnList = new ArrayList<>();

        String queryString="SELECT * FROM " + INV_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()){

            do {
                int itemID = cursor.getInt(0);
                String itemName = cursor.getString(1);
                int itemQuantity = cursor.getInt(2);

                ItemModel newItem = new ItemModel(itemID, itemName, itemQuantity);
                returnList.add(newItem);

            }
            while (cursor.moveToNext());

        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }
}
