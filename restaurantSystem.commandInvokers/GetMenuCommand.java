package restaurantSystem.commandInvokers;

import restaurantSystem.commandInvokers.RestaurantCommandInterface;
import restaurantSystem.restaurantData.Aggregator;
import restaurantSystem.restaurantData.Menu;

public class GetMenuCommand implements RestaurantCommandInterface 
{

  private Menu menuData;

  public GetMenuCommand(Aggregator agg){
    this.menuData=agg.getMenu();
  }

  @Override
  public Menu execute(){ 
    return this.menuData;
   }
}