public class Opponent {
    public Team myTeam;
    public Badge theBadge;
    public String name;
    //level number associated with the gym leader
    public int level;
    Database db;

    //starter poke ids for opponent
    int[] starterIDs;

    Opponent(Database db,int level,String name){
        this.level = level;
        this.name = name;
        this.db=db;

        //assign starter poke ids for opponent
        createStarterIDs();
        
        //create opponents team
        myTeam = new Team(db,this.starterIDs);
        Logger.print("OPPONENT constructor called");
        //add pokemons to the team
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

    private void createStarterIDs(){
        //create a random team of 6 pokemons (consider repetion too)
        int[] easyID = {21, 16, 39, 47, 88, 82};
        int[] mediumID = {22, 25, 40, 42, 76, 87};
        int[] hardID = {12, 15, 50, 56, 63, 84};
        int[] oppPokeID;

        Logger.print("The level is : " + level);
        switch(level) {
            case 0:
                oppPokeID = easyID;
                Logger.print("Level 0 easy ID pokes assigned");
                Logger.print(oppPokeID.toString());
                break;
            case 1:
                oppPokeID = mediumID;
                break;
            case 2:
                oppPokeID = hardID;
                break;
            default:
                oppPokeID = easyID;
        }
        starterIDs = oppPokeID;
    }

    private void makeTeam(){
        for(int i=0; i<6; i++){
            myTeam.addPokemon(starterIDs[i]);
            Logger.print("pokemon with id " + starterIDs[i] + "added");
        }
        Logger.print("The pokemon list is" + myTeam.teamSize);
    }

    public boolean attackPlayer(Player player){
        //check if curr pokemon has enough health
        boolean foundPokemon = false;
        Logger.print("Usable pokemons in opponent : " + myTeam.teamSize);

        for (int j = 0;j<myTeam.teamSize;j++){
            //get opponents cur pokemon
            Pokemon myPoke = this.myTeam.myPokemons.get(j);
            
            //if its health is > 0
            if(myPoke.getHealth()>0){
                foundPokemon = true;
                //equip this as the curr pokemon
                myTeam.equipPokemon(j);
                //find move with enough pp and do damage
                boolean foundMove = false;
                for(int i = 0;i<myPoke.moves.theMoves.size();i++){
                    if(myPoke.moves.getPP(i)>0){
                        foundMove = true;
                        Logger.print("Poke using the move is : " + myPoke.Name);

                        //display the opponet's curr pokemon
                        System.out.println(this.name + " 's"+" current pokemon");
                        String alignmentFormat = "|%-3s|%-10s|%-5s|%-6d|%-3d|%n";
                        System.out.format("+---+----------+-----+------+---+%n");
                        System.out.format("|ID |Name      |Type |Health|XP |%n");
                        System.out.format("+---+----------+-----+------+---+%n");
                        Pokemon oppCurrPoke = this.myTeam.myPokemons.get(this.myTeam.currPokemon);
                        System.out.format(alignmentFormat,oppCurrPoke.ID,oppCurrPoke.Name,oppCurrPoke.type,oppCurrPoke.health,oppCurrPoke.XP);
                        System.out.format("+---+----------+-----+------+---+%n");

                        System.out.println("Using Move: "+myTeam.myPokemons.get(myTeam.currPokemon).moves.theMoves.get(i).name);
                        myTeam.useMove(i, player.myTeam);
                        break;
                    }
                }
                if(foundMove) break;
                else if(!foundMove){
                    foundPokemon = false;
                }
            } 
        }
        Logger.print("foundPokemon Boolean is : " + foundPokemon);
        if(!foundPokemon){
            //player wins since opponent has no pokemon with enough PP
            myTeam.currPokemon = -1;
            System.out.println(name + " lost because he didnt have a pokemon with enough health and pp :)");
            return false;
        }
        //opponent executed his move with a pokemon he found in his team
        return true;
    }
}
