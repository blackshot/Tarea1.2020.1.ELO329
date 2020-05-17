public class SkyController implements Actionable {
   // Fields
   private Drone drone;
   private InputDevice ID;
   private State button;

   // Constructor
   public SkyController (Drone drone) {
      this.drone = drone; // private variable "drone" (local) now points to Drone object
      // At creation, the drone is LANDED
      button = State.IDLE;
   }

   // Methods   
   public void setInputDevice(InputDevice ID){
      this.ID = ID;
   }

   public void pushTakeOff_Land(){
      if (button == State.IDLE){
         drone.takeOff();
         button = State.TAKE_OFF;
      }
      else if (button == State.TAKE_OFF){
         drone.land();
         button = State.LANDING;
      }
   }

   public void takeAction(float time) {
      // JIV, JDV, JDH
      drone.setFlySpeed(ID.getVerticalPos(),
                        ID.getForwardPos(),
                        ID.getSidewaysPos());
      // JIH
      drone.setRotationSpeed(ID.getRotationPos());
   }
   
}