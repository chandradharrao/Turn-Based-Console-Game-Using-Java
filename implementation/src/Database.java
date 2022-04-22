import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public int numPokemons;
    public int[] starterIDs = {0,1,2,3,4,5,6,7,8,9,10};

    //list of all pokemons
    List<Pokemon> all_Pokemons;
    
    Database(){
        //fetch pokemons from database and store in all_pokemons list
        all_Pokemons = new ArrayList<Pokemon>();

        for(int i = 0;i<30;i++){
            all_Pokemons.add(new Pokemon(i, "pokemon"+i, i+50, 1, i+50));
            Logger.print(all_Pokemons.get(i).Name);
        }
        numPokemons = all_Pokemons.size();
    }

    //get the pokemon object at index
    Pokemon getPokemon(int indx){
        Logger.print("Retrieveing Pokemon: "+all_Pokemons.get(indx).Name);
        return all_Pokemons.get(indx);
    }

}
