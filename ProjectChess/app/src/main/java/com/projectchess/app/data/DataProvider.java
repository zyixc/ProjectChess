package com.projectchess.app.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

/**
 * Created by zyixc on 15-5-2014.
 */
public enum DataProvider {
    INSTANCE;
    private String hostname;
    private int port;
    private ObjectMapper mapper = new ObjectMapper();
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private BufferedReader in;

    public void initDataProvider(Context context){
        SharedPreferences prefs = context.getSharedPreferences("",Context.MODE_PRIVATE);
        hostname = prefs.getString("ip","192.168.178.25");
        port = prefs.getInt("port",8080);
    }

    private boolean openConnection(){
        try{
            socket = new Socket(hostname,port);
            is = socket.getInputStream();
            os = socket.getOutputStream();
            in = new BufferedReader(new InputStreamReader(is));
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
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

    public boolean testConnection(){
        if(openConnection()){
            closeConnection();
            return true;
        }return false;
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
            e.printStackTrace();
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
                    +whiteopening2+"&"+whiteopening3+"&"+blackopening1+"&"+blackopening2+"&"+blackopening2+"&"
                    +blackopening2+"&"+"\n").getBytes());
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

