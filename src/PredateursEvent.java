import gui.*;

public class PredateursEvent extends Event{
   private Proies proies;
   private Predateurs predateurs;
   private GUISimulator window;

  public PredateursEvent(long date, Proies proies, Predateurs predateurs, GUISimulator window){
    super(date);
    this.proies = proies;
    this.window = window;
    this.predateurs = predateurs;
  }

  @Override
  public void execute(){
    this.predateurs.move(window.getPanelWidth(), window.getPanelHeight(), this.proies);

  }
}
