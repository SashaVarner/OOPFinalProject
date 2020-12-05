import restaurantSystem.commandInvokers.CommandErrorException;
import restaurantSystem.restaurantData.CompositeException;

import java.util.*;

public class UserInterface {

  //private SystemInterface systemInterface;

  public UserInterface () throws CompositeException {
    //systemInterface = SystemInterface.getSystemInterface();
    SystemInterface.initialize();
  }

  public void start() throws CommandErrorException {
    boolean loopVar = true;
    while (loopVar)
    {
    String screen_lines = new String();

    System.out.println("Enter 1 for menu, 2 for place order, 3 for getting tab, " +
            "4 for getting seated at a table,  and 5 for exit");

    Scanner selectionS = new Scanner(System.in);
    Scanner itemS = new Scanner(System.in);
    String item = new String();
    int selection = selectionS.nextInt();
    switch(selection){
      case 1: screen_lines =   
          SystemInterface.getMenu();  break;
      case 2: // get item from UserInterface
          System.out.println("Enter in your order number");
          item=itemS.nextLine();
          System.out.println(item);
          screen_lines = 
          SystemInterface.placeOrder(item); break;
      case 3: //get tab
        screen_lines = 
        SystemInterface.getTab(); break;
//      case 4:
//        System.out.println("How many people are in your party?");
//        item=itemS.nextLine();
//        screen_lines = SystemInterface.getSeated(item);
//        break;
      case 5:
        screen_lines = SystemInterface.timeCard("5", "Anthony", "Varner", "1");
        //ask for sign in or sign out
      case 6: // Finish and exit
      loopVar = false;
      break;

    }

    System.out.println(screen_lines);
  }
  }
}