import gui.GUISimulator ;
import java.awt.Color ;
import java.awt.Point;
import java.util.Random ;

public class TestBoidsSimulator {
  public static void main ( String [] args ) {
    GUISimulator gui = new GUISimulator (500 , 500 , Color.BLACK ) ;
    EventManager manager = new EventManager ();
    Random rand = new Random();
    int n = 20;
    Point [] pts = new Point[n];
    Point [] vitesses = new Point[n];
    for (int i=0; i<n; i++){
      pts[i] = new Point(rand.nextInt(500), rand.nextInt(500));
      vitesses[i] = new Point(rand.nextInt(10), rand.nextInt(10));
    }

    Boids boids= new Boids(pts, vitesses, 1, 15, 3, 10);
    BoidsSimulator drawBalls = new BoidsSimulator (boids, gui, manager);
    gui.setSimulable (drawBalls) ;

  }

}
