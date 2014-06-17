package projectchessserverv2;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
/**
 * Created by Jakob Jenkov
 * Edited by zyixc
 **/
public class Server implements Runnable{

    private int serverPort = 8080;
    private ServerSocket serverSocket = null;
    private volatile boolean isStopped  = false;
    private Thread runningThread= null;

    public Server(String port){
        serverPort = Integer.parseInt(port);
    }

    public void run(){
        synchronized(this){
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();
        while(!isStopped()){
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if(isStopped()) {
                    System.out.println("Server Stopped.") ;
                    return;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }
            new Thread(new WorkerRunnable(clientSocket, "Multithreaded Server")).start();
        }
        System.out.println("Server Stopped.") ;
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port 8080", e);
        }
    }

    public static void main(String[] args) {
        Server server = new Server(args[0]);
        System.out.println("SERVER STARTING!!");
        while(true) {
            new Thread(server).start();
            try {
                Thread.sleep(24 * 60 * 60 * 1000);
            } catch (InterruptedException e) {
                System.err.println("Thread has not been used in 24 hours.");
            }
        }
    }
}
