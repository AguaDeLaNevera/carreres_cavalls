package classes;

import java.util.List;

public class test extends Thread{
    creacioCavalls creacio = new creacioCavalls();
    Cursa c = new Cursa("san pedro", 0.3, 1);
    List<Cavall> cavalls = creacio.creacioCavall(c);

    public test(){
        new Thread("cavall");
    }
    public void run(){
        boolean iniciCursa = true;
        System.out.println(c.getNomCursa());
        System.out.println(c.getLongitud()+"km");
        System.out.println(c.getQuantitatCavalls()+" cavalls");
        double distancia_restant = c.getLongitud()*1000;
        System.out.print("X_START_LINE_X");
        while (iniciCursa) {
            int ms = (int) (cavalls.get(0).getVelocitat() / 3.6);
            distancia_restant = distancia_restant - ms;

            try {
                currentThread().sleep(1000);
                System.out.print(cavalls.get(0).getNom() + "-"); // Print the horse name in front of the hyphen
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (distancia_restant <= 0) {
                iniciCursa = false;
            }
        }

        System.out.println("X_FINISH_LINE_X");
    }
}