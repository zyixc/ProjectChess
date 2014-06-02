package projectchessserver;

import com.fasterxml.jackson.databind.ObjectMapper;
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
        String url = "jdbc:mysql://localhost/chess";
        conn = DriverManager.getConnection(url, "root", "");
    }

    public String getPlayer(String player_name){
        /*
            Returns filename of a player object
            Queries database if filename doesn't exist
         */
        Player player = null;
        try{
            //## Step1 find name
            //TODO BUG, database contains for example: id[8].firstName[Dariusz].lastName[Okon] && id[82726].firstName[D.].lastName[Okon]. which is the same person
            PreparedStatement spn = conn.prepareStatement("SELECT * FROM players WHERE lastName LIKE ? LIMIT 1");
            spn.setString(1, player_name + "%");
            ResultSet spn_rs = spn.executeQuery();
            if(spn_rs.next()) {
                player = new Player(spn_rs.getString(1),spn_rs.getString(2), spn_rs.getString(3));
                if(new File(System.getProperty("user.dir")+File.separator+"JSON_files"+File.separator+player.getLastname()+".json").isFile()){
                    System.out.println("Found playerfile in cache!");
                    return player.getLastname()+".json";
                }
            }else{
                return null;
            }
            System.out.println("##Step1 found player: "+player.getName());
            //## Step2 get white games
            PreparedStatement spw = conn.prepareStatement("SELECT * FROM games WHERE white = ?");
            spw.setString(1,player.getID());
            ResultSet spw_rs = spw.executeQuery();
            //TODO remove counter
            int counter1 = 0;
            while(spw_rs.next()){
                counter1++;
                player.addWhite_games(new Games(
                        spw_rs.getString(1),
                        spw_rs.getString(2),
                        spw_rs.getString(3),
                        spw_rs.getString(4),
                        spw_rs.getInt(5),
                        spw_rs.getString(6),
                        spw_rs.getString(7),
                        spw_rs.getString(8),
                        spw_rs.getInt(9),
                        spw_rs.getInt(10),
                        spw_rs.getString(11),
                        spw_rs.getString(12),
                        new String[]{spw_rs.getString(13),spw_rs.getString(15),spw_rs.getString(17),spw_rs.getString(19),spw_rs.getString(21),},
                        new String[]{spw_rs.getString(14),spw_rs.getString(16),spw_rs.getString(18),spw_rs.getString(20),spw_rs.getString(22),}
                ));
            }
            System.out.println("#Step2 white games: "+counter1);

            //## Step3 get black games
            PreparedStatement spb = conn.prepareStatement("SELECT * FROM games WHERE black = ?");
            spb.setString(1, player.getID());
            ResultSet spb_rs = spb.executeQuery();
            //TODO remove counter
            int counter2 = 0;
            while(spb_rs.next()){
                counter2++;
                player.addBlack_games(new Games(
                        spb_rs.getString(1),
                        spb_rs.getString(2),
                        spb_rs.getString(3),
                        spb_rs.getString(4),
                        spb_rs.getInt(5),
                        spb_rs.getString(6),
                        spb_rs.getString(7),
                        spb_rs.getString(8),
                        spb_rs.getInt(9),
                        spb_rs.getInt(10),
                        spb_rs.getString(11),
                        spb_rs.getString(12),
                        new String[]{spb_rs.getString(13),spb_rs.getString(15),spb_rs.getString(17),spb_rs.getString(19),spb_rs.getString(21)},
                        new String[]{spb_rs.getString(14),spb_rs.getString(16),spb_rs.getString(18),spb_rs.getString(20),spb_rs.getString(22)}
                ));
            }
            System.out.println("#Step3 black games: "+counter2);
            //##Step4 player to json file
            player.toJSON();
            System.out.println("#Step4 jsonfilename: "+player.getJSONstring());
        }catch(Exception e){
            e.printStackTrace();
        }
        if(player!=null)
            return player.getLastname()+".json";
        else
            return null;
    }

    public String getGames(String resultfor, String minrating, String maxrating, String whiteopening1,
                            String whiteopening2, String whiteopening3, String blackopening1, String blackopening2,
                            String blackopening3, String eco){
        /*
            Return filename of a Games object
            Queries database everytime
         */
        Games[] games = new Games[100];

        //Get info from database
        try {
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
            query.append("LIMIT 100 ");

            //info to Objects
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query.toString());
            while (rs.next()) {
                games[rs.getRow()] = new Games(
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

        ObjectMapper mapper = new ObjectMapper();
        String filename = games.toString()+".json";
        try{
            mapper.writeValue(new File(System.getProperty("user.dir")+File.separator+"JSON_files"+File.separator+filename),this);
        }catch(Exception e){
            e.printStackTrace();
        }

        return games.toString()+".json";
    }
}
