
import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.Serial;
import Errors.UnableToConnect;

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
        private final short AX_MODEL_NUMBER_L = 0;
        private final short AX_MODEL_NUMBER_H = 1;
        private final short AX_VERSION = 2;
        private final short AX_ID = 3;
        private final short AX_BAUD_RATE = 4;
        private final short AX_RETURN_DELAY_TIME = 5;
        private final short AX_CW_ANGLE_LIMIT_L = 6;
        private final short AX_CW_ANGLE_LIMIT_H = 7;
        private final short AX_CCW_ANGLE_LIMIT_L = 8;
        private final short AX_CCW_ANGLE_LIMIT_H = 9;
        private final short AX_SYSTEM_DATA2 = 10;
        private final short AX_LIMIT_TEMPERATURE = 11;
        private final short AX_DOWN_LIMIT_VOLTAGE = 12;
        private final short AX_UP_LIMIT_VOLTAGE = 13;
        private final short AX_MAX_TORQUE_L = 14;
        private final short AX_MAX_TORQUE_H = 15;
        private final short AX_RETURN_LEVEL = 16;
        private final short AX_ALARM_LED = 17;
        private final short AX_ALARM_SHUTDOWN = 18;
        private final short AX_OPERATING_MODE = 19;
        private final short AX_DOWN_CALIBRATION_L = 20;
        private final short AX_DOWN_CALIBRATION_H = 21;
        private final short AX_UP_CALIBRATION_L = 22;
        private final short AX_UP_CALIBRATION_H = 23;
        private final short AX_TORQUE_STATUS = 24;
        private final short AX_LED_STATUS = 25;
        private final short AX_CW_COMPLIANCE_MARGIN = 26;
        private final short AX_CCW_COMPLIANCE_MARGIN = 27;
        private final short AX_CW_COMPLIANCE_SLOPE = 28;
        private final short AX_CCW_COMPLIANCE_SLOPE = 29;
        private final short AX_GOAL_POSITION_L = 30;
        private final short AX_GOAL_POSITION_H = 31;
        private final short AX_GOAL_SPEED_L = 32;
        private final short AX_GOAL_SPEED_H = 33;
        private final short AX_TORQUE_LIMIT_L = 34;
        private final short AX_TORQUE_LIMIT_H = 35;
        private final short AX_PRESENT_POSITION_L = 36;
        private final short AX_PRESENT_POSITION_H = 37;
        private final short AX_PRESENT_SPEED_L = 38;
        private final short AX_PRESENT_SPEED_H = 39;
        private final short AX_PRESENT_LOAD_L = 40;
        private final short AX_PRESENT_LOAD_H = 41;
        private final short AX_PRESENT_VOLTAGE = 42;
        private final short AX_PRESENT_TEMPERATURE = 43;
        private final short AX_REGISTERED_INSTRUCTION = 44;
        private final short AX_PAUSE_TIME = 45;
        private final short AX_MOVING = 46;
        private final short AX_LOCK = 47;
        private final short AX_PUNCH_L = 48;
        private final short AX_PUNCH_H = 49;
        private final short AX_RETURN_NONE = 0;
        private final short AX_RETURN_READ = 1;
        private final short AX_RETURN_ALL = 2;
        private final short AX_PING = 1;
        private final short AX_READ_DATA = 2;
        private final short AX_WRITE_DATA = 3;
        private final short AX_REG_WRITE = 4;
        private final short AX_ACTION = 5;
        private final short AX_RESET = 6;
        private final short AX_SYNC_WRITE = 131;
        private final short AX_RESET_LENGTH = 2;
        private final short AX_ACTION_LENGTH = 2;
        private final short AX_ID_LENGTH = 4;
        private final short AX_LR_LENGTH = 4;
        private final short AX_SRL_LENGTH = 4;
        private final short AX_RDT_LENGTH = 4;
        private final short AX_LEDALARM_LENGTH = 4;
        private final short AX_SHUTDOWNALARM_LENGTH = 4;
        private final short AX_TL_LENGTH = 4;
        private final short AX_VL_LENGTH = 6;
        private final short AX_AL_LENGTH = 7;
        private final short AX_CM_LENGTH = 6;
        private final short AX_CS_LENGTH = 5;
        private final short AX_COMPLIANCE_LENGTH = 7;
        private final short AX_CCW_CW_LENGTH = 8;
        private final short AX_BD_LENGTH = 4;
        private final short AX_TEM_LENGTH = 4;
        private final short AX_MOVING_LENGTH = 4;
        private final short AX_RWS_LENGTH = 4;
        private final short AX_VOLT_LENGTH = 4;
        private final short AX_LOAD_LENGTH = 4;
        private final short AX_LED_LENGTH = 4;
        private final short AX_TORQUE_LENGTH = 4;
        private final short AX_POS_LENGTH = 4;
        private final short AX_GOAL_LENGTH = 5;
        private final short AX_MT_LENGTH = 5;
        private final short AX_PUNCH_LENGTH = 5;
        private final short AX_SPEED_LENGTH = 5;
        private final short AX_GOAL_SP_LENGTH = 7;
        private final short AX_BYTE_READ = 1;
        private final short AX_INT_READ = 2;
        private final short AX_ACTION_CHECKSUM = 250;
        private final short AX_BROADCAST_ID = 254;
        private final short AX_START = 255;
        private final short AX_CCW_AL_L = 255;
        private final short AX_CCW_AL_H = 3;
        private final short AX_LOCK_VALUE = 1;
        private final short LEFT = 0;
        private final short RIGTH = 1;
        private final short RX_TIME_OUT = 10;
        private final double TX_DELAY_TIME = 0.00002;
        
        private final boolean TRANSMITTING = true;
        private final boolean RECEIVING = false;

        // static variables
        
        private static GpioController gpio; 
        private static GpioPinDigitalOutput messageDirectionPin; 
        private static int messageDirectionDelayMillis;
        private static int serialPort;
    
        //----------------------------------------- CONSTRUCTORS -----------------------------------------
    
        public Ax12(){
                this(1000000,10);
        }
    
        public Ax12(long baudrate,int delayMillis){
                setGpio();
                setMessageDirectionPin();
                setMessageDirectionDelayMillis(delayMillis);
                
                try{
                        setSerialPort(baudrate);
                } catch (Exception e){
                        getGpio().shutdown();
                        e.printStackTrace();
                }
        }
        
        //----------------------------------------- SETTERS -----------------------------------------
        
        private void setGpio(){
                this.gpio = GpioFactory.getInstance(); 
        }
        
        private void setMessageDirectionPin(){
                this.messageDirectionPin = getGpio().provisionDigitalOutputPin(RaspiPin.GPIO_08); //PORTAS RELACIONADAS PI4J
        }
        
        private void setMessageDirectionDelayMillis(int delayMillis){
                this.messageDirectionDelayMillis = delayMillis;
        }
        
        private void setSerialPort(long baudrate){
                this.serialPort = Serial.serialOpen(Serial.DEFAULT_COM_PORT, (int) baudrate);
                
                if (this.serialPort == -1)
                        throw new UnableToConnect();
                        
                messageDirection(TRANSMITTING);
        }
        
        //----------------------------------------- GETTERS -----------------------------------------
        
        public GpioController getGpio(){
                return this.gpio;
        } 
        
        public GpioPinDigitalOutput getMessageDirectionPin(){
                return this.messageDirectionPin;
        }
        
        public int getMessageDirectionDelayMillis(){
                return this.messageDirectionDelayMillis;
        }
        
        public int getSerialPort(){
                return this.serialPort;
        }
    
        //-------------------------------------------- METHODS --------------------------------------------
    
        private void messageDirection(boolean direction){
                if(direction == TRANSMITTING)
                    getMessageDirectionPin().high();
                else
                    getMessageDirectionPin().low();
        }
        
        public void move (int id, int position) throws InterruptedException{
        
                messageDirection(TRANSMITTING);
                //Serial.serialFlush(serialPort);
                
                int [] p = {position&0xff,position >> 8};
                int checksum = (~(id + AX_GOAL_LENGTH + AX_WRITE_DATA + AX_GOAL_POSITION_L + p[0] + p[1]))&0xff;
                
                Serial.serialPutByte(serialPort, (byte) AX_START);      
                Serial.serialPutByte(serialPort, (byte) AX_START);   
                Serial.serialPutByte(serialPort, (byte) id); 
                Serial.serialPutByte(serialPort, (byte) AX_GOAL_LENGTH);   
                Serial.serialPutByte(serialPort, (byte) AX_WRITE_DATA);   
                Serial.serialPutByte(serialPort, (byte) AX_GOAL_POSITION_L);   
                Serial.serialPutByte(serialPort, (byte) p[0]);   
                Serial.serialPutByte(serialPort, (byte) p[1]);   
                Serial.serialPutByte(serialPort, (byte) checksum);  
                
                Thread.sleep(getMessageDirectionDelayMillis());
                gpio.shutdown();
        }
        
        public void moveSpeed(int id, int position, int speed){
        
                messageDirection(TRANSMITTING);
                Serial.serialFlush(serialPort);
                
                int [] p = {position&0xff,position >> 8};
                int [] s = {speed&0xff, speed>>8};
                int checksum = (~(id + AX_GOAL_LENGTH + AX_WRITE_DATA + AX_GOAL_POSITION_L + p[0] + p[1] + s[0] + s[1]))&0xff;
                
                Serial.serialPutByte(serialPort, (byte) AX_START);   
                Serial.serialPutByte(serialPort, (byte) AX_START);   
                Serial.serialPutByte(serialPort, (byte) id); 
                Serial.serialPutByte(serialPort, (byte) AX_GOAL_LENGTH);   
                Serial.serialPutByte(serialPort, (byte) AX_WRITE_DATA);   
                Serial.serialPutByte(serialPort, (byte) AX_GOAL_POSITION_L);   
                Serial.serialPutByte(serialPort, (byte) p[0]);   
                Serial.serialPutByte(serialPort, (byte) p[1]);   
                Serial.serialPutByte(serialPort, (byte) s[0]);   
                Serial.serialPutByte(serialPort, (byte) s[1]); 
                Serial.serialPutByte(serialPort, (byte) checksum);  
                 
                gpio.shutdown(); 
        }
        
        public void ping (int id) {
        
                messageDirection(TRANSMITTING);
                Serial.serialFlush(serialPort);
                
                int checksum = (~(id + AX_READ_DATA + AX_PING))&0xff;
                
                Serial.serialPutByte(serialPort, (byte) AX_START);      
                Serial.serialPutByte(serialPort, (byte) AX_START);   
                Serial.serialPutByte(serialPort, (byte) id); 
                Serial.serialPutByte(serialPort, (byte) AX_READ_DATA);   
                Serial.serialPutByte(serialPort, (byte) AX_PING);   
                Serial.serialPutByte(serialPort, (byte) checksum);   
                gpio.shutdown();
        }
        
        public void factoryReset(int id){
                        
                String[] options = new String[] {"SIM", "NAO"};
                
                int response = JOptionPane.showOptionDialog(null, "DESEJA REALIZAR A RESTAURACAO DE FABRICA? \n"
                                        + "OBS: Essa operacao tornara o motor incompati­vel com AX12-JavA, lembre-se de alterar o baudrate do motor e da porta serial",
                                        "Preparando para Reset de Fabrica", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                                null, options, options[0]);   
                        
                if (response == 0){    
                        messageDirection(TRANSMITTING);
                        Serial.serialFlush(serialPort);
                        int checksum = (~(id + AX_RESET_LENGTH + AX_RESET))&0xff;
                        
                        Serial.serialPutByte(serialPort, (byte) AX_START);      
                        Serial.serialPutByte(serialPort, (byte) AX_START);   
                        Serial.serialPutByte(serialPort, (byte) id); 
                        Serial.serialPutByte(serialPort, (byte) AX_RESET_LENGTH);   
                        Serial.serialPutByte(serialPort, (byte) AX_RESET);   
                        Serial.serialPutByte(serialPort, (byte) checksum);   
                        
                        gpio.shutdown(); 
                        Serial.serialClose(serialPort);
                        
                        try{
                                setSerialPort(1000000);
                        } catch (Exception e){
                                getGpio().shutdown();
                                e.printStackTrace();
                        }
                }
                else{
                        System.out.println("");
                        return;
                }
        }
        
        public void setID (int id, int newId) {
        
                messageDirection(TRANSMITTING);
                Serial.serialFlush(serialPort);
                
                int checksum = (~(id + AX_ID_LENGTH + AX_WRITE_DATA + AX_ID + newId))&0xff;
                
                Serial.serialPutByte(serialPort, (byte) AX_START);      
                Serial.serialPutByte(serialPort, (byte) AX_START);   
                Serial.serialPutByte(serialPort, (byte) id); 
                Serial.serialPutByte(serialPort, (byte) AX_ID_LENGTH);   
                Serial.serialPutByte(serialPort, (byte) AX_WRITE_DATA);   
                Serial.serialPutByte(serialPort, (byte) AX_ID);   
                Serial.serialPutByte(serialPort, (byte) newId);      
                Serial.serialPutByte(serialPort, (byte) checksum);   
                
                gpio.shutdown();
        }
        
        public void setBaudRate (int id, int baudRate) {
        
                messageDirection(TRANSMITTING);
                Serial.serialFlush(serialPort);
                
                int br = ((2000000/(baudRate))-1);
                int checksum = (~(id + AX_BD_LENGTH + AX_WRITE_DATA + AX_BAUD_RATE + br))&0xff;
                
                Serial.serialPutByte(serialPort, (byte) AX_START);      
                Serial.serialPutByte(serialPort, (byte) AX_START);   
                Serial.serialPutByte(serialPort, (byte) id); 
                Serial.serialPutByte(serialPort, (byte) AX_BD_LENGTH);   
                Serial.serialPutByte(serialPort, (byte) AX_WRITE_DATA);   
                Serial.serialPutByte(serialPort, (byte) AX_BAUD_RATE);   
                Serial.serialPutByte(serialPort, (byte) br);
                Serial.serialPutByte(serialPort, (byte) checksum);   
                
                gpio.shutdown();
                Serial.serialClose(serialPort);
                
                try{
                        setSerialPort(baudRate);
                } catch (Exception e){
                        getGpio().shutdown();
                        e.printStackTrace();
                }
        }
        
        public void setStatusReturnLevel (int id, int level) {
        
                messageDirection(TRANSMITTING);
                Serial.serialFlush(serialPort);
                
                int checksum = (~(id + AX_SRL_LENGTH + AX_WRITE_DATA + AX_RETURN_LEVEL + level))&0xff;
                
                Serial.serialPutByte(serialPort, (byte) AX_START);      
                Serial.serialPutByte(serialPort, (byte) AX_START);   
                Serial.serialPutByte(serialPort, (byte) id); 
                Serial.serialPutByte(serialPort, (byte) AX_GOAL_LENGTH);   
                Serial.serialPutByte(serialPort, (byte) AX_WRITE_DATA);   
                Serial.serialPutByte(serialPort, (byte) AX_GOAL_POSITION_L);   
                Serial.serialPutByte(serialPort, (byte) checksum);   
                
                gpio.shutdown();
        }
        
        public void setReturnDelayTime (int id, int delay) {
                
                messageDirection(TRANSMITTING);
                Serial.serialFlush(serialPort);
                
                int checksum = (~(id + AX_RDT_LENGTH + AX_WRITE_DATA + AX_RETURN_DELAY_TIME + (delay/2)))&0xff;
                
                Serial.serialPutByte(serialPort, (byte) AX_START);      
                Serial.serialPutByte(serialPort, (byte) AX_START);   
                Serial.serialPutByte(serialPort, (byte) id); 
                Serial.serialPutByte(serialPort, (byte) AX_RDT_LENGTH);   
                Serial.serialPutByte(serialPort, (byte) AX_WRITE_DATA);   
                Serial.serialPutByte(serialPort, (byte) AX_RETURN_DELAY_TIME);   
                Serial.serialPutByte(serialPort, (byte) (delay/2));     
                Serial.serialPutByte(serialPort, (byte) checksum);   
                
                gpio.shutdown();
        }
        
        public void lockRegister (int id) {
        
                messageDirection(TRANSMITTING);
                Serial.serialFlush(serialPort);
                
                int checksum = (~(id + AX_LR_LENGTH + AX_WRITE_DATA + AX_LOCK + AX_LOCK_VALUE))&0xff;
                
                Serial.serialPutByte(serialPort, (byte) AX_START);      
                Serial.serialPutByte(serialPort, (byte) AX_START);   
                Serial.serialPutByte(serialPort, (byte) id); 
                Serial.serialPutByte(serialPort, (byte) AX_LR_LENGTH);   
                Serial.serialPutByte(serialPort, (byte) AX_WRITE_DATA);   
                Serial.serialPutByte(serialPort, (byte) AX_LOCK);   
                Serial.serialPutByte(serialPort, (byte) AX_LOCK_VALUE);      
                Serial.serialPutByte(serialPort, (byte) checksum);   
                
                gpio.shutdown();
        }
        
        public void moveRW (int id, int position) {
        
                messageDirection(TRANSMITTING);
                Serial.serialFlush(serialPort);
                
                int [] p = {position&0xff,position >> 8};
                int checksum = (~(id + AX_GOAL_LENGTH + AX_REG_WRITE + AX_GOAL_POSITION_L + p[0] + p[1]))&0xff;
                
                Serial.serialPutByte(serialPort, (byte) AX_START);      
                Serial.serialPutByte(serialPort, (byte) AX_START);   
                Serial.serialPutByte(serialPort, (byte) id); 
                Serial.serialPutByte(serialPort, (byte) AX_GOAL_LENGTH);   
                Serial.serialPutByte(serialPort, (byte) AX_REG_WRITE);   
                Serial.serialPutByte(serialPort, (byte) AX_GOAL_POSITION_L);   
                Serial.serialPutByte(serialPort, (byte) p[0]);   
                Serial.serialPutByte(serialPort, (byte) p[1]);   
                Serial.serialPutByte(serialPort, (byte) checksum);   
                
                gpio.shutdown();
        }
        
        public void moveSpeedRW (int id, int position, int speed){
        
                messageDirection(TRANSMITTING);
                Serial.serialFlush(serialPort);
                
                int [] p = {position&0xff,position >> 8};
                int [] s = {speed&0xff, speed>>8};
                int checksum = (~(id + AX_GOAL_SP_LENGTH + AX_REG_WRITE + AX_GOAL_POSITION_L + p[0] + p[1] + s[0] + s[1]))&0xff;
                
                Serial.serialPutByte(serialPort, (byte) AX_START);      
                Serial.serialPutByte(serialPort, (byte) AX_START);   
                Serial.serialPutByte(serialPort, (byte) id); 
                Serial.serialPutByte(serialPort, (byte) AX_GOAL_SP_LENGTH);   
                Serial.serialPutByte(serialPort, (byte) AX_REG_WRITE);   
                Serial.serialPutByte(serialPort, (byte) AX_GOAL_POSITION_L);   
                Serial.serialPutByte(serialPort, (byte) p[0]);   
                Serial.serialPutByte(serialPort, (byte) p[1]);   
                Serial.serialPutByte(serialPort, (byte) s[0]);   
                Serial.serialPutByte(serialPort, (byte) s[1]); 
                Serial.serialPutByte(serialPort, (byte) checksum);   
                
                gpio.shutdown();
        }
        
        public void action () {
        
                messageDirection(TRANSMITTING);
                Serial.serialFlush(serialPort);
                
                Serial.serialPutByte(serialPort, (byte) AX_START);      
                Serial.serialPutByte(serialPort, (byte) AX_START);   
                Serial.serialPutByte(serialPort, (byte) AX_BROADCAST_ID); 
                Serial.serialPutByte(serialPort, (byte) AX_ACTION_LENGTH);   
                Serial.serialPutByte(serialPort, (byte) AX_ACTION);   
                Serial.serialPutByte(serialPort, (byte) AX_ACTION_CHECKSUM);  
                          
                gpio.shutdown();
        }
        
        public void setTorqueStatus (int id, int status) {
        
                messageDirection(TRANSMITTING);
                Serial.serialFlush(serialPort);
                
                int ts;
                
                if(status==1) 
                        ts=0; 
                else 
                        ts=0;
                        
                int checksum = (~(id + AX_TORQUE_LENGTH + AX_WRITE_DATA + AX_LED_STATUS + ts))&0xff;
                
                Serial.serialPutByte(serialPort, (byte) AX_START);      
                Serial.serialPutByte(serialPort, (byte) AX_START);   
                Serial.serialPutByte(serialPort, (byte) id); 
                Serial.serialPutByte(serialPort, (byte) AX_TORQUE_LENGTH);   
                Serial.serialPutByte(serialPort, (byte) AX_WRITE_DATA);   
                Serial.serialPutByte(serialPort, (byte) AX_LED_STATUS);   
                Serial.serialPutByte(serialPort, (byte) ts);      
                Serial.serialPutByte(serialPort, (byte) checksum);   
                
                gpio.shutdown();
        }
        
        public void setLedStatus (int id, int status) {
        
                messageDirection(TRANSMITTING);
                Serial.serialFlush(serialPort);
                
                int ls;
                
                if(status==1) 
                        ls=0; 
                else 
                        ls=0;
                int checksum = (~(id + AX_LED_LENGTH + AX_WRITE_DATA + AX_LED_STATUS + ls))&0xff;
                
                Serial.serialPutByte(serialPort, (byte) AX_START);      
                Serial.serialPutByte(serialPort, (byte) AX_START);   
                Serial.serialPutByte(serialPort, (byte) id); 
                Serial.serialPutByte(serialPort, (byte) AX_LED_LENGTH);   
                Serial.serialPutByte(serialPort, (byte) AX_WRITE_DATA);   
                Serial.serialPutByte(serialPort, (byte) AX_LED_STATUS);   
                Serial.serialPutByte(serialPort, (byte) ls);      
                Serial.serialPutByte(serialPort, (byte) checksum); 
                  
                gpio.shutdown();
        }
        
        public void setTemperatureLimit (int id, int temp) {
        
                messageDirection(TRANSMITTING);
                Serial.serialFlush(serialPort);
                
                int checksum = (~(id + AX_TL_LENGTH + AX_WRITE_DATA + AX_LIMIT_TEMPERATURE + temp))&0xff;
                
                Serial.serialPutByte(serialPort, (byte) AX_START);      
                Serial.serialPutByte(serialPort, (byte) AX_START);   
                Serial.serialPutByte(serialPort, (byte) id); 
                Serial.serialPutByte(serialPort, (byte) AX_TL_LENGTH);   
                Serial.serialPutByte(serialPort, (byte) AX_WRITE_DATA);   
                Serial.serialPutByte(serialPort, (byte) AX_LIMIT_TEMPERATURE);   
                Serial.serialPutByte(serialPort, (byte) temp );      
                Serial.serialPutByte(serialPort, (byte) checksum);   
                
                gpio.shutdown();
        }        
        
        public void setVoltageLimit (int id, int lowVolt, int highVolt) {
        
                messageDirection(TRANSMITTING);
                Serial.serialFlush(serialPort);
                
                int checksum = (~(id + AX_VL_LENGTH + AX_WRITE_DATA + AX_DOWN_LIMIT_VOLTAGE + lowVolt + highVolt))&0xff;
                
                Serial.serialPutByte(serialPort, (byte) AX_START);      
                Serial.serialPutByte(serialPort, (byte) AX_START);   
                Serial.serialPutByte(serialPort, (byte) id); 
                Serial.serialPutByte(serialPort, (byte) AX_VL_LENGTH);   
                Serial.serialPutByte(serialPort, (byte) AX_WRITE_DATA);   
                Serial.serialPutByte(serialPort, (byte) AX_DOWN_LIMIT_VOLTAGE);   
                Serial.serialPutByte(serialPort, (byte) lowVolt);
                Serial.serialPutByte(serialPort, (byte) highVolt);
                Serial.serialPutByte(serialPort, (byte) checksum); 
                  
                gpio.shutdown();
        }        
        
        public void setAngleLimit (int id, int cwLimit, int ccwLimit) {
        
                messageDirection(TRANSMITTING);
                Serial.serialFlush(serialPort);
                
                int [] cw = {cwLimit&0xff, cwLimit>> 8};
                int [] ccw = {ccwLimit&0xff, ccwLimit>>8};
                int checksum = (~(id + AX_AL_LENGTH + AX_WRITE_DATA + AX_CW_ANGLE_LIMIT_L + cw[0] + cw[1] + ccw[0] + ccw[1]))&0xff;
                
                Serial.serialPutByte(serialPort, (byte) AX_START);      
                Serial.serialPutByte(serialPort, (byte) AX_START);   
                Serial.serialPutByte(serialPort, (byte) id); 
                Serial.serialPutByte(serialPort, (byte) AX_AL_LENGTH);   
                Serial.serialPutByte(serialPort, (byte) AX_WRITE_DATA);   
                Serial.serialPutByte(serialPort, (byte) AX_CW_ANGLE_LIMIT_L);   
                Serial.serialPutByte(serialPort, (byte) cw[0]);
                Serial.serialPutByte(serialPort, (byte) cw[1]);
                Serial.serialPutByte(serialPort, (byte) ccw[0]);
                Serial.serialPutByte(serialPort, (byte) ccw[1]);
                Serial.serialPutByte(serialPort, (byte) checksum);   
                
                gpio.shutdown();
        }        
        
        public void setTorqueLimit (int id, int torque) {
        
                messageDirection(TRANSMITTING);
                Serial.serialFlush(serialPort);
                
                int [] mt = {torque&0xff, torque>> 8};
                int checksum = (~(id + AX_MT_LENGTH + AX_WRITE_DATA + AX_MAX_TORQUE_L + mt[0] + mt[1]))&0xff;
                
                Serial.serialPutByte(serialPort, (byte) AX_START);      
                Serial.serialPutByte(serialPort, (byte) AX_START);   
                Serial.serialPutByte(serialPort, (byte) id); 
                Serial.serialPutByte(serialPort, (byte) AX_MT_LENGTH);   
                Serial.serialPutByte(serialPort, (byte) AX_WRITE_DATA);   
                Serial.serialPutByte(serialPort, (byte) AX_MAX_TORQUE_L);   
                Serial.serialPutByte(serialPort, (byte) mt[0]);
                Serial.serialPutByte(serialPort, (byte) mt[1]);
                Serial.serialPutByte(serialPort, (byte) checksum);   
                
                gpio.shutdown();
        }    
        
        public void setPunchLimit (int id, int punch) {
        
                messageDirection(TRANSMITTING);
                Serial.serialFlush(serialPort);
                
                int [] p = {punch&0xff, punch>> 8};
                int checksum = (~(id + AX_PUNCH_LENGTH + AX_WRITE_DATA + AX_PUNCH_L + p[0] + p[1]))&0xff;
                
                Serial.serialPutByte(serialPort, (byte) AX_START);      
                Serial.serialPutByte(serialPort, (byte) AX_START);   
                Serial.serialPutByte(serialPort, (byte) id); 
                Serial.serialPutByte(serialPort, (byte) AX_PUNCH_LENGTH);   
                Serial.serialPutByte(serialPort, (byte) AX_WRITE_DATA);   
                Serial.serialPutByte(serialPort, (byte) AX_PUNCH_L);   
                Serial.serialPutByte(serialPort, (byte) p[0]);
                Serial.serialPutByte(serialPort, (byte) p[1]);
                Serial.serialPutByte(serialPort, (byte) checksum);   
                
                gpio.shutdown();
        }    
        
        public void setCompliance (int id, int cwMargin, int ccwMargin, int cwSlope, int ccwSlope) {
        
                messageDirection(TRANSMITTING);
                Serial.serialFlush(serialPort);
                
                int checksum = (~(id + AX_COMPLIANCE_LENGTH + AX_WRITE_DATA + AX_CW_COMPLIANCE_MARGIN + cwMargin + ccwMargin + cwSlope + ccwSlope))&0xff;
                
                Serial.serialPutByte(serialPort, (byte) AX_START);      
                Serial.serialPutByte(serialPort, (byte) AX_START);   
                Serial.serialPutByte(serialPort, (byte) id); 
                Serial.serialPutByte(serialPort, (byte) AX_COMPLIANCE_LENGTH);   
                Serial.serialPutByte(serialPort, (byte) AX_WRITE_DATA);   
                Serial.serialPutByte(serialPort, (byte) AX_CW_COMPLIANCE_MARGIN);   
                Serial.serialPutByte(serialPort, (byte) cwMargin);
                Serial.serialPutByte(serialPort, (byte) ccwMargin);
                Serial.serialPutByte(serialPort, (byte) cwSlope);
                Serial.serialPutByte(serialPort, (byte) ccwSlope);
                Serial.serialPutByte(serialPort, (byte) checksum);   
                
                gpio.shutdown();
        }    
        
        public void setLedAlarm (int id, int alarm) {
        
                messageDirection(TRANSMITTING);
                Serial.serialFlush(serialPort);
                
                int checksum = (~(id + AX_LEDALARM_LENGTH + AX_WRITE_DATA + AX_ALARM_LED + alarm))&0xff;
                
                Serial.serialPutByte(serialPort, (byte) AX_START);      
                Serial.serialPutByte(serialPort, (byte) AX_START);   
                Serial.serialPutByte(serialPort, (byte) id); 
                Serial.serialPutByte(serialPort, (byte) AX_LEDALARM_LENGTH);   
                Serial.serialPutByte(serialPort, (byte) AX_WRITE_DATA);   
                Serial.serialPutByte(serialPort, (byte) AX_ALARM_LED);   
                Serial.serialPutByte(serialPort, (byte) alarm);
                Serial.serialPutByte(serialPort, (byte) checksum);   
                
                gpio.shutdown();
        }    
        
        public void setShutdownAlarm (int id, int alarm) {
        
                messageDirection(TRANSMITTING);
                Serial.serialFlush(serialPort);
                
                int checksum = (~(id + AX_SHUTDOWNALARM_LENGTH + AX_WRITE_DATA + AX_ALARM_SHUTDOWN + alarm))&0xff;
                
                Serial.serialPutByte(serialPort, (byte) AX_START);      
                Serial.serialPutByte(serialPort, (byte) AX_START);   
                Serial.serialPutByte(serialPort, (byte) id); 
                Serial.serialPutByte(serialPort, (byte) AX_SHUTDOWNALARM_LENGTH);   
                Serial.serialPutByte(serialPort, (byte) AX_WRITE_DATA);   
                Serial.serialPutByte(serialPort, (byte) AX_ALARM_SHUTDOWN);   
                Serial.serialPutByte(serialPort, (byte) alarm);
                Serial.serialPutByte(serialPort, (byte) checksum);   
                
                gpio.shutdown();
        }    
        
        public void readTemperature (int id) {
        
                messageDirection(TRANSMITTING);
                Serial.serialFlush(serialPort);
                
                int checksum = (~(id + AX_TEM_LENGTH + AX_READ_DATA + AX_PRESENT_TEMPERATURE + AX_BYTE_READ))&0xff;
                
                Serial.serialPutByte(serialPort, (byte) AX_START);      
                Serial.serialPutByte(serialPort, (byte) AX_START);   
                Serial.serialPutByte(serialPort, (byte) id); 
                Serial.serialPutByte(serialPort, (byte) AX_TEM_LENGTH);   
                Serial.serialPutByte(serialPort, (byte) AX_READ_DATA);   
                Serial.serialPutByte(serialPort, (byte) AX_PRESENT_TEMPERATURE);   
                Serial.serialPutByte(serialPort, (byte) AX_BYTE_READ);
                Serial.serialPutByte(serialPort, (byte) checksum);
                   
                gpio.shutdown();
        }        
        
        public void readPosition (int id) throws InterruptedException {
        
                messageDirection(TRANSMITTING);
                Serial.serialFlush(serialPort);
                
                int checksum = (~(id + AX_POS_LENGTH + AX_READ_DATA + AX_PRESENT_POSITION_L + AX_INT_READ))&0xff;
                
                Serial.serialPutByte(serialPort, (byte) AX_START);      
                Serial.serialPutByte(serialPort, (byte) AX_START);   
                Serial.serialPutByte(serialPort, (byte) id); 
                Serial.serialPutByte(serialPort, (byte) AX_POS_LENGTH);   
                Serial.serialPutByte(serialPort, (byte) AX_READ_DATA);   
                Serial.serialPutByte(serialPort, (byte) AX_PRESENT_POSITION_L);   
                Serial.serialPutByte(serialPort, (byte) AX_INT_READ);
                Serial.serialPutByte(serialPort, (byte) checksum);   
                
                //Thread.sleep(RPI_DIRECTION_SWITCH_DELAY);
                messageDirection(RECEIVING);
                Serial.serialFlush(serialPort);
                
                byte reply[] = new byte[12];
                double initialTime = System.currentTimeMillis();
                double currentTime = initialTime;
                
                while((currentTime - initialTime)<5000){
                        System.out.println("Leitura: ");
                        for (int i = 0; Serial.serialDataAvail(serialPort)>0; i++){
                                //reply = Serial.serialGetAvailableBytes(port);
                                reply[i] = Serial.serialGetByte(serialPort);
                                System.out.println(reply[i]);
                        }
                //System.out.println(reply[0]);
                        currentTime = System.currentTimeMillis();
                }
                
                gpio.shutdown();
        }        
        
        public void readVoltage (int id) {
        
        messageDirection(TRANSMITTING);
        Serial.serialFlush(serialPort);
        
        int checksum = (~(id + AX_VOLT_LENGTH + AX_READ_DATA + AX_PRESENT_VOLTAGE + AX_BYTE_READ))&0xff;
        Serial.serialPutByte(serialPort, (byte) AX_START);      
        Serial.serialPutByte(serialPort, (byte) AX_START);   
        Serial.serialPutByte(serialPort, (byte) id); 
        Serial.serialPutByte(serialPort, (byte) AX_VOLT_LENGTH);   
        Serial.serialPutByte(serialPort, (byte) AX_READ_DATA);   
        Serial.serialPutByte(serialPort, (byte) AX_PRESENT_VOLTAGE);   
        Serial.serialPutByte(serialPort, (byte) AX_BYTE_READ);
        Serial.serialPutByte(serialPort, (byte) checksum);   
        gpio.shutdown();
        }
        
        public void readSpeed (int id) {
        
        messageDirection(TRANSMITTING);
        Serial.serialFlush(serialPort);
        
        int checksum = (~(id + AX_SPEED_LENGTH + AX_READ_DATA + AX_PRESENT_SPEED_L + AX_INT_READ))&0xff;
        Serial.serialPutByte(serialPort, (byte) AX_START);      
        Serial.serialPutByte(serialPort, (byte) AX_START);   
        Serial.serialPutByte(serialPort, (byte) id); 
        Serial.serialPutByte(serialPort, (byte) AX_SPEED_LENGTH);   
        Serial.serialPutByte(serialPort, (byte) AX_READ_DATA);   
        Serial.serialPutByte(serialPort, (byte) AX_PRESENT_SPEED_L);   
        Serial.serialPutByte(serialPort, (byte) AX_INT_READ);
        Serial.serialPutByte(serialPort, (byte) checksum);   
        gpio.shutdown();
        }
        
        public void readLoad (int id) {
        
        messageDirection(TRANSMITTING);
        Serial.serialFlush(serialPort);
        
        int checksum = (~(id + AX_LOAD_LENGTH + AX_READ_DATA + AX_PRESENT_LOAD_L + AX_INT_READ))&0xff;
        Serial.serialPutByte(serialPort, (byte) AX_START);      
        Serial.serialPutByte(serialPort, (byte) AX_START);   
        Serial.serialPutByte(serialPort, (byte) id); 
        Serial.serialPutByte(serialPort, (byte) AX_LOAD_LENGTH);   
        Serial.serialPutByte(serialPort, (byte) AX_READ_DATA);   
        Serial.serialPutByte(serialPort, (byte) AX_PRESENT_LOAD_L);   
        Serial.serialPutByte(serialPort, (byte) AX_INT_READ);
        Serial.serialPutByte(serialPort, (byte) checksum);   
        gpio.shutdown();
        }
        
        public void readMovingStatus (int id) {
        
        messageDirection(TRANSMITTING);
        Serial.serialFlush(serialPort);
        
        int checksum = (~(id + AX_MOVING_LENGTH + AX_READ_DATA + AX_MOVING + AX_BYTE_READ))&0xff;
        Serial.serialPutByte(serialPort, (byte) AX_START);      
        Serial.serialPutByte(serialPort, (byte) AX_START);   
        Serial.serialPutByte(serialPort, (byte) id); 
        Serial.serialPutByte(serialPort, (byte) AX_MOVING_LENGTH);   
        Serial.serialPutByte(serialPort, (byte) AX_READ_DATA);   
        Serial.serialPutByte(serialPort, (byte) AX_MOVING);   
        Serial.serialPutByte(serialPort, (byte) AX_BYTE_READ);
        Serial.serialPutByte(serialPort, (byte) checksum);   
        gpio.shutdown();
        }
        
        public void readRWStatus (int id) {
        
        messageDirection(TRANSMITTING);
        Serial.serialFlush(serialPort);
        
        int checksum = (~(id + AX_RWS_LENGTH + AX_READ_DATA + AX_REGISTERED_INSTRUCTION + AX_BYTE_READ))&0xff;
        Serial.serialPutByte(serialPort, (byte) AX_START);      
        Serial.serialPutByte(serialPort, (byte) AX_START);   
        Serial.serialPutByte(serialPort, (byte) id); 
        Serial.serialPutByte(serialPort, (byte) AX_RWS_LENGTH);   
        Serial.serialPutByte(serialPort, (byte) AX_READ_DATA);   
        Serial.serialPutByte(serialPort, (byte) AX_REGISTERED_INSTRUCTION);   
        Serial.serialPutByte(serialPort, (byte) AX_BYTE_READ);
        Serial.serialPutByte(serialPort, (byte) checksum);   
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

