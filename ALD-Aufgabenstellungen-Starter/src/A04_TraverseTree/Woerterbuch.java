package A04_TraverseTree;

import java.util.HashSet;
import java.util.Set;


public class Woerterbuch {

	/**
	 * Wurzel des Baums (Startknoten)
	 */
	private Wort root;
	
	public Wort getRoot() {
		return root;
	}

	/**
	 * Z�hlt alle W�rter des Teilbaums ab einem bestimmten Wort
	 * @param w Wort
	 * @return Zahl der W�rter (=Anzahl der Elemente)
	 */
	public int countWordsInSubTree(Wort w) {

		if (w==null) {
			return 0;
		}

		if (root == null) {
			return 0;
		}

		Wort start = find(w.getWort());
		int sum = 1;
//		if (start.getRight() == null && start.getLeft() == null){
//			return sum;
//		}
		if (start.getLeft() != null){
			sum += countWordsInSubTree(start.getLeft());
		}
		if (start.getRight() != null){
			sum += countWordsInSubTree(start.getRight());
		}
		return sum;
	}

	/**
	 * Liefert die Menge aller W�rter retour, die ein spezifisches Pr�fix haben.
	 * @param prefix W�rter m�ssen diesen Pr�fix haben
	 * @return Menge aller zutreffenden W�rter
	 */
	public Set<String> getWordsWithPrefix(String prefix) {

//		if (prefix.equals("")){
//			return new HashSet<String>();
//		}

		if (root == null){
			return new HashSet<>();
		}

		Wort start = getRoot();
		Set<String> resultset = getWordsWithPrefix(prefix, start);

		return resultset;
	}

	private Set<String> getWordsWithPrefix(String prefix, Wort current){
		Set<String> resultSet = new HashSet<>();

		if (current.getWort().startsWith(prefix)){
			resultSet.add(current.getWort());
		}

		if (current.getLeft() != null){
			resultSet.addAll(getWordsWithPrefix(prefix, current.getLeft()));
		}

		if (current.getRight() != null){
			resultSet.addAll(getWordsWithPrefix(prefix, current.getRight()));
		}
		return resultSet;
	}


	/**
	 * Neues Wort hinzuf�gen
	 * @param wort Hinzuzuf�gendes Wort
	 */
	public void add(String wort) {
		Wort neu = new Wort(wort);
		if (root == null) {			// Fall 1: Baum ist leer
			root = neu;
			return;
		}
		Wort w = root;				// Fall 2: Baum ist nicht leer
		while (true) {
			int vgl = wort.compareTo(w.getWort());
			if (vgl < 0) {			// Neues Wort ist lexikographisch kleiner
				if (w.getLeft() == null) {
					w.setLeft(neu);
					neu.setParent(w);
					return;
				}
				w = w.getLeft();
			}
			else if (vgl > 0) {		// Neues Wort ist lexikographisch gr��er
				if (w.getRight() == null) {
					w.setRight(neu);
					neu.setParent(w);
					return;
				}
				w = w.getRight();
			}
			else {					// Neues Wort ist lexikographisch gleich
				return;
			}
		}
	}

	public Wort find(String s) {
		return find(root, s);
	}
	
	private Wort find(Wort current, String s) {
		if (current == null) {
			return null;
		}
		int vgl = s.compareTo(current.getWort());
		if (vgl == 0) {		// Gefunden
			return current;
		}
		else if (vgl < 0) {	// Links
			return find(current.getLeft(), s);
		}
		else {				// Rechts
			return find(current.getRight(), s);
		}
	}
	
}
