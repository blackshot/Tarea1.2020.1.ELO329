public class SkyController implements Actionable {
   // Fields
   private Drone drone;
   private InputDevice ID;
   private State drone_state;

   // Constructor
   public SkyController (Drone drone) {
      this.drone = drone; // private variable "drone" (local) now points to Drone object
      // At creation, the drone is LANDED
      drone_state = drone.getState();
   }

   // Methods
   public void setInputDevice(InputDevice ID) {
      this.ID = ID;
   }

   public void pushTakeOff_Land() {
      drone_state = drone.getState();
      if (drone_state == State.IDLE) {
         drone.takeOff();
      } else if (drone_state == State.FLYING) {
         drone.land();
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

   public State getDroneState(){
      return drone.getState();
   }

}