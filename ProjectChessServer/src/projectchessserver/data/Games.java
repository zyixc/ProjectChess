package projectchessserver.data;

import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zyixc on 20-5-2014.
 */
public class Games implements Serializable {
    private String event;
    private String site;
    private String date;
    private int round;
    private String white;
    private String black;
    private String result;
    private int white_elo;
    private int black_elo;
    private String eco;
    private Map<Integer,String> moves_white = new HashMap<Integer,String>();
    private Map<Integer,String> moves_black = new HashMap<Integer,String>();

    public Games(String event, String site, String date, int round, String white, String black,
                 String result, int white_elo, int black_elo, String eco, String moves) {
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
        setMoves(moves);
    }

    public String getEvent() {
        return event;
    }

    public String getSite() {
        return site;
    }

    public String getDate() {
        return date;
    }

    public int getRound() {
        return round;
    }

    public String getWhite() {
        return white;
    }

    public String getBlack() {
        return black;
    }

    public String getResult() {
        return result;
    }

    public int getWhite_elo() {
        return white_elo;
    }

    public int getBlack_elo() {
        return black_elo;
    }

    public String getEco() {
        return eco;
    }

    public Map<Integer,String> getMoves() throws ConcurrentModificationException{
        Map<Integer,String> result = new HashMap<Integer, String>();
        for(int i = 0; i < moves_white.size(); i+=2){
            result.put(i,moves_white.get(i));
            result.put(i+1,moves_black.get(i));
        }
        return result;
    }

    public Map<Integer, String> getMoves_white() {
        return moves_white;
    }

    public Map<Integer, String> getMoves_black() {
        return moves_black;
    }

    public void setMoves(String input){
        try {
            String[] input_split_result = input.split("\\s");
            int movecount = 1;
            for (int i = 0; i < input_split_result.length; i += 3) {
                moves_white.put(movecount, input_split_result[i + 1]);
                moves_black.put(movecount, input_split_result[i + 2]);
                movecount++;
            }
        }catch(Exception e){

        }
    }
}
