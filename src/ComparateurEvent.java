import java.util.*;
// la relation d'ordre de l'ensemble des evenements
class ComparateurEvent implements Comparator<Event> {

     @Override
     public int compare(Event e1, Event e2) {
          if (e1==e2) return 0;
          if (e1.getDate() <= e2.getDate()) return 1;
          return -1;
     }
 }
