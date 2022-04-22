//a single move
public class Move{
    public int pp;
    public int maxPP;
    public String name;
    public int damage;

    Move(String name, int pp, int damage) {
        this.name = name;
        this.maxPP = pp;
        this.pp = pp;
        this.damage = damage;
    }
}
