import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

public class RecieptTests {

	@Test(expected = IllegalArgumentException.class)
	public void CheckIfConstructorThrowsExceptionWhenListIsEmpty() {
		Customer c1 = new Customer("Petter", "salavgen 14", "0703450217",
				"0903134567", new Money(new Currency("USD", "D"), 100));

		new Receipt(c1);

	}
	
	@Test(expected = NullPointerException.class)
	public void CheckIfConstructorThrowsExceptionWhenCustomerIsNull() {
		new Receipt(null);
	}

	@Test
	public void CheckIfGetProductsReturnsTheRightProducts() {

		Product p1 = new Product("Mjolk", new Money(new Currency("USD", "D"),
				150), new Manufacturer("Kalles co"), new ProductType("oo"));
		Product p2 = new Product("Saft", new Money(new Currency("SEK", "kr"),
				10), new Manufacturer("Janis co"), new ProductType("OB"));
		Product p3 = new Product("abc", new Money(new Currency("USD","D"),
				111), new Manufacturer("def q"), new ProductType("bbr"));

		Customer c1 = new Customer("Petter", "salavgen 14", "0703450217",
				"0903134567", new Money(new Currency("USD", "D"), 100));

		c1.addProductsToCart(p1, p2, p3);

		Receipt r = new Receipt(c1);

		assertEquals("- "+"Mjolk " + p1.getPrice().getCurrency().getSymbol() + " "
				+ p1.getPrice().getAmount() + "\n" + "- "+"Saft "
				+ p2.getPrice().getCurrency().getSymbol() + " "
				+ p2.getPrice().getAmount() + "\n" + "- "+"abc "
				+ p3.getPrice().getCurrency().getSymbol() + " "
				+ p3.getPrice().getAmount() + "\n", r.toString());
	}

	@Test
	public void testGetCart() {
		Product p1 = new Product("Mjolk", new Money(new Currency("USD", "D"),
				150), new Manufacturer("Kalles co"), new ProductType("oo"));
		Product p2 = new Product("Saft", new Money(new Currency("SEK", "kr"),
				10), new Manufacturer("Janis co"), new ProductType("OB"));
		Product p3 = new Product("abc", new Money(new Currency("USD","D"),
				111), new Manufacturer("def q"), new ProductType("bbr"));

		Customer c1 = new Customer("Petter", "salavgen 14", "0703450217",
				"0903134567", new Money(new Currency("USD", "D"), 100));

		c1.addProductsToCart(p1, p2, p3);

		Receipt r = new Receipt(c1);

		assertEquals(r.getProducts(), c1.getCart());
	}

	@Test
	public void TestToString() {

		Product p1 = new Product("Saft", new Money(new Currency("SEK", "kr"),
				10), new Manufacturer("Janis co"), new ProductType("OB"));
		Product p2 = new Product("abc", new Money(new Currency("USD","D"),
				111), new Manufacturer("def q"), new ProductType("bbr"));

		Customer c1 = new Customer("Petter", "salavgen 14", "0703450217",
				"0903134567", new Money(new Currency("USD", "D"), 100));

		c1.addProductsToCart(p1);

		Receipt r = new Receipt(c1);
		assertEquals("- Saft kr 10\n", r.toString());

	}

}
