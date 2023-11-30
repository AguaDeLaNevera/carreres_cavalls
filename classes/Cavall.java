package classes;
public class Cavall {
    String nom;
    int velocitat = 50;
    boolean hasAnabolicSteroids;
    long completionTime;
    long interruptedTime;
    String realTime;
    boolean chosen = false;
    double distanciaRecorreguda = 0;

    Cavall(String nom, int velocitat, boolean hasAnabolicSteroids){
        this.nom = nom;
        this.velocitat = velocitat;
        this.hasAnabolicSteroids = hasAnabolicSteroids;
        if(this.velocitat != 50){
            this.velocitat = 50;
        }
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setVelocitat(int velocitat) {
        this.velocitat = velocitat;
        if(this.velocitat > 70 && !this.hasAnabolicSteroids){
            this.velocitat = 70;
        }
        if(this.velocitat < 15 && !this.hasAnabolicSteroids){
            this.velocitat = 15;
        }
        if(this.velocitat > 80 && this.hasAnabolicSteroids){
            this.velocitat = 80;
        }
        if(this.velocitat < 30 && this.hasAnabolicSteroids){
            this.velocitat = 30;
        }

    }

    public String getNom() {
        return nom;
    }

    public int getVelocitat() {
        return velocitat;
    }

    public void obtenirTemps(long temps){
        this.completionTime = temps;
    }

    public void setInterruptedTime(long interruptedTime) {
        this.interruptedTime = interruptedTime;
    }
    public long getCompletionTime(){return this.completionTime;}

    public long getInterruptedTime() {
        return interruptedTime;
    }

    public void obtenirTempsReal(String temps){
        this.realTime = temps;
    }
    public String getRealCompletionTime(){return this.realTime;}

    public void setDistanciaRecorreguda(double distanciaRecorreguda) {
        this.distanciaRecorreguda = distanciaRecorreguda;
    }

    public double getDistanciaRecorreguda() {
        return distanciaRecorreguda;
    }

    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }

    public boolean isChosen() {
        return chosen;
    }
}
