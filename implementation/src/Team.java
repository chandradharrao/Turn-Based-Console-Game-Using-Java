import java.util.*;

public class Team {
    public List<Pokemon> myPokemons;
    public int currPokemon;
    public int teamSize;
    private int numPokemons;
    private int[] starterPokeIDs;
    private Database db;

    //<pokemonID-availability> map per user. If a pokemon with id j is available,make avilablePokemon[j] = True
    boolean[] availablePokemons;

    Team(Database db){
        teamSize = 0;

        numPokemons = db.numPokemons;
        starterPokeIDs = db.starterIDs;

        //allpokemons except starter pokemons
        availablePokemons = new boolean[numPokemons];
        for(int i = 0;i<numPokemons;i++){
            availablePokemons[i] = false;
        }

        //only starter poekmons avaiable intially
        for(int i = 0;i<starterPokeIDs.length;i++){
            availablePokemons[i] = true;
        }
    }

    public boolean addPokemon(int i){
        if(availablePokemons[i]){
            myPokemons.add(db.getPokemon(i));
            teamSize+=1;
            return true;
        }else{
            return false;
        }
    }

    public void viewTeam(){
        for(Pokemon poke: myPokemons){
            System.out.println(poke.toString());
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
        }else{
            System.out.println("Not enough PP :(");
        }
    }
}
