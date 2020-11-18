package restaurantSystem.commandInvokers;

import restaurantSystem.restaurantData.Aggregator;
import restaurantSystem.restaurantData.Tab;
import restaurantSystem.restaurantData.TabDecorator;
import restaurantSystem.restaurantData.TabInterface;

public class GetTabCommand implements RestaurantCommandInterface {

	private TabInterface tabData;
	
	public GetTabCommand(Aggregator systemData)
	{
		this.tabData=systemData.getTab();
	}
	
	@Override
	public TabInterface execute() {
		return this.tabData;
	}

}
