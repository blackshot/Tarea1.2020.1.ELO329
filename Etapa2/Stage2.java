import java.io.*;
import java.util.Scanner;
import java.util.Locale;

public class Stage2{
   public static void main (String[] arg) throws IOException {
      Locale.setDefault(Locale.US);  // to read number in US format, like 1.5 (not like 1,5)
      
      final Scanner in = new Scanner(new File(arg[0]));

      // Time init
      float time = 0;

      // Drone init
      Drone drone = new Drone();
      // Drone in the Controller
      SkyController skyController = new SkyController(drone);
      // Controller in the Operator's Hands
      Operator operator = new Operator(in, skyController);
      
      // Press start
      skyController.pushTakeOff_Land(); // to take-off
      System.out.println("Take off ...");
      
      // Play
      while(operator.takeAction(time)) {
         skyController.takeAction(time);
         drone.takeAction(time);
         System.out.println(String.format("% .2f,",time) + drone.toString());
         time+=0.1;
      }

      // Go back
      skyController.pushTakeOff_Land(); // to land
      while (drone.getHeight() > 0) {
         drone.takeAction(time);
         System.out.println(String.format("% .2f,",time) + drone.toString());
         time+=0.1;
      }
   }
}