import java.util.ArrayList;
import java.util.List;

public class Player {
    //players battle team currently in use
    Team myTeam;
    String name;
    Database db;

    //players badges gathered
    public List<Badge> myBadges;

    Player(Database db){
        this.db = db;
        myTeam = new Team(db,db.starterIDs);
        playerDetails();

        //list of badges collected by the player
        myBadges = new ArrayList<Badge>();
    }

    void playerDetails(){
        System.out.println("Enter name");
        name = System.console().readLine();
        Logger.print("Name is "+name);
    }

    public void createTeam(){
        //Display all the pokemons available to the user
        View.listAvailablePokes(myTeam.availablePokemons,db);
        
        //ask user to choose between displayed pokemon
        while(myTeam.teamSize<6){
            System.out.println("Enter the Pokemon number");
            int pnum = Integer.parseInt(System.console().readLine());

            boolean res = myTeam.addPokemon(pnum);
            if(!res){
                System.out.println("Not allowed to choose that pokemon yet!");
            }
        
            //after choosing 6 pokemons
            View.displayTeam(myTeam);
        }
    }
}
