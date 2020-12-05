package restaurantSystem.restaurantData;
import java.util.Enumeration;
import java.util.Vector;


public class Tables extends RestaurantTables {
    Vector listOfTables = new Vector();


    public void addTable(RestaurantTables rt)
            throws CompositeException {
        listOfTables.add(rt);
    }
    public RestaurantTables getComponent(int location)
            throws CompositeException {
        return (RestaurantTables) listOfTables.elementAt(
                location);
    }
    public int getNumSeats()
        throws CompositeException {
            int totalNumOfSeats = 0;
            Enumeration e = listOfTables.elements();
            while (e.hasMoreElements()) {
                RestaurantTables tableComponent =
                        (RestaurantTables) e.nextElement();
                totalNumOfSeats = totalNumOfSeats +
                        (tableComponent.getNumSeats());
            }
            return totalNumOfSeats;

    }

    public void getSeated(int guests) throws CompositeException {
        int guestsToBeSeated=guests;
        int seatsAtTable=0;
        int seatedGuests=0;
        Enumeration e = listOfTables.elements();
        while (e.hasMoreElements()&& guests>0) {
            RestaurantTables tableComponent =
                    (RestaurantTables) e.nextElement();
                    seatsAtTable = tableComponent.getNumSeats();
                    if(guestsToBeSeated>seatsAtTable) {
                        seatedGuests=seatsAtTable;
                    } else{
                        seatedGuests=guestsToBeSeated;
                    }
                    tableComponent.getSeated(seatedGuests);
                    tableComponent.setState(tableComponent);
                    guestsToBeSeated=guestsToBeSeated-seatedGuests;
        }
        }

    public void getUnseated()
            throws CompositeException {
        Enumeration e = listOfTables.elements();
        while (e.hasMoreElements()) {
            RestaurantTables tableComponent =
                    (RestaurantTables) e.nextElement();
            if (tableComponent.returnOccupiedStatus()==true){
                tableComponent.getUnseated();
                tableComponent.setState(tableComponent);
            }
        }

    }




}//End of class