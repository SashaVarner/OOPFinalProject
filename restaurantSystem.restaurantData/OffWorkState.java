package restaurantSystem.restaurantData;

public class OffWorkState extends State{
    public OffWorkState (EmployeeAccounts account) {
        super(account);
    }

    public OffWorkState(State source){
        super(source);
    }

    private void transitionState() {
        getContext().setState(new AtWorkState(this));
    }

    @Override
    public void signIn(){
        System.out.println("Now signing in...");
        transitionState();
    }

    @Override
    public void signOut(){
        System.out.println("Currently signed out");
    }

}
