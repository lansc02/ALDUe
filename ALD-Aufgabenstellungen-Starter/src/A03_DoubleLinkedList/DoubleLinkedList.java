package A03_DoubleLinkedList;

import com.sun.source.tree.NewArrayTree;

public class DoubleLinkedList<T> {
    private Node<T> current;
    private Node<T> first;
    private Node<T> last;

    /**
     * Einfügen einer neuen <T>
     *
     * @param a <T>
     */
    public void add(T a) {
        Node<T> newElement = new Node(a);

        if (first == null) {
            first = newElement;
            last = newElement;
            newElement.setPrevious(null);
            newElement.setNext(null);
        } else {
            last.setNext(newElement);
            newElement.setPrevious(last);
            last = newElement;
        }

    }

    /**
     * Internen Zeiger für next() zurücksetzen
     */
    public void reset() {
        current = first;
    }

    /**
     * analog zur Funktion reset()
     */
    public void resetToLast() {
        current = last;
    }

    /**
     * Liefert erste Node der Liste retour oder null, wenn Liste leer
     *
     * @return Node|null
     */
    public Node<T> getFirst() {
        if (first == null) {
            return null;
        }
        return first;
    }

    /**
     * Liefert letzte Node der Liste retour oder null, wenn Liste leer
     *
     * @return Node|null
     */
    public Node<T> getLast() {
        if (first == null) {
            return null;
        } else {
            return last;
        }
    }

    /**
     * Gibt aktuelle <T> zurück und setzt internen Zeiger weiter.
     * Falls current nicht gesetzt, wird null retourniert.
     *
     * @return <T>|null
     */
    public T next() {
        if (current == null) {
            return null;
        }
        T data = current.getData();
        current = current.getNext();
        return data;
    }

    /**
     * analog zur Funktion next()
     *
     * @return <T>|null
     */
    public T previous() {
        if (current == null) {
            return null;
        }
        T data = current.getData();
        current = current.getPrevious();
        return data;
    }

    /**
     * Current-Pointer auf nächste <T> setzen (aber nicht auslesen).
     * Ignoriert still, dass current nicht gesetzt ist.
     */
    public void moveNext() {
        if (current == null) return;

        Node<T> next = current.getNext();
        current = next;
    }

    /**
     * Analog zur Funktion moveNext()
     */
    public void movePrevious() {
        if (current == null) return;

        Node<T> previous = current.getPrevious();

        current = previous;
    }

    /**
     * Retourniert aktuelle (current) <T>, ohne Zeiger zu ändern
     *
     * @return <T>
     * @throws CurrentNotSetException
     */
    public T getCurrent() throws CurrentNotSetException {
        try {
            return current.getData();
        } catch (NullPointerException e) {
            throw new CurrentNotSetException();
        }
    }

    /**
     * Gibt <T> an bestimmter Position zurück
     *
     * @param pos Position, Nummerierung startet mit 1
     * @return <T>|null
     */
    public T get(int pos) {
        int counter = 2;

        if (pos == 1) {
            return first.getData();
        }

        current = first.getNext();
        while ((true)) {
            if (counter == pos) {
                return current.getData();
            }
            moveNext();
        }
    }

    /**
     * Entfernen des Elements an der angegebenen Position.
     * Falls das entfernte Element das aktuelle Element ist, wird current auf null gesetzt.
     *
     * @param pos Position
     */
    public void remove(int pos) {

        // Fall 1: empty dll
        if (first == null) {
            System.out.println("Nothing deleted, empty dll");
            return;
        }

        int counter = 1;
        Node<T> toRemove = first;
        Node<T> prev = toRemove.getPrevious();
        Node<T> next = toRemove.getNext();

        // Fall 2: single node
        if (prev == null && next == null && pos == 1) {
            first = null;
            current = null;
            last = null;
            return;
        }

        // Fall 4: first node
        if (prev == null && pos == 1) {
            first = next;
            toRemove.setNext(null);
            next.setPrevious(null);
            if (toRemove == current) {
                current = null;
            }
            return;
        }


        // else
        while (true) {
            toRemove = next;
            counter++;
            prev = toRemove.getPrevious();
            next = toRemove.getNext();

            if (counter == pos) {

                if (current == toRemove) {
                    current = null;
                }

                // Fall: letzte node als Match
                if (next == null) {
                    prev.setNext(null);
                    toRemove.setPrevious(null);
                    last = prev;
                    return;
                }

                // Fall mittlere Node als Match
                prev.setNext(next);
                next.setPrevious(prev);
                toRemove.setPrevious(null);
                toRemove.setNext(null);
                return;
            }
            // Fall: kein Match
            else {

                // Fall: pos zu groß für dll:
                if (next == null) {
                    System.out.println("pos > dll.length()");
                    return;
                }
            }
        }

    }

    /**
     * Entfernt das aktuelle Element.
     * Als neues aktuelles Element wird der Nachfolger gesetzt oder
     * (falls kein Nachfolger) das vorhergehende Element
     *
     * @throws CurrentNotSetException
     */
    public void removeCurrent() throws CurrentNotSetException {

        if (current == null) {
            throw new CurrentNotSetException();
        }

        // Fall 1: empty dll
        if (first == null) {
            throw new CurrentNotSetException("Die DLL ist leer.");
        }

        // Fall 2: single node
        else if (current.getNext() == null && current.getPrevious() == null) {
            first = null;
            last = null;
            current = null;
        }

        // Fall 3: first node
        else if (current.getPrevious() == null) {
            Node<T> next = current.getNext();
            current.setNext(null);
            next.setPrevious(null);
            first = next;
            current = next;
        }

        // Fall 4: last node
        else if (current.getNext() == null) {
            Node<T> prev = current.getPrevious();
            last = prev;
            current.setPrevious(null);
            prev.setNext(null);
            current = prev;
        }

        // Fall 5:
        else {
            Node<T> prev = current.getPrevious();
            Node<T> next = current.getNext();

            prev.setNext(next);
            next.setPrevious(prev);

            current.setPrevious(null);
            current.setNext(null);

            current = next;
        }
    }

    /**
     * Die Methode fügt die übergebene <T> nach der aktuellen (current) ein
     * und setzt dann die neu eingefügte <T> als aktuelle (current) <T>.
     *
     * @throws CurrentNotSetException
     */
    public void insertAfterCurrentAndMove(T a) throws CurrentNotSetException {

        if (current == null){
            throw new CurrentNotSetException();
        }

        Node<T> oldNext = current.getNext();
        Node<T> newNext = new Node<>(a);

        current.setNext(newNext);
        if (oldNext != null) {
            newNext.setNext(oldNext);
            newNext.setPrevious(current);
            oldNext.setPrevious(newNext);
        }
        else {
            newNext.setPrevious(current);
            last = newNext;
        }
        current = newNext;

    }
}
