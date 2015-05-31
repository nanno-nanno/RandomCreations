
public class ProductType {

	private String name;

	public ProductType(String name) {

		if (name == null)
			throw new NullPointerException("Name cannot be null");
		if (name.equals(""))
			throw new IllegalArgumentException("Name cannot be an empty string");

		this.name = name;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name.equals(""))
			throw new IllegalArgumentException("Name cannot be null.");
		this.name = name;
	}
	
	
	public boolean equals(ProductType prod) {
		return this.getName().equals(prod.getName());
	}

	public String toString() {
		return name;
	}
}
