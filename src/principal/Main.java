package principal;

import java.io.File;
import javax.swing.JFileChooser;
import audioAnalisis.Bpm;
import interfaz.Gui;
import procesamiento.Cola;
import procesamiento.convierte;
import sonido.Reproductor;

public class Main {

	public static void main(String args[])
	{
		Cola cola;
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true);
		chooser.showOpenDialog(null);
	    File files[] = chooser.getSelectedFiles();
	    cola = convierte.fileToCola(files);
		Reproductor musica = new Reproductor(cola);
		Gui interfaz = new Gui(musica);
		 try {
			System.out.println("the bmp of : " +cola.actual() +"is:" + Bpm.getBpm(cola.actual()) );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
}
