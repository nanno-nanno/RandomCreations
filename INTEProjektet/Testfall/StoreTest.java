import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class StoreTest {

	Store store = new Store("No Good Goods");

	@Test
	public void testMakingANewStore() {
		Store store = new Store("Yes");
		assertNotNull(store);
	}

	@Test
	public void testMakingANullStore() {
		Store store = null;
		assertNull(store);
	}

	@Test
	public void testSetName() {
		Store testStore = new Store("Well");
		testStore.setName("Best Store");
		assertEquals(testStore.getName(), "Best Store");
	}

	@Test(expected = NullPointerException.class)
	public void testMakingAStoreWithNameNull() {
		Store store = new Store(null);
	}


	@Test(expected = IllegalArgumentException.class)
	public void testMakingAStoreWithNameEmptyString() {
		Store store = new Store("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetNameToAnEmptyString() {
		store.setName("");
	}

	@Test
	public void testAddingADiscountToListDiscounts() {
		PercentageDiscount d = new PercentageDiscount(0.5, new Product("Papper", new Money(
				new Currency("USD", "D"), 5), new Manufacturer("Bolaget"),
				new ProductType("Livs")));

		store.addDiscountForProduct(d);

		ArrayList<Discount> checker = new ArrayList<>();

		checker.add(d);

		assertEquals(store.getAllDiscounts(), checker);
	}

	@Test
	public void testMakingAPurchaseWithNoDiscounts() {
		Product p1 = new Product("Mjolj", new Money(new Currency("USD", "D"),
				5), new Manufacturer("Bolaget"), new ProductType("Mejeri"));
		Product p2 = new Product("Mjolkds", new Money(
				new Currency("USD", "D"), 5), new Manufacturer("Bolaget"),
				new ProductType("Mejeri"));
		Product p3 = new Product("Mjolasdsd", new Money(new Currency("USD",
				"D"), 50), new Manufacturer("Bolaget"), new ProductType(
						"Mejeri"));
		Customer c1 = new Customer("Nils", "Hejvagen 14", "0703450217",
				"0903134567", new Money(new Currency("USD", "D"), 100));
		store.addDiscountForProduct(new PercentageDiscount(50, p3));
		c1.addProductsToCart(p1, p2);
		store.makePurchase(c1);
	}

	@Test
	public void testAddDiscountForProduct(){ //discounten finns redan
		PercentageDiscount d = new PercentageDiscount(0.5, new Product("Papper", new Money(
				new Currency("USD", "D"), 5), new Manufacturer("Bolaget"),
				new ProductType("Livs")));

		store.addDiscountForProduct(d);

		assertEquals(store.addDiscountForProduct(d),false);
	} 

	@Test
	public void testRemoveDiscountForProduct(){ //discounten finns ej
		PercentageDiscount d = new PercentageDiscount(0.5, new Product("Papper", new Money(
				new Currency("USD", "D"), 5), new Manufacturer("Bolaget"),
				new ProductType("Livs")));

		assertEquals(store.removeDiscountForProduct(d),false);
	} 

	@Test
	public void checkIfMakePurchaseReturnsTheCorrectPurchase() {
		Product p1 = new Product("Mjolj", new Money(new Currency("USD", "D"),
				5), new Manufacturer("Bolaget"), new ProductType("Mejeri"));
		Product p2 = new Product("Mjolkds", new Money(
				new Currency("USD", "D"), 5), new Manufacturer("Bolaget"),
				new ProductType("Mejeri"));
		Product p3 = new Product("Mjolasdsd", new Money(new Currency("USD",
				"D"), 50), new Manufacturer("Bolaget"), new ProductType(
						"Mejeri"));
		Product p4 = new Product("Mjoljdwadefgr", new Money(new Currency("USD",
				"D"), 5), new Manufacturer("Bolaget"), new ProductType(
						"Mejeri"));

		Customer c1 = new Customer("Nils", "Hejvagen 14", "0703450217",
				"0903134567", new Money(new Currency("USD", "D"), 100));

		PercentageDiscount d1 = new PercentageDiscount(50, p3);

		c1.addProductsToCart(p1);
		c1.addProductsToCart(p2);
		c1.addProductsToCart(p3);
		c1.addProductsToCart(p4);

		store.addDiscountForProduct(d1);

		Purchase p = store.makePurchase(c1);

		Customer c2 = p.getCustomer();
		Receipt r = p.getreceipt();

		Purchase pp = new Purchase(store, c2, r);

		store.removeDiscountForProduct(d1);

		assertEquals(r, p.getreceipt());
		assertEquals(c2, p.getCustomer());
	}

	@Test
	public void testgetAllPercentageDiscounts1(){
		Product p1 = new Product("Test", new Money(new Currency("USD",
				"D"), 50), new Manufacturer("Bolaget"), new ProductType(
						"Mejeri"));
		PercentageDiscount d1 = new PercentageDiscount(50, p1);

		store.addDiscountForProduct(d1);

		ArrayList<PercentageDiscount>percentageDiscounts = new ArrayList<>();
		percentageDiscounts.add(d1);
		assertEquals(percentageDiscounts, store.getAllPercentageDiscounts());
	}
	@Test
	public void testgetAllPercentageDiscounts2(){
		Product p1 = new Product("Test", new Money(new Currency("USD",
				"D"), 50), new Manufacturer("Bolaget"), new ProductType(
						"Mejeri"));

		ManufacturerDiscount md = new ManufacturerDiscount(new Manufacturer("Arla"));
		PercentageDiscount d1 = new PercentageDiscount(50, p1);

		store.addDiscountForProduct(md);
		store.addDiscountForProduct(d1);

		ArrayList<PercentageDiscount>percentageDiscounts = new ArrayList<>();
		percentageDiscounts.add(d1);
		assertEquals(percentageDiscounts, store.getAllPercentageDiscounts());
	}

	@Test
	public void testgetAllManufactorDiscounts(){
		Product p1 = new Product("Test", new Money(new Currency("USD",
				"D"), 50), new Manufacturer("Bolaget"), new ProductType(
						"Mejeri"));

		ManufacturerDiscount md = new ManufacturerDiscount(new Manufacturer("Arla"));
		PercentageDiscount d1 = new PercentageDiscount(50, p1);

		store.addDiscountForProduct(d1);
		store.addDiscountForProduct(md);

		ArrayList<ManufacturerDiscount>manufacturerDiscounts = new ArrayList<>();
		manufacturerDiscounts.add(md);
		assertEquals(manufacturerDiscounts, store.getAllManufacturerDiscount());
	}

	@Test
	public void TestcheckForDiscounts(){
		Product pp1= new Product("Billigast", new Money(new Currency("SEK","kr"), 1),
				new Manufacturer("Muu.Inc"), new ProductType("Mejeri"));

		Product pp2 = new Product("Mellan", new Money(new Currency("SEK","kr"), 200),
				new Manufacturer("Muu.Inc"), new ProductType("Mejeri"));

		Product pp3 = new Product("Dyrast", new Money(new Currency("USD","D"), 10000),
				new Manufacturer("Muu.Inc"), new ProductType("Mejeri"));

		Customer c = new Customer("Nils", "Hejvagen 14", "0703450217",
				"0903134567", new Money(new Currency("USD","D"), Integer.MAX_VALUE));

		c.addProductsToCart(pp1,pp2,pp3);

		store.addDiscountForProduct(new ManufacturerDiscount(new Manufacturer("Muu.Inc")));

		Purchase pur3 = store.makePurchase(c);

		assertEquals(0, pp1.getPrice().getAmount());
	}



	@Test(expected = IllegalPurchaseException.class)
	public void tryMakingAPurchaseWithCustomerThatHasAnEmptyCart() {
		store.makePurchase(new Customer("Nils", "Hejvagen 14", "0703450217",
				"0903134567", new Money(new Currency("USD", "D"), 100)));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void makePurchaseWithInsufficentFunds01(){
		Product p1 = new Product("Yxa", new Money(new Currency("USD", "D"),
				1000), new Manufacturer("WELL"), new ProductType("Hantverk"));
		
		Customer c1 = new Customer("Ricardo", "Albatrosvagen 12", "0734567891",
				"321245788", new Money(new Currency("USD", "D"), 100));
		
		c1.addProductsToCart(p1);
		
		store.makePurchase(c1);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void makePurchaseWithInsufficentFunds02(){
		Product p1 = new Product("Yxa", new Money(new Currency("SEK", "D"),
				1000), new Manufacturer("WELL"), new ProductType("Hantverk"));
		
		Customer c1 = new Customer("Ricardo", "Albatrosvagen 12", "0734567891",
				"321245788", new Money(new Currency("USD", "D"), 100));
		
		c1.addProductsToCart(p1);
		
		store.makePurchase(c1);
	}

	@Test
	public void makePurchaseWithOneProduct() {
		Product p1 = new Product("Yxa", new Money(new Currency("USD", "D"),
				100), new Manufacturer("WELL"), new ProductType("Hantverk"));
		Customer c1 = new Customer("Ricardo", "Albatrosvagen 12", "0734567891",
				"321245788", new Money(new Currency("USD", "D"), 1500));
		c1.addProductsToCart(p1);
		store.makePurchase(c1);
	}
	
	@Test
	public void getTotalCostForPurchase01(){ 
		Product p1 = new Product("Yxa", new Money(new Currency("SEK", "kr"),
				1000), new Manufacturer("WELL"), new ProductType("Hantverk"));
		Product p2 = new Product("Yxa", new Money(new Currency("SEK", "kr"),
				1000), new Manufacturer("WELL"), new ProductType("Hantverk"));
		Product p3 = new Product("Yxa", new Money(new Currency("SEK", "kr"),
				1000), new Manufacturer("WELL"), new ProductType("Hantverk"));
		Product p4 = new Product("Yxa", new Money(new Currency("SEK", "kr"),
				1000), new Manufacturer("WELL"), new ProductType("Hantverk"));
		
		Customer c1 = new Customer("Ricardo", "Albatrosvagen 12", "0734567891",
				"321245788", new Money(new Currency("USD", "D"), 1000));
		
		c1.addProductsToCart(p1,p2,p3,p4);
		
		store.makePurchase(c1);
		assertEquals(4000,c1.getCartValue().getAmount());
	}
		

	@Test(expected = IllegalPurchaseException.class)
	public void checkIfMakePurchaseThrowsIndexOutOfBoundsException() {
		Product p1 = new Product("Mjolj", new Money(new Currency("USD", "D"),
				5), new Manufacturer("Bolaget"), new ProductType("Mejeri"));
		Product p2 = new Product("Mjolkds", new Money(
				new Currency("USD", "D"), 5), new Manufacturer("Bolaget"),
				new ProductType("Mejeri"));
		Product p3 = new Product("Mjolasdsd", new Money(new Currency("USD",
				"D"), 50), new Manufacturer("Bolaget"), new ProductType(
						"Mejeri"));
		Product p4 = new Product("Mjoljdwadefgr", new Money(new Currency("USD",
				"D"), 5), new Manufacturer("Bolaget"), new ProductType(
						"Mejeri"));

		Customer c1 = new Customer("Nils", "Hejvagen 14", "0703450217",
				"0903134567", new Money(new Currency("USD", "D"), 100));

		Purchase p = store.makePurchase(c1);
	}

	@Test
	public void checkIfAddManufacturerDiscountMethodAddsTheRightDiscount() {
		ManufacturerDiscount md = new ManufacturerDiscount(new Manufacturer("Arla"));

		ArrayList<ManufacturerDiscount> check = new ArrayList<>();
		check.add(md);
		Store s = new Store("abc");
		s.addDiscountForProduct(md);
		assertEquals(check, s.getAllManufacturerDiscount());
	}

	@Test
	public void checkIfAddManufacturerDiscountMethodReturnsFalseWhenArrayContainsThatDiscount() {
		ManufacturerDiscount md = new ManufacturerDiscount(new Manufacturer("Arla"));

		Store s = new Store("abc");
		s.addDiscountForProduct(md);
		assertEquals(false,	s.addDiscountForProduct(md));
	}

	@Test
	public void checkIfcheckForDiscountsMethodWorks() {
		Product pp1= new Product("Billigast", new Money(new Currency("SEK","kr"), 1),
				new Manufacturer("Muu.Inc"), new ProductType("Mejeri"));

		Product pp2 = new Product("Mellan", new Money(new Currency("SEK","kr"), 200),
				new Manufacturer("Muu.Inc"), new ProductType("Mejeri"));

		Product pp3 = new Product("Dyrast", new Money(new Currency("USD","D"), 10000),
				new Manufacturer("Muu.Inc"), new ProductType("Mejeri"));

		Customer c = new Customer("Nils", "Hejvagen 14", "0703450217",
				"0903134567", new Money(new Currency("USD","D"), Integer.MAX_VALUE));

		c.addProductsToCart(pp1,pp2,pp3);

		PercentageDiscount d1 = new PercentageDiscount(50, pp1);
		store.addDiscountForProduct(d1);

		Purchase pur3 = store.makePurchase(c);

	
	}
	
	@Test
	public void removeManufacturerDiscount01(){
		ManufacturerDiscount md = new ManufacturerDiscount(new Manufacturer("Arla"));
		store.addDiscountForProduct(md);
		assertEquals(true,store.removeDiscountForProduct(md));
	}
	
	@Test
	public void removeManufacturerDiscount02(){
		ManufacturerDiscount md = new ManufacturerDiscount(new Manufacturer("Arla"));
		assertEquals(false,store.removeDiscountForProduct(md));
		
	}
	
	@Test(expected=IllegalPurchaseException.class)
	public void testMakePurchase(){
		Customer c = new Customer("Nils", "Hejvagen 14", "0703450217",
				"0903134567", new Money(new Currency("USD","D"), Integer.MAX_VALUE));
		
		store.makePurchase(c);
	}

	@Test
	public void testToString() {
		String name = "No Good Goods";
		assertEquals(name, store.toString());
	}

}
