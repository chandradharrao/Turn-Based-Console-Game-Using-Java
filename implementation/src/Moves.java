import java.util.ArrayList;
import java.util.List;

public class Moves{
    List <Move> theMoves;

    Moves(){
        this.theMoves=new ArrayList<Move>();
    }

    //add moves to the list
    public void addMoves(String name, int pp, int damage){ 
        if(theMoves.size() == 0){
            theMoves.add(0,new Move(name, pp, damage));
        }
        else if (theMoves.size()<4){
            theMoves.add(new Move(name, pp, damage));
        }
        else{
            System.out.println("You can't have more than 4 moves");
        }
    }

    //display the details of all the moves
    public void printMoves() {
        for (Move move : theMoves) {
            System.out.println(move.name + " " + move.pp + " "+ move.damage);
        }
    }

    //reduce the pp of the given move
    public void reducePP(int mvNum){
        this.theMoves.get(mvNum).pp-=1;
    }

    //get PP of particular move
    public int getPP(int mvNum){
        return this.theMoves.get(mvNum).pp;
    }

    //increase pp to max
    public void increasePP(){
        for(Move move: theMoves){
            move.pp = move.maxPP;
        }
    }

    //print the list of moves
    @Override
    public String toString() {
        String movesDesc = "";
        for (Move move : theMoves) {
            movesDesc = "Move: "+ move.name + "\n" + "PP: " + move.pp + "\n"+ "Damage: " + move.damage + "\n";
        }
        return movesDesc;
    }
}
