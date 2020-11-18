package restaurantSystem.restaurantData;

public abstract class TabDecorator implements TabInterface {

    protected Tab decoratedTab;

    public TabDecorator(Tab decoratedTab){
        this.decoratedTab = decoratedTab;
    }

    public String toString() {
       return decoratedTab.toString();
    }
}
