# Comunicação AX-12A, Java e Raspberry Pi 3B

**Descrição:** Biblioteca Java, desenvolvida com o Pi4J [1], para controlar os servos motores AX-12A com a Raspberry Pi 3B.

## 1.Projeto Eletrônico

É possível adquirir o _shield_ Rasp2Dynamixel V2 através do e-mail gilmarjeronimo@uol.com.br: 

<p align="center">
<img src2 = "https://user-images.githubusercontent.com/28567780/32135696-1ff67e84-bbe2-11e7-9de0-32faf4b4759b.png" width = "300">
</p>

Ou, pode ser montado o seguinte circuito, adaptado de [2]. Em (a) está ilustrado a Raspberry Pi 3B, em (b) o CI 74LS241, em (c) os servos motores AX-12A e em (d) a bateria LiPo 11.1V, 1000mAh.   

<p align="center">
<img src = "https://user-images.githubusercontent.com/28567780/32135696-1ff67e84-bbe2-11e7-9de0-32faf4b4759b.png" width = "300">
</p>

## 2.Instalação

Alguns pacotes são necessários para o uso da biblioteca, como o Pi4J. É opcional o uso das IDEs, como Eclipse [3], Netbeans [4] e BlueJ [5], mas é altamente recomendados para correção dos códigos. A última atualização do sistema opercional apresentou alguns problemas para instalação e preparação das IDEs, assim será sugerido a utilização do Geany compilando o código pelo terminal do comandos. Segue-se os procedimentos para instalação da biblioteca Pi4J na Raspberry Pi. 

### 2.1.Preparando a Raspberry com Noobs Versão 3.3.1 (2020-02-14)

*Ao Ligar a Raspberry Pi:*

1. No terminal digitar: 
	1. sudo apt-get update
	2. sudo apt-get upgrade
	3. sudo apt-get install leafpad
	4. sudo leafpad /boot/config.txt
	
2. Irá abrir o arquivo, no final dele acrescentar:
	1. enable_uart=1
	2. dtparam=uart0=on
	3. dtoverlay=pi3-miniuart-bt
	
3. No terminal digitar: 
	1. sudo leafpad ~/.bashrc
	
4. No final do arquivo colocar: 
	1. sudo chmod -R 777 /dev/ttyAMA0
	2. sudo chmod -R 777 /root
	3. sudo chmod -R 777 '/dev/ttyAMA0'
	4. sudo chmod -R 777 '/root'
	
5. Remover o conteúdo do arquivo cmdline.txt
	1. sudo leafpad /boot/cmdline.txt
	2. remover "console=serial0, 115200"

6. No terminal:
	1. sudo raspi-config
	2. Selecionar "Interfacing Options -> Serial"
	3. Colocar "Não" e "Sim", respectivamente

7. Irá pedir para fazer reboot, colocaar "Sim"
		
8. Se não pedir, no terminal digitar: 
	1. sudo reboot

**obs:** Qualquer problema com a comunicação serial, visualizar o vídeo de [6].

### 2.2.PI4J 

*Preparando o Java:*

1. Instalar o Java e Verificar sua versão:
	1. sudo apt update
	2. sudo apt install default-jdk
	3. java -version

A saída será algo do tipo

	openjdk version "11.0.6" 2020-01-14
	OpenJDK Runtime Environment (build 11.0.6+10-post-Raspbian-1deb10u1)
	OpenJDK Server VM (build 11.0.6+10-post-Raspbian-1deb10u1, mixed mode)

Mais dúvidas podem ser tiradas em [7].

*Preparando o WiringPi*

Normalmente a biblioteca WiringPi já vem instalada nas novas versões, portanto os passos a seguir são garantias.

1. No terminal:
	1. sudo apt-get install wiringpi

Para testar, digite no terminal
	1. gpio readall 

Isso irá mostrar todas as portas disponíveis da RPi3 e como estão configuradas para serem utilizadas.

Mais dúvidas, consultar [8].

*Preparando o Pi4J:*

1. No terminal: 
	1. curl -sSL https://pi4j.com/install | sudo bash
	2. sudo apt-get install pi4j

Após isso, os arquivos do pi4j, incluindo os .jar estarão na pasta: /opt/pi4j 

## 2.Programas e Funções da AX12-JavA 

Dentro da pasta ServosAX/src, encontrará os seguintes arquivos:

<p align="center">
*Classe* | *Função*
------------- | -------------
*AX12.java:* | Classe que implementa as funções dos servos classe AX da Dynamixel.
------------- | -------------
*Bioloid.java:* | Classe para auxiliar nas funções do Bioloid, como zerar motores e colocá-los em posição inicial.
------------- | -------------
*panTilt.java:* | Implementa as funções para rodar com os servos SG90.
------------- | -------------
*Run.java:* | Permite rodar o main do programa.
</p>

### 2.1.Funções

<p align="center">
AX12.java
</p>

<p align="center">
*Método* | *Função*
------------- | -------------
_serial():_ | Inicializa a comunicação serial dos motores, sempre começar com essa função.
_direction(int):_ | Configura o pino GPIO 8 para mudar de estado, HIGH se int = 1 ou LOW se int = 0.  
_move(id, pos):_ | Movimenta o servo de um certo ID para uma posição entre 0 (0°) e 1024 (300°).
_moveSpeed(id, pos, speed):_ | Movimenta o servo de um certo ID para uma posição entre 0 (0°) e 1024 (300°) com velocidade entre 0 e 1024.
_ping(id):_ | Retorna qual é o _ping_ do motor indicado pelo ID.
_factoryReset(id):_ | Realiza o Reset de Fábrica no motor com ID indicado. Tal configuração poderá conexão com o PI4J, já que o baudrate de fábrica é 1000000, não suportado pela biblioteca.
_setID(id, newID):_ | Muda o ID do motor indicado (id) para um novo (newID) de 0-252.
_setBaudRate(id,baudrate):_ | Muda o Baud Rate do motor escolhido de 2000000-8000 bps.
_setStatusReturnLevel(id,level):_ | Decide como retornar um Pacote de Status, se level = 0 não será retornado nenhuma leitura exceto ping, se level = 1 retorna uma mensagem somente para o comando read, e se level = 2 retorna uma mensagem para todos comandos enviados. 
_setReturnDelayTime(id,delay):_ | É o tempo de delay entre a mensagem transmitida do pacote de Instrução e recebida no pacote de Status. Varia de 0 a 254, sendo 1 delay de 2 microsegundos, 2 um delay de 4 microsegundos e 250 um delay de 0,5 milisegundos. 
_lockRegister(id):_ | Tranca área de EEPROM do servo, não podendo ser modificada. Nela impede de se alterar ID, Baud Rate, Torque e outros. 
_moveRW(id, position):_ | Seta o servo para locomoção em rotação contínua.
_moveSpeedRW(id,position,speed):_ | Seta a velocidade do servo de rotação contínua.
_action():_ | Verifica se existe algum comando transmitido para o REG_WRITE
_setTorqueStatus(id,status):_ | Define se o torque do motor está ligado ou desligado. Se status = 0 mantém o torque, se status = 1 gera torque.
_setLedStatus(id,status):_ | Define o estado do LED do motor. Se status = 0 o LED desliga, se status = 1 o LED liga.
_setTemperatureLimit(id, temp):_ | Seta a temperatura limite do servo, indo de 0 a 99°C.
_setVoltageLimit(id,lowVolt,highVolt):_ | Seta o limite de voltagem do servo, indo de 50 a 250 para lowVolt e highVolt. Se o valor é 50, temos 5V.
_setAngleLimit(id,cwLimit,ccwLimit):_ | Define o ângulo limite no sentido clockwise (horário) e no counterclockwise (anti-horário). Indo de 0 a 1023.
_setTorqueLimit(id,torque):_ | Define o Torque limite, indo de 0 a 1023.
_setPunchLimit(id,punch):_ | Define a corrente para acionar o motor, indo de 0 a 1023.
_setCompliance(id, cwMargin, ccwMargin, cwSlope, ccwSlope):_ | Define a flexibilidade de controle do motor. cwMargin e ccwMargin vão de 1 a 254 e representam o erro entre a posição desejada e a posição atual. Enquanto que cwSlope e ccwSlope são valores fixo de [2,4,8,16,32,64,128], que definem o nível do Torque próximo a posição desejada.
_setLedAlarm(id,alarm):_ | Define o alarme do LED. Seu valor é pode ser [1,2,4,8,16,32,64]. O valor 1 representa que há um erro na voltagem de entrada, 2 um erro no ângulo limite, 4 problema de superaquecimento, 8 problema de range, 16 problema de checksum, 32 problema de Overload e 64 problema de instrução. 
_setShutdownAlarm(id,alarm):_ | Define o alarme. Seu valor é pode ser [1,2,4,8,16,32,64]. O valor 1 representa que há um erro na voltagem de entrada, 2 um erro no ângulo limite, 4 problema de superaquecimento, 8 problema de range, 16 problema de checksum, 32 problema de Overload e 64 problema de instrução. 
_readTemperature(id):_ | Lê a temperatura do servo. (Não FINALIZADO).
_readPosition(id):_ | Lê a Posição Atual do servo.(Não FINALIZADO).
_readVoltage(id):_ | Lê a Voltagem do servo. (Não FINALIZADO).
_readSpeed(id):_ | Lê a velocidade do servo. (Não FINALIZADO).
_readLoad(id):_ | Lê a carga do servo. (Não FINALIZADO).
_readMovingStatus(id):_ | Lê se o servo está se movimentando ou não. (Não FINALIZADO).
_readRWStatus(id):_ | Lê se o servo está em rotação contínua. (Não FINALIZADO).
</p>

Mais informações em [9].


<p align="center">
Bioloid.java
</p>


_initialPos():_ Seta os motores em posição inicial.

_clear():_ Seta os motores em 512.


### 2.2.Como rodar

1. Entrar pelo terminal na pasta ServosAX
	1. sudo javac -d bin/ -cp "lib/pi4j/*:.jar" src/*.java
	2. sudo java -cp "lib/pi4j/*:.jar:bin" Run

**Possíveis Problemas**
	
_1) Código não roda_
	
	O arquivo em Java roda em um Baud Rate específico, além disso, outras portas seriais e GPIO podem ser usadas, basta realizar a configuração acima para setar outras portas. No arquivo Ax12.java existe uma linha escrita:
	
		- public static GpioPinDigitalOutput RPI_DIRECTION_PIN = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08); //PORTAS RELACIONADAS PI4J
		- static int port = Serial.serialOpen(Serial.DEFAULT_COM_PORT, 57600);
	
	Se for a primeira vez que estiver usando o programa com os servos motores AX-12A, altere o Baud Rate para 57600 utilizando o código em Python disponível em https://github.com/LAB08-SBC/BioloidCodes/tree/master/AX12-Python
	
	Se deseja testar a comunicação Serial da sua Raspberry e verificar se ela está funcionando use um Arduino e monte o seguinte circuito. Use o código Arduino em https://github.com/LAB08-SBC/BioloidCodes/blob/master/SerialArduinoRasp.ino. Acesse o terminal Serial do Arduino e troque o Baud Rate para o mesmo da Raspberry. 
	
	Rode o programa em arduino, em seguida o python da Raspberry. Verifique se os caractéres são transmitidos para o terminal Serial do Arduino, se sim, a comunicação Serial está funcionando (DICA: teste com diferentes Baud Rates).
	
_2) A comunicação serial funciona, mas o motor não mexe_
	
	Verifique se o baudrate dos motores AX-12A são os mesmos que está utilizando no código java.
	
	Utilize o RoboPlus, juntamente com o DynamixelWizard. Utilize os padrões de fábrica, com baudrate = 1000000 e verifique se o ID do motor está correto.
	
	Realize o passo 1 novamente	

## 4.Apoio

<img src="http://www.fc.unesp.br/Home/Cursos/Fisica/fisica-fapesp.png" width="200">
  
<img src = "http://proad.ufabc.edu.br/images/headers/logo_ufabc.png" width="100">

## 5.Referências 

[1] PI4J©. **The Pi4J Project: Java I/O Library for the Raspberry Pi**. Disponível em: <http://pi4j.com/download.html>. Acesso em 4 de Outubro de 2017: Pi4J©, 2016.

[2] HERSAN, T. **How to Drive Dynamixel AX-12A Servos (with a RaspberryPi)**. Disponível em: <http://www.instructables.com/id/How-to-drive-Dynamixel-AX-12A-servos-with-a-Raspbe/>. Acesso em 3 de Outubro de 2017: Instructables, 2015.

[3] ECLIPSE, I.; IDE, E. __Documentação do. Eclipse©__. Disponível em:<http://www.eclipse.org>. Acesso em 4 de Outubro de 2017, v. 12, 2006.

[4] NETBEANS, I. __Netbeans©__. Disponível em:<http://netbeans.org/>. Acesso em 4 de Outubro de 2017, v. 11, 2008.

[5] KÖLLING, M. et al. __The bluej system and its pedagogy__. Computer Science Education, Taylor & Francis, v. 13, n. 4, p. 249–268, 2003.

[6] AL, S. "Raspberry PI3 PI4 Solution to UART PermissionDenied". Disponível em <https://www.youtube.com/watch?v=StFZj7gSwNs>. Acesso em 06 de Abril de 2020. YouTube, 2020.

[7] LINUXIZE. **How to Install Java on Raspberry Pi**. Disponível em: <https://linuxize.com/post/install-java-on-raspberry-pi/>. Acesso em 06 de Abril de 2020: Linuxize, 2020.

[8] WIRINGPI. **Download and Install**. Disponível em: <http://wiringpi.com/download-and-install/>. Acesso em 06 de Abril de 2020. WiringPi, 2020.

[9] ROBOTIS.__AX-12/ AX-12+/ AX-12A__. Disponível em: <http://support.robotis.com/en/product/actuator/dynamixel/ax_series/dxl_ax_actuator.htm#Actuator_Address_2F>. Acesso em 26 de Maio de 2018.

JERONIMO, G. C. **Implementação de Técnica de Processamento de Imagens para a Categoria Kid Size da RoboCup com Validação Real na Plataforma Bioloid ROBOTIS Premium**. FAPESP, UFABC, 2016.

HERSAN, T. **AX-12 Python Library (for RaspberryPi)**. [S.l.]: GitHub, 2014. <https://github.com/thiagohersan/memememe/tree/master/Python/ax12>.

PI4J©. **Pi4J :: Parent POM 1.1 API**. Disponível em: <http://pi4j.com/apidocs/>. Acesso em 13 de Outubro de 2017: Pi4J©, 2016.

CROSTON, B. __RPi.GPIO module basics__. Disponível em: <https://sourceforge.net/p/raspberry-gpio-python/wiki/BasicUsage/>. Acesso em 13 de Outubro de 2017.

PI4J©. **Simple GPIO Control using Pi4J**. Disponível em: <http://pi4j.com/example/control.html>. Acesso em 13 de Outubro de 2017: Pi4J©, 2016.

Programiz©. **Python chr()**. Disponível em: <https://www.programiz.com/python-programming/methods/built-in/chr>. Acesso em 13 de Outubro de 2017.

LIECHTI, C. ©. **pySerial API**. Disponível em: <http://pyserial.readthedocs.io/en/latest/pyserial_api.html>. Acesso em 13 de Outubro de 2017.

EICKHOLD, J. **Serial Communication in Java with Raspberry Pi and RXTX**. Disponível em: <https://eclipsesource.com/blogs/2012/10/17/serial-communication-in-java-with-raspberry-pi-and-rxtx/>. Acesso em 13 de Outubro de 2017.

Python. **FAQ: What do the operators <<, >>, &, |, ~, and ^ do?**. Disponível em: <https://wiki.python.org/moin/BitwiseOperators>. Acesso em 13 de Outubro de 2017.

TutorialsPoint. **Java - Bitwise Operators Example**. Disponível em: <https://www.tutorialspoint.com/java/java_bitwise_operators_examples.htm>. Acesso em 13 de Outubro de 2017.

Domoticz. **Domoticz error after RPI update**. Disponível em: <https://www.domoticz.com/forum/viewtopic.php?t=16433#p122279>. Acesso em 13 de Outubro de 2017.

Opeedijk. **Dynamixel AX12 and the Raspberry Pi**. Disponível em: <http://www.oppedijk.com/robotics/control-dynamixel-with-raspberrypi>. Acesso em 13 de Outubro de 2017.

WiringPi. **Serial Library**. Disponível em: <http://wiringpi.com/reference/serial-library/>. Acesso em 13 de Outubro de 2017.

THOMSEN, A. **Como comunicar o Arduino com Raspberry Pi**. Disponível em: <https://www.filipeflop.com/blog/comunicacao-serial-arduino-com-raspberry-pi/>. Acesso em 13 de Outubro de 2017.

Java-Buddy. **Install NetBeans on Raspberry Pi/Raspbian for Java and C/C++ development**. Disponível em: <http://java-buddy.blogspot.com.br/2015/03/install-netbeans-on-raspberry.html>. Acesso em 13 de Outubro de 2017.  
