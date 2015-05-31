import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;


public class ProductTypeTests {

	@Test (expected = NullPointerException.class)
	public void CheckIfConstructorThrowsExceptionWhenNameIsNull() {
		new ProductType(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void CheckIfConstructorThrowsExceptionWHenNameisAnEmptyString() {
		new ProductType("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void CheckIfSetNameThrowsException(){
		ProductType pt = new ProductType("test");
		
		pt.setName("");
	}
	
	@Test
	public void CheckIfGetNameReturnsTheRightName() {
		
		String name = "Glass";
		ProductType pt = new ProductType(name);
		Assert.assertEquals(pt.getName(), name);
		
	}
	@Test
	public void checkIfEqualsMethodWorks(){
		ProductType pro = new ProductType("ett");
		ProductType pro2 = new ProductType("ett");
		assertEquals(true,pro.equals(pro2));
		
	}
	
	@Test
	public void checkIfEqualsCanReturnFalse() {
		ProductType p = new ProductType("Tjabagodis");
		String s = "Hej";
		assertEquals(false, p.equals(s));
	}
	
	@Test
	public void testSetName() {
		ProductType p = new ProductType("Hej");
		p.setName("Tja");
		assertEquals(p.getName(), "Tja");
	}
	
	
	
	@Test
	public void testToString(){
		
		ProductType pt = new ProductType("Brod");
		assertEquals("Brod",pt.toString());
		
	}

}
