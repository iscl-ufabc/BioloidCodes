# OPENCV NA RASPBERRY

**Descrição:** Testes realizados com OPENCV na Raspberry Pi 3B utilizando Java.

Como instalar OpenCV 3.4.0 JAVA na Raspberry:

	Digitar no terminal:

		Passo 0 - sudo apt-get purge openjdk-8-jre-headless
			  sudo apt-get install openjdk-8-jre-headless
			  sudo apt-get install openjdk-8-jre

		Passo 1 - sudo apt-get update && sudo apt-get install oracle-java7-jdk cmake ant
		Passo 2 - sudo apt-get install build-essential cmake pkg-config libpng12-0 libpng12-dev 
			  libpng++-dev libpng3 libpnglite-dev zlib1g-dbg zlib1g zlib1g-dev pngtools  libtiff4 
			  libtiffxx0c2 libtiff-tools libjpeg8 libjpeg8-dev libjpeg8-dbg libjpeg-progs libavcodec-dev   
			  libavformat-dev libgstreamer0.10-0-dbg libgstreamer0.10-0 libgstreamer0.10-dev  libunicap2 
			  libunicap2-dev libdc1394-22-dev libdc1394-22 libdc1394-utils swig libv4l-0 libv4l-dev
    
		    OBS: se aparecer qualquer problema digitar o seguinte
		    sudo rm /var/lib/apt/lists/* ; sudo rm /var/lib/apt/lists/partial/* ; sudo apt-get -f install ; sudo apt-get clean ; sudo apt-get update
    
		    Após execute o passo 2 novamente

		Passo 3 - Abrir o arquivo em sudo leafpad ~/.bashrc
		Passo 4 - acrescentar no final do documento/:
			export ANT_HOME=/usr/share/ant/
			export PATH=${PATH}:${ANT_HOME}/bin
			export JAVA_HOME=/usr/lib/jvm/jdk-8-oracle-arm32-vfp-hflt/
			export PATH=$PATH:$JAVA_HOME/bin
		Passo 5 - Salvar
		Passo 6 - sudo reboot

		Para garantir execute os seguintes passos:
		- sudo update-alternatives --config javac
			escolha o que tem o java 1.8
                - sudo update-alternatives --config java
			escolha o que tem o java 1.8

	No terminal:

		Passo 7 - baixar o sources (zip) do release mais atual de https://opencv.org/releases.html
		Passo 8 - colocar o zip na pasta pi
		Passo 9 - unzip opencv.zip 
		Passo 10 - cd opencv-3.4.0/
		Passo 11 - mkdir build
		Passo 12 - cd build
		Passo 13 - cmake -D CMAKE_BUILD_TYPE=RELEASE -D WITH_OPENCL=OFF -D BUILD_PERF_TESTS=OFF -D BUILD_SHARED_LIBS=OFF -D JAVA_INCLUDE_PATH=$JAVA_HOME/include -D JAVA_AWT_LIBRARY=$JAVA_HOME/jre/lib/arm/libawt.so -D JAVA_JVM_LIBRARY=$JAVA_HOME/jre/lib/arm/server/libjvm.so -D CMAKE_INSTALL_PREFIX=/usr/local ..
		Passo 14 - make
		Passo 15 - sudo chmod -R 777 /usr/local/include
			   sudo chmod -R 777 /usr/local/lib
			   sudo chmod -R 777 /usr/local/share
			   sudo chmod -R 777 /usr/local/bin
		Passo 15 - make install


## 1.ECLIPSE

Para instalar o ECLIPSE:

	No terminal: 
		- sudo apt-get install eclipse

Para colocar as bibliotecas no Eclipse:

	- Abra o eclipse
	- em window -> preferences
	- em java -> build path -> user libraries
	- New...
	- User Library name: OpenCV-3.1.0 (OK)
	- Add External JARs...
	- Procure por /home/pi/opencv-3.1.0/build/bin/opencv-310.jar
	- em Native Library Location, clique em escolher diretório e adicione: /usr/local/share/OpenCV/java ou /home/pi/opencv-3.4.0/build/lib (o arquivo que puxará a lib é o libopencv_java340.so)
	- OK!

Crie um novo projeto Java:

	- Coloque o nome e clique em next>
	- em Libraries clique em Add Library...
	- User Library
	- Selecione OpenCV-3.1.0
	- Finish
	- Crie a Classe e começe a brincadeira


## 2.NETBEANS

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
	- Coloque como nome: OpenCV-3.1.0 , dê (OK)
	- Adicionar Jar/Pasta...
	- Procure por /home/pi/opencv-3.1.0/build/bin/opencv-310.jar
	- OK!

Crie um novo projeto Java:

	- Clique em Aplicação Java, Próximo >
	- Escolha o Nome de seu projeto e clique em Finalizar
	- Ao criar o arquivo, clique com o botão direito do mouse em cima do projeto criado
	- Vá em propriedades --> bibliotecas --> Adicionar Bibliotecas --> OpenCV-3.1.0
	- Adicione ela, e dê Ok!

## 3.BlueJ

Ao abrir o BlueJ, clique em New Project

Crie uma pasta em /home/pi e nomeie como BlueJ Projects, dentro dessa pasta crie outra com o nome do seu Projeto, clique em create

Ao Abrir vá em Tools --> Preferences --> Library, clique em add

Procure as pastas: /home/pi/opencv-3.1.0/build/bin/opencv-310.jar

Dê ok e reinicie o Programa

Crie a sua classe, clique duas vezes na classe e programe em java!


## 4.Apoio

<img src="http://www.fc.unesp.br/Home/Cursos/Fisica/fisica-fapesp.png" width="200">
  
<img src = "http://proad.ufabc.edu.br/images/headers/logo_ufabc.png" width="100">

## 5.Referências 

PI4J©. **The Pi4J Project: Java I/O Library for the Raspberry Pi**. Disponível em: <http://pi4j.com/download.html>. Acesso em 4 de Outubro de 2017: Pi4J©, 2016.

HERSAN, T. **How to Drive Dynamixel AX-12A Servos (with a RaspberryPi)**. Disponível em: <http://www.instructables.com/id/How-to-drive-Dynamixel-AX-12A-servos-with-a-Raspbe/>. Acesso em 3 de Outubro de 2017: Instructables, 2015.

ECLIPSE, I.; IDE, E. __Documentação do. Eclipse©__. Disponível em:<http://www.eclipse.org>. Acesso em 4 de Outubro de 2017, v. 12, 2006.

NETBEANS, I. __Netbeans©__. Disponível em:<http://netbeans.org/>. Acesso em 4 de Outubro de 2017, v. 11, 2008.

KÖLLING, M. et al. __The bluej system and its pedagogy__. Computer Science Education, Taylor & Francis, v. 13, n. 4, p. 249–268, 2003.

ROBOTIS.__AX-12/ AX-12+/ AX-12A__. Disponível em: <http://support.robotis.com/en/product/actuator/dynamixel/ax_series/dxl_ax_actuator.htm#Actuator_Address_2F>. Acesso em 26 de Maio de 2018.

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

HENNIGES, R. **Part 1: Installing OpenCV 3.1.0 on Raspberry Pi Debian Jessy with Java Library**. Disponível em: <http://robinhenniges.com/en/install-opencv-for-java-raspberry-pi-debian-jessy>. Acesso em 26 de Maio de 2018.

UBUNTU. **pacote quebrado (pipe) quebrado [Resolvido]**. Disponível em: <http://ubuntuforum-br.org/index.php?topic=104190.0>. Acesso em 26 de Maio de 2018.

OpenCV. **how to use .txt and .cmake files in CMake?**. Disponível em:<http://answers.opencv.org/question/12449/how-to-use-txt-and-cmake-files-in-cmake/>. Acesso em 26 de Maio de 2018.

OpenCV. **Using OpenCV Java with Eclipse**. Disponível em:<http://docs.opencv.org/2.4/doc/tutorials/introduction/java_eclipse/java_eclipse.html>. Acesso em 26 de Maio de 2018.

BAGGIO, D. L. **OpenCV 3.0 Computer Vision with Java**. Packt Publishing Ltd, 2015.
