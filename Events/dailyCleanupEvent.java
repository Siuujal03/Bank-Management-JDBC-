package Events;

import Connections.sqlOperations;
import java.sql.Connection;

public class dailyCleanupEvent 
{
    public static void createEvent()
    {
        String structure = "ON SCHEDULE EVERY 1 DAY " +
                            " STARTS TIMESTAMP(CURRENT_DATE ) " +
                            "DO " +
                            "UPDATE transaction " +
                            "SET count = 5;";

        String name = "dailyCleanup";

        sqlOperations op = new sqlOperations();

        Connection connection = op.createConnection();

        op.createEvent(name, structure, connection);
    }
}
