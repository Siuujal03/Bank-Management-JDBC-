package Queries;

import Configuration.SecurityConfig;
import Connections.sqlOperations;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
public class AccountQuery 
{
    int no;

    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    sqlOperations op;
    public AccountQuery()
    {
        this.op = new sqlOperations();
        this.connection = op.createConnection();
        this.statement = null;
        this.preparedStatement = null;
        this.resultSet = null;
        this.no = 0;
    }

    public boolean proceed()
    {
        System.out.println(" Enter Y for Yes. \n Enter N for No.");
        
        System.out.print("Choice : ");
        String choice = new Scanner(System.in).nextLine();

        if(!choice.equals("Y") && !choice.equals("N")
        && !choice.equals("y") && !choice.equals("n"))
        {
            System.out.println("Invalid option entered. Enter the correct choice.");
            return proceed();
        }

        return choice.equals("Y") || choice.equals("y");
    }

    public static int generateAccountNo()
    {
        String accountNoQuery = "Select accountNo from account order by DESC Limit 1";
        
        return 0;
    }

    public java.sql.Date convertDate(String dob) 
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try
        {
            java.util.Date date = sdf.parse(dob);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            return sqlDate;
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
        
    }

    public boolean ageCheck(String date)
    {
        DateTimeFormatter dtm = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dob = LocalDate.parse(date, dtm);
        LocalDate curDate = LocalDate.now();

        Period diff = Period.between(dob, curDate);

        return diff.getYears() >= 18; 
        
    }

    public void input()
    {
        Scanner sc = new Scanner(System.in);
        // int accountNo = generateAccountNo();

        boolean choice = false;

        String name = "", password = "", confirmPassword = "", phoneNo = "", address = "", dob = "";

        while(!choice)
        {
            System.out.println();
            System.out.print("Enter the name of the applicant : ");
            name = sc.nextLine();

            System.out.println("Entered name : " + name);
            System.out.println();
            
            System.out.println("Proceed with next details?");
            choice = proceed();
        }

        choice = false;
        

        while(!choice && password.length() < 8)
        {
            System.out.println();
            System.out.print("Enter the account password of the applicant (length must be >= 8) : ");
            password = sc.nextLine();
            
            if(password.length() < 8)
            {
                System.out.println("Password Length should be >= 8");
                continue;
            }
            
            System.out.println("Entered password : " + password);
            System.out.println();
            System.out.println("Proceed with next details?");
            choice = proceed();
        }
        
        

        while(!password.equals(confirmPassword))
        {
            System.out.println();
            System.out.print("Confirm Password : ");
            confirmPassword = sc.nextLine();

            if(!confirmPassword.equals(password))
            {
                System.out.println("Entered password does not match. Try again");
            }
        }

        choice = false;
        

        while(!choice)
        {
            System.out.println();
            System.out.print("Enter the dob : ");
            dob = sc.nextLine();
            
            if(!ageCheck(dob))
            {
                System.out.println("Applicant must be atleast 18 years old to register for a bank account");
                continue;
            }

            System.out.println("Entered dob : " + dob);
            System.out.println();
            System.out.println("Proceed with next details?");
            choice = proceed();
        }
        
        //sc.nextLine();
        choice = false;
       

        while(!choice)
        {
            System.out.println();
            System.out.print("Enter the phone number : ");
            phoneNo = sc.nextLine();
            
            System.out.println("Entered phone number : " + phoneNo);
            System.out.println();
            System.out.println("Proceed with next details?");
            choice = proceed();
        }
        
       // sc.nextLine();
        choice = false;
        

        while(!choice)
        {
            System.out.println();
            System.out.print("Enter the address : ");
            address = sc.nextLine();
            
            System.out.println("Entered address : " + address);
            System.out.println();
            System.out.println("Proceed with next details?");
            choice = proceed();
        }
        
        choice = false;
        
        int amount = 0;

        while(!choice)
        {
            System.out.println();
            System.out.print("Enter the initial amount to be deposited (min : Rs.1500) : Rs.");
            amount = sc.nextInt();

            if(amount < 1500)
            {
                System.out.println("Does not satisfy min balance criteria.");
                continue;
            }

            System.out.println("Entered amount : " + amount);
            System.out.println();
            System.out.println("Proceed with next details?");
            choice = proceed();
        }

        //sc.close();
        insert(name, dob, phoneNo, address, password, amount);
    }

    public void insert(String name, String dob, String phoneNo, String address, String password, int amount)
    {
        // sqlOperations op = new sqlOperations();
        // connection = op.createConnection();
        //String database = "bank";
        // String accountNoQuery = "Select accountNo from account order by accountNo DESC Limit 1;";
        // int accountNo = 100;

       // String password = "";
        
        try
        {
            password = SecurityConfig.hashPassword(password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        // try
        // {
        //     System.out.println("Inside the try block");
        //     statement = connection.createStatement();
        //     statement.executeUpdate(database);
        //     preparedStatement = connection.prepareStatement(accountNoQuery);
        //     resultSet = preparedStatement.executeQuery();
        //     while(resultSet.next())
        //     {
        //         accountNo = resultSet.getInt("accountNo");
        //         accountNo++;
        //     }
        // }

        // catch(SQLException e)
        // {
        //     // System.out.println(e.printStackTrace());
        //     e.printStackTrace();
        // }
        // try
        // {
        //     resultSet =  op.selectQuery(accountNoQuery, connection);
        //     while(resultSet.next())
        //     {
        //         accountNo = resultSet.getInt("accountNo");
        //         accountNo++;
        //     }
        // }
        // catch(SQLException e)
        // {
        //     e.printStackTrace();
        // }
        

        //System.out.println(accountNo);
        String query = "INSERT INTO ACCOUNT(password, name, dob, phoneNo, address) VALUES\n" + //
                        "(?,?,?,?,?);";
        try
        {
            statement = connection.createStatement();
            //statement.executeUpdate("Use Bank");
            preparedStatement = connection.prepareStatement(query);
            //preparedStatement.setInt(1, accountNo);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, name);
            preparedStatement.setDate(3, convertDate(dob));
            preparedStatement.setString(4, phoneNo);
            preparedStatement.setString(5, address);
            preparedStatement.executeUpdate();

            resultSet = op.selectQuery("SELECT accountNo from account order by accountNo DESC LIMIT 1;", connection);
            int accountNo = 0;
            while(resultSet.next())
            {
                accountNo = resultSet.getInt("accountNo");
                System.out.println("The account number of your account : " + accountNo);
            }
            //no = accountNo;

            String insertQuery = "Insert into records(accountNo, cash) values(?, ?) as user " +
                            "on duplicate key update " + 
                            "cash = user.cash";

            statement = connection.createStatement();
            //statement.executeUpdate(query);

            preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, accountNo);
            preparedStatement.setInt(2, amount);
            preparedStatement.executeUpdate();

            System.out.println("Account created successfully");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            
        }
       
    }

    public int getAccountNo()
    {
        return no;
    }

}
