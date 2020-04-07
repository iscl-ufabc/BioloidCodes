import java.io.IOException;


public class Bioloid extends Ax12{
		
    //------------------------------------------ ATRIBUTES ------------------------------------------
	
    private int MAX_MOTORS;
    private int motors[] = new int[MAX_MOTORS];
    private int initPos[] = {336, 687, 298, 724, 412, 611, 355, 664, 491, 530, 394, 625, 278, 743, 616, 405, 490, 530};
    private long baudrate;
    private panTilt neck;

    //----------------------------------------- CONSTRUCTORS -----------------------------------------
    
	public Bioloid(int MAX_MOTORS) {
		this(MAX_MOTORS, (long) 1000000);
	}	
	
	public Bioloid(int MAX_MOTORS, long baudrate) {
		this.MAX_MOTORS = MAX_MOTORS; // NUMBER OF USED MOTORS 
		this.baudrate = baudrate;
		try {
			neck = new panTilt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//-------------------------------------------- METHODS --------------------------------------------

    public void initialPos() throws InterruptedException {
        for (int i=0;i<motors.length;i++){
        	motors[i]=initPos[i];
        	this.move(i+1,motors[i]);
        	Thread.sleep(1000);
        }
    }
    
    public void initialPos(long delayTime) throws InterruptedException {
        for (int i=0;i<motors.length;i++){
        	motors[i]=initPos[i];
        	this.move(i+1,motors[i]);
        	Thread.sleep(delayTime);
        }
    }
    
    public void clear() throws InterruptedException {
        for(int i=0;i<(motors.length);i++){
        	motors[i] = 512;	
        		if(i==(7-1))
        			motors[i] = 361;
        		if(i==(8-1))
        			motors[i] = 663;
        	this.move(i+1,motors[i]);
        	Thread.sleep(1000);
        }
    }
    
    public void clear(long delayTime) throws InterruptedException {
        for(int i=0;i<(motors.length);i++){
        	motors[i] = 512;	
        		if(i==(7-1))
        			motors[i] = 361;
        		if(i==(8-1))
        			motors[i] = 663;
        	this.move(i+1,motors[i]);
        	Thread.sleep(delayTime);
        }
    }
    
    public void neck(int setBitPan, int setBitTilt){
    	neck.run(setBitPan, setBitTilt);
    }
    
    
    //public static readMotors(){
    	
    //}
    
}
