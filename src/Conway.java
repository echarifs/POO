import java.util.ArrayList;
import java.util.HashSet;
import java.awt.Point;
import java.lang.Math;


public class Conway extends Games{
	public Conway(ArrayList<Point> vivant, int size, int width, int height) {
		super(vivant, "Conway",2,size, width, height);
	}

	@Override
	public int nextEtat(Point p) {
		int s = 0;
		for (Point i : voisins(p)) {
			s += this.rectangles.get(i);
		}
		if (this.rectangles.get(p)==0 && s ==3) {
			return 1;
		}
		else if (this.rectangles.get(p)==1 && (s==2 || s==3) ){
			return 1;
		}
		return 0;
	}
	
	@Override
	public void rules() {
		HashSet<Point> changes = new HashSet<>();
		HashSet<Point> traite = new HashSet<>();
		HashSet <Point> vivants = new HashSet<>();
		for ( Point p : rectangles.keySet()) {
			if(rectangles.get(p) == 1){
				vivants.add(p);
			}
		}
		for (Point k : vivants) {
			if (! traite.contains(k)) {
				traite.add(k);
			}
			for (Point p : this.voisins(k)){
				if (! traite.contains(p)) {
					traite.add(p);
				}
			}
		for(Point p : traite) {
			int etat = nextEtat(p);
			if ( etat != this.rectangles.get(p)) {
					changes.add(p);
			}
			}
		}
		for (Point p : changes) {
			int etat = rectangles.get(p);
			rectangles.replace(p, Math.floorMod(etat+1,2));
		}
	}
}
