public class OpponentFacade {
    public Opponent createOpponent(String name,Database db){
        switch(name){
            case "Maylene":
                return new Opponent(db, 0,"Maylene");
            case "Misty":
                return new Opponent(db, 1, "Misty");
            case "Brock":
                return new Opponent(db, 2, "Brock");
            default:
                return new Opponent(db,0,"Chandradhar");
        }
    }
}
