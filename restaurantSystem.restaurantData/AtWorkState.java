package restaurantSystem.restaurantData;

public class AtWorkState extends State{
    public AtWorkState (EmployeeAccounts account) {
        super(account);
    }

    public AtWorkState(State source){
        super(source);
    }

    public void transitionState() {
        getContext().setState(new OffWorkState(this));
    }

    @Override
    public void signIn(){
        System.out.println("You are already signed in");
    }

    @Override
    public void signOut(){
        System.out.println("Now signing out...");
        transitionState();
    }
}
