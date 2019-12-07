package com.example.contacts;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ContactOpenHelper extends SQLiteOpenHelper {
    static final private String TAG = "ContactOpenHelperTag";

    static final private String DATABASE_NAME = "contactsDatabase";
    static final private int DATABASE_VERSION = 1;

    static final private String TABLE_CONTACTS = "tableContacts";
    static final private String ID = "_id";
    static final String NAME = "name";
    static final private String PHONE_NUMBER = "phoneNumber";
    static final private String EMAIL = "eMail";
    static final private String ADDRESS = "address";

    public ContactOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "CREATE TABLE " + TABLE_CONTACTS +
                "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT, " +
                PHONE_NUMBER + " TEXT, " +
                EMAIL + " TEXT" +
                ADDRESS + " TEXT" + ")";
        Log.d(TAG, "onCreate: " + sqlCreate);


        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertContact(Contact contact) {
        String sqlInsert = "INSERT INTO " + TABLE_CONTACTS + " VALUES(null, '" +
                contact.getName() + "', '" +
                contact.getPhoneNumber() + "', '" +
                contact.geteMail() + "', '" +
                contact.getAddress() + " ')";
        Log.d(TAG, "insertContact: " + sqlInsert);


        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlInsert);
        db.close();
    }

    public Cursor getSelectAllContactsCursor() {
        String sqlSelect = "SELECT * FROM " + TABLE_CONTACTS;
        Log.d(TAG, "getSelectAllContactCursor: " + sqlSelect);


        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlSelect, null);
        return cursor;
    }

    public Contact getSelectContactById(long id) {
        String sqlSelect = "SELECT * FROM " + TABLE_CONTACTS +
                " WHERE " + ID + "=" + id;
        Log.d(TAG, "selectContactById: " + sqlSelect);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlSelect, null);

        cursor.moveToNext();
        String name = cursor.getString(1);
        String phoneNumber = cursor.getString(2);
        String eMail = cursor.getString(3);
        String address = cursor.getString(4);
        Contact returnContact = new Contact(name, phoneNumber, eMail, address);

        db.close();
        return returnContact;
    }

    public void updateContactById(long id, Contact contact) {
        String sqlUpdate = "UPDATE " + TABLE_CONTACTS + " SET " +
                NAME + "='" + contact.getName() + "', " +
                PHONE_NUMBER + "='" + contact.getPhoneNumber() + "', " +
                EMAIL + "='" + contact.geteMail() + "', " +
                ADDRESS + "'='" + contact.getAddress() + "' WHERE " +
                ID + "=" + id;
        Log.d(TAG, "updateContactById: " + sqlUpdate);


        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlUpdate);
        db.close();
    }

    public void deleteAllContacts() {
        String sqlDelete = "DELETE FROM " + TABLE_CONTACTS;
        Log.d(TAG, "deleteAllContacts: " + sqlDelete);


        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlDelete);
        db.close();
    }

    public void deleteContactById(long id) {
        String sqlDelete = "DELETE FROM " + TABLE_CONTACTS + " WHERE " +
                ID + "=" + id;
        Log.d(TAG, "deleteContactById: " + sqlDelete);


        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlDelete);
        db.close();
    }
}
