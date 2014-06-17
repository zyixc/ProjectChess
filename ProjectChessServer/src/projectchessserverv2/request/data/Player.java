package projectchessserverv2.request.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zyixc on 20-5-2014.
 */
public class Player{
    private String id;
    private String firstname;
    private String lastname;
    private String rating;
    private String numberofgames;
    private String w1;
    private String w2;
    private String w3;
    private List<Game> white_games = new ArrayList<Game>();
    private List<Game> black_games = new ArrayList<Game>();

    public Player(){}

    public Player(String id, String firstname, String lastname){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void calculateNeededData(){
        HashMap<String,Integer> prefmoves = new HashMap<String, Integer>();
        for(Game temp: white_games){
            String[] result = temp.getW();
            for(String result_string: result){
                int count = prefmoves.containsKey(result_string) ? prefmoves.get(result_string) : 0;
                prefmoves.put(result_string, count + 1);
            }
        }
        Set<Map.Entry<String, Integer>> entrySet = prefmoves.entrySet();
        if(!entrySet.isEmpty()) {
            List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(entrySet);
            Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> stringIntegerEntry, Map.Entry<String, Integer> stringIntegerEntry2) {
                    return stringIntegerEntry.getValue().compareTo(stringIntegerEntry2.getValue());
                }
            });
            w1 = entryList.get(0).getKey();
            w2 = entryList.get(1).getKey();
            w3 = entryList.get(2).getKey();

            Game ratinggame = white_games.get(white_games.size()-1);
            rating = Integer.toString(ratinggame.getWhite_elo());
        }

        int number_games = white_games.size();
        number_games += black_games.size();
        numberofgames = Integer.toString(number_games);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNumberofgames() {
        return numberofgames;
    }

    public void setNumberofgames(String numberofgames) {
        this.numberofgames = numberofgames;
    }

    public String getW1() {
        return w1;
    }

    public void setW1(String w1) {
        this.w1 = w1;
    }

    public String getW2() {
        return w2;
    }

    public void setW2(String w2) {
        this.w2 = w2;
    }

    public String getW3() {
        return w3;
    }

    public void setW3(String w3) {
        this.w3 = w3;
    }

    public List<Game> getWhite_games() {
        return white_games;
    }

    public void setWhite_games(List<Game> white_games) {
        this.white_games = white_games;
    }

    public List<Game> getBlack_games() {
        return black_games;
    }

    public void setBlack_games(List<Game> black_games) {
        this.black_games = black_games;
    }

    public void addWhite_games(Game e){
        white_games.add(e);
    }

    public void addBlack_games(Game e){
        black_games.add(e);
    }
}
