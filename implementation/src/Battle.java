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

        Logger.print("Number of opponents: "+ allOpponents.size());
    }
    
    public void EndGame(){
        System.out.println("Game Over");
        run = false;
        gameLoop = false;
    }

    public void createTeam(){
        //Display all the pokemons available to the user
        player.myTeam.listAvailablePokes();
        
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
        player.myTeam.viewTeam();
    }

    public void chooseOpponent(){
        System.out.println("Choose your opponent:");
        String alignmentFormat = "|%-10s|%-20s|%n";

        System.out.format("+----------+-----------+%n");
        System.out.format("|Name      |Difficulty |%n");
        System.out.format("+----------+-----------+%n");
        for (Opponent opponent : allOpponents) {
            String diffulty = Opponent.getDifficulty(opponent.level);
            System.out.format(alignmentFormat,opponent.name,diffulty);
        }
        System.out.format("+----------+-----------+%n");

        //get user input
        try {
            this.currOpponent = allOpponents.get(Integer.parseInt(System.console().readLine()));
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
        }
    }

    private void fightOpponent(){
        while(run){

            //display team
            System.out.println("Your team details:");
            player.myTeam.viewTeam();

            System.out.println(currOpponent.name+ "'s team details:");
            currOpponent.myTeam.viewTeam();

            //choose current pokemon in team
            System.out.println("Choose your current pokemon: ");
            int currPoke = Integer.parseInt(System.console().readLine());
            player.myTeam.equipPokemon(currPoke);
            
            //display move of current pokemon
            Pokemon poke = player.myTeam.myPokemons.get(player.myTeam.currPokemon);
            System.out.println("Moves of "+poke.Name+" are:");
            String alignment = "|%-20s|%-4d|%-6d|%n";
            System.out.format("+--------------------+----+------+%n");
            System.out.format("|Name                |PP  |Damage|%n");
            System.out.format("+--------------------+----+------+%n");
            for (Move move : poke.moves.theMoves) {
                System.out.format(alignment,move.name,move.pp,move.damage);
            }
            System.out.format("+--------------------+----+------+%n");

            //ask player to choose move and attack
            System.out.println("Choose a move:");
            int move = Integer.parseInt(System.console().readLine());
            player.myTeam.useMove(move,currOpponent.myTeam);
            Logger.print("After player attacks,opponent health is: " + currOpponent.myTeam.myPokemons.get(currOpponent.myTeam.currPokemon).health);

            //opponent attacks player
            System.out.println(currOpponent.name +" is attacking you......");
            Boolean didOpponentLoose = currOpponent.attackPlayer(player);
            if(!didOpponentLoose){
                System.out.format("+---------------------------+%n");
                String alignment1 = "|%-50s|%n";
                System.out.format(alignment1,Color.ANSI_GREEN+"You Win"+Color.ANSI_RESET);
                System.out.format("+---------------------------+%n");

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
