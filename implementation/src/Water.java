public class Water extends Pokemon{
     Water(int ID, String Name, int health, int XP){
        super(ID, Name, health,"Water", XP);
    }

     Moves waterMoves= new Moves();

     void setWaterMove(String name ,  int pp, int damage){
          waterMoves.addMoves(name, pp, damage);
     }

     void getWaterMoves(){
          waterMoves.printMoves();
     }
}
