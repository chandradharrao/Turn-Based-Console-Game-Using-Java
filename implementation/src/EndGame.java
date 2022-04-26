public class EndGame implements BattleState{
    public void doAction(BattleManager bm){
        System.out.println("Game Over");
        bm.run = false;
        bm.gameLoop = false;
    }
}
