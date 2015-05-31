
public class SampleRun {

	public static void main(String[] args) {

		Store store = new Store("Alex Goods");

		Currency USD = new Currency("USD", "D");
		Currency SEK = new Currency("SEK", "kr");

		ProductType pt1 = new ProductType("Mejeri");
		ProductType pt2 = new ProductType("Godis");
		ProductType pt3 = new ProductType("Klader");
		ProductType pt4 = new ProductType("Verktyg");

		Manufacturer m1 = new Manufacturer("ARLA"); // Finns discount for
		Manufacturer m2 = new Manufacturer("COOP"); // Finns discount for
		Manufacturer m3 = new Manufacturer("BEAR");
		Manufacturer m4 = new Manufacturer("IKEA");

		Money wallet1 = new Money(USD, 10000); // 10000 Dollar
		Money wallet2 = new Money(SEK, 100000); // 100000 Svenska kronor // Anvands inte just nu...
		Money price1 = new Money(USD, 50);
		Money price2 = new Money(USD, 100);
		Money price3 = new Money(SEK, 50);
		Money price4 = new Money(SEK, 100);

		Product p1 = new Product("Ost", price1, m1, pt1); // manD
		Product p2 = new Product("Klubba", price2, m2, pt2); // manD
		Product p3 = new Product("Skjorta", price3, m3, pt3);
		Product p4 = new Product("Hammare", price4, m4, pt4);
		Product p5 = new Product("Kvarg", price1, m1, pt1); // Har perDis och manDis - X
		Product p6 = new Product("Colanapp", price2, m2, pt2); // Har perDis och manDis - X
		Product p7 = new Product("Byxor", price3, m3, pt3); // Har discount
		Product p8 = new Product("Borr", price4, m4, pt4); // Har discount

		PercentageDiscount perd1 = new PercentageDiscount(10, p5); // Galler Kvarg
		PercentageDiscount perd2 = new PercentageDiscount(25, p6); // Galler Colanapp
		PercentageDiscount perd3 = new PercentageDiscount(50, p7); // Galler Byxor
		PercentageDiscount perd4 = new PercentageDiscount(75, p8); // Galler Borr

		ManufacturerDiscount mand1 = new ManufacturerDiscount(m1); // Galler ARLA : Ost, Kvarg
		ManufacturerDiscount mand2 = new ManufacturerDiscount(m2); // Galler COOP : Klubba, Colanapp

		Customer c1 = new Customer("Nils", "Gata 1", "070", "123", wallet1);
		Customer c2 = new Customer("Karl", "Gata 2", "073", "456", wallet2);

		store.addDiscountForProduct(perd1);
		store.addDiscountForProduct(perd2);
		store.addDiscountForProduct(perd3);
		store.addDiscountForProduct(perd4);
		
		store.addDiscountForProduct(mand1);
		store.addDiscountForProduct(mand2);

		c1.addProductsToCart(p1, p2, p3, p4, p5, p6, p7, p8); // Godkand, en vara har rabatt
		c2.addProductsToCart(p1, p3); // Godkand, en vara har 2 rabatter

		Purchase pur1 = store.makePurchase(c1); // Funkar, men har inte tre varor fran samma Manufacturer?
		Purchase pur2 = store.makePurchase(c2); // Kastar ArrayIndexOutOfBoundsException(-1)

		System.out.println(pur1 + "\n" + pur2);
	}

}
