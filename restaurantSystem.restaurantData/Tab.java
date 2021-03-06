//A tab will be constructed from the Tab class containing all of the ordered items, returned as an array of
//        strings for the user interface to display. Note that a tab needs information from both the Menu and the
//        Orders objects. (The Orders object indicates what menu items were ordered, and the Menu class has the
//        description of each item to include in the Tab.)

package restaurantSystem.restaurantData;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Tab {

    private BigDecimal tabTotal;
    private int numberOfOrders;
    private MenuItem[] tabItems;
    private final static String LINESPACE="\n\n";
    private final static char TABCHAR='\t';
    private final static String TABHEADING="Tables tab:";

    /**
    * Parameter Constructor
    */
    public Tab(Menu menu, Order order) {
    	if(menu == null)
    	{
    		throw new IllegalArgumentException("Menu has not been created.");
    	}
    	
    	if(order == null)
    	{
    		throw new IllegalArgumentException("Menu has not been created.");
    	}
    	if(!(this.isCorrectOrder(menu, order)))
    	{
    		throw new IllegalArgumentException("Some of the orders are "
    				+ "not found in the menu given");
    	}
    	this.tabTotal=new BigDecimal(0);
    	this.tabTotal.setScale(2, RoundingMode.CEILING);
    	//We want the tab to generate in the constructor since we want all the functions
    	//to access our array of menuItems not just one function
    	this.generateTab(menu, order);
      System.out.println("Finished Generating Tab");
    	//We Calculate the tab each time the constructor is called so we have a running total
    	this.calculateTab();
    	
    }
    

    /**
     * Used by the constructor to generate the full tab,
     * the tab is then stored in the instance variable for the tab
     * so it can be used by all functions of this instance.
     * @param aMenu
     * @param aOrder
     */
    private void generateTab(Menu aMenu, Order aOrder)
    {
    	this.numberOfOrders=aOrder.size();
    	this.tabItems=new MenuItem[this.numberOfOrders];
    	//Only put the effort in if the lists are not empty.
    	if(numberOfOrders > 0)
    	{
    		ItemsIteratorInterface orderIterator=aOrder.getAllItemsIterator();
    		ItemsIteratorInterface menuIterator = aMenu.getAllItemsIterator();
    		OrderItem tempOrderItem=(OrderItem) orderIterator.getItem();
    		MenuItem tempMenuItem = (MenuItem) menuIterator.getItem();
        
    		//Add the first Item to the tabItems
    		/*Fixed issue where tempMenuItem was comparing its order
    		*number to itself.
    		*/
    		int i=0;
    		if(tempOrderItem.getOrderNumber() == tempMenuItem.getOrderNumber()){
    			this.tabItems[i]= tempMenuItem;
    		}
    		else
    		{
    			boolean found = false;
          while(menuIterator.hasNext()&& !found)
          {
            menuIterator.next();
            tempMenuItem = (MenuItem) menuIterator.getItem();
            if(tempOrderItem.getOrderNumber() == tempMenuItem.getOrderNumber() )
            {
            			found=true;
            			this.tabItems[i]= tempMenuItem;
            }
          }
    		}
    		
    		//Add the rest to the tab items
    		while (orderIterator.hasNext()) 
    		{
    			orderIterator.next();
    			menuIterator.resetIter();
    			//Reset Menu iterator so we can loop through it again 
    			//for every new order number
    			i=i+1;
    			tempOrderItem = (OrderItem) orderIterator.getItem();
    			tempMenuItem = (MenuItem) menuIterator.getItem();


    			if(tempOrderItem.getOrderNumber() == tempMenuItem.getOrderNumber())
    			{
    				this.tabItems[i]= tempMenuItem;
    			}
    			else
    			{
    				boolean found = false;
    				while(menuIterator.hasNext()&& !found)
    				{
    					menuIterator.next();
    					tempMenuItem = (MenuItem) menuIterator.getItem();
    					if(tempOrderItem.getOrderNumber() == tempMenuItem.getOrderNumber())
    					{
    						found=true;
    						this.tabItems[i]= tempMenuItem;
    					}
    				}
    			}
    		}
    	}
    }

    /**
     * Used to caluclate the tab and store it in a private instance variable
     * for an instance of this class, which can be used by all other methods of the
     * class.
     */
    private void calculateTab() 
    {
        BigDecimal itemPrice = new BigDecimal(0);
        itemPrice.setScale(2, RoundingMode.CEILING);
        for(int i=0;i<this.tabItems.length; i++){
           itemPrice= new BigDecimal(this.tabItems[i].getPrice());
           this.tabTotal = this.tabTotal.add(itemPrice);
        }
    }
    
    /**
     * Used to get the total, used by toString, but still
     * useful to the user Interface as well to display
     * to the user
     * @return BigDecimal version of the Total price
     */
    public BigDecimal getTabTotal()
    {
    	return this.tabTotal;
    }
    
    /**
    * Used to get a well formatted version of the tab
    */
    @Override
    public String toString()
    {
    	String tabString=Tab.TABHEADING+Tab.LINESPACE;
    	
    	
    	for(int i=0; i<this.tabItems.length; i++)
    	{
    		MenuItem currentTabItem=this.tabItems[i];
    		tabString=tabString+Tab.TABCHAR+"Item "+currentTabItem.toString()
    		+Tab.LINESPACE;
    	}
    	tabString=tabString+Tab.LINESPACE+"Total Price:"+Tab.TABCHAR+"$"+this.tabTotal.toPlainString();
    	return tabString;
    }
    
    /**
     * Checks if a tab matches a order to menu items that exist
     * @param aMenu
     * @param aOrder
     * @return
     */
    private boolean isCorrectOrder(Menu aMenu, Order aOrder)
	{
    	//Added to Tab because it is more appropriate to check for correctness
    	//closer to the creation of the object. Other we run into exceptions 
    	//that may or may not happen.
    	boolean allOrdersMatch=false;
    	if( aOrder.size() > 0)
    	{
    		ItemsIteratorInterface orderIterator=aOrder.getAllItemsIterator();
    		boolean orderMismatch=false;
    		int currentIndex=orderIterator.getCurrentIndex();
    		OrderItem currentItem=(OrderItem) orderIterator.getItem();
    		int currentOrderNumber=currentItem.getOrderNumber();
    		orderMismatch=!(aMenu.isOrderInMenu(currentOrderNumber));
    		while(orderIterator.hasNext() && !orderMismatch)
    		{
    			orderIterator.next();
    			currentIndex=orderIterator.getCurrentIndex();
    			currentItem=(OrderItem) orderIterator.getItem();
    			currentOrderNumber=currentItem.getOrderNumber();
    			orderMismatch=!(aMenu.isOrderInMenu(currentOrderNumber));
    		}
    		allOrdersMatch=!orderMismatch;
    	}
    	else
    	{
    		//if the size of the orders is 0 then all of
    		//the orders are found in the menu
    		allOrdersMatch=true;
    	
    	}
		return allOrdersMatch;
	}

}
