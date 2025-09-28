package Views;

import java.sql.*;
import Connections.sqlOperations;

public class profileView 
{
    sqlOperations op;
    Connection connection;
    Statement statement;

    public profileView()
    {
        this.op = new sqlOperations();
        this.connection = op.createConnection();
        this.statement = null;
    }

    public void profile()
    {
        // String database = "bank";
        // String databaseQuery = "USE " + database;
        String viewQuery = "CREATE VIEW profileView as " +
                           "SELECT accountNo, name, dob, phoneNo, address from account;";
        
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
