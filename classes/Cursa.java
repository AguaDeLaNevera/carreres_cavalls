package classes;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Cursa {
    String nomCursa;
    double longitud;
    int quantitatCavalls;
    boolean raceOnGoing = true;

    Cursa(String nomCursa, double longitud, int quantitatCavalls){
        this.nomCursa = nomCursa;
        this.longitud = longitud;
        this.quantitatCavalls = quantitatCavalls;
        if(this.quantitatCavalls < 10){
            this.quantitatCavalls = 10;
        }
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public void setNomCursa(String nomCursa) {
        this.nomCursa = nomCursa;
    }

    public void setQuantitatCavalls(int quantitatCavalls) {
        this.quantitatCavalls = quantitatCavalls;
    }

    public double getLongitud() {
        return longitud;
    }

    public int getQuantitatCavalls() {
        return quantitatCavalls;
    }

    public String getNomCursa() {
        return nomCursa;
    }

    public void sortByTime(List<Cavall> cavalls){
        cavalls.sort(Comparator.comparingDouble(Cavall::getCompletionTime));
        int increment = 0;
        for(Cavall cavall : cavalls){
            if(cavall.getRealCompletionTime()!= null){
                increment++;
            }
        }
    }

    public void setRaceOnGoing(boolean raceOnGoing) {
        this.raceOnGoing = raceOnGoing;
    }
    public boolean getRaceOnGoing(){return this.raceOnGoing;}
}
