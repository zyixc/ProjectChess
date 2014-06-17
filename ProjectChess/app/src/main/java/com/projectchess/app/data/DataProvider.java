package com.projectchess.app.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;


/**
 * Created by zyixc on 15-5-2014.
 */
public enum DataProvider{
    INSTANCE;
    private String hostname;
    private int port;
    private ObjectMapper mapper = new ObjectMapper();
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private BufferedReader in;
    private SharedPreferences prefs;

    public void initDataProvider(SharedPreferences sp){
        prefs = sp;
        if (!prefs.contains("ip")) writeDefaults();
    }

    private void writeDefaults() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("ip", "10.0.2.2");
        editor.putString("port", "8080");
        editor.apply();
    }

    private void updateConnectionData() {
        hostname = prefs.getString("ip","10.0.2.2");
        port = Integer.parseInt(prefs.getString("port", "8080"));
    }

    private boolean openConnection(){
        updateConnectionData();
        try{
            socket = new Socket(hostname, port);
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
        boolean result = false;
        DownloadTestConnection test = new DownloadTestConnection();
        test.execute("");
        try{
            result = test.get();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private class DownloadTestConnection extends AsyncTask<String, Void, Boolean>{
        @Override
        protected Boolean doInBackground(String... arg){
            if(openConnection()){
                closeConnection();
                return true;
            }
            return false;
        }
    }

    public Player requestPlayer(String id) {
        try {
            openConnection();
            os.write(("request.player?" + id + "\n").getBytes());
            os.flush();
            String answer;
            if ((answer = in.readLine()) != null) {
                Player player = mapper.readValue(answer, Player.class);
                closeConnection();
                return player;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Player> requestPlayerList(String namestring){
        List<Player> playerList = null;
        DownloadPlayerList dp = new DownloadPlayerList();
        dp.execute(namestring);
        try{
            playerList = dp.get();
        }catch(Exception e){
            e.printStackTrace();
        }
        return playerList == null ? Arrays.<Player>asList() : playerList;
    }

    private class DownloadPlayerList extends AsyncTask<String, Void, List<Player>>{
        @Override
        protected List<Player> doInBackground(String... arg){
            try{
                openConnection();
                os.write(("request.players?"+arg[0]+"\n").getBytes());
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
    }

    public List<Game> requestGameList(String resultfor, String minrating, String maxrating, String whiteopening1,
                                      String whiteopening2, String whiteopening3, String blackopening1, String blackopening2,
                                      String blackopening3, String eco){
        String[] args = {resultfor,minrating,maxrating,whiteopening1,whiteopening2,whiteopening3,blackopening1,blackopening2,
        blackopening3,eco};
        List<Game> gameList = null;
        DownloadGameList dg = new DownloadGameList();
        dg.execute(args);
        try{
            gameList = dg.get();
        }catch(Exception e){
            e.printStackTrace();
        }
        return gameList == null ? Arrays.<Game>asList() : gameList;
    }

    private class DownloadGameList extends AsyncTask<String[], Void, List<Game>>{
        @Override
        protected List<Game> doInBackground(String[]... arg){
            try{
            openConnection();
            os.write(("request.games?"+arg[0][0]+"&"+arg[0][1]+"&"+arg[0][2]+"&"+arg[0][3]+"&"
                    +arg[0][4]+"&"+arg[0][5]+"&"+arg[0][6]+"&"+arg[0][7]+"&"+arg[0][8]+"&"
                    +arg[0][9]+"&"+"\n").getBytes());
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

}

