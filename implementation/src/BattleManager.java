import java.util.ArrayList;
import java.util.List;

public class BattleManager {
    //curr state
    private BattleState state;

    List<Opponent> allOpponents;
    Player player;
    Database db;
    Opponent currOpponent;
    HealCentre nurseJoy;
    //first time when game starts,ask player to choose team. Else if he is restarting level,then dont ask to choose team.
    Boolean gameStart = false; 
    Boolean run = true; //for while loop to check if players team has lost all health or PP
    Boolean gameLoop = true; //run the entire game in a loop
    OpponentFacade opponentCreator;

    //amount of damage induced
    static int[][] damageMatrix = {
        {2,1,5},
        {5,2,1},
        {1,5,2}
    }; 

    public BattleManager(){
        state = null;

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

    //set the state of the Battle
    public void setState(BattleState state){
        this.state = state;
    }
    
    //get the curr state of the Battle class
    public BattleState getState(){
        return this.state;
    }
}
