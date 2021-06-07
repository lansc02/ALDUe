package A11_DijkstraPQShortestPath;

import java.util.ArrayList;

public class VertexHeap {
    ArrayList<Vertex> vertices;

    public VertexHeap() {
        this.vertices = new ArrayList<>();
        vertices.add(null);
    }

    public int VertexPos(int v) {
        for (int i = 1; i < vertices.size(); i++) {
            if (v == vertices.get(i).getNo()) {
                return i;
            }
        }
        return -1;
    }

    public void insert(Vertex newVertex) {
        if (newVertex == null) {
            return;
        }
        vertices.add(newVertex);
        swim(vertices.size() - 1);
    }

    public Vertex pop() throws HeapEmptyException {
        if (vertices.size() == 1){
            throw new HeapEmptyException("Empty heap");
        }

        if (vertices.size() > 3) {
            exchange(1, vertices.size() - 1);
        }
        int pos = vertices.size() - 1;
        Vertex toRemove = vertices.get(pos);
        vertices.remove(pos);
        sink(1);
        return toRemove;
    }



    private void swim(int pos) {
        Vertex current = vertices.get(pos);
        if (pos == 1) {
            return;
        }

        while (cost(pos) < cost(parent(pos))) {
            exchange(pos, parent(pos));
            pos = parent(pos);
            if (pos == 1) {
                return;
            }
        }
    }

    private void sink(int pos) {
        while (hasChildren(pos)) {
            int minChild = minChild(pos);
            if (cost(pos) > cost(minChild)) {
                exchange(pos, minChild);
                pos = minChild;
            } else {
                return;
            }
        }
    }

    private int parent(int pos) {
        return pos / 2;
    }

    private int left(int pos) {
        return pos * 2;
    }

    private int right(int pos) {
        return pos * 2 + 1;
    }

    private boolean exists(int pos) {
        return (pos < vertices.size() && pos > 0);
    }

    private int cost(int pos) {
        return vertices.get(pos).getCost();
    }

    private void exchange(int pos1, int pos2) {
        Vertex temp;
        temp = vertices.get(pos1);
        vertices.set(pos1, vertices.get(pos2));
        vertices.set(pos2, temp);
    }

    private boolean hasChildren(int pos) {
        return exists(left(pos));
    }

    private int minChild(int pos) {
        int min, l, r;
        l = left(pos);
        r = right(pos);
        min = l;
        if (exists(r) && cost(r) < cost(l)) {
            min = r;
        }
        return min;
    }

    public void setCost(int pos, int cost) {
        int oldCost = this.cost(pos);
        if (cost < oldCost) {
            vertices.get(pos).setCost(cost);
            swim(pos);
        }
    }

    public boolean isEmpty() {
        if (vertices.size() <= 1){
            return true;
        }
        return this.vertices.isEmpty();
    }

}
