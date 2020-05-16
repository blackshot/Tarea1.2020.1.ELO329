public class Joysticks extends InputDevice {
   public Joysticks (SkyController controller) {
      super(controller);
      lStick = controller.getLeftStick();
      rStick = controller.getRightStick();
   }

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
      return rStick.getHorPos();
   }
   public float getForwardPos(){
      return rStick.getVerPos();
   }
   public float getSidewaysPos(){
      return rStick.getHorPos();
   }
   
   /* others methods to be coded*/
   private Joystick lStick, rStick;
}