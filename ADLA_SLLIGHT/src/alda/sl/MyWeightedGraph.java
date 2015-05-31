package alda.sl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyWeightedGraph implements WeightedGraph {
	
	Map<Station, List<Edge>> data = new HashMap<Station, List<Edge>>();

	@Override
	public void add(Station node) {
		if(!data.containsKey(node))
			data.put(node, new ArrayList<Edge>());
	}

	@Override
	public void remove(Station node) {
		if(!data.containsKey(node))
			throw new IllegalArgumentException("The node to remove must exist in graph in order to remove.");
		if(data.size()> 1){
			for(Edge edge : getEdgesFrom(node))
				disconnect(node, edge.getDest());
		}
		data.remove(node);
	}

	@Override
	public void connect(Station from, Station to, int weight) {
		if (!data.containsKey(from) || !data.containsKey(to))
			throw new IllegalArgumentException("From and to must exist in graph in order to connect");
		if (weight < 1)
			throw new IllegalArgumentException("Weight can't be a negative value");
		if (getEdgeBetween(from, to) != null) {
			for (Edge e : data.get(from))
				if (e.getDest().equals(to))
					e.setWeight(weight);
			for (Edge e : data.get(to))
				if (e.getDest().equals(from))
					e.setWeight(weight);
		}
		else {
			data.get(from).add(new Edge(to, weight));
			data.get(to).add(new Edge(from, weight));
		}
	}

	@Override
	public void disconnect(Station from, Station to) {
		if (!data.containsKey(from) || !data.containsKey(to))
			throw new IllegalArgumentException("From and to must exist in graph in order to connect");
		List <Edge> edges = getEdgesFrom(from);
		edges.remove(getEdgeBetween(from,to));
		data.put(from, edges);
		
		edges = getEdgesFrom(to);
		edges.remove(getEdgeBetween(to, from));
		data.put(to, edges);
	}

	@Override
	public List<Station> getNodes() {
		ArrayList<Station> nodes = new ArrayList<Station>();
		for (Station node : data.keySet())
			nodes.add(node);
		return nodes;
	}

	@Override
	public List<Edge> getEdgesFrom(Station node) {
		if (node == null || !data.containsKey(node))
			throw new IllegalArgumentException("Node can't be null and must exist in graph");
		return new ArrayList<Edge>(data.get(node));
	}

	@Override
	public Edge getEdgeBetween(Station from, Station to) {
		if (from == null || to == null)
			throw new IllegalArgumentException("From and to can't be null");
		if (!data.containsKey(from) || !data.containsKey(to))
			throw new IllegalArgumentException("From and to must exist in graph");
		for (Edge e : data.get(from))
			if (e.getDest().equals(to))
				return e;
		return null;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (Station node : data.keySet()) {
			str.append(node.toString() + "\n");
			for (Edge e : data.get(node))
				str.append("- " + e.toString() + "\n");
		}
		return str.toString();
	}

}
