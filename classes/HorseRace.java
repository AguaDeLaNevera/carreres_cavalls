package classes;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class HorseRace extends Thread{
    Cursa c;
    Cavall cavall;
    List<Cavall> cavalls;
    private final Object lock = new Object();
    private boolean pauseRequested = false;
    private boolean userAnswered = false; // Flag to track whether the user has answered
    private boolean iniciCursa = true;
    public HorseRace(Cavall cavall, Cursa c, List<Cavall> cavalls){
        this.c = c;
        this.cavall = cavall;
        this.cavalls = cavalls;
    }
    public void run() {
        long startTime = System.currentTimeMillis(); // Enregistra el temps d'inici de la cursa


        double distancia_inicial = c.getLongitud() * 1000; // Emmagatzema la distància inicial en metres
        double distancia_restant = distancia_inicial;
        while (iniciCursa) {
            double ms = cavall.getVelocitat() / 3.6;
            if(!c.getRaceOnGoing()){
                distancia_restant = cavall.getDistanciaRecorreguda();
            }
            distancia_restant = distancia_restant - ms;
            if(distancia_restant < 0){
                distancia_restant = 0;
            }
            cavall.setDistanciaRecorreguda(distancia_restant);
            try {
                sleep(1000);
                // Calcula la taxa d'acompliment i actualitza la barra de progrés
                int completionRate = (int) (((distancia_inicial - cavall.getDistanciaRecorreguda()) * 1.0 / distancia_inicial) * 100);
                alternarVelocitat(cavall);
                System.out.println(printProgressBar(completionRate, cavall.getVelocitat()));
                long currentEndTime = System.currentTimeMillis();
                long endTime = currentEndTime - startTime;

                int increment = 0;
                if(c.getRaceOnGoing()){
                    for (Cavall cv : cavalls) {
                        if (cv.getRealCompletionTime() != null) {
                            increment++;
                            if (increment == 3) {
                                stopThread();
                                Thread.currentThread().interrupt();
                                return;  // Optional: You might want to break out of the loop after interrupting
                            }
                        }
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace(); // Imprimeix la traça de l'excepció si hi ha algun problema amb la interrupció del fil
            }

            if (distancia_restant <= 0) {
                stopThread(); // Finalitza la cursa quan la distància restant és menor o igual a zero
            }
        }

        long endTime = System.currentTimeMillis(); // Registra el temps final de la cursa
        long elapsedTime = endTime - startTime; // Calcula el temps total transcorregut durant la cursa

        // Obtenir el temps i el temps real de la carrera pel cavall
        cavall.obtenirTemps(elapsedTime);
        String formattedTime = formatElapsedTime(elapsedTime);
        cavall.obtenirTempsReal(formattedTime);

        // Imprimeix la informació sobre la finalització de la cursa per al cavall
        System.out.println(cavall.getNom() + " ha acabat la carrera amb un temps de " + formattedTime + " a una velocitat de: " + cavall.getVelocitat() + "Km/h");
    }

    private String printProgressBar(int completionRate, double currentSpeed) {
        StringBuilder result = new StringBuilder();
        result.append(cavall.getNom()).append(" ");

        int barLength = 20; // Adjust the length of the progress bar
        int progress = (int) (barLength * completionRate / 100.0);



        result.append("Progress: [");
        for (int i = 0; i < barLength; i++) {
            if (i < progress) {
                result.append("=");
            } else {
                result.append(" ");
            }
        }
        result.append("] ").append(completionRate).append("% - Speed: ").append(currentSpeed).append(" km/h");
        return result.toString();
    }


    private static String formatElapsedTime(long elapsedTime) {
        // Convert milliseconds to minutes, seconds, and milliseconds
        elapsedTime = elapsedTime;
        long minutes = (elapsedTime / 1000) / 60;
        long seconds = (elapsedTime / 1000) % 60;
        long milliseconds = elapsedTime % 1000;
        String result = String.format("%02d:%02d:%03d", minutes, seconds, milliseconds);
        // Format the time as a string
        return String.format("%02d:%02d:%03d", minutes, seconds, milliseconds);
    }
    private static void alternarVelocitat(Cavall cavall){
        Random random = new Random();
        int randomNumber = random.nextInt(11) - 5;
        if(cavall.hasAnabolicSteroids){
            int boostedNumber = random.nextInt(20) - 3;
        }
        else{
            cavall.setVelocitat(cavall.getVelocitat()+randomNumber);
        }
    }

    public void stopThread() {
        iniciCursa = false;
    }

}