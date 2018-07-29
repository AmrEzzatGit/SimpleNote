package com.example.panda.note;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DataBase extends SQLiteOpenHelper {
    static final String DATABASE_NAME="Note";
    static final String TABLE_NAME="Note";
    static final String COLUMN_ID="ID";
    static final String COLUMN_NAME="NoteTitle";
    static final String COLUMN_CONTENT="NoteContent";
    static final int Version=2;
    public DataBase(Context context) {super(context, DATABASE_NAME, null, Version);}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_Creat="CREATE TABLE " +  TABLE_NAME  + " (" +
                COLUMN_ID  + " INTEGER  PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME+ " TEXT NOT NULL, " +
                COLUMN_CONTENT + " TEXT NOT NULL )" ;
sqLiteDatabase.execSQL(SQL_Creat);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
