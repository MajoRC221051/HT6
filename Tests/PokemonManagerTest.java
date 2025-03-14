import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PokemonManagerTest {
    private PokemonManager pokemonManager;
    private Map<String, Pokemon> pokemonCollection;

    @BeforeEach
    void setUp() {
        pokemonCollection = new HashMap<>();
        pokemonManager = new PokemonManager(pokemonCollection);
    }

    @Test
    void testAddPokemon_Success() {
        Pokemon pikachu = new Pokemon("Pikachu", "025", "Electric", "", "Mouse Pokémon", 0.4, 6.0, "Static", 1, false);
        pokemonCollection.put("Pikachu", pikachu);

        pokemonManager.addPokemon("Pikachu");
        assertTrue(pokemonCollection.containsKey("Pikachu"));
    }

    @Test
    void testAddPokemon_AlreadyAdded() {
        Pokemon pikachu = new Pokemon("Pikachu", "025", "Electric", "", "Mouse Pokémon", 0.4, 6.0, "Static", 1, false);
        pokemonCollection.put("Pikachu", pikachu);

        pokemonManager.addPokemon("Pikachu");
        pokemonManager.addPokemon("Pikachu"); // Segunda vez

        assertEquals(1, pokemonCollection.size());
    }

    @Test
    void testAddPokemon_NotFound() {
        pokemonManager.addPokemon("Charizard");
        assertFalse(pokemonCollection.containsKey("Charizard"));
    }

    @Test
    void testShowPokemon_Found() {
        Pokemon charmander = new Pokemon("Charmander", "004", "Fire", "", "Lizard Pokémon", 0.6, 8.5, "Blaze", 1, false);
        pokemonCollection.put("Charmander", charmander);

        assertDoesNotThrow(() -> pokemonManager.showPokemon("Charmander"));
    }

    @Test
    void testShowPokemon_NotFound() {
        assertDoesNotThrow(() -> pokemonManager.showPokemon("Bulbasaur"));
    }

    @Test
    void testLoadPokemonsFromCSV() throws IOException {
        // Crear un archivo CSV de prueba
        String testCsvPath = "test_pokemon.csv";
        try (FileWriter writer = new FileWriter(testCsvPath)) {
            writer.write("Name,PokedexNumber,Type1,Type2,Classification,Height,Weight,Abilities,Generation,Legendary\n");
            writer.write("Squirtle,007,Water,,Tiny Turtle,0.5,9.0,Torrent,1,false\n");
            writer.write("Charmander,004,Fire,,Lizard,0.6,8.5,Blaze,1,false\n");
        }

        pokemonManager.loadPokemonsFromCSV(testCsvPath);
        assertEquals(2, pokemonCollection.size());
        assertTrue(pokemonCollection.containsKey("Squirtle"));
        assertTrue(pokemonCollection.containsKey("Charmander"));
    }
}
