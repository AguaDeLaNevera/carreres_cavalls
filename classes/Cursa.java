package classes;

public class Cursa {
    String nomCursa;
    double longitud;
    int quantitatCavalls;

    Cursa(String nomCursa, double longitud, int quantitatCavalls){
        this.nomCursa = nomCursa;
        this.longitud = longitud;
        this.quantitatCavalls = quantitatCavalls;
        /*
        if(this.quantitatCavalls < 10){
            this.quantitatCavalls = 10;
        }*/
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
}
