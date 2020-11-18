package restaurantSystem.restaurantData;

public interface ItemsIteratorInterface {

	/**
	 * Provide a way to determine if any items
	 * are left if not throw an exception
	 * @return
	 */
	public boolean hasNext();
	
	/**
	 * Provide a way to get a item (orderItem/menuItem) from the collection
	 * @return
	 */
	public Object getItem();
	
	
	/**
	 * Provide a way to go to the next item in the Iterator
	 */
	public void next();
	
	/**
	 * Method to get the current index
	 */
	public int getCurrentIndex();
	
	/**
	 * Resets the iterator to the beginning
	 */
	public void resetIter();
}
