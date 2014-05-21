package projectchessserver;

import projectchessserver.data.Games;
import projectchessserver.data.Player;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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
    private String line, input;

    doComms(Socket server) {
        this.server = server;
    }

    public void run() {
        Player player;
        try {
            // Get input from the client
            BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
            PrintStream out = new PrintStream(server.getOutputStream());
            out.print("Search term: ");
            try{
                DatabaseHandler db = new DatabaseHandler();
                while ((line = in.readLine()) != null && !line.equals(".")) {
                    out.println("I got: " + line);
                    player = db.getPlayer(line);
                    try {
                        System.out.println(player.getName()+" White: "+player.getWhite_games().size()+"| Black: "+player.getBlack_games().size());
                    }catch(Exception e){
                        System.err.println("Missing info");
                    }
                }
            }catch(Exception e){
                System.err.println("No games found");
            }

            // Now write to the client

            System.out.println("Overall message is:" + input);
            out.println("Overall message is:" + input);

            server.close();
        } catch (IOException ioe) {
            System.out.println("IOException on socket listen: " + ioe);
            ioe.printStackTrace();
        }
    }
}
