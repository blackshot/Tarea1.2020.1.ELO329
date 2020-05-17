public class Joysticks extends InputDevice {
   // FIELDS
   private Joystick lStick, rStick;

   // Constructor Method
   public Joysticks (SkyController controller) {
      super(controller);
      rStick = new Joystick();
      lStick = new Joystick();
   }

   // Methods
   /** 
    * Obtiene la informacion del joystick izquierdo.
    * @return Joystick: informacion del mando izquierdo
    */
   public Joystick getLeftStick(){
      return lStick;
   }
   /** 
    * Obtiene la informacion del joystick derecho.
    * @return Joystick: informacion del mando derecho
    */
   public Joystick getRightStick(){
      return rStick;
   }
    /** 
    * Obtiene la posicion direccional que se le da al dron 
    * tipo float.
    * @return float: posicion direccional.
    */
   public float getForwardPos() {
      return rStick.getVerPos();
   }
   /** 
   * Obtiene la posicion lateral que se le da al dron 
   * tipo float.
   * @return float: posicion lateral.
   */
    public float getSidewaysPos() {
      return rStick.getHorPos();
   }
   /** 
   * Obtiene la posicion vertical que se le da al dron 
   * tipo float.
   * @return float: posicion vertical.
   */
    public float getVerticalPos() {
      return lStick.getVerPos();
   }
   /** 
   * Obtiene la posicion rotacional del dron 
   * tipo float.
   * @return float: posicion rotacional.
   */
    public float getRotationPos() {
      return lStick.getHorPos();
   }
 }