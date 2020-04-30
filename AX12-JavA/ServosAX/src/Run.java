import java.math.BigDecimal;

public class Run{

    public static void main(String[] args) throws InterruptedException, Exception{
            
        Bioloid jason = new Bioloid(18);
  
        jason.move(2,212);
        jason.move(4,512);
        jason.move(6,312);
        Thread.sleep(100);
        jason.readPosition(2);
        Thread.sleep(2000);
        jason.readPosition(4);
        Thread.sleep(2000);
        jason.readPosition(6);
        Thread.sleep(2000);
        jason.readTemperature(6);
        
    }
}
