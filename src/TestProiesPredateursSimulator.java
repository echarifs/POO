import gui.GUISimulator ;
import java.awt.Color ;
import java.awt.Point;
import java.util.Random ;

public class TestProiesPredateursSimulator {
  public static void main ( String [] args ) {
    GUISimulator gui = new GUISimulator (500 , 500 , Color.BLACK ) ;
    EventManager manager = new EventManager ();
    Random rand = new Random();
    int n = 20;
    int m = 10;
    int r1 = 7;
    int r2 = 15;
    Point [] pts = new Point[n];
    Point [] vitesses = new Point[n];
    Point [] pts2 = new Point[m];
    Point [] vitesses2 = new Point[m];
    for (int i=0; i<n; i++){
      pts[i] = new Point(rand.nextInt(500), rand.nextInt(500));
      vitesses[i] = new Point(rand.nextInt(10), rand.nextInt(10));
    }
    for (int i=0; i<m; i++){
      pts2[i] = new Point(rand.nextInt(500), rand.nextInt(500));
      vitesses2[i] = new Point(rand.nextInt(10), rand.nextInt(10));
    }
    Proies proies= new Proies(pts, vitesses, 1, 15, 3, r1);
    Predateurs predateurs= new Predateurs(pts2, vitesses2, 2, 8, 3, r2);
    ProiesPredateursSimulator drawBalls = new ProiesPredateursSimulator (proies, predateurs, gui, manager);
    gui.setSimulable (drawBalls) ;

  }

}
