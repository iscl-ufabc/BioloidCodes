
public class Bioloid {

    static Ax12 motores = new Ax12();
    
    public static void initialPos() {
        motores.serial();
        motores.move(1,336);
        motores.move(2,687);
        motores.move(3,298);
        motores.move(4,724);
        motores.move(5,412);
        motores.move(6,611);
        motores.move(7,355);
        motores.move(8,664);
        motores.move(9,491);
        motores.move(10,530);
        motores.move(11,394);
        motores.move(12,625);
        motores.move(13,278);
        motores.move(14,743);
        motores.move(15,616);
        motores.move(16,405);
        motores.move(17,490);
        motores.move(18,530);
    }
    
    public static void clear() {
        motores.serial();
        motores.move(1,512);
        motores.move(2,512);
        motores.move(3,512);
        motores.move(4,512);
        motores.move(5,512);
        motores.move(6,512);
        motores.move(7,512);
        motores.move(8,512);
        motores.move(9,512);
        motores.move(10,512);
        motores.move(11,512);
        motores.move(12,512);
        motores.move(13,512);
        motores.move(14,512);
        motores.move(15,512);
        motores.move(16,512);
        motores.move(17,512);
        motores.move(18,512);
    }
}
