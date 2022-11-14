import gui.GUISimulator ;
import java.awt.Color ;
import java.awt.Point;
import java.util.ArrayList;

public class TestConwaySimulator {
	public static void main ( String [] args ) {
        GUISimulator gui = new GUISimulator (500 , 500 , Color . WHITE ) ;
        ArrayList<Point> pts = new ArrayList<>();
    	pts.add(new Point(0,0));
    	pts.add(new Point(0,5));
    	pts.add(new Point(5,5));
    	pts.add(new Point(2,2));
    	pts.add(new Point(1,0));
    	pts.add(new Point(0,1));
    	pts.add(new Point(20,5));
    	pts.add(new Point(50,50));
    	pts.add(new Point(51,52));
    	pts.add(new Point(0,80));
    	pts.add(new Point(80,3));
    	pts.add(new Point(81,4));
    	pts.add(new Point(20,20));
    	Color[] couleurs = {Color.WHITE, Color.BLUE};
        GamesSimulator game = new GamesSimulator (pts, "Conway", 0, gui,80, couleurs);
        gui.setSimulable (game) ;
   }
}