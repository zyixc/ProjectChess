package com.projectchessapp.projectchess.data;

/**
 * Created by Inholland on 15-5-2014.
 */
public class Player {
    String player_id = null;
    String name = null;
    String fide_id = null;
    String fide_rating = null;

    public Player(String player_id, String name, String fide_id, String fide_rating) {

        this.player_id = player_id;
        this.name = name;
        this.fide_id = fide_id;
        this.fide_rating = fide_rating;
    }

    public Player() {
    }

    public String getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(String player_id) {
        this.player_id = player_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFide_id() {
        return fide_id;
    }

    public void setFide_id(String fide_id) {
        this.fide_id = fide_id;
    }

    public String getFide_rating() {
        return fide_rating;
    }

    public void setFide_rating(String fide_rating) {
        this.fide_rating = fide_rating;
    }
}
