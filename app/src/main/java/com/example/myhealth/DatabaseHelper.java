package com.example.myhealth;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

  public DatabaseHelper(@Nullable Context context) {
    super(context, "HanenDb.db", null, 1);
    SQLiteDatabase db = this.getWritableDatabase();
  }

  @Override
  public void onCreate(SQLiteDatabase db ) {

    //creating tables;

    db.execSQL("create table patient(id integer primary key autoincrement,username text,password text); ");
    db.execSQL("create table program (_id integer primary key autoincrement,num_p  integer unique ,date_debut text ,maladie text ,duree text,patienti integer, foreign key(patienti) references patient(id) );");
    db.execSQL("create table Medicament (_id integer primary key autoincrement, ref_med integer unique  , date_deb_cons text, duree text ) ");
    db.execSQL("create table Prise (_id integer primary key autoincrement,num_prise  integer , description text, date text , heure text ,qté integer ,ref_med integer , foreign key (ref_med) references Medicament(ref_med), unique(num_prise,ref_med) ) ");
    db.execSQL("create table Temperature (_id integer primary key autoincrement,num_temp integer unique ,degrés text ,num_p integer ,foreign key (num_p) references program(_id)) ");
    db.execSQL("create table Ligne_medicament(num_p integer , ref_med integer , foreign key (num_p) references program(_id),foreign key (ref_med) references Medicament(ref_med) ,primary key(num_p,ref_med)) ");}

    @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    db.execSQL("drop table if exists patient;");

    db.execSQL("drop table if exists program;");

      db.execSQL("update Medicament set num_p=null;");
      db.execSQL("update Temperature set num_p=null;");

      db.execSQL("drop table if exists Medicament;");
      db.execSQL("update Ligne_medicament set ref_med=null;");
      db.execSQL("update Prise set ref_med=null;");
      db.execSQL("drop table if exists Temperature ;");
      db.execSQL("drop table if exists Ligne_medicament ;");
      db.execSQL("drop table if exists Prise  ;");



  }


  // insert data in patient
  public boolean insertinpatient(String name ,String pwd){
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues contentvalues = new ContentValues();
    contentvalues.put("username",name);
    contentvalues.put("password",pwd);
    long result=db.insert("patient",null,contentvalues);
     if ( result==-1)
       return false;
     else
       return true;


  }

  public String getSinlgeEntry( String userName)
  { SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor=db.query("patient", null, " username=?", new String[]{userName}, null, null, null);
    if(cursor.getCount()<1) // UserName Not Exist
    {
      cursor.close();
      return "NOT EXIST";
    }
    cursor.moveToFirst();
    String password= cursor.getString(cursor.getColumnIndex("password"));
    cursor.close();
    return password;
  }
  public int getidexEntry(String userName)
  {
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor=db.query("patient", null, " username=?", new String[]{userName}, null, null, null);
    if(cursor.getCount()<1) // UserName Not Exist
    {
      cursor.close();
      return 0;
    }
    cursor.moveToFirst();
    int  id= cursor.getInt(cursor.getColumnIndex("id"));
    cursor.close();
    return id;
  }


  public void insertEntry(String userName,String password)
  {SQLiteDatabase db = this.getWritableDatabase();
    ContentValues newValues = new ContentValues();
// Assign values for each row.
    newValues.put("username", userName);
    newValues.put("password",password);

// Insert the row into your table
    db.insert("patient", null, newValues);
///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
  }
  // **** CRUD (Create, Read, Update, Delete) Operations ***** //

  // Adding new User Details
  void insertprogDetails(String nump,String datedeb, String maladie,String duree, int patient){
    //Get the Data Repository in write mode
    SQLiteDatabase db = this.getWritableDatabase();
    //Create a new map of values, where column names are the keys
    ContentValues cValues = new ContentValues();
    cValues.put("num_p", nump);

    cValues.put("date_debut", datedeb);
    cValues.put("maladie", maladie);
    cValues.put("patienti", patient);
    cValues.put("duree", duree);

    // Insert the new row, returning the primary key value of the new row
    long newRowId = db.insert("program",null, cValues);
    db.close();
  }
  void insertmedDetails(String mr,String dbc,String dr, int prg){
    //Get the Data Repository in write mode
    SQLiteDatabase db = this.getWritableDatabase();
    //Create a new map of values, where column names are the keys
    ContentValues dValues = new ContentValues();
    dValues.put("num_p",prg);
    dValues.put("ref_med",mr);
    long newRowId1 = db.insert("Ligne_medicament",null, dValues);
    ContentValues cValues = new ContentValues();
    cValues.put("ref_med", mr);

    cValues.put("date_deb_cons", dbc);


    cValues.put("duree", dr);

    // Insert the new row, returning the primary key value of the new row
    long newRowId2 = db.insert("Medicament",null, cValues);
    db.close();
  }

  void insertpriseDetails(String np,String dsc, String dte,String hr,String qte, int rfm){
    //Get the Data Repository in write mode
    SQLiteDatabase db = this.getWritableDatabase();
    //Create a new map of values, where column names are the keys
    ContentValues cValues = new ContentValues();
    cValues.put("num_prise", np);

    cValues.put("description", dsc);
    cValues.put("date", dte);
    cValues.put("heure", hr);
    cValues.put("qté", qte);
    cValues.put("ref_med", rfm);

    // Insert the new row, returning the primary key value of the new row
    long newRowId = db.insert("Prise",null, cValues);
    db.close();
  }
  void inserttempDetails(String numt,String dgr, int p){
    //Get the Data Repository in write mode
    SQLiteDatabase db = this.getWritableDatabase();
    //Create a new map of values, where column names are the keys
    ContentValues cValues = new ContentValues();
    cValues.put("num_temp", numt);

    cValues.put("degrés", dgr);
    cValues.put("num_p", p);


    // Insert the new row, returning the primary key value of the new row
    long newRowId = db.insert("Temperature",null, cValues);
    db.close();
  }
  // Delete Program Details
  public void Deleteprogram(int progid){
    SQLiteDatabase db = this.getWritableDatabase();
    db.delete("program", "num_p = ?",new String[]{String.valueOf(progid)});
    db.delete("Ligne_medicament", "num_p = ?",new String[]{String.valueOf(progid)});
    db.delete("Temperature", "num_p = ?",new String[]{String.valueOf(progid)});
    db.close();
  }
  public void Deleteprogrammed(int medid, int np){
    SQLiteDatabase db = this.getWritableDatabase();
    db.delete("Ligne_medicament", "ref_med = ? and num_p=?",new String[]{String.valueOf(medid),String.valueOf(np)});
    db.delete("Prise", "ref_med = ?",new String[]{String.valueOf(medid)});

    db.close();
  }
  public void Deletemedprise(String priseid){
    SQLiteDatabase db = this.getWritableDatabase();
    db.delete("Prise", "num_prise = ?",new String[]{String.valueOf(priseid)});
    db.close();
  }
  public void DeleteProgTemp(String priseid){
    SQLiteDatabase db = this.getWritableDatabase();
    db.delete("Temperature", "num_temp = ?",new String[]{String.valueOf(priseid)});
    db.close();
  }
  // Update Program Details
  public int UpdateUProgramDetails(String datedeb, String maladie,String duree, int id){
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cVals = new ContentValues();
    cVals.put("date_debut", datedeb);
    cVals.put("maladie", maladie);
    cVals.put("duree", duree);

    int count = db.update("program", cVals, " num_p= ?",new String[]{String.valueOf(id)});
    return  count;
  }
  public int UpdateUPriseDeta(String dscp, String dt,String heure,String qt, int id){
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues cVals = new ContentValues();
    cVals.put("description", dscp);
    cVals.put("date", dt);
    cVals.put("heure", heure);
    cVals.put("qté", qt);

    int count = db.update("prise", cVals, " num_prise= ?",new String[]{String.valueOf(id)});
    return  count;
  }
  public Cursor fetchAllPrograms() {
    SQLiteDatabase db = this.getWritableDatabase();

    Cursor mCursor = db.query("program", new String[] {
      "_id" , "num_p", "date_debut", "duree" , "maladie","patienti"},
      null, null, null, null, null);

    if (mCursor != null) {
      mCursor.moveToFirst();
    }
    return mCursor;
  }
  public Cursor fetchAllProgramsid(int id) {
    SQLiteDatabase db = this.getWritableDatabase();

    Cursor mCursor = db.query("program", new String[] {
        "_id" , "num_p", "date_debut", "duree" , "maladie","patienti"},
      " patienti=?",new String[]{String.valueOf(id)} ,null, null, null, null);

    if (mCursor != null) {
      mCursor.moveToFirst();
    }
    return mCursor;
  }

  public Cursor fetchAllProgramtempsid(int id) {
    SQLiteDatabase db = this.getWritableDatabase();

    Cursor mCursor = db.query("Temperature", new String[] {
        "_id" , "num_temp", "degrés", "num_P" },
      " num_p=?",new String[]{String.valueOf(id)} ,null, null, null, null);

    if (mCursor != null) {
      mCursor.moveToFirst();
    }
    return mCursor;}


  public Cursor fetchAllMedsPrisesid(int id) {
    SQLiteDatabase db = this.getWritableDatabase();

    Cursor mCursor = db.query("Prise", new String[] {
        "_id" , "num_prise", "description", "date","heure","qté" },
      " ref_med=?",new String[]{String.valueOf(id)} ,null, null, null, null);

    if (mCursor != null) {
      mCursor.moveToFirst();
    }
    return mCursor;
  }
  public Cursor fetchAllMedsPid(int id) {
    SQLiteDatabase db = this.getWritableDatabase();

    Cursor mCursor = db.query("Ligne_medicament L , program P , Medicament M ", new String[] {
        "M._id","M.ref_med" ,"M.date_deb_cons","M.duree" },
      "P.num_p=L.num_p and M.ref_med=L.ref_med and P.num_p=?",new String[]{String.valueOf(id)} ,null, null, null, null);

    if (mCursor != null) {
      mCursor.moveToFirst();
    }
    return mCursor;
  }
}
