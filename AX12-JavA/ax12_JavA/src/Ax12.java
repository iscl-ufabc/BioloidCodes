
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
    static double RPI_DIRECTION_SWITCH_DELAY = 0.0001;

    // static variables
    static int port = Serial.serialOpen(Serial.DEFAULT_COM_PORT, 57600);
    
    public void serial(){
    	
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
    
    public void move (int id, int position) {
    	
        direction(1);

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
        gpio.shutdown();
    }
    
    public static void moveSpeed(int id, int position, int speed){
    	
        direction(1);
        
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
	
    public static void ping(int id){
    	direction(1)
   
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
    		direction(1)
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
		return
	}
    }	
}

