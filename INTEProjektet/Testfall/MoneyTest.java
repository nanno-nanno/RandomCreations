import static org.junit.Assert.*;

import org.junit.Test;


public class MoneyTest {

	@Test(expected=IllegalArgumentException.class)
	public void testMoneyConstructorAmountException01(){
		new Money(new Currency("SEK","kr"), -1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMoneyConstructorCurrencyException() {
		new Money(new Currency("SEEEEEEEEK","kr"), 20); 
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testExchangeException(){
		Money m = new Money(new Currency("Tes","kra"), 14);
		m = m.exchangeCurrency(m);
	}
	@Test(expected = NullPointerException.class)
	public void CheckIfConstructorThrowsExceptionWhenCurrencyIsNull() {
		new Money(null, 2200);
		
	}
	@Test
	public void testMoneyExchange1(){
		Money m = new Money(new Currency("SEK","kr"), 1000);
		m = m.exchangeCurrency(m);
		
		assertEquals(new Money(new Currency("USD","D"),137).toString(),m.toString());
	}
	
	@Test
	public void testMoneyExchange2(){
		Money m = new Money(new Currency("USD","D"), 2);
		m = m.exchangeCurrency(m);
		
		assertEquals(new Money(new Currency("SEK","kr"),14).toString(),m.toString());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testMoneyExchangeException(){
		Money m = new Money(new Currency("TEST","TEST"), 14);
		m.exchangeCurrency(m);
	}
	
	@Test
	public void testMoneyGetCurrency(){
		Currency c = new Currency("SEK","kr");
		Money m = new Money(c, 10);
		assertEquals(c,m.getCurrency());
	}
	
	@Test
	public void checkIfEqualsReturnsFalseWhenNotEquals() {
		Currency c1 = new Currency("USD", "D");
		Money m1 = new Money(c1, 15);
		Currency c2 = new Currency("SEK", "kr");
		Money m2 = new Money(c2, 25);
		assertEquals(false, m1.equals(m2));
	}
	
	@Test
	public void checkIfEqualsReturnsTrueWhenEquals01() {
		Currency c = new Currency("USD", "D");
		Money m1 = new Money (c, 551);
		Money m2 = new Money (c, 551);
		assertEquals(true, m1.equals(m2));
	}
	
	@Test
	public void checkIfEqualsReturnsTrueWhenEquals02() {
		Currency c = new Currency("USD", "D");
		Money m1 = new Money (c, 250);
		Money m2 = new Money (c, 551);
		assertEquals(false, m1.equals(m2));
	}
	
	@Test
	public void testMoneyGetAmount(){
		Money m = new Money(new Currency("SEK","kr"), 10);
		assertEquals(10.0,m.getAmount(),1e-15);
	}
}
