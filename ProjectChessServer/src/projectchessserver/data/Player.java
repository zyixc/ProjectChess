package projectchessserver.data;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyixc on 20-5-2014.
 */
public class Player{
    private String ID;
    private String firstname;
    private String lastname;
    private List<Games> white_games = new ArrayList<Games>();
    private List<Games> black_games = new ArrayList<Games>();

    public Player(){}

    public Player(String ID, String firstname, String lastname){
        this.ID = ID;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public List<Games> getWhite_games() {
        return white_games;
    }

    public String getName() {
        return firstname+" "+lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getID() {
        return ID;
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

    public String toJSON(){
        ObjectMapper mapper = new ObjectMapper();
        //String name = this.name.split(",")[1];
        String filename = this.lastname+".json";
        try{
            mapper.writeValue(new File(System.getProperty("user.dir")+File.separator+"JSON_files"+File.separator+filename),this);
        }catch(Exception e){
            e.printStackTrace();
        }
        return filename;
    }

    public String getJSONstring(){
        return this.lastname+".json";
    }
}
