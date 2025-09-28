package Tables;

public class createTables 
{
    public static void main(String[] args) 
    {
        accountTable.createTable();
        accountOperations.createTable();
        transactionHistoryTable.createTable();
        transactionTable.createTable();

        System.out.println("Tables Created Successfully");

    }
}
