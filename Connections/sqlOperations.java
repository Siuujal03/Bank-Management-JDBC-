package Connections;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlOperations
{
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public sqlOperations()
    {
        this.connection = null;
        this.statement = null;
        this.preparedStatement = null;
        this.resultSet = null;
    }

    public Connection createConnection()
    {
        String url = "jdbc:mysql://127.0.0.1:3306/bank";
        String username = "root";
        String password = "mubauman@3";
        // Connection connection = null;
        try
        {
            connection = DriverManager.getConnection(url, username, password);
        }
        catch(SQLException e)
        {
            System.out.print("Exception : ");
            e.printStackTrace();
            System.out.println();
        }
        return connection;
    }

    public void createDatabase(String database, Connection connection)
    {
        // Connection connection = createConnection();
        // Statement statement = null;
        try
        {
            statement = connection.createStatement();
            String databaseQuery = "CREATE DATABASE IF NOT EXISTS " + database;
            statement.executeUpdate(databaseQuery);
            System.out.println("DATABASE CREATED SUCCESSFULLY");
        }
        catch(SQLException e)
        {
            System.out.print("Exception : ");
            e.printStackTrace();
            System.out.println();
        }
        
    }

    public void createTable(String table, String structure, Connection connection)
    {
        // Statement statement = null;
        try
        {
            statement = connection.createStatement();
            //String useQuery = "USE " + database;
            //statement.executeUpdate(useQuery);

            String tableQuery = "CREATE TABLE " + table + structure;
            statement.executeUpdate(tableQuery);

            System.out.println("TABLE CREATED SUCCESSFULLY");


        }
        catch(SQLException e)
        {
            System.out.print("Exception : ");
            e.printStackTrace();
            System.out.println();
        }
    }

    public ResultSet selectQuery(String query, Connection connection)
    {
        // String database = "bank";
       // String databaseQuery = "USE " + database + ";";
        try
        {
            statement = connection.createStatement();
            //statement.executeUpdate(databaseQuery);
             resultSet = statement.executeQuery(query);
            // preparedStatement = connection.prepareStatement(query);
            // resultSet = preparedStatement.executeQuery();
            return resultSet;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return null;

        }
    }

    public void createTrigger(String trigger, String structure, Connection connection)
    {
        //String databaseQuery = "USE " + database + ";";

        String triggerQuery = "CREATE TRIGGER " + trigger + " " + structure;

        try
        {
            statement = connection.createStatement();
            //statement.executeUpdate(databaseQuery);
            statement.execute(triggerQuery);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void createProcedure(String procedure, String structure, Connection connection)
    {
        //String databaseQuery = "USE " + database + ";";
        String procedureQuery = "CREATE PROCEDURE " + procedure + " " + structure ;

        try
        {
            statement = connection.createStatement();
            //statement.executeUpdate(databaseQuery);
            statement.execute(procedureQuery);

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void createEvent(String event, String structure, Connection connection)
    {
        //String databaseQuery = "USE BANK;";
        String eventQuery = "CREATE EVENT " + event + " " + structure;

        try
        {
            statement = connection.createStatement();
            //statement.executeUpdate(databaseQuery);
            statement.execute(eventQuery);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void createFunction(String function, String structure, Connection connection)
    {
        String functionQuery = "CREATE FUNCTION " + function +  " " + structure;

        try
        {
            statement = connection.createStatement();
            //statement.executeUpdate(databaseQuery);
            statement.execute(functionQuery);

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    // public void insertQueries(Connection connection, String query, String database)
    // {
    //     try
    //     {
    //         statement = connection.createStatement();
    //         statement.executeUpdate("Use " + database);
    //         preparedStatement = connection.prepareStatement("insert into stats values (?,?)");
    //         preparedStatement.setString(1, "Siuuujal");
    //         preparedStatement.setInt(2, 3);
    //         preparedStatement.executeUpdate();
    //         System.out.println("VALUES INSERTED SUCCESSFULLY");
    //     }
    //     catch(SQLException e)
    //     {
    //         e.printStackTrace();
    //     }
    // }

    
}
