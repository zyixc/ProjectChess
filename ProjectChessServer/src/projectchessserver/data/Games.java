package projectchessserver.data;

import java.io.Serializable;

/**
 * Created by zyixc on 20-5-2014.
 */
public class Games implements Serializable {
    private String event;
    private String site;
    private String date;
    private String round;
    private String white;
    private String black;
    private String result;
    private String white_elo;
    private String black_elo;
    private String eco;
    private String moves;

    public Games(String event, String site, String date, String round, String white, String black,
                 String result, String white_elo, String black_elo, String eco, String moves) {
        this.event = event;
        this.site = site;
        this.date = date;
        this.round = round;
        this.white = white;
        this.black = black;
        this.result = result;
        this.white_elo = white_elo;
        this.black_elo = black_elo;
        this.eco = eco;
        this.moves = moves;
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

    public String getBlack() {
        return black;
    }

    public void setBlack(String black) {
        this.black = black;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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

    public String getMoves() {
        return moves;
    }

    public void setMoves(String moves) {
        this.moves = moves;
    }
}
