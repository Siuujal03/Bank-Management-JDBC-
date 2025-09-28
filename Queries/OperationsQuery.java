package Queries;
import Configuration.SecurityConfig;
import Connections.sqlOperations;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class OperationsQuery 
{
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    AccountQuery aq;

    sqlOperations op;

    public OperationsQuery()
    {
        this.op = new sqlOperations();
        this.connection = op.createConnection();
        this.statement = null;
        this.preparedStatement = null;
        this.resultSet = null;
        this.aq = new AccountQuery();
    }

    public String selectQuery(int accountNo)
    {
        return "Select cash from records where accountNo = " + accountNo;
    }

    public int getCash(ResultSet resultSet)
    {
        int amount = 0;

        try
        {
            while(resultSet.next())
            {
                amount = resultSet.getInt("cash");
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return amount;
        
    }

    public boolean input(int accountNo)
    {
        Scanner sc = new Scanner(System.in);
        String menu = " Enter 1 for Depositing Money. \n Enter 2 for Withdrawing Money. \n Enter 3 for Transferring Money. \n" +
                      " Enter 4 for Viewing Transaction History. \n Enter 5 for Updating Bank Details. \n" +
                      " Enter 6 for Checking Profile Details. \n Enter 7 for Checking Bank Balance. \n Enter 8 for Logout.";

        System.out.println();
        System.out.println(menu);
        System.out.print("Enter transaction type : ");
        int type = sc.nextInt();
        sc.nextLine();
        //System.out.println();
        int cash = 0;
        int recipient = accountNo;
        // sqlOperations op = new sqlOperations();
        // connection = op.createConnection();

        TransactionViewQuery tq = new TransactionViewQuery();
        UpdateQuery uq = new UpdateQuery();
        if(type == 4)
        {
            tq.viewHistory(accountNo);
            //input(accountNo);
        }

        if(type == 5)
        {
            //tq.viewHistory(accountNo);
            uq.update(accountNo);
            //input(accountNo);
        }


        if(type == 1 || type == 2 || type == 3)
        {
            try
            {
                CallableStatement callableStatement = connection.prepareCall("{call transactionCount(?, ?)}");
                callableStatement.registerOutParameter(1, Types.INTEGER);
                callableStatement.setInt(2, accountNo);
                callableStatement.execute();
                int count = callableStatement.getInt(1);
                System.out.println(count);
                if(count == 0)
                {
                    System.out.println("Transaction Limit for the day reached. Try again tomorrow. ");
                    return true;
                    //throw new SQLException("");
                    //input(accountNo);
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }

            boolean choice = false;
            if(type == 1)
            {
                while(!choice)
                {
                    System.out.println();
                    System.out.print("Enter cash to be deposited : Rs.");
                    cash = sc.nextInt();
                    System.out.println();
                    System.out.println("Entered amount : " + cash);
                    System.out.println();
                    System.out.println("Proceed with next details?");
                    choice = aq.proceed();
                }
                
            }
            else if(type == 2)
            {
                // System.out.println();
                // System.out.print("Enter the amount to be withdrawn : Rs.");
                // cash = sc.nextInt();

                while(!choice)
                {
                    System.out.println();
                    System.out.print("Enter the amount to be withdrawn : Rs.");
                    cash = sc.nextInt();
                    System.out.println();
                    System.out.println("Entered amount : " + cash);
                    System.out.println();
                    System.out.println("Proceed with next details?");
                    choice = aq.proceed();
                }
                
            }
            else if(type == 3)
            {
                // System.out.println();
                // System.out.print("Enter the Account No of the recipient : ");
                // recipient = sc.nextInt();

                // System.out.println();
                // System.out.print("Enter the money to be transferred : ");
                // cash = sc.nextInt();

                while(!choice)
                {
                    System.out.println();
                    System.out.print("Enter the Account No of the recipient : ");
                    recipient = sc.nextInt();

                    if(accountNo == recipient)
                    {
                        System.out.println("Cannot transfer to the same bank account.");
                        continue;
                    }

                    System.out.println("Entered recipient : " + recipient);
                    System.out.println();
                    System.out.println("Proceed with next details?");
                    choice = aq.proceed();
                }

                choice = false;

                while(!choice)
                {
                    System.out.println();
                    System.out.print("Enter the money to be transferred : ");
                    cash = sc.nextInt();
                    System.out.println("Entered amount : " + cash);
                    System.out.println();
                    System.out.println("Proceed with next details?");
                    choice = aq.proceed();
                }
                    
                    // cash = sc.nextInt();
                    // System.out.println();
                    // System.out.println("Entered amount : " + cash);
                    // System.out.println();
                    // System.out.println("Proceed with next details?");
                    // choice = aq.proceed();
                
            }

            insert(accountNo, recipient, cash, type);
            
        }

        if(type == 6)
        {
            ProfileViewQuery.viewProfile(accountNo);
        }

        if(type == 7)
        {
            resultSet = op.selectQuery("SELECT bankBalance(" + accountNo + ") as balance;", connection);

            try
            {
                while(resultSet.next())
                {
                    System.out.println();
                    System.out.println("Bank Balance : Rs." + resultSet.getInt("balance"));
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }

        if(type == 8)
        {
            System.out.println("Logging out.");
            return false;
        }

        if(type > 8)
        {
            System.out.println("Invalid Option Selected.");
        }
        
        return true;
        //input(accountNo);
    }

    public void insert(int accountNo, int recipient, int cash, int type)
    {
        // sqlOperations op = new sqlOperations();
        // connection = op.createConnection();

        //String query = "Use bank;";

        String selectQuery = selectQuery(accountNo);

        resultSet = op.selectQuery(selectQuery, connection);

        int amount = getCash(resultSet);
        

        String insertQuery = "Insert into records(accountNo, cash) values(?, ?) as user " +
                            "on duplicate key update " + 
                            "cash = user.cash";
        try
        {
        
            String transactionType = "deposit";
            int amountSent = cash;
            int amountReceived = cash;

            switch (type) 
            {
                case 1 -> {
                    cash += amount;
                }
                case 2 -> 
                {
                    cash = amount - cash;
                    transactionType = "withdraw";
                }
                case 3 -> {
                    cash = amount - cash;
                    transactionType = "transfer";
                }
                default -> {
                }
            }

            if(cash < 1500)
            {
                System.out.println("Cannot perform the transaction. Minimum Balance Limit Violated.");
                return;
            }

            statement = connection.createStatement();
            //statement.executeUpdate(query);

            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, accountNo);
            preparedStatement.setInt(2, cash);
            preparedStatement.executeUpdate();

            if(accountNo != recipient)
            {
                preparedStatement.setInt(1, recipient);
                selectQuery = selectQuery(recipient);
                resultSet = op.selectQuery(selectQuery, connection);
                amount = getCash(resultSet);
                preparedStatement.setInt(2, amount + amountReceived);
                preparedStatement.executeUpdate();
            }

            updateTransactionHistory(accountNo, recipient, transactionType, amountSent, amountReceived);

        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void updateTransactionHistory(int account1, int account2, String transactionType, int amountSent, int amountReceived)
    {
        TransactionQuery tq = new TransactionQuery();
        tq.insert(account1, account2, transactionType, amountSent, amountReceived);
    }
}
