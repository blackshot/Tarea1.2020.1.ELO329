import java.util.Scanner;

public class Operator {
   
   // Fields
   private float t;
   private Scanner inFile;
   private Joystick l_Joystick, r_Joystick;
   private SkyController skyController;
   
   // Constructor
   public Operator(Scanner in, Joystick l_Joy, Joystick r_Joy){
      inFile = in;
      l_Joystick = l_Joy;
      r_Joystick = r_Joy;
      
      // Skip description line
      inFile.nextLine();
      t = inFile.nextFloat();
   }
   
   // Another constructor
   public Operator(Scanner in, SkyController skyController){
      inFile = in;
      this.skyController = skyController;
      l_Joystick = skyController.getLeftStick();
      r_Joystick = skyController.getRightStick();
      
      // Skip description line
      inFile.nextLine();
      t = inFile.nextFloat();
   }
   
   // Methods
   public boolean takeAction(float time){
      float v,r,f,s;
      // If there's data to read
      if (inFile.hasNextLine()){

         // Round to avoid float epsilon difference
         if (Math.round(time * 10) >= Math.round(t*10)){
            r = inFile.nextFloat();
            v = inFile.nextFloat();
            s = inFile.nextFloat();
            f = inFile.nextFloat();

            // If there's no input, and no more data to read
            if (v == 0.0 && r == 0.0 && f == 0.0 && s == 0.0 && 
            inFile.hasNextLine() == false){
               skyController.pushTakeOff_Land();
               }
            
            // Left Joystick Data
            l_Joystick.setHorPos(r);
            l_Joystick.setVerPos(v);

            // Right Joystick Data
            r_Joystick.setHorPos(s);
            r_Joystick.setVerPos(f);
            
            // If there's a new line
            if (inFile.hasNextLine()){
               t = inFile.nextFloat();
            }
         }
         return true;
      }
      // No more data to read;
      else{ 
         return false;
      }
   }
}