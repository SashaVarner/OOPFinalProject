package restaurantSystem.commandInvokers;

public interface RestaurantCommandInterface {

	/*Executes a command to the restaurant system,
	* and returns object of the appropriate type */
	public Object execute() throws CommandErrorException;
}
