import java.util.*;

public class Team {
    public List<Pokemon> myPokemons;
    public int currPokemon;
    public int teamSize;
    private int numPokemons;
    private int[] starterPokeIDs;
    private Database db;

    //XP needed for levels
    private int level1 = 0;
    private int level2 = 100;
    private int level3 = 200;

    //<pokemonID-availability> map per user. If a pokemon with id j is available,make avilablePokemon[j] = True
    boolean[] availablePokemons;

    Team(Database db){
        this.db = db;

        teamSize = 0;
        myPokemons = new ArrayList<Pokemon>();

        numPokemons = db.numPokemons;
        starterPokeIDs = db.starterIDs;

        //allpokemons except starter pokemons
        availablePokemons = new boolean[numPokemons];
        for(int i = 0;i<numPokemons;i++){
            availablePokemons[i] = false;
        }

        //only starter poekmons avaiable intially
        for(int i = 0;i<starterPokeIDs.length;i++){
            availablePokemons[starterPokeIDs[i]] = true;
        }
    }

    //evolve a certain pokemon
    public void evolvePokemons(){
        //list of prev pokemons to remove
        List<Integer> toRem = new ArrayList<Integer>();

        //if XP of a pokemon crosses certain threshold, remove the pokemon from the team and insert the evolved pokemon into the team
        for(int i = 0;i<myPokemons.size();i++){
            Pokemon poke = myPokemons.get(i);
            if(poke.getXP()>poke.evolveThreshold){
                Pokemon evolvedPoke = db.getPokemon(poke.evolutionID);

                //transfer XP,health from prev pokemon
                evolvedPoke.XP = poke.getXP();
                evolvedPoke.health = poke.getHealth();
                myPokemons.add(evolvedPoke);

                toRem.add(i);
            }
        }

        //remove the pokemons that evoleved
        for (Integer i : toRem) {
            myPokemons.remove(myPokemons.get(i));
        }
    }

    public boolean addPokemon(int i){
        Logger.print("is m_pokemon list null? "+myPokemons==null?"True":"False");

        if(availablePokemons[i]){
            Pokemon poke = db.getPokemon(i);
            myPokemons.add(poke);
            teamSize+=1;
            return true;
        }else{
            return false;
        }
    }

    public void viewTeam(){
        String alignmentFormat = "|%-10s|%-5s|%-6d|%-3d|%n";

        System.out.format("+----------+-----+------+---+%n");
        System.out.format("|Name      |Type |Health|XP |%n");
        System.out.format("+----------+-----+------+---+%n");
        for(Pokemon poke: myPokemons){
            System.out.format(alignmentFormat,poke.Name,Pokemon.getType(poke.type),poke.health,poke.XP);
        }
        System.out.format("+----------+-----+------+---+%n");
    }

    //get total team XP so that some pokemons can be made avaiable if threshold of needed XP is crossed
    void updateAvailability(){
        int totalXP = 0;
        for (Pokemon p : myPokemons) {
            totalXP+=p.XP;
        }
        if(level1<totalXP && totalXP<=level2){
            //make next 10 pokemons avilable for selection
            for(int i = 0;i<10;i++){
                availablePokemons[i] = true;
            }
        }
        else if(level2<totalXP && totalXP<=level3){
            for(int i = 0;i<10;i++){
                availablePokemons[i] = true;
            }
        }
        else{
            //make all pokeons avaialble
            for(int i = 0;i<availablePokemons.length;i++){
                availablePokemons[i] = true;
            }
        }
    }

    //choose a pokemon for battle
    public void equipPokemon(int i){
        this.currPokemon = i;
    }

    //reduce curr pokemon health
    public void damage(int damageAmnt){
        this.myPokemons.get(this.currPokemon).changeHealth(damageAmnt);
    }

    //gain XP or loose XP
    public void changeXP(int xpAmnt){
        updateAvailability();
        this.myPokemons.get(this.currPokemon).changeXP(xpAmnt);
    }

    //use a move from current pokemon
    public void useMove(int moveNumber,Team opponentTeam){
        if(this.myPokemons.get(this.currPokemon).moves.getPP(moveNumber)>0){
            
            //reduce moves PP for curr pokemon
            this.myPokemons.get(this.currPokemon).moves.reducePP(moveNumber);

            //grab damage from amage matrix
            opponentTeam.damage(Battle.damageMatrix[this.myPokemons.get(this.currPokemon).type][opponentTeam.myPokemons.get(opponentTeam.currPokemon).type]);
            this.changeXP(15);
            this.myPokemons.get(this.currPokemon).moves.reducePP(moveNumber);
        }else{
            System.out.println("Not enough PP :(");
        }
    }

    //fetch all pokemons in the database avaialble to the user
    public void listAvailablePokes(){
        System.out.println("Choose From:");
        String alignmentFormat = "|%-3s|%-10s|%-5s|%-6d|%-3d|%n";

        System.out.format("+---+----------+-----+------+---+%n");
        System.out.format("|ID |Name      |Type |Health|XP |%n");
        System.out.format("+---+----------+-----+------+---+%n");
        for(int i = 0;i<availablePokemons.length;i++){
            if(availablePokemons[i]){
                Pokemon poke = db.getPokemon(i);
                System.out.format(alignmentFormat,poke.ID-1,poke.Name,Pokemon.getType(poke.type),poke.health,poke.XP);
            }
        }
        System.out.format("+---+----------+-----+------+---+%n");
    }

    //check if Team has lost
    public Boolean didTeamLoose(){
        //for all pokemons
        Boolean didLoose = true;
        for(int i = 0;i<teamSize;i++){
            //even if one pokemon has health and it has PP for some move
            Pokemon currPoke = this.myPokemons.get(i);

            if(currPoke.health>0){
                //check if any move has acceptable PP
                Boolean validMoves = false;
                for(int j = 0;j<currPoke.moves.theMoves.size();j++){
                    if(currPoke.moves.theMoves.get(j).pp>0){
                        validMoves = true;
                        break;
                    }
                }
                if(validMoves == true){
                    didLoose = false;
                    break;
                }
            }
        }
        return didLoose;
    }
}














