package classes;
public class Cavall {
    String nom;
    int velocitat = 50;

    Cavall(String nom, int velocitat){
        this.nom = nom;
        this.velocitat = velocitat;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setVelocitat(int velocitat) {
        this.velocitat = velocitat;
    }

    public String getNom() {
        return nom;
    }

    public int getVelocitat() {
        return velocitat;
    }
}
