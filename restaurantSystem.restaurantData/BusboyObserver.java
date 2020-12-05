package restaurantSystem.restaurantData;

public class BusboyObserver extends Observable{

        public BusboyObserver(RestaurantTables restaurantTables){
            this.restaurantTables = restaurantTables;
            this.restaurantTables.attach(this);
        }

        @Override
        public void update() {

            System.out.println("A table needs to be cleaned.");
        }
    }

