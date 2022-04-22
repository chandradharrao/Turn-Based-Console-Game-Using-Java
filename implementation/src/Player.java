import java.util.ArrayList;
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

        //list of badges collected by the player
        myBadges = new ArrayList<Badge>();
    }

    void playerDetails(){
        System.out.println("Enter name");
        name = System.console().readLine();
        Logger.print("Name is "+name);
    }
}
