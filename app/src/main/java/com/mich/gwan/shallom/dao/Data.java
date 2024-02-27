/*
 * Copyright (c) 2024. Maina Michael.
 * Created on 2024 / 2 / 15
 */

package com.mich.gwan.shallom.dao;

import org.intellij.lang.annotations.Language;

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

    @Language("SQL")
    public static final String INSERT_QUARTER = "INSERT INTO "+TABLE_LESSON_QUARTER+ " (" +
            COLUMN_LESSON_QUARTER_ID+","+COLUMN_LESSON_QUARTER_YEAR+","+COLUMN_LESSON_QUARTER_MONTH+","
            +COLUMN_LESSON_QUARTER_REG_DATE+","+COLUMN_LESSON_QUARTER_REG_BY+") VALUES"+
            "(1, '2023-2024', 'JAN-MAR', '2024-02-15', 'MICHAEL MAINA')," +
            "(2, '2024-2025', 'APR-JUN', '2024-02-15', 'MICHAEL MAINA')," +
            "(3, '2024-2025', 'JUL-SEP', '2024-02-15', 'MICHAEL MAINA')," +
            "(4, '2024-2025', 'OCT-DEC', '2024-02-15', 'MICHAEL MAINA')," +
            "(5, '2024-2025', 'JAN-MAR', '2024-02-15', 'MICHAEL MAINA')";

    @Language("SQL")
    public static final String INSERT_LESSON_WEEK = "INSERT INTO " +TABLE_LESSON_WEEK+ " (" +
            COLUMN_LESSON_WEEK_ID+","+COLUMN_LESSON_WEEK_DATE+","+COLUMN_LESSON_WEEK_TITLE+","
            +COLUMN_LESSON_WEEK_READING+","+COLUMN_LESSON_WEEK_MEM_VERSE+","+COLUMN_LESSON_WEEK_LANGUAGE+","
            +COLUMN_LESSON_WEEK_QUARTER+","+COLUMN_LESSON_WEEK_REG_DATE+","+COLUMN_LESSON_WEEK_REG_BY+") VALUES"+
            "(1, '2024-01-03', 'Rahab', 'John 3:1-30', 'John 4:1', 'ENGLISH', 1, '2024-02-15', 'MICHAEL MAINA')," +
            "(2, '2024-01-10', 'Thanksgiving', 'James 12', 'Hebrews 2:2', 'ENGLISH', 1, '2024-02-15', 'MICHAEL MAINA')," +
            "(4, '2024-01-10', 'Thanksgiving', 'James 12', 'Hebrews 2:2', 'ENGLISH', 3, '2024-02-15', 'MICHAEL MAINA')," +
            "(5, '2024-01-10', 'Kushukuru', 'Yakobo 12', 'Waebrania', 'KISWAHILI', 3, '2024-02-15', 'MICHAEL MAINA')," +
            "(3, '2024-01-10', 'Kushukuru', 'Yakobo 12', 'Waebrania', 'KISWAHILI', 1, '2024-02-15', 'MICHAEL MAINA')";

    @Language("SQL")
    public static final String INSERT_LESSON_QUESTION = "INSERT INTO " + TABLE_LESSON_QUESTION+ " ("+
            COLUMN_LESSON_QUESTION_ID+","+COLUMN_LESSON_QUESTION_WEEK+","+COLUMN_LESSON_QUESTION_QUESTION+","
            +COLUMN_LESSON_QUESTION_BOOKS+","+COLUMN_LESSON_QUESTION_EXPLANATION+","+COLUMN_LESSON_QUESTION_LANGUAGE+","
            +COLUMN_LESSON_QUESTION_REG_DATE+","+COLUMN_LESSON_QUESTION_REG_BY+") VALUES"+
            "(1, 2, 'What', 'Mat 6, Jam 4,', 'Abcd efgh ijklmnop', 'ENGLISH', '2024-02-15', 'MICHAEL MAINA')," +
            "(5, 4, 'What', 'Mat 6, Jam 4,', 'Abcd efgh ijklmnop', 'ENGLISH', '2024-02-15', 'MICHAEL MAINA')," +
            "(2, 2, 'Did you ', 'John 3:4, James 3:3', 'NULL', 'ENGLISH', '2024-02-15', 'MICHAEL MAINA')," +
            "(3, 2, 'Nini', 'Mat 6, Yak 4,', 'Abcd efgh ijklmnop', 'KISWAHILI', '2024-02-15', 'MICHAEL MAINA')," +
            "(6, 4, 'Nini', 'Mat 6, Yak 4,', 'Abcd efgh ijklmnop', 'KISWAHILI', '2024-02-15', 'MICHAEL MAINA')," +
            "(4, 2, 'Ulifanya', 'Yoh 3:4, Yak 3:3', 'NULL', 'KISWAHILI', '2024-02-15','MICHAEL MAINA')";

    @Language("SQL")
    public static final String INSERT_DOCTRINE = "INSERT INTO " +TABLE_DOCTRINE+ " ("+
            COLUMN_DOCTRINE_ID+","+COLUMN_DOCTRINE_CONTENT+","+COLUMN_DOCTRINE_DESCRIPTION+","+COLUMN_DOCTRINE_REF+") VALUES"+
            "(1, 'God is the Creator', 'God, the Heavenly Father, is the creator of heaven and earth, the sea and all that is there in. He created all things by His son Yahshua, the Messiah.', 'Gen 1:1; Acts 17: 24; Heb. 1: 10; 1 Cor. 8:6; Eph. 3:9; Heb. 1:1-2; Gen 1:26; John 1:1-3; John 1:10; Prov. 8: 22-31; 1 Cor. 8:6; Col 1:15-16')," +
            "(2, 'Completeness of the Bible', 'The Bible - both the Old and New Testaments is the inspired word of God, and is complete, and infallible, and expresses God''s will to mankind.', 'Deut 8:3; Luke 4:4; II Timothy 3:16; II Peter 1:21')," +
            "(3, 'Observance of the Sabbath', 'The Seventh Day of the week is the Sabbath of the Lord, and is to be observed from evening to evening, from sunset to sunset.', 'Gen 2:1-3; Exodus 20:8-11; Acts 13:42-44, 18:4; Matt 28:1; Heb 4:4-11; Mark 1:32')," +
            "(4, 'Yahshua our Saviour and Mediator', 'Yahshua of Nazareth, our savior, is the only begotten son of God; born of the virgin Mary, conceived of the Holy Ghost; with God the Father, before the world began. He is our living Messiah, our high Priest and Mediator before the throne of God.', 'Matt. 1:20-21; John 3:16, 17:5; 1 Timothy 2: 5')," +
            "(5, 'Crucifixion and Resurrection of Messiah', 'Yahshua, the Messiah proved His Messiahship by remaining in the tomb for exactly 3 days and 3 nights; being crucified in the midst of the week (Wednesday), and rising from the dead at the end of the Sabbath (Saturday).', 'Matt 12:40; Daniel 9:27; Matt 28:1-6')," +
            "(6, 'Emulation of Messiah''''s Example', 'The Saint''''s life must be patterned after the example of the perfect man, Messiah Yahshuah.', '1 Peter 2:21- 24, Phil. 2:5, 2 Cor 5:17.')," +
            "(7, 'Manifestation of the Holy Spirit', 'The Holy Spirit is the comforter, which abides in the believer and is manifest by power and the fruits of the Holy Spirit. Manifestation is regulated by scriptural guidelines.', 'Acts 2; Gal 5: 22-26; 1 Cor. 14;')," +
            "(8, 'Preaching Repentance and Remission of Sins', 'Repentance and remission of sins through the shed blood of Yahshua must be preached.', 'Acts 2:38; Matt. 26:28')," +
            "(9, 'Necessity of Conversion', 'Conversion is necessary in order to be saved.', 'Acts 3:19; Matt 18: 3.')," +
            "(10, 'Baptism by Immersion', 'We must be baptized by immersion, in the name of Yahshuah Messiah for the remission of sins.', 'Acts 2:38; Mark 1:9-10.')," +
            "(11, 'Command for Sanctification and Holiness', 'Sanctification and Holiness is commanded for the people of God.', 'Acts 20:32; Heb 12: 14; 1 Peter 1:15-16.')," +
            "(12, 'Power of Righteous Prayer and Anointing', 'There is power in the prayer of the righteous. The prayer of faith and anointing with oil will heal the sick.', 'James 5: 16; John 14:13-14,13; James 5:14-15')," +
            "(13, 'Laying on of Hands and Recognition of Satan', 'The laying on of hands is to be practiced. Satan is a personality, and as devil he is an adversary of God and the children of God.', 'Acts 8: 17; Acts 6:6-15; 1 Peter 5: 8; 1 John 3: 8; Jude 9; Matt 4:1-11')," +
            "(14, 'Origin of Sin and Imperfection', 'Man was created perfect, but through disobedience fell, bringing imperfection, death, and God''s wrath upon mankind.', 'Genesis 3; 1 Cor. 15:21-22')," +
            "(15, 'Observance of the Lord''''s Supper', 'The Lord''s Supper is to be observed annually at the beginning of 14th day of Nisan/Abib, Messiah being our example.', 'Luke 22:7-20; 1 Cor. 11:23-26')," +
            "(16, 'Observance of Foot Washing', 'The washing of the feet is to be observed in connection with the Lord''s Supper.', 'John 13: 4-5')," +
            "(17, 'Obligation of Tithing', 'The paying of tithes on all increase is a continued obligation. This portion of our earnings belongs to the Lord and should be placed in His work.', 'Malachi 3: 10; Matthew 23:23.')," +
            "(18, 'Observance of the Ten Commandments', 'The law of God, the Ten Commandments, must be taught and observed.', 'Romans 3:31; James 2:8-11; Revelation 22:14-15')," +
            "(19, 'Sin as Transgression of the Law', 'Sin is the transgression of the law.', '1 John 3:4')," +
            "(20, 'Justification through Yahshua', 'Justification from sin is through Messiah Yahshuah alone.', '1 Cor. 6:11; Romans 4:25, 5: 16-18.')," +
            "(21, 'Modesty and Worldliness', 'All Saints should dress modestly, and should refrain from all worldliness.', '1 Timothy 2: 9; 1 Cor. 11: 7, 14-15; 1 John 2:15-16.')," +
            "(22, 'Condemnation of Carnal Warfare', 'All Carnal warfare and the participation therein are condemned by the Lord.', 'Matt 5:38-39, 2 Cor. 10: 4, Ex. 20:13; Matt 26:52.')," +
            "(23, 'Observance of Dietary Laws', 'The law of the clean and unclean meats is to be observed.', 'Leviticus 11; Ezekiel 44: 23.')," +
            "(24, 'Prohibition of Intoxicants', 'The use of intoxicating liquors, alcoholic stimulants, tobacco, or any habit forming or mind-bending drug is forbidden.', '1 Cor. 3: 16- 17; Prov. 20:1')," +
            "(25, 'Prohibition of Divorce and Remarriage', 'Messiah condemned divorce and remarriage as being the sin of adultery. A man is permitted to have only one living wife, and a woman only one living husband.', 'Luke 16:18; Romans 7:2-3; 1Timothy 3:2.')," +
            "(26, 'Restoration of Israel', 'There is a final gathering of the dispersed Israel back to their land, one that is taking place now.', 'Jeremiah 16:14-15; Amos 9:14-15; Ezek. 39: 27-28; 36:24; Jer. 32:37.')," +
            "(27, 'The Third Angel''s Message', 'The third angel''s message is a present day message, and will continue till the seconding coming of Yahshua.', 'Revelation 14: 9-11')," +
            "(28, 'Future Literal Plagues', 'The seven last plagues are future and literal.', 'Revelation 16')," +
            "(29, 'Literal Second Coming of Yahshua', 'The return of Yahshua will be literal, visible, personal and imminent.', 'Matt 24: 27; Rev1:7')," +
            "(30, 'Institution of Kingdom of Heaven', 'The institution of the Kingdom of Heaven is at the return of our Lord Yahshua, the Messiah and the throne of David will be established at Jerusalem in the person of Messiah Yahshuah.', 'Isa. 9:6-7; Luke 1:32-33')," +
            "(31, 'Yahshua''''s Reign and Rule', 'Messiah Yahshua will return and fight in the battle of Armageddon. His coming will bring peace to the earth, and He will rule the world from Jerusalem during the 1,000 years of restoration (the Millennium ).', 'Zech. 14; Rev. 20:4-6; Micah 4:3-7')," +
            "(32, 'Resurrection and Reward of the Righteous', 'The righteous people who died will be resurrected and rewarded at the second coming of the Messiah.', 'I Thess. 4:16; Rev. 22:12')," +
            "(33, 'Inheritance of the Meek', 'The meek shall inherit the earth and dwell therein forever.', 'Matt. 5:5; Prov. 10:30')," +
            "(34, 'Final Judgment of the Wicked', 'After the 1,000-year restoration period, Christ will deliver the Kingdom to God, the Father. It is then that the wicked will be resurrected to final judgment.', 'I Cor. 15:22-24; Rev. 20, 21, 22; John 5: 28-29')," +
            "(35, 'Church of God', 'The inspired name for God''s called-out assembly is the ''Church of God.''', 'I Cor. 1:2, 10:32, 11:16, 22, 15:9, II Cor. 1:1; Gal 1: 13; I Thess. 2:14; II Thess. 1: 4; I Tim. 3:5, 15; Acts 20:28.')," +
            "(36, 'Destruction of the Wicked', 'The wicked will be eternally destroyed.', 'Rev 20: 11-15; Rom. 6: 16, 23')," +
            "(39, 'Role of Elders', 'Ministers of the Church of God are called ''Elders'', and are those disciples accepted into the ministry upon recommendation by an ordained Elder, through investigation, both from inside and outside the Church, as to character, his attributes as a Messianic, his ability to teach, and his faithfulness in doctrine of the Church. No one being entangled in adultery may hold any office in the Church.', 'Acts 14: 23; Titus 1:5-9; I Tim. 3:1-5')," +
            "(40, 'Role of Overseer', 'The Overseers shall have general care over the Church as a whole, in territories or various countries, as need requires.', 'Acts 20:28.')";


    @Language("SQL")
    public static final String INSERT_EVENT = "INSERT INTO " +TABLE_EVENT+ " ("+
            COLUMN_EVENT_ID+","+COLUMN_EVENT_LOCATION+","+COLUMN_EVENT_TITLE+","+COLUMN_EVENT_DESCRIPTION+","+COLUMN_EVENT_START_DATE+","+COLUMN_EVENT_END_DATE+","+COLUMN_EVENT_REG_DATE+","+COLUMN_EVENT_REG_BY+") VALUES"+
            "(1, 'NAIROBI', 'Youth Camp', 'A one week congregation', '2024-12-23T00:00:00', '2024-12-30T00:00:00', '2024-02-15T00:00:00', 'MICHAEL MAINA')," +
            "(2, 'BANANA', 'Outreach', 'A three days outreach', '2024-03-23T00:00:00', '2024-03-27T00:00:00', '2024-02-15T00:00:00', 'MICHAEL MAINA')," +
            "(3, 'MARURUI', 'Internal Fundraiser', 'A internal fundraiser for Marurui', '2024-05-25T00:00:00', '2024-05-25T00:00:00', '2024-02-15T00:00:00', 'MICHAEL MAINA')";
}
