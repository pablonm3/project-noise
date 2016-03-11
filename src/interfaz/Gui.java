package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import procesamiento.convierte;
import sonido.Reproductor;

@SuppressWarnings("serial")
public class Gui extends JFrame{

	private JPanel panel;
	private JButton next;
	private JButton play;
	private JButton pause;
	private JButton back;
	private static JLabel picLabel;
	private static JLabel name;
	private static Reproductor control;
	private boolean reproduciendo; 
	
	
	public Gui(Reproductor c)
	{
		control = c;
		armaGui();
	}
	
	
	
	private void armaGui()
	{
		// TRABAJA IMAGEN
		BufferedImage bufer = convierte.extraeImg(control.getPath());
		if(bufer == null)
			try {
				bufer  = ImageIO.read(new File("images/noImage.jpg"));
			} catch (Exception e) {
				System.out.println("ERROR EN ARMA GUI IMAGEN NULA");
				e.printStackTrace();
			}
		picLabel = new JLabel(new ImageIcon(bufer));
		// FIN IMAGEN WORK
		setTitle("Project Noise");
		setResizable(false);
		ImageIcon icon = new ImageIcon("images/iconApp.png");
		picLabel.setBounds(80, 5, 440, 275);
		name = new JLabel();
		name.setText(convierte.extraeNombre(control.getPath()));
		name.setBounds(80, 280, 440, 32);
		name.setFont(new Font("Serif", Font.BOLD, 20));
		name.setForeground(Color.red);
		name.setOpaque(true);
		name.setBackground(Color.gray);
		picLabel.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		setIconImage(icon.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);	
		ImageIcon nextI = new ImageIcon("images/next.png");
		ImageIcon playI = new ImageIcon("images/play.png");
		ImageIcon backI = new ImageIcon("images/back.png");
		ImageIcon pauseI = new ImageIcon("images/pause.png");
		// buttons
		next= new JButton(nextI);
		play= new JButton(playI);
		pause= new JButton(pauseI);
		back = new JButton(backI);
		back.setContentAreaFilled(false);
		play.setContentAreaFilled(false);
		next.setContentAreaFilled(false);
		pause.setContentAreaFilled(false);
		back.setBounds(130,330,70,70);
		next.setBounds(400,330,70,70);
		pause.setBounds(265,330,70,70);
		play.setBounds(265,330,70,70);
		panel = new JPanel();
		panel.add(picLabel);
		panel.setLayout(null);
		panel.setBackground(Color.DARK_GRAY);
		panel.add(back);
		panel.add(name);
		panel.add(pause);
		panel.add(next);
		add(panel);
		setSize(600, 460);
		EventHandler handler = new EventHandler();
		back.addActionListener(handler);
		next.addActionListener(handler);
		pause.addActionListener(handler);
		play.addActionListener(handler);
		reproduciendo = true;
	
	}
	
private class EventHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent event){
			
			if(event.getSource() == pause)
			{
				stop();
			}
			
			if(event.getSource() == play)
			{
				reproducir();
			}
			
			if(event.getSource() == next)
			{
				siguiente();
			}
			
			if(event.getSource() == back)
			{
				anterior();
			}

		}

	
	}	// fin clase handler
	
	public static void recargaNameYPic()
	{
		String path = control.getPath();
		name.setText(convierte.extraeNombre(path));
		// TRABAJA IMAGEN
		BufferedImage bufer = convierte.extraeImg(path);
		if(bufer == null)
			try {
				bufer  = ImageIO.read(new File("images/noImage.jpg"));
			} catch (Exception e) {
				System.out.println("ERROR EN ARMA GUI IMAGEN NULA");
				e.printStackTrace();
			}
		picLabel.setIcon(new ImageIcon(bufer));
		// FIN IMAGEN WORK
		
	}

	public void siguiente()
	{
		control.siguiente();
		if(!reproduciendo)
		{
			control.pause();
		}
		recargaNameYPic();
	}
	
	public void anterior()
	{
		control.anterior();
		if(!reproduciendo)
		{
			control.pause();
		}
		recargaNameYPic();
		
		
	}
	public void reproducir()
	{
		panel.add(pause);
		panel.remove(play);
		control.reanudar();
		reproduciendo = true;
	}

	public void stop()
	{
		panel.add(play);
		panel.remove(pause);
		reproduciendo = false;
		control.pause();
	}
	
	
	
	
	
}
