package classes;

import java.util.Random;

public class HorseRace extends Thread{
    Cursa c;
    Cavall cavall;
    public HorseRace(Cavall cavall, Cursa c){
        this.c = c;
        this.cavall = cavall;
    }
    public void run() {
        System.out.println(""); // Imprimeix una línia en blanc per millorar la llegibilitat en la sortida

        long startTime = System.currentTimeMillis(); // Enregistra el temps d'inici de la cursa
        boolean iniciCursa = true;

        double distancia_inicial = c.getLongitud() * 1000; // Emmagatzema la distància inicial en metres
        double distancia_restant = distancia_inicial;
        System.out.println(cavall.getNom()); // Imprimeix el nom del cavall

        while (iniciCursa) {
            double ms = cavall.getVelocitat() / 3.6;
            distancia_restant = distancia_restant - ms;

            try {
                currentThread().sleep(1000);

                // Calcula la taxa d'acompliment i actualitza la barra de progrés
                int completionRate = (int) (((distancia_inicial - distancia_restant) * 1.0 / distancia_inicial) * 100);
                alternarVelocitat(cavall);
                printProgressBar(completionRate, cavall.getVelocitat());

            } catch (InterruptedException e) {
                e.printStackTrace(); // Imprimeix la traça de l'excepció si hi ha algun problema amb la interrupció del fil
            }

            if (distancia_restant <= 0) {
                iniciCursa = false; // Finalitza la cursa quan la distància restant és menor o igual a zero
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


    private static void printProgressBar(int completionRate, double currentSpeed) {
        int barLength = 20; // Adjust the length of the progress bar
        int progress = (int) (barLength * completionRate / 100.0);
        System.out.print("\r");
        System.out.print("[");
        for (int i = 0; i < barLength; i++) {
            if (i < progress) {
                System.out.print("=");
            } else {
                System.out.print(" ");
            }
        }
        System.out.print("] " + completionRate + "% - Speed: " + currentSpeed + " km/h\r"); // Use carriage return to overwrite the current line
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

}