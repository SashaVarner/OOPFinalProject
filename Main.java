import restaurantSystem.commandInvokers.CommandErrorException;
import restaurantSystem.restaurantData.CompositeException;

class Main {
  public static void main(String[] args) throws CompositeException, CommandErrorException {
     UserInterface ui = new UserInterface();
     ui.start();
  }
}