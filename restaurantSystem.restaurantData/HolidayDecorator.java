package restaurantSystem.restaurantData;

public class HolidayDecorator extends TabDecorator{

    public HolidayDecorator(Tab decoratedTab){
        super(decoratedTab);
    }

    @Override
    public String toString() {
        return ("Happy Holidays! \n" + super.toString());
    }
}
