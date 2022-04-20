import java.util.List;

public class Player {
    //players battle team currently in use
    Team myTeam;
    String name;

    //players badges gathered
    public List<Badge> myBadges;

    Player(Database db){
        myTeam = new Team(db);
        playerDetails();
    }

    void playerDetails(){
        System.out.println("Enter name");
        name = System.console().readLine();
    }
}
