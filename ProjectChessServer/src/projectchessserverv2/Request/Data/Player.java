package projectchessserverv2.Request.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyixc on 20-5-2014.
 */
public class Player{
    private String id;
    private String firstname;
    private String lastname;
    private List<Game> white_games = new ArrayList<Game>();
    private List<Game> black_games = new ArrayList<Game>();

    public Player(){}

    public Player(String id, String firstname, String lastname){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public List<Game> getWhite_games() {
        return white_games;
    }

    public List<Game> getBlack_games() {
        return black_games;
    }

    public void addWhite_games(Game e){
        white_games.add(e);
    }

    public void addBlack_games(Game e){
        black_games.add(e);
    }
}
