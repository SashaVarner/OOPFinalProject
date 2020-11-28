package com.company;

public abstract class RestaurantTables {


    public RestaurantTables() {
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
}

