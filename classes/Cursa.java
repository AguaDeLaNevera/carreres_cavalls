package classes;

public class Cursa {
    String nomCursa;
    int longitud;
    int quantitatCavalls;

    Cursa(String nomCursa, int longitud, int quantitatCavalls){
        this.nomCursa = nomCursa;
        this.longitud = longitud;
        this.quantitatCavalls = quantitatCavalls;
        if(this.quantitatCavalls < 10){
            this.quantitatCavalls = 10;
        }
    }
}
