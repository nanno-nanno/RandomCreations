import static org.junit.Assert.*;

import org.junit.Test;


public class CurrencyTest {

	@Test(expected=IllegalArgumentException.class)
	public void testCurrencyCodeConstructorException01(){
		Currency c = new Currency("SEEEEEEEEEEK","kr");
	}
	@Test(expected=IllegalArgumentException.class)
	public void testCurrencyCodeConstructorException02(){
		Currency c = new Currency("","kr");
	}
		
	@Test(expected=IllegalArgumentException.class)
	public void testCurrencySymbolConstructorException01(){
		Currency c = new Currency("USD","$$$$$$$$$$$$$$$$$$");
	}
	@Test(expected=IllegalArgumentException.class)
	public void testCurrencySymbolConstructorException02(){
		Currency c = new Currency("USD","");
	}
	
	@Test
	public void testCurrencyGetCode(){
		Currency c = new Currency("SEK","kr");
		assertEquals(c.getCode(),"SEK");
	}
	@Test
	public void testCurrencyGetSymbol(){
		Currency c = new Currency("USD","D");
		assertEquals("D",c.getSymbol());
	}
	
	@Test
	public void checkIfEqualsReturnsTrueWhenEquals() {
		Currency c1 = new Currency("USD", "D");
		Currency c2 = new Currency("USD", "D");
		assertEquals(true, c1.equals(c2));
	}
	
	@Test
	public void checkIfEqualsReturnsFalseWhenCodeIsNotEquals() {
		Currency c1 = new Currency("USD", "kr");
		Currency c2 = new Currency("SEK", "kr");
		assertEquals(false, c1.equals(c2));
	}
	@Test
	public void checkIfEqualsReturnsfalseWhenSymbolIsNotEquals(){
		Currency c1 = new Currency("USD", "kr");
		Currency c2 = new Currency("USD", "D");
		assertEquals(false, c1.equals(c2));
	}
	
	@Test
	public void testToString(){
		Currency c = new Currency("SEK","kr");
		c.toString();
		assertEquals(c.getCode()+", "+c.getSymbol(),c.toString());
	}
}
