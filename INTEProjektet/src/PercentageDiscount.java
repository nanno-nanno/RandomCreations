
public class PercentageDiscount implements Discount{

	private int discountedPrice;
	private Product product;

	public PercentageDiscount(double discount, Product product) {
		if (discount < 0 || discount > 100)
			throw new IllegalArgumentException(
					"Discounts must be between 0 and 100");
		if (product == null)
			throw new NullPointerException("Product can't be null");
		this.product = product;
		discountedPrice = (int) (product.getPrice().getAmount() * (1 - discount/100));
	}

	public int getDiscountedPrice() {
		return discountedPrice;
	}

	public Product getProduct() {
		return product;
	}
	public boolean equals(PercentageDiscount dis){
		return this.getDiscountedPrice()==dis.getDiscountedPrice() &&
				this.getProduct().equals(dis.getProduct());


	}

	public String toString() {
		return product.getName() + ", Old price: " + product.getPrice().getCurrency().getSymbol() + " " + product.getPrice().getAmount()
				+ ", New price: " + discountedPrice;
	}
}
