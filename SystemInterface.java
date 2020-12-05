import restaurantSystem.commandInvokers.CommandErrorException;
import restaurantSystem.commandInvokers.Invoker;
import restaurantSystem.restaurantData.Aggregator;
import restaurantSystem.restaurantData.BusboyObserver;
import restaurantSystem.restaurantData.CompositeException;
import restaurantSystem.restaurantData.EmployeeAccounts;
import restaurantSystem.restaurantData.Menu;
import restaurantSystem.restaurantData.MenuItem;
import restaurantSystem.restaurantData.Order;
import restaurantSystem.restaurantData.RestaurantTables;
import restaurantSystem.restaurantData.TabInterface;
import restaurantSystem.restaurantData.Table;
import restaurantSystem.restaurantData.Tables;
import restaurantSystem.restaurantData.WaitressObserver;

public class SystemInterface {

  private static Aggregator systemData;
  private static Invoker invoker;
  private static SystemInterface currentInstance=null;
  
  //speak the language of the backend
  public static void initialize() throws CompositeException {

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

    //initialize tables
    RestaurantTables allTablesInRestaurant = new Tables();
    RestaurantTables partyRoom = new Tables();
    RestaurantTables table1 = new Table("Table 1", 4);
    RestaurantTables table2 = new Table("Table 2", 4);
    RestaurantTables table3 = new Table("Table 3", 4);
    RestaurantTables table4 = new Table("Table 4", 4);
    partyRoom.addTable(table1);
    partyRoom.addTable(table2);
    partyRoom.addTable(table3);
    partyRoom.addTable(table4);
    RestaurantTables table5 = new Table("Table 5", 4);
    RestaurantTables table6 = new Table("Table 6", 4);
    RestaurantTables table7 = new Table("Table 7", 4);
    RestaurantTables table8 = new Table("Table 8", 4);
    RestaurantTables table9 = new Table("Table 9", 4);
    RestaurantTables table10 = new Table("Table 10", 4);
    allTablesInRestaurant.addTable(partyRoom);
    allTablesInRestaurant.addTable(table5);
    allTablesInRestaurant.addTable(table6);
    allTablesInRestaurant.addTable(table7);
    allTablesInRestaurant.addTable(table8);
    allTablesInRestaurant.addTable(table9);
    allTablesInRestaurant.addTable(table10);

    //Initialize Observers
    new WaitressObserver(allTablesInRestaurant);
    new BusboyObserver(allTablesInRestaurant);

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

//  public static String getSeated(String guests) {
//    RestaurantTables yourTable;
//    int numberOfGuests = Integer.parseInt(guests);
//    yourTable = invoker.getSeated(numberOfGuests);
//    return yourTable.toString();
//  }

  public static String timeCard(String employeeID, String firstName, String lastName, String choice) throws CommandErrorException {
    EmployeeAccounts employeeAccount;
    if(choice=="1") {
      employeeAccount = invoker.signIn(employeeID, firstName, lastName);
    }else {
      employeeAccount = invoker.signOut(employeeID, firstName, lastName);
    }
    return employeeAccount.toString();
  }
}