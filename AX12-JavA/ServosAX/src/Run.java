import java.math.BigDecimal;

public class Run{

    public static void main(String[] args) throws InterruptedException{
            
        //Ax12 motor = new Ax12();
        
        //motor.readPosition(2);
        
        
    	Ax12 motor = new Ax12();
        
        motor.move(2,712);
        Thread.sleep(1000);
        motor.move(2,512);
        Thread.sleep(1000);
        motor.move(2,312);
        Thread.sleep(1000);
        
        
    }
}
