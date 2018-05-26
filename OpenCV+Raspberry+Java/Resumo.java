Como usar o OpenCV?

O OpenCV trabalha com matrizes, estas são objetos nomeadas Mat. Para inicializar uma Mat:

public class Book_0{
	
	public static void main(String[] args){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // Inicializa a biblioteca OpenCV
		
							//int largura, int altura , tipo de dados guardados pela matriz 
		Mat imagem = new Mat(     480    ,     640    , CvType.CV_8UC3);
	}
}

Os tipos detalhados como CvType.CV_8UC3 são especificados no livro:

• CV_8U: São unsigned int de 8-bit que variam de 0 a 255;
• CV_8S: São signed integers de 8-bit que variam de -128 até 127;
• CV_16U: São unsigned integers de 16-bit que variam de 0 a 65,535;
• CV_16S: São signed integers de 16-bit que variam de -32,768 a 32,767;
• CV_32S: São signed integers de 32-bit que variam de -2,147,483,648 a 2,147,483,647;
• CV_32F: São pontos flutuantes de 32-bit que variam de -FLT_MAX a FLT_MAX incluindo valores de INF e NAN;
• CV_64F: São pontos flutuantes de 64-bit que variam de -DBL_MAX a DBL_MAX incluindo valores de INF e NAN.

Portanto são detalhados como:

• CV_<bit_depth>U(Unsigned)C(Num_of_Channels)
• CV_<bit_depth>S(Signed)C(Num_of_Channels)
• CV_<bit_depth>F(Floating)C(Num_of_Channels)

O CvType.CV_8UC3 é uma matriz de 3 canais de inteiros de 8-bit sem sinais.
(480,640, CvType.CV_8UC3); -> representa 3 matrizes de 640 de altura por 480 de largura que podem armazenar inteiros de 8-bit.

Outra forma de criar um matriz é:

public class Book_0{
	
	public static void main(String[] args){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Mat imagem = new Mat( new Size(640,480), CvType.CV_8UC3);
	}
}

Posso inicializar os valores da matriz com valor de algum escalar como:

public class Book_0{
	
	public static void main(String[] args){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Mat imagem = new Mat( new Size(640,480), CvType.CV_8UC3, new Scalar(0));
	}
}

Está função vai inicializar 3 matrizes de 640 x 480 com todos valores iguais a zero.

Para imprimir a matriz só realizar a seguinte função:

public class Book_0{
	
	public static void main(String[] args){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Mat imagem = new Mat( new Size(640,480), CvType.CV_8UC3, new Scalar(1,2,3));
		
		System.out.println(imagem.dump());
	}
}

O resultado da impressão pode ser lido da seguinte maneira:

[  1,   2,   3,   1,   2,   3,   1,   2,   3;
   1,   2,   3,   1,   2,   3,   1,   2,   3;
   1,   2,   3,   1,   2,   3,   1,   2,   3]

   
		  | canal 1 | canal 2 | canal 3 |
		  |	        |		  |         |
 linha 1: |  1 1 1  |  2 2 2  |  3 3 3  |
 linha 2: |  1 1 1  |  2 2 2  |  3 3 3  |
 linha 3: |  1 1 1  |  2 2 2  |  3 3 3  |
		  |	        |         |         |

Para alterar os valores de cada canal, row -> altera a linha de um canal, col -> altera a coluna desejada e um vetor
altera o número do canal desejado, por exemplo:

public class Book_0{
	
	public static void main(String[] args){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Mat imagem = new Mat( new Size(640,480), CvType.CV_8UC3, new Scalar(1,2,3));
		
		System.out.println(imagem.dump());
		
		for(int i=0;i<imagem3.rows();i++){
			byte pixelMan[] = new byte[3];
			
			pixelMan[0] = 4 // Novo número do canal 0;
			pixelMan[1] = 5 // Novo número do canal 1;
			pixelMan[2] = 6 // Novo número do canal 2;
			
			for(int j=0;j<imagem3.cols();j++)
				imagem3.put(i,j, pixelMan);
		}
		
		System.out.println(imagem3.dump());
	}
}

como resultado terá a impressão de:

[  1,   2,   3,   1,   2,   3,   1,   2,   3;
   1,   2,   3,   1,   2,   3,   1,   2,   3;
   1,   2,   3,   1,   2,   3,   1,   2,   3]
   
[  4,   5,   6,   4,   5,   6,   4,   5,   6;
   4,   5,   6,   4,   5,   6,   4,   5,   6;
   4,   5,   6,   4,   5,   6,   4,   5,   6]

Que pode ser lido como:

		  | canal 1 | canal 2 | canal 3 |
		  |	        |		  |         |
 linha 1: |  1 1 1  |  2 2 2  |  3 3 3  |
 linha 2: |  1 1 1  |  2 2 2  |  3 3 3  | 
 linha 3: |  1 1 1  |  2 2 2  |  3 3 3  |
		  |	        |         |         |
		 
		  | canal 1 | canal 2 | canal 3 |
		  |	        |		  |         |
 linha 1: |  4 4 4  |  5 5 5  |  6 6 6  |
 linha 2: |  4 4 4  |  5 5 5  |  6 6 6  |
 linha 3: |  4 4 4  |  5 5 5  |  6 6 6  |
		  |	        |         |         |
		  
Para alterar o ponto [0][0] do canal 0, o [1][1] do canal 1 e o [2][2] do canal 2:

		  
public class Book_0{
	
	public static void main(String[] args){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Mat imagem = new Mat( new Size(640,480), CvType.CV_8UC3, new Scalar(1,2,3));
		
		System.out.println(imagem.dump());
			
		imagem.put(0,0, new byte[] {4,2,3});
		imagem.put(1,1, new byte[] {1,5,3});
		imagem.put(2,2, new byte[] {1,2,6});
		
		System.out.println(imagem.dump());
		
	}
}

[  4,   2,   3,   1,   2,   3,   1,   2,   3;
   1,   2,   3,   1,   5,   3,   1,   2,   3;
   1,   2,   3,   1,   2,   3,   1,   2,   6]
   
   
		  | canal 1 | canal 2 | canal 3 |
		  |	        |		  |         |
 linha 1: |  4 1 1  |  2 2 2  |  3 3 3  |
 linha 2: |  1 1 1  |  2 5 2  |  3 3 3  |
 linha 3: |  1 1 1  |  2 2 2  |  3 3 6  |
		  |	        |         |         |

 
