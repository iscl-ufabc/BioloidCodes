
public class Run
{
	
    public static void main(String[] args) {
        Ax12 motores = new Ax12();
        
        motores.serial();
        motores.move(4, 512);
        

    }
}
