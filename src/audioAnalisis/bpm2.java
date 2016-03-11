package audioAnalisis;

import java.io.File;

import com.echonest.api.v3.EchoNestException;
import com.echonest.api.v3.track.TrackAPI;

public class bpm2 {

	public static void showBPMS(File dir) throws EchoNestException {
	     TrackAPI trackAPI = new TrackAPI("8C0XMAEQH4FOGIFGN");
	     File files = dir;
	         if (files.getAbsolutePath().toLowerCase().endsWith(".mp3")) {
	             String id = trackAPI.uploadTrack(files, true);
	             System.out.printf("Tempo 6%.3f %s\n",
	                 trackAPI.getTempo(id).getValue(), files.getAbsoluteFile());
	         
	     }
	}
}
