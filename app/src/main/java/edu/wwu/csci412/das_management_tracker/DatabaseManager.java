package edu.wwu.csci412.das_management_tracker;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
public class DatabaseManager extends SQLiteOpenHelper{

    /*DatabaseManager class contains fields and methods that control a database of donation centers */

    private static final String DATABASE_NAME = "Diary";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_DIARY = "entries";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String TIME = "time";
    private static final String DATE = "date";
    private static final String TEXT = "text";
    private static final String TABLE_PINS = "pins";
    private static final String IDPin = "idPin";
    private static final String DATEPIN = "date";
    private static final String X = "x";
    private static final String Y = "y";




    public DatabaseManager( Context context ) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }
    //DiaryEntry(int ID, String title, String text, String date, String time){
    /*Initializes our database */
    public void onCreate( SQLiteDatabase db ) {
    // build sql create statement
    //Fix create table query
        String sqlCreate = "create table " + TABLE_DIARY + " ( ";
        sqlCreate +=  ID + " integer primary key autoincrement, ";
        sqlCreate +=  TITLE + " title, ";
        sqlCreate +=  TEXT + " text, ";
        sqlCreate +=  DATE + " text, ";
        sqlCreate +=  TIME + " text )";

        db.execSQL( sqlCreate );

        String sqlCreatePin = "create table " + TABLE_PINS + " ( ";
        sqlCreatePin +=  IDPin + " integer primary key autoincrement, ";
        sqlCreatePin +=  X + " double, ";
        sqlCreatePin +=  Y + " double, ";
        sqlCreatePin +=  DATEPIN + " text )";

        db.execSQL( sqlCreatePin );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert( DiaryEntry entry ) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlInsert = "insert into " + TABLE_DIARY;
        sqlInsert += " values( null, ' ";
        sqlInsert += entry.getTitle( ) + "', ' ";
        sqlInsert += entry.getText( ) + "', ' ";
        sqlInsert += entry.getDate( ) + "', ' ";
        sqlInsert += entry.getTime( ) + "' )";

        db.execSQL( sqlInsert );
        db.close( );
    }
    public void insert( Pin newPin ) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlInsert = "insert into " + TABLE_PINS;
        sqlInsert += " values( null, ' ";
        sqlInsert += newPin.getX( ) + "', ' ";
        sqlInsert += newPin.getY( ) + "', ' ";
        sqlInsert += newPin.getDate( ) + "' )";

        db.execSQL( sqlInsert );
        db.close( );
    }

    /*selectAll returns all the Charity items currently in our database */
    public ArrayList<DiaryEntry> selectAllEntries( ) {
        String sqlQuery = "select * from " + TABLE_DIARY;

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        ArrayList<DiaryEntry> entries = new ArrayList<>( );
        while( cursor.moveToNext( ) ) {
            DiaryEntry currentDiary
                = new DiaryEntry(cursor.getInt( 0 ), cursor.getString( 1 ),
                cursor.getString( 2 ),cursor.getString( 3 ),cursor.getString( 4 ));
            entries.add(currentDiary);
        }
        db.close( );
        return entries;
    }
    public ArrayList<Pin> selectAllPins( ) {
        String sqlQuery = "select * from " + TABLE_PINS;

        SQLiteDatabase db = this.getWritableDatabase( );
        Cursor cursor = db.rawQuery( sqlQuery, null );

        ArrayList<Pin> pins = new ArrayList<>( );
        while( cursor.moveToNext( ) ) {
            Pin currentPin
                    = new Pin(cursor.getInt( 0 ), cursor.getDouble( 1 ),
                    cursor.getDouble( 2 ),cursor.getString( 3 ));
            pins.add(currentPin);
        }
        db.close( );
        return pins;
    }

}

