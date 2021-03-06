package restaurantSystem.commandInvokers;
import restaurantSystem.restaurantData.Aggregator;
import restaurantSystem.restaurantData.EmployeeAccounts;
import restaurantSystem.restaurantData.Menu;
import restaurantSystem.restaurantData.OrderItem;
import restaurantSystem.restaurantData.TabInterface;

public class Invoker {
  private Aggregator agg;

  public Invoker(Aggregator agg){
    this.agg = agg;
  }

  public Menu getMenu() {
	RestaurantCommandInterface getMenuCmd = new GetMenuCommand(this.agg);
	try
	{
		Menu newMenu=(Menu) getMenuCmd.execute();
		return newMenu;
	}
	catch(CommandErrorException cee)
	{
		//Throw an IllegalStateException, because this means Menu
		//is not in a state is should be in following execute command
		//This likely means that there is a problem with the aggregator
		throw new IllegalStateException(
				"An unexpected Error occured while getting the menu");
		
	}
   
  }

  /**
  *Tries to place an order using the orderNumber passed
  * if it fails throws an exception and returns false
  * otherwise returns true
  **/
  public boolean placeOrder(String item, String quantity)
  throws IllegalArgumentException
  {
    boolean orderPlaced=false;
    Integer itemInt=Integer.parseInt(item);
    Integer quantityInt=Integer.parseInt(quantity);
    if(!(itemInt instanceof Integer))
    {
      throw new IllegalArgumentException("ERROR: An order number was not given.");
    }
    else if(!(quantityInt instanceof Integer))
    {
    	throw new IllegalArgumentException("ERROR: A quantity number was not given.");
    }
    else{
      try{
    	Integer orderItemNum=itemInt;
    	Integer orderQuantity=quantityInt;
      int orderItemNumInt=orderItemNum.intValue();
      int orderQuantityInt=orderQuantity.intValue();
      OrderItem newOrderItem=new OrderItem(orderItemNumInt,
      orderQuantityInt);
     RestaurantCommandInterface placeOrderCmd=new PlaceOrderCommand(this.agg,
        		orderItemNum.intValue(), orderQuantity.intValue());
        this.agg=(Aggregator) placeOrderCmd.execute();
        orderPlaced=true;
      }
      catch(CommandErrorException cee)
      {
          /**throw an IllegalArguementException with our
          *user error pull the User message at the 
          *systemInterface and pass it to the user interface**/
          throw new IllegalArgumentException(cee.getUserMessage());
      }
    }
    return orderPlaced;
  }

  public TabInterface getTab(){
	  RestaurantCommandInterface getTabCmd=new GetTabCommand(this.agg);
		try
		{
			TabInterface newTab=(TabInterface) getTabCmd.execute();
			return newTab;
		}
		catch(CommandErrorException cee)
		{
			//Throw an IllegalStateException, because this means Tab
			//is not in a state is should be in following execute command
			//This likely means that there is a problem with the aggregator
			throw new IllegalStateException(
					"An unexpected Error occured while getting the menu");
			
		}
  }

//    public RestaurantTables getSeated(int numberOfGuests) {
//        RestaurantCommandInterface getSeatedCmd=new GetSeatedCommand(this.agg);
//        try
//        {
//            RestaurantTables newRestaurantTables=(RestaurantTables) getSeatedCmd.execute();
//            return newRestaurantTables;
//        }
//        catch(CommandErrorException cee)
//        {
//            //Throw an IllegalStateException, because this means Tab
//            //is not in a state is should be in following execute command
//            //This likely means that there is a problem with the aggregator
//            throw new IllegalStateException(
//                    "An unexpected Error occurred while getting seated");
//
//        }
//    }

    public EmployeeAccounts signIn(String employeeId, String firstName, String lastName) throws CommandErrorException {
        RestaurantCommandInterface getSignInCmd=new SignInCommand(employeeId, firstName, lastName, this.agg);
        EmployeeAccounts newAccount=(EmployeeAccounts) getSignInCmd.execute();
        return newAccount;
    }

    public EmployeeAccounts signOut(String employeeId, String firstName, String lastName) throws CommandErrorException {
        RestaurantCommandInterface getSignOutCmd=new SignOutCommand(employeeId, firstName, lastName, this.agg);
        EmployeeAccounts newAccount=(EmployeeAccounts) getSignOutCmd.execute();
        return newAccount;

    }



}