import java.util.ArrayList;

public class Store {

	private String name;
	private ArrayList<Discount> discounts = new ArrayList<>();

	public Store(String name) {
		if (name == null)
			throw new NullPointerException("Store name can not be null");
		if (name.equals(""))
			throw new IllegalArgumentException("Store name can not be empty");
		this.name = name;
	}

	public String getName() {
		return name;
	}

	private void buy3get1free(Manufacturer manufacturer, Customer customer) {
		if (customer.getNumberOfProductsFromManufacturer(manufacturer) < 3) {
			return;
		}

		Product p = customer.getCheapestManufacturerForProduct(manufacturer);
		int index = customer.getCart().indexOf(p);

		if (!p.getHasUsedDiscount()) {
			customer.getCart().get(index)
					.setPrice(new Money((p.getPrice().getCurrency()), 0));
			p.setDiscount(true);
		}

	}

	public void setName(String storeName) {
		if (storeName.equals(""))
			throw new IllegalArgumentException("Store name can not be empty");
		name = storeName;
	}

	public boolean addDiscountForProduct(Discount discount) {
		if (discounts.contains(discount))
			return false;

		discounts.add(discount);
		return true;
	}

	public boolean removeDiscountForProduct(Discount discount) {
		if (!discounts.contains(discount))
			return false;

		discounts.remove(discount);
		return true;
	}

	public ArrayList<Discount> getAllDiscounts() {
		return discounts;
	}

	public ArrayList<PercentageDiscount> getAllPercentageDiscounts() {

		ArrayList<PercentageDiscount> percentageDiscounts = new ArrayList<>();
		for (int i = 0; i < discounts.size(); i++) {
			if (discounts.get(i) instanceof PercentageDiscount)
				percentageDiscounts.add((PercentageDiscount) discounts.get(i));
		}
		return percentageDiscounts;
	}

	public ArrayList<ManufacturerDiscount> getAllManufacturerDiscount() {
		ArrayList<ManufacturerDiscount> manuDiscounts = new ArrayList<>();
		for (int i = 0; i < discounts.size(); i++) {
			if (discounts.get(i) instanceof ManufacturerDiscount)
				manuDiscounts.add((ManufacturerDiscount) discounts.get(i));
		}
		return manuDiscounts;
	}

	private void checkForDiscounts(Customer customer) {
		Discount discount = null;
		for (int i = 0; i < discounts.size(); i++) {
			discount = discounts.get(i);
			if (discount instanceof PercentageDiscount) {

				if (customer.getCart().contains(
						((PercentageDiscount) discount).getProduct())) {
					int index = customer.getCart().indexOf(
							((PercentageDiscount) discount).getProduct());

					Product p = customer.getCart().get(index);
					if (!p.getHasUsedDiscount()) {
						p.setPrice(new Money(p.getPrice().getCurrency(),
								((PercentageDiscount) discounts.get(i))
										.getDiscountedPrice()));
						p.setDiscount(true);
					}
				}
			}
			// EclEmma sager att en branch missas, men det gor den ej! Ifsatsen
			// kan ej vara falsk
			else if (discount instanceof ManufacturerDiscount) {
				Manufacturer md = ((ManufacturerDiscount) discounts.get(i))
						.getManufacturer();
				buy3get1free(md, customer);
			}
		}
	}

	public int calculatePriceForPurchase(Customer customer) { // bor returnera
		// priset i
		// anvandarens
		// valuta
		Money customerCart = customer.getCartValue(); // ar i kronor!
		Money customerMoney = customer.getCash();

		if (customerMoney.getCurrency().getCode().equals("USD")) {
			customerCart = customerCart.exchangeCurrency(customerCart);
		}

		return customerCart.getAmount();
	}

	public Purchase makePurchase(Customer customer) {
		Purchase purchase = null;
		if (customer.getCart().isEmpty())
			throw new IllegalPurchaseException("Cart cannot be empty"); // byt
		// exceptionnamn
		checkForDiscounts(customer); // Checks for possible discounts

		// Om Cartens varde ar i samma valuta som anvandarens planboks
		if (calculatePriceForPurchase(customer) > customer.getCash()
				.getAmount()) {
			throw new IndexOutOfBoundsException("You have insufficient funds!");
		} else {
			int total = customer.getCash().getAmount()
					- calculatePriceForPurchase(customer);
			customer.setCash(new Money((customer.getCash().getCurrency()),
					total));
		}
		purchase = new Purchase(this, customer, new Receipt(customer));
		return purchase;
	}

	public String toString() {
		return name;
	}

}
