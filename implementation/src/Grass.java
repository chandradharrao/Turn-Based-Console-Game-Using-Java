public class Grass extends Pokemon{
    Grass(int ID, String Name, int health, int evolutionId,int XP){
        super(ID, Name, health,2, evolutionId, XP);
    }

    Moves grassMoves= new Moves();

    void setGrassMove(String name , int pp, int damage){
        grassMoves.addMoves(name, pp, damage);
    }

    void getGrassMoves(){
        grassMoves.printMoves();
    }
}
