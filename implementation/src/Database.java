import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public Connection conn;
    public int numPokemons;
    public int[] starterIDs = {0,1,2,3,4,5,6,7,8,9,10};
    public List<Pokemon> all_Pokemons;  //list of all pokemons
    public List<Move> fire_moves;
    public List<Move> water_moves;
    public List<Move> grass_moves;

    Database(){
        openDatbase();  // establish connection with database
        getAllPokemon();    //fetch pokemons from database and store in all_pokemons list
        loadFireMoves();
        loadWaterMoves();
        loadGrassMoves();
        //to run the program
        //java -cp "C:\Users\Pratheek\Desktop\sem 6\OOAD\Turn-Based-Console-Game-Using-Java\implementation\src\postgresql-42.3.3.jar";"C:\Users\Pratheek\Desktop\sem 6\OOAD\Turn-Based-Console-Game-Using-Java\implementation\src" Game

    }

    void openDatbase(){
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
        System.out.println("Opened database successfully");
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
                int evolution = rs.getInt("evolution");
                // insert pokemon to list
                all_Pokemons.add(new Pokemon(id, name, hp, type, evolution, 0 ));
            }
            rs.close();
            stmt.close();
            numPokemons = all_Pokemons.size();
            System.out.println("All Pokemons loaded");
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
            System.out.println("All Fire Moves loaded");
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
            System.out.println("All Water Moves loaded");
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
                water_moves.add(new Move(name, pp,  power));
            }
            rs.close();
            stmt.close();
            System.out.println("All Grass Moves loaded");
        }
        catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }
}
