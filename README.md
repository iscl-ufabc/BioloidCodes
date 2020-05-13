# BioloidCodes

**Description:** Adapting the Robotis Bioloid Premium platform [1] using a Raspberry PI 3B [2]. A vision system was built using a *webcam* [3] and the OpenCV library [4] in Java, and the *shield* Raspi2Dynamixel to control all AX-12A motors of the platform using Java and Python code.

**Objective:** This repository aims to share the knowledge obtained during the scientific research [5], in addition to improving the study on the Robotis Bioloid Premium platform, enabling a simple and educational use of this platform.

## Architecture 

An architecture was proposed for the use of image processing techniques and computer vision, in addition to the use of Raspberry Pi 3B to control the motor servos of humanoid in a practical way, using Java and Python codes [6].

<p align="center">
<img src="https://user-images.githubusercontent.com/28567780/32135686-fd12e826-bbe1-11e7-9b8e-8b909cda75f1.jpg" width="500">
</p>

The image illustrates the proposed architecture, with the _pan/tilt_ support and the _webcam_ making the vision system in (a) and (b). The electronic project using Raspberry Pi and the developed _shield_ Raspi2Dynamixel in (c) and (d). Finally, the humanoid platform is represented in (e). The connection between the vision system and the electronic project occurs through OpenCV, and between the electronic project and the robotic player occurs through the AX12-JavA library developed with Pi4J [7].

## Repository Guidelines

**_AX12-Python:_** Contains the library for the AX-12A servos developed by [6], the Bioloid class with defined functions for the platform and installation instructions. In addition to the connection circuit, adapted from [8].

**_AX12-JavA:_** Contains the AX-12A servos library translated to Java, the Bioloid class with defined functions for the platform and installation instructions. In addition to the connection circuit, adapted from [8].

**_OpenCV+Raspberry+Java:_** Contains the adapted codes from [9] for real time OpenCV use, besides the installation instructions. The project of the vision system can be found on OpenNNCV repository.

**_PIXY+Raspberry+Python:_** In development.

## Contact

Gilmar Correia Jeronimo

e-mail: gilmarjeronimo@uol.com.br

Paulo Consoni

e-mail: paulo.consoni4000@gmail.com

Rodrigo Fialho

e-mail: rodrigo.fialho@aluno.ufabc.edu.br

## Support

<img src="http://www.fc.unesp.br/Home/Cursos/Fisica/fisica-fapesp.png" width="200">
  
<img src = "http://proad.ufabc.edu.br/images/headers/logo_ufabc.png" width="100">

## References 

[1] BIOLOID. **Robotis - Bioloid Premium Kit**. Available at: <http://en.robotis.com/index/product.php?cate_code=121010>. Access on May 24, 2016: [n.º], 2016.

[2] FOUNDATION, R. P ©. **Raspberry Pi**. Available at: <https://www.raspberrypi.org/learning/hardware-guide/components/raspberry-pi/>. Access on November 12, 2016: RaspberryPi Foundation, 2016.

[3] LOGITECH©. **Specifications**. In: Logitech. Available at: <http://support.logitech.com/en_us/product/hd-pro-webcam-c920/specs>. Accessed September 20, 2017: Logitech, 2017.

[4] BRADSKI, G.; KAEHLER, A. __Learning OpenCV: Computer vision with the OpenCV library__. [S.l.]: "O'ReillyMedia, Inc.", 2008.

[5] JERONIMO, G. C. **Implementation of RoboCup Kid-Size Image Processing Technique with Real Validation on Bioloid ROBOTIS Premium Platform**. FAPESP, UFABC, 2016.

[6] HERSAN, T. **AX-12 Python Library (for RaspberryPi)**. [S.l.]: GitHub, 2014. <https://github.com/thiagohersan/memememe/tree/master/Python/ax12>.

[7] PI4J©. **The Pi4J Project: Java I/O Library for the Raspberry Pi**. Available at: <http://pi4j.com/download.html>. Access on October 4, 2017: Pi4J©, 2016.

[8] HERSAN, T. **How to Drive Dynamixel AX-12A Servos (with a RaspberryPi)**. Available at: <http://www.instructables.com/id/How-to-drive-Dynamixel-AX-12A-servos-with-a-Raspbe/>. Accessed October 3, 2017: Instructables, 2015.

[9] PISKIN, M. **OpenCvObjectDetection**. [S.l.]: GitHub, 2017. <https://github.com/mesutpiskin/OpenCvObjectDetection>.
