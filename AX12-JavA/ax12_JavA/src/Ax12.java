
import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.Serial;


import javax.swing.JOptionPane;

/*
 * AX-12A PROPERTIES:
 * 
 * ID = [0-252]
 * BAUDRATE = [2000000 - 8000]
 * RETURN DELAY TIME = [0 - 254]. Ex: 254 * (2 usec)
 * CW ANGLE LIMIT(JOINT/WHEEL MODE) = [0 - 1023] 
 * CCW ANGLE LIMIT (JOINT/WHEEL MODE) = [0 - 1023]
 * HIGHEST LIMIT TEMPERATURE = [0 - 99]̣ Celsius
 * LOWEST LIMIT VOLTAGE = [50 - 250]. Ex: 250 * (1/10) V
 * HIGHEST LIMIT VOLTAGE = [50 - 250]. Ex: 250 * (1/10) V
 * MAX TORQUE = [0 - 1023]
 * STATUS RETURN LEVEL = [0 - 2]. (0 - NO RETURN; 1 - READ ONLY; 2- ALL RETURN)
 * ALARM LED = (64 - INSTRUCTIONS; 32 - OVERLOAD; 16 - CHECKSUM; 8 - RANGE; 4 - OVERHEATING; 2 - ANGLE LIMIT; 1 - INPUT VOLTAGE)
 * ALARM SHUTDOWN = (64 - INSTRUCTIONS; 32 - OVERLOAD; 16 - CHECKSUM; 8 - RANGE; 4 - OVERHEATING; 2 - ANGLE LIMIT; 1 - INPUT VOLTAGE)
 * TORQUE ENABLE = [0 - 1]
 * LED = [0 - 1]
 * CW COMPLIANCE MARGIN = [1 - 254]
 * CCW COMPLIANCE MARGIN = [1 - 254]
 * CW COMPLIANCE SLOPE = [2, 4, 8, 16, 32, 64, 128]
 * CCW COMPLIANCE SLOPE = [2, 4, 8, 16, 32, 64, 128]
 * GOAL POSITION = [0 - 1023]
 * MOVING SPEED = [0 - 1023] RPM
 * TORQUE LIMIT = [0 - 1023]
 * PRESENT POSITION = [0 - 1023]
 * PRESENT SPEED = ONLY ON CW OR CCW MODE
 * PRESENT LOAD = [0 - 100] %
 * PRESENT VOLTAGE = [0 - 255]. Ex: 250 * (1/10) V
 * PRESENT TEMPERATURE = [0 - 99] Celsius
 * REGISTERED INSTRUCTION
 * MOVING = [0 - 1]
 * LOCK = EEPROM AREA LOCKED
 * PUNCH = [0 - 1023] 
 */

public class Ax12 {
    
	//------------------------------------------ ATRIBUTES ------------------------------------------

    // important AX-12 constants
    private final static int AX_MODEL_NUMBER_L = 0;
    private final static int AX_MODEL_NUMBER_H = 1;
    private final static int AX_VERSION = 2;
    private final static int AX_ID = 3;
    private final static int AX_BAUD_RATE = 4;
    private final static int AX_RETURN_DELAY_TIME = 5;
    private final static int AX_CW_ANGLE_LIMIT_L = 6;
    private final static int AX_CW_ANGLE_LIMIT_H = 7;
    private final static int AX_CCW_ANGLE_LIMIT_L = 8;
    private final static int AX_CCW_ANGLE_LIMIT_H = 9;
    private final static int AX_SYSTEM_DATA2 = 10;
    private final static int AX_LIMIT_TEMPERATURE = 11;
    private final static int AX_DOWN_LIMIT_VOLTAGE = 12;
    private final static int AX_UP_LIMIT_VOLTAGE = 13;
    private final static int AX_MAX_TORQUE_L = 14;
    private final static int AX_MAX_TORQUE_H = 15;
    private final static int AX_RETURN_LEVEL = 16;
    private final static int AX_ALARM_LED = 17;
    private final static int AX_ALARM_SHUTDOWN = 18;
    private final static int AX_OPERATING_MODE = 19;
    private final static int AX_DOWN_CALIBRATION_L = 20;
    private final static int AX_DOWN_CALIBRATION_H = 21;
    private final static int AX_UP_CALIBRATION_L = 22;
    private final static int AX_UP_CALIBRATION_H = 23;
    private final static int AX_TORQUE_STATUS = 24;
    private final static int AX_LED_STATUS = 25;
    private final static int AX_CW_COMPLIANCE_MARGIN = 26;
    private final static int AX_CCW_COMPLIANCE_MARGIN = 27;
    private final static int AX_CW_COMPLIANCE_SLOPE = 28;
    private final static int AX_CCW_COMPLIANCE_SLOPE = 29;
    private final static int AX_GOAL_POSITION_L = 30;
    private final static int AX_GOAL_POSITION_H = 31;
    private final static int AX_GOAL_SPEED_L = 32;
    private final static int AX_GOAL_SPEED_H = 33;
    private final static int AX_TORQUE_LIMIT_L = 34;
    private final static int AX_TORQUE_LIMIT_H = 35;
    private final static int AX_PRESENT_POSITION_L = 36;
    private final static int AX_PRESENT_POSITION_H = 37;
    private final static int AX_PRESENT_SPEED_L = 38;
    private final static int AX_PRESENT_SPEED_H = 39;
    private final static int AX_PRESENT_LOAD_L = 40;
    private final static int AX_PRESENT_LOAD_H = 41;
    private final static int AX_PRESENT_VOLTAGE = 42;
    private final static int AX_PRESENT_TEMPERATURE = 43;
    private final static int AX_REGISTERED_INSTRUCTION = 44;
    private final static int AX_PAUSE_TIME = 45;
    private final static int AX_MOVING = 46;
    private final static int AX_LOCK = 47;
    private final static int AX_PUNCH_L = 48;
    private final static int AX_PUNCH_H = 49;
    private final static int AX_RETURN_NONE = 0;
    private final static int AX_RETURN_READ = 1;
    private final static int AX_RETURN_ALL = 2;
    private final static int AX_PING = 1;
    private final static int AX_READ_DATA = 2;
    private final static int AX_WRITE_DATA = 3;
    private final static int AX_REG_WRITE = 4;
    private final static int AX_ACTION = 5;
    private final static int AX_RESET = 6;
    private final static int AX_SYNC_WRITE = 131;
    private final static int AX_RESET_LENGTH = 2;
    private final static int AX_ACTION_LENGTH = 2;
    private final static int AX_ID_LENGTH = 4;
    private final static int AX_LR_LENGTH = 4;
    private final static int AX_SRL_LENGTH = 4;
    private final static int AX_RDT_LENGTH = 4;
    private final static int AX_LEDALARM_LENGTH = 4;
    private final static int AX_SHUTDOWNALARM_LENGTH = 4;
    private final static int AX_TL_LENGTH = 4;
    private final static int AX_VL_LENGTH = 6;
    private final static int AX_AL_LENGTH = 7;
    private final static int AX_CM_LENGTH = 6;
    private final static int AX_CS_LENGTH = 5;
    private final static int AX_COMPLIANCE_LENGTH = 7;
    private final static int AX_CCW_CW_LENGTH = 8;
    private final static int AX_BD_LENGTH = 4;
    private final static int AX_TEM_LENGTH = 4;
    private final static int AX_MOVING_LENGTH = 4;
    private final static int AX_RWS_LENGTH = 4;
    private final static int AX_VOLT_LENGTH = 4;
    private final static int AX_LOAD_LENGTH = 4;
    private final static int AX_LED_LENGTH = 4;
    private final static int AX_TORQUE_LENGTH = 4;
    private final static int AX_POS_LENGTH = 4;
    private final static int AX_GOAL_LENGTH = 5;
    private final static int AX_MT_LENGTH = 5;
    private final static int AX_PUNCH_LENGTH = 5;
    private final static int AX_SPEED_LENGTH = 5;
    private final static int AX_GOAL_SP_LENGTH = 7;
    private final static int AX_BYTE_READ = 1;
    private final static int AX_INT_READ = 2;
    private final static int AX_ACTION_CHECKSUM = 250;
    private final static int AX_BROADCAST_ID = 254;
    private final static int AX_START = 255;
    private final static int AX_CCW_AL_L = 255;
    private final static int AX_CCW_AL_H = 3;
    private final static int AX_LOCK_VALUE = 1;
    private final static int LEFT = 0;
    private final static int RIGTH = 1;
    private final static int RX_TIME_OUT = 10;
    private final static double TX_DELAY_TIME = 0.00002;


    private static GpioController gpio; 
    private static GpioPinDigitalOutput RPI_DIRECTION_PIN;//PORTAS RELACIONADAS PI4J
    private static GpioPinDigitalOutput RPI_DIRECTION_PIN_RX; //PORTAS RELACIONADAS PI4J
    private static int RPI_DIRECTION_SWITCH_DELAY_MILLIS;
   
    // static variables
    private static int port = Serial.serialOpen(Serial.DEFAULT_COM_PORT, 57600);
    
    //----------------------------------------- CONSTRUCTORS -----------------------------------------
    
    public Ax12(){
    	this(57600,1);
    }
    
    public Ax12(long baudrate,int rpiDirSwitch){
    	this.gpio = GpioFactory.getInstance(); 
        this.RPI_DIRECTION_PIN = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08); //PORTAS RELACIONADAS PI4J
        this.RPI_DIRECTION_PIN_RX = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_16); //PORTAS RELACIONADAS PI4J
        this.RPI_DIRECTION_SWITCH_DELAY_MILLIS = rpiDirSwitch;
        this.port = Serial.serialOpen(Serial.DEFAULT_COM_PORT, (int) baudrate);
        
        serial(port);
    }
    
    
    //-------------------------------------------- METHODS --------------------------------------------
    
    private void serial(int port){
        if (port == -1) {
            System.out.println(" ==>> SERIAL SETUP FAILED");
            gpio.shutdown();
            return;
        }
	    
	    direction(0);
	    
    }
    
    private static void direction(int d){
        if(d==1)
            RPI_DIRECTION_PIN.high();
        else
            RPI_DIRECTION_PIN.low();
    }
    
    public void move (int id, int position) throws InterruptedException  {
    	
        direction(1);
        Serial.serialFlush(port);
        
        int [] p = {position&0xff,position >> 8};
        int checksum = (~(id + Ax12.AX_GOAL_LENGTH + Ax12.AX_WRITE_DATA + Ax12.AX_GOAL_POSITION_L + p[0] + p[1]))&0xff;

        Serial.serialPutchar(port, (char) Ax12.AX_START);      
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) id); 
        Serial.serialPutchar(port, (char) Ax12.AX_GOAL_LENGTH);   
        Serial.serialPutchar(port, (char) Ax12.AX_WRITE_DATA);   
        Serial.serialPutchar(port, (char) Ax12.AX_GOAL_POSITION_L);   
        Serial.serialPutchar(port, (char) p[0]);   
        Serial.serialPutchar(port, (char) p[1]);   
        Serial.serialPutchar(port, (char) checksum);  
        Thread.sleep(10);
        gpio.shutdown();
    }
    
    public void moveSpeed(int id, int position, int speed){
    	
        direction(1);
        Serial.serialFlush(port);
        
        int [] p = {position&0xff,position >> 8};
        int [] s = {speed&0xff, speed>>8};
        int checksum = (~(id + Ax12.AX_GOAL_LENGTH + Ax12.AX_WRITE_DATA + Ax12.AX_GOAL_POSITION_L + p[0] + p[1] + s[0] + s[1]))&0xff;
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) id); 
        Serial.serialPutchar(port, (char) Ax12.AX_GOAL_LENGTH);   
        Serial.serialPutchar(port, (char) Ax12.AX_WRITE_DATA);   
        Serial.serialPutchar(port, (char) Ax12.AX_GOAL_POSITION_L);   
        Serial.serialPutchar(port, (char) p[0]);   
        Serial.serialPutchar(port, (char) p[1]);   
        Serial.serialPutchar(port, (char) s[0]);   
        Serial.serialPutchar(port, (char) s[1]); 
        Serial.serialPutchar(port, (char) checksum);   
        gpio.shutdown(); 
    }
	
    public void ping (int id) {
    	
        direction(1);
        Serial.serialFlush(port);
        
        int checksum = (~(id + Ax12.AX_READ_DATA + Ax12.AX_PING))&0xff;
        Serial.serialPutchar(port, (char) Ax12.AX_START);      
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) id); 
        Serial.serialPutchar(port, (char) Ax12.AX_READ_DATA);   
        Serial.serialPutchar(port, (char) Ax12.AX_PING);   
        Serial.serialPutchar(port, (char) checksum);   
        gpio.shutdown();
    }
	
    public void factoryReset(int id){
			
		String[] options = new String[] {"SIM", "NAO"};
		
		int response = JOptionPane.showOptionDialog(null, "DESEJA REALIZAR A RESTAURACAO DE FABRICA? \n"
					+ "OBS: Essa operacao tornara o motor incompati­vel com AX12-JavA, lembre-se de alterar o baudrate do motor e da porta serial",
					"Preparando para Reset de Fabrica", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
				null, options, options[0]);   
			
		if (response == 0){    
			direction(1);
	        Serial.serialFlush(port);
			int checksum = (~(id + Ax12.AX_RESET_LENGTH + Ax12.AX_RESET))&0xff;
			Serial.serialPutchar(port, (char) Ax12.AX_START);      
			Serial.serialPutchar(port, (char) Ax12.AX_START);   
			Serial.serialPutchar(port, (char) id); 
			Serial.serialPutchar(port, (char) Ax12.AX_RESET_LENGTH);   
			Serial.serialPutchar(port, (char) Ax12.AX_RESET);   
			Serial.serialPutchar(port, (char) checksum);   
			gpio.shutdown(); 
		}
		else{
			System.out.println("");
			return;
		}
    }

    public void setID (int id, int newId) {
    	
        direction(1);
        Serial.serialFlush(port);

        int checksum = (~(id + Ax12.AX_ID_LENGTH + Ax12.AX_WRITE_DATA + Ax12.AX_ID + newId))&0xff;
        Serial.serialPutchar(port, (char) Ax12.AX_START);      
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) id); 
        Serial.serialPutchar(port, (char) Ax12.AX_ID_LENGTH);   
        Serial.serialPutchar(port, (char) Ax12.AX_WRITE_DATA);   
        Serial.serialPutchar(port, (char) Ax12.AX_ID);   
        Serial.serialPutchar(port, (char) newId);      
        Serial.serialPutchar(port, (char) checksum);   
        gpio.shutdown();
    }
        
    public void setBaudRate (int id, int baudRate) {
    	
        direction(1);
        Serial.serialFlush(port);

        int br = ((2000000/(baudRate))-1);
        int checksum = (~(id + Ax12.AX_BD_LENGTH + Ax12.AX_WRITE_DATA + Ax12.AX_BAUD_RATE + br))&0xff;
        Serial.serialPutchar(port, (char) Ax12.AX_START);      
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) id); 
        Serial.serialPutchar(port, (char) Ax12.AX_BD_LENGTH);   
        Serial.serialPutchar(port, (char) Ax12.AX_WRITE_DATA);   
        Serial.serialPutchar(port, (char) Ax12.AX_BAUD_RATE);   
        Serial.serialPutchar(port, (char) br);
        Serial.serialPutchar(port, (char) checksum);   
        gpio.shutdown();
    }
    
    public void setStatusReturnLevel (int id, int level) {
    	
        direction(1);
        Serial.serialFlush(port);
        
        int checksum = (~(id + Ax12.AX_SRL_LENGTH + Ax12.AX_WRITE_DATA + Ax12.AX_RETURN_LEVEL + level))&0xff;
        Serial.serialPutchar(port, (char) Ax12.AX_START);      
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) id); 
        Serial.serialPutchar(port, (char) Ax12.AX_GOAL_LENGTH);   
        Serial.serialPutchar(port, (char) Ax12.AX_WRITE_DATA);   
        Serial.serialPutchar(port, (char) Ax12.AX_GOAL_POSITION_L);   
        Serial.serialPutchar(port, (char) checksum);   
        gpio.shutdown();
    }
    
    public void setReturnDelayTime (int id, int delay) {
    	
        direction(1);
        Serial.serialFlush(port);

        int checksum = (~(id + Ax12.AX_RDT_LENGTH + Ax12.AX_WRITE_DATA + Ax12.AX_RETURN_DELAY_TIME + (delay/2)))&0xff;
        Serial.serialPutchar(port, (char) Ax12.AX_START);      
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) id); 
        Serial.serialPutchar(port, (char) Ax12.AX_RDT_LENGTH);   
        Serial.serialPutchar(port, (char) Ax12.AX_WRITE_DATA);   
        Serial.serialPutchar(port, (char) Ax12.AX_RETURN_DELAY_TIME);   
        Serial.serialPutchar(port, (char) (delay/2));     
        Serial.serialPutchar(port, (char) checksum);   
        gpio.shutdown();
    }
    
    public void lockRegister (int id) {
    	
        direction(1);
        Serial.serialFlush(port);

        int checksum = (~(id + Ax12.AX_LR_LENGTH + Ax12.AX_WRITE_DATA + Ax12.AX_LOCK + Ax12.AX_LOCK_VALUE))&0xff;
        Serial.serialPutchar(port, (char) Ax12.AX_START);      
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) id); 
        Serial.serialPutchar(port, (char) Ax12.AX_LR_LENGTH);   
        Serial.serialPutchar(port, (char) Ax12.AX_WRITE_DATA);   
        Serial.serialPutchar(port, (char) Ax12.AX_LOCK);   
        Serial.serialPutchar(port, (char) Ax12.AX_LOCK_VALUE);      
        Serial.serialPutchar(port, (char) checksum);   
        gpio.shutdown();
    }

    public void moveRW (int id, int position) {
    	
        direction(1);
        Serial.serialFlush(port);

        int [] p = {position&0xff,position >> 8};
        int checksum = (~(id + Ax12.AX_GOAL_LENGTH + Ax12.AX_REG_WRITE + Ax12.AX_GOAL_POSITION_L + p[0] + p[1]))&0xff;
        Serial.serialPutchar(port, (char) Ax12.AX_START);      
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) id); 
        Serial.serialPutchar(port, (char) Ax12.AX_GOAL_LENGTH);   
        Serial.serialPutchar(port, (char) Ax12.AX_REG_WRITE);   
        Serial.serialPutchar(port, (char) Ax12.AX_GOAL_POSITION_L);   
        Serial.serialPutchar(port, (char) p[0]);   
        Serial.serialPutchar(port, (char) p[1]);   
        Serial.serialPutchar(port, (char) checksum);   
        gpio.shutdown();
    }
    
    public void moveSpeedRW (int id, int position, int speed){
    	
        direction(1);
        Serial.serialFlush(port);
        
        int [] p = {position&0xff,position >> 8};
        int [] s = {speed&0xff, speed>>8};
        int checksum = (~(id + Ax12.AX_GOAL_SP_LENGTH + Ax12.AX_REG_WRITE + Ax12.AX_GOAL_POSITION_L + p[0] + p[1] + s[0] + s[1]))&0xff;
        Serial.serialPutchar(port, (char) Ax12.AX_START);      
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) id); 
        Serial.serialPutchar(port, (char) Ax12.AX_GOAL_SP_LENGTH);   
        Serial.serialPutchar(port, (char) Ax12.AX_REG_WRITE);   
        Serial.serialPutchar(port, (char) Ax12.AX_GOAL_POSITION_L);   
        Serial.serialPutchar(port, (char) p[0]);   
        Serial.serialPutchar(port, (char) p[1]);   
        Serial.serialPutchar(port, (char) s[0]);   
        Serial.serialPutchar(port, (char) s[1]); 
        Serial.serialPutchar(port, (char) checksum);   
        gpio.shutdown();
    }
    
    public void action () {
    	
        direction(1);
        Serial.serialFlush(port);
        
        Serial.serialPutchar(port, (char) Ax12.AX_START);      
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) Ax12.AX_BROADCAST_ID); 
        Serial.serialPutchar(port, (char) Ax12.AX_ACTION_LENGTH);   
        Serial.serialPutchar(port, (char) Ax12.AX_ACTION);   
        Serial.serialPutchar(port, (char) Ax12.AX_ACTION_CHECKSUM);            
        gpio.shutdown();
    }

    public void setTorqueStatus (int id, int status) {
    	
        direction(1);
        Serial.serialFlush(port);
        
        int ts;
        if(status==1) ts=0; else ts=0;
        int checksum = (~(id + Ax12.AX_TORQUE_LENGTH + Ax12.AX_WRITE_DATA + Ax12.AX_LED_STATUS + ts))&0xff;
        Serial.serialPutchar(port, (char) Ax12.AX_START);      
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) id); 
        Serial.serialPutchar(port, (char) Ax12.AX_TORQUE_LENGTH);   
        Serial.serialPutchar(port, (char) Ax12.AX_WRITE_DATA);   
        Serial.serialPutchar(port, (char) Ax12.AX_LED_STATUS);   
        Serial.serialPutchar(port, (char) ts);      
        Serial.serialPutchar(port, (char) checksum);   
        gpio.shutdown();
    }

    public void setLedStatus (int id, int status) {
    	
        direction(1);
        Serial.serialFlush(port);
        
        int ls;
        if(status==1) ls=0; else ls=0;
        int checksum = (~(id + Ax12.AX_LED_LENGTH + Ax12.AX_WRITE_DATA + Ax12.AX_LED_STATUS + ls))&0xff;
        Serial.serialPutchar(port, (char) Ax12.AX_START);      
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) id); 
        Serial.serialPutchar(port, (char) Ax12.AX_LED_LENGTH);   
        Serial.serialPutchar(port, (char) Ax12.AX_WRITE_DATA);   
        Serial.serialPutchar(port, (char) Ax12.AX_LED_STATUS);   
        Serial.serialPutchar(port, (char) ls);      
        Serial.serialPutchar(port, (char) checksum);   
        gpio.shutdown();
    }
    
    public void setTemperatureLimit (int id, int temp) {
    	
        direction(1);
        Serial.serialFlush(port);
        
        int checksum = (~(id + Ax12.AX_TL_LENGTH + Ax12.AX_WRITE_DATA + Ax12.AX_LIMIT_TEMPERATURE + temp))&0xff;
        Serial.serialPutchar(port, (char) Ax12.AX_START);      
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) id); 
        Serial.serialPutchar(port, (char) Ax12.AX_TL_LENGTH);   
        Serial.serialPutchar(port, (char) Ax12.AX_WRITE_DATA);   
        Serial.serialPutchar(port, (char) Ax12.AX_LIMIT_TEMPERATURE);   
        Serial.serialPutchar(port, (char) temp );      
        Serial.serialPutchar(port, (char) checksum);   
        gpio.shutdown();
    }        
    
    public void setVoltageLimit (int id, int lowVolt, int highVolt) {
    	
        direction(1);
        Serial.serialFlush(port);
        
        int checksum = (~(id + Ax12.AX_VL_LENGTH + Ax12.AX_WRITE_DATA + Ax12.AX_DOWN_LIMIT_VOLTAGE + lowVolt + highVolt))&0xff;
        Serial.serialPutchar(port, (char) Ax12.AX_START);      
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) id); 
        Serial.serialPutchar(port, (char) Ax12.AX_VL_LENGTH);   
        Serial.serialPutchar(port, (char) Ax12.AX_WRITE_DATA);   
        Serial.serialPutchar(port, (char) Ax12.AX_DOWN_LIMIT_VOLTAGE);   
        Serial.serialPutchar(port, (char) lowVolt);
        Serial.serialPutchar(port, (char) highVolt);
        Serial.serialPutchar(port, (char) checksum);   
        gpio.shutdown();
    }        
    
    public void setAngleLimit (int id, int cwLimit, int ccwLimit) {
    	
        direction(1);
        Serial.serialFlush(port);
        
        int [] cw = {cwLimit&0xff, cwLimit>> 8};
        int [] ccw = {ccwLimit&0xff, ccwLimit>>8};
        int checksum = (~(id + Ax12.AX_AL_LENGTH + Ax12.AX_WRITE_DATA + Ax12.AX_CW_ANGLE_LIMIT_L + cw[0] + cw[1] + ccw[0] + ccw[1]))&0xff;
        Serial.serialPutchar(port, (char) Ax12.AX_START);      
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) id); 
        Serial.serialPutchar(port, (char) Ax12.AX_AL_LENGTH);   
        Serial.serialPutchar(port, (char) Ax12.AX_WRITE_DATA);   
        Serial.serialPutchar(port, (char) Ax12.AX_CW_ANGLE_LIMIT_L);   
        Serial.serialPutchar(port, (char) cw[0]);
        Serial.serialPutchar(port, (char) cw[1]);
        Serial.serialPutchar(port, (char) ccw[0]);
        Serial.serialPutchar(port, (char) ccw[1]);
        Serial.serialPutchar(port, (char) checksum);   
        gpio.shutdown();
    }        
    
    public void setTorqueLimit (int id, int torque) {
    	
        direction(1);
        Serial.serialFlush(port);
        
        int [] mt = {torque&0xff, torque>> 8};
        int checksum = (~(id + Ax12.AX_MT_LENGTH + Ax12.AX_WRITE_DATA + Ax12.AX_MAX_TORQUE_L + mt[0] + mt[1]))&0xff;
        Serial.serialPutchar(port, (char) Ax12.AX_START);      
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) id); 
        Serial.serialPutchar(port, (char) Ax12.AX_MT_LENGTH);   
        Serial.serialPutchar(port, (char) Ax12.AX_WRITE_DATA);   
        Serial.serialPutchar(port, (char) Ax12.AX_MAX_TORQUE_L);   
        Serial.serialPutchar(port, (char) mt[0]);
        Serial.serialPutchar(port, (char) mt[1]);
        Serial.serialPutchar(port, (char) checksum);   
        gpio.shutdown();
    }    
    
    public void setPunchLimit (int id, int punch) {
    	
        direction(1);
        Serial.serialFlush(port);
        
        int [] p = {punch&0xff, punch>> 8};
        int checksum = (~(id + Ax12.AX_PUNCH_LENGTH + Ax12.AX_WRITE_DATA + Ax12.AX_PUNCH_L + p[0] + p[1]))&0xff;
        Serial.serialPutchar(port, (char) Ax12.AX_START);      
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) id); 
        Serial.serialPutchar(port, (char) Ax12.AX_PUNCH_LENGTH);   
        Serial.serialPutchar(port, (char) Ax12.AX_WRITE_DATA);   
        Serial.serialPutchar(port, (char) Ax12.AX_PUNCH_L);   
        Serial.serialPutchar(port, (char) p[0]);
        Serial.serialPutchar(port, (char) p[1]);
        Serial.serialPutchar(port, (char) checksum);   
        gpio.shutdown();
    }    
    
    public void setCompliance (int id, int cwMargin, int ccwMargin, int cwSlope, int ccwSlope) {
    	
        direction(1);
        Serial.serialFlush(port);
        
        int checksum = (~(id + Ax12.AX_COMPLIANCE_LENGTH + Ax12.AX_WRITE_DATA + Ax12.AX_CW_COMPLIANCE_MARGIN + cwMargin + ccwMargin + cwSlope + ccwSlope))&0xff;
        Serial.serialPutchar(port, (char) Ax12.AX_START);      
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) id); 
        Serial.serialPutchar(port, (char) Ax12.AX_COMPLIANCE_LENGTH);   
        Serial.serialPutchar(port, (char) Ax12.AX_WRITE_DATA);   
        Serial.serialPutchar(port, (char) Ax12.AX_CW_COMPLIANCE_MARGIN);   
        Serial.serialPutchar(port, (char) cwMargin);
        Serial.serialPutchar(port, (char) ccwMargin);
        Serial.serialPutchar(port, (char) cwSlope);
        Serial.serialPutchar(port, (char) ccwSlope);
        Serial.serialPutchar(port, (char) checksum);   
        gpio.shutdown();
    }    
    
    public void setLedAlarm (int id, int alarm) {
    	
        direction(1);
        Serial.serialFlush(port);
        
        int checksum = (~(id + Ax12.AX_LEDALARM_LENGTH + Ax12.AX_WRITE_DATA + Ax12.AX_ALARM_LED + alarm))&0xff;
        Serial.serialPutchar(port, (char) Ax12.AX_START);      
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) id); 
        Serial.serialPutchar(port, (char) Ax12.AX_LEDALARM_LENGTH);   
        Serial.serialPutchar(port, (char) Ax12.AX_WRITE_DATA);   
        Serial.serialPutchar(port, (char) Ax12.AX_ALARM_LED);   
        Serial.serialPutchar(port, (char) alarm);
        Serial.serialPutchar(port, (char) checksum);   
        gpio.shutdown();
    }    
     
    public void setShutdownAlarm (int id, int alarm) {
    	
        direction(1);
        Serial.serialFlush(port);
        
        int checksum = (~(id + Ax12.AX_SHUTDOWNALARM_LENGTH + Ax12.AX_WRITE_DATA + Ax12.AX_ALARM_SHUTDOWN + alarm))&0xff;
        Serial.serialPutchar(port, (char) Ax12.AX_START);      
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) id); 
        Serial.serialPutchar(port, (char) Ax12.AX_SHUTDOWNALARM_LENGTH);   
        Serial.serialPutchar(port, (char) Ax12.AX_WRITE_DATA);   
        Serial.serialPutchar(port, (char) Ax12.AX_ALARM_SHUTDOWN);   
        Serial.serialPutchar(port, (char) alarm);
        Serial.serialPutchar(port, (char) checksum);   
        gpio.shutdown();
    }    
    
	public void readTemperature (int id) {
    	
        direction(1);
        Serial.serialFlush(port);
        
        int checksum = (~(id + Ax12.AX_TEM_LENGTH + Ax12.AX_READ_DATA + Ax12.AX_PRESENT_TEMPERATURE + Ax12.AX_BYTE_READ))&0xff;
        Serial.serialPutchar(port, (char) Ax12.AX_START);      
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) id); 
        Serial.serialPutchar(port, (char) Ax12.AX_TEM_LENGTH);   
        Serial.serialPutchar(port, (char) Ax12.AX_READ_DATA);   
        Serial.serialPutchar(port, (char) Ax12.AX_PRESENT_TEMPERATURE);   
        Serial.serialPutchar(port, (char) Ax12.AX_BYTE_READ);
        Serial.serialPutchar(port, (char) checksum);   
        gpio.shutdown();
    }        
        
    public void readPosition (int id) throws InterruptedException {
    	
        direction(1);
        Serial.serialFlush(port);
        
        int checksum = (~(id + Ax12.AX_POS_LENGTH + Ax12.AX_READ_DATA + Ax12.AX_PRESENT_POSITION_L + Ax12.AX_INT_READ))&0xff;
        Serial.serialPutchar(port, (char) Ax12.AX_START);      
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) id); 
        Serial.serialPutchar(port, (char) Ax12.AX_POS_LENGTH);   
        Serial.serialPutchar(port, (char) Ax12.AX_READ_DATA);   
        Serial.serialPutchar(port, (char) Ax12.AX_PRESENT_POSITION_L);   
        Serial.serialPutchar(port, (char) Ax12.AX_INT_READ);
        Serial.serialPutchar(port, (char) checksum);   
        
        Thread.sleep(RPI_DIRECTION_SWITCH_DELAY_MILLIS);
        direction(0);
        Serial.serialFlush(port);
        
        byte reply[] = new byte[12];
        double initialTime = System.currentTimeMillis();
        double currentTime = initialTime;
        
        while((currentTime - initialTime)<5000){
        	System.out.println("Leitura: ");
	        for (int i = 0; Serial.serialDataAvail(port)>0; i++){
	        	//reply = Serial.serialGetAvailableBytes(port);
	        	reply[i] = Serial.serialGetByte(port);
	        	System.out.println(reply[i]);
	        }
        //System.out.println(reply[0]);
	        currentTime = System.currentTimeMillis();
        }
        
        gpio.shutdown();
    }        
    
    public void readVoltage (int id) {
        
    	direction(1);
        Serial.serialFlush(port);
        
        int checksum = (~(id + Ax12.AX_VOLT_LENGTH + Ax12.AX_READ_DATA + Ax12.AX_PRESENT_VOLTAGE + Ax12.AX_BYTE_READ))&0xff;
        Serial.serialPutchar(port, (char) Ax12.AX_START);      
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) id); 
        Serial.serialPutchar(port, (char) Ax12.AX_VOLT_LENGTH);   
        Serial.serialPutchar(port, (char) Ax12.AX_READ_DATA);   
        Serial.serialPutchar(port, (char) Ax12.AX_PRESENT_VOLTAGE);   
        Serial.serialPutchar(port, (char) Ax12.AX_BYTE_READ);
        Serial.serialPutchar(port, (char) checksum);   
        gpio.shutdown();
    }
    
    public void readSpeed (int id) {
    	
        direction(1);
        Serial.serialFlush(port);
        
        int checksum = (~(id + Ax12.AX_SPEED_LENGTH + Ax12.AX_READ_DATA + Ax12.AX_PRESENT_SPEED_L + Ax12.AX_INT_READ))&0xff;
        Serial.serialPutchar(port, (char) Ax12.AX_START);      
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) id); 
        Serial.serialPutchar(port, (char) Ax12.AX_SPEED_LENGTH);   
        Serial.serialPutchar(port, (char) Ax12.AX_READ_DATA);   
        Serial.serialPutchar(port, (char) Ax12.AX_PRESENT_SPEED_L);   
        Serial.serialPutchar(port, (char) Ax12.AX_INT_READ);
        Serial.serialPutchar(port, (char) checksum);   
        gpio.shutdown();
    }
    
    public void readLoad (int id) {
    	
        direction(1);
        Serial.serialFlush(port);
        
        int checksum = (~(id + Ax12.AX_LOAD_LENGTH + Ax12.AX_READ_DATA + Ax12.AX_PRESENT_LOAD_L + Ax12.AX_INT_READ))&0xff;
        Serial.serialPutchar(port, (char) Ax12.AX_START);      
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) id); 
        Serial.serialPutchar(port, (char) Ax12.AX_LOAD_LENGTH);   
        Serial.serialPutchar(port, (char) Ax12.AX_READ_DATA);   
        Serial.serialPutchar(port, (char) Ax12.AX_PRESENT_LOAD_L);   
        Serial.serialPutchar(port, (char) Ax12.AX_INT_READ);
        Serial.serialPutchar(port, (char) checksum);   
        gpio.shutdown();
    }
    
    public void readMovingStatus (int id) {
    	
        direction(1);
        Serial.serialFlush(port);
        
        int checksum = (~(id + Ax12.AX_MOVING_LENGTH + Ax12.AX_READ_DATA + Ax12.AX_MOVING + Ax12.AX_BYTE_READ))&0xff;
        Serial.serialPutchar(port, (char) Ax12.AX_START);      
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) id); 
        Serial.serialPutchar(port, (char) Ax12.AX_MOVING_LENGTH);   
        Serial.serialPutchar(port, (char) Ax12.AX_READ_DATA);   
        Serial.serialPutchar(port, (char) Ax12.AX_MOVING);   
        Serial.serialPutchar(port, (char) Ax12.AX_BYTE_READ);
        Serial.serialPutchar(port, (char) checksum);   
        gpio.shutdown();
    }
    
    public void readRWStatus (int id) {
    	
        direction(1);
        Serial.serialFlush(port);
        
        int checksum = (~(id + Ax12.AX_RWS_LENGTH + Ax12.AX_READ_DATA + Ax12.AX_REGISTERED_INSTRUCTION + Ax12.AX_BYTE_READ))&0xff;
        Serial.serialPutchar(port, (char) Ax12.AX_START);      
        Serial.serialPutchar(port, (char) Ax12.AX_START);   
        Serial.serialPutchar(port, (char) id); 
        Serial.serialPutchar(port, (char) Ax12.AX_RWS_LENGTH);   
        Serial.serialPutchar(port, (char) Ax12.AX_READ_DATA);   
        Serial.serialPutchar(port, (char) Ax12.AX_REGISTERED_INSTRUCTION);   
        Serial.serialPutchar(port, (char) Ax12.AX_BYTE_READ);
        Serial.serialPutchar(port, (char) checksum);   
        gpio.shutdown();
    }
    
    
///////////////////////////////////////////////////////////////////////////////////////////    
//def learnServos(self,minValue=1, maxValue=6, verbose=False) :
//        servoList = []
//        for i in range(minValue, maxValue + 1):
//            try :
//                temp = self.ping(i)
//               servoList.append(i)
//                if verbose: print "Found servo #" + str(i)
//                time.sleep(0.1)
//
//            except Exception, detail:
//                if verbose : print "Error pinging servo #" + str(i) + ': ' + str(detail)
//                pass
//        return servoList    
////////////////////////////////////////////////////////////////////////////////////////////  
	
}

