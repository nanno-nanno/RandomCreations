import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CustomerTests {


	@Test(expected = NullPointerException.class)
	public void CheckIfConstructorThrowsExceptionWhenNameIsNull() {
		Money m = new Money(new Currency("SEK", "kr"), 2200);
		new Customer(null, "abcvagen 17", "1235647", "17768534-3404", m);
	}

	@Test(expected = IllegalArgumentException.class)
	public void CheckIfConstructorThrowsExceptionWhenNameIsAnEmptyString() {
		Money m = new Money(new Currency("SEK", "kr"), 2200);
		new Customer("", "abcvagen 17", "1235647", "17768534-3404", m);
	}

	@Test(expected = NullPointerException.class)
	public void CheckIfConstructorThrowsExceptionWhenAddressIsNull() {
		Money m = new Money(new Currency("SEK", "kr"), 2200);
		new Customer("Jonas", null, "1235647", "17768534-3404", m);
	}

	@Test(expected = IllegalArgumentException.class)
	public void CheckIfConstructorThrowsExceptionWhenAddressIsAnEmptyString() {
		Money m = new Money(new Currency("SEK", "kr"), 2200);
		new Customer("Jonas", "", "1235647", "17768534-3404", m);
	}

	@Test(expected = NullPointerException.class)
	public void CheckIfConstructorThrowsExceptionWhenPhoneNumberIsNull() {
		Money m = new Money(new Currency("SEK", "kr"), 2200);
		new Customer("Jonas", "abcvagen 17", null, "17768534-3404", m);
	}

	@Test(expected = IllegalArgumentException.class)
	public void CheckIfConstructorThrowsExceptionWhenPhoneNumberIsAnEmptyString() {
		Money m = new Money(new Currency("SEK", "kr"), 2200);
		new Customer("Jonas", "abcvagen 17", "", "17768534-3404", m);
	}

	@Test(expected = NullPointerException.class)
	public void CheckIfConstructorThrowsExceptionWhenPnrIsNull() {
		Money m = new Money(new Currency("SEK", "kr"), 2200);
		new Customer("Jonas", "abcvagen 17", "1235647", null, m);
	}

	@Test(expected = IllegalArgumentException.class)
	public void CheckIfConstructorThrowsExceptionWhenPnrIsAnEmptyString() {
		Money m = new Money(new Currency("SEK", "kr"), 2200);
		new Customer("Jonas", "abcvagen 17", "1235647", "", m);
	}
	
	@Test(expected = NullPointerException.class)
	public void CheckIfConstructorThrowsExceptionWhenCashIsNull() {
		new Customer("Jonas", "abcvagen 17", "1235647", "765433-3456", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void CheckifSetnameThrowsException01() {
		Money m = new Money(new Currency("SEK", "kr"), 2200);
		Customer c = new Customer("Jonas", "abcvagen 17", "1235647", "0000000", m);

		c.setName("");
	}


	@Test(expected = IllegalArgumentException.class)
	public void CheckifSetaddressThrowsException01() {
		Money m = new Money(new Currency("SEK", "kr"), 2200);
		Customer c = new Customer("Jonas", "abcvagen 17", "1235647", "0000000", m);

		c.setAddress("");
	}


	@Test(expected = IllegalArgumentException.class)
	public void CheckifSetphoneNumberThrowsException01() {
		Money m = new Money(new Currency("SEK", "kr"), 2200);
		Customer c = new Customer("Jonas", "abcvagen 17", "1235647", "0000000", m);

		c.setPhoneNumber("");
	}


	@Test(expected = IllegalArgumentException.class)
	public void CheckifSetPnrThrowsException01() {
		Money m = new Money(new Currency("SEK", "kr"), 2200);
		Customer c = new Customer("Jonas", "abcvagen 17", "1235647", "0000000", m);

		c.setPnr("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void CheckifaddProductsToCartThrowsException(){
		Money m = new Money(new Currency("SEK", "kr"), 2200);
		Customer c = new Customer("Jonas", "abcvagen 17", "1235647", "0000000", m);
		Product[] prodarr = {};
		
		c.addProductsToCart(prodarr);
	}
	
	@Test
	public void CheckgetCartValue01(){
		
		Money m = new Money(new Currency("SEK", "kr"), 2200);
		Customer c = new Customer("Jonas", "abcvagen 17", "1235647", "0000000", m);
		Manufacturer mf = new Manufacturer("Bolaget");
		
		Product p1 = new Product("Mjolk", new Money(new Currency("SEK","kr"),
				5), mf, new ProductType("Mejeri"));
		Product p2 = new Product("Mjolk", new Money(new Currency("SEK","kr"),
				3), mf, new ProductType("Mejeri"));
		Product p3 = new Product("Mjolk", new Money(new Currency("SEK","kr"),
				10), mf, new ProductType("Mejeri"));
		
		c.addProductsToCart(p1,p2,p3);
		
		Money m1 = new Money(new Currency("SEK","kr"),18);
		assertEquals(m1.toString(),c.getCartValue().toString());
	}
	
	@Test
	public void CheckgetCartValue02(){
		
		Money m = new Money(new Currency("SEK", "kr"), 2200);
		Customer c = new Customer("Jonas", "abcvagen 17", "1235647", "0000000", m);
		Manufacturer mf = new Manufacturer("Bolaget");
		
		Product p1 = new Product("Mjolk", new Money(new Currency("USD","D"),
				5), mf, new ProductType("Mejeri"));
		Product p2 = new Product("Mjolk", new Money(new Currency("USD","D"),
				3), mf, new ProductType("Mejeri"));
		Product p3 = new Product("Mjolk", new Money(new Currency("USD","D"),
				10), mf, new ProductType("Mejeri"));
		
		c.addProductsToCart(p1,p2,p3);
		
		Money m1 = new Money(new Currency("SEK","kr"),126);
		assertEquals(m1.toString(),c.getCartValue().toString());
	}

	@Test
	public void CheckgetCheapestManufacturerProduct01(){
		Manufacturer mf = new Manufacturer("Bolaget");
		
		Product p1 = new Product("Mjolk", new Money(new Currency("SEK","kr"),
				5), mf, new ProductType("Mejeri"));
		Product p2 = new Product("Mjolk", new Money(new Currency("SEK","kr"),
				3), mf, new ProductType("Mejeri"));
		Product p3 = new Product("Mjolk", new Money(new Currency("SEK","kr"),
				10), mf, new ProductType("Mejeri"));
		
		Money m = new Money(new Currency("SEK", "kr"), 2200);
		Customer c = new Customer("Jonas", "abcvagen 17", "1235647", "0000000", m);
		
		c.addProductsToCart(p1,p2,p3);
		
		assertEquals(p2,c.getCheapestManufacturerForProduct(mf));
		
	}
	
	@Test
	public void CheckgetCheapestManufacturerProduct02(){
		Manufacturer mf = new Manufacturer("Bolaget");
		
		Product p1 = new Product("Mjolk", new Money(new Currency("USD","D"),
				5), mf, new ProductType("Mejeri"));
		Product p2 = new Product("Mjolk", new Money(new Currency("USD","D"),
				3), mf, new ProductType("Mejeri"));
		Product p3 = new Product("Mjolk", new Money(new Currency("USD","D"),
				10), mf, new ProductType("Mejeri"));
		Product p4 = new Product("Mjolk", new Money(new Currency("USD","D"),
				10), new Manufacturer("test"), new ProductType("Mejeri"));
		
		Money m = new Money(new Currency("SEK", "kr"), 2200);
		Customer c = new Customer("Jonas", "abcvagen 17", "1235647", "0000000", m);
		
		c.addProductsToCart(p1,p2,p3,p4);
		
		assertEquals(p2,c.getCheapestManufacturerForProduct(mf));
		
	}

	@Test
	public void CheckIfGetNameReturnsTheRightName() {
		String name = "lisa";
		Customer c = new Customer(name, "abcvagen 17", "1234567", "12394-5909",
				new Money(new Currency("SEK", "kr"), 2200));
		assertEquals(c.getName(), name);
	}

	@Test
	public void CheckIfReturnedAddressIsgetAddress() {
		// return address;
		String checker = "abcvagen 17";
		Customer c = new Customer("Nils", "abcvagen 17", "1234567",
				"12394-5909", new Money(new Currency("SEK", "kr"), 2200));
		assertEquals(c.getAddress(), checker);
	}

	@Test
	public void CheckIfReturnedPhoneNumberIsgetPhoneNumber() {
		// return phoneNumber;
		String checker = "1234567";
		Customer c = new Customer("Nils", "abcvagen 17", "1234567",
				"12394-5909", new Money(new Currency("SEK", "kr"), 2200));
		assertEquals(c.getPhoneNumber(), checker);
	}

	@Test
	public void CheckIfReturnedPnrIsgetPnr() {
		// return pnr;
		String checker = "12394-5909";
		Customer c = new Customer("Nils", "abcvagen 17", "1234567",
				"12394-5909", new Money(new Currency("SEK", "kr"), 2200));
		assertEquals(c.getPnr(), checker);
	}

	@Test
	public void CheckIfReturnedCashIsgetCash() {
		// return cash;
		Money checker = new Money(new Currency("SEK", "kr"), 2200);
		Customer c = new Customer("Nils", "abcvagen 17", "1234567",
				"12394-5909", checker);
		assertEquals(c.getCash(), checker);
	}

	@Test
	public void CheckIfReturnedCartIsgetCart() {
		// return cart;
		Product p1 = new Product("Mjolk", new Money(new Currency("USD", "D"),
				5), new Manufacturer("Bolaget"), new ProductType("Mejeri"));
		Product p2 = new Product("Mjolk", new Money(
				new Currency("USD", "D"), 5), new Manufacturer("Bolaget"),
				new ProductType("Mejeri"));
		Product p3 = new Product("Mjolk", new Money(new Currency("USD",
				"D"), 50), new Manufacturer("Bolaget"), new ProductType(
						"Mejeri"));
		Product p4 = new Product("Mjolk", new Money(new Currency("USD",
				"D"), 5), new Manufacturer("Bolaget"), new ProductType(
						"Mejeri"));
		ArrayList<Product> checkerCart = new ArrayList<>();
		checkerCart.add(p1);
		checkerCart.add(p2);
		checkerCart.add(p3);
		checkerCart.add(p4);
		ArrayList<Product> realCart = new ArrayList<>();
		Customer c = new Customer("Nils", "abcvagen 17", "1234567",
				"12394-5909", new Money(new Currency("SEK", "kr"), 2200));
		c.addProductsToCart(p1,p2,p3,p4);
		assertEquals(c.getCart(), checkerCart);
	}

	@Test
	public void CheckIfSetNameSetsTheRightName() {
		String name = "Jakob";
		Customer c = new Customer("Nils", "abcvagen 17", "1234567",
				"12394-5909", new Money(new Currency("SEK", "kr"), 2200));
		c.setName(name);
		assertEquals(c.getName(), name);
	}

	@Test
	public void checkIfSetAddressSetsTheRightAdress(){
		String address = "abc 12";
		Customer c = new Customer("Nils", "abcvagen 17", "1234567",
				"12394-5909", new Money(new Currency("SEK", "kr"), 2200));
		c.setAddress(address);
		assertEquals(c.getAddress(), address);
	}

	@Test
	public void CheckIfSetPhoneNumberSetsTheRightPhoneNumber() {
		String phoneNumber = "7654321";
		Customer c = new Customer("Nils", "abcvagen 17", "1234567",
				"12394-5909", new Money(new Currency("SEK", "kr"), 2200));
		c.setPhoneNumber(phoneNumber);
		assertEquals(c.getPhoneNumber(), phoneNumber);
	}

	@Test
	public void setPnr() {
		String pnr = "55663-1234";
		Customer c = new Customer("Nils", "abcvagen 17", "1234567",
				"12394-5909", new Money(new Currency("SEK", "kr"), 2200));
		c.setPnr(pnr);
		assertEquals(c.getPnr(), pnr);
	}

	@Test
	public void checkIfSetCashSetsTheRightCash() {
		Money money = new Money(new Currency("SEK", "kr"), 110);
		Customer c = new Customer("Nils", "abcvagen 17", "1234567",
				"12394-5909", new Money(new Currency("SEK", "kr"), 2200));
		c.setCash(money);
		assertEquals(c.getCash(), money);
	}

	@Test
	public void checkIfEqualsReturnsFalseWhenNameNotEquals() {
		Customer c1 = new Customer("Nisse", "abcvagen 17", "1234567",
				"12394-5909", new Money(new Currency("SEK", "kr"), 2200));

		Customer c2 = new Customer("Nils", "abcvagen 17", "1234567",
				"12394-5909", new Money(new Currency("SEK", "kr"), 2200));
		assertEquals(c1.equals(c2), false);
	}

	@Test
	public void checkIfEqualsReturnsFalseWhenAddressNotEquals() {
		Customer c1 = new Customer("Nils", "defvagen 17", "1234567",
				"12394-5909", new Money(new Currency("SEK", "kr"), 2200));

		Customer c2 = new Customer("Nils", "abcvagen 17", "1234567",
				"12394-5909", new Money(new Currency("SEK", "kr"), 2200));
		assertEquals(c1.equals(c2), false);
	}

	@Test
	public void checkIfEqualsReturnsFalseWhenPhoneNumberNotEquals() {
		Customer c1 = new Customer("Nils", "abcvagen 17", "7654321",
				"12394-5909", new Money(new Currency("SEK", "kr"), 2200));

		Customer c2 = new Customer("Nils", "abcvagen 17", "1234567",
				"12394-5909", new Money(new Currency("SEK", "kr"), 2200));
		assertEquals(c1.equals(c2), false);
	}

	@Test
	public void checkIfEqualsReturnsFalseWhenPnrNotEquals() {
		Customer c1 = new Customer("Nils", "abcvagen 17", "1234567",
				"12394-5909", new Money(new Currency("SEK", "kr"), 2200));

		Customer c2 = new Customer("Nils", "abcvagen 17", "1234567",
				"55553-1234", new Money(new Currency("SEK", "kr"), 2200));
		assertEquals(c1.equals(c2), false);
	}

	@Test
	public void checkIfEqualsReturnsFalseWhenMoneyNotEquals() {
		Customer c1 = new Customer("Nils", "abcvagen 17", "1234567",
				"12394-5909", new Money(new Currency("SEK", "kr"), 2200));

		Customer c2 = new Customer("Nils", "abcvagen 17", "1234567",
				"12394-5909", new Money(new Currency("USD", "D"), 2200));
		assertEquals(c1.equals(c2), false);
	}

	@Test
	public void checkIfEqualsReturnsFalseWhenCartNotEquals() {
		Customer c1 = new Customer("Nils", "abcvagen 17", "1234567",
				"12394-5909", new Money(new Currency("SEK", "kr"), 2200));

		Customer c2 = new Customer("Nils", "abcvagen 17", "1234567",
				"12394-5909", new Money(new Currency("SEK", "kr"), 2200));
		Product p = new Product("Mjolk", new Money(new Currency("USD", "D"), 150), new Manufacturer("Kalles co"), new ProductType("abc"));
		c2.addProductsToCart(p);
		assertEquals(c1.equals(c2), false);
		assertEquals(c1.getCart(),c1.getCart());
	}

	@Test
	public void checkIfEqualsReturnsTrueWhenEquals() {
		Customer c1 = new Customer("Nisse", "defvagen 17", "1234567",
				"15394-5909", new Money(new Currency("SEK", "kr"), 2200));
		Customer c2 = new Customer("Nisse", "defvagen 17", "1234567",
				"15394-5909", new Money(new Currency("SEK", "kr"), 2200));
		assertEquals(true, c1.equals(c2));
	}

	@Test
	public void testChangePriceOnProduct01() {
		Product p1 = new Product("Mjalj", new Money(new Currency("USD", "D"),
				5), new Manufacturer("Bolaget"), new ProductType("Mejeri"));
		Product p2 = new Product("Mjalkds", new Money(
				new Currency("USD", "D"), 5), new Manufacturer("Bolaget"),
				new ProductType("Mejeri"));
		Customer c1 = new Customer("Nisse", "defvagen 17", "1234567",
				"15394-5909", new Money(new Currency("SEK", "kr"), 2200));
		c1.addProductsToCart(p1, p2);
		c1.changePriceForProductInCart(p1, new Money(new Currency("USD", "D"), 3));
		Product p3 = c1.getCart().get(0);
		assertEquals(p3.getPrice().getAmount(), new Money(new Currency("USD", "D"), 3).getAmount());

	}
	
	@Test
	public void testChangePriceOnProduct02() {
		Money m = new Money(new Currency("SEK", "kr"), 2200);
		
		Product p1 = new Product("Mjalj", new Money(new Currency("USD", "D"),
				5), new Manufacturer("Bolaget"), new ProductType("Mejeri"));
		Product p2 = new Product("Mjalkds", new Money(
				new Currency("USD", "D"), 5), new Manufacturer("Bolaget"),
				new ProductType("Mejeri"));
		Customer c1 = new Customer("Nisse", "defvagen 17", "1234567",
				"15394-5909", new Money(new Currency("SEK", "kr"), 2200));
		c1.addProductsToCart(p2);
		
		assertEquals(false,c1.changePriceForProductInCart(p1, m));

	}

	@Test
	public void checkIfAddProductToCartAddsProduct() {
		// cart.add(product);

		// cart.clear();
		Product p1 = new Product("Mjalj", new Money(new Currency("USD", "D"),
				5), new Manufacturer("Bolaget"), new ProductType("Mejeri"));
		Product p2 = new Product("Mjalkds", new Money(
				new Currency("USD", "D"), 5), new Manufacturer("Bolaget"),
				new ProductType("Mejeri"));
		Product p3 = new Product("Mjalasdsd", new Money(new Currency("USD",
				"D"), 50), new Manufacturer("Bolaget"), new ProductType(
						"Mejeri"));

		Product p4 = new Product("Mjaljdwadefgr", new Money(new Currency("USD",
				"D"), 5), new Manufacturer("Bolaget"), new ProductType(
						"Mejeri"));
		Customer c = new Customer("Nils", "abcvagen 17", "1234567",
				"12394-5909", new Money(new Currency("SEK", "kr"), 2200));
		c.addProductsToCart(p1,p2,p3,p4);
		c.addProductsToCart(p1);

		assertEquals(c.getCart().get(0), p1);
		assertEquals(c.getCart().get(1), p2);
		assertEquals(c.getCart().get(2), p3);
		assertEquals(c.getCart().get(3), p4);
	}

	@Test
	public void checkIfReturnedCartIsclear() {

		Product p1 = new Product("Mjalj", new Money(new Currency("USD", "D"),
				5), new Manufacturer("Bolaget"), new ProductType("Mejeri"));
		Product p2 = new Product("Mjalkds", new Money(
				new Currency("USD", "D"), 5), new Manufacturer("Bolaget"),
				new ProductType("Mejeri"));
		Product p3 = new Product("Mjalasdsd", new Money(new Currency("USD",
				"D"), 50), new Manufacturer("Bolaget"), new ProductType(
						"Mejeri"));
		Product p4 = new Product("Mjaljdwadefgr", new Money(new Currency("USD",
				"D"), 5), new Manufacturer("Bolaget"), new ProductType(
						"Mejeri"));
		Customer c = new Customer("Nils", "abcvagen 17", "1234567",
				"12394-5909", new Money(new Currency("SEK", "kr"), 2200));
		c.addProductsToCart(p1,p2,p3,p4);

		c.clearCart();
		assertEquals(c.getCart(), new ArrayList<>());

	}

	@Test
	public void testIfRemoveProductFromCartRemovesRightProduct() {

		Product p1 = new Product("Mjalj", new Money(new Currency("USD", "D"),
				5), new Manufacturer("Bolaget"), new ProductType("Mejeri"));
		Product p2 = new Product("Mjalkds", new Money(
				new Currency("USD", "D"), 5), new Manufacturer("Bolaget"),
				new ProductType("Mejeri"));
		Product p3 = new Product("Mjalasdsd", new Money(new Currency("USD",
				"D"), 50), new Manufacturer("Bolaget"), new ProductType(
						"Mejeri"));
		Customer c = new Customer("Nils", "abcvagen 17", "1234567",
				"12394-5909", new Money(new Currency("SEK", "kr"), 2200));

		c.addProductsToCart(p1,p2,p3);

		c.removeProductFromCart(p2);
		assertEquals(c.getCart().contains(p2), false);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testIfMethodRemoveProductsFromCartThrowsExceptionWhenProductIsNotInCart() {

		Product p1 = new Product("Mjalj", new Money(new Currency("USD", "D"),
				5), new Manufacturer("Bolaget"), new ProductType("Mejeri"));
		Product p2 = new Product("Mjalkds", new Money(
				new Currency("USD", "D"), 5), new Manufacturer("Bolaget"),
				new ProductType("Mejeri"));
		Product p3 = new Product("Mjalasdsd", new Money(new Currency("USD",
				"D"), 50), new Manufacturer("Bolaget"), new ProductType(
						"Mejeri"));
		Customer c = new Customer("Nils", "abcvagen 17", "1234567",
				"12394-5909", new Money(new Currency("SEK", "kr"), 2200));

		c.addProductsToCart(p1,p3);

		c.removeProductFromCart(p2);
	}

	@Test
	public void testToString() {
		// return "Name: " + name + ", Adress: " + address + ", Phonenumber: "
		// + phoneNumber + ", SSN: " + pnr;
		Customer c = new Customer("Nils", "abcvagen 17", "1234567",
				"12394-5909", new Money(new Currency("SEK", "kr"), 2200));
		String checker = "Name: " + "Nils" + ", Adress: " + "abcvagen 17"
				+ ", Phonenumber: " + "1234567" + ", SSN: " + "12394-5909";
		assertEquals(c.toString(), checker);
	}

}
