package com.projectchessapp.projectchess.data;

/**
 * Created by Inholland on 15-5-2014.
 */
public class Moves {
    private String moves_id = null;
    private String moves_number = null;
    private String game_id = null;
    private String player_name = null;
    private String position = null;

    public Moves(String moves_id, String moves_number, String game_id, String player_name,
                 String position) {
        this.moves_id = moves_id;
        this.moves_number = moves_number;
        this.game_id = game_id;
        this.player_name = player_name;
        this.position = position;
    }

    public Moves(){
        //Empty constructor
    }

    public String getMoves_id() {
        return moves_id;
    }

    public void setMoves_id(String moves_id) {
        this.moves_id = moves_id;
    }

    public String getMoves_number() {
        return moves_number;
    }

    public void setMoves_number(String moves_number) {
        this.moves_number = moves_number;
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
