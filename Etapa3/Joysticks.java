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
    * Determina una posicion vertical del joystick segun 
    * el parametro tipo float Value. 
    * @return float: 
    */
   public float getForwardPos() {
      /* to be coded */
      return 0.0f;
   }
   /** 
    * Determina una posicion vertical del joystick segun 
    * el parametro tipo float Value. 
    * @return float: 
    */
    public float getSidewaysPos() {
      /* to be coded */
      return 0.0f;
   }
   /** 
    * Determina una posicion vertical del joystick segun 
    * el parametro tipo float Value. 
    * @return float: 
    */
    public float getVerticalPos() {
      /* to be coded */
      return 0.0f;
   }
   /** 
    * Determina una posicion vertical del joystick segun 
    * el parametro tipo float Value. 
    * @return float: 
    */
    public float getRotationPos() {
      /* to be coded */
      return 0.0f;
   }
 }