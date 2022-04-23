public class Fire extends Pokemon{   
    Fire(int ID, String Name, int health,int evolutionId, int XP){
        super(ID, Name, health, 0, evolutionId, XP);
        this.moves = new Moves();
    }

    void setFireMove(String name ,int pp, int damage){
        this.moves.addMoves(name, pp, damage);
    }

    void getFireMoves(){
        this.moves.printMoves();
    }
}
