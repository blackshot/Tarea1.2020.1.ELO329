import java.io.*;
import java.util.Scanner;
import java.util.Locale;
import java.util.ArrayList;

public class AcrobaticDrones  {
   public static void main (String[] arg) throws IOException {

      // ---------- CODE INIT ---------- //
      
      if (arg.length == 0){
         System.out.println("ERROR : No input handed. Closing program ...");
         return;
      }
      final Scanner in = new Scanner(new File(arg[0]));
      
      // Drone creation
      Drone drone1 = new Drone(1,0,10);
      Drone drone2 = new Drone(2,10,0);

      ArrayList<Actionable> Actionables1 = new ArrayList<Actionable>();
      ArrayList<Actionable> Actionables2 = new ArrayList<Actionable>();
      
      // Drone 1 (automatic) configuration
      SkyController skyController1 = new SkyController(drone1);
      Joysticks joysticks = new Joysticks(skyController1);
      skyController1.setInputDevice(joysticks);
      Operator operator = new Operator(in, joysticks);
      Actionables1.add(operator);   Actionables1.add(skyController1);   Actionables1.add(drone1);

      // Drone 2 (manual) configuration
      SkyController skyController2 = new SkyController(drone2);
      Keyboard keyboard = new Keyboard(skyController2);
      skyController2.setInputDevice(keyboard);
      Actionables2.add(keyboard);   Actionables2.add(skyController2);   Actionables2.add(drone2);
      
      
      // Time init (as close as possible to the operation to avoid time lag...)
      float time, nextPrintTime;
      time = nextPrintTime = getCurrentTime();

      do {
         for (Actionable device : Actionables1)
            device.takeAction(time);

         for (Actionable device : Actionables2)
            device.takeAction(time);
         if (time >= nextPrintTime) {
            System.out.println("Drone 1: " + drone1.toString());
            System.out.println("Drone 2: " + drone2.toString());
            nextPrintTime += 0.5;
         }
         sleepFor(0.1f); // let 0.1 [s] pass to run at real time.
         time = getCurrentTime();
      } while ( (drone1.getState() != DroneState.IDLE) || (drone2.getState() != DroneState.IDLE) );

      System.out.println(drone1.toString());
      System.out.println(drone2.toString());

      // Close the file
      drone1.closeFile();
      drone2.closeFile();
   }

   // ------------------------------------------------------------------ //

   public static float getCurrentTime(){  // time since program started in [s]
      return (float)(System.currentTimeMillis()-t0)/1000.0f;
   }
   public static void sleepFor(float time) { // to let user react
      try {
         Thread.sleep((int)(time*1000));
      }
      catch (InterruptedException e){
      }
   }
   

   public static long t0; 
   static {
      Locale.setDefault(Locale.US);
      t0=System.currentTimeMillis();  // time in ms since app 1970.
   }
}