package restaurantSystem.commandInvokers;

import restaurantSystem.restaurantData.Aggregator;
import restaurantSystem.restaurantData.EmployeeAccounts;
import restaurantSystem.restaurantData.RestaurantTables;

public class SignInCommand implements RestaurantCommandInterface{
    EmployeeAccounts employeeAccount;

    public SignInCommand(String employeeId, String firstName, String lastName, Aggregator systemData)
    {
        this.employeeAccount=systemData.signIn(employeeId, firstName, lastName);
    }

    @Override
    public EmployeeAccounts execute() {
        employeeAccount.signIn();
        return this.employeeAccount;
    }
}
