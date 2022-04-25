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
    Boolean gameLoop = true; //run the entire game in a loop

    //amount of damage induced
    static int[][] damageMatrix = {
        {2,1,5},
        {5,2,1},
        {1,5,2}
    }; 

    OpponentFacade opponentCreator;

    Battle(){

        //singleton design pattern
        db = Database.getInstance();

        //facade design pattern
        opponentCreator = new OpponentFacade();

        //create all gym leaders and fill in the allOpponents array
        player = new Player(db);
        nurseJoy = new HealCentre();
        allOpponents = new ArrayList<Opponent>();
        gameStart = true;
        

        //create 3 gym leaders
        allOpponents.add(opponentCreator.createOpponent("Maylene", db));
        allOpponents.add(opponentCreator.createOpponent("Misty", db));
        allOpponents.add(opponentCreator.createOpponent("Brock", db));

        Logger.print("Number of opponents: "+ allOpponents.size());
    }
    
    public void EndGame(){
        System.out.println("Game Over");
        run = false;
        gameLoop = false;
    }

    public void createTeam(){
        //Display all the pokemons available to the user
        View.listAvailablePokes(player.myTeam.availablePokemons,db);
        
        // //ask user to choose between displayed pokemon
        // while(player.myTeam.teamSize<6){
        //     System.out.println("Enter the Pokemon number");
        //     int pnum = Integer.parseInt(System.console().readLine());

        //     boolean res = player.myTeam.addPokemon(pnum);
        //     if(!res){
        //         System.out.println("Not allowed to choose that pokemon yet!");
        //     }

        //for testing purposes
        for(int i=0 ; i<6; i++){
            player.myTeam.addPokemon(db.starterIDs[i]);
        }
        
             //after choosing 6 pokemons
            View.displayTeam(player.myTeam);
        }
    //}

    public void chooseOpponent(){
        View.displayOpponents(allOpponents);

        //get user input
        try {
            this.currOpponent = allOpponents.get(Integer.parseInt(System.console().readLine()));
        } catch (Exception e) {
            System.out.println(e);
            this.chooseOpponent();
        }
    }

    private void fightOpponent(){
        while(run){

            //display team
            System.out.println("Your team details:");
            View.displayTeam(player.myTeam);

            System.out.println(currOpponent.name+ "'s team details:");
            View.displayTeam(currOpponent.myTeam);

            //choose current pokemon in team
            System.out.println("Choose your current pokemon: ");
            int currPoke = Integer.parseInt(System.console().readLine());
            player.myTeam.equipPokemon(currPoke);

            //display move of current pokemon
            Pokemon poke = player.myTeam.myPokemons.get(player.myTeam.currPokemon);

            View.displayMoves(poke.moves.theMoves,poke.Name);

            //ask player to choose move and attack
            System.out.println("Choose a move:");
            int move = Integer.parseInt(System.console().readLine());
            player.myTeam.useMove(move,currOpponent.myTeam);
            Logger.print("After player attacks,opponent health is: " + currOpponent.myTeam.myPokemons.get(currOpponent.myTeam.currPokemon).health);

            //opponent attacks player
            System.out.println(currOpponent.name +" is attacking you......");
            Boolean didOpponentLoose = currOpponent.attackPlayer(player);
            if(!didOpponentLoose){
                View.displayWin();

                currOpponent.giveBadge(player);
                System.out.println("You recived a badge from: "+player.myBadges.get(player.myBadges.size()-1).gymLeaderName+ " !");
                
                System.out.println("Do you want to heal your pokemon before next battle?\nPress Y or N");
                char doHeal = System.console().readLine().charAt(0);
                
                if(doHeal=='Y'){
                    nurseJoy.healWork(player.myTeam);
                }
                else{
                    System.out.println("Gotta admire your confidence to go with a wounded Team!");
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

    public void StartBattle(){
        if(gameStart){
            gameStart= false;
            this.createTeam();
        }
        while(gameLoop){
            this.chooseOpponent();
            fightOpponent();
            System.out.println("Get Ready for your next battle...");
        }
    }
}
