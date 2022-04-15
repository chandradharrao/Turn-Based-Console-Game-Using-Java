public class Grass extends Pokemon{
    Grass(int ID, String Name, int health, int XP){
        super(ID, Name, health,"Grass", XP);
    }

    Moves grassMoves= new Moves();

    void setGrassMove(String name , int pp, int damage){
        grassMoves.addMoves(name, pp, damage);
    }

    void getGrassMoves(){
        grassMoves.printMoves();
    }
}
