import java.awt.Color;
import gui.*;

public class BoidsSimulator extends BallsSimulator {

  public BoidsSimulator(Boids boids, GUISimulator window, EventManager manager) {
		super(boids, window, manager);
	}

  public void draw(){
    super.draw();
    int n = this.getBalls().getPositions().length;
    for ( int i = 0; i<n; i++) {
      int xh = ((Boids) this.getBalls()).getHeads()[i].x;
    	int yh = ((Boids) this.getBalls()).getHeads()[i].y;
      this.getWindow().addGraphicalElement(new Oval(xh, yh, Color.BLUE, Color.BLUE, 6));
    }
  }

  @Override
	public void next() {
    this.getManager().next();
    this.getManager().addEvent(new BoidsEvent(this.getManager().getCurrentDate()+((Boids) this.getBalls()).getPas(), this.getBalls(), this.getWindow()));
		this.getWindow().reset();
		this.draw();
	}

}
