# Comunicação AX-12A, Java e Raspberry Pi 3B



----------------------------------------- PI4J -------------------------------------------
---------


Para instalar o PI4J:

	No terminal: 
	- curl -s get.pi4j.com | sudo bash

------------------------------------- ECLIPSE -------------------------------------------
---------


Para instalar o ECLIPSE:

	No terminal: 
		- sudo apt-get install eclipse

Para colocar as bibliotecas no Eclipse:

	- Abra o eclipse
	- em window -> preferences
	- em java -> build path -> user libraries
	- New...
	- User Library name: Pi4j (OK)
	- Add External JARs...
	- Procure por /opt/pi4j/lib/pi4j-core.jar 
	- Selecione também: pi4j-device.jar/ pi4j-gpio-extension.jar/ pi4j-service.jar
	- OK!

Crie um novo projeto Java:

	- Coloque o nome e clique em next>
	- em Libraries clique em Add Library...
	- User Library
	- Selecione Pi4j
	- Finish
	- Crie a Classe e começe a brincadeira

----------------------------------------- NETBEANS -------------------------------------------
---------

Para instalar o NETBEANS:

	No site do netbeans: https://netbeans.org/downloads/ , baixe a versão Java SE na plataforma
	SO Independent ZIP.

	Ao baixar, vá em download e descompacte

	Abra o arquivo contido em: /netbeans/bin/netbeans

	Atualize os pacotes, e reinicie o netbeans

Para colocar as bibliotecas no NETBEANS:

	- Abra o NETBEANS
	- Em ferramentas, clique em bibliotecas
	- Clique em Nova Biblioteca...
	- Coloque como nome: Pi4j , dê (OK)
	- Adicionar Jar/Pasta...
	- Procure por /opt/pi4j/lib/pi4j-core.jar 
	- Selecione também: pi4j-device.jar/ pi4j-gpio-extension.jar/ pi4j-service.jar
	- OK!

Crie um novo projeto Java:

	- Clique em Aplicação Java, Próximo >
	- Escolha o Nome de seu projeto e clique em Finalizar
	- Ao criar o arquivo, clique com o botão direito do mouse em cima do projeto criado
	- Vá em propriedades --> bibliotecas --> Adicionar Bibliotecas --> Pi4j
	- Adicione ela, e dê Ok!

----------------------------------------------------------------------------------------------
--------------------------------------------BlueJ---------------------------------------------
----------------------------------------------------------------------------------------------

	
Ao abrir o BlueJ, clique em New Project

Crie uma pasta em /home/pi e nomeie como BlueJ Projects, dentro dessa pasta crie outra com o nome do seu Projeto, clique em create

Ao Abrir vá em Tools --> Preferences --> Library, clique em add

Procure as pastas: /opt/pi4j/lib/pi4j-core.jar (e também pi4j-device.jar/ pi4j-gpio-extension.jar/ pi4j-service.jar)

Dê ok e reinicie o Programa

Crie a sua classe, clique duas vezes e programe em java!



----------------------------------------------------------------------------------------------
-----------------------------------------AX12-A LIB-------------------------------------------
----------------------------------------------------------------------------------------------

Ao executar o código RUN, aparecerá o seguinte problema:


%Unable to determine hardware version. I see: Hardware	: BCM2835
%,
% - expecting BCM2708 or BCM2709. Please report this to projects@drogon.net
%terminate called after throwing an instance of 'boost::exception_detail::clone_impl<boost::exception_detail::error_info_injector<boost::lock_error> >'
%  what():  boost: mutex lock failed in pthread_mutex_lock: Invalid argument

Para resolvê-lo, digitar no terminal:

	- sudo rpi-update 52241088c1da59a359110d39c1875cda56496764
	- sudo reboot

Ao executar novamente o código, aparecerá outro problema: 

% wiringPiSetup: Must be root (Did you forget sudo ?)

Para resolvê-lo: ???



----------------------------------------------------------------------------------------------
------------------------------------------OBS E REF-------------------------------------------
----------------------------------------------------------------------------------------------

dtoverlay=pi3-miniuart-bt
dtoverlay=w1-gpio
enable_uart=1

** Pacotes e exemplos do Pi4j em: /opt/pi4j/

Fonte: http://robinhenniges.com/en/install-opencv-for-java-raspberry-pi-debian-jessy
       http://ubuntuforum-br.org/index.php?topic=104190.0
       http://answers.opencv.org/question/12449/how-to-use-txt-and-cmake-files-in-cmake/
       http://docs.opencv.org/2.4/doc/tutorials/introduction/java_eclipse/java_eclipse.html
       http://pi4j.com/install.html
       https://codeshare.io/
       http://pi4j.com/apidocs/                                                                            --> pi4j reference
       https://sourceforge.net/p/raspberry-gpio-python/wiki/BasicUsage/                                    --> RPi.GPIO python reference
       http://pi4j.com/example/control.html                                                                --> como acender um led, java raspberry
       https://www.programiz.com/python-programming/methods/built-in/chr                                   --> o que é chr python 
       http://pyserial.readthedocs.io/en/latest/pyserial_api.html                                          --> python serial
       https://eclipsesource.com/blogs/2012/10/17/serial-communication-in-java-with-raspberry-pi-and-rxtx/ --> RXTX SERIAL JAVA
       https://wiki.python.org/moin/BitwiseOperators                                                       --> operações de bits python
       https://www.tutorialspoint.com/java/java_bitwise_operators_examples.htm                             --> operação de bits em java
       https://www.domoticz.com/forum/viewtopic.php?t=16433#p122279                                        --> arrumar o primeiro problema do java de não reconhecer o hardware
       http://www.oppedijk.com/robotics/control-dynamixel-with-raspberrypi
       http://wiringpi.com/reference/serial-library/
       https://www.filipeflop.com/blog/comunicacao-serial-arduino-com-raspberry-pi/
       http://java-buddy.blogspot.com.br/2015/03/install-netbeans-on-raspberry.html
