import gui.GUISimulator ;
import java.awt.Color ;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class TestSchellingSimulator {

	public static void main(String[] args) {
		GUISimulator gui = new GUISimulator (1000 , 1000 , Color . WHITE ) ;
		int seuil = 5;
		Color[] couleurs = {Color.BLUE, Color.RED, Color.BLACK, Color.YELLOW, Color.GREEN, Color.PINK};
        ArrayList<Point> pts = new ArrayList<>();
		
       Random rand = new Random();
    	for ( int i = 0; i<=150; i++) {
    		pts.add(new Point(rand.nextInt(999), rand.nextInt(999)));
    	}
        
        GamesSimulator game = new GamesSimulator(pts, "Schelling", seuil, gui, 80, couleurs);
        gui.setSimulable (game) ;

	}

}
