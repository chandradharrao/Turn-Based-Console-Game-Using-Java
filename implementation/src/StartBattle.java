public class StartBattle implements BattleState{
    public void doAction(BattleManager battleManager){
        if(battleManager.gameStart){
            battleManager.gameStart= false;
            battleManager.player.createTeam();
        }
        while(battleManager.gameLoop){
            ChooseOpponent chooseOpponent = new ChooseOpponent();
            chooseOpponent.doAction(battleManager);

            FightOpponent fightOpponent = new FightOpponent();
            fightOpponent.doAction(battleManager);

            System.out.println("Get Ready for your next battle...");
        }
    }
}
