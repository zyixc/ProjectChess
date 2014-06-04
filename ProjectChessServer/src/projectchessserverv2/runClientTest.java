package projectchessserverv2;

import com.fasterxml.jackson.databind.ObjectMapper;
import projectchessserver.data.Player;

import java.io.*;
import java.net.Socket;

/**
 * Created by zyixc on 27-5-2014.
 */
public class runClientTest {
    private final static String hostname = "localhost";
    private final static int port = 4444;

    public static void main(String[] args){
        try(
                Socket socket = new Socket(hostname,port);
                DataInputStream is = new DataInputStream(socket.getInputStream());
                DataOutputStream os = new DataOutputStream(socket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(is));)
        {
            os.writeBytes("request.player?Aagaard"+"\n"); os.flush();
            System.out.println("request send");
            String line;
            if((line = in.readLine()) != null) {
                if(line.equals("no.result")){
                    System.out.println("no result found");
                    return;
                }
                ObjectMapper mapper = new ObjectMapper();
                Player player = mapper.readValue(line,Player.class);
                System.out.println(player.toString());
                System.out.println(player.getFirstname()+" "+player.getLastname());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
