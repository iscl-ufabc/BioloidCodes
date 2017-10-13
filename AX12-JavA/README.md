# Comunicação AX-12A, Java e Raspberry Pi 3B

**Descrição:** Biblioteca Java, desenvolvida com o Pi4J [1], para controlar os servos motores AX-12A com a Raspberry Pi 3B.

## Projeto Eletrônico

Para utilizar a biblioteca, deve ser montado o seguinte circuito, adaptado de [2]. Em (a) está ilustrado a Raspberry Pi 3B, em (b) o CI 74LS241, em (c) os servos motores AX-12A e em (d) a bateria LiPo 11.1V, 1000mAh.   

<p align="center">
<img src = "https://user-images.githubusercontent.com/28567780/31362861-22917b22-ad31-11e7-919d-b8ea135fb5ae.png" width = "300">
</p>

Ou, pode-se confeccionar o _shield_ Raspi2Dynamixel em:

_Em desenvolvimento_

## Instalação

Alguns pacotes são necessários para o uso da biblioteca, como o Pi4J. É opcional o uso das IDEs, como Eclipse [3], Netbeans [4] e BlueJ [5], mas é altamente recomendados para correção dos códigos. Segue-se os procedimentos para instalação da biblioteca e IDEs na Raspberry Pi. 

### PI4J 

*Para instalar o PI4J:*

	No terminal: 
		- curl -s get.pi4j.com | sudo bash

### ECLIPSE 

*Para instalar o ECLIPSE:*

	No terminal: 
		- sudo apt-get install eclipse

*Para colocar as bibliotecas no Eclipse:*

	- Abra o eclipse
	- em window -> preferences
	- em java -> build path -> user libraries
	- New...
	- User Library name: Pi4j (OK)
	- Add External JARs...
	- Procure por /opt/pi4j/lib/pi4j-core.jar 
	- Selecione também: pi4j-device.jar/ pi4j-gpio-extension.jar/ pi4j-service.jar
	- OK!

*Crie um novo projeto Java:*

	- Coloque o nome e clique em next>
	- em Libraries clique em Add Library...
	- User Library
	- Selecione Pi4j
	- Finish
	- Crie a Classe e começe a brincadeira

### NETBEANS

*Para instalar o NETBEANS:*

	- No site do netbeans: https://netbeans.org/downloads/ , baixe a versão Java SE na plataforma
	SO Independent ZIP.
	- Ao baixar, vá em download e descompact.
	- Abra o arquivo contido em: /netbeans/bin/netbeans
	- Atualize os pacotes, e reinicie o netbeans.

*Para colocar as bibliotecas no NETBEANS:*

	- Abra o NETBEANS.
	- Em ferramentas, clique em bibliotecas.
	- Clique em Nova Biblioteca...
	- Coloque como nome: Pi4j , dê (OK)
	- Adicionar Jar/Pasta...
	- Procure por /opt/pi4j/lib/pi4j-core.jar 
	- Selecione também: pi4j-device.jar/ pi4j-gpio-extension.jar/ pi4j-service.jar
	- OK!

*Crie um novo projeto Java:*

	- Clique em Aplicação Java, Próximo >
	- Escolha o Nome de seu projeto e clique em Finalizar
	- Ao criar o arquivo, clique com o botão direito do mouse em cima do projeto criado
	- Vá em propriedades --> bibliotecas --> Adicionar Bibliotecas --> Pi4j
	- Adicione ela, e dê Ok!

### BlueJ 

*Crie um novo projeto Java:*

	- Ao abrir o BlueJ, clique em New Project.
	- Crie uma pasta em /home/pi e nomeie como BlueJ Projects, dentro dessa pasta crie outra com o nome do seu Projeto, clique em create.
	- Ao Abrir vá em Tools --> Preferences --> Library, clique em add.
	- Procure as pastas: /opt/pi4j/lib/pi4j-core.jar (e também pi4j-device.jar/ pi4j-gpio-extension.jar/ pi4j-service.jar)
	- Dê ok e reinicie o Programa.
	- Crie a sua classe, clique duas vezes e programe em java!

## Programas e Funções da AX12-JavA 

Dentro da pasta encontrará os seguintes arquivos:

*AX12.java:* Biblioteca que implementa as funções dos servos.

*Bioloid.java:* Biblioteca para auxiliar nas funções do Bioloid, como zerar motores e colocá-los em posição inicial.

*Run.java:* Permite rodar o programa.

### Funções

<p align="center">
AX12.java
</p>

_serial():_ Inicializa a comunicação serial dos motores, sempre começar com essa função.

_move(id, pos):_ Movimenta o servo de um certo ID para uma posição entre 0 (0°) e 1024 (300°).

_moveSpeed(id, pos, speed):_ Movimenta o servo de um certo ID para uma posição entre 0 (0°) e 1024 (300°) com velocidade entre 0 e 1024.

_em desenvolvimento_


<p align="center">
Bioloid.java
</p>


_initialPos():_ Seta os motores em posição inicial.

_clear():_ Seta os motores em 512.

_em desenvolvimento_

### Instalação

*Ao executar o código RUN, aparecerá o seguinte problema:*


	- Unable to determine hardware version. I see: Hardware	: BCM2835 - expecting BCM2708 or BCM2709. Please report this to projects@drogon.net terminate called after throwing an instance of 'boost::exception_detail::clone_impl<boost::exception_detail::error_info_injector<boost::lock_error> >' what():  boost: mutex lock failed in pthread_mutex_lock: Invalid argument

*Para resolvê-lo, digitar no terminal:*

	- sudo rpi-update 52241088c1da59a359110d39c1875cda56496764
	- sudo reboot

*Ao executar novamente o código, aparecerá outro problema: *

	- wiringPiSetup: Must be root (Did you forget sudo ?)

*Para resolvê-lo: ???*

## Apoio

<img src="http://www.fc.unesp.br/Home/Cursos/Fisica/fisica-fapesp.png" width="200">
  
<img src = "http://proad.ufabc.edu.br/images/headers/logo_ufabc.png" width="100">

## Referências 

[1] PI4J©. **The Pi4J Project: Java I/O Library for the Raspberry Pi**. Disponível em: <http://pi4j.com/download.html>. Acesso em 4 de Outubro de 2017: Pi4J©, 2016.

[2] HERSAN, T. **How to Drive Dynamixel AX-12A Servos (with a RaspberryPi)**. Disponível em: <http://www.instructables.com/id/How-to-drive-Dynamixel-AX-12A-servos-with-a-Raspbe/>. Acesso em 3 de Outubro de 2017: Instructables, 2015.

[3] ECLIPSE, I.; IDE, E. __Documentação do. Eclipse©__. Disponível em:<http://www.eclipse.org>. Acesso em 4 de Outubro de 2017, v. 12, 2006.

[4] NETBEANS, I. __Netbeans©__. Disponível em:<http://netbeans.org/>. Acesso em 4 de Outubro de 2017, v. 11, 2008.

[5] KÖLLING, M. et al. __The bluej system and its pedagogy__. Computer Science Education, Taylor & Francis, v. 13, n. 4, p. 249–268, 2003.

JERONIMO, G. C. **Implementação de Técnica de Processamento de Imagens para a Categoria Kid Size da RoboCup com Validação Real na Plataforma Bioloid ROBOTIS Premium**. FAPESP, UFABC, 2016.

HERSAN, T. **AX-12 Python Library (for RaspberryPi)**. [S.l.]: GitHub, 2014. <https://github.com/thiagohersan/memememe/tree/master/Python/ax12>.


       http://docs.opencv.org/2.4/doc/tutorials/introduction/java_eclipse/java_eclipse.html
       http://pi4j.com/apidocs/     
       https://sourceforge.net/p/raspberry-gpio-python/wiki/BasicUsage/                                   
       http://pi4j.com/example/control.html                                                              
       https://www.programiz.com/python-programming/methods/built-in/chr                                  
       http://pyserial.readthedocs.io/en/latest/pyserial_api.html                                          
       https://eclipsesource.com/blogs/2012/10/17/serial-communication-in-java-with-raspberry-pi-and-rxtx/ 
       https://wiki.python.org/moin/BitwiseOperators                                                       
       https://www.tutorialspoint.com/java/java_bitwise_operators_examples.htm                             
       https://www.domoticz.com/forum/viewtopic.php?t=16433#p122279                                        
       http://www.oppedijk.com/robotics/control-dynamixel-with-raspberrypi
       http://wiringpi.com/reference/serial-library/
       https://www.filipeflop.com/blog/comunicacao-serial-arduino-com-raspberry-pi/
       http://java-buddy.blogspot.com.br/2015/03/install-netbeans-on-raspberry.html
