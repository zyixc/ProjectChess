package com.projectchessap.projectchess.data;

/**
 * Created by Inholland on 15-5-2014.
 */
public class Games {
    private String game_id = null;
    private String event = null;
    private String site = null;
    private String date = null;
    private String round = null;
    private String white = null;
    private String result = null;
    private String black = null;
    private String white_elo = null;
    private String black_elo = null;
    private String eco = null;

    public Games(String game_id, String event, String site, String date, String round, String white,
                 String result, String black, String white_elo, String black_elo, String eco) {
        this.game_id = game_id;
        this.event = event;
        this.site = site;
        this.date = date;
        this.round = round;
        this.white = white;
        this.result = result;
        this.black = black;
        this.white_elo = white_elo;
        this.black_elo = black_elo;
        this.eco = eco;
    }

    public Games(){
        //Empty constructor
    }

    public String getGame_id() {
        return game_id;
    }

    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getWhite() {
        return white;
    }

    public void setWhite(String white) {
        this.white = white;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getBlack() {
        return black;
    }

    public void setBlack(String black) {
        this.black = black;
    }

    public String getWhite_elo() {
        return white_elo;
    }

    public void setWhite_elo(String white_elo) {
        this.white_elo = white_elo;
    }

    public String getBlack_elo() {
        return black_elo;
    }

    public void setBlack_elo(String black_elo) {
        this.black_elo = black_elo;
    }

    public String getEco() {
        return eco;
    }

    public void setEco(String eco) {
        this.eco = eco;
    }

    public static Games createGames() {
        return new Games();

    }
}
