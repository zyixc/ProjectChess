package projectchessserverv2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import projectchessserverv2.request.data.Game;
import projectchessserverv2.request.data.Player;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

/**
 * Created by zyixc on 15-5-2014.
 */
public enum Client {
    INSTANCE;
    ;
    private final static String hostname = "localhost";
    private final static int port = 8080;
    private ObjectMapper mapper = new ObjectMapper();
    private static Socket socket;
    private static InputStream is;
    private static OutputStream os;
    private static BufferedReader in;


    private void openConnection(){
        try{
            socket = new Socket(hostname,port);
            is = socket.getInputStream();
            os = socket.getOutputStream();
            in = new BufferedReader(new InputStreamReader(is));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void closeConnection(){
        try{
            in.close();
            os.close();
            is.close();
            socket.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Player requestPlayer(String id){
        try{
            openConnection();
            os.write(("request.player?"+id+"\n").getBytes());
            os.flush();
            String answer;
            if((answer = in.readLine())!=null){
                Player player = mapper.readValue(answer, Player.class);
                closeConnection();
                return player;
            }
        }catch(Exception e){

        }
        return null;
    }

    public List<Player> requestPlayerList(String namestring){
        try{
            openConnection();
            os.write(("request.players?"+namestring+"\n").getBytes());
            os.flush();
            String answer;
            if((answer = in.readLine()) != null) {
                ObjectMapper mapper = new ObjectMapper();
                List<Player> players = mapper.readValue(answer, new TypeReference<List<Player>>() { });
                closeConnection();
                return players;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Game> requestGameList(String resultfor, String minrating, String maxrating, String whiteopening1,
                                      String whiteopening2, String whiteopening3, String blackopening1, String blackopening2,
                                      String blackopening3, String eco){
        try{
            openConnection();
            os.write(("request.games?"+resultfor+"&"+minrating+"&"+maxrating+"&"+whiteopening1+"&"
                    +whiteopening2+"&"+whiteopening3+"&"+blackopening1+"&"+blackopening2+"&"+blackopening3+"&"
                    +eco+"&"+"\n").getBytes());
            os.flush();
            String answer;
            if((answer = in.readLine()) != null) {
                ObjectMapper mapper = new ObjectMapper();
                List<Game> players = mapper.readValue(answer, new TypeReference<List<Game>>() { });
                closeConnection();
                return players;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}

