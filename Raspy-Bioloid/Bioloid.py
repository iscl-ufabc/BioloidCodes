from ax12 import Ax12
import sys
import time
 
motors = Ax12()
 
class BIOLOID:
 
    def initialPos(self):
        motors.move(1,336)
        motors.move(2,687)
        motors.move(3,298)
        motors.move(4,724)
        motors.move(5,412)
        motors.move(6,611)
        motors.move(7,355)
        motors.move(8,664)
        motors.move(9,491)
        motors.move(10,530)
        motors.move(11,394)
        motors.move(12,625)
        motors.move(13,278)
        motors.move(14,743)
        motors.move(15,616)
        motors.move(16,405)
        motors.move(17,490)
        motors.move(18,530)
 
    def clear(self):
        motors.move(1,512)
        motors.move(2,512)
        motors.move(3,512)
        motors.move(4,512)
        motors.move(5,512)
        motors.move(6,512)
        motors.move(7,361)
        motors.move(8,663)
        motors.move(9,512)
        motors.move(10,512)
        motors.move(11,512)
        motors.move(12,512)
        motors.move(13,512)
        motors.move(14,512)
        motors.move(15,512)
        motors.move(16,512)
        motors.move(17,512)
        motors.move(18,512)
