// Paquet que conté la classe
package classes;

// Importació de classes necessàries
import java.util.Comparator;
import java.util.List;

// Classe que representa una cursa de cavalls
public class Cursa {
    // Variables de la classe
    String nomCursa; // Nom de la cursa
    double longitud; // Longitud de la cursa
    int quantitatCavalls; // Quantitat de cavalls a la cursa
    boolean raceOnGoing = true; // Indica si la cursa està en curs

    // Constructor amb paràmetres
    Cursa(String nomCursa, double longitud, int quantitatCavalls) {
        this.nomCursa = nomCursa;
        this.longitud = longitud;
        this.quantitatCavalls = quantitatCavalls;

        // Ajusta la quantitat de cavalls dins dels límits permessos
        if (this.quantitatCavalls < 10) {
            this.quantitatCavalls = 10;
        }
        if (this.quantitatCavalls > 20) {
            this.quantitatCavalls = 20;
        }
    }

    // Mètode setter per a la longitud de la cursa
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    // Mètode setter per al nom de la cursa
    public void setNomCursa(String nomCursa) {
        this.nomCursa = nomCursa;
    }

    // Mètode setter per a la quantitat de cavalls a la cursa
    public void setQuantitatCavalls(int quantitatCavalls) {
        this.quantitatCavalls = quantitatCavalls;
    }

    // Mètode getter per a la longitud de la cursa
    public double getLongitud() {
        return longitud;
    }

    // Mètode getter per a la quantitat de cavalls a la cursa
    public int getQuantitatCavalls() {
        return quantitatCavalls;
    }

    // Mètode getter per al nom de la cursa
    public String getNomCursa() {
        return nomCursa;
    }

    // Mètode per ordenar els cavalls segons el temps de finalització
    public void sortByTime(List<Cavall> cavalls) {
        cavalls.sort(Comparator.comparingDouble(Cavall::getCompletionTime));
        int increment = 0;
        for (Cavall cavall : cavalls) {
            if (cavall.getRealCompletionTime() != null) {
                increment++;
            }
        }
    }

    // Mètode setter per indicar si la cursa està en curs
    public void setRaceOnGoing(boolean raceOnGoing) {
        this.raceOnGoing = raceOnGoing;
    }

    // Mètode getter per saber si la cursa està en curs
    public boolean getRaceOnGoing() {
        return this.raceOnGoing;
    }
}
