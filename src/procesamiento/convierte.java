package procesamiento;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.apache.commons.collections.buffer.CircularFifoBuffer;
import org.jaudiotagger.audio.mp3.MP3File;

public class convierte {

	public static byte[]audioToArray(String path)
	{
		File myFile = new File(path);

		AudioInputStream is;
		try {
			File f = new File(path);
			byte[] array = new byte[(int) f.length()];
			FileInputStream fis = new FileInputStream(f);
			fis.read(array);
			return array;
		} catch (Exception e) {
			System.out.println("error en: audioToarray");
			e.printStackTrace();
		}
	
		return null;
	}
	
	public static BufferedImage imgToBuffer(Image img)
	{
		 if (img instanceof BufferedImage)
		    {
		        return (BufferedImage) img;
		    }
		 BufferedImage bimage =  new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);;

		    // Create a buffered image with transparency

		    // Draw the image on to the buffered image
		    Graphics2D bGr = bimage.createGraphics();
		    bGr.drawImage(img, 0, 0, null);
		    bGr.dispose();

		    // Return the buffered image
		    return bimage;
	}
	
	public static BufferedImage extraeImg(String path)
	{
		MP3File mp3;
		try {
			mp3 = new MP3File(path);
		Image img =  (BufferedImage) mp3.getTag().getFirstArtwork().getImage();
		img =  img.getScaledInstance(440, 275, 1);
		return imgToBuffer(img);
		} catch (Exception e) {
		}    
		return null;
	}
	public static String extraeNombre(String path)
	{
		String name;
		int len = path.length();
		while(len>0 && path.charAt(len-1) != '/' && path.charAt(len-1) != '\\')
			len--;
		name = path.substring(len);
		return name;
	}
	public static Cola fileToCola(File[] files)
	{
		String path;
		Cola cola = new Cola(files.length);
		for(int i=0; i< files.length; i++)
		{
			path = files[i].getAbsolutePath();
			cola.add(path);
		}
		return cola;
	}
}
