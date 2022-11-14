import java.util.ArrayList;
import java.awt.Point;
import java.util.HashSet;
import java.lang.Math;

public class Immigration extends Games {
	
	public Immigration(ArrayList<Point> points, int nbEtats, int size, int width, int height) {
		super(points, "Immigration", nbEtats, size, width, height);		
	}

	@Override
	public int nextEtat(Point p) {
		int s = 0;
		int etat = this.rectangles.get(p);
		int Netat = Math.floorMod(etat+1,n);
		for (Point i : voisins(p)) {
			int c = 0;
			if (this.rectangles.get(i)==Netat) {
				c = 1;
			}
			s += c;
		}
		if (s >=3) {
			return Netat;
		}
		return etat;
	}
	
	@Override
	public void rules() {
		HashSet<Point> changes = new HashSet<>();
		for ( Point p : rectangles.keySet()) {
				int k = nextEtat(p);
				if (k == Math.floorMod(rectangles.get(p)+1,n) ) {
					changes.add(p);
				}
			}
		for (Point p : changes) {
			int etat = rectangles.get(p);
			rectangles.replace(p, Math.floorMod(etat+1,n));
			}
		}

}
