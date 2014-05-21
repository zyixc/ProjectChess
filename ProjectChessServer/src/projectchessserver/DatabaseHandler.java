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

    public Player getPlayer(String player_name){
        Player player = null;
        try{
            PreparedStatement stmt_player_name = conn.prepareStatement("SELECT name FROM players WHERE name LIKE ? LIMIT 1");
            stmt_player_name.setString(1,"'"+player_name+"%'");
            ResultSet stmt_player_name_rs = stmt_player_name.executeQuery();
            if(stmt_player_name_rs.next()) {
                player = new Player(stmt_player_name_rs.getString(1));
            }else{
                return player;
            }
            System.out.println(player.getName());

            PreparedStatement stmt_player_white = conn.prepareStatement("SELECT * FROM games WHERE white LIKE '?'");
            stmt_player_white.setString(1,player.getName());
            ResultSet stmt_player_white_rs = stmt_player_white.executeQuery();
            while(stmt_player_white_rs.next()){
                player.addWhite_games(new Games(stmt_player_white_rs.getString(2),
                        stmt_player_white_rs.getString(3),
                        stmt_player_white_rs.getString(4),
                        stmt_player_white_rs.getInt(5),
                        stmt_player_white_rs.getString(6),
                        stmt_player_white_rs.getString(7),
                        stmt_player_white_rs.getString(8),
                        stmt_player_white_rs.getInt(9),
                        stmt_player_white_rs.getInt(10),
                        stmt_player_white_rs.getString(11),
                        stmt_player_white_rs.getString(12)));
            }

            PreparedStatement stmt_player_black = conn.prepareStatement("SELECT * FROM games WHERE black LIKE '?'");
            stmt_player_black.setString(1,player.getName());
            ResultSet stmt_player_black_rs = stmt_player_white.executeQuery();
            while(stmt_player_black_rs.next()){
                player.addBlack_games(new Games(stmt_player_black_rs.getString(2),
                        stmt_player_black_rs.getString(3),
                        stmt_player_black_rs.getString(4),
                        stmt_player_black_rs.getInt(5),
                        stmt_player_black_rs.getString(6),
                        stmt_player_black_rs.getString(7),
                        stmt_player_black_rs.getString(8),
                        stmt_player_black_rs.getInt(9),
                        stmt_player_black_rs.getInt(10),
                        stmt_player_black_rs.getString(11),
                        stmt_player_black_rs.getString(12)));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return player;
    }
}
