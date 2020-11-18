package restaurantSystem.restaurantData;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MenuItem {

	private String description;
	private int orderNumber;
	private BigDecimal price;
	
	public MenuItem()
	{
		this.description="";
		this.orderNumber=0;
		this.price=new BigDecimal(0);
		this.price=this.price.setScale(2, RoundingMode.CEILING);
	}
	
	public MenuItem(String descript, int orderNum, double price)
	throws IllegalArgumentException
	{
		
		if(descript == null)
		{
			throw new IllegalArgumentException("Menu Item Description can not be null");
		}
		
		this.description=descript;
		this.orderNumber=orderNum;
		String priceString=Double.toString(price);
		this.price=new BigDecimal(priceString);
		this.price=this.price.setScale(2, RoundingMode.CEILING);
	}
	
	
	public MenuItem(MenuItem itemToCopy)
	{
		this.description=itemToCopy.getDescription();
		this.orderNumber=itemToCopy.getOrderNumber();
		this.price=new BigDecimal(itemToCopy.getPrice());
		this.price=this.price.setScale(2, RoundingMode.CEILING);
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public int getOrderNumber()
	{
		return this.orderNumber;
	}
	
	public String getPrice()
	{
		return this.price.toString();
	}
	
	public String toString()
	{
		String menuItemString=this.orderNumber+": "+this.description+"\n"
				+"$"+this.price;
		return menuItemString;
	}
}