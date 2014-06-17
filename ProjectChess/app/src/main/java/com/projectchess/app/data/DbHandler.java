package com.projectchess.app.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Inholland on 15-5-2014.
 */
public class DbHandler extends SQLiteOpenHelper{
    //TODO not implemented!!
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ChessMaster";
    private static final String TABLE_GAMES = "games";
    private static final String TABLE_PLAYERS = "players";

    //TABLE_GAMES
    private static final String GAMES_GAME_ID = "d";
    private static final String GAMES_EVENT = "event";
    private static final String GAMES_SITE = "site";
    private static final String GAMES_DATE = "date";
    private static final String GAMES_ROUND = "round";
    private static final String GAMES_WHITE = "white";
    private static final String GAMES_BLACK = "black";
    private static final String GAMES_RESULT = "result";
    private static final String GAMES_WHITE_ELO = "white_elo";
    private static final String GAMES_BLACK_ELO = "black_elo";
    private static final String GAMES_ECO = "eco";
    private static final String GAMES_MOVES = "moves";
    private static final String GAMES_W1 = "w1";
    private static final String GAMES_W2 = "w2";
    private static final String GAMES_W3 = "w3";
    private static final String GAMES_W4 = "w4";
    private static final String GAMES_W5 = "w5";
    private static final String GAMES_B1 = "b1";
    private static final String GAMES_B2 = "b2";
    private static final String GAMES_B3 = "b3";
    private static final String GAMES_B4 = "b4";
    private static final String GAMES_B5 = "b5";

    //TABLE_PLAYERS
    private static final String PLAYERS_PLAYER_ID = "id";
    private static final String PLAYERS_FIRSTNAME = "firstName";
    private static final String PLAYERS_LASTNAME = "lastName";

    public DbHandler(Context context, String name, CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMES);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_PLAYERS = "CREATE TABLE " +
                TABLE_PLAYERS + "("
                + PLAYERS_PLAYER_ID + " INTEGER PRIMARY KEY,"
                + PLAYERS_FIRSTNAME + " TEXT,"
                + PLAYERS_LASTNAME + " TEXT,"
                + ")";
        db.execSQL(CREATE_TABLE_PLAYERS);

        String CREATE_TABLE_GAMES = "CREATE TABLE " +
                TABLE_GAMES + "("
                + GAMES_GAME_ID + " INTEGER PRIMARY KEY,"
                + GAMES_EVENT + " TEXT,"
                + GAMES_SITE + " TEXT,"
                + GAMES_DATE + " TEXT,"
                + GAMES_ROUND + " TEXT,"
                + GAMES_RESULT + " TEXT,"
                + GAMES_WHITE + " INT,"
                + GAMES_BLACK + " INT,"
                + GAMES_WHITE_ELO + " TEXT,"
                + GAMES_BLACK_ELO + " TEXT,"
                + GAMES_ECO + " TEXT,"
                + GAMES_MOVES + " TEXT,"
                + GAMES_W1 + " TEXT,"
                + GAMES_W2 + " TEXT,"
                + GAMES_W3 + " TEXT,"
                + GAMES_W4 + " TEXT,"
                + GAMES_W5 + " TEXT,"
                + GAMES_B1 + " TEXT,"
                + GAMES_B2 + " TEXT,"
                + GAMES_B3 + " TEXT,"
                + GAMES_B4 + " TEXT,"
                + GAMES_B5 + " TEXT,"
                + "FOREIGN KEY(" + GAMES_WHITE + ") REFERENCES " + TABLE_PLAYERS + "(" + PLAYERS_PLAYER_ID + ")"
                + "FOREIGN KEY(" + GAMES_BLACK + ") REFERENCES " + TABLE_PLAYERS + "(" + PLAYERS_PLAYER_ID + ")"
                + ")";
        db.execSQL(CREATE_TABLE_GAMES);
    }

}
