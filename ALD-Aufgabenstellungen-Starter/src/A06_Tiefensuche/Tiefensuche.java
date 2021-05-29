package A06_Tiefensuche;

import java.util.ArrayList;
import java.util.List;

import A01_Stack.Stack;
import A01_Stack.StackEmptyException;
import A05_Breitensuche.BaseTree;
import A05_Breitensuche.Node;

public class Tiefensuche extends BaseTree<Film> {

	@Override
	/**
	 * Sortierkriterium im Baum: Länge des Films
	 */
	protected int compare(Film a, Film b) {
		return Double.compare(a.getLänge(), b.getLänge());
	}

	/**
	 * Retourniert die Titelliste der Film-Knoten des Teilbaums in symmetrischer Folge (engl. in-order, d.h. links-Knoten-rechts)
	 * @param node Wurzelknoten des Teilbaums
	 * @return Liste der Titel in symmetrischer Reihenfolge
	 */
	public List<String> getNodesInOrder(Node<Film> node) {
		Stack<Node<Film>> stack = new Stack<>();
		List<String> resultList = new ArrayList<>();
	    Node<Film> start = find(node.getValue());

	    if (start == null){
	    	return resultList;
		}

		return null;
	}
	
	/**
	 * Retourniert Titelliste jener Filme, deren Länge zwischen min und max liegt, in Hauptreihenfolge (engl. pre-order, d.h. Knoten-links-rechts)
	 * @param min Minimale Länge des Spielfilms
	 * @param max Maximale Länge des Spielfilms
	 * @return Liste der Filmtitel in Hauptreihenfolge
	 */
	public List<String> getMinMaxPreOrder(double min, double max) {
		List<String> result = new ArrayList<>();
		Stack<Node<Film>> stack = new Stack<>();

		Node<Film> current = getRoot();

		stack.push(current);
		while (stack.getCount() > 0) {
			try {
				current = stack.pop();
			} catch (StackEmptyException e) {
				e.printStackTrace();
			}

			if (min < current.getValue().getLänge() && current.getValue().getLänge() < max) {
				result.add(current.getValue().getTitel());
			}

			if (current.getRight() != null) { // man könnte hier auch mit der Schrank vergleichen um die Laufzeit zu
				// optimieren
				stack.push(current.getRight());
			}
			if (current.getLeft() != null) { // man könnte hier auch mit der Schrank vergleichen um die Laufzeit zu
				// optimieren
				stack.push(current.getLeft());
			}
		}

		return result;
	}

}
