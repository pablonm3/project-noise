package sonido;
import java.io.FileInputStream;

import interfaz.Gui;
import javazoom.jl.player.advanced.AdvancedPlayer;
import procesamiento.Cola;
import procesamiento.convierte;






public class Reproductor implements Runnable{

	private byte[]array1;
	
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
	
	@SuppressWarnings("deprecation")
	public void siguiente()
	{
		ruta = cola.siguiente();
		t.stop();
		t = new Thread(this);
		t.start();
	}
	
	@SuppressWarnings("deprecation")
	public void anterior()
	{
		ruta = cola.anterior();
		t.stop();
		t = new Thread(this);
		t.start();
		
	}
	
	@SuppressWarnings("deprecation")
	public void pause(){
	   
		t.suspend();
		
	}
	
	@SuppressWarnings("deprecation")
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
