package Queries;

import java.sql.*;
import java.util.Scanner;


import Configuration.SecurityConfig;
import Connections.sqlOperations;

public class Login 
{
    
    public static int login()
    {

        sqlOperations op = new sqlOperations();
        Connection connection = op.createConnection();
        ResultSet resultSet = null;
        AccountQuery aq = new AccountQuery();

        Scanner sc = new Scanner(System.in);
        int accountNo = 0;
        String password = "";
        boolean choice = false;

        while(!choice)
        {
            System.out.println();

            System.out.print("Enter the account number : ");
            accountNo = sc.nextInt();
            sc.nextLine();

            System.out.println("Entered account number : " + accountNo);
            System.out.println();

            choice = aq.proceed();
        }

        choice = false;

        while(!choice)
        {
            System.out.println();
            //System.out.println();
            System.out.print("Enter the password : ");
            password = sc.nextLine();
            System.out.println("Entered password : " + password);

            System.out.println();

            choice = aq.proceed();
        }
        //System.out.println();
        
        try
        {
            password = SecurityConfig.hashPassword(password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
        String query = "SELECT 1 FROM account " +
                       "WHERE accountNo = " + accountNo  + " and password = '" + password + "';";
        
        //connection = op.createConnection();

        resultSet = op.selectQuery(query, connection);

        try
        {
            if(!resultSet.next())
            {
                System.out.println("Invalid account number or password.");
                login();
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        System.out.println("Logged in Successfully.");
        System.out.println();
        return accountNo;
        // try
        // {
        //     PreparedStatement preparedStatement = connection.prepareStatement(query);
        //     preparedStatement.setInt(1, accountNo);
        //     preparedStatement.setString(2, password);
        // }
        // catch(SQLException e)
        // {
        //     e.printStackTrace();
        // }

        
    }
}
