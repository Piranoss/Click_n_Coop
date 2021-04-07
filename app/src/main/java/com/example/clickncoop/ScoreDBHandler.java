package com.example.clickncoop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ScoreDBHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "score.db";
    public static final int DATABSE_VERSION = 1;
    public static final String TABLE_SCORE = "table_score";
    public static final String COL_ID = "ID";
    public static final String COL_SCORE_MASH = "SCORE_MASH";
    public static final String COL_SCORE_RYTHM = "SCORE_RYTHM";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_SCORE + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_SCORE_MASH + " INTEGER NOT NULL, "
            + COL_SCORE_RYTHM + " INTEGER NOT NULL);";

    public ScoreDBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public  void onCreate(SQLiteDatabase db){
        //creation de la table
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE " + TABLE_SCORE + ";");
        onCreate(db);
    }
}
