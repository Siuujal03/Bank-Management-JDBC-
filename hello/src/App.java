// import java.sql.*; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


public class App 
{
    public static void main(String[] args) throws Exception {
        
        try {
            String url = "jdbc:mysql://localhost:3306/data1";
            jdbc:mysql://127.0.0.1:3306/?user=root

            // String databaseName = "goat";
            String userName = "root";
            String password = "mubauman@3";
    
            Connection connection = DriverManager.getConnection(url,userName, password);
    
            String sql = "select name from GENERAL where roll = 41 ";
    
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            String name = rs.getString("name");
            System.out.println(name);
            statement.close();
            // JOptionPane.showMessageDialog(null, databaseName + " Database has been created successfully", "System Message", JOptionPane.INFORMATION_MESSAGE);
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
