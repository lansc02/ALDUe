package A11_DijkstraPQShortestPath;

public class Vertex {
    private int no;
    private int cost;

    public Vertex(int no, int cost) {
        this.no = no;
        this.cost = cost;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
