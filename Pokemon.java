public class Pokemon {
    private String name;
    private String type1;
    private String type2;
    private double height;
    private double weight;
    private String abilities;
    private int generation;
    private boolean legendaryStatus;

    public Pokemon(String name, String pokedexNumber, String type1, String type2, String classification,
                   double height, double weight, String abilities, int generation, boolean legendaryStatus) {
        this.name = name;

        this.type1 = type1;
        this.type2 = type2;
        this.height = height;
        this.weight = weight;
        this.abilities = abilities;
        this.generation = generation;
        this.legendaryStatus = legendaryStatus;
    }

    public String getName() {
        return name;
    }

    public String getType1() {
        return type1;
    }

    public String getAbilities() {
        return abilities;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Type1: " + type1 + ", Type2: " + type2 + ", Height: " + height +
                "m, Weight: " + weight + "kg, Abilities: " + abilities + ", Generation: " + generation +
                ", Legendary: " + legendaryStatus;
    }
}