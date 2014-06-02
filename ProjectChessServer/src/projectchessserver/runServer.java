package projectchessserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import projectchessserver.data.Games;
import projectchessserver.data.Player;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zyixc on 20-5-2014.
 */
public class runServer {
    private static int port = 4444, maxConnections = 0;

    // Listen for incoming connections and handle them
    public static void main(String[] args) {
        int i = 0;

        try {
            ServerSocket listener = new ServerSocket(port);
            Socket server;

            while ((i++ < maxConnections) || (maxConnections == 0)) {
                doComms connection;

                server = listener.accept();
                doComms conn_c = new doComms(server);
                Thread t = new Thread(conn_c);
                t.start();
            }
        } catch (IOException ioe) {
            System.out.println("IOException on socket listen: " + ioe);
            ioe.printStackTrace();
        }
    }
}

class doComms implements Runnable {
    private Socket server;
    private String line;

    doComms(Socket server) {
        this.server = server;
    }

    public void run() {
        try {
            DataInputStream is = new DataInputStream(server.getInputStream());
            DataOutputStream os = new DataOutputStream(server.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            System.out.println("Connected from " + server .getInetAddress() + " on port "
                    + server .getPort() + " to port " + server .getLocalPort() + " of "
                    + server .getLocalAddress());

            try{
                DatabaseHandler db = new DatabaseHandler();

                while ((line = in.readLine()) != null) {
                    String request[] = line.split("[\\.\\?=\\&]+");
                    if(request[0].equals("request")&&request[1].equals("player")){
                        //get json file
                        byte[] encoded = Files.readAllBytes(Paths.get("JSON_files" + File.separator + db.getPlayer(request[2])));
                        System.out.println(Paths.get("JSON_files" + File.separator + "filehere"));
                        String filestring = new String(encoded, Charset.defaultCharset());
                        os.writeBytes(filestring);
                        os.flush();
                    }else if(request[0].equals("request")&&request[1].equals("games")){

                    }
                    else{
                        System.out.println("Nothing found");
                    }
                }
//                while ((line = in.readLine()) !=null) {
//                    System.out.println(line);
//                    os.writeBytes("Request received: "+line+"\n");
//                }
            }catch(Exception e){
                e.printStackTrace();
            }
            System.out.println("Connection closed from " + server .getInetAddress() + " on port "
                    + server .getPort() + " to port " + server .getLocalPort() + " of "
                    + server .getLocalAddress());
            server.close();
        } catch (IOException e) {
            System.out.println("IOException on socket listen: " + e);
            e.printStackTrace();
        }
    }
}
