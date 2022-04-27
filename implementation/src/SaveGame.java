import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SaveGame {
    String file = "data.ser";

    SaveGame(Player player){
        try{
            FileOutputStream fout = new FileOutputStream(file,true);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(player);
            System.out.println("Saved Player");
            oos.close();
        }catch(Exception e){
            Logger.print(""+e);
        }
    }
}
