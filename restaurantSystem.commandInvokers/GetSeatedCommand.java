package restaurantSystem.commandInvokers;
import restaurantSystem.restaurantData.Aggregator;
import restaurantSystem.restaurantData.RestaurantTables;

public class GetSeatedCommand implements RestaurantCommandInterface {

    private RestaurantTables restaurantTables;

//    public GetSeatedCommand(Aggregator systemData)
//    {
//        this.restaurantTables=systemData.getSeated();
//    }

    @Override
    public RestaurantTables execute() {
        return this.restaurantTables;
    }

}
