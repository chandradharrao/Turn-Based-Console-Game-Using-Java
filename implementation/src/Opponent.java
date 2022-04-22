public class Opponent {
    public Team myTeam;
    public Badge theBadge;
    public String name;
    //level number associated with the gym leader
    public int level;

    Opponent(Database db,int level,String name){
        myTeam = new Team(db);
        this.level = level;
        this.name = name;

        //create opponent's team
        makeTeam();
    }

    public void generateBadge(){
        this.theBadge = new Badge(this.name,this.level);
    }

    //get difficulty of gym leader
    public static String getDifficulty(int lvl){
        switch(lvl){
            case 0:
                return Color.ANSI_GREEN+ "Easy"+Color.ANSI_RESET;
            case 1:
                return Color.ANSI_YELLOW+"Medium"+Color.ANSI_RESET;
            case 2:
                return Color.ANSI_RED+"Tough"+Color.ANSI_RESET;
            default:
                return "None";
        }
    }

    public void giveBadge(Player to){
        generateBadge();
        to.myBadges.add(theBadge);
    }

    private void makeTeam(){
        //create a random team of 6 pokemons (consider repetion too)
        for(int i = 0;i<6;i++){
            myTeam.addPokemon(0+(int)(Math.random()*(29-0)+1));
        }
    }

    public boolean attackPlayer(Player player){
        //check if curr pokemon has enough health
        boolean foundPokemon = false;
        for (int j = 0;j<myTeam.teamSize;j++){
            Pokemon myPoke = this.myTeam.myPokemons.get(j);
            if(myPoke.getHealth()>0){
                foundPokemon = true;
                //equip this as the curr pokemon
                myTeam.equipPokemon(j);
                //find move with enough pp and do damage
                boolean foundMove = false;
                for(int i = 0;i<myPoke.moves.theMoves.size();i++){
                    if(myPoke.moves.getPP(i)>0){
                        foundMove = true;
                        myTeam.useMove(i, player.myTeam);
                    }
                }
                if(foundMove) break;
            }
        }
        if(!foundPokemon){
            //player wins since opponent has no pokemon with enough PP
            myTeam.currPokemon = -1;
            return false;
        }
        //opponent executed his move with a pokemon he found in his team
        return true;
    }
}
