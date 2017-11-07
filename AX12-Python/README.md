# Comunicação AX-12A, Python e Raspberry Pi 3B

**Descrição:** Biblioteca Python, para controlar os servos motores AX-12A com a Raspberry Pi 3B.

## 1.Projeto Eletrônico

Para utilizar a biblioteca, deve ser montado o seguinte circuito, adaptado de [1]. Em (a) está ilustrado a Raspberry Pi 3B, em (b) o CI 74LS241, em (c) os servos motores AX-12A e em (d) a bateria LiPo 11.1V, 1000mAh.   

<p align="center">
<img src = "https://user-images.githubusercontent.com/28567780/32135696-1ff67e84-bbe2-11e7-9de0-32faf4b4759b.png" width = "300">
</p>

Ou, pode-se confeccionar o _shield_ Raspi2Dynamixel em:

_Em desenvolvimento_

## 2.Instalação

Alguns recursos são necessários para a integração da Raspiberry com os motores dynamixel, com o Python. A [biblioteca](https://github.com/thiagohersan/memememe/blob/master/Python/ax12/ax12.py) utilizada foi a criada por [thiagohersan](https://github.com/thiagohersan), escrita em Python 2. Segue-se os procedimentos para o uso da biblioteca na Raspiberry Pi 3. 

### 2.2.Preparando a Raspiberry 

*Ao Ligar a RaspiBerry Pi:*

	No terminal: 
		- sudo leafpad /boot/config.txt
	Irá abrir o arquivo, no final dele acrescentar:
		- init_uart_clock = 16000000
		- init_uart_baud=1000000
	Existirá uma parte comentada começando com #dtoverlay... 
	Descomentar e alterar para: 
		- dtoverlay = pi3-disable-bt
	No terminal: 
		- sudo leafpad ~/.bashrc
	No final do arquivo colocar: 
		- sudo chmod 777 /dev/ttyAMA0
	No terminal: 
		- sudo reboot
		
*Abrindo a biblioteca em Python:*

	Abrir uma pasta com os arquivos:
		- ax12.py, __init__.py, Bioloid.py e RUN.py
		- rode o RUN.py no Python 2
		- começe a brincadeira
		
## 3.Programas e Funções da AX12-Python 

Dentro da pasta encontrará os seguintes arquivos:

*AX12.py:* Biblioteca que implementa as funções dos servos.

*Bioloid.py:* Biblioteca para auxiliar nas funções do Bioloid, como zerar motores e colocá-los em posição inicial.

*Run.py:* Permite rodar o programa.

### 3.1.Funções

<p align="center">
AX12.py
</p>

As funções presentes no arquivo Ax12.py foram criadas por [thiagohersan](https://github.com/thiagohersan), e estão explicadas detalhadamente em [AX12-JavA](https://github.com/LAB08-SBC/BioloidCodes/tree/master/AX12-JavA).


<p align="center">
Bioloid.py
</p>


_initialPos():_ Seta os motores em posição inicial.

_clear():_ Seta os motores em 512.

_em desenvolvimento_

## 4.Apoio

<img src="http://www.fc.unesp.br/Home/Cursos/Fisica/fisica-fapesp.png" width="200">
  
<img src = "http://proad.ufabc.edu.br/images/headers/logo_ufabc.png" width="100">

## 5.Referências 

[1] HERSAN, T. **How to Drive Dynamixel AX-12A Servos (with a RaspberryPi)**. Disponível em: <http://www.instructables.com/id/How-to-drive-Dynamixel-AX-12A-servos-with-a-Raspbe/>. Acesso em 3 de Outubro de 2017: Instructables, 2015.

JERONIMO, G. C. **Implementação de Técnica de Processamento de Imagens para a Categoria Kid Size da RoboCup com Validação Real na Plataforma Bioloid ROBOTIS Premium**. FAPESP, UFABC, 2016.

HERSAN, T. **AX-12 Python Library (for RaspberryPi)**. [S.l.]: GitHub, 2014. <https://github.com/thiagohersan/memememe/tree/master/Python/ax12>.

CROSTON, B. __RPi.GPIO module basics__. Disponível em: <https://sourceforge.net/p/raspberry-gpio-python/wiki/BasicUsage/>. Acesso em 13 de Outubro de 2017.

Programiz©. **Python chr()**. Disponível em: <https://www.programiz.com/python-programming/methods/built-in/chr>. Acesso em 13 de Outubro de 2017.

LIECHTI, C. ©. **pySerial API**. Disponível em: <http://pyserial.readthedocs.io/en/latest/pyserial_api.html>. Acesso em 13 de Outubro de 2017.

EICKHOLD, J. **Serial Communication in Java with Raspberry Pi and RXTX**. Disponível em: <https://eclipsesource.com/blogs/2012/10/17/serial-communication-in-java-with-raspberry-pi-and-rxtx/>. Acesso em 13 de Outubro de 2017.

Python. **FAQ: What do the operators <<, >>, &, |, ~, and ^ do?**. Disponível em: <https://wiki.python.org/moin/BitwiseOperators>. Acesso em 13 de Outubro de 2017.

Domoticz. **Domoticz error after RPI update**. Disponível em: <https://www.domoticz.com/forum/viewtopic.php?t=16433#p122279>. Acesso em 13 de Outubro de 2017.

Opeedijk. **Dynamixel AX12 and the Raspberry Pi**. Disponível em: <http://www.oppedijk.com/robotics/control-dynamixel-with-raspberrypi>. Acesso em 13 de Outubro de 2017.

WiringPi. **Serial Library**. Disponível em: <http://wiringpi.com/reference/serial-library/>. Acesso em 13 de Outubro de 2017.

THOMSEN, A. **Como comunicar o Arduino com Raspberry Pi**. Disponível em: <https://www.filipeflop.com/blog/comunicacao-serial-arduino-com-raspberry-pi/>. Acesso em 13 de Outubro de 2017.

Robotttini. **Dynamixel AX-12A and Arduino: how to use the Serial Port**. Disponível em:<http://robottini.altervista.org/dynamixel-ax-12a-and-arduino-how-to-use-the-serial-port>. Acesso em 07/11/2017.

 Savage, J. A. **Arduino y Dynamixel AX-12**. Disponível em <http://savageelectronics.blogspot.com.br/2011/01/arduino-y-dynamixel-ax-12.html>. Acesso em 07/11/2017.

