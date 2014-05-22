import java.io.IOException;
import java.sql.*;

/**
 * Created by zyixc on 19-5-2014.
 */
public class replacePlayersNameId {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, NumberFormatException, IOException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost/test";
        Connection conn = DriverManager.getConnection(url, "root", "");

        for(int i = 1; i < 368470; i++){
            Statement stmt = conn.createStatement();
            try{
                ResultSet rs = stmt.executeQuery("SELECT name FROM players WHERE id == "+ i);
                stmt.executeUpdate("UPDATE games SET white = '" + i + "' WHERE white = " + rs.getString(1));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
