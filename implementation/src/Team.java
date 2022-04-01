public class Team {
    public List<Pokemon> myPokemons;
    public Pokemon currPokemon;

    public void viewTeam(){
        Game.displayPokemons(myPokemons, Integer.MAX_VALUE);
    }
}
