import java.io.PrintWriter;
import java.io.IOException;

public class Drone implements Actionable{
   // FIELDS
   private DroneState state;
   private float time;
   private float fSpeed, vSpeed, sSpeed, rSpeed;
   private float direction; // angle
   private float x,y,h;
   private int num;
   private PrintWriter Archive;

   private static float MAX_F_SPEED;
   private static float MAX_V_SPEED;
   private static float MAX_S_SPEED;
   private static float MAX_R_SPEED;
   private static float TAKEOFF_LANDING_SPEED;
   
   static {
      MAX_F_SPEED = MAX_S_SPEED = 5; // [m/s]
      MAX_V_SPEED = 2;    // [m/s]
      MAX_R_SPEED = (float)(0.25*Math.PI/2); // [rad/s]
      TAKEOFF_LANDING_SPEED = 1; // [m/s]
   }
   
   // Constructor
   public Drone(int num, int x, int y){
      state = DroneState.IDLE;
      time = 0.0f;
      this.x = x;      this.y = y;      h = 0.0f;
      fSpeed = 0.0f; vSpeed = 0.0f; sSpeed = 0.0f; rSpeed = 0.0f;
      direction = 0.0f;
      try {
         Archive = new PrintWriter("drone"+num+".csv", "UTF-8");
         System.out.println("File Created"); // termina el print despues de close
         Archive.write("time, x, y, h\n");
      } catch (IOException e) {
         System.out.println("File Creation Error");
      }
   }
   
   // Methods
   /** 
    * Realiza alguna de las acciones dependiendo del estado del Dron.
    * @param t (float): tiempo actual.
    */
   public void takeAction(float t){
      float delta_t = t-time;
      switch (state) {
      case TAKE_OFF:  //drone moves only upwards in this stage
         h += delta_t * TAKEOFF_LANDING_SPEED;
         if (h >= 1.0f){
            state = DroneState.FLYING;
            System.out.println("Drone " + this.num + " reached flying altitude...");
         }
         break;
      case FLYING:
         x += delta_t * (fSpeed*(float)Math.cos(direction) + sSpeed*(float)Math.sin(direction));
         y += delta_t * (fSpeed*(float)Math.sin(direction) + sSpeed*(float)Math.cos(direction));
         h += delta_t * vSpeed;
         direction += delta_t * rSpeed;
         break;
      case LANDING: //drone moves only downwards in this stage
         h -= delta_t * TAKEOFF_LANDING_SPEED;
         if (h <= 0) { 
            state = DroneState.IDLE;
            h = 0.0f; // specifies that it can't go below zero.
            System.out.println("Drone " + this.num + " landed... Changing State to IDLE");
         }
         break;
         case IDLE: // Waiting for TAKE_OFF or TURNED OFF BY CRASHING
      }
      time = t;
      
      // Export the data
      this.print2File();
   }

   /** 
    * Determina la velocidad rotacional del dron segun 
    * el left Joy.
    * @param rotPer (float): angulo de posicion left Joy horizontal.
    */
   public void setRotationSpeed(float rotPer) {
      rSpeed = MAX_R_SPEED * rotPer;
   }
   /** 
    * Determina las velocidades del dron segun los
    * joysticks.
    * @param vertPer (float): angulo de posicion left Joy vertical.
    * @param forwPer (float): angulo de posicion right Joy vertical.
    * @param sidePer (float): angulo de posicion right Joy horizontal.
    */
   public void setFlySpeed(float verPer, float forwPer, float sidePer) {
      vSpeed = MAX_V_SPEED * verPer;
      fSpeed = MAX_F_SPEED * forwPer;
      sSpeed = MAX_S_SPEED * sidePer;
   }
   /** 
    * Obtiene la informacion de la altura.
    * @return float: altura a la que se encuentra.
    */
   public float getHeight() {
      return h;
   }
   /** 
    * Obtiene el estado del dron.
    * @return DroneState: estado en el que se encuentra.
    */
   public DroneState getState(){
      return state;
   }
   /** 
    * Obtiene la informacion del dron.
    * @return String: posicion x,y,h en string.
    */
   public String toString() {
      String fString = String.format("%.2f, % .2f, % .2f, % .2f",time,x,y,h);
      return fString;
   }
   /** 
    * Realiza el despegue, dependiendo del estado del Dron.
    */
   public void takeOff() {
      if (state==DroneState.IDLE){
         state = DroneState.TAKE_OFF;
      }
      else{
         System.out.println("Error: Drone can't take off if it is already in flight...");
      }
   }
   /** 
    * Realiza el aterrizaje, dependiendo del estado del Dron.
    */
   public void land() {
      if (state==DroneState.FLYING){
         state = DroneState.LANDING;
         System.out.println("Landing ...");
      }
      else{
         System.out.println("Error: Drone can't land if it is already on ground...Maybe it crashed...?");
      }
   }
   /** 
    * Cierra el archivo correspondiente.
    */
   public void closeFile(){
      Archive.close();
   }
   /** 
    * Imprime la informacion del dron.
    */
   public void print2File(){
      Archive.write(this.toString()+'\n');

   }
}
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