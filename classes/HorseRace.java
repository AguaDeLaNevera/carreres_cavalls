// Paquet que conté la classe
package classes;

// Importació de classes necessàries
import java.util.List;
import java.util.Random;

// Classe que representa una cursa de cavalls que estén els filtres Thread
public class HorseRace extends Thread {
    Cursa c; // Instància de la cursa
    Cavall cavall; // Instància del cavall que participa en la cursa
    final List<Cavall> cavalls; // Llista de tots els cavalls de la cursa
    private boolean iniciCursa = true; // Indica si la cursa està en curs
    private static final Object lock = new Object();
    private static int print = 0;
    // Constructor amb paràmetres
    public HorseRace(Cavall cavall, Cursa c, List<Cavall> cavalls) {
        this.c = c;
        this.cavall = cavall;
        this.cavalls = cavalls;
    }

    // Mètode que s'executarà quan es llança un fil
    @Override
    public void run() {
        long startTime = System.currentTimeMillis(); // Registra el temps d'inici de la cursa

        double distancia_inicial = c.getLongitud() * 1000; // Emmagatzema la distància inicial en metres
        double distancia_restant = distancia_inicial;

        // Bucle principal que simula la cursa
        while (iniciCursa) {
            double ms = cavall.getVelocitat() / 3.6; // Converteix la velocitat a metres per segon

            // Comprova si la cursa està en curs o si la distància ha estat modificada externament
            if (!c.getRaceOnGoing()) {
                distancia_restant = cavall.getDistanciaRecorreguda();
            }

            // Actualitza la distància restant
            distancia_restant = distancia_restant - ms;

            // Assegura que la distància restant no sigui negativa
            if (distancia_restant < 0) {
                distancia_restant = 0;
            }

            // Actualitza la distància recorreguda pel cavall
            cavall.setDistanciaRecorreguda(distancia_restant);

            try {
                sleep(1000); // Pausa el fil durant un segon
                // Calcula la taxa d'acompliment i imprimeix la barra de progrés
                int completionRate = (int) (((distancia_inicial - cavall.getDistanciaRecorreguda()) * 1.0 / distancia_inicial) * 100);
                alternarVelocitat(cavall);

                boolean finishedPrinting = true; // Declare finishedPrinting outside the synchronized block

                synchronized (lock) {
                    // Wait until it's the horse's turn to print
                    while (!finishedPrinting) {
                        lock.wait();
                    }


                    // Print the progress bars for all horses
                    if(print == 0){
                        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
                        System.out.println("║                                   #FASTHORSE                                 ║");
                        System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
                        for (Cavall cav : cavalls) {
                            int newCompletionRate = (int) (((distancia_inicial - cav.getDistanciaRecorreguda()) * 1.0 / distancia_inicial) * 100);
                            System.out.println(printProgressBar(newCompletionRate, cav.getVelocitat(), cav));
                        }
                    }
                    print++;
                    // Notify other waiting threads
                    lock.notifyAll();
                }
                if(print >= cavallsNull(cavalls)){
                    print = 0;
                }


                // Registra el temps actual i calcula el temps transcorregut des de l'inici de la cursa
                long currentEndTime = System.currentTimeMillis();
                long endTime = currentEndTime - startTime;

                int increment = 0;

                // Verifica si la cursa està en curs i si s'han completat tres cavalls
                if (c.getRaceOnGoing()) {
                    for (Cavall cv : cavalls) {
                        if (cv.getRealCompletionTime() != null) {
                            increment++;
                            // Si s'han completat tres cavalls, s'interromp la cursa
                            if (increment == 3) {
                                cavall.setInterruptedTime(endTime);
                                stopThread(); // S'atura el fil
                                Thread.currentThread().interrupt();
                                return;  // Opcional: pots voler sortir del bucle després d'interrumpre el fil
                            }
                        }
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace(); // Imprimeix la traça de l'excepció si hi ha algun problema amb la interrupció del fil
            }

            // Verifica si la distància restant és menor o igual a zero i finalitza la cursa
            if (distancia_restant <= 0) {
                stopThread();
            }
        }

        long endTime = System.currentTimeMillis(); // Registra el temps final de la cursa
        long elapsedTime = endTime - startTime; // Calcula el temps total transcorregut durant la cursa

        String formattedTime;

        // Comprova si la cursa està en curs o si ha estat interrompuda
        if (c.getRaceOnGoing()) {
            cavall.setInterruptedTime(elapsedTime);
            cavall.obtenirTemps(elapsedTime);
            formattedTime = formatElapsedTime(elapsedTime);
            cavall.obtenirTempsReal(formattedTime);
        } else {
            elapsedTime = elapsedTime + cavall.getInterruptedTime();
            cavall.obtenirTemps(elapsedTime);
            formattedTime = formatElapsedTime(elapsedTime);
            cavall.obtenirTempsReal(formattedTime);
        }

        // Imprimeix la informació sobre la finalització de la cursa per al cavall
        System.out.println(cavall.getNom() + " ha acabat la cursa amb un temps de " + formattedTime + " a una velocitat de: " + cavall.getVelocitat() + " km/h");
    }

    // Mètode per imprimir la barra de progrés
    private String printProgressBar(int completionRate, double currentSpeed, Cavall cavall) {
        StringBuilder result = new StringBuilder();

        // Pad the horse's name to a fixed width
        String paddedName = String.format("%-20s", cavall.getNom());

        result.append("\u001B[0m").append(paddedName).append(" ");

        int barLength = 20; // Adjust the length of the progress bar
        int progress = (int) (barLength * completionRate / 100.0);

        result.append("\u001B[0mProgress: [");
        for (int i = 0; i < barLength; i++) {
            if (i < progress) {
                // ANSI escape code to reset the color
                result.append("\u001B[0m=");
            } else {
                // ANSI escape code to reset the color
                result.append("\u001B[0m ");
            }
        }
        result.append("] ").append(completionRate).append("% - Velocitat: ").append(currentSpeed).append(" km/h");
        return result.toString();
    }


    // Mètode per formatar el temps transcorregut
    private static String formatElapsedTime(long elapsedTime) {
        // Converteix mil·lisegons a minuts, segons i mil·lisegons
        long minutes = (elapsedTime / 1000) / 60;
        long seconds = (elapsedTime / 1000) % 60;
        long milliseconds = elapsedTime % 1000;
        return String.format("%02d:%02d:%03d", minutes, seconds, milliseconds);
    }

    // Mètode per alternar la velocitat del cavall
    private static void alternarVelocitat(Cavall cavall) {
        Random random = new Random();
        int randomNumber = random.nextInt(11) - 5;
        if (cavall.hasAnabolicSteroids) {
            int boostedNumber = random.nextInt(20) - 3;
            cavall.setVelocitat(cavall.getVelocitat() + boostedNumber);
        } else {
            cavall.setVelocitat(cavall.getVelocitat() + randomNumber);
        }
    }

    // Mètode per aturar el fil
    public void stopThread() {
        iniciCursa = false;
    }
    private int cavallsNull(List<Cavall> cavalls){
       int i = 0;
       for(Cavall cavall : cavalls){
           if(cavall.getRealCompletionTime() == null){
               i++;
           }
       }
       return i;
    }
}
