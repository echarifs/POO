import gui.*;

public class BallsEvent extends Event{
  private Balls balls;
  private GUISimulator window; // la fenetre de la simulation


  public GUISimulator getWindow(){
    return window;
  }
  public Balls getBalls(){
    return balls;
  }
  public BallsEvent(long date, Balls balls, GUISimulator window){
    super(date);
    this.balls = balls;
    this.window = window;
  }

  public void execute(){
    // on change les vitesses et on translate les balls
    this.balls.update_vitesses(window.getPanelWidth(), window.getPanelHeight());
		this.balls.move();
  }
}
