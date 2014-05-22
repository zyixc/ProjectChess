/**
 * Created by zyixc on 13-5-2014.
 */
import java.io.*;
import java.lang.ClassNotFoundException;
import java.sql.*;


public class insertMoves {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, NumberFormatException, IOException {
        //SQL CONNECTION
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost/test";
        Connection conn = DriverManager.getConnection(url,"root","");
        conn.setAutoCommit(false);

        int gameCount = 0;

        BufferedReader br = new BufferedReader(new FileReader("Z:\\chessdatabase\\millionbase-2.22.pgn"));
        String line = null;

        String Event=null, Date=null, White=null, Black=null, Result=null, WhiteElo="0", BlackElo="0", ECO=null;
        StringBuilder Moves = new StringBuilder();

        while ((line = br.readLine()) != null) {
            if(line.length()>0){
                if(line.contains("White ")){
                    White = line.substring(8,line.lastIndexOf(" "));
                }
                else if(line.contains("Black ")){
                    Black = line.substring(8,line.lastIndexOf(" "));
                }
                else if(line.contains("1.")){
                    if(gameCount<1240000){
                        while(line.length()>0){
                            line = br.readLine();
                        }
                        if(gameCount%10000==0) System.out.println(gameCount);
                    }else {
                        int moveCount = 1;
                        try {
                            PreparedStatement stmt = conn.prepareStatement("INSERT INTO `moves`(`move_number`, `game_id`, `player_name`, `position`) VALUES(?,?,?,?)");

                            while (line.length() > 0) {
                                String[] moves = null;
                                moves = line.split(" ");
                                for (int i = 0; i < moves.length; i += 3) {
                                    stmt.setString(1, Integer.toString(moveCount));
                                    stmt.setString(2, Integer.toString(gameCount));
                                    stmt.setString(3, White);
                                    stmt.setString(4, moves[i + 1]);
                                    stmt.executeUpdate();
                                    stmt.setString(1, Integer.toString(moveCount));
                                    stmt.setString(2, Integer.toString(gameCount));
                                    stmt.setString(3, Black);
                                    stmt.setString(4, moves[i + 2]);
                                    stmt.executeUpdate();
                                    moveCount++;
                                }
                                line = br.readLine();
                            }
                        }catch(Exception e){
                            try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("failedrecords2.txt", true)))) {
                                out.println(gameCount + moveCount);
                            }catch (IOException e2) {
                            }
                        }

                        if (gameCount % 10000 == 0) {
                            conn.commit();
                            System.out.println(gameCount);
                        }
                        if(gameCount % 100000 == 0){
                            conn.close();
                            conn = DriverManager.getConnection(url,"root","");
                            conn.setAutoCommit(false);
                        }

                    }
                    gameCount++;
                    Moves.setLength(0);
                }
            }
        }
        conn.close();
        br.close();
        System.out.println("JOB DONE!");
    }

}