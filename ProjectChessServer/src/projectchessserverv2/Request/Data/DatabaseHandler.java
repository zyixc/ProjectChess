package projectchessserverv2.Request.Data;

import java.sql.*;

/**
 * Created by zyixc on 4-6-2014.
 */
public class DatabaseHandler {
    private String url = "jdbc:mysql://localhost/chess";
    private String login = "root";
    private String password = "";
    private Connection conn;

    public DatabaseHandler(){
        try{
            conn = DriverManager.getConnection(url,login,password);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Player getPlayer(String ID){
        Player result = null;
        try(PreparedStatement spn = conn.prepareStatement("SELECT * FROM players WHERE id = ?");){
            spn.setString(1, ID);
            ResultSet spn_rs = spn.executeQuery();
            if(spn_rs.next()){
                result = new Player(spn_rs.getString(1),spn_rs.getString(2), spn_rs.getString(3));
            }
            return result;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Player[] getPlayers(String player_name){
        Player[] result = null;
        try(PreparedStatement spn = conn.prepareStatement("SELECT * FROM players WHERE lastName LIKE ?");){
            spn.setString(1, player_name + "%");
            ResultSet spn_rs = spn.executeQuery();
            int counter = 0;
            while(spn_rs.next()){
                result[counter] = new Player(spn_rs.getString(1),spn_rs.getString(2), spn_rs.getString(3));
            }
            return result;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private Game[] queryGames(String query){
        Game[] games = null;
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                games[rs.getRow()] = new Game(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getInt(10),
                        rs.getString(11),
                        rs.getString(12),
                        new String[]{rs.getString(13),rs.getString(15),rs.getString(17),rs.getString(19),rs.getString(21)},
                        new String[]{rs.getString(14),rs.getString(16),rs.getString(18),rs.getString(20),rs.getString(22)}
                );
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Player getGamesFromPlayer(Player player){
        String queryWhiteGames = "SELECT * FROM games WHERE white = "+player.getId();
        String queryBlackGames = "SELECT * FROM games WHERE black = "+player.getId();
        Game[] whitegames = queryGames(queryWhiteGames);
        Game[] blackgames = queryGames(queryBlackGames);
        for(Game game: whitegames){
            player.addWhite_games(game);
        }
        for(Game game: blackgames){
            player.addBlack_games(game);
        }
        return player;
    }

    public Game[] getGames(String resultfor, String minrating, String maxrating, String whiteopening1,
                            String whiteopening2, String whiteopening3, String blackopening1, String blackopening2,
                            String blackopening3, String eco){
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM games WHERE ");
        if (resultfor.equals("w")) query.append("result = '1-0' ");
        else if (resultfor.equals("b")) query.append("result = '0-1' ");
        else if (resultfor.equals("d")) query.append("result = '1/2-1/2' ");
        if (minrating != null) query.append("AND white_elo > " + minrating + " AND black_elo > " + minrating + " ");
        if (minrating != null) query.append("AND white_elo < " + maxrating + " AND black_elo < " + maxrating + " ");
        if (whiteopening1 != null) query.append("AND w1 = '" + whiteopening1 + "' ");
        if (whiteopening2 != null) query.append("AND w2 = '" + whiteopening2 + "' ");
        if (whiteopening3 != null) query.append("AND w3 = '" + whiteopening3 + "' ");
        if (blackopening1 != null) query.append("AND w1 = '" + blackopening1 + "' ");
        if (blackopening2 != null) query.append("AND w2 = '" + blackopening2 + "' ");
        if (blackopening3 != null) query.append("AND w3 = '" + blackopening3 + "' ");
        if (eco != null) query.append("AND eco = '" + eco + "' ");
        query.append("LIMIT 100");

        return queryGames(query.toString());
    }
}
