package com.projectchessap.projectchess.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Inholland on 15-5-2014.
 */
public class DbHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ChessMaster";
    private static final String TABLE_GAMES = "games";
    private static final String TABLE_PLAYERS = "players";
    private static final String TABLE_MOVES = "moves";

    //TABLE_GAMES
    private static final String GAMES_GAME_ID = "game_id";
    private static final String GAMES_EVENT = "event";
    private static final String GAMES_SITE = "site";
    private static final String GAMES_DATE = "date";
    private static final String GAMES_ROUND = "round";
    private static final String GAMES_RESULT = "result";
    private static final String GAMES_WHITE = "white";
    private static final String GAMES_BLACK = "black";
    private static final String GAMES_WHITE_ELO = "white_elo";
    private static final String GAMES_BLACK_ELO = "black_elo";
    private static final String GAMES_ECO = "eco";

    //TABLE_PLAYERS
    private static final String PLAYERS_PLAYER_ID = "player_id";
    private static final String PLAYERS_NAME = "name";
    private static final String PLAYERS_FIDE_ID = "fide_id";
    private static final String PLAYERS_FIDE_RATING = "fide_rating";

    //TABLE_MOVES
    private static final String MOVES_ID = "moves_id";
    private static final String MOVES_NUMBER = "moves_number";
    private static final String MOVES_GAME_ID = "game_id";
    private static final String MOVES_PLAYER_ID = "player_name";
    private static final String MOVES_POSITION = "position";

    public DbHandler(Context context, String name, CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GAMES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVES);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_PLAYERS = "CREATE TABLE " +
                TABLE_PLAYERS + "("
                + PLAYERS_PLAYER_ID + " INTEGER PRIMARY KEY,"
                + PLAYERS_NAME + " TEXT,"
                + PLAYERS_FIDE_ID + " TEXT,"
                + PLAYERS_FIDE_RATING + " TEXT,"
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
                + "FOREIGN KEY(" + GAMES_WHITE + ") REFERENCES " + TABLE_PLAYERS + "(" + PLAYERS_PLAYER_ID + ")"
                + ")";
        db.execSQL(CREATE_TABLE_GAMES);

        String CREATE_TABLE_MOVES = "CREATE TABLE " +
                TABLE_MOVES + "("
                + MOVES_ID + " INTEGER PRIMARY KEY,"
                + MOVES_NUMBER + " TEXT,"
                + MOVES_GAME_ID + " TEXT,"
                + MOVES_PLAYER_ID + " TEXT,"
                + MOVES_POSITION + " TEXT, "
                + "FOREIGN KEY(" + MOVES_GAME_ID + ") REFERENCES " + TABLE_GAMES + "(" + GAMES_GAME_ID + ")"
                + "FOREIGN KEY(" + MOVES_PLAYER_ID + ") REFERENCES " + TABLE_PLAYERS + "(" + PLAYERS_PLAYER_ID + ")"
                + ")";
        db.execSQL(CREATE_TABLE_MOVES);
    }
}
