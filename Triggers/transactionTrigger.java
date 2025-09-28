package Triggers;

import Connections.sqlOperations;
import java.sql.Connection;

public class transactionTrigger 
{
    public static void createTrigger()
    {
        String database = "BANK";
        String structure = "AFTER INSERT ON account " + 
                           "FOR EACH ROW " +
                           "BEGIN " +
                           "INSERT INTO transaction(accountNo) VALUES(NEW.accountNo);" +
                           "END;";

        sqlOperations op = new sqlOperations();
        Connection connection = op.createConnection();
        

        op.createTrigger("transactionTrigger", structure, connection);
    }
}
