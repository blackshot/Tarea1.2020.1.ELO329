public class Joysticks extends InputDevice {
   // Fields
   private Joystick lStick, rStick;

   // Constructor
   public Joysticks (SkyController controller) {
      super(controller);
      lStick = new Joystick();
      rStick = new Joystick();
   }

   // Methods
   public Joystick getLeftStick(){
      return lStick;
   }
   public Joystick getRightStick(){
      return rStick;
   }

   public float getVerticalPos(){
      return lStick.getVerPos();
   }
   public float getRotationPos(){
      return lStick.getHorPos();
   }
   public float getForwardPos(){
      return rStick.getVerPos();
   }
   public float getSidewaysPos(){
      return rStick.getHorPos();
   }

}