import java.util.ArrayList;

public class NumberCollection {
    private ArrayList<Integer> numberCollection = new ArrayList<>();

    /*
    Denne metoden kan anses som en enhet, men returnerer ingen verdi. Istedet oppdaterer den en ekstern liste.
    Vi bør teste at den påvirker listen på den måten vi forventer.
     */
    public void addPositiveNumber(int number) {
        if (number >= 0) {
            numberCollection.add(number);
        }
    }

    public ArrayList<Integer> getNumberCollection() {
        return new ArrayList<>(numberCollection);
    }
}
