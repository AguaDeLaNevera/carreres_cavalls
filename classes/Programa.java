// Importació de les classes necessàries
package classes;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// Classe principal del programa
public class Programa {
    public void programa() {
        Scanner sc = new Scanner(System.in);
        String userInput = "";
        boolean exit = true;

        // Bucle principal del programa
        while(exit){
            System.out.println("1. Simulació carrera de cavalls: escriu '1'\n2. Sortir: escriu '2'");
            userInput = sc.nextLine();

            try{
                int choice = Integer.parseInt(userInput);
                switch(choice){
                    case 1:
                        // Configuració inicial de la cursa
                        System.out.println("Introduex la distància en km de la carrera");
                        userInput = sc.nextLine();
                        double longitud = Double.parseDouble(userInput);

                        System.out.println("Introdueix la quantitat de cavalls a participar, mínim 10 i màxim 20");
                        userInput = sc.nextLine();
                        int quantitatCavalls = Integer.parseInt(userInput);

                        creacioCavalls cr = new creacioCavalls();
                        Cursa cursa = new Cursa("San Pedro", longitud, quantitatCavalls);
                        List<Cavall> cavalls = cr.creacioCavall(cursa);
                        List<Thread> threads = createThreads(cursa, cavalls);

                        // Mostra informació de la cursa
                        System.out.println(cursa.getNomCursa());
                        System.out.println(cursa.getLongitud() + "km");
                        System.out.println(cursa.getQuantitatCavalls() + " cavalls");

                        // Encoratja un cavall específic
                        System.out.println("Dona ànims a un cavall!! Escriu el nom d'un cavall per donar-li ànims!!");
                        displayHorses(cavalls);
                        userInput = sc.nextLine();
                        chosenHorse(cavalls, userInput);

                        // Inicia els fils i espera que finalitzin
                        startThread(threads);
                        joinThread(threads);

                        // Mostra el TOP 3 i dóna l'opció de continuar la cursa
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

                        // Un cop tots els fils han acabat, continua amb la classificació final
                        cursa.sortByTime(cavalls);
                        System.out.println("RANKING");
                        int pos = 0;
                        for (Cavall cavall : cavalls) {
                            if (cavall.getRealCompletionTime() != null) {
                                pos++;
                                System.out.println(pos+"."+cavall.getNom() + " " + cavall.getRealCompletionTime());
                            }
                        }
                        // Control antidopatge
                        controlAntiDoping(cavalls, cursa);
                        System.out.println("Control finalitzat, mostrant ranking final:");
                        displayRankingFinal(cavalls);
                        break;

                    case 2:
                        exit = false;
                        break;

                    default:
                        System.out.println("Nombre incorrecte");
                }
            }
            catch(NumberFormatException e){
                System.out.println("Has d'introduir un nombre");
            }
        }
    }

    // Mètode per crear llista de fils per a cada cavall
    public static List<Thread> createThreads(Cursa c, List<Cavall> cv){
        List<Thread> threadList = new ArrayList<>();
        for(int i=0; i<c.quantitatCavalls; i++){
            HorseRace hr = new HorseRace(cv.get(i), c, cv);
            threadList.add(hr);
        }
        return threadList;
    }

    // Mètode per iniciar els fils
    public static void startThread(List<Thread> t){
        for(Thread thread : t){
            thread.start();
        }
    }

    // Mètode per esperar que els fils finalitzin
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

    // Mètode per mostrar els noms dels cavalls
    public static void displayHorses(List<Cavall> cavalls) {
        StringBuilder horseNames = new StringBuilder();

        for (Cavall c : cavalls) {
            horseNames.append(c.getNom()).append(", ");
        }
        if (!horseNames.isEmpty()) {
            horseNames.setLength(horseNames.length() - 2);
            System.out.println(horseNames);
        }
    }

    // Mètode per seleccionar un cavall i augmentar la seva velocitat
    public static void chosenHorse(List<Cavall> cavalls, String userInput){
        for(Cavall c : cavalls){
            if(c.getNom().equals(userInput)){
                c.setChosen(true);
                c.setVelocitat(60);
                c.setHasAnabolicSteroids(true);
            }
        }
    }

    // Mètode per realitzar el control antidopatge
    public static void controlAntiDoping(List<Cavall> cavalls, Cursa c){
        try{
            System.out.println("Realitzant control de substàncies");
            Thread.sleep(5000);
            Random random = new Random();
            for (int i = 0; i < 3; i++) {
                if (cavalls.get(i).hasAnabolicSteroids) {
                    int probability = random.nextInt(100);
                    if (probability > 30) {
                        System.out.println("Oh no! " + cavalls.get(i).getNom() + " ha donat positiu en substàncies anabolitzants, aquest cavall serà desqualificat");
                        cavalls.get(i).obtenirTempsReal(null);
                        c.sortByTime(cavalls);
                    }
                }
            }
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    // Mètode per mostrar el ranking final de manera més estètica
    public static void displayRankingFinal(List<Cavall> cavalls) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║                 RANKING FINAL                ║");
        System.out.println("╠══════════════════════════════════════════════╣");

        int pos = 0;
        for (Cavall cavall : cavalls) {
            if (cavall.getRealCompletionTime() != null) {
                pos++;
                System.out.printf("║ %-2d. %-20s %20s║%n", pos
                        , cavall.getNom(), cavall.getRealCompletionTime());
            }
        }

        System.out.println("╚══════════════════════════════════════════════╝");
    }
}
