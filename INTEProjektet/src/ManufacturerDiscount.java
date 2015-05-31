
public class ManufacturerDiscount implements Discount {

	private Manufacturer m;
	
	public ManufacturerDiscount(Manufacturer m) {
		if(m == null)
			throw new NullPointerException("Manufacturer cannot be null.");
		this.m = m;
	}
	
	public Manufacturer getManufacturer() {
		return m;
	}
	
	public boolean equals(ManufacturerDiscount mf){
		return m.getName().equals(mf.getManufacturer().getName());
	}
	
	public String toString() {
		return m.toString();
	}

}
