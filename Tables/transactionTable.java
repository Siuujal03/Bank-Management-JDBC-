package Tables;

import Connections.sqlOperations;
import java.sql.Connection;

public class transactionTable 
{
    public static void createTable()
    {
        String database = "BANK";

        String structure = "(accountNo int PRIMARY KEY," +
                            "totalCount int DEFAULT 0," + 
                            "count int DEFAULT 5 check(count >= 0)," + 
                            "FOREIGN KEY (accountNo) REFERENCES account(accountNo))";
                            
        sqlOperations op = new sqlOperations();
        Connection connection = op.createConnection();

        op.createTable("transaction", structure, connection);
                            
    }
}
