package audioAnalisis;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.player.Player;
public class Bpm {

	 static Logger log = Logger.getLogger("BeatIt");
	    /**
	     * @param args the command line arguments
	     */
	    public static int getBpm(String source) throws Exception {
	        BPM2SampleProcessor processor = new BPM2SampleProcessor();
	       // processor.setSampleSize(1024);
	        EnergyOutputAudioDevice output = new EnergyOutputAudioDevice(processor);
	       // output.setAverageLength(1024);
	        Player player = new Player(new FileInputStream(source), output);
	        player.play();
	        return processor.getBPM();
	    }

}
