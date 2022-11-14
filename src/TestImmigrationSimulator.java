import gui.GUISimulator ;
import java.awt.Color ;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;


public class TestImmigrationSimulator {
	public static void main ( String [] args ) {
        GUISimulator gui = new GUISimulator (700 , 700 , Color . WHITE ) ;
        ArrayList<Point> pts = new ArrayList<>();
        
        Random rand = new Random();
    	for ( int i = 0; i<=49; i++) {
    		pts.add(new Point(rand.nextInt(600), rand.nextInt(600)));
    	}
    	Color[] couleurs = new Color[1];
        GamesSimulator game = new GamesSimulator (pts,"Immigration",4, gui,100, couleurs);
        gui.setSimulable (game) ;
   }
}