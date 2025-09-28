package Views;

import Connections.sqlOperations;
import java.sql.*;

public class transactionViews 
{
    // sqlOperations op = new sqlOperations();
    // Connection connection = op.createConnection();
    // Statement statement = connection.createStatement();

    sqlOperations op;
    Connection connection;
    Statement statement;

    public transactionViews()
    {
        this.op = new sqlOperations();
        this.connection = op.createConnection();
        this.statement = null;
    }

    public void fullView()
    {
        // String database = "bank";
        // String databaseQuery = "USE " + database;
        String viewQuery = "CREATE VIEW fullView as " +
                           "SELECT * from transactionHistory;";
        
        try
        {
            statement = connection.createStatement();
            //statement.executeUpdate(databaseQuery);
            statement.executeUpdate(viewQuery);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void depositWithdrawView(String transactionType)
    {
        // String database = "bank";
        // String databaseQuery = "USE " + database;

        String viewQuery = "CREATE VIEW " + transactionType + "View as " +
                            "SELECT accountNo1, amountSent as Amount_Deposited, time from transactionHistory " +
                            "WHERE transactionType = '" + transactionType + "'";

        try
        {
            statement = connection.createStatement();
            //statement.executeUpdate(databaseQuery);
            statement.executeUpdate(viewQuery);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void transferView()
    {
        // String database = "bank";
        // String databaseQuery = "USE " + database;
        String viewQuery = "CREATE VIEW transferView as " +
                           "SELECT accountNo1 as Sender, accountNo2 as Receiver, amountSent as amount, time from transactionHistory " +
                           "WHERE transactionType = 'transfer'";
        
        try
        {
            statement = connection.createStatement();
            //statement.executeUpdate(databaseQuery);
            statement.executeUpdate(viewQuery);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
 