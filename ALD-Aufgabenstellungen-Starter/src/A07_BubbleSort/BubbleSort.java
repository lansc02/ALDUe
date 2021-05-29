package A07_BubbleSort;


public class BubbleSort implements PersonenSort {

    /**
     * Sortier-Funktion
     */
    public void sort(Person[] personen) {

        int count = 1;
        boolean swapped = true;
        while (swapped || count < personen.length) {
            for (int i = 0; i < personen.length - count; i++) {
                if (personen[i].compareTo(personen[i + 1]) > 0) {
                    Person tmpperson = personen[i];
                    personen[i] = personen[i + 1];
                    personen[i + 1] = tmpperson;
                    swapped = true;
                }
            }
            swapped=false;
            count++;
        }

    }

}
