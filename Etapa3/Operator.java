import java.util.Scanner;

public class Operator implements Actionable {
   // FIELDS
   private float t;
   private Scanner inFile;
   private Joystick l_Joystick, r_Joystick;
   private Joysticks JS;

   // Constructor Methods
   public Operator (Scanner in, Joysticks JS){
      inFile = in;
      this.JS = JS; 
      l_Joystick = JS.getLeftStick();
      r_Joystick = JS.getRightStick();
      inFile.nextLine(); // skip description line
      t = inFile.nextFloat();
   }

   // Methods
   /** 
    * Toma las acciones pertinentes dependiendo del archivo 
    * de entrada.
    * @param time (float): el tiempo actual
    * @return boolean: si se tomo o no la accion
    */
    public void takeAction(float time){
      float ver,rot,forw,side;
      // If there's data to read
      if (inFile.hasNextFloat()){
         // Round to avoid float epsilon difference
         if (Math.round(time * 10) >= Math.round(t*10)){
            rot = inFile.nextFloat();
            ver = inFile.nextFloat();
            side = inFile.nextFloat();
            forw = inFile.nextFloat();

            if (ver == 0.0 && rot == 0.0 && forw == 0.0 && side == 0.0 && inFile.hasNextLine() == false){
               JS.pushTakeOff_Land();
               }

            // Left Joystick Data
            l_Joystick.setHorPos(rot);
            l_Joystick.setVerPos(ver);
             // Right Joystick Data
            r_Joystick.setHorPos(side);
            r_Joystick.setVerPos(forw);

             // If there's a new line
            if (inFile.hasNextLine()){
               t = inFile.nextFloat();
            }
         }
      }
      else {} // No action
   }
}