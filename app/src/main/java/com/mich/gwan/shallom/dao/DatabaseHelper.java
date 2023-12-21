package com.mich.gwan.shallom.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
            + COLUMN_USER_PASSWORD + " TEXT,"
            + COLUMN_USER_REG_DATE + " TEXT,"
            + COLUMN_USER_STATUS + " TEXT,"
            + COLUMN_USER_PASSWORD + " TEXT" + ")";

    /**
     *
     * @param context
     */

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
