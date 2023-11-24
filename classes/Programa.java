package classes;

import java.util.List;

public class Programa {
    public static void main(String[] arg) {
        creacioCavalls cr = new creacioCavalls();
        Cursa cursa = new Cursa("san pedro", 0.1, 10);
        List<Cavall> cavalls = cr.creacioCavall(cursa);
        HorseRace t = new HorseRace(cavalls.get(0), cursa);
        HorseRace t2 = new HorseRace(cavalls.get(1), cursa);
        HorseRace t3 = new HorseRace(cavalls.get(2), cursa);
        HorseRace t4 = new HorseRace(cavalls.get(3), cursa);
        HorseRace t5 = new HorseRace(cavalls.get(4), cursa);
        System.out.println(cursa.getNomCursa());
        System.out.println(cursa.getLongitud() + "km");
        System.out.println(cursa.getQuantitatCavalls() + " cavalls");

        // Start the race threads
        t.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        // Wait for each race thread to finish
        try {
            t.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Once all threads have finished, continue with the ranking
        cursa.sortByTime(cavalls);
        int increment = 1;
        System.out.println("RANKING");
        for (Cavall cavall : cavalls) {
            if(cavall.getRealCompletionTime() != null){
                System.out.println(cavall.getNom() + " " + cavall.getRealCompletionTime());
            }
        }
    }
}

