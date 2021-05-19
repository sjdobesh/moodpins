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
        private static final String DATE = "date";
        private static final String INPUT = "input";



        public DatabaseManager( Context context ) {
            super( context, DATABASE_NAME, null, DATABASE_VERSION );
        }

        /*Initializes our database */
        public void onCreate( SQLiteDatabase db ) {
            // build sql create statement
            //Fix create table query
            String sqlCreate = "create table " + TABLE_DIARY + "( ";
            sqlCreate +=  ID + " integer primary key autoincrement, ";
            sqlCreate +=  DATE + " text, ";
            sqlCreate +=  INPUT + " double )";

            db.execSQL( sqlCreate );
        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    /*Insert adds a donation center into the database */
        public void insert( DiaryEntry entry ) {
            SQLiteDatabase db = this.getWritableDatabase( );
            String sqlInsert = "insert into " + TABLE_DIARY;
            sqlInsert += " values( null, ' ";
            sqlInsert += entry.getDate( ) + "', ' ";
            sqlInsert += entry.getInput( ) + "' )";

            db.execSQL( sqlInsert );
            db.close( );
        }


        /*selectAll returns all the Charity items currently in our database */
        public ArrayList<DiaryEntry> selectAll( ) {
            String sqlQuery = "select * from " + TABLE_DIARY;

            SQLiteDatabase db = this.getWritableDatabase( );
            Cursor cursor = db.rawQuery( sqlQuery, null );

            ArrayList<DiaryEntry> entries = new ArrayList<>( );
            while( cursor.moveToNext( ) ) {
                DiaryEntry currentDiary
                        = new DiaryEntry(cursor.getInt( 0 ), cursor.getString( 1 ),
                        cursor.getString( 2 ));
                entries.add(currentDiary);
            }
            db.close( );
            return entries;
        }

    }

