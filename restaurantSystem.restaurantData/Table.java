package com.company;

public class Table extends RestaurantTables {
    int numOfSeats;

    public Table(String tableName, int numOfSeats) {
        super(tableName);
        this.numOfSeats = numOfSeats;
    }

    public int getNumSeats() {
        return numOfSeats;
    }


}
