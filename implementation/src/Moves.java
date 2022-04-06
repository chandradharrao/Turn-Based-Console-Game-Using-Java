class DamageMatrix {
    float[][] damageMatrix; 
    DamageMatrix() {
        damageMatrix = new float[3][3];
        damageMatrix[0][0] = damageMatrix[1][1] = damageMatrix[2][2] = (float)0.1;
        damageMatrix[0][1] = damageMatrix[1][2] = damageMatrix[2][0] = (float)0.05;
        damageMatrix[0][2] = damageMatrix[1][0] = damageMatrix[2][1] = (float)0.2;
    }
}

public class Moves {
    public int pp;
    public String name;
    public int damage;
    public DamageMatrix dm;
}
