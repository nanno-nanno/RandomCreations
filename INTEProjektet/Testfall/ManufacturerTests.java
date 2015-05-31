import static org.junit.Assert.*;
import org.junit.Test;


public class ManufacturerTests {
	
	@Test(expected = NullPointerException.class)
	public void CheckIfConstructorThrowsExceptionWhenNameIsNull() {
		
		Manufacturer manu = new Manufacturer(null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void CheckIfConstructorThrowsExceptionWhenNameIsAnEmptyString(){
		
		Manufacturer manu = new Manufacturer("");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void CheckIfSetNameThrowsException(){
		
		Manufacturer manu = new Manufacturer("test");
		manu.setName("");
	}
	
	@Test
	public void CheckIfGetNameReturnsTheRightName() {
		
		String name = "kalle & co";
		Manufacturer mf = new Manufacturer(name);
		assertEquals(mf.getName(), name);
		
	}
	@Test
	public void checkIfSetNameWorks(){
		Manufacturer mf = new Manufacturer("yolo");
	    String name = "solo";
	    mf.setName(name);
	    assertEquals(mf.getName(),name);
	}
	@Test
	public void checkIfEqualsMethodWorks(){
		Manufacturer mf = new Manufacturer("dolo");
		Manufacturer mf2 = new Manufacturer("fofo");
		assertEquals(false,mf.equals(mf2));
	}
	@Test
	public void TestToString() {
		
		Manufacturer mf = new Manufacturer("abc123");
		mf.toString();
		
	}
}
