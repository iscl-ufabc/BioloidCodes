import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class Book_1 extends Matriz{
	
	public static void main(String[] args) throws Exception {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Mat imagem1 = Imgcodecs.imread("cathedral.jpg");
		
		if(imagem1.dataAddr()==0)
			throw new Exception ("Couldn't open file "+"cathedral.jpg");
		
		matChannel(imagem1, 0, 0);
		matChannel(imagem1, 1, 0);
		
		matPrintAll(imagem1);
		
		Imgcodecs.imwrite("NomeNovo.jpg", imagem1);
		
		ImageViewer teste = new ImageViewer();
		
		teste.show(imagem1);
		
		teste.toBufferedImage(imagem1);
		
		teste.show(imagem1);
		
	}

}
