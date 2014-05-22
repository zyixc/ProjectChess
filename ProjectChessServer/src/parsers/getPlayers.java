import java.io.*;
import java.sql.*;

/**
 * Created by zyixc on 19-5-2014.
 */
public class getPlayers {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, NumberFormatException, IOException {
        //SQL CONNECTION
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost/test";
        Connection conn = DriverManager.getConnection(url, "root", "");

        Statement stmt = conn.createStatement() ;
        String query = "SELECT *" +
                       "FROM games " +
                       "WHERE white OR black LIKE 'Aagaard%'";
        ResultSet rs = stmt.executeQuery(query) ;

        while(rs.next()) {
            System.out.println(rs.getInt(1)+" "+rs.getString(6)+" "+rs.getString(7));
        }
    }
}
