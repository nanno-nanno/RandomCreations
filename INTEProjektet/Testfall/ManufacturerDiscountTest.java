import static org.junit.Assert.*;

import org.junit.Test;


public class ManufacturerDiscountTest {

	@Test(expected = NullPointerException.class)
	public void CheckIfConstructorThrowsExceptionWhenManufacturerIsNull() {
		new ManufacturerDiscount(null);
	}
	
	@Test
	public void checkIfgetManufacturerReturnsTheRightManufacturer() {
		
		Manufacturer m = new Manufacturer("Arla");
		ManufacturerDiscount md = new ManufacturerDiscount(m);
		
		assertEquals(m, md.getManufacturer());
	}
	
	@Test
	public void testEquals(){
		ManufacturerDiscount m = new ManufacturerDiscount(new Manufacturer("Arla"));
		assertEquals(true,m.equals(m));
	}
	
	@Test
	public void checkIfToStringWorks(){

		Manufacturer m = new Manufacturer("Arla");
		
		
		ManufacturerDiscount md = new ManufacturerDiscount(m);
		assertEquals("Arla", md.toString());
	}

}
