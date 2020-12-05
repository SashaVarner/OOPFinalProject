package restaurantSystem.restaurantData;

public class EmployeeAccounts {
    private String employeeNumber;
    private String firstName;
    private String lastName;
    private State objState;

    public void setState(State newState) {
        objState = newState;
    }

    public State getState() {
        return objState;
    }

    public String getEmployeeInformation() {
        return "Employee ID: " + employeeNumber + ". First name: " + firstName + ". Last Name: ";
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public EmployeeAccounts(){
        objState = State.InitialState(this);
    }

    public EmployeeAccounts(String employeeNumber, String firstName, String lastName){
        this.employeeNumber=employeeNumber;
        this.firstName=firstName;
        this.lastName=lastName;
        objState = State.InitialState(this);
    }

    public void signIn(){
        objState.signIn();
    }

    public void signOut(){
        objState.signOut();
    }


}
