import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public Connection conn;
    public int numPokemons;
    public int[] starterIDs = {0,3,5,30,33,35,60,63,66};
    public List<Pokemon> all_Pokemons;  //list of all pokemons
    public List<Move> fire_moves;
    public List<Move> water_moves;
    public List<Move> grass_moves;

    Database(){
        openDatabase();  // establish connection with database

        loadFireMoves();
        loadWaterMoves();
        loadGrassMoves();
        
        getAllPokemon();    //fetch pokemons from database and store in all_pokemons list

        //to run the program
        //java -cp "C:\Users\Pratheek\Desktop\sem 6\OOAD\Turn-Based-Console-Game-Using-Java\implementation\src\postgresql-42.3.3.jar";"C:\Users\Pratheek\Desktop\sem 6\OOAD\Turn-Based-Console-Game-Using-Java\implementation\src" Game

    }

    void openDatabase(){
        // establish connection with database
        conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/pokemondb",  //database link
                "postgres", //db username
                "pratheek"); //user password
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        Logger.print("Opened database successfully");
    }

    //Load all the pokemons from database
    void getAllPokemon() {
        all_Pokemons = new ArrayList<Pokemon>();
        try {
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM POKEMONS;" );
            while ( rs.next() ) {
                //Read all pokemon data
                int id = rs.getInt("id");
                String  name = rs.getString("name");
                int hp  = rs.getInt("hp");
                int  type = rs.getInt("type1");

                Logger.print("+______________________+");
                Logger.print(name + "Type from DB: "+type);
                int evolution = rs.getInt("evolution");
                // insert pokemon to list
                Pokemon newPoke = new Pokemon(id, name, hp, type, evolution, 0, this.createMoves(type));
                all_Pokemons.add(newPoke);
                Logger.print("+______________________+");
                Logger.print("\n");
                
            }
            rs.close();
            stmt.close();
            //Logger.print("Fetched all pokemons from database");
            numPokemons = all_Pokemons.size();
            //Logger.print("All Pokemons loaded");
        }
        catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    } 

    //get the pokemon object at index
    //todo: indx=indx-1 as it is zero based indexing 
    Pokemon getPokemon(int indx){
        Logger.print("Retrieveing Pokemon: "+all_Pokemons.get(indx).Name);
        return all_Pokemons.get(indx);
    }

    //randomize moves for each pokemon depending on type
    public Moves createMoves(int type){
        Moves theMoves = new Moves();
        try{
            List<Move> allMovesType = this.getTypeMoves(type);
            //Logger.print("Loading moves for type: "+type);
            for(int i = 0;i<4;i++){
                int random = (int)(Math.random()*allMovesType.size());
                //Logger.print("Size of allMovesType list "+allMovesType.size());
                //Logger.print("Random number is : "+random);
                Move move= allMovesType.get(random);
                //Logger.print("Gotten move!");
                Logger.print("Move added: "+ move.name);
                theMoves.addMoves(move.name, move.pp, move.damage);    
            }
        }
            catch(Exception e){
                Logger.print(e.getClass().getName()+": "+e.getMessage(),1);
            }   
        return theMoves; 
    }


    //load all the fire moves 
    void loadFireMoves(){
        fire_moves = new ArrayList<Move>();
        try {
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM FIRE_MOVES WHERE type = 0;" );
            while ( rs.next() ) {
                //Read all pokemon data
                String  name = rs.getString("name");
                int  power = rs.getInt("power");
                int  pp = rs.getInt("pp");
                // insert pokemon to list
                fire_moves.add(new Move(name, pp,  power));
            }
            rs.close();
            stmt.close();
            Logger.print("All Fire Moves loaded");
        }
        catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    //load all the water moves
    void loadWaterMoves(){
        water_moves = new ArrayList<Move>();
        try {
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM WATER_MOVES WHERE type = 1;" );
            while ( rs.next() ) {
                //Read all pokemon data
                String  name = rs.getString("name");
                int  power = rs.getInt("power");
                int  pp = rs.getInt("pp");
                // insert pokemon to list
                water_moves.add(new Move(name, pp,  power));
            }
            rs.close();
            stmt.close();
            Logger.print("All Water Moves loaded");
        }
        catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    //load all the grass moves
    void loadGrassMoves(){
        grass_moves = new ArrayList<Move>();
        try {
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM GRASS_MOVES WHERE type = 2;" );
            while ( rs.next() ) {
                //Read all pokemon data
                String  name = rs.getString("name");
                int  power = rs.getInt("power");
                int  pp = rs.getInt("pp");
                // insert pokemon to list
                grass_moves.add(new Move(name, pp,  power));
            }
            rs.close();
            stmt.close();
            Logger.print("All Grass Moves loaded");
        }
        catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
    
    List <Move> getTypeMoves(int type){
        if(type == 0){
            return fire_moves;
        }
        else if(type == 1){
            return water_moves;
        }
        else{
            return grass_moves;
        }
    }
}