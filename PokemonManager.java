import java.io.*;
import java.util.*;

public class PokemonManager {
    private Map<String, Pokemon> pokemonCollection;
    private List<String> addedPokemons;

    public PokemonManager(Map<String, Pokemon> pokemonCollection) {
        this.pokemonCollection = pokemonCollection;
        this.addedPokemons = new ArrayList<>();
    }

    public void addPokemon(String name) {
        if (pokemonCollection.containsKey(name.trim())) {
            if (!addedPokemons.contains(name.trim())) {
                addedPokemons.add(name.trim());
                saveAddedPokemonToFile(name.trim());
                System.out.println("Pokémon agregado a la colección: " + name);
            } else {
                System.out.println("El Pokémon ya está en la colección.");
            }
        } else {
            System.out.println("El Pokémon no se encuentra en los datos leídos.");
        }
    }

    public void showPokemon(String name) {
        Pokemon pokemon = pokemonCollection.get(name);
        if (pokemon != null) {
            System.out.println(pokemon);
        } else {
            System.out.println("Pokémon no encontrado.");
        }
    }

    public void showAllByType1() {
        pokemonCollection.values().stream()
                .sorted(Comparator.comparing(Pokemon::getType1))
                .forEach(pokemon -> System.out.println(pokemon.getName() + " - " + pokemon.getType1()));
    }

    public void showByAbility(String ability) {
        pokemonCollection.values().stream()
                .filter(pokemon -> pokemon.getAbilities().contains(ability))
                .forEach(pokemon -> System.out.println(pokemon.getName()));
    }

    public void loadPokemonsFromCSV(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        br.readLine(); // Omitir la primera línea (encabezados)
        while ((line = br.readLine()) != null) {
            System.out.println("Procesando línea: " + line); // Imprimir la línea
            String[] data = line.split(",");
            if (data.length < 10) {
                System.out.println("Línea ignorada (menos de 10 campos): " + line);
                continue;
            }
    
            try {
                String name = data[0].trim();
                String pokedexNumber = data[1].trim();
                String type1 = data[2].trim();
                String type2 = data[3].trim();
                String classification = data[4].trim();
                double height = Double.parseDouble(data[5].trim());
                double weight = Double.parseDouble(data[6].trim());
                String abilities = data[7].trim();
                int generation = Integer.parseInt(data[8].trim());
                boolean legendaryStatus = Boolean.parseBoolean(data[9].trim());
    
                Pokemon pokemon = new Pokemon(name, pokedexNumber, type1, type2, classification,
                        height, weight, abilities, generation, legendaryStatus);
                pokemonCollection.put(pokemon.getName(), pokemon);
                System.out.println("Cargado Pokémon: " + pokemon.getName());
            } catch (NumberFormatException e) {
                System.out.println("Error al procesar la línea: " + line + " - " + e.getMessage());
            }
        }
        br.close();
    
        // Imprimir todos los Pokémon cargados
        System.out.println("Pokémon cargados en la colección:");
        for (String pokemonName : pokemonCollection.keySet()) {
            System.out.println(pokemonName);
        }
    }

    private void saveAddedPokemonToFile(String name) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("added_pokemons.txt", true))) {
            bw.write(name + "\n");
        } catch (IOException e) {
            System.out.println("Error al guardar el Pokémon agregado: " + e.getMessage());
        }
    }
}