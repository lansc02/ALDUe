package A10_ZyklenTiefensuche;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Zyklen {

    int[] pred;
    int last;
    private Graph g;
    private int[] visited;

    public Zyklen(Graph g) {
        this.g = g;
        pred = new int[g.numVertices()];
        visited = new int[g.numVertices()];
        for (int i = 0; i < pred.length; i++) {
            pred[i] = -1;
            visited[i] = 0;
        }
    }


    /**
     * Retourniert einen Zyklus eines Graphen, sofern einer existiert
     *
     * @param //g zu prüfender Graph
     * @return Anzahl der Komponenten
     */
    public List<Integer> getCycle() {
        boolean hasCycle = DFS(0);
        if (!hasCycle){
        for (int i = 1; i < visited.length; i++) {
            if (visited[i] == 0){
                hasCycle = DFS(i);
                if (hasCycle) break;
            }
        }}
        if (hasCycle) {
            List<Integer> visited = new ArrayList<>();

            int i = last;
            visited.add(0, i);
            int l;
            do {
                    if (pred[i]==-1){ //notwendig für directed graph, die keine Kreise haben aber bei denen die DFS
                        // wieder am gleichen Knoten findet und daher glaub, es wäre ein Kreis
                        hasCycle=false;
                        return null;
                    }
                    visited.add(0, pred[i]);
                    l = visited.size()-1;
                    i = pred[i];
                } while (i != visited.get(l));

        //    visited.add(0, pred[i]);

            return visited;
        }
        return null;
    }

    private boolean DFS(int start) {
        boolean result = false;
        visited[start] = 1;
        List<WeightedEdge> edges = g.getEdges(start);
        for (WeightedEdge edge : edges) {
            if ((!g.isDirected() && edge.to_vertex != pred[start]) || g.isDirected()) {
                if (visited[edge.to_vertex] == 0) {
                    pred[edge.to_vertex] = start;
                    result = result | DFS(edge.to_vertex);
                } else {
                    last = edge.to_vertex;
                    pred[edge.to_vertex] = start;
                    result = true;
                }
            }
        }
        return result;
    }


}
