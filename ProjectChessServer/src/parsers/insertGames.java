import java.io.BufferedReader;
import java.lang.ClassNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;


public class insertGames {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new FileReader("Z:\\chessdatabase\\millionbase-2.22.pgn"));

        String line=null, Name=null;
        Set<String> values = new HashSet<String>();

        while ((line = br.readLine()) != null) {
            if(line.contains("White ")||line.contains("Black ")) {
                Name = line.substring(8, line.lastIndexOf("\""));
                if (Name.contains("\'")) {
                    Name.replace("\'", "\'\'");
                }
                values.add(Name);
            }
        }
        br.close();

        //SQL CONNECTION
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost/test";
        Connection conn = DriverManager.getConnection(url,"root","");
        conn.setAutoCommit(false);

        int counter = 0;
        for(String player : values){
            try {
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO `players` (`id`, `name`, `preferred_eco`) VALUES (?,?,?)");
                stmt.setString(1, Integer.toString(counter));
                stmt.setString(2, player);
                stmt.setString(3, "0");
                stmt.executeUpdate();
                if (counter % 1000 == 0) {
                    conn.commit();
                    System.out.println(counter);
                }
                if (counter % 100000 == 0) {
                    conn.close();
                    conn = DriverManager.getConnection(url, "root", "");
                    conn.setAutoCommit(false);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            counter++;
        }

        conn.close();

        System.out.println("JOB DONE!");
    }
}
