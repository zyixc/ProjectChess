package projectchessserverv2;

import projectchessserverv2.request.RequestHandler;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Jakob Jenkov
 * Edited by zyixc
 **/
public class WorkerRunnable implements Runnable{

    protected Socket clientSocket = null;
    protected String serverText   = null;

    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText   = serverText;
    }

    public void run() {
        try(DataInputStream is = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream os = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(is));
        ){
            System.out.println("Connected from " + clientSocket .getInetAddress() + " on port " + clientSocket .getPort()
                    + " to port " + clientSocket .getLocalPort() + " of " + clientSocket .getLocalAddress());

            String line = null;
            if((line = in.readLine()) != null) {
                RequestHandler rq = new RequestHandler(line);
                Path filepath = rq.processRequest();
                byte[] encoded = Files.readAllBytes(filepath);
                os.writeBytes(new String(encoded, Charset.defaultCharset()));
                os.flush();
                System.out.println(line+"; request succesfully handled: " + filepath.toString());

                //delete file
                Files.delete(filepath);
            }

            System.out.println("Connection closed from " + clientSocket .getInetAddress() + " on port " + clientSocket .getPort()
                    + " to port " + clientSocket .getLocalPort() + " of " + clientSocket .getLocalAddress());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
