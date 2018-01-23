----------------------------------------------------------------------------------------------
-------------------------------------------OPENCV---------------------------------------------
----------------------------------------------------------------------------------------------


Como instalar OpenCV 3.1.0 JAVA na Raspberry:

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

		Passo 3 - Abrir o arquivo em home/pi/.bashrc
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
		Passo 10 - cd opencv-3.1.0/
		Passo 11 - mkdir build
		Passo 12 - cd build
		Passo 13 - cmake -D CMAKE_BUILD_TYPE=RELEASE -D WITH_OPENCL=OFF -D BUILD_PERF_TESTS=OFF -D BUILD_SHARED_LIBS=OFF -D JAVA_INCLUDE_PATH=$JAVA_HOME/include -D JAVA_AWT_LIBRARY=$JAVA_HOME/jre/lib/arm/libawt.so -D JAVA_JVM_LIBRARY=$JAVA_HOME/jre/lib/arm/server/libjvm.so -D CMAKE_INSTALL_PREFIX=/usr/local ..
		Passo 14 - make
		Passo 15 - sudo chmod a+rw /usr/local/include
			   sudo chmod a+rw /usr/local/lib
			   sudo chmod a+rw /usr/local/share
			   sudo chmod a+rw /usr/local/bin
		Passo 15 - make install


----------------------------------------------------------------------------------------------
-------------------------------------------ECLIPSE--------------------------------------------
----------------------------------------------------------------------------------------------


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
	- em Native Library Location, clique em escolher diretório e adicione: /usr/local/share/OpenCV/java ou /home/pi/opencv-3.1.0/build/lib (o arquivo que puxará a lib é o libopencv_java310.so)
	- OK!

Crie um novo projeto Java:

	- Coloque o nome e clique em next>
	- em Libraries clique em Add Library...
	- User Library
	- Selecione OpenCV-3.1.0
	- Finish
	- Crie a Classe e começe a brincadeira


----------------------------------------------------------------------------------------------
------------------------------------------NETBEANS--------------------------------------------
----------------------------------------------------------------------------------------------

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


----------------------------------------------------------------------------------------------
--------------------------------------------BlueJ---------------------------------------------
----------------------------------------------------------------------------------------------

	
Ao abrir o BlueJ, clique em New Project

Crie uma pasta em /home/pi e nomeie como BlueJ Projects, dentro dessa pasta crie outra com o nome do seu Projeto, clique em create

Ao Abrir vá em Tools --> Preferences --> Library, clique em add

Procure as pastas: /home/pi/opencv-3.1.0/build/bin/opencv-310.jar

Dê ok e reinicie o Programa

Crie a sua classe, clique duas vezes na classe e programe em java!


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
