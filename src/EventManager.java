import java.util.*;

public class EventManager {
  private long currentDate;
  private TreeSet<Event> setEvents; // ensemble ordonne des evenements par le temps de chacune


  public long getCurrentDate(){
    return currentDate;
  }
  public EventManager(){
    this.currentDate = 0;
    this.setEvents = new TreeSet<Event>(new ComparateurEvent());
  }

  public void addEvent(Event event){
    this.setEvents.add(event);
  }

  public void next(){
    this.currentDate++; // on incremente le temps courant
    // notre ensemble est ordonné donc on prend les derniers evenements jusqu'à la date courante
    while((!setEvents.isEmpty()) && (setEvents.last().getDate() <= currentDate)){
      this.setEvents.last().execute();
      this.setEvents.remove(setEvents.last());
    }

  }

  public boolean isFinished(){
    return setEvents.isEmpty();
  }
  public void restart(){
    this.currentDate = 0;
    this.setEvents = new TreeSet<Event>(new ComparateurEvent());
  }

}
