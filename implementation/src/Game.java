import java.io.Console;
import java.util.ArrayList;
import java.util.List;

class Game{
    public static void main(String[] args) {
        Logger.print("GAME STARTED!");

        BattleManager battleManager = new BattleManager();

        StartBattle startBattle = new StartBattle();
        startBattle.doAction(battleManager);
    }
}