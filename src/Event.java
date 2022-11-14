
public abstract class Event{
  long date;

  public Event(long date){
    this.date = date;
  }

  public long getDate(){
    return date;
  }
  public abstract void execute();

}
