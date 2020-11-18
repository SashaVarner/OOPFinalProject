import restaurantSystem.commandInvokers.CommandErrorException;
import restaurantSystem.commandInvokers.Invoker;
import restaurantSystem.restaurantData.Aggregator;
import restaurantSystem.restaurantData.Menu;
import restaurantSystem.restaurantData.MenuItem;
import restaurantSystem.restaurantData.Order;
import restaurantSystem.restaurantData.TabInterface;
import restaurantSystem.restaurantData.Table;

public class SystemInterface {

  private static Aggregator systemData;
  private static Invoker invoker;
  private static SystemInterface currentInstance=null;
  
  //speak the language of the backend
  public static void initialize(){

    //initialize menu
    Menu menu = new Menu();
    MenuItem tempMenuItem = new MenuItem("Shrimp Salad", 1, 9.99);
    menu.addMenuItem(tempMenuItem);
    tempMenuItem = new MenuItem("Fruit Parfait", 2, 9.99);
    menu.addMenuItem(tempMenuItem);
    tempMenuItem = new MenuItem("Cheese", 3, 1.99);
    menu.addMenuItem(tempMenuItem);
    tempMenuItem = new MenuItem("Cola", 4, 2.99);
    menu.addMenuItem(tempMenuItem);
    tempMenuItem = new MenuItem("Bottle Pink Moscato", 7, 17.99);
    menu.addMenuItem(tempMenuItem);
    tempMenuItem = new MenuItem("Bean Burrito", 5, 1.99);
    menu.addMenuItem(tempMenuItem);
    tempMenuItem = new MenuItem("Porterhouse", 6, 32.99);
    menu.addMenuItem(tempMenuItem);
    tempMenuItem = new MenuItem("Brownie Sundae", 8, 15.99);
    menu.addMenuItem(tempMenuItem);
    tempMenuItem = new MenuItem(" Bowl of Cheerios", 9, 4.99);
    menu.addMenuItem(tempMenuItem);
    tempMenuItem = new MenuItem("All You Can Eat Pasta", 10, 19.99);
    menu.addMenuItem(tempMenuItem);

    //initialize orders
    Order order=new Order();

    //initialize tables look at code for this
    Table table = new Table();


    systemData = new Aggregator(menu, order);
    invoker=new Invoker(systemData);
  }


  // public static SystemInterface getSystemInterface()
  // {
  //   if(SystemInterface.currentInstance == null)
  //   {
  //     SystemInterface.currentInstance=new SystemInterface();
  //   }
  //   return SystemInterface.currentInstance;
  // }

  public static String getMenu(){
      Menu menu;
      menu = invoker.getMenu();
      return menu.toString();

  };

  public static String placeOrder(String item)
  {
    String resultString="";
    try
    {
      boolean result=invoker.placeOrder(item, "1");
      if(result)
      {
        resultString="Successfully Added "+item +
        " to order";
      }
    }
    catch(IllegalArgumentException iae)
    {
       resultString=iae.getMessage();
    }
    return resultString;
  };


  public static String getTab(){
    TabInterface tab;
    tab = invoker.getTab();
    return tab.toString();
  };

  public static String getSeated() {
    Table table;
    seat = invoker.getSeated();
    return table.toString();
  }
}