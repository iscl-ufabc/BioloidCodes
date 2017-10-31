# BioloidCodes

**Descrição:** Adaptação da plataforma Robotis Bioloid Premium [1] utilizando uma Raspberry PI 3B [2]. Foi construido um sistema de visão utilizando uma *webcam* [3] e a biblioteca OpenCV [4] em Java, além do *shield* Raspi2Dynamixel para controlar todos os motores AX-12A da plataforma a partir de códigos em Java e Python.

**Objetivo:** Este repositório tem o objetivo de compartilhar os conhecimentos obtidos durante a iniciação científica [5], além de aprimorar o estudo na plataforma Robotis Bioloid Premium, possibilitando um uso simples e educacional desta plataforma.

## Arquitetura 

Foi proposto uma arquitetura para o uso de técnicas de processamento de imagem e visão computacional, além da utilização da Raspberry Pi 3B para controlar os servos motores do humanoide de uma forma prática, utilizando códigos do Java e Python [6].

<p align="center">
<img src="https://user-images.githubusercontent.com/28567780/32135686-fd12e826-bbe1-11e7-9b8e-8b909cda75f1.jpg" width="500">
</p>

A imagem ilustra a arquitetura proposta, com o suporte _pan/tilt_ e a _webcam_ formando o sistema de visão em (a) e (b). O projeto eletrônico utilizando a Raspberry Pi e o _shield_ Raspi2Dynamixel desenvolvido em (c) e (d). A plataforma humanoide em (e). A conexão entre o sistema de visão e o projeto eletrônico ocorre pelo OpenCV, e entre o projeto eletrônico e o jogador robótico ocorre pela biblioteca AX12-JavA desenvolvida com o Pi4J [7].

## Orientações do Repositório

**_AX12-Python:_** Contém a biblioteca para os servos AX-12A desenvolvida por [6], a classe Bioloid com funções definidas para a plataforma e instruções de instalação. Além do circuito para conexão, adaptado de [8].

**_AX12-JavA:_** Contém a biblioteca para os servos AX-12A traduzida para Java, a classe Bioloid com funções definidas para a plataforma e instruções de instalação. Além do circuito para conexão, adaptado de [8].

**_OpenCV+Raspberry+Java:_** Contém os códigos adaptados de [9] para uso do OpenCV em tempo real, além das instruções de instalação.

**_PIXY+Raspberry+Python:_** Em desenvolvimento.

**_Tutorial para Montar a Shield Raspi2Dynamixel:_** Em desenvolvimento.

## Contato

Gilmar Correia Jeronimo

e-mail: gilmarjeronimo@uol.com.br

Paulo Consoni

e-mail: paulo.consoni4000@gmail.com

Rodrigo Fialho 2

e-mail: 

## Apoio

<img src="http://www.fc.unesp.br/Home/Cursos/Fisica/fisica-fapesp.png" width="200">
  
<img src = "http://proad.ufabc.edu.br/images/headers/logo_ufabc.png" width="100">

## Referências 

[1] BIOLOID. **Robotis - Bioloid Premium Kit**. Disponível em: <http://en.robotis.com/index/product.php?cate_code=121010>. Acesso em 24 de Maio de 2016: [s.n.], 2016.

[2] FOUNDATION, R. P ©. **Raspberry Pi**. Disponível em: <https://www.raspberrypi.org/learning/hardware-guide/components/raspberry-pi/>. Acesso em 12 de Novembro de 2016: RaspberryPi Foundation, 2016.

[3] LOGITECH©. **Specifications**. In: Logitech. Disponível em: <http://support.logitech.com/en_us/product/hd-pro-webcam-c920/specs>. Acesso em 20 de Setembro de 2017: Logitech, 2017.

[4] BRADSKI, G.; KAEHLER, A. __Learning OpenCV: Computer vision with the OpenCV library__. [S.l.]: "O’ReillyMedia, Inc.", 2008.

[5] JERONIMO, G. C. **Implementação de Técnica de Processamento de Imagens para a Categoria Kid Size da RoboCup com Validação Real na Plataforma Bioloid ROBOTIS Premium**. FAPESP, UFABC, 2016.

[6] HERSAN, T. **AX-12 Python Library (for RaspberryPi)**. [S.l.]: GitHub, 2014. <https://github.com/thiagohersan/memememe/tree/master/Python/ax12>.

[7] PI4J©. **The Pi4J Project: Java I/O Library for the Raspberry Pi**. Disponível em: <http://pi4j.com/download.html>. Acesso em 4 de Outubro de 2017: Pi4J©, 2016.

[8] HERSAN, T. **How to Drive Dynamixel AX-12A Servos (with a RaspberryPi)**. Disponível em: <http://www.instructables.com/id/How-to-drive-Dynamixel-AX-12A-servos-with-a-Raspbe/>. Acesso em 3 de Outubro de 2017: Instructables, 2015.

[9] PISKIN, M. **OpenCvObjectDetection**. [S.l.]: GitHub, 2017. <https://github.com/mesutpiskin/OpenCvObjectDetection>.
