package com.science.app.sciencelab;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toolbar;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDbHelper extends SQLiteOpenHelper {
    public static final String ITEM_TABLE = "item";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DETAILS = "details";
    public static final String DEPARTMENT = "department_num";
    public static final String IMAGE_ID = "image";


    public MyDbHelper(@Nullable Context context) {
        super(context, "lab", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + ITEM_TABLE + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + NAME + " TEXT, " + DETAILS + " TEXT , " + DEPARTMENT + " TEXT , " + IMAGE_ID + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addItem(String name, String details, int imageId, int department_num) {
        this.getWritableDatabase().execSQL("INSERT INTO " + ITEM_TABLE + " (" + NAME + "," + DETAILS + "," + DEPARTMENT + "," + IMAGE_ID + ") Values ('" + name + "','" + details + "','" + department_num + "','" + imageId + "')");
    }

    public ArrayList<Item> getAllItemForDepartment(int dept) {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM " + ITEM_TABLE + " WHERE " + DEPARTMENT + "='" + dept + "'", null);
        ArrayList<Item> items = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id_index = cursor.getColumnIndex(ID);
            int id = cursor.getInt(id_index);

            int name_index = cursor.getColumnIndex(NAME);
            String name = cursor.getString(name_index);

            int details_index = cursor.getColumnIndex(DETAILS);
            String details = cursor.getString(details_index);

            int department_id = cursor.getColumnIndex(DEPARTMENT);
            int department = cursor.getInt(department_id);

            int image_index = cursor.getColumnIndex(IMAGE_ID);
            int image = cursor.getInt(image_index);

            Item item = new Item(id, name, details, department, image);
            items.add(item);
        }
        return items;
    }


    public Item getItemById(int id) {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM " + ITEM_TABLE + " WHERE " + ID + "=" + id, null);
        Item item = new Item(0, "", "", 0, 0);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            item.setId(cursor.getInt(0));
            item.setName(cursor.getString(1));
            item.setDetails(cursor.getString(2));
            item.setDepartmentNum(cursor.getInt(3));
            item.setImageId(cursor.getInt(4));
            item.setDepartmentNum(cursor.getPosition());
        }
        if (item.getId() != 0) {
            return item;
        }
        return null;
    }

}
