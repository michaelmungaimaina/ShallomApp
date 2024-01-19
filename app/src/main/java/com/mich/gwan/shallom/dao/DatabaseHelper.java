/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 1 / 10
 */

package com.mich.gwan.shallom.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.mich.gwan.shallom.enums.Status;
import com.mich.gwan.shallom.model.Doctrine;
import com.mich.gwan.shallom.model.Event;
import com.mich.gwan.shallom.model.LessonQuarter;
import com.mich.gwan.shallom.model.LessonQuestion;
import com.mich.gwan.shallom.model.LessonWeek;
import com.mich.gwan.shallom.model.Song;
import com.mich.gwan.shallom.model.SongChorus;
import com.mich.gwan.shallom.model.SongStanza;
import com.mich.gwan.shallom.model.User;
import com.mich.gwan.shallom.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "cogmers.db";

    // Table names
    private static final String TABLE_USER = "users";
    private static final String TABLE_EVENT = "events";
    private static final String TABLE_DOCTRINE = "doctrines";
    private static final String TABLE_REQUEST = "requests";
    private static final String TABLE_LESSON_QUARTER = "lesson_quarter";
    private static final String TABLE_LESSON_QUESTION = "questions";
    private static final String TABLE_LESSON_WEEK = "lesson_week";
    private static final String TABLE_SONG = "songs";
    private static final String TABLE_SONG_STANZA = "song_stanzas";
    private static final String TABLE_SONG_CHORUS = "song_choruses";

    // Columns for table user
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_FIRSTNAME = "firstname";
    private static final String COLUMN_USER_LASTNAME = "lastname";
    private static final String COLUMN_USER_EMAIL = "email";
    private static final String COLUMN_USER_PHONE = "phone";
    private static final String COLUMN_USER_USERNAME = "username";
    private static final String COLUMN_USER_COUNTY = "county";
    private static final String COLUMN_USER_SUB_COUNTY = "sub_county";
    private static final String COLUMN_USER_VILLAGE = "village";
    private static final String COLUMN_USER_GROUP = "group";
    private static final String COLUMN_USER_DESIGNATION = "designation";
    private static final String COLUMN_USER_PASSWORD = "password";
    private static final String COLUMN_USER_REG_DATE = "registration_date";
    private static final String COLUMN_USER_STATUS = "status";
    private static final String COLUMN_USER_PROF = "profile_pic";

    // Columns for table events
    private static final String COLUMN_EVENT_ID = "event_id";
    private static final String COLUMN_EVENT_LOCATION = "event_location";
    private static final String COLUMN_EVENT_TITLE = "event_title";
    private static final String COLUMN_EVENT_DESCRIPTION = "event_description";
    private static final String COLUMN_EVENT_START_DATE = "start_date";
    private static final String COLUMN_EVENT_END_DATE = "end_date";
    private static final String COLUMN_EVENT_REG_DATE = "registration_date";
    private static final String COLUMN_EVENT_REG_BY = "registered_by";

    // Columns for table doctrine
    private static final String COLUMN_DOCTRINE_ID = "doc_id";
    private static final String COLUMN_DOCTRINE_CONTENT = "description";
    private static final String COLUMN_DOCTRINE_REF = "doc_reference";

    // Columns for table lesson_questions
    private static final String COLUMN_LESSON_QUESTION_ID = "question_id";
    private static final String COLUMN_LESSON_QUESTION_WEEK = "week_id";
    private static final String COLUMN_LESSON_QUESTION_QUESTION = "question";
    private static final String COLUMN_LESSON_QUESTION_BOOKS = "books";
    private static final String COLUMN_LESSON_QUESTION_EXPLANATION = "explanation";
    private static final String COLUMN_LESSON_QUESTION_LANGUAGE = "language";
    private static final String COLUMN_LESSON_QUESTION_REG_DATE = "registration_date";
    private static final String COLUMN_LESSON_QUESTION_REG_BY = "registered_by";

    // Columns for table lesson_quarter
    private static final String COLUMN_LESSON_QUARTER_ID = "quarter_id";
    private static final String COLUMN_LESSON_QUARTER_YEAR = "quarter_year";
    private static final String COLUMN_LESSON_QUARTER_MONTH = "quarter_month";
    private static final String COLUMN_LESSON_QUARTER_REG_DATE = "registration_date";
    private static final String COLUMN_LESSON_QUARTER_REG_BY = "registered_by";

    // Columns for table lesson_week
    private static final String COLUMN_LESSON_WEEK_ID = "week_id";
    private static final String COLUMN_LESSON_WEEK_TITLE = "lesson_title";
    private static final String COLUMN_LESSON_WEEK_READING = "lesson_reading";
    private static final String COLUMN_LESSON_WEEK_MEM_VERSE = "memory_verse";
    private static final String COLUMN_LESSON_WEEK_LANGUAGE = "language";
    private static final String COLUMN_LESSON_WEEK_QUARTER = "quarter_id";
    private static final String COLUMN_LESSON_WEEK_REG_DATE = "registration_date";
    private static final String COLUMN_LESSON_WEEK_REG_BY = "registered_by";

    // Columns for table songs
    private static final String COLUMN_SONG_ID = "song_id";
    private static final String COLUMN_SONG_TITLE = "song_title";
    private static final String COLUMN_SONG_REG_DATE = "registration_date";
    private static final String COLUMN_SONG_CATEGORY = "song_category";
    private static final String COLUMN_SONG_GROUP = "song_group";
    private static final String COLUMN_SONG_AUTHOR = "song_author";
    private static final String COLUMN_SONG_PREFERENCE = "song_preference";

    // Columns for table Chorus
    private static final String COLUMN_CHORUS_ID = "chorus_id";
    private static final String COLUMN_CHORUS_SONG_ID = "song_id";
    private static final String COLUMN_CHORUS_CONTENT = "chorus_content";

    // Columns for table Stanzas
    private static final String COLUMN_STANZA_ID = "stanza_id";
    private static final String COLUMN_STANZA_SONG_ID = "song_id";
    private static final String COLUMN_STANZA_CONTENT = "stanza_content";

    /**
     * SQL queries for creating all the data tables used in this context
     *
     */

    // create table user sql query
    private final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY UNIQUE NOT NULL,"
            + COLUMN_USER_FIRSTNAME + " TEXT,"
            + COLUMN_USER_LASTNAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT,"
            + COLUMN_USER_PHONE + " TEXT,"
            + COLUMN_USER_USERNAME + " TEXT,"
            + COLUMN_USER_COUNTY + " TEXT,"
            + COLUMN_USER_SUB_COUNTY + " TEXT,"
            + COLUMN_USER_VILLAGE + " TEXT,"
            + COLUMN_USER_GROUP + " TEXT,"
            + COLUMN_USER_DESIGNATION + " TEXT,"
            + COLUMN_USER_REG_DATE + " TEXT,"
            + COLUMN_USER_STATUS + " TEXT,"
            + COLUMN_USER_PASSWORD + " TEXT,"
            + COLUMN_USER_PROF + " BLOB" + ")";

    // create table event sql query
    private final String CREATE_EVENT_TABLE = "CREATE TABLE " + TABLE_EVENT + "("
            + COLUMN_EVENT_ID + " INTEGER PRIMARY KEY UNIQUE NOT NULL,"
            + COLUMN_EVENT_LOCATION + " TEXT,"
            + COLUMN_EVENT_TITLE + " TEXT,"
            + COLUMN_EVENT_DESCRIPTION + " TEXT,"
            + COLUMN_EVENT_START_DATE + " TEXT,"
            + COLUMN_EVENT_END_DATE + " TEXT,"
            + COLUMN_EVENT_REG_DATE + " TEXT,"
            + COLUMN_EVENT_REG_BY + " TEXT" + ")";

    // create table doctrine sql query
    private final String CREATE_DOCTRINE_TABLE = "CREATE TABLE " + TABLE_DOCTRINE + "("
            + COLUMN_DOCTRINE_ID + " INTEGER PRIMARY KEY UNIQUE NOT NULL,"
            + COLUMN_DOCTRINE_CONTENT + " TEXT NOT NULL,"
            + COLUMN_DOCTRINE_REF + " TEXT NOT NULL" + ")";

    // create table lesson_question sql query
    private final String CREATE_LESSON_QUESTION_TABLE = "CREATE TABLE " + TABLE_LESSON_QUESTION + "("
            + COLUMN_LESSON_QUESTION_ID + " INTEGER PRIMARY KEY UNIQUE NOT NULL,"
            + COLUMN_LESSON_QUESTION_WEEK + " TEXT,"
            + COLUMN_LESSON_QUESTION_QUESTION + " TEXT,"
            + COLUMN_LESSON_QUESTION_BOOKS + " TEXT,"
            + COLUMN_LESSON_QUESTION_EXPLANATION + " TEXT,"
            + COLUMN_LESSON_QUESTION_LANGUAGE + " TEXT,"
            + COLUMN_LESSON_QUESTION_REG_DATE + " TEXT,"
            + COLUMN_LESSON_QUESTION_REG_BY + " TEXT" + ")";

    // create table lesson_question sql query
    private final String CREATE_LESSON_QUARTER_TABLE = "CREATE TABLE " + TABLE_LESSON_QUARTER + "("
            + COLUMN_LESSON_QUARTER_ID + " INTEGER PRIMARY KEY UNIQUE NOT NULL,"
            + COLUMN_LESSON_QUARTER_YEAR + " TEXT NOT NULL,"
            + COLUMN_LESSON_QUARTER_MONTH + " TEXT NOT NULL,"
            + COLUMN_LESSON_QUARTER_REG_DATE + " TEXT NOT NULL,"
            + COLUMN_LESSON_QUARTER_REG_BY + " TEXT NOT NULL" + ")";

    // create table lesson_week sql query
    private final String CREATE_LESSON_WEEK_TABLE = "CREATE TABLE " + TABLE_LESSON_WEEK + "("
            + COLUMN_LESSON_WEEK_ID + " INTEGER PRIMARY KEY UNIQUE NOT NULL,"
            + COLUMN_LESSON_WEEK_TITLE + " TEXT NOT NULL,"
            + COLUMN_LESSON_WEEK_READING + " TEXT NOT NULL,"
            + COLUMN_LESSON_WEEK_MEM_VERSE + " TEXT NOT NULL,"
            + COLUMN_LESSON_WEEK_LANGUAGE + " TEXT NOT NULL,"
            + COLUMN_LESSON_WEEK_QUARTER + " TEXT NOT NULL,"
            + COLUMN_LESSON_WEEK_REG_DATE + " TEXT NOT NULL,"
            + COLUMN_LESSON_WEEK_REG_BY + " TEXT NOT NULL" + ")";

    // create table song sql query
    private final String CREATE_SONG_TABLE = "CREATE TABLE " + TABLE_SONG + "("
            + COLUMN_SONG_ID + " INTEGER PRIMARY KEY UNIQUE NOT NULL,"
            + COLUMN_SONG_TITLE + " TEXT NOT NULL,"
            + COLUMN_SONG_REG_DATE + " TEXT NOT NULL,"
            + COLUMN_SONG_CATEGORY + " TEXT NOT NULL,"
            + COLUMN_SONG_GROUP + " TEXT NOT NULL,"
            + COLUMN_SONG_AUTHOR + " TEXT NOT NULL,"
            + COLUMN_SONG_PREFERENCE + " TEXT NOT NULL" + ")";

    // create table chorus sql query
    private final String CREATE_CHORUS_TABLE = "CREATE TABLE " + TABLE_SONG_CHORUS + "("
            + COLUMN_CHORUS_ID + " INTEGER PRIMARY KEY UNIQUE NOT NULL,"
            + COLUMN_CHORUS_SONG_ID + " TEXT NOT NULL,"
            + COLUMN_CHORUS_CONTENT + " TEXT NOT NULL" + ")";

    // create table stanza sql query
    private final String CREATE_STANZA_TABLE = "CREATE TABLE " + TABLE_SONG_STANZA + "("
            + COLUMN_STANZA_ID + " INTEGER PRIMARY KEY UNIQUE NOT NULL,"
            + COLUMN_STANZA_SONG_ID + " TEXT NOT NULL,"
            + COLUMN_STANZA_CONTENT + " TEXT NOT NULL" + ")";

    /**
     * SQL DROP QUERIES FOR ALL TABLES
     */

    private final String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private final String DROP_EVENT_TABLE = "DROP TABLE IF EXISTS " + TABLE_EVENT;
    private final String DROP_DOCTRINE_TABLE = "DROP TABLE IF EXISTS " + TABLE_DOCTRINE;
    private final String DROP_LESSON_QUESTION_TABLE = "DROP TABLE IF EXISTS " + TABLE_LESSON_QUESTION;
    private final String DROP_LESSON_QUARTER_TABLE = "DROP TABLE IF EXISTS " + TABLE_LESSON_QUARTER;
    private final String DROP_LESSON_WEEK_TABLE = "DROP TABLE IF EXISTS " + TABLE_LESSON_WEEK;
    private final String DROP_SONG_TABLE = "DROP TABLE IF EXISTS " + TABLE_SONG;
    private final String DROP_CHORUS_TABLE = "DROP TABLE IF EXISTS " + TABLE_SONG_CHORUS;
    private final String DROP_STANZA_TABLE = "DROP TABLE IF EXISTS " + TABLE_SONG_STANZA;

    /**
     * Constructor for DatabaseHelper
     * It is a helper object to create, open, and/or manage the database.
     * @param context to use for locating paths to the the database
     */

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creation of tables and initial population of tables
     * and is called when database is created.
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_EVENT_TABLE);
        db.execSQL(CREATE_DOCTRINE_TABLE);
        db.execSQL(CREATE_LESSON_QUESTION_TABLE);
        db.execSQL(CREATE_LESSON_QUARTER_TABLE);
        db.execSQL(CREATE_LESSON_WEEK_TABLE);
        db.execSQL(CREATE_SONG_TABLE);
        db.execSQL(CREATE_CHORUS_TABLE);
        db.execSQL(CREATE_STANZA_TABLE);

    }

    /**
     * Called when database needs to be upgraded.
     * It executes within a transaction.
     * @param db The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_EVENT_TABLE);
        db.execSQL(DROP_DOCTRINE_TABLE);
        db.execSQL(DROP_LESSON_QUESTION_TABLE);
        db.execSQL(DROP_LESSON_QUARTER_TABLE);
        db.execSQL(DROP_LESSON_WEEK_TABLE);
        db.execSQL(DROP_SONG_TABLE);
        db.execSQL(DROP_CHORUS_TABLE);
        db.execSQL(DROP_STANZA_TABLE);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    /**
     * Creates a new user record
     * @param par User data model
     */
    public void addUser(User par){
        SQLiteDatabase db  = this.getWritableDatabase();
        byte[] userProfile = ImageUtils.bitmapToByteArray(par.getUserProf());
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, par.getUserId());
        values.put(COLUMN_USER_FIRSTNAME, par.getFirstName());
        values.put(COLUMN_USER_LASTNAME, par.getLastName());
        values.put(COLUMN_USER_EMAIL, par.getEmail());
        values.put(COLUMN_USER_PHONE, par.getPhone());
        values.put(COLUMN_USER_USERNAME, par.getUsername());
        values.put(COLUMN_USER_COUNTY, par.getCounty());
        values.put(COLUMN_USER_SUB_COUNTY, par.getSubCounty());
        values.put(COLUMN_USER_VILLAGE, par.getVillage());
        values.put(COLUMN_USER_GROUP, par.getUserGroup());
        values.put(COLUMN_USER_DESIGNATION, par.getDesignation());
        values.put(COLUMN_USER_REG_DATE, par.getRegistrationDate());
        values.put(COLUMN_USER_STATUS, par.getStatus().ordinal());
        values.put(COLUMN_USER_PASSWORD, par.getUserId());
        values.put(COLUMN_USER_PROF, userProfile);

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    @SuppressLint("Range")
    public List<User> getAllUsers(){
        String[] columns = {"*"};
        String sortOrder = COLUMN_USER_ID + " ASC";
        List<User> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_USER,columns, null, null, null, null,
                sortOrder);

        if (cursor != null && cursor.getCount() > 0){
            if (cursor.moveToFirst()){
                do {
                    User par = new User();
                    par.setUserId(cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID)));
                    par.setFirstName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_FIRSTNAME)));
                    par.setLastName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_LASTNAME)));
                    par.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                    par.setPhone(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PHONE)));
                    par.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USER_USERNAME)));
                    par.setCounty(cursor.getString(cursor.getColumnIndex(COLUMN_USER_COUNTY)));
                    par.setSubCounty(cursor.getString(cursor.getColumnIndex(COLUMN_USER_SUB_COUNTY)));
                    par.setVillage(cursor.getString(cursor.getColumnIndex(COLUMN_USER_VILLAGE)));
                    par.setUserGroup(cursor.getString(cursor.getColumnIndex(COLUMN_USER_GROUP)));
                    par.setDesignation(cursor.getString(cursor.getColumnIndex(COLUMN_USER_DESIGNATION)));
                    par.setRegistrationDate(cursor.getString(cursor.getColumnIndex(COLUMN_USER_REG_DATE)));
                    par.setStatus(Status.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_USER_STATUS))));
                    par.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                    Bitmap bitmap = ImageUtils.byteArrayToBitmap(cursor.getBlob(cursor.getColumnIndex(COLUMN_USER_PROF)));
                    par.setUserProf(bitmap);
                    list.add(par);
                }
                while (cursor.moveToNext());
            }
        }
        assert cursor != null;
        cursor.close();
        return list;

    }

    @SuppressLint("Range")
    public List<User> getUser(String username, String password){
        String[] columns = {"*"};
        String[] selectionArgs = {username, password};
        String selection  = COLUMN_USER_USERNAME + " = ? AND " + COLUMN_USER_PASSWORD + " = ?";
        List<User> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null,null);

        if (cursor != null && cursor.getCount() > 0){
            if (cursor.moveToFirst()) {
                do {
                    User par = new User();
                    par.setUserId(cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID)));
                    par.setFirstName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_FIRSTNAME)));
                    par.setLastName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_LASTNAME)));
                    par.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                    par.setPhone(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PHONE)));
                    par.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USER_USERNAME)));
                    par.setCounty(cursor.getString(cursor.getColumnIndex(COLUMN_USER_COUNTY)));
                    par.setSubCounty(cursor.getString(cursor.getColumnIndex(COLUMN_USER_SUB_COUNTY)));
                    par.setVillage(cursor.getString(cursor.getColumnIndex(COLUMN_USER_VILLAGE)));
                    par.setUserGroup(cursor.getString(cursor.getColumnIndex(COLUMN_USER_GROUP)));
                    par.setDesignation(cursor.getString(cursor.getColumnIndex(COLUMN_USER_DESIGNATION)));
                    par.setRegistrationDate(cursor.getString(cursor.getColumnIndex(COLUMN_USER_REG_DATE)));
                    par.setStatus(Status.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_USER_STATUS))));
                    par.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                    Bitmap bitmap = ImageUtils.byteArrayToBitmap(cursor.getBlob(cursor.getColumnIndex(COLUMN_USER_PROF)));
                    par.setUserProf(bitmap);
                    list.add(par);
                }
                while (cursor.moveToNext());
            }
        }
        assert cursor != null;
        cursor.close();
        return list;
    }

    public boolean checkUser(String username, String password){
        String[] columns = {"*"};
        String[] selectionArgs = {username, password};
        String selection = COLUMN_USER_USERNAME + " = ? AND " + COLUMN_USER_PASSWORD + " = ?";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null,null);

        if (cursor != null){
            try {
                // returns true if empty
                cursor.moveToFirst();
                return cursor.getInt(0) == 0;
            } catch (CursorIndexOutOfBoundsException e) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a new event record
     * @param par Event data model
     */
    public void addEvent(Event par){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EVENT_ID, par.getEventId());
        values.put(COLUMN_EVENT_LOCATION, par.getEventLocation());
        values.put(COLUMN_EVENT_TITLE, par.getEventTitle());
        values.put(COLUMN_EVENT_DESCRIPTION, par.getEventDescription());
        values.put(COLUMN_EVENT_START_DATE, par.getEventStartDate());
        values.put(COLUMN_EVENT_END_DATE, par.getEventEndDate());
        values.put(COLUMN_EVENT_REG_DATE, par.getRegistrationDate());
        values.put(COLUMN_EVENT_REG_BY, par.getRegisteredBy());

        db.insert(TABLE_EVENT, null, values);
        db.close();
    }

    /**
     * Creates a new Doctrine record
     * @param par Doctrine data model
     */
    public void addDoctrine(Doctrine par){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DOCTRINE_ID, par.getDoctrineId());
        values.put(COLUMN_DOCTRINE_CONTENT, par.getDoctrineContent());
        values.put(COLUMN_DOCTRINE_REF, par.getDoctrineRef());

        db.insertWithOnConflict(TABLE_DOCTRINE, null, values,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    /**
     * Creates a new quarter record
     * @param par LessonQuarter data model
     */
    public void addLessonQuarter(LessonQuarter par){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LESSON_QUARTER_ID, par.getQuarterId());
        values.put(COLUMN_LESSON_QUARTER_YEAR, par.getQuarterYear());
        values.put(COLUMN_LESSON_QUARTER_MONTH, par.getQuarterQuarter());
        values.put(COLUMN_LESSON_QUARTER_REG_DATE, par.getRegistrationDate());
        values.put(COLUMN_LESSON_QUARTER_REG_BY, par.getRegisteredBy());

        db.insert(TABLE_LESSON_QUARTER, null, values);
        db.close();
    }

    /**
     * Creates a new question record
     * @param par LessonQuestion data model
     */
    public void addLessonQuestion(LessonQuestion par){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LESSON_QUESTION_ID, par.getQuestionId());
        values.put(COLUMN_LESSON_QUESTION_WEEK, par.getLessonWeekId());
        values.put(COLUMN_LESSON_QUESTION_QUESTION, par.getQuestion());
        values.put(COLUMN_LESSON_QUESTION_BOOKS, par.getQuestionRefBooks());
        values.put(COLUMN_LESSON_QUESTION_EXPLANATION, par.getExplanation());
        values.put(COLUMN_LESSON_QUESTION_LANGUAGE, par.getLessonLanguage().ordinal());
        values.put(COLUMN_LESSON_QUESTION_REG_DATE, par.getRegistrationDate());
        values.put(COLUMN_LESSON_QUESTION_REG_BY, par.getRegisteredBy());

        db.insert(TABLE_LESSON_QUESTION, null, values);
        db.close();
    }


    /**
     * Creates a new week record
     * @param par LessonWeek data model
     */
    public void addLessonWeek(LessonWeek par){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LESSON_WEEK_ID, par.getLessonWeekId());
        values.put(COLUMN_LESSON_WEEK_TITLE, par.getLessonTitle());
        values.put(COLUMN_LESSON_WEEK_READING, par.getLessonReading());
        values.put(COLUMN_LESSON_WEEK_MEM_VERSE, par.getMemoryVerse());
        values.put(COLUMN_LESSON_WEEK_LANGUAGE, par.getLessonLanguage().name());
        values.put(COLUMN_LESSON_WEEK_QUARTER, par.getQuarterQuarter());
        values.put(COLUMN_LESSON_WEEK_REG_DATE, par.getRegistrationDate());
        values.put(COLUMN_LESSON_WEEK_REG_BY, par.getRegisteredBy());

        db.insert(TABLE_LESSON_WEEK, null, values);
        db.close();
    }

    /**
     * Create a new song record
     * @param par Song data model
     */
    public void addSong(Song par){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SONG_ID, par.getSongId());
        values.put(COLUMN_SONG_TITLE, par.getSongTitle());
        values.put(COLUMN_SONG_REG_DATE, par.getSongRegDate());
        values.put(COLUMN_SONG_CATEGORY, par.getSongCategory());
        values.put(COLUMN_SONG_GROUP, par.getSongGroup());
        values.put(COLUMN_SONG_AUTHOR, par.getSongAuthor());
        values.put(COLUMN_SONG_PREFERENCE, par.getPreference().name());

        db.insert(TABLE_SONG, null, values);
        db.close();
    }

    /**
     * Creates a new stanza record
     * @param par SongStanza data model
     */
    public void addSongStanza(SongStanza par){
        SQLiteDatabase db  = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STANZA_ID, par.getStanzaId());
        values.put(COLUMN_STANZA_SONG_ID, par.getSongId());
        values.put(COLUMN_STANZA_CONTENT, par.getStanzaContent());

        db.insert(TABLE_SONG_STANZA, null, values);
        db.close();
    }

    /**
     * Creates a new chorus record
     * @param par SongChorus data model
     */
    public void addSongChorus(SongChorus par){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CHORUS_ID, par.getChorusId());
        values.put(COLUMN_CHORUS_SONG_ID, par.getSongId());
        values.put(COLUMN_CHORUS_CONTENT, par.getChorusContent());

        db.insert(TABLE_SONG_CHORUS, null, values);
        db.close();
    }
}
