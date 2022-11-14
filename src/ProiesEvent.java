import gui.*;

public class ProiesEvent extends Event{
   private Proies proies;
   private Predateurs predateurs;
   private GUISimulator window;

  public ProiesEvent(long date, Proies proies, Predateurs predateurs, GUISimulator window){
    super(date);
    this.proies = proies;
    this.window = window;
    this.predateurs = predateurs;
  }

  @Override
  public void execute(){
    this.proies.move(window.getPanelWidth(), window.getPanelHeight(), this.predateurs);

  }
}
