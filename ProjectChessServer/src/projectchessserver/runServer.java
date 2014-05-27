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
    private Pattern my_pattern = Pattern.compile("request\\.player\\?.+");
    private Matcher my_matcher;

    doComms(Socket server) {
        this.server = server;
    }

    public void run() {
        try {
            DataInputStream is = new DataInputStream(server.getInputStream());
            DataOutputStream os = new DataOutputStream(server.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
            PrintWriter pw = new PrintWriter(os);
            System.out.println("Connected from " + server .getInetAddress() + " on port "
                    + server .getPort() + " to port " + server .getLocalPort() + " of "
                    + server .getLocalAddress());

            try{
                DatabaseHandler db = new DatabaseHandler();
                ObjectMapper mapper = new ObjectMapper();

                while ((line = in.readLine()) != null) {
                    my_matcher = my_pattern.matcher(line);
                    String request = null;

                    if(my_matcher.find()){
                        //get requested name
                        request = my_matcher.group();
                        System.out.println("Request: "+request);
                        request = request.split("\\?")[1];
                        System.out.println("Found: "+request);

                        //get json file
                        byte[] encoded = Files.readAllBytes(Paths.get("JSON_files\\" + db.getPlayer(request)));
                        String filestring = new String(encoded, Charset.defaultCharset());
                        pw.println(filestring);
                        pw.flush();
                    }else{
                        System.out.println("Nothing found");
                    }
                }
            }catch(Exception e){

            }
            System.out.println("Connected closed from " + server .getInetAddress() + " on port "
                    + server .getPort() + " to port " + server .getLocalPort() + " of "
                    + server .getLocalAddress());
            server.close();
            is.close();
            os.close();
        } catch (IOException e) {
            System.out.println("IOException on socket listen: " + e);
            e.printStackTrace();
        }
    }
}
