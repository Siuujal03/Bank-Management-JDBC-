package Tables;

import Connections.sqlOperations;

public class accountTable 
{

    public static void createTable()
    {
        sqlOperations op = new sqlOperations();
        String structure = "(" +
            "accountNo int AUTO_INCREMENT primary key," +
            "password VARCHAR(300) not null," + 
            "name varchar(50)," + 
            "dob date not null," + 
            "phoneNo varchar(50) unique not null," + 
            "address varchar(50)" +
        ")AUTO_INCREMENT = 1000;";
        op.createTable("account", structure, op.createConnection());
    }
    // public static void main(String[] args) 
    // {
    //     sqlOperations op = new sqlOperations();
    //     String structure = "(" +
    //         "accountNo int primary key," +
    //         "accountPin varchar(50) check(length(accountPin) >= 4 and instr(accountPin, '@'))," + 
    //         "name varchar(50)," + 
    //         "age int check(age >= 18)," + 
    //         "phoneNo int unique not null," + 
    //         "address varchar(50)" +
    //     ");";
    //     op.createTable("bank", "account", structure, op.createConnection());
    // }
}
