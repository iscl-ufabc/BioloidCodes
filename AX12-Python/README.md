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

Alguns recursos são necessários para a integração da Raspiberry com os motores dynamixel, com o Python. A [biblioteca](https://github.com/thiagohersan/memememe/blob/master/Python/ax12/ax12.py) utilizada foi a criada por [thiagohersan](https://github.com/thiagohersan), escrita em Python 2 e melhorada durante o desenvolvimento de projeto de pesquisa. Segue-se os procedimentos para o uso da biblioteca na Raspiberry Pi 3. 

### 2.2.Preparando a Raspiberry 

*Ao Ligar a RaspiBerry Pi:*

	1) No terminal digitar: 
		sudo leafpad /boot/config.txt
	
	2) Irá abrir o arquivo, no final dele acrescentar:
		enable_uart=1
		init_uart_clock=16000000
		init_uart_baud=1000000
		sudo stty -F /dev/ttyAMA0 1000000
		
	3) Existirá uma parte comentada começando com #dtoverlay... Descomentar (tirar o #) e alterar para: 
		dtoverlay=pi3-disable-bt
	
	4) No terminal digitar: 
		sudo leafpad ~/.bashrc
		
	5) No final do arquivo colocar: 
		sudo chmod -R 777 /dev/ttyAMA0
		sudo chmod -R 777 /root
		sudo chmod -R 777 '/dev/ttyAMA0'
		sudo chmod -R 777 '/root'
	
	6) No terminal digitar:
		sudo leafpad /boot/cmdline.txt
	
	7) Ao abrir o arquivo remova todas opções citando ttyAMA0.
	
	8) No terminal: 
		sudo reboot
		
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

_clear():_ Seta os motores em 512, zerando eles.

_readMotors():_ Lê os todos os motores.


**Possíveis Problemas**
	
_1) Código não roda_
	
	O arquivo em Python roda a qualquer baudrate, além disso, outras portas seriais e GPIO podem ser usadas, basta realizar a configuração acima para setar outras portas. No arquivo ax12.py existe uma linha escrita:
	
		- Ax12.port = Serial("/dev/ttyAMA0", baudrate=57600, timeout=0.001) 
	
	Se for a primeira vez que estiver usando o programa com os servos motores AX-12A, altere o baudrate para 1000000, configuração inicial do AX-12A. 
	
	Se deseja testar a comunicação Serial da sua Raspberry e verificar se ela está funcionando use um Arduino e monte o seguinte circuito. Use o código Arduino em [Arduino] . Acesse o terminal Serial do Arduino e troque o baudrate para o mesmo da Raspberry. 
	
	Após a linha Ax12.port = Serial("/dev/ttyAMA0", baudrate=57600, timeout=0.001) coloque o seguinte
	
		- Ax12.port.write('A');
	
	Rode o programa em arduino, em seguida o python da Raspberry. Verifique se o caracter é transmitido para o terminal Serial do Arduino, se sim, a comunicação Serial está funcionando (DICA: teste com diferentes baudrates).
	
_2) A comunicação serial funciona, mas o motor não mexe_
	
	Verifique se o baudrate dos motores AX-12A são os mesmos que está utilizando no código python.
	
	Utilize o RoboPlus, juntamente com o DynamixelWizard. Utilize os padrões de fábrica, com baudrate = 1000000 e verifique se o ID do motor está correto.
	
	Realize o passo 1 novamente
	
_3) A leitura dos motores não funciona_
	
	Se por acaso a leitura dos dados dos motores não funcionar, entre em contato com nossa equipe.
	
	Para voltar a utilizar as ações dos motores, digite no terminal: 
		sudo chmod -R 777 /dev/ttyAMA0
		sudo chmod -R 777 /root
		sudo chmod -R 777 '/dev/ttyAMA0'
		sudo chmod -R 777 '/root'
		
	Dê reboot se o problema persistir.	

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

THOMSEN, A. **Como comunicar o Arduino com Raspberry Pi**. Disponível em: <https://www.filipeflop.com/blog/comunicacao-serial-arduino-com-raspberry-pi/>. Acesso em 13 de Outubro de 2017.

Robotttini. **Dynamixel AX-12A and Arduino: how to use the Serial Port**. Disponível em:<http://robottini.altervista.org/dynamixel-ax-12a-and-arduino-how-to-use-the-serial-port>. Acesso em 07/11/2017.

 Savage, J. A. **Arduino y Dynamixel AX-12**. Disponível em <http://savageelectronics.blogspot.com.br/2011/01/arduino-y-dynamixel-ax-12.html>. Acesso em 07/11/2017.

