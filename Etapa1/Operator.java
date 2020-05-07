public class Operator {
   private float t;
   private Scanner inFile;
   private Joystick l_Joystick, r_Joystick;

   public Operator (Scanner in, Joystick l_Joy, Joystick r_Joy){
      inFile = in;
      l_Joystick = l_Joy;
      r_Joystick = r_Joy;

      // skip description line
      inFile.nextLine();
      t = inFile.nextFloat();
   }
   public boolean takeAction(float time){
      float f;
      if (time > t) {

         System.out.println("data time is : " + t);
         
         l_Joystick.setHorPos(inFile.nextFloat());
         l_Joystick.setVerPos(inFile.nextFloat());
         System.out.println("l_Joystick data : " + l_Joystick.toString());
         
         r_Joystick.setHorPos(inFile.nextFloat());
         r_Joystick.setVerPos(inFile.nextFloat());
         System.out.println("r_Joystick data : " + r_Joystick.toString());

         // Change the line if there's another one
         if(inFile.hasNextLine()){
            inFile.nextLine();
            t = inFile.nextFloat();
         }
         else {
            System.out.println("No more data to read ... ");
            return false;
         }
      }
      return true;
   }
}