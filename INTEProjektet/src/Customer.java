import java.util.ArrayList;

//Andrad

public class Customer {

	private String name;
	private String address;
	private String phoneNumber;
	private String ssn;
	private Money cash;
	private ArrayList<Product> cart = new ArrayList<>();

	public Customer(String name, String address, String phoneNumber,
			String pnr, Money cash) {

		if (name == null || address == null || phoneNumber == null || pnr == null || cash == null)
			throw new NullPointerException("Cannot initialize a parameter to the value null.");
		if (name.equals("") || address.equals("") || phoneNumber.equals("") || pnr.equals(""))
			throw new IllegalArgumentException("Cannot initialize a parameter to an empty string");

		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.ssn = pnr;
		this.cash = cash;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getPnr() {
		return ssn;
	}

	public Money getCash() {
		return cash;
	}

	public ArrayList<Product> getCart() {
		return cart;
	}

	public void setName(String name) {
		if(name.equals(""))
			throw new IllegalArgumentException("Name cannot be an empty string");
		this.name = name;
	}

	public void setAddress(String address) {
		if(address.equals(""))
			throw new IllegalArgumentException("address cannot be an empty string");

		this.address = address;
	}

	public void setPhoneNumber(String phoneNumber) {
		if(phoneNumber.equals(""))
			throw new IllegalArgumentException("phoneNumber cannot be an empty string");

		this.phoneNumber = phoneNumber;
	}

	public void setPnr(String pnr) {
		if(pnr.equals(""))
			throw new IllegalArgumentException("pnr cannot be an empty string");
		this.ssn = pnr;
	}

	public void setCash(Money money) {
		cash = money;
	}

	public void addProductsToCart(Product... product) {
		if(product.length < 1){
			throw new IllegalArgumentException("Cannot add empty array into cart");
		}
		for (int i = 0; i < product.length; i++)
			cart.add(product[i]);
	}

	public int getNumberOfProductsFromManufacturer(Manufacturer m){
		int total = 0;
		for(Product p: cart){
			
			if(p.getManufacturer().equals(m)){
				total++;
			}
		}
		
		return total;
	}
	public Product getCheapestManufacturerForProduct(Manufacturer manufacturer){
		Product tokenProduct = new Product("test", new Money(new Currency("SEK","kr"), Integer.MAX_VALUE),
				manufacturer, new ProductType("Mejeri"));

		for(Product x: getCart()){

			if(x.getManufacturer().equals(manufacturer)){

				if(!tokenProduct.getPrice().getCurrency().equals(x.getPrice().getCurrency())){ //om vardet pa varan i px ar annan valuta
					int Xvarde = x.getPrice().exchangeCurrency(x.getPrice()).getAmount(); //byter valuta
					

					if(Xvarde < tokenProduct.getPrice().getAmount() && !x.getHasUsedDiscount()){ //returnerar den billigaste varan som discount ej har anvants pa
						tokenProduct = x;
					}
				}

				//Om det ar samma valuta
				else if(tokenProduct.getPrice().getCurrency().equals(x.getPrice().getCurrency())){
					if(x.getPrice().getAmount() < tokenProduct.getPrice().getAmount()&& !x.getHasUsedDiscount()){ //returnerar den billigaste varan som discount ej har anvants pa
						tokenProduct = x;
					}
				}

			}
		}

		return tokenProduct;
	}

	public void removeProductFromCart(Product product) {
		if (!cart.contains(product)) {
			throw new IllegalArgumentException("Cart does not contain "
					+ product);
		}
		cart.remove(product);
	}
	
	public Money getCartValue(){ //returnerar priser i kr
		int total = 0;
		
		for(Product p: cart){
			if(p.getPrice().getCurrency().getCode().equals("USD")){
				total += p.getPrice().getAmount()*7;
			}
			else{
				total += p.getPrice().getAmount();
			}
		}
		
		return new Money(new Currency("SEK","kr"),total);
	}

	public void clearCart() {
		cart.clear();
	}

	public boolean changePriceForProductInCart(Product p, Money price) {
		if (cart.contains(p)) {
			p.setPrice(price);
			return true;
		}
		return false;
	}

	public boolean equals(Customer other) {

		return this.getName().equals(other.getName())
				&& this.getAddress().equals(other.getAddress())
				&& this.getPhoneNumber().equals(other.getPhoneNumber())
				&& this.getPnr().equals(other.getPnr())
				&& this.getCash().equals(other.getCash())
				&& this.getCart().equals(other.getCart());
	}

	public String toString() {
		return "Name: " + name + ", Adress: " + address + ", Phonenumber: "
				+ phoneNumber + ", SSN: " + ssn;
	}

}
