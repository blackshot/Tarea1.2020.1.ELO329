public class SkyController implements Actionable {
   // FIELDS
   private Drone drone;
   private InputDevice ID;
   private DroneState button;

   // Constructor Method
   public SkyController (Drone drone) {
      this.drone = drone;
      button = drone.getState();
   }
   
   // Methods
   /** 
    * Obtiene la informacion de mando.
    */
   public void setInputDevice(InputDevice JJS){
      ID = JJS;
   };

   /** 
    * Realiza el despegue o aterrizaje dependiendo del estado del Dron.
    */
   public void pushTakeOff_Land () {
      button = drone.getState();
      if (button == DroneState.IDLE)   drone.takeOff();
      else if (button == DroneState.FLYING)   drone.land();
   }
   /** 
    * Dirige las acciones tomadas por los Joystick hacia el Dron.
    */
   public void takeAction(float time) {
      drone.setRotationSpeed(ID.getRotationPos());
      drone.setFlySpeed(ID.getVerticalPos(), ID.getForwardPos(), ID.getSidewaysPos());
   }
   /** 
    * Obtiene el estado del Dron.
    * @return DroneState: estado del dron.
    */
   public DroneState getDroneState(){
      return drone.getState();
   }
}

