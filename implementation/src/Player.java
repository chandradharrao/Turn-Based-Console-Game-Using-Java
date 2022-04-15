import java.util.List;

public class Player {
    //players battle team currently in use
    Team myTeam;

    //players badges gathered
    public List<Badge> myBadges;

    Player(Database db){
        myTeam = new Team(db);
    }
}
