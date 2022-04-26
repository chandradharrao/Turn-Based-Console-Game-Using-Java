public class ChooseOpponent implements BattleState {
    public void doAction(BattleManager battleManager){
        View.displayOpponents(battleManager.allOpponents);

        //get user input
        try {
            battleManager.currOpponent = battleManager.allOpponents.get(Integer.parseInt(System.console().readLine()));
        } catch (Exception e) {

            //wrong input,hence ask him to choose again
            System.out.println(e);
            this.doAction(battleManager);
        }

        battleManager.setState(this);
    }
}
