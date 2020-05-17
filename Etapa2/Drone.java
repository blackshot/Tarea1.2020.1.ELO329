public class Drone {
   public Drone () {
      x = 0;
      y = 0;
      h = 0; 
      fSpeed = 0;
      vSpeed = 0;
      sSpeed = 0;
      rSpeed = 0;
      direction = 0;
      time = 0;
      state = State.LANDED;
   }
   static {
      MAX_F_SPEED = MAX_S_SPEED = 5; // [m/s]
      MAX_V_SPEED = 2;    // [m/s]
      MAX_R_SPEED = (float)(0.25*Math.PI/2); // [rad/s]
      TAKEOFF_LANDING_SPEED = 1; // [m/s]
   }
   public void takeAction(final float t) {
      final float delta_t = t - time;
      switch (state) {
         case TAKING_OFF: // drone moves only upwards in this stage
            h += TAKEOFF_LANDING_SPEED*delta_t;
            if (h >= 1){
               h = 1;
               state = State.FLYING;
            } break;
         case FLYING:
            direction += rSpeed*delta_t;
            x += (fSpeed*Math.cos(direction) + sSpeed*Math.cos((Math.PI/2) - direction))*delta_t;
            y += (fSpeed*Math.sin(direction) + sSpeed*Math.sin((Math.PI/2) - direction))*delta_t;
            h += vSpeed*delta_t;
            break;
         case LANDING: // drone moves only downwards in this stage
            h += (-TAKEOFF_LANDING_SPEED)*delta_t;
            if (h < 0){
               h = 0;
            }
            break;
         case LANDED:
            break;
         case TAKE_OFF:
            break;
      }  
      time = t;
   }

   public void setRotationSpeed(final float percentage) {
      rSpeed = percentage*MAX_R_SPEED;
   }

   public void setFlySpeed(final float verPer, final float forwPer, final float sidePer) {
      fSpeed = forwPer*MAX_F_SPEED;
      vSpeed = verPer*MAX_V_SPEED;
      sSpeed = sidePer*MAX_S_SPEED;
   }

   public float getHeight() {
      return h;
   }

   public String toString() {
      return x + ", " + y + ", " + h;
   }

   public void takeOff() {
      if (state == State.LANDED)
         state = State.TAKING_OFF;
   }

   public void land() {
      if (state == State.FLYING)
         state = State.LANDING;
   }
   
   private State state;
   private float time;
   private float fSpeed, vSpeed, sSpeed, rSpeed;
   private float direction; // angle
   private float x,y,h;
   private static float MAX_F_SPEED;
   private static float MAX_V_SPEED;
   private static float MAX_S_SPEED;
   private static float MAX_R_SPEED;
   private static float TAKEOFF_LANDING_SPEED;
}