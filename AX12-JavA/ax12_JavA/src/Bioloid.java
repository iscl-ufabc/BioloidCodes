
public class Bioloid extends Ax12{
		
	public static int MAX_MOTOR;
    public static int motors[] = new int[MAX_MOTOR];
    public static int initPos[] = {336, 687, 298, 724, 412, 611, 355, 664, 491, 530, 394, 625, 278, 743, 616, 405, 490, 530};
    

	Bioloid(int MAX_MOTORS){
    	MAX_MOTOR = MAX_MOTORS; // NUMBER OF USED MOTORS 
	}
    		

    public static void initialPos() throws InterruptedException {
        //serial();
        
        for (int i=0;i<motors.length;i++){
        	motors[i]=initPos[i];
        	move(i+1,motors[i]);
        	Thread.sleep(1000);
        }
    }
    
    public static void clear() throws InterruptedException {
        //serial();
        
        for(int i=0;i<(motors.length);i++){
        	motors[i] = 512;	
        		if(i==(7-1))
        			motors[i] = 361;
        		if(i==(8-1))
        			motors[i] = 663;
        	move(i+1,motors[i]);
        	Thread.sleep(1000);
        }
    }
    
    //public static readMotors(){
    	
    //}
    
}
