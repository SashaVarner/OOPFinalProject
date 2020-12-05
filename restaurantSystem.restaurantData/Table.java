package restaurantSystem.restaurantData;

public class Table extends RestaurantTables {
    String tableName;
    int numOfSeats;
    boolean occupied;

    public Table(String tableName, int numOfSeats) {
        this.tableName=tableName;
        this.numOfSeats = numOfSeats;
        this.occupied = false;
    }

    public int getNumSeats() {
        return numOfSeats;
    }


    public void getSeated(String guests){
        this.occupied = true;
    }

    public void getUnseated(){
        this.occupied = false;
    }

    public boolean returnOccupiedStatus(){
        return occupied;
    }

    public String toString(){
        return ("Table " + tableName + ", which seats " + numOfSeats + " guests.");
    }

}
