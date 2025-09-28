package Databases;
import Connections.sqlOperations;
import java.sql.Connection;
// import Tables.practice;
public class database 
{
    public static void main(String arg[])
    {
        sqlOperations op = new sqlOperations();
        Connection connection = op.createConnection();
        op.createDatabase("bank", connection);
    }
}
