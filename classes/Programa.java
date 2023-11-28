package classes;
import java.util.ArrayList;
import java.util.List;

public class Programa {
    public static void main(String[] arg) {

        creacioCavalls cr = new creacioCavalls();
        Cursa cursa = new Cursa("san pedro", 0.5, 10);
        List<Cavall> cavalls = cr.creacioCavall(cursa);
        List<Thread> threads = createThreads(cursa, cavalls);
        System.out.println(cursa.getNomCursa());
        System.out.println(cursa.getLongitud() + "km");
        System.out.println(cursa.getQuantitatCavalls() + " cavalls");
        cavalls.get(0).setVelocitat(70);
        cavalls.get(1).setVelocitat(70);
        cavalls.get(2).setVelocitat(70);

        startThread(threads);
        joinThread(threads);

        // Once all threads have finished, continue with the ranking
        cursa.sortByTime(cavalls);
        System.out.println("RANKING");
        for (Cavall cavall : cavalls) {
            if (cavall.getRealCompletionTime() != null) {
                System.out.println(cavall.getNom() + " " + cavall.getRealCompletionTime());
            }
        }
    }
    public static List<Thread> createThreads(Cursa c, List<Cavall> cv){
        List<Thread> threadList = new ArrayList<>();
        for(int i=0; i<c.quantitatCavalls; i++){
            HorseRace hr = new HorseRace(cv.get(i), c, cv);
            threadList.add(hr);
        }
        return threadList;
    }
    public static void startThread(List<Thread> t){
        for(Thread thread : t){
            thread.start();
        }
    }
    public static void joinThread(List<Thread> t){
        try{
            for(Thread thread : t){
                thread.join();
            }
            System.out.println("holas  w0wuq 09e uq0w9 ueq09wu eq0w9ueqw0 e9uq");
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

}
