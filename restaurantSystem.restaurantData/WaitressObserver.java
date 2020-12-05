package restaurantSystem.restaurantData;

public class WaitressObserver extends Observable{

    public WaitressObserver(RestaurantTables restaurantTables){
        this.restaurantTables = restaurantTables;
        this.restaurantTables.attach(this);
    }

    @Override
    public void update() {
        System.out.println("A new table is now occupied");
    }
}
