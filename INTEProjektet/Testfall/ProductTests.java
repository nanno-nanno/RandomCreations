import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;


public class ProductTests {

	@Test (expected = NullPointerException.class)
	public void CheckIfConstructorThrowsExceptionWhenNameisNull() {
		
		String name = null;
		Currency c = new Currency("SEK", "kr");
		Money price = new Money(c, 100);
		Manufacturer mf = new Manufacturer("Kalle");
		ProductType pt = new ProductType("Brod");
		
		new Product(name, price, mf, pt);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void CheckIfConstructorThrowsExceptionWhenNameIsAnEmptyString() {
		
		String name = "";
		Currency c = new Currency("SEK", "kr");
		Money price = new Money(c, 100);
		Manufacturer manu = new Manufacturer("h");
		ProductType pt = new ProductType("lask");
		
		new Product(name, price, manu, pt);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void CheckIfSetNameThrowsException(){
		String name = "Test";
		Currency c = new Currency("SEK", "kr");
		Money price = new Money(c, 100);
		Manufacturer manu = new Manufacturer("h");
		ProductType pt = new ProductType("lask");
		
		Product p = new Product(name, price, manu, pt);	
		p.setName("");
	}
	
	@Test(expected = NullPointerException.class)
	public void CheckIfConstructorThrowsExceptionWhenMoneyIsNull() {
		new Product("Mjlk", null, new Manufacturer("arla"), new ProductType("abc"));
	}
	
	@Test(expected = NullPointerException.class)
	public void CheckIfConstructorThrowsExceptionWhenManufacturerIsNull() {
		new Product("Mjlk", new Money(new Currency("SEK", "kr"), 100), null, new ProductType("abc"));
	}
	
	@Test(expected = NullPointerException.class)
	public void CheckIfConstructorThrowsExceptionWhenProductTypeIsNull() {
		new Product("Mjlk", new Money(new Currency("SEK", "kr"), 100), new Manufacturer("arla"), null);
	}
	
	@Test
	public void CheckIfGetNameReturnsTheRightName(){
		
		String name = "Mjolk";
		Product p = new Product(name, new Money(new Currency("USD", "D"), 150), new Manufacturer("Kalles co"), new ProductType("oo"));
		Assert.assertEquals(p.getName(), name);
	}
	
	@Test
	public void CheckIfGetPriceReturnsTheRightPrice() {
		
		Money price = new Money(new Currency("USD", "D"), 150);
		Product p = new Product("Mjolk", price, new Manufacturer("Kalles co"), new ProductType("oo"));
		Assert.assertEquals(p.getPrice(), price);
	}
	
	@Test
	public void CheckIfgetManufacturerReturnsTheRightManufacturer() {
		
		Manufacturer manu = new Manufacturer("Kalles co");
		Product p = new Product("Mjolk", new Money(new Currency("USD", "D"), 150), manu, new ProductType("oo"));
		Assert.assertEquals(p.getManufacturer(), manu);
		
	}
	
	@Test
	public void CheckIfGetProductTypeReturnsTheRightProductType() {
		
		ProductType pt = new ProductType("Mejeri");
		Product p = new Product("Mjolk", new Money(new Currency("USD", "D"), 150), new Manufacturer("Kalles co"), pt);
		Assert.assertEquals(p.getProductType(), pt);

	}
	@Test
	public void checkIfSetNameWorks(){
		String name = "frogurt";
		Product p = new Product("Mjolk", new Money(new Currency("USD", "D"), 150), new Manufacturer("Kalles co"), new ProductType("glass"));
		p.setName(name);
		assertEquals(p.getName(),name);
	}
	@Test
	public void checkIfSetPriceWorks(){
		Product p = new Product("apple", new Money(new Currency("USD", "D"), 1500), new Manufacturer("Kalles ltd"), new ProductType("gfrukt"));
		Money price = new Money(new Currency("USD","D"),150);
		p.setPrice(price);
		assertEquals(p.getPrice(),price);
	}
	@Test
	public void checkIfSetManufacturerWorks(){
		Product p = new Product("sko", new Money(new Currency("USD", "D"), 1500), new Manufacturer("skomakarn"), new ProductType("gfrukt"));
		Manufacturer manu = new Manufacturer("Sneakersnstuff");
		p.setManufacturer(manu);
		assertEquals(p.getManufacturer(),manu);
	}
	@Test
	public void checkIfSetProductTypeWorks(){
		Product p = new Product("sko", new Money(new Currency("USD", "D"), 1500), new Manufacturer("skomakarn"), new ProductType("gfrukt"));
		ProductType pro = new ProductType("daggdjur");
		p.setProductType(pro);
		assertEquals(p.getProductType(),pro);
	}
	@Test
	public void checkIfSetHasDiscountWorks(){
		Product p = new Product("sko", new Money(new Currency("USD", "D"), 1500), new Manufacturer("skomakarn"), new ProductType("gfrukt"));
		p.setDiscount(false);
		assertEquals(false,p.getHasUsedDiscount());
	}
	@Test
	public void checkIfEqualsMethodWorks(){
		Product p = new Product("sko", new Money(new Currency("USD", "D"), 1500), new Manufacturer("skomakarn"), new ProductType("gfrukt"));
		Product p2 = new Product("sko", new Money(new Currency("USD", "D"), 1500), new Manufacturer("skomakarn"), new ProductType("gfrukt"));
		assertEquals(true,p.equals(p2));
	}
	@Test
	public void checkIfEqualsMethodWorksWhenNameIsFalse(){
		Product p = new Product("sko", new Money(new Currency("USD", "D"), 1500), new Manufacturer("skomakarn"), new ProductType("gfrukt"));
		Product p2 = new Product("sko1", new Money(new Currency("USD", "D"), 1500), new Manufacturer("skomakarn"), new ProductType("gfrukt"));
		assertEquals(false,p.equals(p2));
	}
	@Test
	public void checkIfEqualsMethodWorksWhenPriceIsFalse(){
		Product p = new Product("sko", new Money(new Currency("USD", "D"), 1500), new Manufacturer("skomakarn"), new ProductType("gfrukt"));
		Product p2 = new Product("sko", new Money(new Currency("USD", "D"), 150), new Manufacturer("skomakarn"), new ProductType("gfrukt"));
		assertEquals(false,p.equals(p2));
	}
	@Test
	public void checkIfEqualsMethodWorksWhenManufacturerIsFalse(){
		Product p = new Product("sko", new Money(new Currency("USD", "D"), 1500), new Manufacturer("skomakarn"), new ProductType("gfrukt"));
		Product p2 = new Product("sko", new Money(new Currency("USD", "D"), 1500), new Manufacturer("skomn"), new ProductType("gfrukt"));
		assertEquals(false,p.equals(p2));
	}
	@Test
	public void checkIfEqualsMethodWorksWhenProductTypeIsFalse(){
		Product p = new Product("sko", new Money(new Currency("USD", "D"), 1500), new Manufacturer("skomakarn"), new ProductType("gfrukt"));
		Product p2 = new Product("sko", new Money(new Currency("USD", "D"), 1500), new Manufacturer("skomakarn"), new ProductType("applefrura"));
		assertEquals(false,p.equals(p2));
	}
	@Test
	public void CheckIfToStringWorks() {
		
		Product p = new Product("Mjolk", new Money(new Currency("USD", "D"), 150), new Manufacturer("Kalles co"), new ProductType("Mejeri"));
		assertEquals("Kalles co, Mjolk, Mejeri, D 150",p.toString());
	}
	
}
