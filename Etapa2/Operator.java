import java.util.Scanner;

public class Operator {

   public Operator(Scanner in, SkyController sky){
      inFile = in;
      this.sky = sky;
      inFile.nextLine(); // skip description line
      t = inFile.nextFloat();
   }
 
   public boolean takeAction(float time){
      if (time >= t) { // Es correcto ? 
        sky.getLeftStick().setHorPos(inFile.nextFloat());
        sky.getLeftStick().setVerPos(inFile.nextFloat());
        sky.getRightStick().setHorPos(inFile.nextFloat());
        sky.getRightStick().setVerPos(inFile.nextFloat());

        if (inFile.hasNextLine()){
          inFile.nextLine();
          t = inFile.nextFloat();
        }
        else return false;
      }
      return true;
   }

   private SkyController sky;
   private float t;
   private Scanner inFile;
}