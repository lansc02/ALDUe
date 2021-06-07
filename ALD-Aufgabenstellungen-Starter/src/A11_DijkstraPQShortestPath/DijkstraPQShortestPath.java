package A11_DijkstraPQShortestPath;

import java.util.List;

public class DijkstraPQShortestPath extends FindWay {
    private int[] dist;

    public DijkstraPQShortestPath(Graph graph) {
        super(graph);
    }

    /**
     * Startentfernung initialisieren
     */
    protected void initPathSearch() {
        int numv = graph.numVertices();
        dist = new int[numv];
        for (int i = 0; i < numv; i++) {
            dist[i] = 9999; // Summen im Graph dürfen nie mehr ergeben
        }
    }

    /**
     * Berechnet *alle* kürzesten Wege ausgehend vom Startknoten Setzt dist[]-
     * und pred[]-Arrays, kein Rückgabewert
     *
     * @param from Startknoten
     */
    protected boolean calculatePath(int from, int to) {

        // TODO: IHRE IMPLEMENTIERUNG

        for (int i = 0; i < dist.length; i++) {
            pred[i] = -1;
        }

        VertexHeap PQ = new VertexHeap();
        dist[from] = 0;

        PQ.insert(new Vertex(from, 0));

        while (!PQ.isEmpty()) {
            Vertex working = null;
            try {
                working = PQ.pop();
                from = working.getNo();
            } catch (HeapEmptyException e) {
                e.printStackTrace();
            }
            List<WeightedEdge> edges = this.graph.getEdges(working.getNo());
            int currentDistance = working.getCost();
            for (WeightedEdge edge : edges) {
                if ((currentDistance + edge.weight) < dist[edge.to_vertex]) {
                    if (pred[edge.to_vertex] == -1) {
                        PQ.insert(new Vertex(edge.to_vertex, currentDistance + edge.weight));
                        pred[edge.to_vertex] = from;
                        dist[edge.to_vertex] = currentDistance + edge.weight;
                    } else if (pred[edge.to_vertex] != -1 && edge.to_vertex != pred[edge.from_vertex]) {
                        int pos = PQ.VertexPos(edge.to_vertex);
                        PQ.setCost(pos, currentDistance + edge.weight);
                        pred[edge.to_vertex] = from;
                        dist[edge.to_vertex] = currentDistance + edge.weight;
                    }
                }
            }

        }

        return true;
    }

}
