package alda.sl;

public class Edge implements Comparable<Edge>{

	private Station dest;
	private int weight;
	
	public Edge(Station dest, int weight) {
		if (dest == null)
			throw new IllegalArgumentException("Destination can't be null");
		if (weight < 0)
			throw new IllegalArgumentException("Weight can't be a negative value");
		this.dest = dest;
		this.weight = weight;
	}
	
	public Station getDest() {
		return dest;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int newWeight) {
		if (newWeight < 0)
			throw new IllegalArgumentException("Weight can't be a negative value");
		weight = newWeight;
	}
	
	public int compareTo(Edge other) {
		return weight - other.getWeight();
	}
	
	public String toString() {
		return "Time: " + weight + " to: " + dest.toString();
	}
}
