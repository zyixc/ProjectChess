package projectchessserverv2.Request;

import projectchessserverv2.Request.Data.Player;
import projectchessserverv2.Request.Data.DatabaseHandler;
import projectchessserverv2.Request.Data.Game;

import java.nio.file.Path;
import java.util.List;

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
        db = new DatabaseHandler();
        String prequest[] = request.split("[\\.\\?=&]+");
        switch(prequest[1]){
            case "player":
                RequestResult<Player> player = new RequestResult<>(requestTypePlayer(prequest[2]));
                return player.getJSONPath();
            case "players":
                RequestResult<List<Player>> players = new RequestResult<>(requestTypePlayers(prequest[2]));
                return players.getJSONPath();
            case "games":
                RequestResult<List<Game>> games = new RequestResult<>(requestTypeGames(prequest));
                return games.getJSONPath();
        }
        return null;
    }

    private Player requestTypePlayer(String prequest){
        Player player = db.getPlayer(prequest);
        return player;
    }

    private List<Player> requestTypePlayers(String prequest){
        List<Player> players = db.getPlayers(prequest);
        return players;
    }

    private List<Game> requestTypeGames(String[] prequest){
        List<Game> games = db.getGames(prequest[3],prequest[4],prequest[5],prequest[6],prequest[7],prequest[8],prequest[9],
                prequest[10],prequest[11],prequest[12]);
        return games;
    }

    //test
    public static void main(String[] args){
        RequestHandler rq = new RequestHandler("request.players?Aagaard");
        Path path = rq.processRequest();
        System.out.println(path.toString());
    }
}
