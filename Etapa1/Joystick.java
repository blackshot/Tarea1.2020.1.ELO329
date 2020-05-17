public class Joystick {
   // FIELDS
   private float vf, hf;

   // Constructor Method
   public Joystick () {
      vf=0.0f;
      hf=0.0f;
   }

   // Methods
   /** 
    * Determina una posicion vertical del joystick segun 
    * el parametro tipo float Value.
    * @param Value (float): angulo de posicion
    */
   public void setVerPos(float Value){
      vf = Value;
   }
   /** 
    * Determina una posicion horizontal del joystick segun 
    * el parametro tipo float Value.
    * @param Value (float): angulo de posicion
    */
   public void setHorPos(float Value){
      hf = Value;
   } 
   /** 
    * Obtiene la posicion vertical del joystick.
    * @return float: angulo de posicion vertical
    */
   public float getVerPos() {
     return vf;
   } 
   /** 
    * Obtiene la posicion horizontal del joystick.
    * @return float: angulo de posicion horizontal
    */
   public float getHorPos() {
      return hf;
   } 
   /** 
    * Convierte las posiciones hor. y vert. del joystick
    * en datos tipo String.
    * @return String: angulos de posicion
    */
   public String toString() {
      return hf+","+vf;
   }
}