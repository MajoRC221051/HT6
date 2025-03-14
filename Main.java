import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Preguntar al inicio qué tipo de mapa se desea utilizar
        System.out.println("Seleccione la implementación de Map: 1) HashMap 2) TreeMap 3) LinkedHashMap");
        String choice = scanner.nextLine();
        Map<String, Pokemon> pokemonMap = PokemonFactory.createMap(choice);
        PokemonManager manager = new PokemonManager(pokemonMap);

        try {
            manager.loadPokemonsFromCSV("Pokemon.csv");
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
            return;
        }

        while (true) {
            System.out.println(" _______________________________ ");
            System.out.println("|  ______   POKÉDEX             |");
            System.out.println("| |      | -------------------  |");
            System.out.println("| |  ▄▀▄  |                     |");
            System.out.println("| |  ▀█▀  |                     |");
            System.out.println("| |______|                      |");
            System.out.println("|                                |");
            System.out.println("|  ██████████████████████████   |");
            System.out.println("|  █ [1] Agregar Pokémon     █   |");
            System.out.println("|  █ [2] Ver datos           █   |");
            System.out.println("|  █ [3] Listar por Tipo     █   |");
            System.out.println("|  █ [4] Buscar Habilidad    █   |");
            System.out.println("|  █ [5] Cambiar tipo de Map █   |");
            System.out.println("|  █ [6] Salir               █   |");
            System.out.println("|  ██████████████████████████   |");
            System.out.println("|________________________________|");
            System.out.print("Seleccione una opción: ");

            int operation = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            long startTime, endTime, duration;

            switch (operation) {
                case 1:
                    System.out.println("Ingrese el nombre del Pokémon a agregar:");
                    String nameToAdd = scanner.nextLine();
                    startTime = System.nanoTime();
                    manager.addPokemon(nameToAdd);
                    endTime = System.nanoTime();
                    duration = endTime - startTime;
                    System.out.println("Tiempo de ejecución: " + duration + " nanosegundos");
                    break;
                case 2:
                    System.out.println("Ingrese el nombre del Pokémon a mostrar:");
                    String nameToShow = scanner.nextLine();
                    startTime = System.nanoTime();
                    manager.showPokemon(nameToShow);
                    endTime = System.nanoTime();
                    duration = endTime - startTime;
                    System.out.println("Tiempo de ejecución: " + duration + " nanosegundos");
                    break;
                case 3:
                    System.out.println("Pokémon en su colección ordenados por Type1:");
                    startTime = System.nanoTime();
                    manager.showAllByType1();
                    endTime = System.nanoTime();
                    duration = endTime - startTime;
                    System.out.println("Tiempo de ejecución: " + duration + " nanosegundos");
                    break;
                case 4:
                    System.out.println("Ingrese la habilidad:");
                    String ability = scanner.nextLine();
                    System.out.println("Pokémon con la habilidad '" + ability + "':");
                    startTime = System.nanoTime();
                    manager.showByAbility(ability);
                    endTime = System.nanoTime();
                    duration = endTime - startTime;
                    System.out.println("Tiempo de ejecución: " + duration + " nanosegundos");
                    break;
                case 5:
                    System.out.println("Seleccione la nueva implementación de Map: 1) HashMap 2) TreeMap 3) LinkedHashMap");
                    String newChoice = scanner.nextLine();
                    pokemonMap = PokemonFactory.createMap(newChoice);
                    manager = new PokemonManager(pokemonMap);
                    try {
                        manager.loadPokemonsFromCSV("Pokemon.csv");
                    } catch (IOException e) {
                        System.out.println("Error al cargar el archivo: " + e.getMessage());
                    }
                    System.out.println("Tipo de mapa cambiado exitosamente.");
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Operación no válida. Intente de nuevo.");
            }
        }
    }
}