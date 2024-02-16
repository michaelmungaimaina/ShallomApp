/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 15
 */

package com.mich.gwan.shallom.dao;

public class Data {
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
    private static final String COLUMN_DOCTRINE_CONTENT = "content";
    private static final String COLUMN_DOCTRINE_DESCRIPTION = "description";
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

    public static final String INSERT_QUARTER = "INSERT INTO "+TABLE_LESSON_QUARTER+ " (" +
            COLUMN_LESSON_QUARTER_ID+","+COLUMN_LESSON_QUARTER_YEAR+","+COLUMN_LESSON_QUARTER_MONTH+","
            +COLUMN_LESSON_QUARTER_REG_DATE+","+COLUMN_LESSON_QUARTER_REG_BY+") VALUES"+
            "(1, '2023-2024', 'JAN-MAR', '2024-02-15', 'MICHAEL MAINA')," +
            "(2, '2024-2025', 'APR-JUN', '2024-02-15', 'MICHAEL MAINA')," +
            "(3, '2024-2025', 'MAY-AUG', '2024-02-15', 'MICHAEL MAINA')," +
            "(4, '2024-2025', 'SEP-DEC', '2024-02-15', 'MICHAEL MAINA')," +
            "(5, '2024-2025', 'JAN-MAR', '2024-02-15', 'MICHAEL MAINA')";

    public static final String INSERT_LESSON_WEEK = "INSERT INTO " +TABLE_LESSON_WEEK+ " (" +
            COLUMN_LESSON_WEEK_ID+","+COLUMN_LESSON_WEEK_DATE+","+COLUMN_LESSON_WEEK_TITLE+","
            +COLUMN_LESSON_WEEK_READING+","+COLUMN_LESSON_WEEK_MEM_VERSE+","+COLUMN_LESSON_WEEK_LANGUAGE+","
            +COLUMN_LESSON_WEEK_QUARTER+","+COLUMN_LESSON_WEEK_REG_DATE+","+COLUMN_LESSON_WEEK_REG_BY+") VALUES"+
            "(1, '2024-01-03', 'Rahab', 'John 3:1-30', 'John 4:1', 'ENGLISH', 1, '2024-02-15', 'MICHAEL MAINA')," +
            "(2, '2024-01-10', 'Thanksgiving', 'James 12', 'Hebrews 2:2', 'ENGLISH', 1, '2024-02-15', 'MICHAEL MAINA')," +
            "(3, '2024-01-10', 'Kushukuru', 'Yakobo 12', 'Waebrania', 'KISWAHILI', 1, '2024-02-15', 'MICHAEL MAINA')";

    public static final String INSERT_LESSON_QUESTION = "INSERT INTO " + TABLE_LESSON_QUESTION+ " ("+
            COLUMN_LESSON_QUESTION_ID+","+COLUMN_LESSON_QUESTION_WEEK+","+COLUMN_LESSON_QUESTION_QUESTION+","
            +COLUMN_LESSON_QUESTION_BOOKS+","+COLUMN_LESSON_QUESTION_EXPLANATION+","+COLUMN_LESSON_QUESTION_LANGUAGE+","
            +COLUMN_LESSON_QUESTION_REG_DATE+","+COLUMN_LESSON_QUESTION_REG_BY+") VALUES"+
            "(1, 2, 'What', 'Mat 6, Jam 4,', 'Abcd efgh ijklmnop', 'ENGLISH', '2024-02-15', 'MICHAEL MAINA', )," +
            "(2, 2, 'Did you ', 'John 3:4, James 3:3', 'NULL', 'ENGLISH', '2024-02-15', 'MICHAEL MAINA')," +
            "(3, 2, 'Nini', 'Mat 6, Yak 4,', 'Abcd efgh ijklmnop', 'KISWAHILI', '2024-02-15', 'MICHAEL MAINA')," +
            "(4, 2, 'Ulifanya', 'Yoh 3:4, Yak 3:3', 'NULL', 'KISWAHILI', '2024-02-15','MICHAEL MAINA')";

    public static final String INSERT_DOCTRINE = "INSERT INTO " +TABLE_DOCTRINE+ " ("+
            COLUMN_DOCTRINE_ID+","+COLUMN_DOCTRINE_CONTENT+","+COLUMN_DOCTRINE_DESCRIPTION+","+COLUMN_DOCTRINE_REF+") VALUES"+
            "(1, 'Elohim is the creator', 'We bilieve that Elohim is the creator', 'Gen 1:1, John 1:1')," +
            "(2, 'There is no other God', 'We believe that Elohim is one and there is no else.', 'Mat 20:3, Psm 101:2')";

    public static final String INSERT_EVENT = "INSERT INTO " +TABLE_EVENT+ " ("+
            COLUMN_EVENT_ID+","+COLUMN_EVENT_LOCATION+","+COLUMN_EVENT_TITLE+","+COLUMN_EVENT_DESCRIPTION+","+COLUMN_EVENT_START_DATE+","+COLUMN_EVENT_END_DATE+","+COLUMN_EVENT_REG_DATE+","+COLUMN_EVENT_REG_BY+") VALUES"+
            "(1, 'NAIROBI', 'Youth Camp', 'A one week congregation', '2024-12-23', '2024-12-30', '2024-02-15', 'MICHAEL MAINA')," +
            "(2, 'BANANA', 'Outreach', 'A three days outreach', '2024-03-23', '2024-03-27', '2024-02-15', 'MICHAEL MAINA')," +
            "(3, 'MARURUI', 'Internal Fundraiser', 'A internal fundraiser for Marurui', '2024-05-25', '2024-05-25', '2024-02-15', 'MICHAEL MAINA')";
}
