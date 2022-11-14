import java.awt.Color;

import gui.*;

public class BallsSimulator implements Simulable {
	private  Balls balls;
	private  GUISimulator window;
	private EventManager manager; // le gestionneur des evenements

	public BallsSimulator(Balls balls, GUISimulator window, EventManager manager) {
		super();
		this.balls = balls;
		this.window = window;
		this.manager = manager;
	  this.draw();

	}

	public Balls getBalls(){
		return balls;
	}

	public GUISimulator getWindow(){
		return window;
	}

	public EventManager getManager(){
		return manager;
	}

	public void draw(){
		// fonction qui dessine les balls
		int n = this.balls.getPositions().length;
		for ( int i = 0; i<n; i++) {
			int x = balls.getPositions()[i].x;
			int y = balls.getPositions()[i].y;
			this.window.addGraphicalElement(new Oval(x, y, Color.YELLOW, Color.YELLOW, this.balls.getRayon()*2));
		}
	}

	@Override
	public void next() {
		// a chaque appel a next on appelle la fonction next du manager
		// et on ajoute l'evenment suivant dans l'ensemble des evenements
		this.manager.next();
		this.manager.addEvent(new BallsEvent(this.manager.getCurrentDate()+1, balls, window));
		window.reset();
		this.draw();
	}

	@Override
	public void restart() {
		this.balls.reInit();
		this.manager.restart();
		window.reset();
		this.draw();
	}

}
