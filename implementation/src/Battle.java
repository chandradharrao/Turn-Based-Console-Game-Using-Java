import java.util.ArrayList;
import java.util.List;

public class Battle {
    Game newGame;
    List<Opponent> allOpponents;
    Player player;
    Database db;
    Opponent currOpponent;
    HealCentre nurseJoy;
    //first time when game starts,ask player to choose team. Else if he is restarting level,then dont ask to choose team.
    Boolean gameStart = false; 
    Boolean run = true; //for while loop to check if players team has lost all health or PP

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
        nurseJoy = new HealCentre();
        allOpponents = new ArrayList<Opponent>();
        gameStart = true;

        //create 3 gym leaders
        allOpponents.add(new Opponent(db, 0,"Maylene"));
        allOpponents.add(new Opponent(db, 1, "Misty"));
        allOpponents.add(new Opponent(db, 2, "Brock"));
    }
    
    public void EndGame(){
        System.out.println("Game Over");
        run = false;
    }

    public void createTeam(){
        //Display all the pokemons available to the user
        
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
        if(gameStart){
            gameStart= false;
            this.createTeam();
        }
        this.chooseOpponent();

        while(run){
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
            System.out.print(player.myTeam.myPokemons.get(player.myTeam.currPokemon));
            System.out.print(player.myTeam.myPokemons.get(player.myTeam.currPokemon).moves);

            //ask player to choose move and attack
            System.out.println("Choose move:");
            int move = Integer.parseInt(System.console().readLine());
            player.myTeam.useMove(move,currOpponent.myTeam);

            //opponent attacks player
            Boolean didOpponentLoose = currOpponent.attackPlayer(player);
            if(didOpponentLoose){
                System.out.println("You win!!");
                currOpponent.giveBadge(player);
                System.out.println("Do you want to heal your pokemon before next battle? Press Y or N");
                char doHeal = System.console().readLine().charAt(0);
                
                if(doHeal=='Y'){
                    nurseJoy.healWork(player.myTeam);
                }
                break;
            }
            else{
                //opponent didnt loose,doesnt mean player lost
                if(player.myTeam.didTeamLoose()){
                    System.out.println("You lost :(");
                    this.EndGame();
                }
            }
        }
    }
}
