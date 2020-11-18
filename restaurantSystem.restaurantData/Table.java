package restaurantSystem.restaurantData;
import java.util.ArrayList;
import java.util.List;

public class Table {
    private int tableNumber;
    private int numSeats;
    private List<Table> largeTable;

public Table(int tableNumber, int numSeats){
        this.tableNumber=tableNumber;
        this.numSeats=numSeats;
        largeTable = new ArrayList<Table>();
    }

    public void add(Table table){
    largeTable.add(table);
    }

    public void remove (Table table){
    largeTable.remove(table);
    }

    public List<Table> getLargeTable(){
    return largeTable;
    }

    public String toString(){
    return ("Table Number: " + tableNumber + ". Total number of seats: " + numSeats);
    }
}
