LIGANDO OS MOTORES NA RASPBERRY PI 3:
- montar o circuito:
	
 ![circuito](https://user-images.githubusercontent.com/28567780/32135696-1ff67e84-bbe2-11e7-9de0-32faf4b4759b.png)
 
- ao ligar a raspberry:
1.	No terminal: sudo leafpad /boot/config.txt
2.	Irá abrir o arquivo, no final dele acrescentar:
a.	init_uart_clock = 16000000
b.	init_uart_baud=1000000
c.	existirá uma parte comentada começando com #dtoverlay... 
descomentar e alterar para: dtoverlay = pi3-disable-bt
3.	No terminal: sudo leafpad ~/.bashrc
4.	No final do arquivo colocar: sudo chmod 777 /dev/ttyAMA0
5.	No terminal: sudo reboot
- abrir uma pasta com os arquivos:
1.	ax12.py, __init__.py, Bioloid.py e RUN.py
2.	rode o RUN.py no Python 2

links:
http://robottini.altervista.org/dynamixel-ax-12a-and-arduino-how-to-use-the-serial-port
http://savageelectronics.blogspot.com.br/2011/01/arduino-y-dynamixel-ax-12.html
http://www.instructables.com/id/How-to-drive-Dynamixel-AX-12A-servos-with-a-Raspbe/
http://www.oppedijk.com/robotics/control-dynamixel-with-raspberrypi
https://github.com/thiagohersan/memememe


