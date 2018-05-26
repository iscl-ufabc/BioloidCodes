import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class Matriz{

	public static void matPoint(Mat matrix, int[] info, int value) {
		int channel = info[0];
		int row = info[1];
		int col = info[2];
		
		int size = matrix.channels();
		
		if (size == 1) 
			matrix.put(row, col, value);
		else {
			if (size == 2) {
				if(channel == 0)
					matrix.put(row, col,new byte[] {(byte) value, (byte) matrix.get(row, col)[1]});
				else 
					matrix.put(row, col,new byte[] {(byte) matrix.get(row, col)[0],(byte) value});
			}
			else {
				if (size == 3) {
					if(channel == 0)
						matrix.put(row, col,new byte[] {(byte) value, (byte) matrix.get(row, col)[1],(byte) matrix.get(row, col)[2]});
					else {
						if(channel == 1) 
							matrix.put(row, col,new byte[] {(byte) matrix.get(row, col)[0],(byte) value,(byte) matrix.get(row, col)[2]});
						else	
							matrix.put(row, col,new byte[] {(byte) matrix.get(row, col)[0],(byte) matrix.get(row, col)[1],(byte) value});
					}
				}
				else {
					if (size == 4) {
						if(channel == 0)
							matrix.put(row, col,new byte[] {(byte) value, (byte) matrix.get(row, col)[1],(byte) matrix.get(row, col)[2],(byte) matrix.get(row, col)[3]});
						else {
							if(channel == 1) 
								matrix.put(row, col,new byte[] {(byte) matrix.get(row, col)[0],(byte) value,(byte) matrix.get(row, col)[2],(byte) matrix.get(row, col)[3]});
							else {
								if(channel == 2)
									matrix.put(row, col,new byte[] {(byte) matrix.get(row, col)[0],(byte) matrix.get(row, col)[1],(byte) value,(byte) matrix.get(row, col)[3]});
								else	
								matrix.put(row, col,new byte[] {(byte) matrix.get(row, col)[0],(byte) matrix.get(row, col)[1],(byte) matrix.get(row, col)[2],(byte) value});
							}
						}
					}
				}
			}
		}	
	}
	
	public static void matRow(Mat matrix, int[] info, int value) {
		int channel = info[0];
		int row = info[1];

		for(int i = 0;i<matrix.cols();i++)
			matPoint(matrix, new int[] {channel,row,i}, value);

	}
	
	public static void matCol(Mat matrix, int[] info, int value) {
		int channel = info[0];
		int col = info[1];

		for(int i = 0;i<matrix.rows();i++)
			matPoint(matrix, new int[] {channel,i,col}, value);

	}
	
	public static void matChannel(Mat matrix, int channel, int value) {

		int totalBytes = (int) (matrix.total()*matrix.elemSize());
		
		byte dadosMatriz[] = new byte[totalBytes]; 
		
		matrix.get(0 , 0, dadosMatriz);
		
		for(int i = 0;i<totalBytes;i+=3) {
			if(channel == 0)
				dadosMatriz[i] = (byte) value;
			else {
				if(channel == 1)
					dadosMatriz[i+1] = (byte) value;
				else
					dadosMatriz[i+2] = (byte) value;
			}
		}
		
		matrix.put(0, 0, dadosMatriz);

	}
	
	public static void matAllChannels(Mat matrix, int value) {
		
		int totalBytes = (int) (matrix.total()*matrix.elemSize());
		
		byte dadosMatriz[] = new byte[totalBytes]; 
		
		matrix.get(0 , 0, dadosMatriz);
		
		for(int i = 0;i<totalBytes;i+=3) {
			dadosMatriz[i] = (byte) value;
			dadosMatriz[i+1] = (byte) value;
			dadosMatriz[i+2] = (byte) value;
		}
		
		matrix.put(0, 0, dadosMatriz);
	}

	public static void matPrintChannel(Mat matrix, int channel) {

		System.out.println("Channel " + channel);
		
		System.out.println();
		
		for(int i = 0;i<matrix.rows();i++) {
			for(int j = 0;j<matrix.cols();j++) {
				if((int) matrix.get(i, j)[channel]<10)
					System.out.print("   " + (int) matrix.get(i, j)[channel]);
				else {
					if((int) matrix.get(i, j)[channel]<100)
						System.out.print("  " + (int) matrix.get(i, j)[channel]);
					else
						System.out.print(" " + (int) matrix.get(i, j)[channel]);
				}
			}
			System.out.println();
		}
		
		System.out.println();
	}
	
	public static void matPrintAll(Mat matrix) {

		for(int k = 0;k < matrix.channels();k++) {
		
			System.out.println("Channel " + k);
			
			System.out.println();
			
			for(int i = 0;i<matrix.rows();i++) {
				for(int j = 0;j<matrix.cols();j++){
					if((int) matrix.get(i, j)[k]<10)
						System.out.print("   " + (int) matrix.get(i, j)[k]);
					else {
						if((int) matrix.get(i, j)[k]<100)
							System.out.print("  " + (int) matrix.get(i, j)[k]);
						else
							System.out.print(" " + (int) matrix.get(i, j)[k]);
					}
				}
				System.out.println();
			}
			
			System.out.println();
		}
	}
	
	// Grayscale https://www.johndcook.com/blog/2009/08/24/algorithms-convert-color-grayscale/
	
	public static Mat matGrayscale_Lightless(Mat matrix) {
		
		Mat gray = new Mat(matrix.size(),CvType.CV_8UC1);
		
		int totalBytes1 = (int) (gray.total()*gray.elemSize());
		byte dadosMatriz1[] = new byte[totalBytes1]; 
		
		int totalBytes3 = (int) (matrix.total()*matrix.elemSize());
		byte dadosMatriz3[] = new byte[totalBytes3]; 
		
		matrix.get(0 , 0, dadosMatriz3);
			
		for(int i = 0;i<totalBytes3;i+=3){
			int maior = (int) (dadosMatriz3[i]&0xFF);
			int menor = (int) (dadosMatriz3[i]&0xFF);
			
			for(int k = 1;k<3;k++ ) {

				if((int) (dadosMatriz3[i+k]&0xFF) < menor)
					menor = (int) (dadosMatriz3[i+k]&0xFF); 
					
				if((int) (dadosMatriz3[i+k]&0xFF) > maior)
					maior = (int) (dadosMatriz3[i+k]&0xFF);
					
			}
			dadosMatriz1[(i/3)] = 0;
			dadosMatriz1[(i/3)] = (byte) ((maior+menor)/2); 
		}
		
		gray.put(0, 0, dadosMatriz1);
		
		return gray;
	}
	
	public static Mat matGrayscale_Average(Mat matrix) {
		
		Mat gray = new Mat(matrix.size(),CvType.CV_8UC1);
		
		int totalBytes1 = (int) (gray.total()*gray.elemSize());
		byte dadosMatriz1[] = new byte[totalBytes1]; 
		
		int totalBytes3 = (int) (matrix.total()*matrix.elemSize());
		byte dadosMatriz3[] = new byte[totalBytes3]; 
		
		matrix.get(0 , 0, dadosMatriz3);
		
		for(int i = 0;i<totalBytes3;i+=3)
			dadosMatriz1[i/3] = (byte) (( (int) (dadosMatriz3[i]&0xFF) + (int) (dadosMatriz3[i+1]&0xFF) + (int) (dadosMatriz3[i+2]&0xFF))/3);
		
		gray.put(0, 0, dadosMatriz1);
		
		return gray;
	}
	
	public static Mat matGrayscale_Luminosity(Mat matrix) {
		
		Mat gray = new Mat(matrix.size(),CvType.CV_8UC1);
		
		int totalBytes1 = (int) (gray.total()*gray.elemSize());
		byte dadosMatriz1[] = new byte[totalBytes1]; 
		
		int totalBytes3 = (int) (matrix.total()*matrix.elemSize());
		byte dadosMatriz3[] = new byte[totalBytes3]; 
		
		matrix.get(0 , 0, dadosMatriz3);
		
		for(int i = 0;i<totalBytes3;i+=3) 
			dadosMatriz1[i/3] = (byte) ((float) (0.07 * (int) (dadosMatriz3[i]&0xFF)) + (float) (0.72 * (int) (dadosMatriz3[i+1]&0xFF)) + (float) (0.21 * (int) (dadosMatriz3[i+2]&0xFF)) );
		
		gray.put(0, 0, dadosMatriz1);
		
		return gray;
	}

	// VideoViewer
	
	public static BufferedImage toBufferedImage(Mat matrix){
		int type = BufferedImage.TYPE_BYTE_GRAY;
		if ( matrix.channels() > 1 ) 
				type = BufferedImage.TYPE_3BYTE_BGR;
			
		int bufferSize = matrix.channels()*matrix.cols()*matrix.rows();
		byte [] buffer = new byte[bufferSize];
		matrix.get(0,0,buffer); // get all the pixels
		BufferedImage image = new BufferedImage(matrix.cols(),matrix.rows(), type);
		final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);  
		
		return image;
	}
	
	public static void matVideo(JFrame frame, JLabel imageLabel, Mat matrix) {
		if( !matrix.empty() ){  
			Image tempImage= toBufferedImage(matrix);
			ImageIcon imageIcon = new ImageIcon(tempImage, "Captured video");
			imageLabel.setIcon(imageIcon);
			frame.pack();  //this will resize the window to fit the image
		}  
		else
			System.out.println(" -- Frame not captured -- Break!"); 
	}
}
