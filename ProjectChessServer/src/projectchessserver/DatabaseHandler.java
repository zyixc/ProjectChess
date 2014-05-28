package projectchessserver;

import projectchessserver.data.Games;
import projectchessserver.data.Player;

import java.io.File;
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

    public String getPlayer(String player_name){
        /*
            Returns filename of a player object
            Queries database if filename doesn't already exist
         */
        Player player = null;
        try{
            PreparedStatement stmt_player_name = conn.prepareStatement("SELECT name FROM players WHERE name LIKE ? LIMIT 1");
            stmt_player_name.setString(1,player_name+"%");
            ResultSet stmt_player_name_rs = stmt_player_name.executeQuery();
            if(stmt_player_name_rs.next()) {
                player = new Player(stmt_player_name_rs.getString(1));
                if(new File(System.getProperty("user.dir")+File.separator+"JSON_files"+File.separator+player.getName()+".json").isFile()){
                    return player.getName()+".json";
                }
            }else{
                return null;
            }
            System.out.println(player.getName());

            PreparedStatement stmt_player_white = conn.prepareStatement("SELECT * FROM games WHERE white LIKE ?");
            stmt_player_white.setString(1,player_name+"%");
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

            PreparedStatement stmt_player_black = conn.prepareStatement("SELECT * FROM games WHERE black LIKE ?");
            stmt_player_black.setString(1,player_name+"%");
            ResultSet stmt_player_black_rs = stmt_player_black.executeQuery();
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
        player.toJSON();
        return player.getName()+".json";
    }

    public String getGames(String resultfor, String minrating, String maxrating, String whiteopening1,
                            String whiteopening2, String whiteopening3, String blackopening1, String blackopening2,
                            String blackopening3, String eco){
        /*
            Return filename of a Games object
            Queries database everytime
         */
        Games[] games = null;

        //Get info from database
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT * FROM games WHERE ");
            if(resultfor.equals("w")) query.append("result = '1-0' ");
            else if(resultfor.equals("b")) query.append("result = '0-1' ");
            else if(resultfor.equals("d")) query.append("result = '1/2-1/2' ");
            if(minrating!=null) query.append("AND white_elo > "+minrating+" AND black_elo > "+minrating+" ");
            if(minrating!=null) query.append("AND white_elo < "+maxrating+" AND black_elo < "+maxrating+" ");
            if(whiteopening1!=null) query.append("AND w1 = '"+whiteopening1+"' ");
            if(whiteopening2!=null) query.append("AND w2 = '"+whiteopening2+"' ");
            if(whiteopening3!=null) query.append("AND w3 = '"+whiteopening3+"' ");
            if(blackopening1!=null) query.append("AND w1 = '"+blackopening1+"' ");
            if(blackopening2!=null) query.append("AND w2 = '"+blackopening2+"' ");
            if(blackopening3!=null) query.append("AND w3 = '"+blackopening3+"' ");
            if(eco!=null) query.append("AND eco = '"+eco+"' ");

            //info to Objects
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query.toString());
            while (rs.next()) {

            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
