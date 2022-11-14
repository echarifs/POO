import java.awt.Color;

import gui.*;

public class ProiesPredateursSimulator implements Simulable {
  private Proies proies;
  private Predateurs predateurs;
  private  GUISimulator window;
  private EventManager manager;

  public ProiesPredateursSimulator(Proies proies, Predateurs predateurs, GUISimulator window, EventManager manager){
    this.proies = proies;
    this.predateurs = predateurs;
    this.window = window;
    this.manager = manager;
    this.draw();
  }
  public void draw(){
		int n = this.proies.getPositions().length;
    int m = this.predateurs.getPositions().length;
		for ( int i = 0; i<n; i++) {
			int x = proies.getPositions()[i].x;
			int y = proies.getPositions()[i].y;
      int xh = this.proies.getHeads()[i].x;
      int yh = this.proies.getHeads()[i].y;
      this.window.addGraphicalElement(new Oval(xh, yh, Color.BLUE, Color.BLUE, 6));
			this.window.addGraphicalElement(new Oval(x, y, Color.YELLOW, Color.YELLOW, this.proies.getRayon()*2));
		}
    for ( int i = 0; i<m; i++) {
			int x = predateurs.getPositions()[i].x;
			int y = predateurs.getPositions()[i].y;
      int xh = predateurs.getHeads()[i].x;
      int yh = predateurs.getHeads()[i].y;
      window.addGraphicalElement(new Oval(xh, yh, Color.BLUE, Color.BLUE, 6));
			window.addGraphicalElement(new Oval(x, y, Color.RED, Color.RED, this.predateurs.getRayon()*2));
		}
	}
  @Override
  public void next() {
    this.manager.next();
    this.manager.addEvent(new ProiesEvent(this.manager.getCurrentDate()+ this.proies.getPas(), this.proies, this.predateurs, window));
    this.manager.addEvent(new PredateursEvent(this.manager.getCurrentDate()+ this.predateurs.getPas(), this.proies, this.predateurs, window));
    this.window.reset();
    this.draw();
  }

  @Override
  public void restart() {
    this.proies.reInit();
    this.predateurs.reInit();
    this.manager.restart();
    window.reset();
    this.draw();
  }
}
