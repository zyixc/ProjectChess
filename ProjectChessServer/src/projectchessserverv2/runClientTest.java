package projectchessserverv2;

import projectchessserverv2.request.data.Game;
import projectchessserverv2.request.data.Player;

import java.util.List;

/**
 * Created by zyixc on 6/5/14.
 */
public class runClientTest {
    public static void main(String[] args){
//        Player dimitri = Client.INSTANCE.requestPlayer("146119");
//        if(dimitri != null)
//            System.out.println(dimitri.getFirstname()+" "+dimitri.getLastname());
//        else
//            System.out.println("Object returned null!");
        List<Player> playerlist = Client.INSTANCE.requestPlayerList("Aagaard");
        for(Player player: playerlist){
            System.out.println(player.getFirstname()+" "+player.getLastname());
        }
//        List<Game> gamelist = Client.INSTANCE.requestGameList(null,"1200","2000",null,null,null,null,null,null,"A32");
//        for(Game game: gamelist){
//            System.out.println(game.getGameid()+" "+game.getDate());
//        }
    }
}
