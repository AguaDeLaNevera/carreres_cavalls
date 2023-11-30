package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class creacioCavalls {
    String[] nomsCavalls = {
            "Relámpago",
            "Fuego",
            "Esperanza",
            "Destello",
            "Tormenta",
            "Centelleo",
            "Rayo",
            "Aurora",
            "Valiente",
            "Cielo",
            "Trueno",
            "Galope",
            "Sol",
            "Aventura",
            "Bravo",
            "Cascabel",
            "Dorado",
            "Viento",
            "Luna",
            "Majestad",
            "Ébano",
            "Amanecer",
            "Coraje",
            "Mariposa",
            "Corazón",
            "Susurro",
            "Canela",
            "Paloma",
            "Estrella",
            "Veloz",
            "Esplendor",
            "Duna",
            "Brisa",
            "Sombra",
            "Coral",
            "Crepúsculo",
            "Azabache",
            "Ciruela",
            "Sinfonía",
            "Rocío",
            "Melodía",
            "Canela",
            "Pegaso",
            "Esmeralda",
            "Rugido",
            "Cascada",
            "Magnífico",
            "Ambar",
            "Estampido",
            "Caramelo",
            "Dulzura",
            "Pirueta",
            "Elegancia",
            "Montaña",
            "Rubi",
            "Valle",
            "Júbilo",
            "Salto",
            "Flecha",
            "Piedra",
            "Zafiro",
            "Eclipse",
            "Travesía",
            "Coral",
            "Paloma",
            "Orquídea",
            "Jade",
            "Maravilla",
            "Suspiro",
            "Capricho",
            "Zarza",
            "Pétalo",
            "Río",
            "Abril",
            "Felicidad",
            "Ave",
            "Sonrisa",
            "Rocío",
            "Serenidad",
            "Amor",
            "Trébol",
            "Delfín",
            "Épico",
            "Olivo",
            "Ráfaga",
            "Cascabel",
            "Estampida",
            "Espléndido",
            "Gloria",
            "Travesía",
            "Ráfaga",
            "Canela",
            "Susurro",
            "Capricho",
            "Valle",
            "Montaña",
            "Aurora",
            "Maravilla",
            "Amor",
            "Ciruela"
    };
    Random rndm = new Random();
    public List<Cavall> creacioCavall(Cursa cursa) {
        List<Cavall> cavalls = new ArrayList<>();
        int quantitat = cursa.getQuantitatCavalls();

        List<String> usedNames = new ArrayList<>();

        for (int i = 0; i < quantitat; i++) {
            String randomName;
            do {
                randomName = nomsCavalls[rndm.nextInt(nomsCavalls.length)];
            } while (usedNames.contains(randomName));

            usedNames.add(randomName);

            boolean steroidUser = rndm.nextInt(100) > 80;
            Cavall cavall = new Cavall(randomName, 50, steroidUser);
            cavalls.add(cavall);
        }
        return cavalls;
    }

}
