import java.io.Console;

import javax.xml.crypto.Data;

class Game{
    Game(){
        Database db = new Database();
        Player player = new Player();
        Opponent opponent = new Opponent();
    }

    public static void displayPokemons(List<Pokemon>allPokemons,int constraint){
        int i =0;
        for (Pokemon p : allPokemons) {
            if(p.XP <= constraint){
                System.out.println(i++);
                System.out.println(p);
            }
        }
    }

    public Pokemon choosePokemon(List<Pokemon> allPokemons,int constraint){
        //display the pokemons
        displayPokemons(allPokemons, constraint);

        //ask user to choose between displayed pokemon
        while(true){
            System.out.println("Enter the Pokemon number");
            int pnum = Integer.parseInt(System.console().readLine());

            if(pnum<allPokemons.size()){
                return allPokemons[pnum];
            }
            else{
                System.out.println("Choose correct pokemon number");
            }
        }
    }


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