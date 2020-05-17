public class SkyController implements Actionable {
   // Fields
   private Drone drone;
   private InputDevice ID;
   private DroneState drone_state;

   // Constructor Method
   public SkyController (Drone drone) {
      this.drone = drone; // private variable "drone" (local) now points to Drone object
      // At creation, the drone is LANDED
      drone_state = drone.getState();
   }

   // Methods
   /** 
    * Obtiene la informacion de mando.
    */
   public void setInputDevice(InputDevice ID) {
      this.ID = ID;
   }

   /** 
    * Realiza el despegue o aterrizaje dependiendo del estado del Dron.
    */
   public void pushTakeOff_Land() {
      drone_state = drone.getState();
      if (drone_state == DroneState.IDLE) {
         drone.takeOff();
      } else if (drone_state == DroneState.FLYING) {
         drone.land();
      }
   }
   /** 
    * Dirige las acciones tomadas por los Joystick hacia el Dron.
    */
   public void takeAction(float time) {
      // JIV, JDV, JDH
      drone.setFlySpeed(ID.getVerticalPos(),
                        ID.getForwardPos(),
                        ID.getSidewaysPos());
      // JIH
      drone.setRotationSpeed(ID.getRotationPos());
   }
   /** 
    * Obtiene el estado del Dron.
    * @return DroneState: estado del dron.
    */
   public DroneState getDroneState(){
      return drone.getState();
   }

}