package Queries;

import Connections.sqlOperations;
import java.sql.*;
import java.util.Scanner;

import Configuration.SecurityConfig;

public class UpdateQuery 
{
    public void update(int accountNo)
    {
        sqlOperations op = new sqlOperations();
        Connection connection = op.createConnection();

        Scanner sc = new Scanner(System.in);
        AccountQuery aq = new AccountQuery();

        int option = 0;

        String menu = " Enter 1 for Updating Name.\n Enter 2 for Updating Password.\n Enter 3 for Updating DoB.\n" +
                      " Enter 4 for Updating Phone Number.\n Enter 5 for Updating Address.";

        System.out.println();
        System.out.println(menu);

        System.out.print("Choice : ");
        option = sc.nextInt();
        sc.nextLine();

        String parameter = "";

        boolean choice = false;

        
        if(option == 1)
        {
            while(!choice)
            {
                System.out.println();
                System.out.print("Enter Name : ");
                parameter = sc.nextLine();

                System.out.println();
                System.out.println("Entered name : " + parameter);
                System.out.println();
                
                System.out.println("Confirmed?");
                choice = aq.proceed();
            }
            
        }

        if(option == 2)
        {
            while(!choice && parameter.length() < 8)
            {
                System.out.println();
                System.out.print("Enter Password : ");
                parameter = sc.nextLine();

                if(parameter.length() < 8)
                {
                    System.out.println("Password Length should be >= 8");
                    continue;
                }
                
                System.out.println();
                System.out.println("Entered password : " + parameter);
                System.out.println();

                System.out.println("Confirmed?");
                choice = aq.proceed();
            }

            String confirmPassword = "";
            while(!parameter.equals(confirmPassword))
            {
                System.out.println();
                System.out.print("Confirm Password : ");
                confirmPassword = sc.nextLine();

                if(!confirmPassword.equals(parameter))
                {
                    System.out.println("Entered password does not match. Try again");
                }
            }

            try
            {
                parameter = SecurityConfig.hashPassword(parameter);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            
            
        }

        if(option == 3)
        {
            while(!choice)
            {
                System.out.println();
                System.out.print("Enter DoB : ");
                parameter = sc.nextLine();
                
                if(!aq.ageCheck(parameter))
                {
                    System.out.println("Applicant must be atleast 18 years old to register for a bank account");
                    continue;
                }

                System.out.println();
                System.out.println("Entered DoB : " + parameter);
                System.out.println();
                
                System.out.println("Confirmed?");
                choice = aq.proceed();
            }
            
        }

        if(option == 4)
        {
            while(!choice)
            {
                System.out.println();
                System.out.print("Enter Phone Number : ");
                parameter = sc.nextLine();

                System.out.println();
                System.out.println("Entered Phone Number : " + parameter);
                System.out.println();
                
                System.out.println("Confirmed?");
                choice = aq.proceed();
            }
            
        }

        if(option == 5)
        {
            while(!choice)
            {
                System.out.println();
                System.out.println("Enter Address : ");
                parameter = sc.nextLine();

                System.out.println("Entered address : " + parameter);
                System.out.println();
                
                System.out.println("Confirmed?");
                choice = aq.proceed();
            }
            
        }

        String updateQuery = "UPDATE account";

        // String name = "";
        // String password = "";
        // String dob = "";
        // String address = "";

        // String phoneNo = "";

       
        
        switch(option)
        {
            case 1 -> {updateQuery += " SET name = ? " + 
                                     "WHERE accountNo = ?;";
                     }
            
            case 2 -> {updateQuery += " SET password = ? " + 
            "WHERE accountNo = ? ;";
            }

            case 3 -> {updateQuery += " SET dob = ? " + 
                     "WHERE accountNo = ? ;";
                     }
            
            case 4 -> {updateQuery += " SET phoneNo = ? " + 
                    "WHERE accountNo = ? ;";
                    }

            case 5 -> {updateQuery += " SET address = ? " + 
                                     "WHERE accountNo = ? ;";
                     }
        }

       // System.out.println(updateQuery);
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            
            if(option == 3)
            {
                preparedStatement.setDate(1, aq.convertDate(parameter));
            }
            else
            {
                preparedStatement.setString(1, parameter);
            }
            
            preparedStatement.setInt(2, accountNo);

            preparedStatement.executeUpdate();

            System.out.println("Details updated successfully");
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
    }


    public static void main(String[] args) {
        
    }
}
