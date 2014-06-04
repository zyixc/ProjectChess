package projectchessserverv2.request.data;

/**
 * Created by zyixc on 20-5-2014.
 */
public class Game {
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

    public Game(){}

    public Game(String gameid, String event, String site, String date, int round, String white, String black,
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
}
