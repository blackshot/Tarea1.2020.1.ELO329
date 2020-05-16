public class Drone {
   /*
   y
   ^
   |      y
   |   \d |
   |    \ |
   |      D ---- x
   |
   |---------------->x
   */
   // Fields
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
   
   // Constructor
   public Drone(){
      state = State.IDLE;
      /* +y is Forward at the start
      // The direction doesn't matter, as long as you change the equation in the FLYING state
      */
      direction = 0;
      x = 0;   y = 0;   h = 0;
   }
   
   static {
      MAX_F_SPEED = MAX_S_SPEED = 5; // [m/s]
      MAX_V_SPEED = 2;    // [m/s]
      MAX_R_SPEED = (float)(0.25*Math.PI/2); // [rad/s]
      TAKEOFF_LANDING_SPEED = 1; // [m/s]
   }
   
   // Methods
   public void takeAction(float t){
      float delta_t = t-time;
      switch (state) {
         case TAKE_OFF:  //drone moves only upwards in this stage
         h += delta_t * TAKEOFF_LANDING_SPEED;
         if (h >= 1.0){
            state = State.FLYING;
            System.out.println("Drone reached flying altitude...");
         }
         break;

         case FLYING:
         h  += delta_t * vSpeed;
         // In case of crash, turn off
         if (h <= 0){
            state = State.IDLE;
            System.out.println("Drone crashed to the ground... Turning Off...");
         }

         direction += delta_t * rSpeed;

         x += delta_t * ( sSpeed * Math.cos(direction) - fSpeed * Math.sin(direction) );
         y += delta_t * ( sSpeed * Math.sin(direction) + fSpeed * Math.cos(direction) );
         
         break;
         
         case LANDING: //drone moves only downwards in this stage
         h -= delta_t * TAKEOFF_LANDING_SPEED;
         
         case IDLE: // Waiting for TAKE_OFF or TURNED OFF BY CRASHING
      }
      time = t;
   }
   public void setRotationSpeed(float rotPer) {
      rSpeed = MAX_R_SPEED * rotPer;
   }
   
   public void setFlySpeed(float verPer, float forwPer, float sidePer) {
      vSpeed = MAX_V_SPEED * verPer;
      fSpeed = MAX_F_SPEED * forwPer;
      sSpeed = MAX_S_SPEED * sidePer;
   }
   
   public float getHeight() {
      return h;
   }
   
   public String toString() {
      String fString = String.format("% .2f,% .2f,% .2f",x,y,h);
      return fString;
   }
   
   public void takeOff() {
      if (state==State.IDLE){
         state = State.TAKE_OFF;
      }
      else{
         System.out.println("Error: Drone can't take off if it is already in flight...");
      }
   }
   
   public void land() {
      if (state==State.FLYING){
         state = State.LANDING;
         System.out.println("Landing ...");
      }
      else{
         System.out.println("Error: Drone can't land if it is already on ground...Maybe it crashed...?");
      }
   }
}