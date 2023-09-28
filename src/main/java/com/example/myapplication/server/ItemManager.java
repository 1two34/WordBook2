package com.example.myapplication.server;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.entity.OneLearn;
import com.example.myapplication.util.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private Context context;
    public  ItemManager(Context context){
        this.context = context;
    }

    public boolean insertWord(String word,String trans){
        SQLiteDatabase sqLiteDatabase = new DBHelper(context).getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.WORD,word);
        cv.put(DBHelper.TRANSLATION,trans);
        sqLiteDatabase.insert("words",null,cv);
        return true;
    }

    public StringBuffer queryWord(String word) {
        StringBuffer buffer = new StringBuffer();
        if(word.equals("")){
            Toast.makeText(context,"没有找到相关单词",Toast.LENGTH_SHORT).show();
        }else{
            SQLiteDatabase sqLiteDatabase = new DBHelper(context).getWritableDatabase();
            String[] col = {DBHelper.WORD,DBHelper.TRANSLATION};
            String selection = "word like ?";
            String[] selectionArgs = {"%" + word + "%"};
            Cursor cursor = sqLiteDatabase.query("words", col, selection, selectionArgs, null, null, null);
            if (cursor.getCount() == 0) {
                Toast.makeText(context,"没有找到相关单词",Toast.LENGTH_SHORT).show();
            } else {
                while (cursor.moveToNext()) {
                    String word2 = cursor.getString(0);
                    String trans = cursor.getString(1);
                    buffer.append(word2).append(" : ").append(trans).append("\n");
                }
            }
        }
        return buffer;
    }

    public List<OneLearn> getAllWords(){
        SQLiteDatabase sqLiteDatabase = new DBHelper(context).getWritableDatabase();
        String[] cols = {DBHelper.ID,DBHelper.WORD,DBHelper.TRANSLATION};
        List<OneLearn> oneLearns = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(DBHelper.TABLE_NAME,cols,null,null,null,null,null);
        while(cursor.moveToNext()){
            oneLearns.add(new OneLearn(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2)));
        }
        return oneLearns;
    }

    public void updateWord(int id,String word,String trans){
        SQLiteDatabase sqLiteDatabase = new DBHelper(context).getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.WORD,word);
        cv.put(DBHelper.TRANSLATION,trans);
        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(id)};
        sqLiteDatabase.update(DBHelper.TABLE_NAME,cv,whereClause,whereArgs);
    }

    public void deleteWord(int id){
        SQLiteDatabase sqLiteDatabase = new DBHelper(context).getWritableDatabase();
        sqLiteDatabase.delete(DBHelper.TABLE_NAME,"id = ?", new String[] {String.valueOf(id)});
    }
}
