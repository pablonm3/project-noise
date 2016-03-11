package sonido;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import interfaz.Gui;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import procesamiento.Cola;
import procesamiento.convierte;






public class Reproductor implements Runnable{

	private byte[]array1;
	private int pausedOnFrame = 0;
	
	Thread t;
	String ruta;
	AdvancedPlayer player;
	FileInputStream fis;
	Cola cola;
	
	public Reproductor(Cola col){
		 
		cola = col;
		ruta = cola.actual();
		array1 = convierte.audioToArray(ruta);
		if(array1 == null)
			System.out.println("ARRAY NULO");
		t = new Thread(this);
		t.start();
	}
	
	public void run()
	{
		
		try {
			fis = new FileInputStream(ruta);
			player = new AdvancedPlayer(fis);
			player.play();
			while(true)
			{
				ruta = cola.siguiente();
				fis = new FileInputStream(ruta);
				player = new AdvancedPlayer(fis);
				Gui.recargaNameYPic();
				player.play();
			}
		} catch (Exception e) {
			System.out.println("ERROR EN EL RUN");
			e.printStackTrace();
		}
		
	}
	
	public void siguiente()
	{
		ruta = cola.siguiente();
		t.stop();
		t = new Thread(this);
		t.start();
	}
	
	public void anterior()
	{
		ruta = cola.anterior();
		t.stop();
		t = new Thread(this);
		t.start();
		
	}
	
	public void pause(){
	   
		t.suspend();
		
	}
	
	public void reanudar()
	{
		t.resume();
	}
	
	public String getPath()
	{
		return ruta;
	}
	
	public boolean isPlaying()
	{
		return !t.isInterrupted();
	}
	
}
