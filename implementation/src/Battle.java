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
        gameLoop = false;
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
            System.out.println("+------MOVES LIST------+");
            System.out.print(player.myTeam.myPokemons.get(player.myTeam.currPokemon));
            System.out.print(player.myTeam.myPokemons.get(player.myTeam.currPokemon).moves);
            System.out.println("+------END OF LIST------+");

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
