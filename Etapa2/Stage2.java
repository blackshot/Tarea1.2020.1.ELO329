import java.io.*;
import java.util.Scanner;
import java.util.Locale;

public class Stage2{
   public static void main (String[] arg) throws IOException {
      Locale.setDefault(Locale.US);  // to read number in US format, like 1.5 (not like 1,5)
      
      // ---------- CODE INIT ---------- //
      if (arg.length == 0){
         System.out.println("ERROR : No input handed. Closing program ...");
         return;
      }
      final Scanner in = new Scanner(new File(arg[0]));

      // Time init
      float time = 0;

      // Drone init
      Drone drone = new Drone();
      // Create the controller
      SkyController skyController = new SkyController(drone);
      // Controller in the Operator's Hands
      Operator operator = new Operator(in, skyController);
      
      // Press start
      skyController.pushTakeOff_Land(); // to take-off
      // System.out.println("Take off ...");
      
      // Play
      while(operator.takeAction(time)) {
         skyController.takeAction(time);
         drone.takeAction(time);
         System.out.println(String.format("% .2f,",time) + drone.toString());
         time+=0.1;
      }

      // Go back
      // The operator will press the button automatically if it runs out of commands ...
      
      while (drone.getHeight() > 0) {
         drone.takeAction(time);
         System.out.println(String.format("% .2f,",time) + drone.toString());
         time+=0.1;
      }
   }
}