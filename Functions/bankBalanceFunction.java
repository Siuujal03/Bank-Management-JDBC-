package Functions;

import java.sql.Connection;

import Connections.sqlOperations;

public class bankBalanceFunction 
{
    public static void createFunction()
    {
        sqlOperations op = new sqlOperations();

        //String database = "BANK";

        String structure = "RETURNS INT " +
                            "DETERMINISTIC " + 
                            "BEGIN " +
                            "DECLARE balance INT;" +
                            "SELECT cash into balance " +
                            "FROM records " +
                            "WHERE accountNo = accountNo1;" + 
                            "return balance; " + 
                            "END";

        String name = "bankBalance(accountNo1 INT)";

        Connection connection = op.createConnection();

        op.createFunction(name, structure, connection);
    }
}
