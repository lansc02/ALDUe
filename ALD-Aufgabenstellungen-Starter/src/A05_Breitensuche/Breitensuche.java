package A05_Breitensuche;

import A03_DoubleLinkedList.DoubleLinkedList;

import java.util.ArrayList;
import java.util.List;

public class Breitensuche extends BaseTree<Integer> {

    @Override
    protected int compare(Integer a, Integer b) {
        return a.compareTo(b);
    }

    /**
     * Liefert Knoten des Baums ausgehend von Start in Reihenfolge der Breitensuche zurück
     *
     * @param start Startknoten für Teilbaum
     * @return Liste der Knoten in Breitenfolge
     */
    public List<Integer> getBreadthFirstOrder(Node<Integer> start) {
        List<Integer> nodeList = new ArrayList<>();
        Node<Integer> testNode = find(start.getValue());

        if (testNode == null) {
            return nodeList;
        }

        DoubleLinkedList<Node> queue = new DoubleLinkedList();
        queue.add(testNode);

        if (queue.getFirst() != null) {
            do {
                testNode = queue.getFirst().getData();
                nodeList.add(testNode.getValue());
                queue.remove(1);
                if (testNode.getLeft() != null) {
                    queue.add(testNode.getLeft());
                }
                if (testNode.getRight() != null) {
                    queue.add(testNode.getRight());
                }
            } while (queue.getFirst() != null);
        }

        return nodeList;
    }

    /**
     * Liefert Knoten des Baums ausgehend von Start in Reihenfolge der Breitensuche zurück,
     * allerdings nur jene Knoten, die in der angegebenen Ebene liegen (Start hat Ebene=1)
     *
     * @param start Startknoten für Teilbaum
     * @param level Nur Knoten dieser Ebene ausgeben
     * @return Liste aller Knoten
     */
    public List<Integer> getBreadthFirstOrderForLevel(Node<Integer> start, int level) {
        class nodeWrapper {

            Node<Integer> node;
            Integer level;

            /**
             * Konstruktor
             *
             * @param node die zu speichernde node
             * @param level das dazugehörige level im baum
             */
            public nodeWrapper(Node<Integer> node, Integer level) {
                this.level = level;
                this.node = node;
            }

            public Node<Integer> getNode() {
                return node;
            }

            public Integer getLevel() {
                return level;
            }
        }

        List<Integer> nodeList = new ArrayList<>();
        Node<Integer> testNode = find(start.getValue());
        int testLevel = 1;

        if (testNode == null) {
            return nodeList;
        }

        DoubleLinkedList<nodeWrapper> queue = new DoubleLinkedList();
        queue.add(new nodeWrapper(testNode, testLevel));

        if (queue.getFirst() != null) {
            do {
                testNode = queue.getFirst().getData().getNode();
                testLevel = queue.getFirst().getData().getLevel();
                if (testLevel == level) {
                    nodeList.add(testNode.getValue());
                }
                queue.remove(1);
                if (testNode.getLeft() != null) {
                    queue.add(new nodeWrapper(testNode.getLeft(), testLevel+1));
                }
                if (testNode.getRight() != null) {
                    queue.add(new nodeWrapper(testNode.getRight(), testLevel+1));
                }
            } while (queue.getFirst() != null);
        }

        return nodeList;
    }

}
