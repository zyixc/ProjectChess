import java.io.BufferedReader;
import java.lang.ClassNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;


public class main {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, NumberFormatException, IOException {
		//SQL CONNECTION
		Class.forName("com.mysql.jdbc.Driver"); 
		String url = "jdbc:mysql://localhost/test";
	    Connection conn = DriverManager.getConnection(url,"root","");  
        conn.setAutoCommit(false);

		int gameCount = 0;
		
		BufferedReader br = new BufferedReader(new FileReader("Z:\\chessdatabase\\millionbase-2.22.pgn"));
		 String line = null;
		 
		 String Event=null, Site=null, Date=null, Round=null, White=null, Black=null, Result=null, WhiteElo="0", BlackElo="0", ECO=null;
		 StringBuilder Moves = new StringBuilder();
		 
		 while ((line = br.readLine()) != null) {
             if(line.length()>0){
                 if(line.contains("Event ")){
                     Event = line.substring(8,line.lastIndexOf("\""));
                 }
                 else if(line.contains("Site ")){
                     Site = line.substring(7,line.lastIndexOf("\""));
                 }
                 else if(line.contains("Date ")){
                     Date = line.substring(7,line.lastIndexOf("\""));
                 }
                 else if(line.contains("Round ")){
                     Round = line.substring(8,line.lastIndexOf("\""));
                 }
                 else if(line.contains("White ")){
                     White = line.substring(8,line.lastIndexOf("\""));
                     if(White.contains("\'")){
                         White.replace("\'","\'\'");
                     }
                 }
                 else if(line.contains("Black ")){
                     Black = line.substring(8,line.lastIndexOf("\""));
                     if(Black.contains("\'")){
                         Black.replace("\'","\'\'");
                     }
                 }
                 else if(line.contains("Result ")){
                     Result = line.substring(9,line.lastIndexOf("\""));
                 }
                 else if(line.contains("WhiteElo ")){
                     WhiteElo = line.substring(11,line.lastIndexOf("\""));
                 }
                 else if(line.contains("BlackElo ")){
                     BlackElo = line.substring(11,line.lastIndexOf("\""));
                 }
                 else if(line.contains("ECO ")){
                     ECO = line.substring(6,line.lastIndexOf("\""));
                 }
                 else if(line.contains("1.")){
					 if(gameCount<0){
                         while(line.length()>0){
                             line = br.readLine();
                         }
                         if(gameCount%10000==0) System.out.println(gameCount);
                     }else {

                         while (line.length() > 0) {
                             Moves.append(line);
                             line = br.readLine();
                         }

                         try {
                             PreparedStatement stmt = conn.prepareStatement("INSERT INTO `games`(`game_id`, `event`, `site`, `date`, `round`,`white`, `black`, `result`, `white_elo`, `black_elo`, `eco`, `moves`) "
                                     + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
                             stmt.setString(1, Integer.toString(gameCount));
                             stmt.setString(2, Event);
                             stmt.setString(3, Site);
                             stmt.setString(4, Date);
                             stmt.setString(5, Round);
                             stmt.setString(6, White);
                             stmt.setString(7, Black);
                             stmt.setString(8, Result);
                             stmt.setString(9, WhiteElo);
                             stmt.setString(10, BlackElo);
                             stmt.setString(11, ECO);
                             stmt.setString(12, Moves.toString());
                             stmt.executeUpdate();
                         } catch (Exception e) {
                             System.err.println("Error in trying to insert game: " + gameCount);
                         }

                         if (gameCount % 1000 == 0) {
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
