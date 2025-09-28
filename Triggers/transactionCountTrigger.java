package Triggers;

import Connections.sqlOperations;
import java.sql.Connection;

public class transactionCountTrigger 
{
    public static void createTrigger()
    {
        //String database = "BANK";
        String structure = "AFTER INSERT ON transactionHistory " + 
                           "FOR EACH ROW " +
                           "BEGIN " +
                           "UPDATE transaction " +
                           "SET count = count - 1, totalCount = totalCount + 1 " +
                           "WHERE accountNo = NEW.accountNo1;" +
                           "END;";

        sqlOperations op = new sqlOperations();
        Connection connection = op.createConnection();
        
        op.createTrigger("transactionCountTrigger", structure, connection);
    }
}
