package Queries;

import Connections.sqlOperations;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionQuery 
{
    public void insert(int account1, int account2, String transactionType, int amountSent, int amountReceived)
    {
        sqlOperations op = new sqlOperations();
        Connection connection = op.createConnection();

        // String database = "BANK";
        // String databaseQuery = "USE " + database + ";"; 

        String transactionQuery = "INSERT into transactionHistory(accountNo1, accountNo2, transactionType, amountSent, amountReceived) VALUES(?, ?, ?, ?, ?)";

        try
        {
            Statement statement = connection.createStatement();
            //statement.executeUpdate(databaseQuery);

            PreparedStatement preparedStatement = connection.prepareStatement(transactionQuery);
            preparedStatement.setInt(1, account1);
            preparedStatement.setInt(2, account2);
            preparedStatement.setString(3, transactionType);
            preparedStatement.setInt(4, amountSent);
            preparedStatement.setInt(5, amountReceived);
            preparedStatement.executeUpdate();

            System.out.println("Transaction Table Updated");

            String selectQuery = "SELECT * from transactionHistory";
            ResultSet rs = op.selectQuery(selectQuery, connection);

            // while(rs.next())
            // {
            //     System.out.println(rs.getInt("accountNo1"));
            //     System.out.println(rs.getInt("accountNo2"));
            //     System.out.println(rs.getString("transactionType"));
            //     System.out.println(rs.getInt("amountSent"));
            //     System.out.println(rs.getInt("amountReceived"));
            // }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
