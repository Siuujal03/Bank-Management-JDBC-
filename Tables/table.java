package Tables;
import Connections.sqlOperations;
import java.sql.Connection;
public class table 
{
    public static void main(String arg[])   
    {
        sqlOperations op = new sqlOperations();
        Connection connection = op.createConnection();
        String structure = " (name VARCHAR(50), jerseyNo int PRIMARY KEY)";
        op.createTable( "stats", structure, connection);

    }
}
