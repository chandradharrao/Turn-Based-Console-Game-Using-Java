import java.util.List;
import java.io.*;

public class Battle {
    Game newGame;
    List<Opponent> allOpponents;
    Player player;
    Database db;
    Opponent currOpponent;

    //amount of damage induced
    static int[][] damageMatrix = {
        {10,5,20},
        {20,10,5},
        {5,20,10}
    }; 

    Battle(){
        //create all gym leaders and fill in the allOpponents array
        db = new Database();
        player = new Player(db);

        //create 3 gym leaders
        allOpponents.add(new Opponent(db, 0,"Maylene"));
        allOpponents.add(new Opponent(db, 1, "Misty"));
        allOpponents.add(new Opponent(db, 2, "Brock"));
    }
    
    public void EndGame(){
        System.out.println("Game Over");
    }

    public void createTeam(){
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

    public void chooseOpponent(){
        System.out.println("Choose your opponent: ");
        for (Opponent opponent : allOpponents) {
            System.out.println(opponent.toString());
        }

        //get user input
        try {
            this.currOpponent = allOpponents.get(Integer.parseInt(System.console().readLine()));
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }
    }

    public void StartBattle(){
        this.createTeam();
        this.chooseOpponent();

        while(true){
            //display team
            System.out.println("Your team details:");
            player.myTeam.viewTeam();

            System.out.println("Your opponent team details:");
            currOpponent.myTeam.viewTeam();


            //choose current pokemon in team
            System.out.println("Choose your current pokemon: ");
            int currPoke = Integer.parseInt(System.console().readLine());
            player.myTeam.equipPokemon(currPoke);
            
            //display move of current pokemon
            for(int i = 0;i<player.myTeam.teamSize;i++){
                System.out.print(player.myTeam.myPokemons.get(player.myTeam.currPokemon));
                System.out.print(player.myTeam.myPokemons.get(i).moves);
            }

            //ask player to choose move and attack
            System.out.println("Choose move:");
            int move = Integer.parseInt(System.console().readLine());
            player.myTeam.useMove(move,currOpponent.myTeam);

            //opponent attacks player
            Boolean didOpponentLoose = currOpponent.attackPlayer(player);
            if(didOpponentLoose){
                System.out.println("You win!!");
                break;
            }
        }
    }
}
