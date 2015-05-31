package alda.sl;

public class SLLight {

	public static void main(String[] args){
		MyWeightedGraph graph = new MyWeightedGraph();
		Station Kallhäll = new Station("Kallhäll Station");
		Station Termovägen = new Station("Termovägen");
		Station Malmvägen = new Station("Malmvägen");
		Station Rotebroleden = new Station("Rotebroleden");
		Station NorraStäket = new Station("Norra Stäket");
		Station Stäket = new Station("Stäket(Kajsas Hof)");
		
		graph.add(Kallhäll);
		graph.add(Termovägen);
		graph.add(Malmvägen);
		graph.add(Rotebroleden);
		graph.add(NorraStäket);
		graph.add(Stäket);
		
		graph.connect(Kallhäll, Termovägen, 5);
		graph.connect(Termovägen, Malmvägen, 4);
		graph.connect(Malmvägen, Rotebroleden, 2);
		graph.connect(Rotebroleden, NorraStäket, 3);
		graph.connect(NorraStäket, Stäket, 3);
		
		System.out.println(graph.toString());
	}
	
}
