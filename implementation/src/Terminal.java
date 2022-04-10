
public class Terminal {
    public static void main(String[] args) {
        while(true){
            System.out.println("Avaiable Pokemons:\n");

            //fetch pokemons from db
            List<Pokemon> allPokemons = db.getPokemons();

            //ask user to choose 6 pokemon
            for(int i = 0;i<6;i++){
                player.myPokemons[i] = choosePokemon(allPokemons,player.XP);
            }


        }
    }   
}
