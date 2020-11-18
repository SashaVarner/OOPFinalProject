package restaurantSystem.restaurantData;


public class Menu {

	private MenuItem[] menuList;
	private int menuSize=10;
	private int firstEmptyIndex=0;
	
	
	public Menu()
	{
		menuList=new MenuItem[menuSize];
	}
	
	public Menu(int menuSize)
	throws IllegalArgumentException
	{
		if(menuSize < 1)
		{
			throw new IllegalArgumentException("Menu must be of at least size 1");
		}
		this.menuSize=menuSize;
		menuList=new MenuItem[menuSize];
	}
	
	/**
	 * Performs a deep copy of an original menu and uses
	 * it to construct a new menu
	 * @param menuToCopy
	 */
	public Menu(Menu menuToCopy)
	{
		ItemsIteratorInterface iterator=menuToCopy.getAllItemsIterator();
		if(menuToCopy.size() == 0)
		{
			this.menuList=new MenuItem[menuSize];
		}
		else
		{
			//double the size to add room for more
			int copySize=menuToCopy.size()*2;
			this.menuList=new MenuItem[copySize];
			//get the first item then loop
			MenuItem currentItem=(MenuItem) iterator.getItem();
			int currentIndex=0;
			this.menuList[currentIndex]=currentItem;
			this.firstEmptyIndex=this.firstEmptyIndex+1;
			while(iterator.hasNext())
			{
				iterator.next();
				currentIndex=iterator.getCurrentIndex();
				currentItem=(MenuItem) iterator.getItem();
				this.menuList[currentIndex]=currentItem;
				this.firstEmptyIndex=this.firstEmptyIndex+1;
			}
      System.out.println("Finished creating the menu");
		}
	}
	
	public int size()
	{
		return this.firstEmptyIndex;
	}
	
	/**
	 * Adds an item to the menuList
	 * @param item
	 */
	public void addMenuItem(MenuItem item)
	throws NullPointerException
	{
		if(this.menuSize == this.firstEmptyIndex)
		{
			this.menuSize=(menuSize*2);
			this.menuList=new MenuItem[menuSize];
			try
			{
				AllItemsIterator items=this.getAllItemsIterator();
				MenuItem anItem=(MenuItem) items.getItem();
				this.menuList[items.getCurrentIndex()]=anItem;
				while(items.hasNext())
				{
					items.next();
					anItem=(MenuItem) items.getItem();
					this.menuList[items.getCurrentIndex()]=anItem;
				}
			}
			catch(NullPointerException npe)
			{
				System.err.println("The list which is being copied is empty");
			}
		}
		else
		{
			this.menuList[this.firstEmptyIndex]=item;
			this.firstEmptyIndex=firstEmptyIndex+1;
		}
	}
	
	/**
	 * Give an index with an iterator point to a the list
	 * item that is to be deleted.
	 * If the 
	 */
	public void deleteMenuItem(ItemsIteratorInterface itemIter)
	{
		int indexOfItemToRemove=itemIter.getCurrentIndex();
		boolean last;
		if(indexOfItemToRemove == this.size()-1)
		{
			last=true;
			this.firstEmptyIndex=indexOfItemToRemove;
			this.menuList[indexOfItemToRemove]=null;
		}
		else
		{
			last=false;
		}
		int currentIndex;
		int previousIndex;
		//Move back all of the indexes of the list by one
		while(itemIter.hasNext() && !last)
		{
			itemIter.next();
			currentIndex=itemIter.getCurrentIndex();
			previousIndex=currentIndex-1;
			//Set previous index regardless in order to move back all items from removed
			//index
			this.menuList[previousIndex]=this.menuList[currentIndex];
			if(currentIndex == (this.size()-1))
			{
				last=true;
				this.firstEmptyIndex=currentIndex;
				this.menuList[currentIndex]=null;
			}
		}	
		itemIter.resetIter();
	}
	
	/**
	 * Returns if an order ins in a Menu
	 * @param orderNumber
	 * @return true if found, otherwise false
	 */
	public boolean isOrderInMenu(int orderNumber)
	{
		AllItemsIterator searchIterator=this.getAllItemsIterator();
		boolean found=false;
		if(searchIterator.getItem().getOrderNumber() == orderNumber)
		{
			found=true;
		}
		while(!found & searchIterator.hasNext())
		{
			searchIterator.next();
			if(searchIterator.getItem().getOrderNumber() == orderNumber)
			{
				found=true;
			}
		}
		return found;
	}
	
	@Override
	public String toString()
	{
		ItemsIteratorInterface menuIterator=this.getAllItemsIterator();
		MenuItem currentItem=(MenuItem) menuIterator.getItem();
		String menuString="\t\tMENU\t\t"+"\n\n"+currentItem.toString()+"\n\n";
		while(menuIterator.hasNext())
		{
			menuIterator.next();
			currentItem=(MenuItem) menuIterator.getItem();
			menuString=menuString+"\t"+currentItem.toString()+"\n\n";
		}
		return menuString;
	}
	
	/**
	 * Factory method to return an AllItemsIterator
	 * @return
	 */
	public AllItemsIterator getAllItemsIterator()
	{
		AllItemsIterator tempItemsIterator=new AllItemsIterator();
		return tempItemsIterator;
	}
	
	/**
	 * Class provides an interator to iterate through the menu list
	 * @author ryan
	 *
	 */
	private class AllItemsIterator implements ItemsIteratorInterface
	{
	
		private MenuItem currentItem;
		private int currentIndex;
		
		public AllItemsIterator()
		{
			this.currentIndex=0;
			//see if list is empty if not set currentItem to the item at the current index
			if(!(Menu.this.firstEmptyIndex == 0))
			{
				currentItem=Menu.this.menuList[currentIndex];
			}
			else
			{
				throw new NullPointerException("List is empty");
			}
		}
		
		/**
		 * Provide a way to determine if any items
		 * are left if not throw an exception
		 * @return
		 */
		public boolean hasNext()
		{
			return (currentIndex+1 < Menu.this.size());
		}
		
		/**
		 * Provide a way to get a item from the collection
		 * @return
		 */
		public MenuItem getItem()
		{
			return this.currentItem;
		}
		
		
		/**
		 * Provide a way to go to the next item in the Iterator
		 */
		public void next()
		{
			if(this.hasNext())
			{
				this.currentIndex=currentIndex+1;
				this.currentItem=Menu.this.menuList[this.currentIndex];
			}
			else
			{
				throw new IllegalStateException("There is no more items in this list");
			}
		}
		
		/**
		 * Method to get the current index
		 */
		public int getCurrentIndex()
		{
		
			return this.currentIndex;
		}
		
		public void resetIter()
		{
			this.currentIndex=0;
			if(!(Menu.this.firstEmptyIndex == 0))
			{
				currentItem=Menu.this.menuList[currentIndex];
			}
			else
			{
				currentItem=null;
			}			
		}
	}
}