public class InformationChecker {

    /*
    Denne metoden er en enhet som tar en input og returnerer en verdi
    Vi bør teste at forskjellige inputs returerer riktig verdi.
     */
    public int controlAge(int age) {
        if (age > 120) {
            return 120;
        }
        else if (age < 0) {
            return 0;
        }
        return age;
    }
}
