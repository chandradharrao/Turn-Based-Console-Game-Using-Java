public class Fire extends Pokemon{   
    Fire(int ID, String Name, int health,int XP){
        super(ID, Name, health, "Fire", XP);
        this.moves = new Moves();
    }

    void setFireMove(String name ,int pp, int damage){
        this.moves.addMoves(name, pp, damage);
    }

    void getFireMoves(){
        this.moves.printMoves();
    }
}
