package projectchessserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import projectchessserver.data.Player;

import java.io.*;
import java.net.Socket;

/**
 * Created by zyixc on 27-5-2014.
 */
public class runClientTest {
    private static String hostname = "localhost";
    private static int port = 4444;

    public static void main(String[] args){
        try{
            Socket socket = new Socket(hostname,port);
            DataInputStream is = new DataInputStream(socket.getInputStream());
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            PrintWriter pw = new PrintWriter(os);
            pw.println("request.player?Aagaard");
            pw.flush();
            String line = null;
            while((line = in.readLine()) != null) {
                ObjectMapper mapper = new ObjectMapper();
                Player player = mapper.readValue(line,Player.class);
                System.out.println(player.toString());
                System.out.println(player.getName());
            }
            socket.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
