package Procedures;

import Connections.sqlOperations;
import java.sql.Connection;

public class transactionCountProcedure 
{
    public static void createProcedure()
    {
        sqlOperations op = new sqlOperations();

        //String database = "BANK";

        String structure = "BEGIN " +
                            "SELECT count into tc " +
                            "FROM transaction " +
                            "WHERE accountNo = accountNo1;" + 
                            "END;";

        String name = "transactionCount(OUT tc int, IN accountNo1 int)";

        Connection connection = op.createConnection();

        op.createProcedure(name, structure, connection);
    }
}
