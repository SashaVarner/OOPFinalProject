package com.company;
import java.util.Enumeration;
import java.util.Vector;


public class Tables extends RestaurantTables {
    Vector listOfTables = new Vector();
    //individual files/sub folders collection
    public Tables(String tableName, int numOfSeats) {
        super(tableName, numOfSeats);
    }
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

    public void putTablesTogether(int numOfPeople){
        //divide by 4 to figure out of number of tables
        for(int i = 0; i<numOfRequiredTables; i++){
            listOfTables.remove(0);

        }

    }
}//End of class