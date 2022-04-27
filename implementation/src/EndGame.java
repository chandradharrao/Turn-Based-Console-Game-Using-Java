public class EndGame implements BattleState{
    public void doAction(BattleManager bm){
        System.out.println("Game Over");
        bm.run = false;
        bm.gameLoop = false;

        System.out.println("Do you want to save game state?");
        char choice = System.console().readLine().charAt(0);
        if(choice=='Y'||choice=='y'){
            SaveGame sg = new SaveGame(bm.player);
        }
    }
}
