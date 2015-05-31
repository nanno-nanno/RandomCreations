import static org.junit.Assert.*;

import org.junit.Test;


public class PercentageDiscountTest {

	@Test
	public void testMakingADiscountWithOneProduct() {
		Manufacturer m = new Manufacturer("Bolaget");
		Currency c = new Currency("SEK", "kr");
		Product p = new Product("Papper", new Money(c, 5), m,
				new ProductType("Livs"));
		PercentageDiscount d = new PercentageDiscount(0.453, p);
	}

	@Test(expected = NullPointerException.class)
	public void testMakingADiscountWithNoProduct() {
		Product p = null;
		PercentageDiscount d = new PercentageDiscount(0.5, p);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMakingADiscountWithDiscountUnder0() {
		PercentageDiscount d = new PercentageDiscount(-30.0, new Product("Chorizo", new Money(new Currency("USD", "D"),
				3), new Manufacturer("Bolaget"), new ProductType("Halsa")));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMakingADiscountWithDiscountOver100() {
		PercentageDiscount d = new PercentageDiscount(100.5, new Product("Chorizo", new Money(new Currency("USD", "D"),
				3), new Manufacturer("Bolaget"), new ProductType("Halsa")));
	}
	
	@Test
	public void testGettingTheDiscountedPriceForThisDiscount() {
		PercentageDiscount d = new PercentageDiscount(0.5, new Product("Chorizo", new Money(new Currency("USD", "D"),
				3), new Manufacturer("Bolaget"), new ProductType("Halsa")));
		double dPrice = d.getDiscountedPrice();
		assertEquals(dPrice, d.getDiscountedPrice(), 1e-8);
	}
	
	@Test
	public void testGettingProductOfThisDiscount() {
		PercentageDiscount d = new PercentageDiscount(0.5, new Product("Chorizo", new Money(new Currency("USD", "D"),
				3), new Manufacturer("Bolaget"), new ProductType("Halsa")));
		Product p = d.getProduct();
		assertEquals(p, d.getProduct());
	}
	@Test
	public void checkIfEqualsMethodWorks(){
		PercentageDiscount d = new PercentageDiscount(0.5, new Product("Chorizo", new Money(new Currency("USD", "D"),
				3), new Manufacturer("Bolaget"), new ProductType("Halsa")));
		PercentageDiscount d2 = new PercentageDiscount(0.5, new Product("Chorizo", new Money(new Currency("USD", "D"),
				3), new Manufacturer("Bolaget"), new ProductType("Halsa")));
		assertEquals(true,d.equals(d2));
	}
	@Test
	public void checkIfEqualsMethodWorksWhenProductIsFalse(){
		PercentageDiscount d = new PercentageDiscount(0.5, new Product("Chorizo", new Money(new Currency("USD", "D"),
				3), new Manufacturer("holla"), new ProductType("Halsa")));
		PercentageDiscount d2 = new PercentageDiscount(0.5, new Product("Chorizo", new Money(new Currency("USD", "D"),
				3), new Manufacturer("Bolaget"), new ProductType("Halsa")));
		assertEquals(false,d.equals(d2));
	}
	@Test
	public void checkIfEqualsMethodWorksWhenDiscountPriceIsFalse(){
		PercentageDiscount d = new PercentageDiscount(50, new Product("Chorizo", new Money(new Currency("USD", "D"),
				3), new Manufacturer("Bolaget"), new ProductType("Halsa")));
		PercentageDiscount d2 = new PercentageDiscount(10, new Product("Chorizo", new Money(new Currency("USD", "D"),
				3), new Manufacturer("Bolaget"), new ProductType("Halsa")));
		assertEquals(false,d.equals(d2));
	}
	
	@Test
	public void checkIfToStringWorks() {
		PercentageDiscount d = new PercentageDiscount(0.5, new Product("Chorizo", new Money(new Currency("USD", "D"),
				3), new Manufacturer("Bolaget"), new ProductType("Halsa")));
		d.toString();
		assertEquals("Chorizo, Old price: D 3, New price: 2",d.toString());
	}


}
