import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public int numPokemons;
    public int[] starterIDs;

    //list of all pokemons
    List<Pokemon> all_Pokemons;
    
    Database(){
        //fetch pokemons from database and store in all_pokemons list
    }

    //get the pokemon object at index
    Pokemon getPokemon(int indx){
        return all_Pokemons.get(indx);
    }

}
