package restaurantSystem.commandInvokers;

import restaurantSystem.restaurantData.Order;
import restaurantSystem.restaurantData.OrderItem;
import restaurantSystem.restaurantData.Aggregator;
import restaurantSystem.restaurantData.Menu;
import restaurantSystem.commandInvokers.CommandErrorException;

public class PlaceOrderCommand implements RestaurantCommandInterface {

	private Order orderData;
	private Menu menuData;
	private OrderItem newOrderItem;
	
	public PlaceOrderCommand(Aggregator systemData, int orderChoice, int orderQuantity)
	{
		this.menuData=systemData.getMenu();
		this.orderData=systemData.getOrder();
		this.newOrderItem=new OrderItem(orderChoice, orderQuantity);
	}
	
	
	@Override
	public Aggregator execute() throws CommandErrorException {
		this.orderData.addOrderItem(this.newOrderItem);
		//copy the order add it to the new aggregator which will attempt to make a new tab
		//with the data
		Order newOrder=new Order(this.orderData);
		try
		{
      System.out.println("I start creating aggregators");
			Aggregator newAggeragator=new Aggregator(this.menuData, newOrder);
			return newAggeragator;
		}
		catch(IllegalArgumentException iae)
		{
			String programmerError="Error occured while processing execute on placeOrder "+
					iae.getCause();
			String userError="Error: You order had issues processing. check your order selection and try again.";
			throw new CommandErrorException(programmerError, userError);
		}
		
	}

}