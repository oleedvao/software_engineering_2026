public class DangerZone {

    /*
    Enhet som kaster en Exception (Vanligvis ville dette bare blitt gjort under definerte betingelser eller feil)
    Ved definerte feilsituasjoner, bør vi teste at en Exception kastes riktig.
     */
    public void killTheProgram() {
        throw new RuntimeException("Nothing personal, kid!");
    }
}
