package Queries;

import Connections.sqlOperations;
import java.sql.*;

public class ProfileViewQuery
{
    public static void viewProfile(int accountNo)
    {
        String query = "SELECT * from ProfileView WHERE accountNo = " + accountNo + ";";

        sqlOperations op = new sqlOperations();
        Connection connection = op.createConnection();

        ResultSet resultSet = op.selectQuery(query, connection);

        try
        {
            while(resultSet.next())
            {
                System.out.println();
                System.out.println("Profile ");
                System.out.println("Account Number : " + resultSet.getInt("accountNo"));
                System.out.println("Name : " + resultSet.getString("name"));
                System.out.println("DoB : " + resultSet.getString("dob"));
                System.out.println("Phone Number : " + resultSet.getString("phoneNo"));
                System.out.println("Address : " + resultSet.getString("address"));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        

    
    }
}
