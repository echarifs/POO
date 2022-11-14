import gui.*;

public class BoidsEvent extends BallsEvent{

  public BoidsEvent(long date, Balls boids, GUISimulator window){
    super(date, boids, window);
  }

  @Override
  public void execute(){
    ((Boids) this.getBalls()).move(this.getWindow().getPanelWidth(), this.getWindow().getPanelHeight());
  }
}
