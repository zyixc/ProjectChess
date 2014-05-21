package projectchessserver.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyixc on 20-5-2014.
 */
public class Player implements Serializable {
    private String name;
    private List<Games> white_games = new ArrayList<Games>();
    private List<Games> black_games = new ArrayList<Games>();

    public Player(String name){
        this.name = name;
    }

    public List<Games> getWhite_games() {
        return white_games;
    }

    public String getName() {
        return name;
    }

    public List<Games> getBlack_games() {
        return black_games;
    }

    public void addWhite_games(Games e){
        white_games.add(e);
    }

    public void addBlack_games(Games e){
        black_games.add(e);
    }
}
