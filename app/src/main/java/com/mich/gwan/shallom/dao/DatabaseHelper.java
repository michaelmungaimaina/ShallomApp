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
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.mich.gwan.shallom.enums.Language;
import com.mich.gwan.shallom.enums.Preference;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

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
    private static final String COLUMN_USER_GENDER = "gender";
    private static final String COLUMN_USER_USERNAME = "username";
    private static final String COLUMN_USER_REGION = "region";
    private static final String COLUMN_USER_COUNTY = "county";
    private static final String COLUMN_USER_SUB_COUNTY = "sub_county";
    private static final String COLUMN_USER_VILLAGE = "village";
    private static final String COLUMN_USER_GROUP = "groups";
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
    private static final String COLUMN_LESSON_WEEK_DATE = "week_date";
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
            + COLUMN_USER_GENDER + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT,"
            + COLUMN_USER_PHONE + " TEXT,"
            + COLUMN_USER_USERNAME + " TEXT,"
            + COLUMN_USER_REGION + " TEXT,"
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
            + COLUMN_LESSON_WEEK_DATE + " TEXT NOT NULL,"
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
        values.put(COLUMN_USER_GENDER, par.getGender());
        values.put(COLUMN_USER_USERNAME, par.getUsername());
        values.put(COLUMN_USER_COUNTY, par.getCounty());
        values.put(COLUMN_USER_REGION, par.getRegion());
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

    /**
     * Fetches a list of all available users
     * @return a list af all registered users
     */
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
                    par.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_USER_GENDER)));
                    par.setPhone(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PHONE)));
                    par.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USER_USERNAME)));
                    par.setRegion(cursor.getString(cursor.getColumnIndex(COLUMN_USER_REGION)));
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

    /**
     * Fetches a list of all users from a given village
     * @param village the village to where the users belong
     * @return a list of the members from the given village
     */
    @SuppressLint("Range")
    public List<User> getAllUsers(String village){
        String[] columns = {"*"};
        String selection  = COLUMN_USER_VILLAGE + " = ?";
        String[] selectionArgs  = {village};
        String sortOrder = COLUMN_USER_ID + " ASC";
        List<User> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_USER,columns, selection, selectionArgs, null, null,
                sortOrder);

        if (cursor != null && cursor.getCount() > 0){
            if (cursor.moveToFirst()){
                do {
                    User par = new User();
                    par.setUserId(cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID)));
                    par.setFirstName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_FIRSTNAME)));
                    par.setLastName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_LASTNAME)));
                    par.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_USER_GENDER)));
                    par.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                    par.setPhone(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PHONE)));
                    par.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USER_USERNAME)));
                    par.setRegion(cursor.getString(cursor.getColumnIndex(COLUMN_USER_REGION)));
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

    /**
     * Fetches members in a given village classified in groups
     * @param group classifier of the data
     * @param village the village from where the members belong
     * @return a list of village members for the given group
     */
    @SuppressLint("Range")
    public List<User> getAllUsers(String group, String village){
        String[] columns = {"*"};
        String selection  = COLUMN_USER_GROUP + " = ? AND " + COLUMN_USER_VILLAGE + " = ?";
        String[] selectionArgs  = {group, village};
        String sortOrder = COLUMN_USER_ID + " ASC";
        List<User> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_USER,columns, selection, selectionArgs, null, null,
                sortOrder);

        if (cursor != null && cursor.getCount() > 0){
            if (cursor.moveToFirst()){
                do {
                    User par = new User();
                    par.setUserId(cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID)));
                    par.setFirstName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_FIRSTNAME)));
                    par.setLastName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_LASTNAME)));
                    par.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_USER_GENDER)));
                    par.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                    par.setPhone(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PHONE)));
                    par.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USER_USERNAME)));
                    par.setRegion(cursor.getString(cursor.getColumnIndex(COLUMN_USER_REGION)));
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

    /**
     * Gets the details of the logged user in the mobile number
     * @param username current user username
     * @param password current user password
     * @return a 1 sized list with current user details
     */
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
                    par.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_USER_GENDER)));
                    par.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                    par.setPhone(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PHONE)));
                    par.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USER_USERNAME)));
                    par.setRegion(cursor.getString(cursor.getColumnIndex(COLUMN_USER_REGION)));
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

    /**
     * Checks if the provided user details exist in the database.
     * <p>
     * This method queries the database to determine whether a user with the given username and
     * password exists.
     *
     * @param username The username attribute of the user.
     * @param password The password attribute of the user.
     * @return {@code true} if the user details do not exist; {@code false} otherwise.
     */
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
            } finally {
                cursor.close();
            }
        }
        return false;
    }

    /**
     * Checks for the existence of a duplicate username during user registration.
     * <p>
     * This method queries the database to determine whether the provided username already exists.
     *
     * @param username The username to be checked for duplication.
     * @return {@code true} if the email does not exist (is unique), {@code false} if it already exists.
     */
    public boolean checkUsernameIfExists(String username){
        String[] columns = {COLUMN_USER_USERNAME};
        String selection = COLUMN_USER_USERNAME + " = ? ";
        String[] selectionArgs = {username};
        SQLiteDatabase db  = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);

        if (cursor != null){
            try {
                cursor.moveToFirst();
                return cursor.getInt(0) == 0;
            } catch (CursorIndexOutOfBoundsException e){
                return true;
            } finally {
                cursor.close();
            }
        }
        return false;
    }

    /**
     * Checks for the existence of a duplicate email during user registration.
     * <p>
     * This method queries the database to determine whether the provided email already exists.
     *
     * @param email The email to be checked for duplication.
     * @return {@code true} if the username does not exist (is unique), {@code false} if it already exists.
     */
    public boolean checkEmailIfExists(String email){
        String[] columns = {COLUMN_USER_EMAIL};
        String selection = COLUMN_USER_EMAIL + " = ? ";
        String[] selectionArgs = {email};
        SQLiteDatabase db  = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);

        if (cursor != null){
            try {
                cursor.moveToFirst();
                return cursor.getInt(0) == 0;
            } catch (CursorIndexOutOfBoundsException e){
                return true;
            } finally {
                cursor.close();
            }
        }
        return false;
    }

    /**
     * Checks for the existence of a duplicate designation during user registration.
     * <p>
     * This method queries the database to determine whether the provided designation already exists.
     *
     * @param designation The designation to be checked for duplication.
     * @return {@code true} if the designation does not exist (is unique), {@code false} if it already exists.
     */
    public boolean checkDesignation(String designation) {
        String[] columns = {COLUMN_USER_DESIGNATION};
        String selection = COLUMN_USER_DESIGNATION + " = ? ";
        String[] selectionArgs = {designation};
        SQLiteDatabase db  = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);

        if (cursor != null){
            try {
                cursor.moveToFirst();
                return cursor.getInt(0) == 0;
            } catch (CursorIndexOutOfBoundsException e){
                return true;
            } finally {
                cursor.close();
            }
        }
        return false;
    }

    /**
     * Checks for the existence of a duplicate designation within a specific region and group during user registration.
     * <p>
     * This method queries the database to determine whether the provided combination of region, group, and designation already exists.
     *
     * @param region      The region to filter the search.
     * @param group       The group to filter the search.
     * @param designation The designation to be checked for duplication within the specified region and group.
     * @return {@code true} if the designation does not exist within the given region and group (is unique), {@code false} if it already exists.
     */
    public boolean checkDesignation(String region, String group, String designation) {
        String[] columns = {COLUMN_USER_REGION, COLUMN_USER_GROUP, COLUMN_USER_DESIGNATION};
        String selection = COLUMN_USER_GROUP + " = ? AND " + COLUMN_USER_REGION + " = ? AND " + COLUMN_USER_DESIGNATION + " = ? ";
        String[] selectionArgs = {group, region, designation};
        SQLiteDatabase db  = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);

        if (cursor != null){
            try {
                cursor.moveToFirst();
                return cursor.getInt(0) == 0;
            } catch (CursorIndexOutOfBoundsException e){
                return true;
            } finally {
                cursor.close();
            }
        }
        return false;
    }

    /**
     * Checks for the existence of a duplicate designation within a specific region and group for an office bearer during user registration.
     * <p>
     * This method queries the database to determine whether the provided combination of region, group, and designation already exists
     * specifically for users designated as office bearers and with a limit on the count.
     *
     * @param region      The region to filter the search.
     * @param group       The group to filter the search.
     * @param designation The designation to be checked for duplication within the specified region and group for office bearers.
     * @return {@code true} if the count of the designation within the given region and group for office bearers is less than or equal to 5 (is unique or not present),
     *         {@code false} if it already exceeds the limit.
     */
    public boolean checkDesignationOfficeBearer(String region, String group, String designation) {
        String[] columns = {COLUMN_USER_REGION, COLUMN_USER_GROUP, COLUMN_USER_DESIGNATION};
        String selection = COLUMN_USER_GROUP + " = ? AND " + COLUMN_USER_REGION + " = ? AND " + COLUMN_USER_DESIGNATION + " = ? ";
        String[] selectionArgs = {group, region, designation};
        SQLiteDatabase db  = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);

        if (cursor != null){
            try {
                cursor.moveToFirst();
                return cursor.getInt(0) <= 5;
            } catch (CursorIndexOutOfBoundsException e){
                return true;
            } finally {
                cursor.close();
            }
        }
        return false;
    }

    /**
     * Checks for the existence of a duplicate designation within a specific region, village, and group during user registration.
     * <p>
     * This method queries the database to determine whether the provided combination of region, village, group, and designation
     * already exists for users with the specified details.
     *
     * @param region      The region to filter the search.
     * @param village     The village to filter the search.
     * @param group       The group to filter the search.
     * @param designation The designation to be checked for duplication within the specified region, village, and group.
     * @return {@code true} if the designation does not exist within the given region, village, and group (is unique),
     *         {@code false} if it already exists.
     */
    public boolean checkDesignation(String region, String village, String group, String designation) {
        String[] columns = {COLUMN_USER_REGION, COLUMN_USER_VILLAGE, COLUMN_USER_GROUP, COLUMN_USER_DESIGNATION};
        String selection = COLUMN_USER_GROUP + " = ? AND " + COLUMN_USER_REGION + " = ? AND " + COLUMN_USER_VILLAGE + " = ? " + COLUMN_USER_DESIGNATION + " = ? ";
        String[] selectionArgs = {group, region, village, designation};
        SQLiteDatabase db  = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);

        if (cursor != null){
            try {
                cursor.moveToFirst();
                return cursor.getInt(0) == 0;
            } catch (CursorIndexOutOfBoundsException e){
                return true;
            } finally {
                cursor.close();
            }
        }
        return false;
    }

    /**
     * Checks for the existence of a duplicate designation within a specific village during user registration.
     * <p>
     * This method queries the database to determine whether the provided combination of village and designation already exists.
     *
     * @param village     The village to be checked for duplication.
     * @param designation The designation to be checked for duplication within the specified village.
     * @return {@code true} if the designation does not exist within the given village (is unique), {@code false} if it already exists.
     */
    public boolean checkDesignation(String village, String designation) {
        String[] columns = {COLUMN_USER_VILLAGE, COLUMN_USER_DESIGNATION};
        String selection = COLUMN_USER_VILLAGE + " = ? AND " + COLUMN_USER_DESIGNATION + " = ? ";
        String[] selectionArgs = {village, designation};
        SQLiteDatabase db  = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);

        if (cursor != null){
            try {
                cursor.moveToFirst();
                return cursor.getInt(0) == 0;
            } catch (CursorIndexOutOfBoundsException e){
                return true;
            } finally {
                cursor.close();
            }
        }
        return false;
    }

    /**
     * Checks whether the count of a specific designation in a given village is less than or equal to 5
     * in the SQLite database.
     *
     * @param village     The name of the village to be checked.
     * @param designation The designation for which the count is to be checked.
     * @return            Returns true if the count of the specified designation in the given village
     *                    is less than or equal to 5; otherwise, returns false.
     */
    public boolean checkDesignationDcn(String village, String designation) {
        String[] columns = {COLUMN_USER_VILLAGE, "COUNT(" + COLUMN_USER_DESIGNATION + ") AS designation_count"};
        String selection = COLUMN_USER_VILLAGE + " = ? AND " + COLUMN_USER_DESIGNATION + " = ? ";
        String[] selectionArgs = {village, designation};
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            try {
                @SuppressLint("Range") int designationCount = cursor.getInt(cursor.getColumnIndex("designation_count"));
                return designationCount <= 5;
            } finally {
                cursor.close();
            }
        }
        return false;
    }
    /**
     * Updates the password for a user based on the provided username.
     * <p>
     * This method updates the user's password in the database where the username matches the provided username.
     * </p>
     * @param username The username used in the WHERE clause to identify the user.
     * @param password The new password to be set for the user.
     */
    public void updatePassword(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_PASSWORD, password);

        String whereClause = COLUMN_USER_USERNAME + " = ? ";
        db.update(TABLE_USER,values, whereClause, new String[]{username});
        db.close();
    }

    /**
     * Updates the details of a user based on the provided username and user id.
     * <p>
     *     This method updates the user profile in the database where username and user id matches
     *     the provided username and user id.
     * </p>
     * @param par The User data model containing the details; user id, user firstname, lastname, email,
     *            phone, username, county, village, group, designation, registration date, status,
     *            profile picture, and password.
     */
    public void updateUser(User par){
        SQLiteDatabase db = this.getWritableDatabase();
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

        String whereClause = COLUMN_USER_ID + " = ? AND " + COLUMN_USER_USERNAME + " = ?";
        String[] whereArgs = {String.valueOf(par.getUserId()), par.getUsername()};

        db.update(TABLE_USER, values, whereClause, whereArgs);
        db.close();
    }

    /**
     * Deletes a user from the SQLite database based on the provided user ID and username.
     *
     * @param userId   The unique identifier of the user to be deleted.
     * @param username The username of the user to be deleted.
     */
    public void deleteUser(String userId, String username){
        SQLiteDatabase db  = this.getWritableDatabase();
        String whereClause = COLUMN_USER_ID + " = ? AND " + COLUMN_USER_USERNAME + " = ?";
        String[] whereArgs = {userId, username};

        db.delete(TABLE_USER, whereClause, whereArgs);
        db.close();
    }

    /**
     * Creates a new event record in the database.
     * <p>
     * This method inserts a new event record into the database using the provided event data model.
     *
     * @param par The event data model containing details such as event ID, location, title,
     *            description, start date, end date, registration date, and registered by.
     */
    public void addEvent(Event par){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EVENT_ID, par.getEventId());
        values.put(COLUMN_EVENT_LOCATION, par.getEventLocation());
        values.put(COLUMN_EVENT_TITLE, par.getEventTitle());
        values.put(COLUMN_EVENT_DESCRIPTION, par.getEventDescription());
        values.put(COLUMN_EVENT_START_DATE, String.valueOf(par.getEventStartDate()));
        values.put(COLUMN_EVENT_END_DATE, String.valueOf(par.getEventEndDate()));
        values.put(COLUMN_EVENT_REG_DATE, String.valueOf(par.getRegistrationDate()));
        values.put(COLUMN_EVENT_REG_BY, par.getRegisteredBy());

        db.insert(TABLE_EVENT, null, values);
        db.close();
    }

    /**
     * Retrieves a list of all events from the SQLite database.
     *
     * @return A List of Event objects representing the events in the database.
     */
    @SuppressLint("Range")
    public List<Event> getEvent(){
        String[] columns = {"*"};
        String sortOrder = COLUMN_EVENT_ID + " ASC";
        List<Event> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_EVENT, columns, null, null, null, null, sortOrder);

        if (cursor.moveToFirst()){
            do {
                Event par = new Event();
                par.setEventId(cursor.getInt(cursor.getColumnIndex(COLUMN_EVENT_ID)));
                par.setEventLocation(cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_LOCATION)));
                par.setEventTitle(cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_TITLE)));
                par.setEventDescription(cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_DESCRIPTION)));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    par.setEventStartDate(LocalDateTime.parse(cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_START_DATE))));
                    par.setEventEndDate(LocalDateTime.parse(cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_END_DATE))));
                    par.setRegistrationDate(LocalDateTime.parse(cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_REG_DATE))));
                }
                par.setRegisteredBy(cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_REG_BY)));

                list.add(par);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    /**
     * Retrieves a list of events from the SQLite database based on the specified location.
     *
     * @param location The location for which events are to be retrieved.
     * @return A List of Event objects representing the events in the specified location.
     */
    @SuppressLint("Range")
    public List<Event> getEvent(String location){
        String[] columns = {"*"};
        String selection = COLUMN_EVENT_LOCATION + " = ?";
        String[] selectionArgs = {location};
        String sortOrder = COLUMN_EVENT_ID + " ASC";
        List<Event> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_EVENT, columns, selection, selectionArgs, null, null, sortOrder);

        if (cursor.moveToFirst()){
            do {
                Event par = new Event();
                par.setEventId(cursor.getInt(cursor.getColumnIndex(COLUMN_EVENT_ID)));
                par.setEventLocation(cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_LOCATION)));
                par.setEventTitle(cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_TITLE)));
                par.setEventDescription(cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_DESCRIPTION)));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    par.setEventStartDate(LocalDateTime.parse(cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_START_DATE))));
                    par.setEventEndDate(LocalDateTime.parse(cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_END_DATE))));
                    par.setRegistrationDate(LocalDateTime.parse(cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_REG_DATE))));
                }
                par.setRegisteredBy(cursor.getString(cursor.getColumnIndex(COLUMN_EVENT_REG_BY)));

                list.add(par);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public boolean getEventCount() {
        String[] columns = {"*"};
        SQLiteDatabase db  = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_EVENT, columns, null, null, null, null, null);

        if (cursor != null){
            try {
                cursor.moveToFirst();
                return cursor.getCount() == 0;
            } catch (CursorIndexOutOfBoundsException e){
                return true;
            } finally {
                cursor.close();
            }
        }
        return false;
    }


    /**
     * Updates an existing event in the SQLite database with the provided Event object.
     *
     * @param par The Event object containing updated information for the event to be updated.
     */
    public void updateEvent(Event par){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EVENT_ID, par.getEventId());
        values.put(COLUMN_EVENT_LOCATION, par.getEventLocation());
        values.put(COLUMN_EVENT_TITLE, par.getEventTitle());
        values.put(COLUMN_EVENT_DESCRIPTION, par.getEventDescription());
        values.put(COLUMN_EVENT_START_DATE, String.valueOf(par.getEventStartDate()));
        values.put(COLUMN_EVENT_END_DATE, String.valueOf(par.getEventEndDate()));
        values.put(COLUMN_EVENT_REG_DATE, String.valueOf(par.getRegistrationDate()));
        values.put(COLUMN_EVENT_REG_BY, par.getRegisteredBy());

        String whereClause = COLUMN_EVENT_ID + " = ?";
        String[] whereArgs = {String.valueOf(par.getEventId())};

        db.update(TABLE_EVENT, values, whereClause, whereArgs);
        db.close();
    }

    /**
     * Deletes an event from the SQLite database based on the provided event ID.
     *
     * @param eventId The unique identifier of the event to be deleted.
     */
    public void deleteEvent(String eventId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EVENT, COLUMN_EVENT_ID + " = ?", new String[]{eventId});
        db.close();
    }

    /**
     * Creates a new Doctrine record in the database.
     * <p>
     * This method inserts or replaces a Doctrine record in the database using the provided
     * Doctrine data model.
     *
     * @param par The Doctrine data model containing details such as doctrine ID, content, and reference.
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
     * Retrieves a list of all doctrines from the SQLite database.
     *
     * @return A List of Doctrine objects representing the doctrines in the database.
     */
    @SuppressLint("Range")
    public List<Doctrine> getDoctrine(){
        String[] columns = {"*"};
        String sortOder = COLUMN_DOCTRINE_ID + " ASC";
        List<Doctrine> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_DOCTRINE, columns, null, null, null, null, sortOder);

        if (cursor.moveToFirst()){
            do {
                Doctrine par = new Doctrine();
                par.setDoctrineId(cursor.getInt(cursor.getColumnIndex(COLUMN_DOCTRINE_ID)));
                par.setDoctrineContent(cursor.getString(cursor.getColumnIndex(COLUMN_DOCTRINE_CONTENT)));
                par.setDoctrineRef(cursor.getString(cursor.getColumnIndex(COLUMN_DOCTRINE_REF)));

                list.add(par);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    /**
     * Updates an existing doctrine in the SQLite database with the provided Doctrine object.
     *
     * @param par The Doctrine object containing updated information for the doctrine to be updated.
     */
    public void updateDoctrine(Doctrine par){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DOCTRINE_ID, par.getDoctrineId());
        values.put(COLUMN_DOCTRINE_CONTENT, par.getDoctrineContent());
        values.put(COLUMN_DOCTRINE_REF, par.getDoctrineRef());

        String whereClause = COLUMN_DOCTRINE_ID + " = ?";
        String[] whereArgs = {String.valueOf(par.getDoctrineId())};

        db.update(TABLE_DOCTRINE, values, whereClause, whereArgs);
        db.close();
    }

    /**
     * Deletes a doctrine from the SQLite database based on the provided doctrine ID.
     *
     * @param doctrineId The unique identifier of the doctrine to be deleted.
     */
    public void deleteDoctrine(int doctrineId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DOCTRINE, COLUMN_DOCTRINE_ID + " = ?", new String[] {String.valueOf(doctrineId)});
        db.close();
    }

    /**
     * Creates a new lesson quarter record in the database.
     * <p>
     * This method inserts a new lesson quarter record into the database using the provided
     * LessonQuarter data model.
     *
     * @param par The LessonQuarter data model containing details such as quarter ID, year, month, registration date, and
     *            registered by.
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
     * Retrieves a list of LessonQuarter objects from the database, sorted by quarter ID in descending order.
     *
     * @return A list of LessonQuarter objects containing information about quarters.
     */
    @SuppressLint("Range")
    public List<LessonQuarter> getLessonQuarter(){
        String[] columns = {"*"};
        String sortOrder = COLUMN_LESSON_QUARTER_ID + " DESC";

        List<LessonQuarter> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_LESSON_QUARTER, columns, null, null ,null, null, sortOrder);
        if (cursor.moveToFirst()){
            do {
                LessonQuarter par = new LessonQuarter();
                par.setQuarterId(cursor.getInt(cursor.getColumnIndex(COLUMN_LESSON_QUARTER_ID)));
                par.setQuarterYear(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_QUARTER_YEAR)));
                par.setQuarterQuarter(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_QUARTER_MONTH)));
                par.setRegistrationDate(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_QUARTER_REG_DATE)));
                par.setRegisteredBy(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_QUARTER_REG_BY)));

                list.add(par);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    /**
     * Retrieves a list of LessonQuarters  from the database, sorted by quarter ID in descending order.
     *
     * @return A list of LessonQuarters strings.
     */
    @SuppressLint("Range")
    public List<String> getLessonQuarters(String quarterYear){
        String[] columns = {"*"};
        String sortOrder = COLUMN_LESSON_QUARTER_ID + " DESC";
        String selection = COLUMN_LESSON_QUARTER_YEAR + " = ?";
        String[] selectionArgs = {quarterYear};

        List<String> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_LESSON_QUARTER, columns, selection, selectionArgs ,null, null, sortOrder);
        if (cursor.moveToFirst()){
            do {
                list.add(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_QUARTER_MONTH)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }


    /**
     * Retrieves a list of LessonQuarter objects from the database for a specific quarter ID, sorted by quarter ID in descending order.
     *
     * @param quarterId The ID of the quarter for which LessonQuarter objects are to be retrieved.
     * @return A list of LessonQuarter objects containing information about the specified quarter.
     */
    @SuppressLint("Range")
    public List<LessonQuarter> getLessonQuarter(int quarterId){
        String[] columns = {"*"};
        String sortOrder = COLUMN_LESSON_QUARTER_ID + " DESC";
        String selection = COLUMN_LESSON_QUARTER_ID + " = ?";
        String[] selectionArgs = {String.valueOf(quarterId)};

        List<LessonQuarter> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_LESSON_QUARTER, columns, selection, selectionArgs ,null, null, sortOrder);
        if (cursor.moveToFirst()){
            do {
                LessonQuarter par = new LessonQuarter();
                par.setQuarterId(cursor.getInt(cursor.getColumnIndex(COLUMN_LESSON_QUARTER_ID)));
                par.setQuarterYear(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_QUARTER_YEAR)));
                par.setQuarterQuarter(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_QUARTER_MONTH)));
                par.setRegistrationDate(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_QUARTER_REG_DATE)));
                par.setRegisteredBy(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_QUARTER_REG_BY)));

                list.add(par);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return list;
    }

    /**
     * Updates the information of a LessonQuarter in the database.
     *
     * @param par The LessonQuarter object containing the updated information.
     */
    public void updateLessonQuarter(LessonQuarter par){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_LESSON_QUARTER_ID, par.getQuarterId());
        values.put(COLUMN_LESSON_QUARTER_YEAR, par.getQuarterYear());
        values.put(COLUMN_LESSON_QUARTER_MONTH, par.getQuarterQuarter());
        values.put(COLUMN_LESSON_QUARTER_REG_DATE, par.getRegistrationDate());
        values.put(COLUMN_LESSON_QUARTER_REG_BY, par.getRegisteredBy());

        String whereClause = COLUMN_LESSON_QUARTER_ID + " = ?";
        String[] whereArgs = {String.valueOf(par.getQuarterId())};

        db.update(TABLE_LESSON_QUARTER, values,whereClause, whereArgs);
        db.close();
    }

    /**
     * Deletes a LessonQuarter from the database based on the provided quarter ID.
     *
     * @param quarterId The ID of the quarter to be deleted.
     */
    public void deleteLessonQuarter(int quarterId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LESSON_QUARTER, COLUMN_LESSON_QUARTER_ID + " = ?", new String[]{String.valueOf(quarterId)});
        db.close();
    }

    /**
     * Creates a new lesson question record in the database.
     * <p>
     * This method inserts a new lesson question record into the database using the provided
     * LessonQuestion data model.
     *
     * @param par The LessonQuestion data model containing details such as question ID,
     *            lesson week ID, question, reference books, explanation, lesson language, registration date, and registered by.
     */
    public void addLessonQuestion(LessonQuestion par){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_LESSON_QUESTION_ID, par.getQuestionId());
        values.put(COLUMN_LESSON_QUESTION_WEEK, par.getLessonWeekId());
        values.put(COLUMN_LESSON_QUESTION_QUESTION, par.getQuestion());
        values.put(COLUMN_LESSON_QUESTION_BOOKS, par.getQuestionRefBooks());
        values.put(COLUMN_LESSON_QUESTION_EXPLANATION, par.getExplanation());
        values.put(COLUMN_LESSON_QUESTION_LANGUAGE, par.getLessonLanguage().toString());
        values.put(COLUMN_LESSON_QUESTION_REG_DATE, par.getRegistrationDate());
        values.put(COLUMN_LESSON_QUESTION_REG_BY, par.getRegisteredBy());

        db.insert(TABLE_LESSON_QUESTION, null, values);
        db.close();
    }

    /**
     * Retrieves a list of LessonQuestion objects from the database for a specific week ID, sorted by question ID in ascending order.
     *
     * @param weekId The ID of the week for which LessonQuestion objects are to be retrieved.
     * @return A list of LessonQuestion objects containing information about questions for the specified week.
     */
    @SuppressLint("Range")
    public List<LessonQuestion> getLessonQuestion(int weekId){
        String[] columns = {"*"};
        String sortOrder = COLUMN_LESSON_QUESTION_ID + " ASC";
        String selection = COLUMN_LESSON_QUESTION_WEEK + " = ?";
        String[] selectionArgs = {String.valueOf(weekId)};

        List<LessonQuestion> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_LESSON_QUESTION, columns, selection, selectionArgs, null, null, sortOrder);
        if (cursor.moveToFirst()){
            do {
                LessonQuestion obj = new LessonQuestion();
                obj.setQuestionId(cursor.getInt(cursor.getColumnIndex(COLUMN_LESSON_QUESTION_ID)));
                obj.setLessonWeekId(cursor.getInt(cursor.getColumnIndex(COLUMN_LESSON_QUESTION_WEEK)));
                obj.setQuestion(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_QUESTION_QUESTION)));
                obj.setQuestionRefBooks(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_QUESTION_BOOKS)));
                obj.setExplanation(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_QUESTION_EXPLANATION)));
                obj.setLessonLanguage(Language.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_QUESTION_LANGUAGE))));
                obj.setRegisteredBy(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_QUESTION_REG_BY)));
                obj.setRegistrationDate(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_QUESTION_REG_DATE)));

                list.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return list;

    }

    /**
     * Updates the information of a LessonQuestion in the database.
     *
     * @param par The LessonQuestion object containing the updated information.
     */
    public void updateQuestion(LessonQuestion par){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_LESSON_QUESTION_ID, par.getQuestionId());
        values.put(COLUMN_LESSON_QUESTION_WEEK, par.getLessonWeekId());
        values.put(COLUMN_LESSON_QUESTION_QUESTION, par.getQuestion());
        values.put(COLUMN_LESSON_QUESTION_BOOKS, par.getQuestionRefBooks());
        values.put(COLUMN_LESSON_QUESTION_EXPLANATION, par.getExplanation());
        values.put(COLUMN_LESSON_QUESTION_LANGUAGE, par.getLessonLanguage().toString());
        values.put(COLUMN_LESSON_QUESTION_REG_DATE, par.getRegistrationDate());
        values.put(COLUMN_LESSON_QUESTION_REG_BY, par.getRegisteredBy());

        String whereClause = COLUMN_LESSON_QUESTION_ID + " = ?";
        String[] whereArgs = {String.valueOf(par.getQuestionId())};

        db.update(TABLE_LESSON_QUESTION, values, whereClause, whereArgs);
        db.close();
    }

    /**
     * Deletes a LessonQuestion from the database based on the provided question ID.
     *
     * @param questionId The ID of the question to be deleted.
     */
    public void deleteQuestion(int questionId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LESSON_QUESTION, COLUMN_LESSON_QUESTION_ID + " = ?", new String[]{String.valueOf(questionId)});
        db.close();
    }


    /**
     * Deletes LessonQuestions from the database based on the provided week ID.
     *
     * @param weekId The ID of the week for which LessonQuestions are to be deleted.
     */
    public void deleteQuestion(String weekId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LESSON_QUESTION, COLUMN_LESSON_QUESTION_WEEK + " = ?", new String[]{String.valueOf(weekId)});
        db.close();
    }


    /**
     * Creates a new lesson week record in the database.
     * <p>
     * This method inserts a new lesson week record into the database using the provided LessonWeek data model.
     *
     * @param par The LessonWeek data model containing details such as lesson week ID, lesson title, lesson reading,
     *            memory verse, lesson language, quarter, registration date, and registered by.
     */
    public void addLessonWeek(LessonWeek par){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LESSON_WEEK_ID, par.getLessonWeekId());
        values.put(COLUMN_LESSON_WEEK_DATE, par.getLessonDate());
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
     * Retrieves a list of LessonWeek objects from the database for a specific quarter ID, sorted by week ID in ascending order.
     *
     * @param quarterId The ID of the quarter for which LessonWeek objects are to be retrieved.
     * @return A list of LessonWeek objects containing information about weeks for the specified quarter.
     */
    @SuppressLint("Range")
    public List<LessonWeek> getLessonWeek(int quarterId){
        String[] columns = {"*"};
        String sortOder = COLUMN_LESSON_WEEK_ID + " ASC";
        String selection = COLUMN_LESSON_WEEK_QUARTER + " = ?";
        String[] selectionArgs = {String.valueOf(quarterId)};

        List<LessonWeek> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_LESSON_WEEK, columns, selection, selectionArgs, null, null, sortOder);
        if (cursor.moveToFirst()){
            do {
                LessonWeek obj = new LessonWeek();
                obj.setLessonWeekId(cursor.getInt(cursor.getColumnIndex(COLUMN_LESSON_WEEK_ID)));
                obj.setLessonTitle(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_WEEK_TITLE)));
                obj.setLessonDate(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_WEEK_DATE)));
                obj.setLessonReading(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_WEEK_READING)));
                obj.setMemoryVerse(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_WEEK_MEM_VERSE)));
                obj.setLessonLanguage(Language.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_WEEK_LANGUAGE))));
                obj.setQuarterQuarter(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_WEEK_QUARTER)));
                obj.setRegisteredBy(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_WEEK_REG_BY)));
                obj.setRegistrationDate(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_WEEK_REG_DATE)));

                list.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return list;
    }

    /**
     * Retrieves a list of LessonWeek objects from the database for a specific quarter ID, sorted by week ID in ascending order.
     *
     * @param quarterId The ID of the quarter for which LessonWeek objects are to be retrieved.
     * @return A list of LessonWeek objects containing information about weeks for the specified quarter.
     */
    @SuppressLint("Range")
    public List<LessonWeek> getLessonWeek(int quarterId, String quarter){
        String[] columns = {"*"};
        String sortOder = COLUMN_LESSON_WEEK_ID + " ASC";
        String selection = COLUMN_LESSON_WEEK_QUARTER + " = ? AND " + COLUMN_LESSON_WEEK_QUARTER + " = ?";
        String[] selectionArgs = {String.valueOf(quarterId), quarter};

        List<LessonWeek> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_LESSON_WEEK, columns, selection, selectionArgs, null, null, sortOder);
        if (cursor.moveToFirst()){
            do {
                LessonWeek obj = new LessonWeek();
                obj.setLessonWeekId(cursor.getInt(cursor.getColumnIndex(COLUMN_LESSON_WEEK_ID)));
                obj.setLessonTitle(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_WEEK_TITLE)));
                obj.setLessonDate(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_WEEK_DATE)));
                obj.setLessonReading(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_WEEK_READING)));
                obj.setMemoryVerse(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_WEEK_MEM_VERSE)));
                obj.setLessonLanguage(Language.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_WEEK_LANGUAGE))));
                obj.setQuarterQuarter(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_WEEK_QUARTER)));
                obj.setRegisteredBy(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_WEEK_REG_BY)));
                obj.setRegistrationDate(cursor.getString(cursor.getColumnIndex(COLUMN_LESSON_WEEK_REG_DATE)));

                list.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return list;
    }

    /**
     * Retrieves a list of LessonWeek objects from the database for a specific quarter ID, sorted by week ID in ascending order.
     *
     * @param quarterId The ID of the quarter for which LessonWeek objects are to be retrieved.
     * @return A list of LessonWeek objects containing information about weeks for the specified quarter.
     */
    @SuppressLint("Range")
    public boolean checkLessonQuarter(int quarterId, String quarter){
        String[] columns = {"*"};
        String sortOder = COLUMN_LESSON_WEEK_ID + " ASC";
        String selection = COLUMN_LESSON_WEEK_QUARTER + " = ? AND " + COLUMN_LESSON_WEEK_QUARTER + " = ?";
        String[] selectionArgs = {String.valueOf(quarterId), quarter};

        List<LessonWeek> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_LESSON_WEEK, columns, selection, selectionArgs, null, null, sortOder);
        if (cursor != null){
            try {
                cursor.moveToFirst();
                return cursor.getCount() == 0;
            } catch (CursorIndexOutOfBoundsException e){
                return true;
            } finally {
                cursor.close();
            }
        }
        return false;
    }

    /**
     * Updates the information of a LessonWeek in the database.
     *
     * @param par The LessonWeek object containing the updated information.
     */
    public void updateLessonWeek(LessonWeek par){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_LESSON_WEEK_ID, par.getLessonWeekId());
        values.put(COLUMN_LESSON_WEEK_TITLE, par.getLessonTitle());
        values.put(COLUMN_LESSON_WEEK_READING, par.getLessonReading());
        values.put(COLUMN_LESSON_WEEK_MEM_VERSE, par.getMemoryVerse());
        values.put(COLUMN_LESSON_WEEK_LANGUAGE, par.getLessonLanguage().name());
        values.put(COLUMN_LESSON_WEEK_QUARTER, par.getQuarterQuarter());
        values.put(COLUMN_LESSON_WEEK_DATE, par.getLessonDate());
        values.put(COLUMN_LESSON_WEEK_REG_DATE, par.getRegistrationDate());
        values.put(COLUMN_LESSON_WEEK_REG_BY, par.getRegisteredBy());

        String whereClause = COLUMN_LESSON_WEEK_ID + " = ?";
        String[] whereArgs = {String.valueOf(par.getLessonWeekId())};

        db.update(TABLE_LESSON_WEEK, values, whereClause, whereArgs);
        db.close();
    }

    /**
     * Deletes a LessonWeek from the database based on the provided lesson ID.
     *
     * @param lessonId The ID of the lesson to be deleted.
     */
    public void deleteLesson(int lessonId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LESSON_WEEK, COLUMN_LESSON_WEEK_ID + " = ?", new String[]{String.valueOf(lessonId)});
        db.close();
    }

    /**
     * Deletes LessonWeeks from the database based on the provided quarter ID.
     *
     * @param quarterId The ID of the quarter for which LessonWeeks are to be deleted.
     */
    public void deleteLesson(String quarterId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LESSON_WEEK, COLUMN_LESSON_WEEK_QUARTER + " = ?", new String[]{String.valueOf(quarterId)});
        db.close();
    }

    /**
     * Creates a new song record in the database.
     * <p>
     * This method inserts a new song record into the database using the provided Song data model.
     *
     * @param par The Song data model containing details such as song ID, song title, registration date, song category,
     *            song group, song author, and preference.
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
     * Retrieves a list of Song objects from the database, sorted by song ID in ascending order.
     *
     * @return A list of Song objects containing information about songs.
     */
    @SuppressLint("Range")
    public List<Song> getSong(){
        String[] columns = {"*"};
        String sortOrder = COLUMN_SONG_ID + " ASC";

        List<Song> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_SONG, columns, null, null, null, null,sortOrder);
        if (cursor.moveToFirst()){
            do {
                Song obj = new Song();
                obj.setSongId(cursor.getInt(cursor.getColumnIndex(COLUMN_SONG_ID)));
                obj.setSongTitle(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_TITLE)));
                obj.setSongCategory(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_CATEGORY)));
                obj.setSongGroup(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_GROUP)));
                obj.setSongAuthor(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_AUTHOR)));
                obj.setSongRegDate(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_REG_DATE)));
                obj.setPreference(Preference.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_PREFERENCE))));

                list.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return list;
    }

    /**
     * Retrieves a list of Song objects from the database for a specific song category, sorted by song ID in ascending order.
     *
     * @param songCategory The category of songs to be retrieved.
     * @return A list of Song objects containing information about songs in the specified category.
     */
    @SuppressLint("Range")
    public List<Song> getSong(String songCategory){
        String[] columns = {"*"};
        String sortOrder = COLUMN_SONG_ID + " ASC";
        String selection = COLUMN_SONG_CATEGORY + " = ?";
        String[] selectionArgs = {songCategory};

        List<Song> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_SONG, columns, selection, selectionArgs, null, null, sortOrder);
        if (cursor.moveToFirst()){
            do {
                Song obj = new Song();
                obj.setSongId(cursor.getInt(cursor.getColumnIndex(COLUMN_SONG_ID)));
                obj.setSongTitle(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_TITLE)));
                obj.setSongCategory(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_CATEGORY)));
                obj.setSongGroup(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_GROUP)));
                obj.setSongAuthor(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_AUTHOR)));
                obj.setSongRegDate(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_REG_DATE)));
                obj.setPreference(Preference.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_PREFERENCE))));

                list.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return list;
    }


    /**
     * Retrieves a list of Song objects from the database for a specific song group, sorted by song ID in ascending order.
     *
     * @param songGroup The group of songs to be retrieved.
     * @return A list of Song objects containing information about songs in the specified group.
     */
    @SuppressLint("Range")
    public List<Song> getSongByGroup(String songGroup){
        String[] columns = {"*"};
        String sortOrder = COLUMN_SONG_ID + " ASC";
        String selection = COLUMN_SONG_GROUP + " = ?";
        String[] selectionArgs = {songGroup};

        List<Song> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_SONG, columns, selection, selectionArgs, null, null, sortOrder);
        if (cursor.moveToFirst()){
            do {
                Song obj = new Song();
                obj.setSongId(cursor.getInt(cursor.getColumnIndex(COLUMN_SONG_ID)));
                obj.setSongTitle(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_TITLE)));
                obj.setSongCategory(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_CATEGORY)));
                obj.setSongGroup(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_GROUP)));
                obj.setSongAuthor(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_AUTHOR)));
                obj.setSongRegDate(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_REG_DATE)));
                obj.setPreference(Preference.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_PREFERENCE))));

                list.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return list;
    }


    /**
     * Retrieves a list of Song objects from the database for a specific song group and category, sorted by song ID in ascending order.
     *
     * @param songGroup The group of songs to be retrieved.
     * @param songCategory The category of songs to be retrieved.
     * @return A list of Song objects containing information about songs in the specified group and category.
     */
    @SuppressLint("Range")
    public List<Song> getSong(String songGroup, String songCategory){
        String[] columns = {"*"};
        String sortOrder = COLUMN_SONG_ID + " ASC";
        String selection = COLUMN_SONG_GROUP + " = ? AND " + COLUMN_SONG_CATEGORY + " = ?";
        String[] selectionArgs = {songGroup, songCategory};

        List<Song> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_SONG, columns, selection, selectionArgs, null, null, sortOrder);
        if (cursor.moveToFirst()){
            do {
                Song obj = new Song();
                obj.setSongId(cursor.getInt(cursor.getColumnIndex(COLUMN_SONG_ID)));
                obj.setSongTitle(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_TITLE)));
                obj.setSongCategory(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_CATEGORY)));
                obj.setSongGroup(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_GROUP)));
                obj.setSongAuthor(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_AUTHOR)));
                obj.setSongRegDate(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_REG_DATE)));
                obj.setPreference(Preference.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_SONG_PREFERENCE))));

                list.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return list;
    }

    /**
     * Updates the information of a Song in the database.
     *
     * @param par The Song object containing the updated information.
     */
    public void updateSong(Song par){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SONG_ID, par.getSongId());
        values.put(COLUMN_SONG_TITLE, par.getSongTitle());
        values.put(COLUMN_SONG_REG_DATE, par.getSongRegDate());
        values.put(COLUMN_SONG_CATEGORY, par.getSongCategory());
        values.put(COLUMN_SONG_GROUP, par.getSongGroup());
        values.put(COLUMN_SONG_AUTHOR, par.getSongAuthor());
        values.put(COLUMN_SONG_PREFERENCE, par.getPreference().name());

        String whereClause = COLUMN_SONG_ID + " = ?";
        String[] whereArgs = {String.valueOf(par.getSongId())};

        db.update(TABLE_SONG, values, whereClause, whereArgs);
        db.close();
    }

    /**
     * Deletes a Song from the database based on the provided song ID.
     *
     * @param songId The ID of the song to be deleted.
     */
    public void deleteSong(int songId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SONG, COLUMN_SONG_ID + " = ?", new String[]{String.valueOf(songId)});
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
     * Retrieves a list of SongStanza objects from the database for a specific song ID, sorted by stanza ID in ascending order.
     *
     * @param songId The ID of the song for which stanzas are to be retrieved.
     * @return A list of SongStanza objects containing information about stanzas for the specified song.
     */
    @SuppressLint("Range")
    public List<SongStanza> getStanza(int songId){
        String[] columns = {"*"};
        String sortOrder = COLUMN_STANZA_ID + " ASC";
        String selection = COLUMN_STANZA_SONG_ID + " = ?";
        String[] selectionArgs = {String.valueOf(songId)};

        List<SongStanza> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_SONG_STANZA, columns, selection, selectionArgs, null, null, sortOrder);
        if (cursor.moveToFirst()){
            do {
                SongStanza obj = new SongStanza();
                obj.setStanzaId(cursor.getInt(cursor.getColumnIndex(COLUMN_STANZA_ID)));
                obj.setSongId(cursor.getInt(cursor.getColumnIndex(COLUMN_STANZA_SONG_ID)));
                obj.setStanzaContent(cursor.getString(cursor.getColumnIndex(COLUMN_STANZA_CONTENT)));

                list.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return list;
    }

    /**
     * Updates the information of a SongStanza in the database.
     *
     * @param par The SongStanza object containing the updated information.
     */
    public void updateStanza(SongStanza par){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_STANZA_ID, par.getStanzaId());
        values.put(COLUMN_STANZA_SONG_ID, par.getSongId());
        values.put(COLUMN_STANZA_CONTENT, par.getStanzaContent());

        String whereClause = COLUMN_STANZA_ID + " = ?";
        String[] whereArgs = {String.valueOf(par.getStanzaId())};

        db.update(TABLE_SONG_STANZA, values, whereClause, whereArgs);
        db.close();
    }

    /**
     * Deletes a SongStanza from the database based on the provided stanza ID.
     *
     * @param stanzaId The ID of the stanza to be deleted.
     */
    public void deleteStanza(int stanzaId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SONG_STANZA, COLUMN_STANZA_ID + " = ?", new String[]{String.valueOf(stanzaId)});
        db.close();
    }


    /**
     * Deletes all stanzas associated with a specific song ID from the database.
     *
     * @param songId The ID of the song whose stanzas are to be deleted.
     */
    public void deleteStanza(String songId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SONG_STANZA, COLUMN_STANZA_SONG_ID + " = ?", new String[]{String.valueOf(songId)});
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

    /**
     * Retrieves a list of SongChorus objects from the database for a specific song ID, sorted by chorus ID in ascending order.
     *
     * @param songId The ID of the song for which choruses are to be retrieved.
     * @return A list of SongChorus objects containing information about choruses for the specified song.
     */
    @SuppressLint("Range")
    public List<SongChorus> getChorus(int songId){
        String[] columns = {"*"};
        String sortOrder = COLUMN_CHORUS_ID + " ASC";
        String selection = COLUMN_CHORUS_SONG_ID + " = ?";
        String[] selectionArgs = {String.valueOf(songId)};

        List<SongChorus> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_SONG_CHORUS, columns, selection, selectionArgs, null, null, sortOrder);
        if (cursor.moveToFirst()){
            do {
                SongChorus obj = new SongChorus();
                obj.setChorusId(cursor.getInt(cursor.getColumnIndex(COLUMN_CHORUS_ID)));
                obj.setSongId(cursor.getInt(cursor.getColumnIndex(COLUMN_CHORUS_SONG_ID)));
                obj.setChorusContent(cursor.getString(cursor.getColumnIndex(COLUMN_CHORUS_CONTENT)));

                list.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return list;
    }

    /**
     * Updates the information of a SongChorus in the database.
     *
     * @param par The SongChorus object containing the updated information.
     */
    public void updateChorus(SongChorus par){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CHORUS_ID, par.getChorusId());
        values.put(COLUMN_CHORUS_SONG_ID, par.getSongId());
        values.put(COLUMN_CHORUS_CONTENT, par.getChorusContent());

        String whereClause = COLUMN_CHORUS_ID + " = ?";
        String[] whereArgs = {String.valueOf(par.getChorusId())};

        db.update(TABLE_SONG_CHORUS, values, whereClause, whereArgs);
        db.close();
    }


    /**
     * Deletes a SongChorus from the database based on the provided chorus ID.
     *
     * @param chorusId The ID of the chorus to be deleted.
     */
    public void deleteChorus(int chorusId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SONG_CHORUS, COLUMN_CHORUS_ID + " = ?", new String[]{String.valueOf(chorusId)});
        db.close();
    }

    /**
     * Deletes all choruses associated with a specific song ID from the database.
     *
     * @param songId The ID of the song whose choruses are to be deleted.
     */
    public void deleteChorus(String songId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SONG_CHORUS, COLUMN_CHORUS_SONG_ID + " = ?", new String[]{String.valueOf(songId)});
        db.close();
    }

}
