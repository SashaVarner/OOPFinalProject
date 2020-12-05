package restaurantSystem.restaurantData;

public class State {

    private EmployeeAccounts context;

    public EmployeeAccounts getContext(){
        return context;
    }

    public void setContext(EmployeeAccounts newAccount){
        context=newAccount;
    }


    public State(EmployeeAccounts account) {
        setContext(account);
    }

    public State(State source) {
        setContext(source.getContext());
    }

    public static State InitialState(EmployeeAccounts account) {
        return new OffWorkState(account);
    }

    public void signIn(){

    }

    public void signOut(){

    }


}
