import java.util.*;

class Pokemon{
    public int health;
    
    Pokemon(int x){
        health=x;
    }
}

class Test {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        List<Pokemon> ps = new ArrayList<Pokemon>();
        ps.add(new Pokemon(100));
        ps.get(0).health-=1;
        
        System.out.println(ps.get(0).health);
    }
}
