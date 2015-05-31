
public class PurchaseTestPrinter {

	public static void main(String[] args) {
		
		Store store = new Store("Much PurchaseDiscount");

		Currency cur = new Currency("USD", "D");

		// Product
		Product p1 = new Product("Mjlk 1l", new Money(cur, 10),
				new Manufacturer("Muu.Inc"), new ProductType("Mejeri"));
		Product p2 = new Product("Kindergg", new Money(cur, 15),
				new Manufacturer("Deutsche Anhalt"), new ProductType(
						"Schigkeiten"));
		Product p3 = new Product("Sourcream & Onion Chips",
				new Money(cur, 50), new Manufacturer("OWL"), new ProductType(
						"Snacks"));
		Product p4 = new Product("Kladdkaka", new Money(cur, 74),
				new Manufacturer("Bagaren"), new ProductType("Bakat"));
		Product p5 = new Product("Cola 1,5l", new Money(cur, 89),
				new Manufacturer("Liquid Sugar"), new ProductType("Dryck"));
		Product p6 = new Product("Varma Koppen", new Money(cur, 56),
				new Manufacturer("Hot in Pot"), new ProductType("Mat"));
		Product p7 = new Product("WunderBox", new Money(cur, 12),
				new Manufacturer("Wunderbare Hansa"), new ProductType("Ovrigt"));
		Product p8 = new Product("Bagar utan glas", new Money(cur, 43),
				new Manufacturer("Before it was cool.Inc"), new ProductType(
						"Hipster"));
		Product p9 = new Product("Royal with cheese", new Money(cur, 123),
				new Manufacturer("McDonk"), new ProductType("Snabbmat"));
		// Product END

		// Customer
		Customer c1 = new Customer("Nils", "Hejvgen 14", "0703450217",
				"0903134567", new Money(cur, 500));
		Customer c2 = new Customer("Karl", "Nahdatroad 84", "0706789451",
				"0905124565", new Money(cur, 500));
		// Customer END

		// PurchaseDiscount
		PercentageDiscount d1 = new PercentageDiscount(50, p3);
		PercentageDiscount d2 = new PercentageDiscount(75, p4);
		PercentageDiscount d3 = new PercentageDiscount(30, p1);
		PercentageDiscount d4 = new PercentageDiscount(40, p2);
		PercentageDiscount d5 = new PercentageDiscount(10, p5);
		// PurchaseDiscount END
		
	
		store.addDiscountForProduct(d1);
		store.addDiscountForProduct(d2);
		store.addDiscountForProduct(d3);
		store.addDiscountForProduct(d4);
		store.addDiscountForProduct(d5);

		c1.addProductsToCart(p1);
		c1.addProductsToCart(p2);
		c1.addProductsToCart(p3);
		c1.addProductsToCart(p4);

		c2.addProductsToCart(p5);
		c2.addProductsToCart(p6);
		c2.addProductsToCart(p7);
		c2.addProductsToCart(p5);
		c2.addProductsToCart(p8);
		c2.addProductsToCart(p9);

		Purchase pur1 = store.makePurchase(c1);
		Purchase pur2 = store.makePurchase(c2);
		System.out.println(pur1);
		System.out.println(pur2);
		
		Money s = new Money(new Currency("SEK","kr"),100);
		System.out.println(s.exchangeCurrency(s));
		Money d = new Money(new Currency("USD","D"), 100);
		System.out.println(d.exchangeCurrency(d));
		
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
		
		System.out.println(c.getCheapestManufacturerForProduct(new Manufacturer("Muu.Inc")));
		
		
		System.out.println(c.getCart());
		
		double d11 = 100.0 / 7.13763;
		System.out.println((int)d11);
		
		System.out.println("_________________END________________________");
		
		
		
		
	}

}
