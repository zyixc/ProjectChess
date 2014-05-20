package projectchessserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
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
        try {
            // Get input from the client
            DataInputStream in = new DataInputStream(server.getInputStream());
            PrintStream out = new PrintStream(server.getOutputStream());
            try{
                DatabaseHandler db = new DatabaseHandler();
                while ((line = in.readLine()) != null && !line.equals(".")) {
                    out.println("I got:" + line);
                    out.print(db.getGames(line));
                }
            }catch(Exception e){
                e.printStackTrace();
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
