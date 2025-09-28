package Queries;

import Connections.sqlOperations;
import java.sql.*;
import java.util.Scanner;


public class TransactionViewQuery 
{
    sqlOperations op;
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    AccountQuery aq;

    public TransactionViewQuery()
    {
        this.op = new sqlOperations();
        this.connection = op.createConnection();
        this.aq = new AccountQuery();

    }

    public Filter filter()
    {
        int choice = 1;
        String startDate = "";
        String endDate = "";

        Scanner sc = new Scanner(System.in);
        System.out.println();

        String menu = " Enter 1 for Viewing Full Transaction History. \n" + 
                      " Enter 2 for Before a Particular Date. \n" + 
                      " Enter 3 for After a Particular Date. \n" + 
                      " Enter 4 for Range Based. \n" + 
                      " Enter 5 for a Particular Date.";

        System.out.println(menu);

        System.out.print("Choice : ");

        choice = sc.nextInt();
        sc.nextLine();

        boolean flag = false;
        switch(choice)
        {
            case 2 -> {
                // System.out.println();
                // System.out.print("\n Enter the date : ");
                // endDate = sc.nextLine();

                
                while(!flag)
                {
                    System.out.println();
                    System.out.print("\n Enter the date : ");
                    endDate = sc.nextLine();
                    System.out.println();

                    System.out.println("Entered Date : " + endDate);
                    System.out.println();
                    System.out.println("Proceed with next details?");
                    flag = aq.proceed();
                }

            }
            case 3 -> {
                // System.out.println();
                // System.out.print("\n Enter the date : ");
                // startDate = sc.nextLine();

                while(!flag)
                {
                    System.out.println();
                    System.out.print("\n Enter the date : ");
                    startDate = sc.nextLine();
                    System.out.println();

                    System.out.println("Entered Date : " + startDate);
                    System.out.println();
                    System.out.println("Proceed with next details?");
                    flag = aq.proceed();
                }
            }
            case 4 -> {
                // System.out.print("\n Enter the start date : ");
                // startDate = sc.nextLine();
                // System.out.println();
                // System.out.print("\n Enter the end date : ");
                // endDate = sc.nextLine();

                while(!flag)
                {
                    System.out.println();
                    System.out.print("\n Enter the start date : ");
                    startDate = sc.nextLine();
                    System.out.println();
                    System.out.println("Entered Date : " + startDate);
                    System.out.println();
                    System.out.println("Proceed with next details?");
                    flag = aq.proceed();
                }

                flag = false;

                while(!flag)
                {
                    System.out.println();
                    System.out.print("\n Enter the end date : ");
                    endDate = sc.nextLine();
                    System.out.println();
                    System.out.println("Entered Date : " + endDate);
                    System.out.println();
                    System.out.println("Proceed with next details?");
                    flag = aq.proceed();
                }


            }
            case 5 -> 
            {
                while(!flag)
                {
                    System.out.println();
                    System.out.print("\n Enter the start date : ");
                    startDate = sc.nextLine();
                    System.out.println();
                    System.out.println("Entered Date : " + startDate);
                    System.out.println();
                    System.out.println("Proceed with next details?");
                    flag = aq.proceed();
                }
                // System.out.print("\n Enter the date : ");
                // startDate = sc.nextLine();

            }
        }

        return new Filter(choice, startDate, endDate);

    }
    public ResultSet fetchHistory(int type, int accountNo)
    {
            String query = "SELECT * from ";
            //String database = "BANK";

            Filter filter = filter();

            switch(type)
            {
                case 1 -> 
                {
                    //System.out.println("yes");
                    query += "fullView WHERE (accountNo1 = " + accountNo + " or accountNo2 = " + accountNo + ")";
                }

                case 2 -> 
                {
                    query += "depositView WHERE (accountNo1 = " + accountNo + ")";
                }

                case 3 -> 
                {
                    query += "withdrawView WHERE (accountNo1 = " + accountNo + ")";
                }

                case 4 -> 
                {
                    int subType = 0;

                    Scanner sc = new Scanner(System.in);
                    

                    System.out.println();
                    System.out.println(" Enter 1 for Viewing Full Transfer History.\n Enter 2 for Viewing Money Sent History.\n Enter 3 for Viewing Money Received History.");
                    System.out.print("Choice : ");

                    subType = sc.nextInt();

                    switch(subType)
                    {
                        case 1 -> query += "transferView WHERE (Sender = " + accountNo + " or Receiver = " + accountNo + ")";
                        case 2 -> query += "transferView WHERE (Sender = " + accountNo + ")";
                        case 3 ->  query += "transferView WHERE (Receiver = " + accountNo + ")";
                        
                    }
                }

                default -> resultSet = null;
            }

            int choice = filter.choice;
            String startDate = filter.startDate;
            String endDate = filter.endDate;

            if(choice == 2)
            {
                System.out.println("Before " + endDate);
                query += " and DATE(time) < '" + endDate + "'";
            }
            else if(choice == 3)
            {
                System.out.println("After " + startDate);
                query += " and DATE(time) > '" + startDate + "'";
            }
            else if(choice == 4)
            {
                System.out.println("Between " + startDate + " and " + endDate);
                query += " and DATE(time) BETWEEN '" + startDate + "' and '" + endDate + "' ";
            }
            else if(choice == 5)
            {
                System.out.println("Of " + startDate);
                query += " and DATE(time) = '" + startDate + "'";
            }

            System.out.println("Query : " + query);

            return resultSet = op.selectQuery(query, connection);

    }

    public void viewHistory(int accountNo)
    {
        Scanner sc = new Scanner(System.in);

        int type = 0;
        
        String menu = " Enter 1 for Viewing Full History.\n Enter 2 for Viewing Deposit History.\n" +
                      " Enter 3 for Viewing Withdraw History.\n Enter 4 for Viewing Transfer History.";
        
        System.out.println();
        System.out.println(menu);

        System.out.print("Choice : ");
        type = sc.nextInt();

        System.out.println();
        System.out.print("Transaction History ");

        resultSet = fetchHistory(type, accountNo);

        try
        {
            System.out.println();
           
            switch(type)
            {
                case 1 -> 
                {
                    System.out.println("All Time History.");
                    System.out.print("Transaction Type   ");
                    System.out.print("Sender   ");
                    System.out.print("Receiver    ");
                    System.out.print("Amount       ");
                    System.out.println("Time");
                    while(resultSet.next())
                    {
                        System.out.println();
                        String transactionType = resultSet.getString("transactionType");
                        int account = resultSet.getInt("accountNo2");
                        System.out.print(transactionType);
                        System.out.print("           " + resultSet.getInt("accountNo1"));
                        System.out.print("        " + resultSet.getInt("accountNo2"));

                        System.out.print("    Rs." + resultSet.getInt("amountSent"));
                        System.out.println("     " + resultSet.getTimestamp("time"));
                    }
                }

                case 2 -> 
                {
                    System.out.println("Deposit History.");
                    System.out.print("Amount Deposited ");
                    System.out.println(" Date");
                    
                    while(resultSet.next())
                    {
                        System.out.print(" Rs." +resultSet.getInt("Amount_Deposited"));
                        System.out.println("     " + resultSet.getTimestamp("time"));
                    }
                }

                case 3 -> 
                {
                    System.out.println("Withdraw History.");
                    System.out.print("Amount Withdrawn ");
                    System.out.println(" Date");
                    
                    while(resultSet.next())
                    {
                        System.out.print("  Rs." +resultSet.getInt("Amount_Deposited"));
                        System.out.println("            " + resultSet.getTimestamp("time"));
                    }
                }

                case 4 ->  
                {
                    System.out.println("Transfer History.");
                   System.out.print("Sender ");
                   System.out.print("Receiver ");
                   System.out.print("Amount ");
                   System.out.println("Time ");

                   while(resultSet.next())
                   {
                        System.out.print(resultSet.getInt("Sender"));
                        System.out.print("     " + resultSet.getInt("Receiver"));
                        System.out.print("      Rs." + resultSet.getInt("amount"));
                        System.out.println("     " + resultSet.getTimestamp("time"));
                        //System.out.print(resultSet.getInt("accountNo1"));
                   }

                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
    }
}

class Filter
{
    int choice;
    String startDate;
    String endDate;

    public Filter(int choice, String startDate, String endDate)
    {
        this.choice = choice;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

