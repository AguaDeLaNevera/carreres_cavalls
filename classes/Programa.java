package classes;
import java.util.List;

public class Programa {
    public static void main(String[] arg) {

        creacioCavalls cr = new creacioCavalls();
        Cursa cursa = new Cursa("san pedro", 0.1, 10);
        List<Cavall> cavalls = cr.creacioCavall(cursa);
        HorseRace t = new HorseRace(cavalls.get(0), cursa, cavalls);
        HorseRace t2 = new HorseRace(cavalls.get(1), cursa, cavalls);
        HorseRace t3 = new HorseRace(cavalls.get(2), cursa, cavalls);
        HorseRace t4 = new HorseRace(cavalls.get(3), cursa, cavalls);
        HorseRace t5 = new HorseRace(cavalls.get(4), cursa, cavalls);
        HorseRace t6 = new HorseRace(cavalls.get(5), cursa, cavalls);
        HorseRace t7 = new HorseRace(cavalls.get(6), cursa, cavalls);
        HorseRace t8 = new HorseRace(cavalls.get(7), cursa, cavalls);
        HorseRace t9 = new HorseRace(cavalls.get(8), cursa, cavalls);
        HorseRace t10 = new HorseRace(cavalls.get(9), cursa, cavalls);

        System.out.println(cursa.getNomCursa());
        System.out.println(cursa.getLongitud() + "km");
        System.out.println(cursa.getQuantitatCavalls() + " cavalls");
        cavalls.get(0).setVelocitat(70);
        cavalls.get(1).setVelocitat(70);
        cavalls.get(2).setVelocitat(70);

        // Start the race threads
        t.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();



        // Wait for each race thread to finish
        try {
            t.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
            t7.join();
            t8.join();
            t9.join();
            t10.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Once all threads have finished, continue with the ranking
        cursa.sortByTime(cavalls);
        System.out.println("RANKING");
        for (Cavall cavall : cavalls) {
            if (cavall.getRealCompletionTime() != null) {
                System.out.println(cavall.getNom() + " " + cavall.getRealCompletionTime());
            }
        }
    }
}
