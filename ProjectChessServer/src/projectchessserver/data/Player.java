package projectchessserver.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by zyixc on 20-5-2014.
 */
public class Player implements Serializable {
    private String name;
    private ArrayList<Games> white_games = new ArrayList<Games>();
    private ArrayList<Games> black_games = new ArrayList<Games>();

    public Player(String name){
        this.name = name;
    }

    public ArrayList<Games> getWhite_games() {
        return white_games;
    }

    public ArrayList<Games> getBlack_games() {
        return black_games;
    }

    public void addWhite_games(Games e){
        white_games.add(e);
    }

    public void addBlack_games(Games e){
        black_games.add(e);
    }
}
