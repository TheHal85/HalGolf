package com.ryan.hallermeier.golfrules.main.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ryan.hallermeier.golfrules.main.models.Course;
import com.ryan.hallermeier.golfrules.main.models.Hole;
import com.ryan.hallermeier.golfrules.main.models.Player;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Hal on 6/3/2014.
 */
public class DBUtils extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "GolfDB";

    //Table Names
    private static final String TABLE_COURSE = "course";
    private static final String TABLE_HOLE = "hole";
    private static final String TABLE_PLAYER = "player";
    private static final String TABLE_ROUND = "round";
    private static final String TABLE_SHOT = "shot";
    private static final String TABLE_TEAM = "team";

    //Column Names
    //Common Column Names
    private static final String COLUMN_COURSE_ID = "course_id";
    private static final String COLUMN_HOLE_ID = "hole_id";
    private static final String COLUMN_PLAYER_ID = "player_id";
    private static final String COLUMN_TEAM_ID = "team_id";
    private static final String COLUMN_ROUND_ID = "round_id";

    //Course Column Names
    private static final String COLUMN_COURSE_NAME = "course_name";

    //Hole Column Names
    private static final String COLUMN_PAR = "par";
    private static final String COLUMN_MEN_DISTANCE = "men_distance";
    private static final String COLUMN_WOMEN_DISTANCE = "women_distance";

    //Player Column Names
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_EXTRA_SHOT = "extra_shot";

    //Round Column Names
    private static final String COLUMN_DATE = "date";

    //Shot Column Names
    private static final String COLUMN_SHOT_ID = "shot_id";

    //Team Column Names
    private static final String COLUMN_TEAM_NUMBER = "team_number";


    //Table Columns
    private static final String[] COLUMNS_COURSE = {COLUMN_COURSE_ID, COLUMN_COURSE_NAME};
    private static final String[] COLUMNS_HOLE = {COLUMN_HOLE_ID, COLUMN_COURSE_ID, COLUMN_PAR, COLUMN_MEN_DISTANCE, COLUMN_WOMEN_DISTANCE};
    private static final String[] COLUMNS_PLAYER = {COLUMN_PLAYER_ID, COLUMN_TEAM_ID, COLUMN_FIRST_NAME, COLUMN_LAST_NAME, COLUMN_EXTRA_SHOT};
    private static final String[] COLUMNS_ROUND = {COLUMN_ROUND_ID, COLUMN_COURSE_ID, COLUMN_DATE};
    private static final String[] COLUMNS_SHOT = {COLUMN_SHOT_ID, COLUMN_HOLE_ID, COLUMN_PLAYER_ID};
    private static final String[] COLUMNS_TEAM = {COLUMN_TEAM_ID, COLUMN_ROUND_ID, COLUMN_TEAM_NUMBER};


    //Create Statements
    //Create Course
    private static final String CREATE_TABLE_COURSE = "CREATE TABLE "
            + TABLE_COURSE + "(" + COLUMN_COURSE_ID + " INTEGER PRIMARY KEY," + COLUMN_COURSE_NAME
            + " TEXT" + ")";

    //Create Hole
    private static final String CREATE_TABLE_HOLE = "CREATE TABLE "
            + TABLE_HOLE + "(" + COLUMN_HOLE_ID + " INTEGER PRIMARY KEY," + COLUMN_COURSE_ID
            + " INTEGER," + COLUMN_PAR
            + " INTEGER," + COLUMN_MEN_DISTANCE
            + " INTEGER," + COLUMN_WOMEN_DISTANCE
            + " INTEGER" + ")";

    //Create Hole
    private static final String CREATE_TABLE_PLAYER = "CREATE TABLE "
            + TABLE_PLAYER + "(" + COLUMN_PLAYER_ID + " INTEGER PRIMARY KEY," + COLUMN_TEAM_ID
            + " INTEGER," + COLUMN_FIRST_NAME
            + " TEXT," + COLUMN_LAST_NAME
            + " TEXT," + COLUMN_EXTRA_SHOT
            + " INTEGER" + ")";

    //Create Round
    private static final String CREATE_TABLE_ROUND = "CREATE TABLE "
            + TABLE_ROUND + "(" + COLUMN_ROUND_ID + " INTEGER PRIMARY KEY," + COLUMN_COURSE_ID
            + " INTEGER," + COLUMN_DATE
            + " TEXT" + ")";

    //Create Shot
    private static final String CREATE_TABLE_SHOT = "CREATE TABLE "
            + TABLE_SHOT + "(" + COLUMN_SHOT_ID + " INTEGER PRIMARY KEY," + COLUMN_HOLE_ID
            + " INTEGER," + COLUMN_PLAYER_ID
            + " INTEGER" + ")";

    //Create Team
    private static final String CREATE_TABLE_TEAM = "CREATE TABLE "
            + TABLE_TEAM + "(" + COLUMN_TEAM_ID + " INTEGER PRIMARY KEY," + COLUMN_ROUND_ID
            + " INTEGER," + COLUMN_TEAM_NUMBER
            + " INTEGER" + ")";


    public DBUtils(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // creating required tables
        sqLiteDatabase.execSQL(CREATE_TABLE_COURSE);
        sqLiteDatabase.execSQL(CREATE_TABLE_HOLE);
        sqLiteDatabase.execSQL(CREATE_TABLE_PLAYER);
        sqLiteDatabase.execSQL(CREATE_TABLE_ROUND);
        sqLiteDatabase.execSQL(CREATE_TABLE_SHOT);
        sqLiteDatabase.execSQL(CREATE_TABLE_TEAM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        // on upgrade drop older tables
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_COURSE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_HOLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_PLAYER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_ROUND);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_SHOT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_TEAM);

        // create new tables
        onCreate(sqLiteDatabase);
    }


    //Helper Methods

    //Player Methods
    public void addPlayer(Player player) {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(COLUMN_PLAYER_ID, player.getPlayerId());
        values.put(COLUMN_TEAM_ID, player.getTeamId());
        values.put(COLUMN_FIRST_NAME, player.getFirstName());
        values.put(COLUMN_LAST_NAME, player.getLastName());
        values.put(COLUMN_EXTRA_SHOT, player.getExtraShot());

        // 3. insert
        db.insert(TABLE_PLAYER, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public Player getPlayer(int id) {

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_PLAYER, // a. table
                        COLUMNS_PLAYER, // b. column names
                        " id = ?", // c. selections
                        new String[]{String.valueOf(id)}, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build player object
        Player player = new Player();
        player.setPlayerId(cursor.getInt(0));
        player.setTeamId(cursor.getInt(1));
        player.setFirstName(cursor.getString(2));
        player.setLastName(cursor.getString(3));
        player.setExtraShot(cursor.getInt(4));

        // 5. return player
        return player;
    }

    public ArrayList<Player> getAllPlayers() {
        ArrayList<Player> players = new ArrayList<Player>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_PLAYER;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build player and add it to list
        Player player = null;
        if (cursor.moveToFirst()) {
            do {
                player = new Player();
                player.setPlayerId(cursor.getInt(0));
                player.setTeamId(cursor.getInt(1));
                player.setFirstName(cursor.getString(2));
                player.setLastName(cursor.getString(3));
                player.setExtraShot(cursor.getInt(4));

                // Add player to players
                players.add(player);
            } while (cursor.moveToNext());
        }
        // return players
        return players;
    }

    public ArrayList<Player> getAllPlayersByTeamId(int teamId) {
        ArrayList<Player> players = new ArrayList<Player>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_PLAYER + " WHERE " + COLUMN_TEAM_ID + "==" + teamId;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build player and add it to list
        Player player = null;
        if (cursor.moveToFirst()) {
            do {
                player = new Player();
                player.setPlayerId(cursor.getInt(0));
                player.setTeamId(cursor.getInt(1));
                player.setFirstName(cursor.getString(2));
                player.setLastName(cursor.getString(3));
                player.setExtraShot(cursor.getInt(4));

                // Add player to players
                players.add(player);
            } while (cursor.moveToNext());
        }
        // return players
        return players;
    }

    public int updatePlayer(Player player) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(COLUMN_TEAM_ID, player.getTeamId());
        values.put(COLUMN_FIRST_NAME, player.getFirstName());
        values.put(COLUMN_LAST_NAME, player.getLastName());
        values.put(COLUMN_EXTRA_SHOT, player.getExtraShot());

        // 3. updating row
        int i = db.update(TABLE_PLAYER, //table
                values, // column/value
                COLUMN_PLAYER_ID + " = ?", // selections
                new String[]{String.valueOf(player.getPlayerId())}); //selection args

        // 4. close
        db.close();

        return i;

    }

    public void deletePlayer(Player player) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_PLAYER, //table name
                COLUMN_PLAYER_ID + " = ?",  // selections
                new String[]{String.valueOf(player.getPlayerId())}); //selections args

        // 3. close
        db.close();

    }

    //Hole Methods
    public void addHole(Hole hole) {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(COLUMN_HOLE_ID, hole.getHoleId());
        values.put(COLUMN_COURSE_ID, hole.getCourseId());
        values.put(COLUMN_PAR, hole.getPar());
        values.put(COLUMN_MEN_DISTANCE, hole.getMenDistance());
        values.put(COLUMN_WOMEN_DISTANCE, hole.getWomenDistance());

        // 3. insert
        db.insert(TABLE_HOLE, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public Hole getHole(int id) {

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_HOLE, // a. table
                        COLUMNS_HOLE, // b. column names
                        " id = ?", // c. selections
                        new String[]{String.valueOf(id)}, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build hole object
        Hole hole = new Hole();
        hole.setHoleId(cursor.getInt(0));
        hole.setCourseId(cursor.getInt(1));
        hole.setPar(cursor.getInt(2));
        hole.setMenDistance(cursor.getInt(3));
        hole.setWomenDistance(cursor.getInt(4));


        // 5. return hole
        return hole;
    }

    public ArrayList<Hole> getAllHolesByCourseId(int courseId) {
        ArrayList<Hole> holes = new ArrayList<Hole>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_HOLE + " WHERE " + COLUMN_COURSE_ID + "==" + courseId;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build hole and add it to list
        Hole hole = null;
        if (cursor.moveToFirst()) {
            do {
                hole = new Hole();
                hole.setHoleId(cursor.getInt(0));
                hole.setCourseId(cursor.getInt(1));
                hole.setPar(cursor.getInt(2));
                hole.setMenDistance(cursor.getInt(3));
                hole.setWomenDistance(cursor.getInt(4));

                // Add hole to holes
                holes.add(hole);
            } while (cursor.moveToNext());
        }
        // return holes
        return holes;
    }

    public int updateHole(Hole hole) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(COLUMN_COURSE_ID, hole.getCourseId());
        values.put(COLUMN_PAR, hole.getPar());
        values.put(COLUMN_MEN_DISTANCE, hole.getMenDistance());
        values.put(COLUMN_WOMEN_DISTANCE, hole.getWomenDistance());

        // 3. updating row
        int i = db.update(TABLE_HOLE, //table
                values, // column/value
                COLUMN_HOLE_ID + " = ?", // selections
                new String[]{String.valueOf(hole.getHoleId())}); //selection args

        // 4. close
        db.close();

        return i;

    }

    public void deleteHole(Hole hole) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_HOLE, //table name
                COLUMN_HOLE_ID + " = ?",  // selections
                new String[]{String.valueOf(hole.getHoleId())}); //selections args

        // 3. close
        db.close();

    }


    //Course Methods
    public void addCourse(Course course) {
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(COLUMN_COURSE_ID, course.getCourseId());
        values.put(COLUMN_COURSE_NAME, course.getCourseName());

        // 3. insert
        db.insert(TABLE_COURSE, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // 4. close
        db.close();
    }

    public Course getCourse(int id) {

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();

        // 2. build query
        Cursor cursor =
                db.query(TABLE_COURSE, // a. table
                        COLUMNS_COURSE, // b. column names
                        " id = ?", // c. selections
                        new String[]{String.valueOf(id)}, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build hole object
        Course course = new Course();
        course.setCourseId(cursor.getInt(0));
        course.setCourseName(cursor.getString(1));


        // 5. return hole
        return course;
    }

    public ArrayList<Course> getAllCourses() {
        ArrayList<Course> courses = new ArrayList<Course>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_COURSE;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build hole and add it to list
        Course course = null;
        if (cursor.moveToFirst()) {
            do {
                course = new Course();
                course.setCourseId(cursor.getInt(0));
                course.setCourseName(cursor.getString(1));

                // Add hole to holes
                courses.add(course);
            } while (cursor.moveToNext());
        }
        // return holes
        return courses;
    }

    public int updateCourse(Course course) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(COLUMN_COURSE_ID, course.getCourseId());
        values.put(COLUMN_COURSE_NAME, course.getCourseName());

        // 3. updating row
        int i = db.update(TABLE_COURSE, //table
                values, // column/value
                COLUMN_COURSE_ID + " = ?", // selections
                new String[]{String.valueOf(course.getCourseId())}); //selection args

        // 4. close
        db.close();

        return i;

    }

    public void deleteCourse(Course course) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_COURSE, //table name
                COLUMN_COURSE_ID + " = ?",  // selections
                new String[]{String.valueOf(course.getCourseId())}); //selections args

        // 3. close
        db.close();

    }
}
