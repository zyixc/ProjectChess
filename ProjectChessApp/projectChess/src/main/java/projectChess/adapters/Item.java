package adapters;

/**
 * Created by otteran on 6/8/2014.
 */
public class Item {
    private String name;
    private String rating;
    private String number_games;

    public Item(String name, String rating, String number_games){
        super();
        this.name = name;
        this.rating = rating;
        this.number_games = number_games;
    }

    public String getName(){
        return name;
    }

    public String getRating(){
        return rating;
    }

    public String getNumber_games(){
        return number_games;
    }
}
