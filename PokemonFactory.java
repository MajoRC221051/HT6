import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class PokemonFactory {
    public static Map<String, Pokemon> createMap(String type) {
        switch (type) {
            case "1":
                return new HashMap<>();
            case "2":
                return new TreeMap<>();
            case "3":
                return new LinkedHashMap<>();
            default:
                throw new IllegalArgumentException("Tipo de mapa no válido");
        }
    }
}