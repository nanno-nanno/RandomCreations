
public class Manufacturer {

	private String name;
	
	public Manufacturer(String name){
		if(name == null)
			throw new NullPointerException("Name cannot be a null value");
		if(name.equals(""))
			throw new IllegalArgumentException("Name cannot be an empty string");
		
		this.name = name;
		
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		if (name.equals(""))
			throw new IllegalArgumentException("Name cannot be an empty string");
		this.name = name;
	}
	
	public boolean equals(Manufacturer obj){
		return this.getName().equals(obj.getName());
	}
	public String toString() {
		return name;
	}
	
}
