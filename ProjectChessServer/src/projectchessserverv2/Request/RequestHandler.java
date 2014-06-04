package projectchessserverv2.Request;

import projectchessserver.data.Player;
import projectchessserverv2.Request.Data.DatabaseHandler;
import projectchessserverv2.Request.Data.Game;

import java.nio.file.Path;

/**
 * Created by zyixc on 4-6-2014.
 */
public class RequestHandler {
    private DatabaseHandler db;
    private String request;

    public RequestHandler(String request){
        this.request = request;
    }

    public Path processRequest(){
        String prequest[] = request.split("[\\.\\?=&]+");
        switch(prequest[1]){
            case "player":
                RequestResult<Player> player = new RequestResult<>(requestTypePlayer(prequest[2]));
                return player.getJSONPath();
            case "players":
                RequestResult<Player[]> players = new RequestResult<Player[]>(requestTypePlayers(prequest[2]));
                return players.getJSONPath();
            case "games":
                RequestResult<Game[]> games = new RequestResult<Game[]>(requestTypeGames(prequest));
                return games.getJSONPath();
        }
        return null;
    }

    private Player requestTypePlayer(String prequest){
        Player player = null;
        

        return player;
    }

    private Player[] requestTypePlayers(String prequest){
        Player[] players = null;

        return players;
    }

    private Game[] requestTypeGames(String[] prequest){
        Game[] games = null;

        return games;
    }

    //test
    public static void main(String[] args){
        RequestHandler rq = new RequestHandler("request.player?Aagaard");

    }
}
