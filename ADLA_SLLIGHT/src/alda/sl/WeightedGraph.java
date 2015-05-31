package alda.sl;

import java.util.List;

public interface WeightedGraph {

	void add(Station node);
	
	void remove(Station node);
	
	void connect(Station from, Station to, int weight);
	
	void disconnect(Station from, Station to);
	
	List<Station> getNodes();
	
	List<Edge> getEdgesFrom(Station node);
	
	Edge getEdgeBetween(Station from, Station to);
	
}
