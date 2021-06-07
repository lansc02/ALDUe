package A11_DijkstraPQShortestPath;

public class WeightedEdge implements Comparable<WeightedEdge>{
	public int from_vertex;
	public int to_vertex;
	public int weight;

	public WeightedEdge(int from_vertex, int to_vertex, int weight) {
		this.from_vertex = from_vertex;
		this.to_vertex = to_vertex;
		this.weight = weight;
	}



	@Override
	public int compareTo(WeightedEdge weightedEdge) {
		return Integer.compare(this.weight, weightedEdge.weight);
	}
}
