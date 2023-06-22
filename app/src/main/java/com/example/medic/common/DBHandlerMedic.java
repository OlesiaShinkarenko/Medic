package com.example.medic.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHandlerMedic extends SQLiteOpenHelper {

    private static final String DB_NAME = "Medicdb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME_BASKET = "basket";
    private static final String ID_ANALYSIS_COL_BASKET="id_analysis";
    private static final String CATEGORY_COL_BASKET = "category";
    private static final String NAME_COL_BASKET = "name";
    private static final String DESCRIPTION_COL_BASKET = "description";
    private static final String PRICE_COL_BASKET = "price";
    private static final String TIME_RESULT_COL_BASKET = "time_result";
    private static final String PREPARATION_COL_BASKET = "preparation";
    private static final String BIO_COL_BASKET = "bio";
    private static final String NUMBER_COL_BASKET = "num";


    private static final String TABLE_NAME_PATIENT = "patient";
    private static final String ID_PATIENT_COL_PATIENT="id_patient";
    private static final String FIRST_NAME_COL_PATIENT= "first_name";
    private static final String LAST_NAME_COL_PATIENT = "last_name";
    private static final String MIDDLE_NAME_COL_PATIENT = "middle_name";
    private static final String DATE_OF_BIRTH_COL_PATIENT = "date_of_birth";
    private static final String POL_COL_PATIENT = "pol";
    private static final String IMAGE_COL_PATIENT = "image";


    private static final String TABLE_NAME_ADDRESS = "address";
    private static final String ID_COL_ADDRESS = "id";
    private static final String ADDRESS_COL_ADDRESS = "addre";
    private static final String FLAT_COL_ADDRESS = "flat";
    private static final String ENTRANCE_COL_ADDRESS = "entrance";
    private static final String FLOOR_COL_ADDRESS = "floor";
    private static final String DOORPHONE_COL_ADDRESS = "doorphone";
    private static final String LABEL_COL_ADDRESS = "label";


    private static final String TABLE_NAME_ORDER = "order_table";
    private static final String ID_COL_ORDER="id";
    private static final String ADDRESS_COL_ORDER = "address";
    private static final String DATE_COL_ORDER  = "date_time";
    private static final String PHONE_COL_ORDER = "phone";
    private static final String COMMENT_COL_ORDER = "comment";



    private static final String TABLE_NAME_PATIENT_ORDER = "patient_in_order_table";
    private static final String ID_COL_PATIENT_ORDER = "id_patient_in_order";
    private static final String PATIENT_COL_PATIENT_ORDER = "patient_in_order";
    private static final String ORDER_COL_PATIENT_ORDER = "order_i";


    private static final String TABLE_NAME_ANALYSIS_IN_PATIENT = "analysis_in_patient";
    private static final String ID_COL_ANALYSIS_IN_PATIENT = "id_analysis_in_patient";
    private static final String PATIENT_IN_ORDER_COL_ANALYSIS_IN_PATIENT = "for_patient_in_order";
    private static final String ANALYSIS_COL_ANALYSIS_IN_PATIENT = "analysis";

   // private static final String PRICE_COL_ANALYSIS_IN_PATIENT= "price_analysis_in_order";



    public DBHandlerMedic(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME_BASKET + " ("
                + ID_ANALYSIS_COL_BASKET + " INTEGER, "
                + CATEGORY_COL_BASKET + " TEXT,"
                + NAME_COL_BASKET + " TEXT,"
                + DESCRIPTION_COL_BASKET + " TEXT,"
                + PRICE_COL_BASKET + " TEXT,"
                + TIME_RESULT_COL_BASKET + " TEXT,"
                + PREPARATION_COL_BASKET + " TEXT,"
                + BIO_COL_BASKET + " TEXT,"
                + NUMBER_COL_BASKET + " INTEGER DEFAULT 1)";

        //БД для хранения данных о пользователе
        String query2 = "CREATE TABLE "+TABLE_NAME_PATIENT+" ("
                +ID_PATIENT_COL_PATIENT+" INTEGER, "
                +FIRST_NAME_COL_PATIENT+" TEXT, "
                +LAST_NAME_COL_PATIENT+" TEXT, "
                +MIDDLE_NAME_COL_PATIENT+" TEXT, "
                +DATE_OF_BIRTH_COL_PATIENT+" TEXT, "
                +POL_COL_PATIENT+" TEXT, "
                +IMAGE_COL_PATIENT+ " TEXT DEFAULT '')";

        String query3 = "CREATE TABLE "+TABLE_NAME_ADDRESS+" ("
                +ID_COL_ADDRESS+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +ADDRESS_COL_ADDRESS + " TEXT,"
                +FLAT_COL_ADDRESS + " TEXT,"
                +ENTRANCE_COL_ADDRESS + " TEXT,"
                +FLOOR_COL_ADDRESS + " TEXT,"
                +DOORPHONE_COL_ADDRESS+" TEXT,"
                +LABEL_COL_ADDRESS+ " TEXT)";

        String query4 = "CREATE TABLE "+ TABLE_NAME_ORDER+ " ("
                +ID_COL_ORDER+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +ADDRESS_COL_ORDER+ " TEXT,"
                +DATE_COL_ORDER+ " TEXT,"
                +PHONE_COL_ORDER+ " TEXT,"
                +COMMENT_COL_ORDER+ " TEXT)";

        String query5 = "CREATE TABLE "+TABLE_NAME_PATIENT_ORDER+" ("
                +ID_COL_PATIENT_ORDER+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +ORDER_COL_PATIENT_ORDER +" INTEGER, "
                +PATIENT_COL_PATIENT_ORDER+ " INTEGER, "
                +"FOREIGN KEY (" + PATIENT_COL_PATIENT_ORDER+") REFERENCES " +
                TABLE_NAME_PATIENT+ "("+ID_COL_ANALYSIS_IN_PATIENT+"), "
                +"FOREIGN KEY (" + ORDER_COL_PATIENT_ORDER+") REFERENCES " +
                TABLE_NAME_ORDER+ "("+ID_COL_ORDER+") )"
                ;

        String query6 = "CREATE TABLE "+TABLE_NAME_ANALYSIS_IN_PATIENT +" ("
                +ID_COL_ANALYSIS_IN_PATIENT+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +ANALYSIS_COL_ANALYSIS_IN_PATIENT + " INTEGER, "
                +PATIENT_IN_ORDER_COL_ANALYSIS_IN_PATIENT+ " INTEGER, "
                + "FOREIGN KEY (" + PATIENT_IN_ORDER_COL_ANALYSIS_IN_PATIENT+") REFERENCES " +
                TABLE_NAME_PATIENT_ORDER+ "("+ID_COL_PATIENT_ORDER+") )"
                ;
        db.execSQL(query);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
        db.execSQL(query5);
        db.execSQL(query6);
    }

    public void addAddress(Address address){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ADDRESS_COL_ADDRESS,address.getAdd());
        contentValues.put(FLAT_COL_ADDRESS,address.getFlat());
        contentValues.put(ENTRANCE_COL_ADDRESS,address.getEntrance());
        contentValues.put(FLOOR_COL_ADDRESS,address.getFloor());
        contentValues.put(DOORPHONE_COL_ADDRESS,address.getDoorphone());
        contentValues.put(LABEL_COL_ADDRESS,address.getLabel());

        database.insert(TABLE_NAME_ADDRESS,null,contentValues);
        database.close();
    }

    public void addOrder(){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ADDRESS_COL_ORDER,"");
        contentValues.put(DATE_COL_ORDER,"");
        contentValues.put(PHONE_COL_ORDER,"");
        contentValues.put(COMMENT_COL_ORDER,"");
        contentValues.put(ADDRESS_COL_ORDER,"");

        database.insert(TABLE_NAME_ORDER,null,contentValues);
        database.close();
    }
    public void setOrderAddress(String address){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ADDRESS_COL_ORDER,address);
        database.update(TABLE_NAME_ORDER,contentValues,"id = "+getLastIndexOrder(),null);
        database.close();
    }

    public void setDateTimeOrder(String dateTimeOrder){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ADDRESS_COL_ORDER,dateTimeOrder);
        database.update(TABLE_NAME_ORDER,contentValues,"id = "+getLastIndexOrder(),null);
        database.close();
    }

    public int getLastIndexOrder(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT MAX("+ID_COL_ORDER+") FROM " + TABLE_NAME_ORDER, null);
        c.moveToFirst();
        int id = c.getInt(0);
        c.close();
        return id;
    }

    public boolean AddressExist(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE_NAME_ADDRESS,null);
        boolean exists = (cursor.getCount()>0);
        return exists;
    }
    public Address getAddress(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE_NAME_ADDRESS,null);
        Address address= null;
        if(cursor.moveToFirst()) {
            address = new Address(cursor.getString(1),cursor.getString(2),
                    cursor.getString(3),cursor.getString(4),cursor.getString(5),
                    cursor.getString(6));
        }
        cursor.close();
        return address;
    }

    public void addPatientInOrder(Integer patient_id){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ORDER_COL_PATIENT_ORDER,getLastIndexOrder());
        contentValues.put(PATIENT_COL_PATIENT_ORDER, patient_id);
        database.insert(TABLE_NAME_PATIENT_ORDER,null,contentValues);
        database.close();
    }

   public boolean PatientAlreadyInOrder(Integer id_patient){
       SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
       Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE_NAME_PATIENT_ORDER +" WHERE id_patient_in_order ="+ id_patient,null);
       boolean exists = (cursor.getCount()>0);
       return exists;
   }

    public void addAnalysis(Analysis analysis){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID_ANALYSIS_COL_BASKET,analysis.getId());
        contentValues.put(CATEGORY_COL_BASKET,analysis.getCategory());
        contentValues.put(NAME_COL_BASKET,analysis.getName());
        contentValues.put(DESCRIPTION_COL_BASKET,analysis.getDescription());
        contentValues.put(PRICE_COL_BASKET,analysis.getPrice());
        contentValues.put(TIME_RESULT_COL_BASKET,analysis.getTime_result());
        contentValues.put(PREPARATION_COL_BASKET,analysis.getPreparation());
        contentValues.put(BIO_COL_BASKET,analysis.getBio());
        database.insert(TABLE_NAME_BASKET,null,contentValues);
        database.close();
    }

    //метод для добавления карточки пациента
   public void addCardPatient(CardPatient cardPatient){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID_PATIENT_COL_PATIENT,cardPatient.getId());
        contentValues.put(FIRST_NAME_COL_PATIENT,cardPatient.getFirst_name());
        contentValues.put(LAST_NAME_COL_PATIENT,cardPatient.getLast_name());
        contentValues.put(MIDDLE_NAME_COL_PATIENT,cardPatient.getMiddle_name());
        contentValues.put(DATE_OF_BIRTH_COL_PATIENT,cardPatient.getDate_of_birth());
        contentValues.put(POL_COL_PATIENT,cardPatient.getPol());
        contentValues.put(IMAGE_COL_PATIENT,cardPatient.getImage());
        database.insert(TABLE_NAME_PATIENT,null,contentValues);
        database.close();
    }

    //метод для показа профиля (id для запроса на сервер)
    public Integer getCardPatientId(){
        int id = -1;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor =sqLiteDatabase.rawQuery("SELECT MIN("+ID_PATIENT_COL_PATIENT+") FROM "+TABLE_NAME_PATIENT,null);
        try {
            if (cursor != null && cursor.moveToFirst()) {
                id = cursor.getInt(0);
            }
        }finally {
            cursor.close();
        }
        return id;
    }

    //метод для получения списка анализов
    public ArrayList<Analysis> readAnalysis(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE_NAME_BASKET,null);
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


    public ArrayList<CardPatient> readPatient(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE_NAME_PATIENT,null);
        ArrayList<CardPatient> patients = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                patients.add(new CardPatient(cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return patients;
    }

    public void addPatient(Integer analysis){
        SQLiteDatabase database = this.getWritableDatabase();

        int number_patient = getPatient(analysis);
        ++number_patient;

        ContentValues values = new ContentValues();
        values.put(NUMBER_COL_BASKET,number_patient);
        database.update(TABLE_NAME_BASKET,values,"id_analysis = ?",new String[]{String.valueOf(analysis)});
        database.close();
    }
    public void deletePatient(Integer analysis){
        SQLiteDatabase database = this.getWritableDatabase();

        int number_patient = getPatient(analysis);
        --number_patient;

        ContentValues values = new ContentValues();
        values.put(NUMBER_COL_BASKET,number_patient);
        database.update(TABLE_NAME_BASKET,values,"id_analysis = ?",new String[]{String.valueOf(analysis)});
        database.close();
    }

    public int getPatient(Integer analysis) {
        int number= 0;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME_BASKET,
                new  String[]{NUMBER_COL_BASKET},
                " id_analysis=?",
                new String[]{String.valueOf(analysis)},null,null,null,null);
        try {
            if (cursor != null && cursor.moveToFirst()) {
                number = cursor.getInt(0);
            }
        }finally {
            cursor.close();
        }
        return number;
    }

    public void deleteAllAnalysis(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("delete from "+ TABLE_NAME_BASKET);
    }

    public boolean AnalysisExists(Integer analysis){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE_NAME_BASKET +" WHERE id_analysis ="+analysis,null);
        boolean exists = (cursor.getCount()>0);
        return exists;
    }
    public boolean PatientExists(Integer patient){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE_NAME_PATIENT +" WHERE id_patient ="+patient,null);
        boolean exists = (cursor.getCount()>0);
        return exists;
    }

    public void deleteAnalysis(Integer analysis){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_NAME_BASKET,"id_analysis=?", new String[]{String.valueOf(analysis)});
        database.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_BASKET);
        onCreate(db);
    }

    public int getSumPrice(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT SUM(price*num) FROM " + TABLE_NAME_BASKET, null);
        c.moveToFirst();
        int sum = c.getInt(0);
        c.close();
        return sum;
    }



    public void updatePatient(CardPatient cardPatient){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int patientId = cardPatient.getId();
        contentValues.put(FIRST_NAME_COL_PATIENT,cardPatient.getFirst_name());
        contentValues.put(LAST_NAME_COL_PATIENT,cardPatient.getLast_name());
        contentValues.put(MIDDLE_NAME_COL_PATIENT,cardPatient.getMiddle_name());
        contentValues.put(DATE_OF_BIRTH_COL_PATIENT,cardPatient.getDate_of_birth());
        contentValues.put(POL_COL_PATIENT,cardPatient.getPol());
        contentValues.put(IMAGE_COL_PATIENT,cardPatient.getImage());
        database.update(TABLE_NAME_PATIENT,contentValues,"id_patient = "+patientId,null);
        database.close();
    }
}
