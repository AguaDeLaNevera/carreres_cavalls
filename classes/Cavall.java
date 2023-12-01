// Paquet que conté la classe
package classes;

// Classe que representa un cavall
public class Cavall {
    // Variables de la classe
    String nom; // Nom del cavall
    int velocitat = 50; // Velocitat del cavall (valor per defecte: 50)
    boolean hasAnabolicSteroids; // Indica si el cavall ha pres esteroides anabòlics
    long completionTime; // Temps que triga a completar una carrera
    long interruptedTime; // Temps en què es va interrompre la carrera
    String realTime; // Temps real en què es va completar la carrera
    boolean chosen = false; // Indica si el cavall ha estat escollit per a una carrera
    double distanciaRecorreguda = 0; // Distància recorreguda pel cavall en una carrera

    // Constructor amb paràmetres
    Cavall(String nom, int velocitat, boolean hasAnabolicSteroids) {
        this.nom = nom;
        this.velocitat = velocitat;

        // Ajusta la velocitat dins de l'interval vàlid
        if (this.velocitat != 50) {
            this.velocitat = 50;
        }
    }

    // Mètode setter per al nom
    public void setNom(String nom) {
        this.nom = nom;
    }

    // Mètode setter per a la velocitat
    public void setVelocitat(int velocitat) {
        this.velocitat = velocitat;

        // Ajusta la velocitat segons les condicions
        if (this.velocitat > 70 && !this.hasAnabolicSteroids) {
            this.velocitat = 70;
        }
        if (this.velocitat < 15 && !this.hasAnabolicSteroids) {
            this.velocitat = 15;
        }
        if (this.velocitat > 80 && this.hasAnabolicSteroids) {
            this.velocitat = 80;
        }
        if (this.velocitat < 30 && this.hasAnabolicSteroids) {
            this.velocitat = 30;
        }
    }

    // Mètode getter per al nom
    public String getNom() {
        return nom;
    }

    // Mètode getter per a la velocitat
    public int getVelocitat() {
        return velocitat;
    }

    // Mètode per establir el temps de finalització
    public void obtenirTemps(long temps) {
        this.completionTime = temps;
    }

    // Mètode setter per al temps interromput
    public void setInterruptedTime(long interruptedTime) {
        this.interruptedTime = interruptedTime;
    }

    // Mètode getter per al temps de finalització
    public long getCompletionTime() {
        return this.completionTime;
    }

    // Mètode getter per al temps interromput
    public long getInterruptedTime() {
        return interruptedTime;
    }

    // Mètode per establir el temps de finalització real
    public void obtenirTempsReal(String temps) {
        this.realTime = temps;
    }

    // Mètode getter per al temps de finalització real
    public String getRealCompletionTime() {
        return this.realTime;
    }

    // Mètode setter per a la distància recorreguda
    public void setDistanciaRecorreguda(double distanciaRecorreguda) {
        this.distanciaRecorreguda = distanciaRecorreguda;
    }

    // Mètode getter per a la distància recorreguda
    public double getDistanciaRecorreguda() {
        return distanciaRecorreguda;
    }

    // Mètode setter per indicar si ha estat escollit
    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }

    // Mètode getter per indicar si ha estat escollit
    public boolean isChosen() {
        return chosen;
    }
    public void setHasAnabolicSteroids(boolean hasAnabolicSteroids) {
        this.hasAnabolicSteroids = hasAnabolicSteroids;
    }
}
