public class Water extends Pokemon{
     Water(int ID, String Name, int health, int evolutionId,int XP){
        super(ID, Name, health,1,evolutionId, XP);
    }

     Moves waterMoves= new Moves();

     void setWaterMove(String name ,  int pp, int damage){
          waterMoves.addMoves(name, pp, damage);
     }

     void getWaterMoves(){
          waterMoves.printMoves();
     }
}
