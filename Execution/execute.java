package Execution;
// import Queries.AccountQuery;
import Queries.AccountQuery;
import Queries.Login;
import Queries.OperationsQuery;
import Queries.TransactionQuery;
import Queries.TransactionViewQuery;
import java.util.*;

public class execute 
{
    public static void main(String[] args) 
    {
        AccountQuery aq = new AccountQuery();
        OperationsQuery oq = new OperationsQuery();
        TransactionQuery tq = new TransactionQuery();
        TransactionViewQuery vq = new TransactionViewQuery();

        int option = 0;
        String menu = " Enter 1 for creating an account \n Enter 2 for operations";

        Scanner sc = new Scanner(System.in);

        while(option != 3)
        {
            //boolean isLoggedIn = false;

            System.out.println();
            System.out.println(menu);
            System.out.print("Option : ");
            option = sc.nextInt();

            if(option == 1)
            {
                System.out.println();
                System.out.println("Enter the following details for creating your bank account.");
                aq.input();
                sc.nextLine();
                //isLoggedIn = false;
            }

            else if(option == 2)
            {
                System.out.println();
                System.out.println("Login to your bank account.");
                
                int accoutNo = Login.login();

                while(oq.input(accoutNo))
                {

                }
            }

            else if(option == 3)
            {

            }
            else
            {
                System.out.println("Invalid option selected.");
            }

        }

        
        // int accountNo = 102;

        // Scanner sc = new Scanner(System.in);
        
        // System.out.println(menu);

        // System.out.print("Option : ");
        // int option = sc.nextInt();
        // sc.nextLine();
        // System.out.println();

        // if(option == 1)
        // {
        //     aq.input();
        // }
        // else
        // {
        //     oq.input(accountNo);
        //     //vq.viewHistory("deposit", "all", accountNo);
        //     vq.viewHistory("transfer", "all", accountNo);
        //     System.out.println();
        //     vq.viewHistory("transfer", "sent", accountNo);
        //     System.out.println();
        //     vq.viewHistory("transfer", "received", accountNo);
        // }
        // int accountNo = aq.getAccountNo();
        
    }
}
