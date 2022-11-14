import gui.GUISimulator ;
import java.awt.Color ;
import java.awt.Point;
import java.util.Random ;
 public class TestBallsSimulator {
     public static void main ( String [] args ) {
          GUISimulator gui = new GUISimulator (500 , 500 , Color.BLUE ) ;
          EventManager manager = new EventManager ();
          Point [] pts = new Point[10];
          Point [] vitesses = new Point[10];
          Random rand = new Random();
          for (int i=0; i<10; i++){
            pts[i] = new Point(rand.nextInt(500), rand.nextInt(500));
            vitesses[i] = new Point(10, 6);
          }
          int r = 15;
          Balls balls= new Balls(pts, vitesses, r);
          BallsSimulator drawBalls = new BallsSimulator (balls, gui, manager);
          gui.setSimulable (drawBalls) ;
     }
}
