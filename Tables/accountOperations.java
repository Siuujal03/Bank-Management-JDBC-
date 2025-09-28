package Tables;

import Connections.sqlOperations;

public class accountOperations 
{

    public static void createTable()
    {
        sqlOperations op = new sqlOperations();
        String tableName = "Records";
        String structure = "(accountNo int primary key," +
                           "cash int check(cash >= 1500)," +
                           "Foreign Key (accountNo) references account(accountNo));";
        op.createTable(tableName, structure, op.createConnection());
    }
    // public static void main(String[] args) 
    // {
    //     sqlOperations op = new sqlOperations();
    //     String tableName = "Records";
    //     String structure = "(accountNo int primary key," +
    //                        "cash int check(cash >= 1500)," +
    //                        "Foreign Key (accountNo) references account(accountNo));";
    //     op.createTable("bank", tableName, structure, op.createConnection());
        
    // }
}
