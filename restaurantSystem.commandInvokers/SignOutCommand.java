package restaurantSystem.commandInvokers;

import restaurantSystem.restaurantData.Aggregator;
import restaurantSystem.restaurantData.EmployeeAccounts;

public class SignOutCommand implements RestaurantCommandInterface{
    EmployeeAccounts employeeAccount;
    public SignOutCommand(String employeeId, String firstName, String lastName,Aggregator systemData)
    {
        this.employeeAccount=systemData.signOut(employeeId, firstName, lastName);
    }


    @Override
    public EmployeeAccounts execute() {
        employeeAccount.signOut();
        return this.employeeAccount;
    }}
