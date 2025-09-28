package Tables;

import Connections.sqlOperations;

public class transactionHistoryTable 
{
    public static void createTable()
    {
        sqlOperations op = new sqlOperations();
        String structure = "(accountNo1 int," + //
                        "accountNo2 int ," + //
                        "transactionType varchar(50)," + //
                        "amountSent int," +
                        "amountReceived int," +
                        "time TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                        "foreign key (accountNo1) references account(accountNo)," + //
                        "foreign key (accountNo2) references account(accountNo)," + //
                        "check(transactionType = \"deposit\" or transactionType = \"withdraw\" or accountNo1 != accountNo2)" + //
                        ");";
        op.createTable("transactionHistory", structure, op.createConnection());
    }
    // public static void main(String arg[])
    // {
    //     sqlOperations op = new sqlOperations();
    //     String structure = "(accountNo1 int," + //
    //                     "accountNo2 int ," + //
    //                     "transactionType varchar(50)," + //
    //                     "amountSent int," +
    //                     "amountReceived int," +
    //                     "foreign key (accountNo1) references account(accountNo)," + //
    //                     "foreign key (accountNo2) references account(accountNo)," + //
    //                     "check(transactionType = \"deposit\" or accountNo1 != accountNo2)" + //
    //                     ");";
    //     op.createTable("bank", "transactionHistory", structure, op.createConnection());
    // }
}
