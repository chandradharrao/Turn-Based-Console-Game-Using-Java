public class Badge {
    public int badgeLevel;
    public String gymLeaderName;

    Badge(String name,int level){
        this.badgeLevel = level;
        this.gymLeaderName = name;
    }

    @Override
    public String toString(){
        return "Badge Level: " + badgeLevel + " Gym Leader: " + gymLeaderName;
    }
}
