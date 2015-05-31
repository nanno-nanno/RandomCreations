

public class Product {

	private String name;
	private Money price;
	private Manufacturer manufacturer;
	private ProductType productType;
	private boolean usedDiscount;
	
	public Product(String name,Money price, Manufacturer manufacturer, ProductType productType ){
		
		if(name == null)
			throw new NullPointerException("Name cannot be a null value");
		if(name.equals(""))
			throw new IllegalArgumentException("Name cannot be an empty string");
		if(price == null)
			throw new NullPointerException("Price cannot be null.");
		if(manufacturer == null)
			throw new NullPointerException("Manufacturer cannot be null.");
		if(productType == null)
			throw new NullPointerException("ProductType cannot be null.");
		
		this.name = name;
		this.price = price;
		this.manufacturer = manufacturer;
		this.productType = productType;
		this.usedDiscount = false;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name){
		if(name.equals(""))
			throw new IllegalArgumentException("Name cannot be empty");
		this.name = name;
	}
	public Money getPrice() {
		return price;
	}
	public void setPrice(Money price){
		this.price = price;
	}
	
	public Manufacturer getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(Manufacturer mf){
		this.manufacturer = mf;
	}
	
	public ProductType  getProductType() {
		return productType;
	}
	public void setProductType(ProductType pt){
		this.productType = pt;
	}
	
	public void setDiscount(boolean b){
		this.usedDiscount = b;
	}
	
	public boolean getHasUsedDiscount(){
		return usedDiscount;
	}
	public boolean equals(Product prod){
		return this.getName().equals(prod.getName())&&
		        this.getPrice().equals(prod.getPrice())&&
		        this.getManufacturer().equals(prod.getManufacturer())&&
		        this.getProductType().equals(prod.getProductType());
		         
	}
	
	public String toString() {
		return manufacturer + ", " + name + ", " + productType.toString() + ", " + price.getCurrency().getSymbol() + " " + price.getAmount();
	}
	
}
