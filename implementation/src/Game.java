import java.io.Console;
import java.util.ArrayList;
import java.util.List;

class Game{
    Database db;
    Player player;

    Game(){
        db = new Database();
        player = new Player(db.numPokemons,db.starterIDs);
        Opponent opponent = new Opponent();
    }

    public void choosePokemons(){
        //ask user to choose between displayed pokemon
        while(player.myTeam.teamSize<6){
            System.out.println("Enter the Pokemon number");
            int pnum = Integer.parseInt(System.console().readLine());

            boolean res = player.myTeam.addPokemon(pnum);
            if(!res){
                System.out.println("Not allowed to choose that pokemon yet!");
            }
        }

        //after choosing 6 pokemons
        player.myTeam.viewTeam();
    }
}