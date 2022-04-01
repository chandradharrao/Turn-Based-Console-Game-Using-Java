import java.util.List;

public class Battle {
    Game newGame;
    List<Opponent> allOpponents;

    public void StartBattle(){
        newGame = new Game();
    }

    public void EndGame(){
        //save the state to disk
    }

    public void chooseOpponent(int constraint){
        while(true){
            for (Opponent opponent : allOpponents) {
                if()
            }
        }
    }
}
