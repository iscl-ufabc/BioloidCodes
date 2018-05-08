
public class Bioloid extends Ax12{
		
	private static int MAX_MOTORS;
	private static int i;

	Bioloid(int MAX_MOTORS){
    	this.MAX_MOTORS = MAX_MOTORS; // NUMBER OF USED MOTORS 
	}
    		
    static int motors[] = new int[MAX_MOTORS];
    static int initPos[] = {336, 687, 298, 724, 412, 611, 355, 664, 491, 530, 394, 625, 278, 743, 616, 405, 490, 530};
    
    public static void initialPos() {
        serial();
        
        for (i=0;i<motors.length;i++){
        	motors[i]=initPos[i];
        	move(i+1,motors[i]);
        }
    }
    
    public static void clear() {
        serial();
        
        for(i=0;i<(motors.length);i++){
        	motors[i] = 512;	
        		if(i==7-1)
        			motors[i] = 361;
        		if(i==8-1)
        			motors[i] = 663;
        	move(i,motors[i]);
        }
    }
    
    //public static readMotors(){
    	
    //}
    
}
