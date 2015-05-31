import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class PurchaseTest {

	Store store = new Store("No Good Goods");
	
	@Test (expected = NullPointerException.class)
	public void checkIfConstructorThrowsExceptionWhenStoreIsNull() {
		Customer c1 = new Customer("Per", "salavgen 14", "0703450217",
				"0903134567", new Money(new Currency("USD", "D"), 100));
		Product p1 = new Product("Mjolk", new Money(new Currency("USD", "D"),
				150), new Manufacturer("Kalles co"), new ProductType("oo"));
		c1.addProductsToCart(p1);
		new Purchase(null, c1, new Receipt(c1));
	}
	@Test (expected = NullPointerException.class)
	public void checkIfConstructorThrowsExceptionWhenCustomerIsNull() {
		Customer c1 = new Customer("Per", "salavgen 14", "0703450217",
				"0903134567", new Money(new Currency("USD", "D"), 100));
		Product p1 = new Product("Mjolk", new Money(new Currency("USD", "D"),
				150), new Manufacturer("Kalles co"), new ProductType("oo"));
		c1.addProductsToCart(p1);
		new Purchase(store, null, new Receipt(c1));
	}
	@Test (expected = NullPointerException.class)
	public void checkIfConstructorThrowsExceptionWhenReceiptIsNull() {
		Customer c1 = new Customer("Per", "salavgen 14", "0703450217",
				"0903134567", new Money(new Currency("USD", "D"), 100));
		new Purchase(store, c1, null);
	}
	@Test
	public void testGetCustomerMethod() {
		Customer c1 = new Customer("Petter", "salavagen 14", "0703450217",
				"0903134567", new Money(new Currency("USD", "D"), 100));
		Product p1 = new Product("Mjolk", new Money(new Currency("USD", "D"),
				5), new Manufacturer("Bolaget"), new ProductType("Mejeri"));
		c1.addProductsToCart(p1);

		Receipt rec = new Receipt(c1);

		Purchase p = new Purchase(store, c1, rec);
		assertEquals(p.getCustomer(), c1);
	}

	@Test
	public void testGetReceiptMethod() {
		Customer c1 = new Customer("Petter", "salavagen 14", "0703450217",
				"0903134567", new Money(new Currency("USD", "D"), 100));

		Product p1 = new Product("Mjolk", new Money(new Currency("USD", "D"),
				5), new Manufacturer("Bolaget"), new ProductType("Mejeri"));
		c1.addProductsToCart(p1);

		Receipt rec = new Receipt(c1);

		Purchase p = new Purchase(store, c1, rec);
		assertEquals(p.getreceipt(), rec);
	}

	@Test
	public void testToString() {
		Customer c1 = new Customer("Petter", "salavagen 14", "0703450217",
				"0903134567", new Money(new Currency("USD", "D"), 100));

		Product p1 = new Product("Mjolk", new Money(new Currency("USD", "D"),
				5), new Manufacturer("Bolaget"), new ProductType("Mejeri"));
		c1.addProductsToCart(p1);

		Receipt rec = new Receipt(c1);

		Purchase p = new Purchase(store, c1, rec);
		String verifier = "--Purchase--" + "\nStore: " + store.toString()
				+ "\nCustomer: " + c1.getName() + "\nCurrent wallet: "
				+ c1.getCash() + "\nReceipt: \n" + rec.toString();
		assertEquals(p.toString(), verifier);
	}

	@Test
	public void testGetStore() {

		Customer c1 = new Customer("Petter", "salavagen 14", "0703450217",
				"0903134567", new Money(new Currency("USD", "D"), 100));

		Product p1 = new Product("Mjolk", new Money(new Currency("USD", "D"),
				5), new Manufacturer("Bolaget"), new ProductType("Mejeri"));
		c1.addProductsToCart(p1);

		Receipt rec = new Receipt(c1);

		Purchase p = new Purchase(store, c1, rec);
		Store st = p.getStore();
		assertEquals(st, p.getStore());
	}

}
