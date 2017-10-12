
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;



public class OpenCvJavaRasp {


	static JFrame frame;
	static JLabel lbl;
	static ImageIcon icon;
	
	private static BufferedImage ConvertMat2Image(Mat CamData) {
		
		MatOfByte byteMatVerisi = new MatOfByte();
		Imgcodecs.imencode(".jpg", CamData, byteMatVerisi);
		byte[] byteArray = byteMatVerisi.toArray();
		BufferedImage video = null;
		try {
			InputStream in = new ByteArrayInputStream(byteArray);
			video = ImageIO.read(in);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return video;
	}
	
 
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		VideoCapture videoDevice = new VideoCapture();
		videoDevice.open(0);
		
		if (videoDevice.isOpened()) {
	
			while (true) {		
				Mat frameCapture = new Mat();
				videoDevice.read(frameCapture);

				if (frame == null)
					frame = new JFrame();
					frame.setLayout(new FlowLayout());
					frame.setSize(700, 600);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				if (lbl != null)
					frame.remove(lbl);
				icon = new ImageIcon(ConvertMat2Image(frameCapture));
				lbl = new JLabel();
				lbl.setIcon(icon);
				frame.add(lbl);
				frame.revalidate();
			}
		}
		
		
		else {
			System.out.println("Webcam não encontrada. Coloque no USB.");
			return;
		}
		
	}

	
}
