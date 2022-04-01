public class Opponent {
    public Team myTeam;
    public Badge theBadge;

    Opponent(){
        myTeam = new Team();
    }

    public void giveBadge(Player to){
        to.myBadges.add(Badge);
    }
}
