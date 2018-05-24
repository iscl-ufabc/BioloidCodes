
import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.Serial;


import javax.swing.JOptionPane;


public class Ax12 {
    
    // important AX-12 constants
    public static final int AX_MODEL_NUMBER_L = 0;
    public static final int AX_MODEL_NUMBER_H = 1;
    public static final int AX_VERSION = 2;
    public static final int AX_ID = 3;
    public static final int AX_BAUD_RATE = 4;
    public static final int AX_RETURN_DELAY_TIME = 5;
    public static final int AX_CW_ANGLE_LIMIT_L = 6;
    public static final int AX_CW_ANGLE_LIMIT_H = 7;
    public static final int AX_CCW_ANGLE_LIMIT_L = 8;
    public static final int AX_CCW_ANGLE_LIMIT_H = 9;
    public static final int AX_SYSTEM_DATA2 = 10;
    public static final int AX_LIMIT_TEMPERATURE = 11;
    public static final int AX_DOWN_LIMIT_VOLTAGE = 12;
    public static final int AX_UP_LIMIT_VOLTAGE = 13;
    public static final int AX_MAX_TORQUE_L = 14;
    public static final int AX_MAX_TORQUE_H = 15;
    public static final int AX_RETURN_LEVEL = 16;
    public static final int AX_ALARM_LED = 17;
    public static final int AX_ALARM_SHUTDOWN = 18;
    public static final int AX_OPERATING_MODE = 19;
    public static final int AX_DOWN_CALIBRATION_L = 20;
    public static final int AX_DOWN_CALIBRATION_H = 21;
    public static final int AX_UP_CALIBRATION_L = 22;
    public static final int AX_UP_CALIBRATION_H = 23;
    public static final int AX_TORQUE_STATUS = 24;
    public static final int AX_LED_STATUS = 25;
    public static final int AX_CW_COMPLIANCE_MARGIN = 26;
    public static final int AX_CCW_COMPLIANCE_MARGIN = 27;
    public static final int AX_CW_COMPLIANCE_SLOPE = 28;
    public static final int AX_CCW_COMPLIANCE_SLOPE = 29;
    public static final int AX_GOAL_POSITION_L = 30;
    public static final int AX_GOAL_POSITION_H = 31;
    public static final int AX_GOAL_SPEED_L = 32;
    public static final int AX_GOAL_SPEED_H = 33;
    public static final int AX_TORQUE_LIMIT_L = 34;
    public static final int AX_TORQUE_LIMIT_H = 35;
    public static final int AX_PRESENT_POSITION_L = 36;
    public static final int AX_PRESENT_POSITION_H = 37;
    public static final int AX_PRESENT_SPEED_L = 38;
    public static final int AX_PRESENT_SPEED_H = 39;
    public static final int AX_PRESENT_LOAD_L = 40;
    public static final int AX_PRESENT_LOAD_H = 41;
    public static final int AX_PRESENT_VOLTAGE = 42;
    public static final int AX_PRESENT_TEMPERATURE = 43;
    public static final int AX_REGISTERED_INSTRUCTION = 44;
    public static final int AX_PAUSE_TIME = 45;
    public static final int AX_MOVING = 46;
    public static final int AX_LOCK = 47;
    public static final int AX_PUNCH_L = 48;
    public static final int AX_PUNCH_H = 49;
    public static final int AX_RETURN_NONE = 0;
    public static final int AX_RETURN_READ = 1;
    public static final int AX_RETURN_ALL = 2;
    public static final int AX_PING = 1;
    public static final int AX_READ_DATA = 2;
    public static final int AX_WRITE_DATA = 3;
    public static final int AX_REG_WRITE = 4;
    public static final int AX_ACTION = 5;
    public static final int AX_RESET = 6;
    public static final int AX_SYNC_WRITE = 131;
    public static final int AX_RESET_LENGTH = 2;
    public static final int AX_ACTION_LENGTH = 2;
    public static final int AX_ID_LENGTH = 4;
    public static final int AX_LR_LENGTH = 4;
    public static final int AX_SRL_LENGTH = 4;
    public static final int AX_RDT_LENGTH = 4;
    public static final int AX_LEDALARM_LENGTH = 4;
    public static final int AX_SHUTDOWNALARM_LENGTH = 4;
    public static final int AX_TL_LENGTH = 4;
    public static final int AX_VL_LENGTH = 6;
    public static final int AX_AL_LENGTH = 7;
    public static final int AX_CM_LENGTH = 6;
    public static final int AX_CS_LENGTH = 5;
    public static final int AX_COMPLIANCE_LENGTH = 7;
    public static final int AX_CCW_CW_LENGTH = 8;
    public static final int AX_BD_LENGTH = 4;
    public static final int AX_TEM_LENGTH = 4;
    public static final int AX_MOVING_LENGTH = 4;
    public static final int AX_RWS_LENGTH = 4;
    public static final int AX_VOLT_LENGTH = 4;
    public static final int AX_LOAD_LENGTH = 4;
    public static final int AX_LED_LENGTH = 4;
    public static final int AX_TORQUE_LENGTH = 4;
    public static final int AX_POS_LENGTH = 4;
    public static final int AX_GOAL_LENGTH = 5;
    public static final int AX_MT_LENGTH = 5;
    public static final int AX_PUNCH_LENGTH = 5;
    public static final int AX_SPEED_LENGTH = 5;
    public static final int AX_GOAL_SP_LENGTH = 7;
    public static final int AX_BYTE_READ = 1;
    public static final int AX_INT_READ = 2;
    public static final int AX_ACTION_CHECKSUM = 250;
    public static final int AX_BROADCAST_ID = 254;
    public static final int AX_START = 255;
    public static final int AX_CCW_AL_L = 255;
    public static final int AX_CCW_AL_H = 3;
    public static final int AX_LOCK_VALUE = 1;
    public static final int LEFT = 0;
    public static final int RIGTH = 1;
    public static final int RX_TIME_OUT = 10;
    public static final double TX_DELAY_TIME = 0.00002;


    final static GpioController gpio = GpioFactory.getInstance(); 
    public static GpioPinDigitalOutput RPI_DIRECTION_PIN = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08); //PORTAS RELACIONADAS PI4J
    public static GpioPinDigitalOutput RPI_DIRECTION_PIN_RX = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_16); //PORTAS RELACIONADAS PI4J
    static double RPI_DIRECTION_SWITCH_DELAY = 0.0001;
   
    // static variables
    static int port = Serial.serialOpen(Serial.DEFAULT_COM_PORT, 57600);
    
 
    public static void serial(){
    	
        if (port == -1) {
            System.out.println(" ==>> SERIAL SETUP FAILED");
            return;
        }
	    
	    direction(0);
    }
    
    public static void direction(int d){
        if(d==1)
            RPI_DIRECTION_PIN.high();
        else
            RPI_DIRECTION_PIN.low();
    }
    
    public static void move (int id, int position) throws InterruptedException {
    	
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
    
    public static void moveSpeed(int id, int position, int speed){
    	
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
	
    public static void ping (int id) {
    	
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
	
    public static void factoryReset(int id){
			
		String[] options = new String[] {"SIM", "NÃO"};
		
		int response = JOptionPane.showOptionDialog(null, "DESEJA REALIZAR A RESTAURAÇÃO DE FÁBRICA? \n"
					+ "OBS: Essa operação tornará o motor incompatível com AX12-JavA, lembre-se de alterar o baudrate do motor",
					"Preparando para Reset de Fábrica", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
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

    public static void setID (int id, int newId) {
    	
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
        
    public static void setBaudRate (int id, int baudRate) {
    	
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
    
    public static void setStatusReturnLevel (int id, int level) {
    	
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
    
    public static void setReturnDelayTime (int id, int delay) {
    	
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
    
    public static void lockRegister (int id) {
    	
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

    public static void moveRW (int id, int position) {
    	
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
    
    public static void moveSpeedRW (int id, int position, int speed){
    	
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
    
    public static void action () {
    	
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

    public static void setTorqueStatus (int id, int status) {
    	
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

    public static void setLedStatus (int id, int status) {
    	
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
    
    public static void setTemperatureLimit (int id, int temp) {
    	
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
    
    public static void setVoltageLimit (int id, int lowVolt, int highVolt) {
    	
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
    
    public static void setAngleLimit (int id, int cwLimit, int ccwLimit) {
    	
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
    
    public static void setTorqueLimit (int id, int torque) {
    	
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
    
    public static void setPunchLimit (int id, int punch) {
    	
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
    
    public static void setCompliance (int id, int cwMargin, int ccwMargin, int cwSlope, int ccwSlope) {
    	
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
    
    public static void setLedAlarm (int id, int alarm) {
    	
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
     
    public static void setShutdownAlarm (int id, int alarm) {
    	
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
    
	public static void readTemperature (int id) {
    	
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
        
    public static void readPosition (int id) {
    	
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
        
        direction(0);
        byte reply[] = new byte[8];
        reply = Serial.serialGetAvailableBytes(port);
        System.out.println(reply[0]);
        
        gpio.shutdown();
    }        
    
    public static void readVoltage (int id) {
        
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
    
    public static void readSpeed (int id) {
    	
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
    
    public static void readLoad (int id) {
    	
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
    
    public static void readMovingStatus (int id) {
    	
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
    
    public static void readRWStatus (int id) {
    	
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

