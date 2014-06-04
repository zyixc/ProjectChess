package projectchessserverv2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import projectchessserver.data.Player;

import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyixc on 27-5-2014.
 */
public class RunClientTest {
    private final static String hostname = "localhost";
    private final static int port = 8080;
    private ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args){
        try(
                Socket socket = new Socket(hostname,port);
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(is));)
        {
            //os.writeBytes("request.player?Aagaard"+"\n"); os.flush();
            os.write(("request.players?Aagaard"+"\n").getBytes()); os.flush();
            System.out.println("request send");
            String line;
            if((line = in.readLine()) != null) {
                ObjectMapper mapper = new ObjectMapper();
                //Player player = mapper.readValue(line,Player.class);
                List<Player> players = mapper.readValue(line, new TypeReference<List<Player>>() { });
                System.out.println(players.toString());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
