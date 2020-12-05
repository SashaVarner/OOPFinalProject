package restaurantSystem.restaurantData;
import java.util.ArrayList;
import java.util.List;

public abstract class RestaurantTables {
private List<Observable> observers = new ArrayList<Observable>();
private RestaurantTables state;

    public RestaurantTables() {
    }

    public RestaurantTables getState() {
        return state;
    }

    public void setState(RestaurantTables state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observable observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observable observer : observers){
            observer.update();
        }
    }

    public void addTable(RestaurantTables component)
            throws CompositeException {
        throw new CompositeException(
                "Invalid Operation. Not Supported");
    }

    public int getNumSeats()
            throws CompositeException {
        throw new CompositeException(
                "Invalid Operation. Not Supported");
    }

    public String getTableName()
            throws CompositeException {
        throw new CompositeException(
                "Invalid Operation. Not Supported");
    }

    public void getSeated(int guests)
            throws CompositeException {
        throw new CompositeException(
                "Invalid Operation. Not Supported");
    }

    public void getUnseated()
            throws CompositeException {
        throw new CompositeException(
                "Invalid Operation. Not Supported");
    }

    public boolean returnOccupiedStatus()
            throws CompositeException {
        throw new CompositeException(
                "Invalid Operation. Not Supported");
    }

    public String toString(){
        return ("Invalid Operation. Not Supported");
    }
}

