package projectchessserver;

import projectchessserver.data.Games;
import projectchessserver.data.Player;

import java.sql.*;

/**
 * Created by zyixc on 20-5-2014.
 */
public class DatabaseHandler {
    Connection conn;

    public DatabaseHandler() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost/test";
        conn = DriverManager.getConnection(url, "root", "");
    }

    public Player getGames(String player_name){
        Player player = null;
        try{
            Statement stmt_player_name = conn.createStatement();
            ResultSet stmt_player_name_rs = stmt_player_name.executeQuery("SELECT name FROM players WHERE name LIKE '"+player_name+"%' LIMIT 1");
            player = new Player(stmt_player_name_rs.getString(1));

            Statement stmt_player_white = conn.createStatement();
            ResultSet stmt_player_white_rs = stmt_player_white.executeQuery("SELECT * FROM games WHERE white LIKE '"+stmt_player_name_rs.getString(1)+"%'");
            while(stmt_player_white_rs.next()){
                player.addWhite_games(new Games());
            }

            Statement stmt_player_black = conn.createStatement();
            ResultSet stmt_player_black_rs = stmt_player_black.executeQuery("SELECT * FROM games WHERE black LIKE '"+stmt_player_name_rs.getString(1)+"%'");
            while(stmt_player_black_rs.next()){
                player.addBlack_games(new Games());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return player;
    }
}
