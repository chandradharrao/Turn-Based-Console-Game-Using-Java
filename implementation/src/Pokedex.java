import java.util.List;

public class Pokedex{
    protected int numPokemons;
    protected Database db;
    protected List<Pokemon> all_Pokemons;
    
    private static Pokedex pokedex;

    Pokedex(Database db){
        this.db = db;
        numPokemons=db.numPokemons;
        all_Pokemons = db.all_Pokemons;
    }

    public static Pokedex getInstance(){
        if(pokedex==null){
            pokedex = new Pokedex(Database.getInstance());
        }
        return pokedex;
    }
    public void listAllPokemons(){
        db.getAllPokemon();
    }

    // public void getInforFromName(String name){
    //     for(int i=0;i<numPokemons;i++){
    //         if(all_Pokemons.get(i).Name.equals(name)){
    //             Pokemon pokemon = all_Pokemons.get(i);
    //             String alignment = "|%-9s|%-12d|%-11d|%-9|%n";
    //             System.out.format("+---------+------------+-----------+--------+%n");
    //             System.out.format("|ID       |Name        |Max Health |Type    |%n");
    //             System.out.format("+---------+------------+-----------+--------+%n");
    //             System.out.format(alignment, pokemon.ID, pokemon.Name, pokemon.maxHealth, pokemon.type);
    //             System.out.format("+---------+------------+-----------+--------+%n");
    //         }
    //     }
    //}
    
    public void getInfoFromName(String name){
        View.getInforFromName(db, name);
    }

    public void getInfoFromPokemon(Pokemon p){
        View.getInfoFromPokemon(db, p);
    }
    // public void getInfoFromPokemon(Pokemon p){
    //    for(int i=0 ; i<numPokemons; i++){
    //           if(all_Pokemons.get(i).Name.equals(p.Name)){
    //             String alignment = "|%-9s|%-12d|%-11d|%-9|%n";
    //             System.out.format("+---------+------------+-----------+--------+%n");
    //             System.out.format("|ID       |Name        |Max Health |Type    |%n");
    //             System.out.format("+---------+------------+-----------+--------+%n");
    //             System.out.format(alignment, p.ID, p.Name, p.maxHealth, p.type);
    //             System.out.format("+---------+------------+-----------+--------+%n");
    //           }
    //    }
    // }
}