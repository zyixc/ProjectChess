package projectchessserver.data;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zyixc on 20-5-2014.
 */
public class Games{
    private String gameid;
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
    private String moves;
    private String[] w;
    private String[] b;

    //private Map<Integer,String> moves_white = new HashMap<Integer,String>();
    //private Map<Integer,String> moves_black = new HashMap<Integer,String>();

    public Games(){}

    public Games(String gameid, String event, String site, String date, int round, String white, String black,
                 String result, int white_elo, int black_elo, String eco, String moves, String[] w, String[] b) {
        this.gameid = gameid;
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
        this.w = w;
        this.b = b;
    }

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
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

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
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

    public int getWhite_elo() {
        return white_elo;
    }

    public void setWhite_elo(int white_elo) {
        this.white_elo = white_elo;
    }

    public int getBlack_elo() {
        return black_elo;
    }

    public void setBlack_elo(int black_elo) {
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

    public String[] getW() {
        return w;
    }

    public void setW(String[] w) {
        this.w = w;
    }

    public String[] getB() {
        return b;
    }

    public void setB(String[] b) {
        this.b = b;
    }

    public String toJSON(){
        ObjectMapper mapper = new ObjectMapper();
        //String name = this.name.split(",")[1];
        String filename = this.toString()+".json";
        try{
            mapper.writeValue(new File(System.getProperty("user.dir")+File.separator+"JSON_files"+File.separator+filename),this);
        }catch(Exception e){
            e.printStackTrace();
        }
        return filename;
    }

//    public Map<Integer,String> getMoves() throws ConcurrentModificationException{
//        Map<Integer,String> result = new HashMap<Integer, String>();
//        for(int i = 0; i < moves_white.size(); i+=2){
//            result.put(i,moves_white.get(i));
//            result.put(i+1,moves_black.get(i));
//        }
//        return result;
//    }
//
//    public Map<Integer, String> getMoves_white() {
//        return moves_white;
//    }
//
//    public Map<Integer, String> getMoves_black() {
//        return moves_black;
//    }
//
//    public void setMoves(String input){
//        try {
//            String[] input_split_result = input.split("\\s");
//            int movecount = 1;
//            for (int i = 0; i < input_split_result.length; i += 3) {
//                moves_white.put(movecount, input_split_result[i + 1]);
//                moves_black.put(movecount, input_split_result[i + 2]);
//                movecount++;
//            }
//        }catch(Exception e){
//
//        }
//    }
}
