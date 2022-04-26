class Game{
    public static void main(String[] args) {
        Logger.print("GAME STARTED!");

        BattleManager battleManager = BattleManager.getInstance();

        StartBattle startBattle = new StartBattle();
        startBattle.doAction(battleManager);
    }
}