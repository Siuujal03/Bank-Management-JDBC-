package Views;

public class createViews 
{
    public static void main(String[] args) 
    {
        transactionViews views = new transactionViews();
        views.fullView();
        views.depositWithdrawView("deposit");
        views.depositWithdrawView("withdraw");
        views.transferView();

        new profileView().profile();

        System.out.println("Views Created Successfully");
    }
}
