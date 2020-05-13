import java.awt.Image;
import java.lang.management.ManagementPermission;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

public class Book_2 extends Matriz{
	
	private static JFrame frame;
	private static JLabel imageLabel;
	public static int width = 640, heigth = 480;
	
	public static void initGUI() {
		frame = new JFrame("Camera Input Example");  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		frame.setSize(500,500);  
		imageLabel = new JLabel();
		frame.add(imageLabel);
		frame.setVisible(true);       
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Mat webcamMatImage = new Mat(); 
		initGUI();
		
		int t = 0;
		
		VideoCapture capture = new VideoCapture(0);
		
		capture.set(Videoio.CAP_PROP_FRAME_WIDTH,width);
		capture.set(Videoio.CAP_PROP_FRAME_HEIGHT,heigth);
		
		long initial = System.nanoTime()/1000000;

		if( capture.isOpened()) {
			while (true){  
				capture.read(webcamMatImage);
					
				//matChannel(webcamMatImage, 0, 255);
				//Mat blur = webcamMatImage.clone();
					
				//Imgproc.blur(webcamMatImage, blur, new Size(50,50));
					
				Mat imagem = webcamMatImage.clone();
				imagem = matGrayscale_Luminosity(webcamMatImage);

				matVideo(frame, imageLabel,imagem);
				
				long finals = System.nanoTime()/1000000;
				long time = finals - initial;
				
				if(time>=2500) {
					String nome = Integer.toString(t);
					
					nome  = nome.concat(".jpg");
						
					System.out.println(nome);
					
					Imgcodecs.imwrite(nome,imagem);
					t++;
					
					initial = System.nanoTime()/1000000;
				}
			}  
		}
		else{
			System.out.println("Couldn't open capture.");
		}
			
	}

}
