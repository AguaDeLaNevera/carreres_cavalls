package classes;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Programa {
    public static void main(String[] arg) {
        Scanner sc = new Scanner(System.in);
        String userInput;
        creacioCavalls cr = new creacioCavalls();
        Cursa cursa = new Cursa("sn pedro", 0.1, 10);
        List<Cavall> cavalls = cr.creacioCavall(cursa);
        List<Thread> threads = createThreads(cursa, cavalls);
        System.out.println(cursa.getNomCursa());
        System.out.println(cursa.getLongitud() + "km");
        System.out.println(cursa.getQuantitatCavalls() + " cavalls");

        System.out.println("Dona ànims a un cavall!!");
        displayHorses(cavalls);
        userInput = sc.nextLine();
        chosenHorse(cavalls, userInput);


        startThread(threads);
        joinThread(threads);
        cursa.sortByTime(cavalls);
        System.out.println("TOP 3");
        for (Cavall cavall : cavalls) {
            if (cavall.getRealCompletionTime() != null) {
                System.out.println(cavall.getNom() + " " + cavall.getRealCompletionTime());
            }
        }
        System.out.println("Vols continuar la carrera? Introdueix 'si' per continuar");
        userInput = sc.nextLine();
        if(userInput.equals("si")){
            cursa.setRaceOnGoing(false);
            threads = createThreads(cursa, cavalls);
            startThread(threads);
            joinThread(threads);
        }
        // Once all threads have finished, continue with the ranking
        cursa.sortByTime(cavalls);
        System.out.println("RANKING");
        int pos = 0;
        for (Cavall cavall : cavalls) {
            if (cavall.getRealCompletionTime() != null) {
                pos++;
                System.out.println(pos+"."+cavall.getNom() + " " + cavall.getRealCompletionTime());
            }
        }
        controlAntiDoping(cavalls, cursa);
        System.out.println("Control finalitzat, mostrant ranking final:");
        displayRankingFinal(cavalls);

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
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    public static void displayHorses(List<Cavall> cavalls){
        for(Cavall c : cavalls){
            System.out.print(c.getNom()+", ");
        }
    }
    public static void chosenHorse(List<Cavall> cavalls, String userInput){
        for(Cavall c : cavalls){
            if(c.getNom().equals(userInput)){
                c.setChosen(true);
                c.setVelocitat(55);
            }
        }
    }
    public static void controlAntiDoping(List<Cavall> cavalls, Cursa c){
        Random random = new Random();
        if(cavalls.get(0).hasAnabolicSteroids){
            int probability = random.nextInt(100);
            if(probability > 50){
                System.out.println("Oh no! " + cavalls.get(0).getNom() + " ha donat positiu en substàncies anabolitzants, aquest cavall serà desqualificat");
                cavalls.get(0).obtenirTempsReal(null);
                c.sortByTime(cavalls);
            }
        }
        if(cavalls.get(1).hasAnabolicSteroids){
            int probability = random.nextInt(100);
            if(probability > 50){
                System.out.println("Oh no! " + cavalls.get(1).getNom() + " ha donat positiu en substàncies anabolitzants, aquest cavall serà desqualificat");
                cavalls.get(1).obtenirTempsReal(null);
                c.sortByTime(cavalls);
            }
        }
        if(cavalls.get(2).hasAnabolicSteroids){
            int probability = random.nextInt(100);
            if(probability > 50){
                System.out.println("Oh no! " + cavalls.get(2).getNom() + " ha donat positiu en substàncies anabolitzants, aquest cavall serà desqualificat");
                cavalls.get(2).obtenirTempsReal(null);
                c.sortByTime(cavalls);
            }
        }
    }
    public static void displayRankingFinal(List<Cavall> cavalls) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║                 RANKING FINAL                ║");
        System.out.println("╠══════════════════════════════════════════════╣");

        int pos = 0;
        for (Cavall cavall : cavalls) {
            if (cavall.getRealCompletionTime() != null) {
                pos++;
                System.out.printf("║ %-2d. %-20s %20s║%n", pos, cavall.getNom(), cavall.getRealCompletionTime());
            }
        }

        System.out.println("╚══════════════════════════════════════════════╝");
    }

}
