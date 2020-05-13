
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;

public class Book_0 extends Matriz{

	public static void filter(Mat image){
		
		int totalBytes = (int)(image.total() * image.elemSize());
		
		byte buffer[] = new byte[totalBytes];
		
		image.get(0, 0,buffer);
		
		System.out.println();
		
		for(int i=0;i<totalBytes;i++){
			if(i%3==0)
				buffer[i]=0;
		}
		
		image.put(0, 0, buffer);
	}
 
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Mat imagem1 = new Mat(480,640,CvType.CV_8UC3);
		Mat imagem2 = new Mat(new Size(640,480),CvType.CV_8UC3);
		Mat imagem3 = new Mat(new Size(3,3), CvType.CV_8UC3, new Scalar(1));
		
		System.out.println("Matrix " +imagem3.size()+  ", Channels: " + imagem3.channels() 
						    + "\n" + imagem3.dump() + "\n");
		
	
		//matPoint(imagem3,new int[] {0,1,2},200); //Altera o ponto [1][2] do canal 2 para 200 
		
		//matRow(imagem3,new int[] {1,1},50); //Altera a linha 1 do canal 3 para 50 
		
		//matCol(imagem3,new int[] {0,1},150); //Altera a coluna 1 do canal 0 para 150
		
		//matChannel(imagem3,1,8); // Altera o canal 1 para 8
		
		matPrintAll(imagem3);
		
		matAllChannels(imagem3, 10); // Altera todos canais para 10
		
		matPrintAll(imagem3);
		
		filter(imagem3);
		
		matPrintAll(imagem3);
		
		//System.out.println(imagem3.dump());
		
	}
}
