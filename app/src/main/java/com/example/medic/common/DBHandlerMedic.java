package com.example.medic.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandlerMedic extends SQLiteOpenHelper {

    private static final String DB_NAME = "Medicdb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "basket";
    private static final String ID_ANALYSIS_COL="id_analysis";
    private static final String CATEGORY_COL = "category";
    private static final String NAME_COL = "name";
    private static final String DESCRIPTION_COL = "description";
    private static final String PRICE_COL = "price";
    private static final String TIME_RESULT_COL = "time_result";
    private static final String PREPARATION_COL = "preparation";
    private static final String BIO_COL = "bio";
    private static final String NUMBER_COL = "num";


    public DBHandlerMedic(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_ANALYSIS_COL + " INTEGER, "
                + CATEGORY_COL + " TEXT,"
                + NAME_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT,"
                + PRICE_COL + " TEXT,"
                + TIME_RESULT_COL + " TEXT,"
                + PREPARATION_COL + " TEXT,"
                + BIO_COL + " TEXT,"
                + NUMBER_COL + " INTEGER DEFAULT 1)"
                ;

        db.execSQL(query);
    }

    public void addAnalysis(Analysis analysis){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID_ANALYSIS_COL,analysis.getId());
        contentValues.put(CATEGORY_COL,analysis.getCategory());
        contentValues.put(NAME_COL,analysis.getName());
        contentValues.put(DESCRIPTION_COL,analysis.getDescription());
        contentValues.put(PRICE_COL,analysis.getPrice());
        contentValues.put(TIME_RESULT_COL,analysis.getTime_result());
        contentValues.put(PREPARATION_COL,analysis.getPreparation());
        contentValues.put(BIO_COL,analysis.getBio());
        database.insert(TABLE_NAME,null,contentValues);
        database.close();
    }

    public ArrayList<Analysis> readAnalysis(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        ArrayList<Analysis> analyses = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                analyses.add(new Analysis(cursor.getInt(0),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        Integer.valueOf(cursor.getString(1)),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return analyses;
    }

    public void addPatient(Integer analysis){
        SQLiteDatabase database = this.getWritableDatabase();

        int number_patient = getPatient(analysis);
        ++number_patient;

        ContentValues values = new ContentValues();
        values.put(NUMBER_COL,number_patient);
        database.update(TABLE_NAME,values,"id_analysis = ?",new String[]{String.valueOf(analysis)});
        database.close();
    }
    public void deletePatient(Integer analysis){
        SQLiteDatabase database = this.getWritableDatabase();

        int number_patient = getPatient(analysis);
        --number_patient;

        ContentValues values = new ContentValues();
        values.put(NUMBER_COL,number_patient);
        database.update(TABLE_NAME,values,"id_analysis = ?",new String[]{String.valueOf(analysis)});
        database.close();
    }

    public int getPatient(Integer analysis) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME,
                new  String[]{NUMBER_COL},
                " id_analysis=?",
                new String[]{String.valueOf(analysis)},null,null,null,null);
        if (cursor!=null){
            cursor.moveToFirst();
        }
        int number = cursor.getInt(0);
        return number;
    }

    public void deleteAllAnalysis(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("delete from "+TABLE_NAME);
    }

    public boolean AnalysisExists(Integer analysis){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE id_analysis ="+analysis,null);
        boolean exists = (cursor.getCount()>0);
        return exists;
    }

    public void deleteAnalysis(Integer analysis){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_NAME,"id_analysis=?", new String[]{String.valueOf(analysis)});
        database.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public int getSumPrice(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT SUM(price*num) FROM " + TABLE_NAME, null);
        c.moveToFirst();
        int sum = c.getInt(0);
        c.close();
        return sum;
    }
}
