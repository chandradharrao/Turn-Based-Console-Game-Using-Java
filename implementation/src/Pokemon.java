import java.util.ArrayList;

//create Pokemon class

/*
Each Pokemon should have a unique zero based id
Team.java has an array with index===pokemon.id
If user can access pokemon with id i,then arraavailablePokemons[i]=True

Shift EvolvePokemon() to Team because the player has the datastructure needed
for this method, leads to low coupling
XP can of pokemon can be obtained using getXP()
*/

public class Pokemon{
    protected int health;
    protected int maxHealth;
    protected int XP;
    protected int ID;
    protected String Name;
    protected int type;
    public Moves moves;

    Pokemon(int ID, String Name, int maxHealth, int type, int XP){
        this.ID = ID;
        this.Name = Name;
        this.maxHealth = maxHealth;
        this.health= maxHealth;
        this.type = type;
        this.XP = XP;

        //add moves for pokemon --only for testing purposes--
        this.moves = new Moves();
        this.moves.theMoves = new ArrayList<Move>();
        for(int i = 0;i<4;i++)
            moves.theMoves.add(new Move("Move"+i, i+3, i+7));
    }   

    //returns the health of the pokemon
    public int getHealth(){
        return health;
    }

    //returns experience points
    public int getXP(){
        return XP;
    }

    //returns ID of pokemon -  zero based index
    public int getID(){
        return ID;
    }

    //returns name of pokemon
    public String getName(){
        return Name;
    }

    //returns type of pokemon
    public int getType(){
        return type;
    }

    //mapping b/w types
    public static String getType(int type){
        switch (type){
            case 0:
                return "Fire";
            case 1:
                return "Water";
            case 2:
                return "Grass";
            default:
                return "None";
        }
    }

    //sets the health of the pokemon during battle
    public void changeHealth(int health){
        this.health=this.health-health;
    }

    //sets the experience points of the pokemon
    public void changeXP(int XP){
        this.XP=this.XP+XP;
    }

    //display  a single Pokemon
    @Override
    public String toString() {
        return "Name:"+this.Name+"\n"+"Type:"+getType(this.type)+"\n"+"Health:"+this.health+"\n"+"XP:"+this.XP+"\n";
    }

    public void healMe(){
        this.health = maxHealth;
    }

    //display the list of moves available in the pokemon
    
}