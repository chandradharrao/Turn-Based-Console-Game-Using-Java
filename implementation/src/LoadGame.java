import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class LoadGame {
    String file = "data.ser";
    Player player;

    LoadGame(){
        try{
            FileInputStream in = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(in);
            Player player = (Player) ois.readObject();
            this.player = player;
            ois.close();
        } catch(Exception e){
            Logger.print(""+e);
        }
    }

    public Player getPlayer(){
        return this.player;
    }
}
