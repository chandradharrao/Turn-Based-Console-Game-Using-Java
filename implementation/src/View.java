import java.util.*;

class View{
    public static void displayOpponents(List<Opponent> allOpponents){
        System.out.println("Choose your opponent:");
        String alignmentFormat = "|%-4s|%-10s|%-20s|%n";

        System.out.format("+----+----------+-----------+%n");
        System.out.format("|ID  |Name      |Difficulty |%n");
        System.out.format("+----+----------+-----------+%n");
        int i = 0;
        for (Opponent opponent : allOpponents) {
            String diffulty = View.getDifficulty(opponent.level);
            //Logger.print(diffulty);
            System.out.format(alignmentFormat,i,opponent.name,diffulty);
            i+=1;
        }
        System.out.format("+----+----------+-----------+%n");
    }

    public static void displayMoves(List<Move> moves,String name){
        System.out.println("Moves of "+name+" are:");
        String alignment = "|%-3d|%-20s|%-4d|%-6d|%n";
        System.out.format("+---+--------------------+----+------+%n");
        System.out.format("|ID |Name                |PP  |Damage|%n");
        System.out.format("+---+--------------------+----+------+%n");
        int id=0;
        for (Move move : moves) {
                System.out.format(alignment,id,move.name,move.pp,move.damage);
                id+=1;
        }
        System.out.format("+---+--------------------+----+------+%n");
    }

    public static void displayWin(){
        System.out.format("+---------------------------+%n");
        String alignment1 = "|%-50s|%n";
        System.out.format(alignment1,Color.ANSI_GREEN+"You Win"+Color.ANSI_RESET);
        System.out.format("+---------------------------+%n");
    }

    public static void displayTeam(Team team){
        String alignmentFormat = "|%-3d|%-14s|%-5s|%-6d|%-5d|%n";

        System.out.format("+---+--------------+-----+------+-----+%n");
        System.out.format("|ID |Name          |Type |Health|XP   |%n");
        System.out.format("+--+--------------+-----+------+-----+%n");
        for(int i = 0;i<team.myPokemons.size();i++){
            Pokemon poke =  team.myPokemons.get(i);
            if (poke.health<=0){
                continue;
            }
            System.out.format(alignmentFormat,i,poke.Name,Pokemon.getType(poke.type),poke.health,poke.XP);
        }
        System.out.format("+---+--------------+-----+------+-----+%n");
    }

    public static void listAvailablePokes(boolean availablePokemons[], Database db){
        System.out.println("Choose From:");
        String alignmentFormat = "|%-3s|%-10s|%-5s|%-7d|%-5d|%n";

        System.out.format("+---+----------+-----+-------+-----+%n");
        System.out.format("|ID |Name      |Type |Health |XP   |%n");
        System.out.format("+---+----------+-----+-------+-----+%n");
        for(int i = 0;i<availablePokemons.length;i++){
            if(availablePokemons[i]){
                Pokemon poke = db.getPokemon(i);
                System.out.format(alignmentFormat,poke.ID-1,poke.Name,Pokemon.getType(poke.type),poke.health,poke.XP);
            }
        }
        System.out.format("+---+----------+-----+-------+-----+%n");
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

    public static void displaySinglePoke(Opponent opponent){
        System.out.println(opponent.name + " 's"+" current pokemon");
        String alignmentFormat = "|%-3s|%-10s|%-5s|%-6d|%-3d|%n";
        System.out.format("+---+----------+-----+------+---+%n");
        System.out.format("|ID |Name      |Type |Health|XP |%n");
        System.out.format("+---+----------+-----+------+---+%n");
        Pokemon oppCurrPoke = opponent.myTeam.myPokemons.get(opponent.myTeam.currPokemon);
        System.out.format(alignmentFormat,oppCurrPoke.ID,oppCurrPoke.Name,oppCurrPoke.type,oppCurrPoke.health,oppCurrPoke.XP);
        System.out.format("+---+----------+-----+------+---+%n");
    }

    public static void getInforFromName(Database db, String name){
        int numPokemons= db.numPokemons;
        List<Pokemon>all_Pokemons=db.all_Pokemons;
        for(int i=0;i<numPokemons;i++){
            if(all_Pokemons.get(i).Name.equals(name)){
                Pokemon pokemon = all_Pokemons.get(i);
                String alignment = "|%-9s|%-12d|%-11d|%-9|%n";
                System.out.format("+---------+------------+-----------+--------+%n");
                System.out.format("|ID       |Name        |Max Health |Type    |%n");
                System.out.format("+---------+------------+-----------+--------+%n");
                System.out.format(alignment, pokemon.ID, pokemon.Name, pokemon.maxHealth, pokemon.type);
                System.out.format("+---------+------------+-----------+--------+%n");
            }
        }
    }

    public static void getInfoFromPokemon(Database db, Pokemon p){
        int numPokemons= db.numPokemons;
        List<Pokemon>all_Pokemons=db.all_Pokemons;
       for(int i=0 ; i<numPokemons; i++){
              if(all_Pokemons.get(i).Name.equals(p.Name)){
                String alignment = "|%-9s|%-12d|%-11d|%-9|%n";
                System.out.format("+---------+------------+-----------+--------+%n");
                System.out.format("|ID       |Name        |Max Health |Type    |%n");
                System.out.format("+---------+------------+-----------+--------+%n");
                System.out.format(alignment, p.ID, p.Name, p.maxHealth, p.type);
                System.out.format("+---------+------------+-----------+--------+%n");
              }
       }
    }
}