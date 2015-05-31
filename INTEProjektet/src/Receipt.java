import java.util.ArrayList;

/* Ett Receipt skrivs nu ut som:
 * Mjolk, 150$ \n
 *  
 */

public class Receipt {

	private ArrayList<Product> products;

	public Receipt(Customer c) {
		if(c == null)
			throw new NullPointerException("Customer cannot be null.");
		if (c.getCart().isEmpty())
			throw new IllegalArgumentException(
					"Receipt constructor can't have an empty List");
		this.products = c.getCart();
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < products.size(); i++) {
			str.append("- " + products.get(i).getName() + " "
					+ products.get(i).getPrice().getCurrency().getSymbol()
					+ " " + products.get(i).getPrice().getAmount() + "\n");
		}
		String s = str.toString();
		return s;
	}
}
