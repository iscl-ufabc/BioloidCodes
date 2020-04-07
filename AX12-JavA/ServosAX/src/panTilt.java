import java.io.IOException;

import com.pi4j.wiringpi.Gpio;


public class panTilt {
	
	//------------------------------------------ ATRIBUTES ------------------------------------------
	
	private int portPan, portTilt;
	private Runtime runTime = Runtime.getRuntime();
	
	//----------------------------------------- CONSTRUCTORS -----------------------------------------
	
	public panTilt() throws IOException{
		this(23,26);
	}
	
	public panTilt(int portPan, int portTilt) {
		setPortPan(portPan);
		setPortTilt(portTilt);
		
		//System.out.println(getPortPan());
		
        try {
			runTime.exec("gpio mode "+getPortPan()+ " pwm");
			runTime.exec("gpio  mode "+getPortTilt()+ " pwm");
			
			runTime.exec("gpio pwm-ms");
	        runTime.exec("gpio pwmc 192"); 
	        runTime.exec("gpio pwmr 2000"); 
	        
	        runTime.exec("gpio pwm "+getPortPan()+ " 152"); // ~center
	        runTime.exec("gpio pwm "+getPortTilt()+ " 152");

            Thread.sleep(500);
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
		
	}
	
	//------------------------------------- GETTERS AND SETTERS --------------------------------------
	
	private void setPortPan(int portPan){
		this.portPan = portPan;
	}
	
	private void setPortTilt(int portTilt){
		this.portTilt = portTilt;
	}
	
	public int getPortPan(){
		return this.portPan;
	}
	
	public int getPortTilt(){
		return this.portTilt;
	}
	
    //-------------------------------------------- METHODS --------------------------------------------
	
	public void runPan(int setBit){
		try {
			runTime.exec("gpio pwm "+getPortPan() +" "+ setBit);
			//Thread.sleep(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void runTilt(int setBit){
		try {
			runTime.exec("gpio pwm "+ getPortTilt()+ setBit);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void run(int setBitPan, int setBitTilt){
		runPan(setBitPan);
		runTilt(setBitTilt);
	}

}
