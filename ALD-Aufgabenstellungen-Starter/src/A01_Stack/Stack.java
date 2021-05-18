package A01_Stack;


public class Stack<T>
{
	 private Node<T> first;
    /**
     * Oberstes Element entfernen und zurückliefern.
     * Existiert kein Element, wird eine Exception ausgelöst.
     * @throws StackEmptyException 
     */
    public T pop() throws StackEmptyException {
        if (first == null){
            throw new StackEmptyException();
        }
        T payload = first.getData();
        Node<T> temp = first;
        first = first.getNext();
        temp.setNext(null);
        temp = null;
        return payload;
    }
    
    /**
     * Übergebenen T auf Stack (als oberstes Element) speichern.
     * @param i data
     */
    public void push(T i) {

        Node<T> toAdd = new Node<>(i);

        if (first == null){
           first = toAdd;
           return;
        }

        toAdd.setNext(first);
        first = toAdd;
    }
    
    /**
     * Liefert die Anzahl der Elemente im Stack
     * @return
     */
    public int getCount() {

        int counter = 0;

        if (first == null){
            return counter;
        }
        counter++;
        Node<T> current = first;
        while(current.getNext() != null){
            counter++;
            current = current.getNext();
        }
        return counter;
    }
}
