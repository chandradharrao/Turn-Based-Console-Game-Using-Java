import java.util.ArrayList;
import java.util.List;

// class DamageMatrix {
//     DamageMatrix() {
//         damageMatrix = new float[3][3];
//         damageMatrix[0][0] = damageMatrix[1][1] = damageMatrix[2][2] = (float)0.1;
//         damageMatrix[0][1] = damageMatrix[1][2] = damageMatrix[2][0] = (float)0.05;
//         damageMatrix[0][2] = damageMatrix[1][0] = damageMatrix[2][1] = (float)0.2;
//     }
// }

class Move{
    public int pp;
    public int maxPP;
    public String name;
    public int damage;

    Move(String name, int pp, int damage) {
        this.name = name;
        this.maxPP = pp;
        this.pp = pp;
        this.damage = damage;
    }
}

public class Moves{
    List <Move> moves;

    Moves(){
        this.moves=new ArrayList<Move>();
    }

    //add moves to the list
    public void addMoves(String name, int pp, int damage){ 
        if(moves.size() == 0){
            moves.add(0,new Move(name, pp, damage));
        }
        else if (moves.size()<4){
            moves.add(new Move(name, pp, damage));
        }
        else{
            System.out.println("You can't have more than 4 moves");
        }
    }

    //display the details of all the moves
    public void printMoves() {
        for (Move move : moves) {
            System.out.println(move.name + " " + move.pp + " "+ move.damage);
        }
    }

    //reduce the pp of the given move
    public void reducePP(int mvNum){
        this.moves.get(mvNum).pp-=1;
    }

    //get PP of particular move
    public int getPP(int mvNum){
        return this.moves.get(mvNum).pp;
    }

    //increase pp to max
    public void increasePP(){
        for(Move move: moves){
            move.pp = move.maxPP;
        }
    }

    //print the list of moves
    @Override
    public String toString() {
        String movesDesc = "";
        for (Move move : moves) {
            movesDesc = move.name + " " + move.pp + " "+ move.damage + "\n";
        }
        return movesDesc;
    }
}
