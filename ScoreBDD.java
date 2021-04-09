package com.example.clickncoop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

public class ScoreBDD {
    private SQLiteDatabase bdd = null;
    private ScoreDBHandler dbHandler = null;
    private CountViewModel viewModel;

    public ScoreBDD(Context context){
        dbHandler = new ScoreDBHandler(context);
    }

    public void open(){
        if (bdd == null){
            bdd = dbHandler.getWritableDatabase();
        }
    }

    public void close(){
        if(bdd != null){
            if(bdd.isOpen()){
                bdd.close();
            }
        }
    }

    public SQLiteDatabase getBdd(){
        return bdd;
    }

    public long insererValeur(){
        ContentValues values = new ContentValues();
        values.put(ScoreDBHandler.COL_SCORE_MASH, viewModel.getCounterMash());
        values.put(ScoreDBHandler.COL_SCORE_RYTHM, viewModel.getCounterRythm());

        return bdd.insert(ScoreDBHandler.TABLE_SCORE, null, values);
    }

    public List<Integer> getScoreMash(){
        List<Integer> score = new ArrayList<Integer>();
        Cursor c = bdd.rawQuery("select " + " count(" + ScoreDBHandler.COL_SCORE_MASH + ") as 'scoreMashTop', max(scoreMashTop) " + " from " + ScoreDBHandler.TABLE_SCORE + " group by " + ScoreDBHandler.COL_SCORE_MASH + " having scoreMashTop <= ?", new String[]{"3"});
        c.moveToFirst();
        while (! c.isAfterLast()){
            c.moveToNext();
        }
        c.close();
        return  score;
    }

    public List<Integer> getScoreRythm(){
        List<Integer> score = new ArrayList<Integer>();
        Cursor c = bdd.rawQuery("select " + " count(" + ScoreDBHandler.COL_SCORE_RYTHM + ") as 'scoreMashTop', max(scoreRythmTop) " + " from " + ScoreDBHandler.TABLE_SCORE + " group by " + ScoreDBHandler.COL_SCORE_RYTHM + " having scoreRythmTop <= ?", new String[]{"3"});
        c.moveToFirst();
        while (! c.isAfterLast()){
            c.moveToNext();
        }
        c.close();
        return  score;
    }
}
