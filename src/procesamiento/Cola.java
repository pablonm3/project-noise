package procesamiento;

import java.util.ArrayList;

public class Cola {

	private ArrayList<String>cola;
	private int tama�o,
				posicion;
	public Cola(int size)
	{
		tama�o = size;
		posicion = 0;
		cola = new ArrayList<String>(size);
	}
	
	public void add(String cadena)
	{
		cola.add(cadena);
	}
	
	public String actual()
	{
		return cola.get(posicion);
	}
	
	public String siguiente()
	{
		posicion = (posicion+1)%tama�o;
		return cola.get(posicion);
	}
	
	public String anterior()
	{
		posicion = posicion-1;
		if(posicion < 0)
			posicion = tama�o-1;
		return cola.get(posicion);
	}
	
	
}
